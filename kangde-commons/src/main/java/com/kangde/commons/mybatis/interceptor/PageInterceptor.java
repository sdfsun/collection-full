package com.kangde.commons.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.vo.ParamCondition;

/**
 * MyBatis分页拦截器
 * @author lisuo
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
@SuppressWarnings("rawtypes")
public class PageInterceptor implements Interceptor {

	/** mysql,oracle... 数据库语言*/
	private static String dialect;
	
	private static final String MYSQL = "mysql";
	private static final String ORACLE = "oracle";

	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			BoundSql boundSql = statementHandler.getBoundSql();
			Object param = boundSql.getParameterObject();
			if (param != null && param instanceof ParamCondition) {
				ParamCondition condition = (ParamCondition) param;
				// 含有分页就进行分页处理
				if (condition.hashPager()) {
					String originalSql = boundSql.getSql();
					MappedStatement mappedStatement = (MappedStatement) ReflectUtil
							.getPrivateProperty(statementHandler.getParameterHandler(), "mappedStatement");
					//设置查询出的记录条数
					String DSJ=condition.get("DSJ");
					condition.setTotal(queryTotal(invocation, mappedStatement, boundSql, param, originalSql,DSJ));
					//重置SQL
					ReflectUtil.setPrivateProperty(boundSql, "sql", pageSql(originalSql, condition));
				}
			}
		}
		return invocation.proceed();

	}

	/**
	 * 为count语句设置参数.
	 * 
	 * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler#setParameters(PreparedStatement)
	 * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler#setParameters(PreparedStatement)
	 * 
	 * @param preparedStatement
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> mappings = boundSql.getParameterMappings();
		if (CollectionUtils.isEmpty(mappings)) {
			return;
		}
		Configuration configuration = mappedStatement.getConfiguration();
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
		for (int i = 0; i < mappings.size(); i++) {
			ParameterMapping parameterMapping = mappings.get(i);
			if (parameterMapping.getMode() != ParameterMode.OUT) {
				Object value;
				String propertyName = parameterMapping.getProperty();
				PropertyTokenizer prop = new PropertyTokenizer(propertyName);
				if (parameterObject == null) {
					value = null;
				} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
					value = parameterObject;
				} else if (boundSql.hasAdditionalParameter(propertyName)) {
					value = boundSql.getAdditionalParameter(propertyName);
				} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
						&& boundSql.hasAdditionalParameter(prop.getName())) {
					value = boundSql.getAdditionalParameter(prop.getName());
					if (value != null) {
						value = configuration.newMetaObject(value)
								.getValue(propertyName.substring(prop.getName().length()));
					}
				} else {
					value = metaObject == null ? null : metaObject.getValue(propertyName);
				}
				TypeHandler typeHandler = parameterMapping.getTypeHandler();
				if (typeHandler == null) {
					throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
							+ " of statement " + mappedStatement.getId());
				}
				typeHandler.setParameter(preparedStatement, i + 1, value, parameterMapping.getJdbcType());
			}
		}
	}

	/**
	 * 生成特定数据库的分页语句
	 * 
	 * @param originalSql
	 * @param condition
	 * @return 分页SQL
	 */
	private String pageSql(String originalSql, ParamCondition condition) {
		StringBuilder sb = new StringBuilder();
		int pageSize = condition.getLimit(); // 每页显示记录数
		int pageNo = condition.getOffset(); // 第几页
		if (MYSQL.equals(dialect)) {
			sb.append(originalSql);
			sb.append(" limit " + new Integer((pageNo - 1) * pageSize) + "," + pageSize);
		} else if (ORACLE.equals(dialect)) {
			sb.append("select * from (select pagetable.*,ROWNUM AS rowcounter from (");
			sb.append(originalSql);
			sb.append(")  pagetable) subt where subt.rowcounter>");
			sb.append(new Integer((pageNo - 1) * pageSize));
			sb.append(" AND subt.rowcounter<=");
			sb.append(new Integer(pageNo * pageSize));
			sb.append(" and rownum <=" + pageSize);// 解决海量数据分页
		} else {
			throw new IllegalArgumentException("分页插件不支持此数据库：" + dialect);
		}
		return sb.toString();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		if(StringUtils.isBlank(p.getProperty("dialect"))){
			throw new IllegalArgumentException("dialect 配置不能为空");
		}
		dialect = p.getProperty("dialect").toLowerCase();
	}

	/**
	 * 查询总数
	 * 
	 * @param invocation
	 * @param mappedStatement
	 * @param boundSql
	 * @param param
	 * @param originalSql
	 * @return count
	 * @throws SQLException
	 */
	private int queryTotal(Invocation invocation, MappedStatement mappedStatement, BoundSql boundSql, Object param,
			String originalSql,String DSJ) throws SQLException {
		String countSql=null;
		String distinctCount = "";
		if(StringUtils.isNotBlank(DSJ)){
			if(DSJ.equals("DSJ")){
				distinctCount = "count(distinct pr.id)";
				countSql="select "+distinctCount+originalSql.substring(originalSql.indexOf("FROM"));
				 
			}else{
				countSql = "select count(*) from (" + originalSql + ") as a";
			}
		}else{
			countSql = "select count(*) from (" + originalSql + ") as a";
		}
		
		Connection conn = (Connection) invocation.getArgs()[0];
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int count = 0;
		try {
			stmt = conn.prepareStatement(countSql);
			setParameters(stmt, mappedStatement, boundSql, param);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
		}
		return count;
	}

}