package com.kangde.collection.service;


import java.util.List;

import com.kangde.collection.model.CaseBatchModel;
import com.kangde.commons.service.BaseService;

/**
 * 批次Service
 * @author zhangyj
 * @date 2016年5月9日
 */
public interface CaseBatchService extends BaseService<CaseBatchModel,String>{
	/**
	 * 撤案 恢复案件
	 * @param model
	 * @return
	 */
	public int updateForStatus(CaseBatchModel model);
	/**
	 * 软删除
	 * @param model
	 * @return
	 */
	//public int softDelete(String id);
	public void deleteByBatchId(String id);
	/**
	 * 生成批次号
	 * @param model
	 * @return
	 */
	public String createBatchCode(CaseBatchModel model);
	
	/**
	 * 批次号下拉
	 */
	public List<CaseBatchModel> findBatchCode();
	
	/**
	 * 批次号分解
	 */
	String BatchCode(String Code);
	
}
