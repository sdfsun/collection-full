package com.kangde.commons.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.exception.web.SessionNullPointerException;
import com.kangde.commons.util.WEBUtil;
import com.kangde.commons.util.excel.ExcelHeader;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.web.view.ExcelView;

/**
 * Controller基类,如果不需要泛型注入,可以考虑继承该类
 * @author lisuo
 *
 */
public abstract class SimpleController {
	
	/** 日志 */
	protected Logger log = Logger.getLogger(this.getClass());
	//----------------------------分页相关----------------------//
	/** 分页请求入参名称,第几页 */
	private static final String PAGE_NO = "page";
	/** 分页请求入参每页条数名称 */
	private static final String PAGE_LIMIT = "rows";
	//----------------------------分页相关End----------------------//

	
	//----------------------------排序相关----------------------//
	/** 请求入参排序字段名称 */
	private static final String SORT_NAME = "sort";
	/** 请求入参排序方式名称 */
	private static final String ORDER_NAME = "order";
	/** 升序排列,用于安全校验排序规则 */
	private static final String SORT_TYPE_ASC = "asc"; 
	/** 降序排列 ,用于安全校验排序规则*/
	private static final String SORT_TYPE_DESC = "desc"; 
	//----------------------------排序相关End-------------------//
	
	//----------------------------页面相关----------------------//
	/**默认首页页面名称*/
	protected static final String PAGE_INDEX = "index";
	/**默认添加页面名称*/
	protected static final String PAGE_EDIT_NEW = "editNew";
	/**默认修改页面名称*/
	protected static final String PAGE_EDIT = "edit";
	/**默认展示页面名称*/
	protected static final String PAGE_SHOW = "show";
	/**默认输入页面名称（增加，修改，展示都用这个页面）*/
	protected static final String PAGE_INPUT = "input";
	//----------------------------页面相关End-------------------//
	
	//----------------------------查询映射路径----------------------//
	/**查询默认路径*/
	protected static final String QUERY_LIST = "list";
	/**分页查询默认路径*/
	protected static final String QUERY_PAGE = "query";
	//----------------------------查询映射路径End-------------------//
	
	/**基础路径*/
	private String baseName;
	
	
	/**
	 * 构造方法
	 */
	public SimpleController() {
		//初始化路径名称
		this.baseName = getSimpleBaseName();
	}
	
	/**
	 * 获取基础请求名称,类的简单类名去掉Controller后缀,首字母转小写加上/,
	 * 假设当前控制器名称为StudentController,返回student/
	 * @return
	 */
	protected String getSimpleBaseName(){
		String name = getClass().getSimpleName();
		String firstWord = name.substring(0,1);
		name = name.replace("Controller", "/").replaceFirst(firstWord, firstWord.toLowerCase());
		return name;
	}
	
	/**
	 * 获取请求跳转页基础路径,可以被重写
	 */
	protected String getBaseName(){
		return baseName;
	}
	
	/**
	 * 获取基础请求名称/子路径名称
	 * @param subPath 子路径名称
	 */
	protected String getBaseName(String subPath){
		return new StringBuilder(getBaseName()).append(subPath).toString();
	}
	
	/**
	 * 创建一个页面视图,生成规则：getBaseName()/viewName
	 * @param viewName
	 * @return ModelAndView
	 */
	protected ModelAndView createBaseView(String viewName){
		ModelAndView view = new ModelAndView(getBaseName(viewName));
		return view;
	}
	
	/**
	 * 解析检索条件,分页排序及信息
	 * 如果指定了需要获取的参数,默认去除参数的前后空格
	 * @param names 请求参数名称,如果值不为空,直接已String类型放入condition中,不支持数组,如果所有参数都需要可以写*
	 * @return SearchCondition
	 */
	protected ParamCondition parseCondition(String ... names){
		//默认去除参数前后空格
		return parseCondition(true, names);
	}

