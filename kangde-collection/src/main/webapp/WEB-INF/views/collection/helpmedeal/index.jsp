<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<style>
.icon {
	background: url(${ctxStatic}/img/icon/tianjia.png) no-repeat;
	width: 18px;
	line-height: 18px;
	display: inline-block;
	cursor: pointer;
	margin-left: 10px;
}

.icon-tel {
	background-position: 0px 0px;
}
</style>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 73px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
<!-- 			<table id="table_query"> -->
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; </td>
					<td><input id="orgId" name="orgId" width="150" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseCode"
						name="caseCode" maxLength="25" style="width: 150px; height: 22px" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查资类型&nbsp; </td>
					<td><k:dictionary constName="HELPME" name="applyType"
							required="true" selectType="all" width="150" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请日期&nbsp; </td>
					<td><input
						id="appTime" name="appTime"
						style="width: 150px;height:23px;" class="Wdate textbox"
						onFocus="var appTime1=$dp.$('appTime1');WdatePicker({onpicked:function(){appTime1.focus();},maxDate:'#F{$dp.$D(\'appTime1\')}'})" />
						&nbsp;至&nbsp; <input id="appTime1" name="appTime1"
						style="width: 150px;height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'appTime\')}'})" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="applyUserName"
						name="applyUserName" maxLength="25" style="width: 150px; height: 22px" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseName"
						name="caseName" maxLength="25" style="width: 150px; height: 22px" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseNum"
						name="caseNum" maxLength="25" style="width: 150px; height: 22px" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;</td><td> <input id="search_entrustId"
						name="entrustId" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a></td>

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

