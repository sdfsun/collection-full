package com.kangde.collection.excel;

import org.apache.commons.lang.CharSetUtils;
import org.springframework.stereotype.Component;

import com.kangde.commons.util.excel.ResolveFieldValueConverter;
import com.kangde.commons.util.excel.vo.FieldValue;

/**
 * 去除Excel特殊字符转换器
 * @author lisuo
 *
 */
@Component
public class TrimConverter implements ResolveFieldValueConverter {

	@Override
	public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum)
			throws Exception {
		if(value instanceof String){
			return CharSetUtils.delete(value.toString(), "‘");
		}
		return value;
	}

}
