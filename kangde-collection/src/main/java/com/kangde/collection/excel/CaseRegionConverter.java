package com.kangde.collection.excel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.model.RegionModel;
import com.kangde.collection.service.RegionService;
import com.kangde.commons.util.CoreUtil;
import com.kangde.commons.util.excel.ExcelException;
import com.kangde.commons.util.excel.ResolveFieldValueConverter;
import com.kangde.commons.util.excel.vo.FieldValue;

/**
 * 案件省市Excel 转换器
 * @author lisuo
 *
 */
@Component
public class CaseRegionConverter implements ResolveFieldValueConverter {
	
	@Autowired
	private RegionService regionService;
	
	@Override
	public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum)
			throws Exception {
		if(type==Type.IMPORT){
			CaseExcelDto caseExcelDto = (CaseExcelDto) bean;
			String name = fieldValue.getName();
			List<RegionModel> regions;
			if("provinceName".equals(name)){
				regions = regionService.findByProvinceName(value.toString());
				
			}else{
				regions = regionService.findByCityName(value.toString());
			}
			//是否存在对应的地址
			if(CollectionUtils.isEmpty(regions)){
				StringBuilder err = new StringBuilder()
				.append("第[").append(rowNum).append("行],[")
				.append(fieldValue.getTitle()).append("]")
				.append("["+value.toString()+"]在系统地址表没有找到,请核对！");
				throw new ExcelException(err.toString());
			}else{
				//如果查找出多个地址
				if(regions.size() > 1){
					StringBuilder err = new StringBuilder()
					.append("第[").append(rowNum).append("行],[")
					.append(fieldValue.getTitle()).append("]")
					.append("无法精准定位["+value.toString()+"]")
					.append("在系统配置地址中找到了["+regions.size()+"]条,[");
					for(RegionModel area:regions){
						err.append(area.getName());
						err.append(",");
					}
					err.delete(err.length()-1, err.length());
					err.append("]请按照正确的地址进行修改,如有疑问,请与系统管理员联系");
					throw new ExcelException(err.toString());
				}
				//只存在一个地址
				RegionModel one = CoreUtil.getOne(regions);
				if("provinceName".equals(name)){
					caseExcelDto.setProvinceId(one.getId());
					caseExcelDto.setProvinceName(one.getName());
				}else{
					//核对省份是否正确
					if(caseExcelDto.getProvinceId()!=null){
						if(!caseExcelDto.getProvinceId().equals(one.getParentId())){
							StringBuilder err = new StringBuilder()
							.append("第[").append(rowNum).append("行],[")
							.append(fieldValue.getTitle()).append("]")
							.append("["+value.toString()+"]")
							.append("与案件区域-省份["+caseExcelDto.getProvinceName()+"]不匹配");
							throw new ExcelException(err.toString());
						}
					}
					caseExcelDto.setCityId(one.getId());
					caseExcelDto.setCityName(one.getName());
				}
			}
		}
		return value;
	}
	
}
