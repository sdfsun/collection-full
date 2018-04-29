package com.kangde.sys.mapper;

import java.util.List;
import java.util.Map;

import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.dto.EntrustDto;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.model.EntrustProduct;

/**
 * 委托方mapper
 * @author zhangyj
 * @date 2016.6.1
 *
 */
public interface EntrustProductMapper extends BaseMapper<EntrustProduct,String> {
	
	/**
	 * 营业管理  显示主页面信息
	 * @param condition
	 * @return
	 */
	List<EntrustProduct> queryProduct(ParamCondition condition);
	
	/**
	 * 启用 停用 修改状态 
	 * @param model
	 * @return
	 */
	int updateForStatus(Map<String, Object> params);
	
	String findCodeById(String id);
	
	EntrustProduct findByCode(String code);
	
	
	List<EntrustModel> findAll2();
	
	
	List<EntrustModel> findByIds2(List<String> ids);
}