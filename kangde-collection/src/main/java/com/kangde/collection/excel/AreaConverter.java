package com.kangde.collection.excel;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.mapper.AreaMapper;
import com.kangde.collection.model.AreaModel;
import com.kangde.commons.util.excel.ExcelException;
import com.kangde.commons.util.excel.ResolveFieldValueConverter;
import com.kangde.commons.util.excel.vo.FieldValue;
import com.kangde.sys.mapper.OrganizationMapper;
import com.kangde.sys.model.OrganizationModel;

/**
 * 催收区域转换器
 * @author lisuo
 *
 */
@Component
public class AreaConverter implements ResolveFieldValueConverter {
	
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Override
	public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum)
			throws Exception {
		if(type==Type.IMPORT){
			CaseExcelDto caseExcelDto = (CaseExcelDto) bean;
			List<AreaModel> areas = areaMapper.findByName(value.toString());
			if(CollectionUtils.isEmpty(areas)){
				StringBuilder err = new StringBuilder()
				.append("第[").append(rowNum).append("行],[")
				.append(fieldValue.getTitle()).append("]")
				.append("没有找到匹配的催收区域["+value.toString()+"]");
				throw new ExcelException(err.toString());
			}
			if(areas.size() > 1){
				StringBuilder err = new StringBuilder()
				.append("第[").append(rowNum).append("行],[")
				.append(fieldValue.getTitle()).append("]")
				.append("无法精准定位["+value.toString()+"]")
				.append("在系统区域配置中找到了["+areas.size()+"]条,[");
				for(AreaModel area:areas){
					err.append(area.getName());
					err.append(",");
				}
				err.delete(err.length()-1, err.length());
				err.append("]请按照对应的区域进行修改,如有疑问,请与系统管理员联系");
				throw new ExcelException(err.toString());
			}
			AreaModel area = areas.get(0);
			//如果催收区域没有找到对应的机构,业务不确定（挂在北京,还是抛出错误）
			if(StringUtils.isBlank(area.getOrgId())){
				StringBuilder err = new StringBuilder()
				.append("第[").append(rowNum).append("行],[")
				.append(fieldValue.getTitle()).append("],[")
				.append(value.toString())
				.append("]没有对应的公司部门监管,如有疑问,请与系统管理员联系");
				throw new ExcelException(err.toString());
			}
			//关联组织（催收公司）
			caseExcelDto.setOrgId(area.getOrgId());
			caseExcelDto.setAreaId(area.getId());
			caseExcelDto.setAreaName(area.getName());
			OrganizationModel org = organizationMapper.findById(area.getOrgId());
			if(org==null){
				throw new ExcelException("在["+area.getName()+"],没有找到对应的机构,请核对区域数据");
			}
			caseExcelDto.setOrgName(org.getName());
		}
		return value;
	}
	
}
