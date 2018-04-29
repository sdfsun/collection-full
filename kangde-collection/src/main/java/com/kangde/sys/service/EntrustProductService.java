package com.kangde.sys.service;

import com.kangde.collection.model.CaseBatchModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.dto.EntrustDto;
import com.kangde.sys.model.EntrustProduct;

/**
 *  委托方Service
 * @author zhangyj
 */
public interface EntrustProductService extends BaseService<EntrustProduct,String>{
	
	/**
	 * 显示營業
	 * @param condition
	 * @return
	 */
	public SearchResult<EntrustProduct> queryProduct(ParamCondition condition);
	/**
	 * 自动生成全简码
	 * @param condition
	 * @return
	 */
	public String createWholeCode(EntrustProduct model);
	
	/**
	 * 启用 停用
	 * @param model
	 * @return
	 */
	public int updateForStatus(String[] ids);
	
	public int updateForStatusNo(String[] ids);
}
