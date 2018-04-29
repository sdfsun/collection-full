<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<div id="tt" class="easyui-tabs" style="width: 100%; height: 575px" id ="visitRecordShowTabls">
			<div title="外访审批">
				&nbsp;&nbsp;&nbsp;<label>过滤条件</label>
				<form id="_search_form" style="padding: 5px;">
				<table>
			<tr>
				<td>&nbsp;审批状态: &nbsp;<k:dictionary constName="CASE_APPROVAL" name="vrState" required="true" selectType="all"/> </td>
				
				<td>&nbsp;风控方 :&nbsp; <input id="orgId" name="orgId" width="160" /></td>
				
				<td>&nbsp;批次号 : &nbsp;<input type="text"
				class="easyui-validatebox textbox eu-input" id="batchCode"
				name="batchCode" onkeydown="if(event.keyCode==13)search()"
				maxLength="25" style="width: 160px" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;案件地区: &nbsp;
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaProvince" 
				name="areaId1" onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="省" /> 
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaId2" name="areaId2"
				onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="市" />
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaId3" name="areaId3"
				onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="县" /></td>
			</tr>
			<tr>   
				<td>&nbsp;风控状态: &nbsp;<k:dictionary constName="CS_STATE" name="collecStateId" required="true" selectType="all"/> </td>
				
				<td>&nbsp;委托方: &nbsp;<input id="search_entrustId" name="entrustId" /></td>
				<td>&nbsp;风控员 :&nbsp; <input id="caseAssignedId" name="caseAssignedId" width="150" style="width:150px;"/></td>
				<td>&nbsp;案件序列号: &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" id="caseCode"
				name="caseCode" onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 160px" />
				<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-search',width:75,height:28,onClick:search">查询</a>
			<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-no',width:80,height:28"
				onclick="javascript:_search_form.form('reset');">重置查询</a>
				
				</td>
			</tr>
		</table>
				</form>
				<div data-options="region:'center',split:false,border:false"
					style="padding: 0px; height: 69%; width: 100%; overflow-y: hidden;">
					<table id="_datagrid"></table>
				</div>
			</div>
			<div title="留案审批">
				&nbsp;&nbsp;&nbsp;<label>过滤条件</label>
				<form id="_search_form1" style="padding: 5px;">
				<table>
			<tr>
				<td>&nbsp;审批状态: &nbsp;<k:dictionary constName="CASE_APPROVAL" name="approveState" required="true" selectType="all"/> </td>
				
				<td>&nbsp;风控方 :&nbsp; <input id="orgId1" name="orgId" width="160" /></td>
				
				<td>&nbsp;批次号 : &nbsp;<input type="text"
				class="easyui-validatebox textbox eu-input" id="batchCode"
				name="batchCode" onkeydown="if(event.keyCode==13)search()"
				maxLength="25" style="width: 150px" /></td>
			
			</tr>
			<tr>   
				<td>&nbsp;风控状态: &nbsp;<k:dictionary constName="CS_STATE" name="collecStateId" required="true" selectType="all"/> </td>
				
				<td>&nbsp;委托方: &nbsp;<input id="search_entrustId1" name="entrustId" /></td>
				
			<td>&nbsp;风控员 :&nbsp; <input id="caseAssignedId1" name="caseAssignedId" width="150" style="width:150px;"/></td>
				<td>&nbsp;案件序列号: &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" id="caseCode"
				name="caseCode" onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 160px" />
				<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-search',width:75,height:28,onClick:search1">查询</a>
			<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-no',width:80,height:28"
				onclick="javascript:_search_form1.form('reset');">重置查询</a>
				
				</td>
			</tr>
		</table>
				</form>
				<div data-options="region:'center',split:false,border:false"
					style="padding: 0px; height: 69%; width: 100%; overflow-y: hidden;">
					<table id="_datagridleave"></table>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 查询条件 -->
<%-- <div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 100px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
		<table>
			<tr>
				<td>&nbsp;审批状态: &nbsp;<k:dictionary constName="CASE_APPROVAL" name="approveState" required="true" selectType="all"/> </td>
				
				<td>&nbsp;风控方 :&nbsp; <input id="orgId" name="orgId" width="160" /></td>
				
				<td>&nbsp;批次号 : &nbsp;<input type="text"
				class="easyui-validatebox textbox eu-input" id="batchCode"
				name="batchCode" onkeydown="if(event.keyCode==13)search()"
				maxLength="25" style="width: 160px" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;案件地区: &nbsp;
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaProvince" 
				name="areaId1" onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="省" /> 
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaId2" name="areaId2"
				onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="市" />
				<input type="text" class="easyui-validatebox textbox eu-input" id="areaId3" name="areaId3"
				onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 100px" placeholder="县" /></td>
			</tr>
			<tr>   
				<td>&nbsp;风控状态: &nbsp;<k:dictionary constName="CS_STATE" name="approveState" required="true" selectType="all"/> </td>
				
				<td>&nbsp;委托方: &nbsp;<input id="search_entrustId" name="entrustId" /></td>
				
			<td>&nbsp;风控员 :&nbsp; <input id="caseAssignedId" name="caseAssignedId" width="150" style="width:150px;"/></td>
				<td>&nbsp;案件序列号: &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" id="caseCode"
				name="caseCode" onkeydown="if(event.keyCode==13)search()" maxLength="25" style="width: 160px" />
				<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-search',width:75,height:28,onClick:search">查询</a>
			<a class="easyui-linkbutton" href="#"
				data-options="iconCls:'easyui-icon-no',width:80,height:28"
				onclick="javascript:_search_form.form('reset');">重置查询</a>
				
				</td>
			</tr>
		</table>
			 
				 
		</form>
	</div>

	中间部分 列表
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<div class="easyui-tabs" style="width: 100%; height: 420px">
			<div title="外访审批">
				<table id="_datagrid"></table>
			</div>
			<div title="留案审批">
				<table id="_datagridleave"></table>
			</div>
		</div>


	</div>
</div>



 --%>
 <jsp:include page="../region/region.jsp"></jsp:include>