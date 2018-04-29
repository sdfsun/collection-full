<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function(){
	
	var province=$('#areaId1').combogrid({
		mode : 'remote',
		url : '${ctx}/region/getProvince',
		idField : 'id',
		textField : 'name',
		editable:false,
		readonly:true,
		required:true,
		panelWidth:150,
		columns : [ [ {
			width : 135,
			field : 'name',
			title : '省'
		} ] ],
		onSelect : function(index, data) {
			city.combogrid('grid').datagrid('load', {
				provinceId : data.id
			});
		},
		onChange : function(newvalue, oldvalue) {
		
			if(oldvalue=='' && newvalue!=''){ //编辑地址， 调用setvalue时触发
				city.combogrid('grid').datagrid('load', {
					provinceId : newvalue
				});
			}else{
				if (oldvalue != newvalue) {
					city.combogrid('setValue', '');
					distinct.combogrid('setValue', '');
					details.attr('value', '');
				}
				if (newvalue == '') {
					province.combogrid('grid').datagrid('load');
				}
			}
			
		}
	});
	var city=$('#areaId2').combogrid({
		mode : 'remote',
		url : '${ctx}/region/getCity',
		idField : 'id',
		textField : 'name',
		editable:false,
		readonly:true,
		required:true,
		panelWidth:150,
		columns : [ [ {
			width : 135,
			field : 'name',
			title : '市'
		} ] ],
		
		onSelect : function(index, data) {
			distinct.combogrid('grid').datagrid('load', {
				cityId : data.id
			});
		},
		onChange : function(newvalue, oldvalue) {
			if(oldvalue=='' && newvalue!=''){ //编辑地址， 调用setvalue时触发
				distinct.combogrid('grid').datagrid('load', {
					cityId : newvalue
				});
				
			}else if (oldvalue != newvalue) {
				distinct.combogrid('setValue', '');
				details.attr('value', '');
			}
			if (newvalue == "") {
				city.combogrid('grid').datagrid('load', {});
			}
		}
	});
	var distinct=$('#areaId3').combogrid({
		mode : 'remote',
		url : '${ctx}/region/getArea',
		idField : 'id',
		textField : 'name',
		editable:false,
		readonly:true,
		required:true,
		panelWidth:150,
		columns : [ [ {
			width : 135,
			field : 'name',
			title : '区'
		} ] ]
	});

});


</script>
<div>
	<form id="plan_form" class="dialog-form" method="post">
		<input type="hidden"  name="id" value="${id}"/>
		<input type="hidden"  name="state" value="${state}"/>
		<div></div>
		<div>
			<label >外访员</label>
			<input type="text" id="visitUserId" name="visitUserId" style="width:220px;" class="easyui-textbox"/>
			
		</div>
		<div>
			<label >预计外访时间 </label>
			<input id="visitTime" name="visitTime" style="width:220px;" class="easyui-datebox" value="${visitTime}"/> 
		</div>
	
	</form>
</div>