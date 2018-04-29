<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 76px; width: 100%; margin-top:3px;">
		<form id="_search_form" style="padding: 5px;">
			<table border="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方</td>
					<td><input  name="entrustName1" id="entrustName1" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方全称</td>
					<td><input  name="entrustProductId1" id="entrustProductId1" class="easyui-combobox" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加日期</td>
					<td><input id="createTime" name="createTime"
						style="width: 150px;" class="Wdate textbox"
						onFocus="var createTime1=$dp.$('createTime1');WdatePicker({onpicked:function(){createTime1.focus();},maxDate:'#F{$dp.$D(\'createTime1\')}'})" />
						 &nbsp;至&nbsp;
					 	<input id="createTime1" name="createTime1"
						style="width: 150px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createTime\')}'})" /></td>
				</tr>
				<tr>
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



