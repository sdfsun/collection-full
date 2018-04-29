<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link href="${ctxStatic}/js/kendoui/styles/kendo.common.min.css"
	rel="stylesheet">
<link href="${ctxStatic}/js/kendoui/styles/kendo.default.min.css"
	rel="stylesheet">
<script src="${ctxStatic}/js/kendoui/js/kendo.all.min.js"></script>
<script
	src="${ctxStatic}/js/easyui-${ev}/datagridview/datagrid-bufferview-min.js"></script>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 100px; width: 100%; overflow-y: hidden;">
		
		<form id="_search_form" style="padding: 5px;">
		
		<table>
			<tr>
				<td>&nbsp;风控方 :&nbsp; <input id="orgId" name="orgId" width="145" /></td>
				<td>&nbsp;批次状态 : &nbsp;<k:dictionary constName="CASE_STATUS" name="state" required="true" selectType="all" width="150"/></td>
				<td> &nbsp;委案日期 : &nbsp;<input type="text" id="beginDate"
				name="beginDate" maxLength="255" class="easyui-datebox"
				placeholder="请输入委托开始日期..." width="100"
				data-options="editable:false,required:false,missingMessage:'请输入委托开始日期...',validType:['minLength[1]']" />
				&nbsp;至 &nbsp;
				<input type="text" id="beginDate1"
				name="beginDate1" maxLength="255" class="easyui-datebox"
				placeholder="请输入委托开始日期..." width="100"
				data-options="editable:false,required:false,missingMessage:'请输入委托开始日期...',validType:['minLength[1]']" /></td>
				
			</tr>
			
			<tr>
				<td> &nbsp;委托方: &nbsp;
			 	<input id="search_entrustId" name="entrustId"/></td>
				<td>&nbsp;案件类型 : &nbsp;<k:dictionary constName="CASE_TYPE" name="typeId" required="true"  selectType="all" width="150"/></td>
				<td>&nbsp;批次号 : &nbsp; <input type="text" class="easyui-validatebox textbox eu-input" id="batchCode" name="batchCode" placeholder="请输入批次号..."
                              onkeydown="if(event.keyCode==13)search()"  maxLength="25" style="width: 165px" />
                 &nbsp;<a class="easyui-linkbutton" href="#" id="linkbutton"
				data-options="iconCls:'easyui-icon-search',width:90,height:28,onClick:search">查询</a>
				<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-no',width:90,height:28"
				onclick="javascript:_search_form.form('reset');">重置查询</a>              
                </td>
			</tr>
		</table>
			
		</form>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid"></table>
	</div>
</div>



