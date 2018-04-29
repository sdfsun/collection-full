package com.kangde.commons.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.kangde.commons.CoreConst;
import com.kangde.commons.exception.dao.OptimisticLockingFailureException;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.model.BaseModel;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.SQLUtil;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;

/**
 * 抽象服务实现类
 * @author lisuo
 *
 * @param <T> (数据库表Model)泛型
 * @param <PK> 主键类型
 */
public abstract class AbstractService<T extends BaseModel<PK>,PK extends Serializable> extends SimpleService implements BaseService<T,PK>{
	
	/** 注入泛型Mapper */
	@Autowired
	protected BaseMapper<T,PK> singleMapper;

	//PK泛型是否是String
	protected boolean pkIsString = false;

	public AbstractService() {
		Class<?> clazz = ReflectUtil.getSuperClassGenricType(this.getClass(), 1);
		if(String.class == clazz){
			this.pkIsString = true;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T save(T model) {
		if(pkIsString){
			PK id = (PK) UUIDUtil.UUID32();
			model.setId(id);//ID生成
		}
		if(model.getCreateTime() == null){
			model.setCreateTime(new Date());//创建时间
		}
		if(model.getStatus() == null){
			model.setStatus(CoreConst.STATUS_NORMAL);//系统状态,默认正常
		}
		model.setVersion(0);
		singleMapper.save(model);
		return model;
	}
	
	@Override
	public int batchSave(List<? extends T> models) {
		Class<?> clazz = getMybatisProxyClass(singleMapper);
		String sql = SQLUtil.getSql(clazz, "save",null);
		Connection connection = SQLUtil.getConnection();
		PreparedStatement prst = null;
		try{
			prst = connection.prepareStatement(sql);
			int res = SQLUtil.executeBatch(sql, models, SQLUtil.Type.INSERT, prst, 5000);
			return res;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			SQLUtil.close(prst);
		}
	}

	@Override
	public T update(T model) {
		model.setModifyTime(new Date());//修改时间
		int i = singleMapper.update(model);
		if(i==0){
			throw new OptimisticLockingFailureException("操作失败,数据可能已被其他用户修改或删除,请【刷新数据后重新操作】");
		}
		return model;
	}

	@Override
	public void delete(T model) {
		singleMapper.delete(model);
	}

	@Override
	public void deleteById(PK id) {
		singleMapper.deleteById(id);
	}

	@Override
	public void deleteByIds(List<PK> ids) {
		singleMapper.deleteByIds(ids);
	}

	@Override
	public T findById(PK id) {
		return singleMapper.findById(id);
	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		return singleMapper.findByIds(ids);
	}
	
	@Override
	public List<T> findAll() {
		return singleMapper.findAll();
	}

	@Override
	public List<T> query(ParamCondition condition) {
		//清空分页信息
		condition.clearPager();
		return singleMapper.query(condition);
	}

	
	@Override
	public SearchResult<T> queryForPage(ParamCondition condition) {
		List<T> list = singleMapper.query(condition);
		SearchResult<T> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	/**
	 * 通过字段查询数据,表达式为=,非BaseService方法
	 * @param fieldName
	 * @param fieldValue
	 * @return 查询结果集合List<泛型>
	 */
	protected List<T> findByField(String fieldName, Object fieldValue) {
		return this.findByField(fieldName, fieldValue, "=");
	}
	
	/**
	 * 通过字段查询数据,非BaseService方法
	 * @param fieldName
	 * @param fieldValue
	 * @param expression SQL表达式  (=,!=,like) 等...
	 * @return 查询结果集合List<泛型>
	 */
	protected List<T> findByField(String fieldName, Object fieldValue,String expression) {
		Map<String,Object> map = new HashMap<>(3);
		map.put("fieldName", fieldName);
		map.put("fieldValue", fieldValue);
		map.put("expression", expression);
		return singleMapper.findByField(map);
	}
	
	/**
	 * 检查是否有重复数据
	 * @param id 排除的ID（自身）,可以为空（既:不排除）
	 * @param fieldName 字段名称
	 * @param value 字段值
	 * @return 是否有重复数据(true:有,false:没有)
	 */
	protected boolean hasDuplicate(String id, String fieldName, Object value){
		List<T> list = findByField(fieldName, value);
		if(CollectionUtils.isEmpty(list)){//如果没有相关的数据,则不会重复
			return false;
		}
		if(StringUtils.isBlank(id)){//如果有相关数据,则传入的id无效,则有重复
			return true;
		}else{
			//存在排除ID
			for(T model : list){
				if(!id.equals(model.getId())){//id与存在数据的id不同,则有重复
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 获取mybatis 代理对象代理的真实类型
	 * @param proxy
	 * @return
	 */
    @SuppressWarnings("rawtypes")
	private static Class getMybatisProxyClass(Object proxy){  
    	try{
    		MapperProxy m = (MapperProxy) ReflectUtil.getPrivateProperty(proxy, "h");
	        Object o = ReflectUtil.getPrivateProperty(m, "mapperInterface");
	        return  Class.forName(ReflectUtil.getPrivateProperty(o, "name").toString());
    	}catch(Exception e){
    		throw new RuntimeException(e);
    	}
    }  
	

	

}
