<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="../region/region.jsp"></jsp:include>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<div class="easyui-layout" data-options="fit:true"
	style="margin: 0px; padding: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true, region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 69px; width: 100%;margin-top:5px;">
		<form id="_search_form">
			<!-- <table id="table_query"> -->
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; </td>
					<td><input id="search_entrustId" name="entrustId" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp; </td>
					<td><k:dictionary constName="COLLECTION_STATE"
							name="collecStateId" required="true" selectType="all" width="150" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访地区&nbsp; </td>
					<td colspan="3"><input type="text"
						class="easyui-validatebox textbox eu-input" id="areaProvince"
						name="areaId1" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 100px" placeholder="省" /> <input
						type="text" class="easyui-validatebox textbox eu-input"
						id="areaId2" name="areaId2"
						onkeydown="if(event.keyCode==13)search()" maxLength="25"
						style="width: 100px" placeholder="市" /> <input type="text"
						class="easyui-validatebox textbox eu-input" id="areaId3"
						name="areaId3" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 100px" placeholder="县" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp; </td>
					<td><input id="batchCode"name="batchCode"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseCode"
						name="caseCode" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 150px; height: 23px;" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;
					</td>
					<td>
					<input type="text" class="easyui-validatebox textbox eu-input" id="caseName"
						name="caseName" maxLength="25" style="width: 150px; height: 23px;" /> <a class="easyui-linkbutton" href="#"
						data-options="width:75,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:80,height:23"
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