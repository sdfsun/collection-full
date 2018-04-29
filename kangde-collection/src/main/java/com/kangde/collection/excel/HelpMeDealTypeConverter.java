package com.kangde.collection.excel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kangde.collection.dto.CaseApplyDto;
import com.kangde.collection.model.CaseApplyModel;
import com.kangde.collection.service.HelpMeService;
import com.kangde.commons.util.excel.ExcelException;
import com.kangde.commons.util.excel.ResolveFieldValueConverter;
import com.kangde.commons.util.excel.vo.FieldValue;

/**
 * 导入协催类型转换器
 * @author lisuo
 *
 */
@Component
public class HelpMeDealTypeConverter implements ResolveFieldValueConverter {
	
	@Autowired
	private HelpMeService helpMeService;

	@Override
	public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum)
			throws Exception {
		CaseApplyDto dto = (CaseApplyDto) bean;
		//导入处理
		if(Type.IMPORT.equals(type)){
			String val = value.toString();
			if(StringUtils.isNotBlank(val)){
				//获取数据类型
				CaseApplyModel hp = helpMeService.findById(val);
				if(hp==null){
					StringBuilder err = new StringBuilder()
					.append("第[").append(rowNum).append("行],[")
					.append(fieldValue.getTitle()).append("]")
					.append("没有找到ID为["+value.toString()+"]协催记录");
					throw new ExcelException(err.toString());
				}
				dto.setApplyType(hp.getApplyType());
			}
		}
		return value;
	}
	
}
