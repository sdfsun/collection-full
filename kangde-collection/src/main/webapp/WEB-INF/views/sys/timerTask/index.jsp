<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<div id="_datagrid_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="showDialog();" data-options="iconCls:'easyui-icon-add'">新增</div>
	<div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑</div>
	<div onclick="del();" data-options="iconCls:'easyui-icon-remove'">删除</div>
	<div onclick="runJob();" data-options="iconCls:'easyui-icon-edit'">立即调度一次</div>
</div>

<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
         style="padding: 0px; margin-top:5px; height: 40px;width:100%; overflow-y: hidden;">
        <form id="_search_form" style="padding: 5px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 作业名称&nbsp; <input type="text" class="easyui-validatebox textbox eu-input" name="name" 
                              onkeydown="if(event.keyCode==13)search()"  maxLength="25" style="width: 160px" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" 
				href="#" data-options="width:73,height:23,onClick:search"><span style="color:white;">查&nbsp;&nbsp;询</span></a>
					<a class="easyui-linkbutton" href="#" data-options="width:73,height:23"
					onclick="javascript:_search_form.form('reset');"><span style="color:white;">重置查询</span></a>
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid"></table>
    </div>
</div>


   