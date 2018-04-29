<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="pending_script.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true, region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 5px 0 0 0; height: 70px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" class="search_form">
			<input type="hidden" name="applyState" value="0"/>
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; <input name="caseCode" style="width:150px;" class="easyui-validatebox textbox" width="150"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;风控方&nbsp; <input id="orgId" name="orgId"/>
					</td>
				<!-- 	<td>
						<label>申请时间</label>
						<input
						id="appTimeStart" name="appTimeStart"
						class="Wdate textbox"
						onFocus="var appTimeEnd=$dp.$('appTimeEnd');WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){appTimeEnd.focus();},maxDate:'#F{$dp.$D(\'appTimeEnd\')}'})" />
						&nbsp; 至 &nbsp; <input id="appTimeEnd" name="appTimeEnd"
						 class="Wdate textbox"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'appTimeStart\')}'})" />
					</td> -->
					<td>&nbsp;&nbsp;&nbsp;申请人&nbsp; <input name="applyUserName" style="width:150px;" class="easyui-validatebox textbox"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;申请时间&nbsp; <input
						id="appTimeStart" name="appTimeStart"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var appTimeEnd=$dp.$('appTimeEnd');WdatePicker({onpicked:function(){appTimeEnd.focus();},maxDate:'#F{$dp.$D(\'appTimeEnd\')}'})" />
						&nbsp; 至 &nbsp; <input id="appTimeEnd" name="appTimeEnd"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'appTimeStart\')}'})" />
					</td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp; <input name="caseName" style="width:150px;" class="easyui-validatebox textbox"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;证件号&nbsp; <input name="caseNum" style="width:150px;" class="easyui-validatebox textbox"/>
					</td>
					
					<%-- <td>
						<label>信函状态</label>
						<k:dictionary constName="CASE_ASSIGN_STATE" name="caseAssignState" selectType="all" />
					</td> --%>
					<td>&nbsp;&nbsp;&nbsp;委托方&nbsp; <input id="search_entrustId"
						name="entrustId" />
						</td><td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
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


