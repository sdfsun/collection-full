<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<!-- <div id="_datagrid_menu" class="easyui-menu"
	style="width: 120px; display: none;">
	<div onclick="showDialog();" data-options="iconCls:'easyui-icon-add'">新增</div>
	<div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑</div>
	<div onclick="detail();" data-options="iconCls:'easyui-icon-edit'">委托方详情</div>
</div> -->
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 68px; width: 100%; margin-top:5px;">
		<form id="_search_form">
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方</td>
					<td><input id="search_entrustId" name="entrustId"/></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案源地</td>
					<td><input type="text" class="easyui-validatebox textbox eu-input" id="search_caseSourceId" name="caseSourceId" onkeydown="if(event.keyCode==13)search()"
					maxLength="25" style="width: 150px;height:21px;" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业员</td>
					<td><input type="text" class="easyui-validatebox textbox eu-input" id="search_staff" name="staff" onkeydown="if(event.keyCode==13)search()"
					maxLength="25" style="width: 150px;height:21px;" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签约日期</td>
					<td><input id="contractAwardDate" name="contractAwardDate"
						style="width: 150px;" class="Wdate textbox"
						onFocus="var contractAwardDate1=$dp.$('contractAwardDate1');WdatePicker({onpicked:function(){contractAwardDate1.focus();},maxDate:'#F{$dp.$D(\'contractAwardDate1\')}'})" />
						 &nbsp;至&nbsp;
					 	<input id="contractAwardDate1" name="contractAwardDate1"
						style="width: 150px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'contractAwardDate\')}'})" /></td>
				</tr>
				<tr>
					<td align="right">案件类型</td>
					<td><k:dictionary constName="CASE_TYPE" name="caseTypeId" width="150"/></td>
				
					<td align="right">手次</td>
					<td><k:dictionary constName="HANDLE" name="handle" width="150"/></td>
					<td></td>
					<td colspan="3"><a class="easyui-linkbutton" href="#"
							data-options="width:73,height:23,onClick:search"><span style="color:white;">查&nbsp;&nbsp;询</span></a>
							<a class="easyui-linkbutton" href="#"
							data-options="width:73,height:23"
							onclick="javascript:_search_form.form('reset');"><span style="color:white;">重置查询</span></a></td>
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



