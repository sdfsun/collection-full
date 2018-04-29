<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<div id="_datagrid_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="show();" data-options="iconCls:'easyui-icon-edit'">查看异常信息</div>
</div>

<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; margin-top:8px; height: 70px; width: 100%; overflow-y: hidden;">
        <form id="_search_form" style="padding: 0px;">
        	<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调度状态&nbsp; </td>
					<td><k:dictionary width="150" constName="SCHEDU_LOG_STATE" name="state" selectType="all" />
					</td>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;调度时间&nbsp; </td>
					<td><input name="startTimeS" class="easyui-datetimebox"
						style="width: 150px;" data-options="editable:false" /> 
							&nbsp;至&nbsp; 
						<input name="startTimeE" class="easyui-datetimebox" 
						style="width: 150px;" data-options="editable:false" /> 
					</td>
					
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务名称&nbsp; </td>
					<td><input name="taskName" style="width:150px;" class="easyui-textbox" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务主机(名称&nbsp;或&nbsp;IP)&nbsp; </td>
					<td><input name="serverHost" style="width:150px;" class="easyui-textbox" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" 
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


   