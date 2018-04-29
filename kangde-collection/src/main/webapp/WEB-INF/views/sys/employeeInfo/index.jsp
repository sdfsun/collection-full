<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<!-- <div id="_datagrid_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="showDialog();" data-options="iconCls:'eu-icon-xinzeng'">新增</div>
	<div onclick="edit();" data-options="iconCls:'eu-icon-bianji'">编辑</div>
	<div onclick="del();" data-options="iconCls:'eu-icon-shanchu'">删除</div>
	<div onclick="editRoles();" data-options="iconCls:'easyui-icon-edit'">设置权限组</div>
	<div onclick="editResources();" data-options="iconCls:'easyui-icon-edit'">设置权限</div>
</div> -->
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 35px; width: 100%; margin-top:3px;">
        <form id="_search_form" style="padding: 5px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员工状态  &nbsp;<k:dictionary id="status" constName="EMP_STATUS" width="150" 
							name="status" value="" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机构  &nbsp;<input type="text" id="orgId1" name="orgId"/>
           
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号  &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" name="loginName"
                              onkeydown="if(event.keyCode==13)search()"  maxLength="25" style="width: 150px;height:23px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名  &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" name="userName"
                              onkeydown="if(event.keyCode==13)search()"  maxLength="25" style="width: 150px;height:23px;" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="#"
						data-options="width:75,height:23,onClick:search"><span style="color:white;">查&nbsp;&nbsp;询</span></a>
						<a class="easyui-linkbutton" href="#"
						data-options="width:80,height:23"
						onclick="javascript:_search_form.form('reset');"><span style="color:white;">重置查询</span></a>
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid"></table>
    </div>
</div>



   