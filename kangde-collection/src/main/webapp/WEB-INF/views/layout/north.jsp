<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctxStatic}/app/layout/north.js"></script>
<%-- jQuery Cookie插件 --%>
<script type="text/javascript" src="${ctxStatic}/js/jquery/jquery.cookie-min.js" charset="utf-8"></script>
<div style="height: 100%;background:#0080BB;">
    <div style="float: left;">
	    <img src="${ctxStatic}/img/loginPicture/atguigu.png">
	</div>
	<div style="float: right; position: absolute; bottom: 10px; right: 10px;" id="topManager">
		<a id="user_link_name" href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_logoutMenu" iconCls="eu-icon-topuser"><shiro:principal property="userName"/></a>
		<div id="layout_north_logoutMenu" style="width: 100px; display: none;background: #498ec1;">
			<!-- <shiro:hasPermission name="employeeInfo:updateUserPassword"> -->
			<div onclick="editLoginUserPassword();" iconCls="eu-icon-mdfpwd">修改密码</div>
            <!-- </shiro:hasPermission> -->
			
			<div onclick="logout(true);"  iconCls="eu-icon-secout">安全退出</div>
		</div>
	</div>
</div>
