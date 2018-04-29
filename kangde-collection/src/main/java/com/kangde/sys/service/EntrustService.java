package com.kangde.sys.service;

import java.util.List;

import com.kangde.commons.service.BaseService;
import com.kangde.sys.model.EntrustModel;

/**
 *  委托方Service
 * @author zhangyj
 */
public interface EntrustService extends BaseService<EntrustModel,String>{
	/**
	 * 启用 停用
	 * @param model
	 * @return
	 */
	public int updateForStatus(String[] ids);
	
	public int updateForStatusNo(String[] ids);

}
