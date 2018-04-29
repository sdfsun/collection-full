package com.kangde.collection.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.model.CaseBatchModel;
import com.kangde.commons.mapper.BaseMapper;
/**
 * 职位mapper
 * @author wcy
 * @date 2016年5月9日11:19:10
 *
 */
public interface CaseBatchMapper extends BaseMapper<CaseBatchModel,String> {
	/**
	 * 撤案 恢复案件 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(CaseBatchModel model);
	/**
	 * 批次软删除
	 * @param model
	 * @return
	 */
	int softDelete(@Param(value="id") String id,@Param(value="status") Integer status);
	
	/**
	 * 查找委案截止日期小于now的
	 * @param now
	 * @return
	 */
	List<String> findIdWithEnddate(Date now);
	
	/**
	 * 更新定时跑批委案到期
	 * @param caseBatch
	 */
	int updateForTimer(CaseBatchModel caseBatch);
	/**
	 * 通过批次号查找批次
	 * @param caseBatch
	 */
	CaseBatchModel findByBatchCode(String batchCode);
	
	/**
	 * 批次号下拉
	 */
	public List<CaseBatchModel> findBatchCode();
}