	/**
	 * 解析检索条件,分页排序及信息
	 * @param trim 是否去除参数两侧空格
	 * @param names 请求参数名称,如果值不为空,直接已String类型放入condition中,不支持数组,如果所有参数都需要可以写*
	 * @return SearchCondition
	 */
	protected ParamCondition parseCondition(boolean trim,String ... names) {
		HttpServletRequest request = WEBUtil.getRequest();
		ParamCondition condition = new ParamCondition();
		// 获取分页信息
		String pageTxt = request.getParameter(PAGE_NO);
		String sizeTxt = request.getParameter(PAGE_LIMIT);
		//分页信息不为空
		if (StringUtils.isNotBlank(pageTxt) && StringUtils.isNotBlank(sizeTxt)) {
			try {
				int page = Integer.parseInt(pageTxt);
				int size = Integer.parseInt(sizeTxt);
				if (page < 1) {
					page = 1;
				}
				condition.setOffset(page);
				condition.setLimit(size);
			} catch (NumberFormatException ignore) {}
		}
		// 获取数据排序信息
		String sortTxt = request.getParameter(SORT_NAME);
		String orderTxt = request.getParameter(ORDER_NAME);
		if (StringUtils.isNotBlank(sortTxt) && StringUtils.isNotBlank(orderTxt)) {
			//预防SQL注入
			String columnName = getColumnName(sortTxt);
			if(StringUtils.isNotBlank(columnName)){
				condition.setSort(columnName);
				if(SORT_TYPE_ASC.equalsIgnoreCase(orderTxt) || SORT_TYPE_DESC.equalsIgnoreCase(orderTxt)){
					//排序规则,升序or降序
					condition.setOrder(orderTxt);
				}else{
					//默认排序规则
					condition.setOrder(SORT_TYPE_ASC);
				}
			}
		}
		// 参数放入,如果是*的通配符,那么获取所有request中的key,放入condition
		if(ArrayUtils.isNotEmpty(names)){
			if(names.length ==1 && "*".equals(names[0])){
				Map<String, String[]> map = request.getParameterMap();  
		        Set<Entry<String, String[]>> set = map.entrySet();  
		        Iterator<Entry<String, String[]>> it = set.iterator();  
		        while (it.hasNext()) {  
		            Entry<String, String[]> entry = it.next();
		            //空格处理
		            String val = trim?
		            		StringUtils.trim(request.getParameter(entry.getKey())):
		            		request.getParameter(entry.getKey());
		            condition.put(entry.getKey(), val);
		        }
			}else{
				for (String name:names) {
					//空格处理
		            String val = trim?
		            		StringUtils.trim(request.getParameter(name)):
		            		request.getParameter(name);
					condition.put(name, val);
				}
			}
		}
		return condition;
	}
	
	/**
	 * 获取数据表对应的列名称,如果不需要排序不需要重写该方法（如果需要排序必须重写该方法）
	 * @param sortTxt 请求传递的排序字段名称
	 * @return 字段名称在数据库对于的列名称
	 */
	public String getColumnName(String sortTxt) {
		return null;
	}

	/**
	 * 创建Excel视图
	 * @param id xml配置中的ID
	 * @param beans 对应class的类型集合
	 * @param excelName excel文件名称,不需要任何后缀
	 * @param header 可以为空,自定义Excel头信息
	 * @param fields 指定导出的字段
	 * @return
	 */
	protected ModelAndView createExcelView(String id,List<?> beans,String excelName,ExcelHeader header,List<String> fields){
		ModelAndView view = new ModelAndView("excelView");
		view.addObject(ExcelView.EXCEL_ID, id)
			.addObject(ExcelView.EXCEL_BEANS, beans)
			.addObject(ExcelView.EXCEL_NAME, excelName)
			.addObject(ExcelView.EXCEL_HEADER, header)
			.addObject(ExcelView.EXCEL_FIELDS, fields);
		return view;
	}
	
	//---------------------------操作消息提示---------------------------//
	/**操作成功提示*/
	protected static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
	/**操作错误|异常提示*/
	protected static final String DEFAULT_ERROR_MESSAGE = "发生未知错误";
	
	/**
	 * 获取成功提示信息
	 * @return
	 */
	protected String getSuccessMessage(){
		return DEFAULT_SUCCESS_MESSAGE;
	}
	
	/**
	 * 获取异常提示信息
	 * @return
	 */
	protected String getErrorMessage(){
		return DEFAULT_ERROR_MESSAGE;
	}
	
	/**
	 * 获取请求
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return WEBUtil.getRequest();
	}
	
	/**
	 * 获取响应
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return WEBUtil.getResponse();
	}
	
	/**
	 * 获取Session
	 * @return
	 * @throws SessionNullPointerException
	 */
	protected HttpSession getSession()throws SessionNullPointerException{
		return WEBUtil.getSession();
	}
	
}
