<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="taskscript.jsp"></jsp:include>
<style>
#table_query {
	border-collapse: collapse;
	cellspacing: 0;
	cellpadding: 0;
	border: 0;
	border-spacing: 0px;
	cellspacing: 0;
	width: 100%;
}

table tr td label {
	display: block;
	height: 100%;
	padding: 0px;
	margin: 5px;
	text-align: right;
}

.easyui-linkbutton {
	color: white;
}
</style>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 68px; width: 100%; margin-top: 5px; overflow-y: hidden;">
		<form id="_search_form">
<!-- 			<table id='table_query'> -->
			<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>
					<td><input name="caseCode" style="width: 150px; height: 23px;"
						class="easyui-textbox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; </td>
					<td><input id="orgId" name="orgId"
						style='width: 150px; height: 23' /></td>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请日期&nbsp; </td>
					<td><input name="appTimeStart" class="easyui-datebox"
						style="width: 150px; height: 23px;" data-options="editable:false" />
						&nbsp;至 &nbsp; <input name="appTimeEnd" class="easyui-datebox"
						style="width: 150px; height: 23px;" data-options="editable:false" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请日期&nbsp; </td><td> <input
						id="appTimeStart" name="appTimeStart"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var appTimeEnd=$dp.$('appTimeEnd');WdatePicker({onpicked:function(){appTimeEnd.focus();},maxDate:'#F{$dp.$D(\'appTimeEnd\')}'})" />
						&nbsp;至&nbsp; <input id="appTimeEnd" name="appTimeEnd"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'appTimeStart\')}'})" />
					</td>
				
				</tr>

				<tr>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人&nbsp; </td>
					<td><input name="applyUserName"
						style="width: 150px; height: 23px;" class="easyui-textbox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; </td>
					<td><input id="search_entrustId" name="entrustId"/></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;协催类型&nbsp; </td>
					<td><select name="applyType" class="easyui-combobox"
						style="width: 150px; height: 23px;">
							<option value="">全部...</option>
							<option value="11">客服咨询</option>
							<option value='7'>公安协助</option>
							<option value='5'>法院协助</option>
							<option value='10'>主管协催</option>
					</select>
					<span style='margin-left: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							class="easyui-linkbutton" href="#"
							data-options="width:73,height:23,onClick:search"><span
								style="color: white;">查&nbsp;&nbsp;询</span></a> <a
							class="easyui-linkbutton" href="#"
							data-options="width:73,height:23"
							onclick="javascript:_search_form.form('reset');"><span
								style="color: white;">重置查询</span></a>
					</span></td>

				</tr>


			</table>

		</form>



	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; width: 100%;">

		<div style="padding: 0px; height: 100%; width: 100%;">
			<table id="_datagrid"></table>
		</div>
	</div>

</div>
