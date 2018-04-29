<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<jsp:include page="../region/region.jsp"></jsp:include>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" data-options="fit:true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 98px; width: 100%; margin-top:5px;">
		<form id="_search_form">
<!-- 			<table id="table_query"> -->
			<table>
				<tr>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访姓名&nbsp; </td>
					<td><input class="easyui-textbox" name="name" width="150"
						style="width: 150px;" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; </td>
					<td><input id="already_entrustId" name="entrustId" /></td>

				<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预计外访时间&nbsp; </td>
					<td><input type="text" id="visitTime" name="visitTimeStart"
						maxLength="255" class="easyui-datebox"
						data-options="editable:false,required:false,missingMessage:'请输入预计外访时间...',validType:['minLength[1]']" />
						&nbsp;至 &nbsp; <input type="text" id="visitTime1"
						name="visitTimeEnd" maxLength="255" class="easyui-datebox"
						data-options="editable:false,required:false,missingMessage:'请输入预计外访时间...',validType:['minLength[1]']" /></td> -->


				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预计外访时间&nbsp; </td>
 						<td><input
						id="visitTime" name="visitTimeStart"
						style="width: 139px; height:23px;" class="Wdate textbox"
						onFocus="var visitTime1=$dp.$('visitTime1');WdatePicker({onpicked:function(){visitTime1.focus();},maxDate:'#F{$dp.$D(\'visitTime1\')}'})" />
						&nbsp;至 &nbsp;<input id="visitTime1" name="visitTimeEnd"
						style="width: 140px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'visitTime\')}'})" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访员 &nbsp; </td>
					<td><input class="easyui-textbox" name="visitUserId"
						id="visitUserId" width="150" style="width: 150px;" /> <input
						type='hidden' name="visitUser" id="visitUser" value="" /></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp; </td>
					<td><k:dictionary constName="CS_STATE" name="collecStateId"
							selectType="all" width="150" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>
					<td><input name="caseCode" style="width: 150px;"
						class="easyui-textbox" /></td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访地区&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="areaProvince"
						name="areaId1" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 100px" placeholder="省" /> <input
						type="text" class="easyui-validatebox textbox eu-input"
						id="areaCity" name="areaId2"
						onkeydown="if(event.keyCode==13)search()" maxLength="25"
						style="width: 100px" placeholder="市" /> <input type="text"
						class="easyui-validatebox textbox eu-input" id="areaId3"
						name="areaId3" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 100px" placeholder="区" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;</td>
					<td>
						<input type="text" class="easyui-validatebox textbox eu-input" id="caseName"
						name="caseName" maxLength="25" style="width: 150px; height: 23px;" /></td>
				</tr>
				<tr>
				<td></td>
				<td><a class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="javascript:reset()"><span
							style="color: white;">重置查询</span></a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="already_datagrid"></table>
	</div>
</div>