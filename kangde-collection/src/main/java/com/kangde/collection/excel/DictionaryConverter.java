package com.kangde.collection.excel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.kangde.commons.util.excel.ResolveFieldValueConverter;
import com.kangde.commons.util.excel.vo.FieldValue;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.util.DictUtil;

/**
 * 字典转换器
 * @author lisuo
 *
 */
@Component
public class DictionaryConverter implements ResolveFieldValueConverter {
	@Override
	public Object resolveFieldValue(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum)
			throws Exception {
		if (value != null) {
			String temp = value.toString();
			if (StringUtils.isNotBlank(temp)) {
				List<DictionaryModel> dicts = DictUtil.findSubsByPath(getDictPath(fieldValue));
				if (CollectionUtils.isNotEmpty(dicts)) {
					if (Type.IMPORT.equals(type)) {
						// 导入处理
						for (DictionaryModel dict : dicts) {
							if (temp.equals(dict.getName())) {
								return dict.getValue();
							}
						}
					} else if (Type.EXPORT.equals(type)) {
						// 导出处理
						for (DictionaryModel dict : dicts) {
							if (temp.equals(dict.getValue())) {
								return dict.getName();
							}
						}
					}
				}
			}

		}
		return value;
	}

	/**
	 * 获取字典路径
	 * 
	 * @return
	 */
	public String getDictPath(FieldValue fieldValue){
		if(fieldValue.getOtherConfig()!=null){
			return DictUtil.getPathValue(fieldValue.getOtherConfig());
		}else{
			return getDictPath();
		}
		
	}
	
	public String getDictPath(){
		return null;
	}

}
