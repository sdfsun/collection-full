<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<!-- <div id="_datagrid_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="showDialog();" data-options="iconCls:'eu-icon-xinzeng'">新增</div>
	<div onclick="edit();" data-options="iconCls:'eu-icon-bianji'">编辑</div>
	<div onclick="del();" data-options="iconCls:'eu-icon-shanchu'">删除</div>
</div> -->

<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid"></table>
    </div>
</div>


   