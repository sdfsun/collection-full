package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.EntrustModel;

/**
 * 委托方mapper
 * @author zhangyj
 * @date 2016.6.1
 *
 */
public interface EntrustMapper extends BaseMapper<EntrustModel,String> {
	
	/**
	 * 启用 停用 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(Map<String, Object> params);
	
	String findCodeById(String id);
	
	
	List<EntrustModel> findAll2();
	
	
	List<EntrustModel> findByIds2(List<String> ids);
	
	/**
	 * 通过
	 * @param 
	 */
	EntrustModel findByName(String name);
	EntrustModel findByCode(String code);
}