<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">

    <%-- 列表右键 --%>
<!--     <div id="_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="showDialog();" data-options="iconCls:'easyui-icon-add'">新增</div>
        <div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑</div>
        <div onclick="del();" data-options="iconCls:'easyui-icon-remove'">删除</div>
    </div> -->
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_treegrid" ></table>
		<%--Grid按钮--%>
	    <div id="_toolbar" style="display: none;">
			<shiro:hasPermission name="position:save">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-xinzeng'" onclick="showDialog()">新增</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="position:update">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-bianji'" onclick="edit()">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="position:delete">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-shanchu'" onclick="del()">删除</a>
			</shiro:hasPermission>
		</div>
    </div>
</div>