<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<jsp:include page="script.jsp"></jsp:include>
<script type="text/javascript">
$().ready(function(){
	
	var myDate = new Date(); 
	$("#year").combobox('setValue',myDate.getFullYear())
	$("#month").combobox('setValue',myDate.getMonth()+1)
});
</script>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">

	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 40px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
			<table>
				<tr>
					<td id="a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年份&nbsp;
						<select class="easyui-combobox" name="year" id="year"
						style="width: 150px; height: 23px;">
							<!-- <option value=""></option> -->
							<option value=""></option>
							<option value="2020">2020年</option>
							<option value="2019">2019年</option>
							<option value="2018">2018年</option>
							<option value="2017">2017年</option>
							<option value="2016">2016年</option>
							<option value="2015">2015年</option>

					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月份&nbsp;
						<select class="easyui-combobox" name="month" id="month"
						style="width: 150px; height: 23px;">
							<option value=""></option>
							<option value="1">一月</option>
							<option value="2">二月</option>
							<option value="3">三月</option>
							<option value="4">四月</option>
							<option value="5">五月</option>
							<option value="6">六月</option>
							<option value="7">七月</option>
							<option value="8">八月</option>
							<option value="9">九月</option>
							<option value="10">十月</option>
							<option value="11">十一月</option>
							<option value="12">十二月</option>

					</select>
					</td>
<td hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; <input id="orgId" name="orgId" /></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <!-- <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a> --></td>
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
			
					<th
						data-options="field : 'empName',title : '员工',width : 120"></th>
					<th
						data-options="field : 'achieve',title : '目标业绩（元）',editor:{type:'numberbox',options:{precision:2}},width : 120,
						formatter : function(value, row, index) {
							if (!value) {
 							return '0.00';
 						}else{
                        	return $.fmoney(value); 
 						}
 						return value;
 					} "></th>
					<th
						data-options="field : 'createTime',title : '创建日期',width : 120,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>
					<th
						data-options="field : 'createName',title : '创建人',width : 120,"></th>
					<th
						data-options="field : 'modifyTime',title : '最后修改日期',width : 120,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>
					<th
						data-options="field : 'modifyName',title : '操作人',width : 120,"></th>
				</tr>
			</thead>
		</table>
	</div>
</div>



