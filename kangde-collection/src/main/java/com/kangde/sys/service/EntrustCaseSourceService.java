package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.model.EntrustCaseSource;

/**
 *  委托方Service
 * @author zhangyj
 */
public interface EntrustCaseSourceService extends BaseService<EntrustCaseSource,String>{
	/**
	 * 启用 停用
	 * @param model
	 * @return
	 */
	public int updateForStatus(String[] ids);
	
	public int updateForStatusNo(String[] ids);

	/**
	 * 通过组织机构查询用户信息
	 * @return
	 */
	List<EntrustCaseSource> findSourceByEnId(String entrustId);
	List<EntrustCaseSource> findSourceByEnId(EntrustCaseSource entrustCaseSource);
}
