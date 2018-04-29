package com.kangde.commons.util;

import com.thoughtworks.xstream.XStream;

/**
 * XML与Object互转工具类
 *
 */
public abstract class XmlUtil {
	
    /**
	 * 把对象转换为xml字符串
	 * @param object 需要转换为xml的对象
	 * @return 返回对象的xml形式
	 */
	public static String object2Xml(Object object)
	{
		if(object!=null)
		{
			XStream sm = new XStream();
			return sm.toXML(object);
		}
		else
		{
			return null;
		}
	}

	/**
	 * 把xml串转换为对象
	 * @param xml 需要转换为对象的xml字符串
	 * @return 返回xml对应的对象
	 */
	public static Object xml2Object(String xml)
	{
		if(xml==null || xml.equalsIgnoreCase("null"))
		{
			return null;
		}
		else
		{
			XStream sm = new XStream();
			return sm.fromXML(xml);
		}
	}
}
