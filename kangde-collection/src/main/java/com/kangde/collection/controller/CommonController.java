package com.kangde.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Common 页面转发控制器
 * @author lisuo
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController{
	
	/**
     * 用户跳转JSP页面
     * <p/>
     * 此方法不考虑权限控制
     *
     * @param folder 路径
     * @param pageName 页面名称(不加后缀)
     * @return 指定JSP页面
     */
    @RequestMapping("/{folder}/{pageName}")
    public String redirectJsp(@PathVariable String folder, @PathVariable String pageName) {
        return "/" + folder + "/" + pageName;
    }

}
