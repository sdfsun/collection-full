<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<jsp:include page="script.jsp"></jsp:include>
<%-- 列表右键 --%>
<!-- <div id="_datagrid_menu" class="easyui-menu"
	style="width: 120px; display: none;">
	<div onclick="showDialog();" data-options="iconCls:'easyui-icon-add'">新增批次</div>
	<div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑批次</div>
	<div onclick="del();" data-options="iconCls:'easyui-icon-remove'">删除批次</div>
	<div onclick="batch();" data-options="iconCls:'easyui-icon-remove'">批次详情</div>
</div> -->
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true, region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; margin-top:5px; height: 69px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 0px;">

<!-- 			<table id="table_query"> -->
			<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp; </td>
					<td><input id="batchCode1"name="batchCode"/></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件类型&nbsp; </td>
					<td><k:dictionary constName="CASE_TYPE" name="typeId"
							required="true" width="150" /></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; </td>

					<td><input id="search_entrustId" name="entrustId" /></td>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案日期&nbsp; </td>
					<td><input type="text" id="beginDate" name="beginDate"
						maxLength="255" class="easyui-datebox" width="100"
						data-options="editable:false,required:false,missingMessage:'',validType:['minLength[1]']" />
						&nbsp;至 &nbsp; <input type="text" id="beginDate1"
						name="beginDate1" maxLength="255" class="easyui-datebox"
						width="100"
						data-options="editable:false,required:false,missingMessage:'',validType:['minLength[1]']" /></td> -->
				
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案日期 &nbsp; <input
						id="beginDate" name="beginDate"
						style="width: 143px;height:23px;" class="Wdate textbox"
						onFocus="var beginDate1=$dp.$('beginDate1');WdatePicker({onpicked:function(){beginDate1.focus();},maxDate:'#F{$dp.$D(\'beginDate1\')}'})" />
						&nbsp;至 &nbsp;<input id="beginDate1" name="beginDate1"
						style="width: 143px;height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}'})" />
					</td>
				</tr>

				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次状态&nbsp; </td>
					<td><k:dictionary constName="BATCH_TYPE" name="state"
							required="true" selectType="all" width="150" /></td>

					<td></td>
					<td><a class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
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
		<table id="_datagrid">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true,width:150"></th>
					<th data-options="title:'批次号',field:'batchCode',width:120,sortable:true,align:'left',
					 formatter:function(value, row, index){
					 		return openCase(value, row, index);
					 		
                		}"></th>
                	<th data-options="title:'委托方',field:'entrustName',width:120,sortable:true"></th>
                	<!-- <th data-options="title:'业绩率',field:'achieve',width:100"></th> -->
                	<th data-options="title:'总户数',field:'totalNumber',width:60,sortable:true"></th>
                	<th data-options="title:'总金额',field:'totalMoney',width:95,sortable:true,lign:'left',
					 formatter:function(value, row, index){
					 		return fmmoney(value);
					 		
                		}"></th>
                	<th data-options="title:'案件类型',field:'typeId',width:95,sortable:true,lign:'left',
					 formatter:function(value, row, index){
					 		return app.dictName('CASE_TYPE', value);
					 		
                		}"></th>	
                		
                	<th data-options="title:'委案日期',field:'beginDate',width:95,sortable:true,lign:'left',
					 formatter:function(value, row, index){
					 	   return fmDate(value);
                		}"></th>
                	<th data-options="title:'退案日期',field:'endDate',width:95,sortable:true,lign:'left',
					 formatter:function(value, row, index){
					 	   return fmDate(value);
                		}"></th>
                		<th data-options="title:'批次状态',field:'state',width:95,lign:'left',
					 formatter:function(value, row, index){
					 		return app.dictName('BATCH_TYPE', value);
                		}"></th>	
                	<th data-options="title:'备注',field:'remark',width:120"></th>	
                	<th data-options="title:'修改时间',field:'modifyTime',width:130,sortable:true,lign:'left',
					 formatter:function(value, row, index){
					 	   return fmDateTime(value);
                		}"></th>	
				</tr>
			</thead>
		</table>
	</div>
</div>