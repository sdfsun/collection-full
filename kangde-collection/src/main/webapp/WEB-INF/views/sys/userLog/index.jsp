<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; margin-top:7px; height: 72px; width: 100%; overflow-y: hidden;">
        <form id="_search_form" style="padding: 0px;">
        	<table border="0">
        		<tr>
				<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作时间&nbsp; 
						<input name="startTimeS"	class="easyui-datetimebox"
						style="width: 160px;" data-options="editable:false" /> 
							&nbsp;至&nbsp; 
						<input name="startTimeE" class="easyui-datetimebox" 
						style="width: 160px;" data-options="editable:false" /> 
					</td> -->
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户登录名称&nbsp; </td>
					<td><input name="loginName" style="width:150px;" class="easyui-textbox" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IP地址&nbsp; </td>
					<td><input name="ipAddr" class="easyui-textbox" style="width:150px;"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志类型&nbsp; </td>
					<td><input name="type" style="width:150px;" class="easyui-textbox" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作时间&nbsp;  <input
						id="startTimeS" name="startTimeS"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var startTimeE=$dp.$('startTimeE');WdatePicker({onpicked:function(){startTimeE.focus();},maxDate:'#F{$dp.$D(\'startTimeE\')}'})" />
						&nbsp;至&nbsp; <input id="startTimeE" name="startTimeE"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTimeS\')}'})" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户中文名称&nbsp;</td>
					<td><input name="userName" style="width:150px;" class="easyui-textbox" />
					
					
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" 
					href="#" data-options="width:73,height:23,onClick:search"><span style="color:white;">查&nbsp;&nbsp;询</span></a>
					<a class="easyui-linkbutton" href="#" data-options="width:73,height:23"
					onclick="javascript:_search_form.form('reset');"><span style="color:white;">重置查询</span></a>
					</td>
				</tr>
			</table>
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid"></table>
    </div>
</div>


   