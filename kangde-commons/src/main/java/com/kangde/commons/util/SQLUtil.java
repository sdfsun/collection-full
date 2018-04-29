package com.kangde.commons.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.kangde.commons.web.filter.SpringUtil;

/**
 * SQL工具类
 * @author lisuo
 *
 */
public abstract class SQLUtil {
	
	/**
	 * 操作类型：INSERT,UPDATE
	 */
	public enum Type{
		INSERT,UPDATE
	}
	
	private static Map<Class<?>,Reflector> cacheReflectors = new HashMap<>();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSourceTransactionManager transactionManager = SpringUtil.getBean("transactionManager", DataSourceTransactionManager.class);
	private static SqlSessionFactory sqlSessionFactory = SpringUtil.getBean("sqlSessionFactory",SqlSessionFactory.class);;
	
	/**
	 * 把一个字符串以,号分割,转换成符合SQL'1','2'形式的字符串
	 * 假设传入参数为 "a,b,c" 那么返回 "'a','b','f'"
	 * @param source
	 * @return
	 */
	public static String warpSql(String source){
		Validate.notNull(source, "source 参数不能为空");
		StringBuilder temp = new StringBuilder();
		String[] arr = StringUtils.split(source,",");
		for(String str:arr){
			temp.append("'"+str+"',");
		}
		temp.delete(temp.length()-1,temp.length());
		return temp.toString();
	}
	
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource() {
		return transactionManager.getDataSource();
	}
	
	/**
	 * 获取Connection,线程安全
	 * @param autoCommit 是否自动提交
	 * @return
	 */
	public static Connection getConnection(boolean autoCommit) {
		Connection connection = tl.get();
		try {
			if (connection == null) {
				connection = getDataSource().getConnection();
			}
			connection.setAutoCommit(autoCommit);
			tl.set(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
	/**
	 * 获取Connection,线程安全,自动提交默认为false
	 * @return
	 */
	public static Connection getConnection() {
		return getConnection(false);
	}
	
	/**
	 * 关闭Connection
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				tl.remove();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 * @param prst
	 */
	public static void close(ResultSet rs,PreparedStatement prst){
		close(rs);
		close(prst);
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 * @param prst
	 */
	public static void close(PreparedStatement prst){
		try{
			if(prst!=null){
				prst.close();
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 */
	public static void close(ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 提交
	 */
	public static void commit(Connection connection){
		if(connection!=null){
			try {
				connection.commit();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 回滚
	 */
	public static void rollback(Connection connection){
		if(connection!=null){
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 执行批量操作
	 * @param sql SQL语句
	 * @param list 数据集合
	 * @param type 执行类型,insert OR update
	 * @param prst PreparedStatement
	 * @param batchSize 每执行多少条进行一次批次操作
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public static int executeBatch(String sql,List list,Type type,PreparedStatement prst,int batchSize) throws SQLException{
		int result = 0;
		if(CollectionUtils.isNotEmpty(list)){
			Class clazz = list.get(0).getClass();
			Reflector reflector = cacheReflectors.get(clazz);
			if(reflector == null){
				reflector = new Reflector(clazz);
				cacheReflectors.put(clazz, reflector);
			}
			if(Type.INSERT==type){
				int idx = sql.indexOf("values");
				String prefix = sql.substring(0, idx);
				prefix = prefix.substring(prefix.indexOf("(")+1, prefix.indexOf(")"));
				List<String> names = new ArrayList<>();
				String[] columns = prefix.split(",");
				for(String column:columns){
					column = column.trim().replace("_", "");
					String name = reflector.findPropertyName(column);
					if(column == null){
						throw new RuntimeException("没有找到["+column+"]的属性");
					}
					names.add(name);
				}
				int index = 1;
				//便利数据
				for(Object obj:list){
					//便利数据字段属性
					for(int i=0;i<names.size();i++){
						String name = names.get(i);
						Object val = ReflectUtil.getProperty(obj, name);
						prst.setObject((i+1), val);
					}
					prst.addBatch();
					if(index%batchSize==0){
						int[] res = prst.executeBatch();
						for(int re:res){
							result += re;
						}
						prst.clearBatch();
					}
					index++;
				}
				int[] res = prst.executeBatch();
				for(int re:res){
					result += re;
				}
			}else if(Type.UPDATE == type){
				
			}
		}else{
			throw new RuntimeException("没有数据进行："+type.toString());
		}
		return result;
	}
	
	/**
	 * 获取MapperSQL语句
	 * @param clazz Mapper
	 * @param name Mapper id
	 * @param parameterObject Mapper参数
	 * @return
	 */
	public static String getSql(Class<?> clazz,String name,Object parameterObject){
		String id = clazz.getName()+"."+name;
		MappedStatement stat = sqlSessionFactory.getConfiguration().getMappedStatement(id);
		BoundSql boundSql = stat.getBoundSql(parameterObject);
		return boundSql.getSql();
	}
	
}
