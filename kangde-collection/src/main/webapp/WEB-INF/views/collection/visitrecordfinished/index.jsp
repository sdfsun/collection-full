<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<jsp:include page="../region/region.jsp"></jsp:include>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout"   data-options="fit:true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">

	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 95px; width: 100%; margin-top:5px;">
		<form id="_finish_form" style="padding: 0px;">
<!-- 			<table id="table_query"> -->
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; </td>
					<td><input id="finish_entrustId" name="entrustId" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访姓名&nbsp; </td>
					<td><input class="easyui-textbox" name="name" width="150"
						style="width: 150px;" /></td>


					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实际外访时间 &nbsp; </td>
					<td><input type="text" id="actualVisitTime"
						name="actualVisitTimeStart" maxLength="255" class="easyui-datebox"
						data-options="editable:false,required:false,missingMessage:'请输入实际外访时间...',validType:['minLength[1]']" />
						&nbsp;至 &nbsp; <input type="text" id="actualVisitTime1"
						name="actualVisitTimeEnd" maxLength="255" class="easyui-datebox"
						data-options="editable:false,required:false,missingMessage:'请输入实际外访时间...',validType:['minLength[1]']" /></td> -->
							
							
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实际外访时间&nbsp; </td>
 						<td><input
						id="actualVisitTime" name="actualVisitTimeStart"
						style="width: 144px;height:23px;" class="Wdate textbox"
						onFocus="var actualVisitTime1=$dp.$('actualVisitTime1');WdatePicker({onpicked:function(){actualVisitTime1.focus();},maxDate:'#F{$dp.$D(\'actualVisitTime1\')}'})" />
						&nbsp;至 &nbsp;<input id="actualVisitTime1" name="actualVisitTimeEnd"
						style="width: 144px;height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'actualVisitTime\')}'})" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>

					<td><input name="caseCode" style="width: 150px;"
						class="easyui-textbox" /></td>

				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访员&nbsp; </td>
					<td><input class="easyui-textbox" id="visitUserId"
						name="visitUserId" width="150" style="width: 150px;" /> <input
						type='hidden' name="visitUser" id="visitUser" value="" /></td>
					

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp; </td>
					<td><k:dictionary constName="CS_STATE" name="collecStateId"
							selectType="all" width="150" /></td>

					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访地区&nbsp; </td>

					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="areaProvince"
						name="areaId1" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 103px" placeholder="省" /> <input
						type="text" class="easyui-validatebox textbox eu-input"
						id="areaCity" name="areaId2"
						onkeydown="if(event.keyCode==13)search()" maxLength="25"
						style="width: 103px" placeholder="市" /> <input type="text"
						class="easyui-validatebox textbox eu-input" id="areaId3"
						name="areaId3" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 103px" placeholder="区" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访结果&nbsp; </td>
					<td>
					<k:dictionary constName="VISIT_RESULT" name="visitState"
							selectType="all" width="150" />
							</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;</td>
					<td>
					<input type="text" class="easyui-validatebox textbox eu-input" id="caseName"
						name="caseName" maxLength="25" style="width: 150px; height: 23px;" />
						</td>
				<!-- <td colspan="2"></td> -->
				<td colspan="8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="reset()"><span
							style="color: white;">重置查询</span></a></td></tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="finish_datagrid"></table>
	</div>

</div>
