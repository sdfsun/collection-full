<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/app/menu/amazonmenu.css" />
<script type="text/javascript" src="${ctxStatic}/app/menu/amazonmenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctxStatic}/app/layout/center.js" charset="utf-8"></script>
<style>


.easyui-linkbutton {
    color: #444;
    background: none;
    background-repeat: repeat-x;
    border: none;
}

.easyui-linkbutton:hover{
	color: #444;
    background: none;
    background-repeat: repeat-x;
    border: none;
}
</style>

<div style="height: 100%">
	<!-- <div style="height: 100%;float: left;width: 5%;">
	
		<nav id="mysidebarmenu" class="amazonmenu" style="height:100%;background: #4b91c2;">
			<ul id="menu_nvn" style="width: 100%;height: 100%;">
			</ul>
		</nav>
	</div> -->
	<div style="height: 100%;">
		<div id="layout_center_tabs" fit="true" style="overflow: hidden;">
		</div>
		<div id="layout_center_tabsMenu" style="width: 120px;display:none;">
			<div type="refresh" data-options="iconCls:'easyui-icon-reload'">刷新</div>
			<div class="menu-sep"></div>
			<div type="close" data-options="iconCls:'easyui-icon-cancel'">关闭</div>
			<div type="closeOther">关闭其他</div>
			<div type="closeAll">关闭所有</div>
		</div>
		<%--全屏工具栏--%>
		<div id="layout_center_tabs_full-tools" style="display: none;border-left:0px;border-top:0px;border-right:0px;">
		    <a href="#" class="easyui-linkbutton easyui-tooltip" title="全屏" data-options="iconCls:'eu-icon-full_screen',plain:true"
		       onclick="javascript:screenToggle(true);"></a>
		    <a href="#" class="easyui-linkbutton easyui-tooltip" title="刷新" data-options="iconCls:'easyui-icon-reload',plain:true"
		       onclick="javascript:refresh();"></a>
		   
		</div>
		<%--退出全屏工具栏--%>
		<div id="layout_center_tabs_unfull-tools" style="display: none;">
		    <a href="#" class="easyui-linkbutton easyui-tooltip" title="退出全屏" data-options="iconCls:'eu-icon-full_screen',plain:true"
		       onclick="javascript:screenToggle(false);"></a>
		    <a href="#" class="easyui-linkbutton easyui-tooltip" title="刷新" data-options="iconCls:'easyui-icon-reload',plain:true"
		       onclick="javascript:refresh();"></a>
		   
		</div>
	</div>
</div>
