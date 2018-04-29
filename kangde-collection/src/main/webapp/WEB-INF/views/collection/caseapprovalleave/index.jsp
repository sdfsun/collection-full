<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<%-- 引入kindEditor插件 --%>
<link rel="stylesheet"
	href="${ctxStatic}/js/kindeditor-4.1.10/themes/default/default.css">
<script type="text/javascript"
	src="${ctxStatic}/js/kindeditor-4.1.10/kindeditor-all-min.js"
	charset="utf-8"></script>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true, region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 100px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审批状态&nbsp; <select
						class="easyui-combobox" name="approveState" id="approveState"
						style="width: 150px; height: 23px;">
							<option value="0">待审批</option>
							<option value="">全部</option>
							<option value="1">审批通过</option>
							<option value="2">审批不通过</option>

					</select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp; <k:dictionary constName="CS_STATE" name="collecStateId"
							required="true" selectType="all" width="150" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select class="easyui-combobox" id="selectByNum"
						name="selectByNum" style="width: 200px; height: 23px;">
							<option value="1">案件序列号</option>
							<option value="2">档案号</option>
							<option value="3">证件号</option>
							<option value="4">卡号</option>
					</select>
					</td>
				

				</tr>

				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控员&nbsp;
						<input id="caseAssignedId" name="caseAssignedId" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp; 
					<input id="batchCode"name="batchCode"/></td>
					<td rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="content" name="content" style="width: 200px; height: 50px;" class="easyui-textbox" 
					data-options="multiline:true"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;
						<input id="search_entrustId" name="entrustId" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; <input id="orgId" name="orgId" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="#"
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

<%-- <tr></tr>
			<tr>
				
					</tr> --%>

<!-- 	
					<tr>
				
					
			</tr> -->
			
		<!-- 	 -->
