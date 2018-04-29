package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustCaseSource;
import com.kangde.sys.model.EntrustModel;

/**
 * 委托方mapper
 * @author zhangyj
 * @date 2016.6.1
 *
 */
public interface EntrustCaseSourceMapper extends BaseMapper<EntrustCaseSource,String> {
	
	/**
	 * 启用 停用 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(Map<String, Object> params);
	
	String findCodeById(String id);
	
	
	List<EntrustCaseSource> findAll2();
	
	
	List<EntrustCaseSource> findByIds2(List<String> ids);

	EntrustCaseSource findByName(String name);

	EntrustCaseSource findByCode(String code);
	
	/**
	 * 通过组织机构查询用户信息
	 * @param orgId 限制的机构ID
	 * @return
	 */
	List<EntrustCaseSource> findSourcesByEnId(Map<String, Object> params);
}