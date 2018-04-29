<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">

    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_treegrid" ></table>
		<%--Grid按钮--%>
	    <div id="_toolbar" style="display: none;">
			<shiro:hasPermission name="organization:save">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-xinzeng'" onclick="showDialog()">新增</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="organization:update">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-bianji'" onclick="edit()">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="organization:delete">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-shanchu'" onclick="del()">删除</a>
			</shiro:hasPermission>
		</div>
    </div>
</div>