<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
$().ready(function(){
	var provinces = null;
	var citys = null;
	var distincts = null;
	var detailss = null;
	var getAddress = function(index) {
		var province = $(provinces[index]);
		var city = $(citys[index]);
		var distinct = $(distincts[index]);
		var details = $(detailss[index]);
		province.combogrid({
			mode : 'remote',
			url : ctx+'/region/getProvince',
			idField : 'id',
			textField : 'name',
			editable:false,
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
		city.combogrid({
			mode : 'remote',
			url : ctx+'/region/getCity',
			idField : 'id',
			textField : 'name',
			editable:false,
			required:true,
			panelWidth:150,
			columns : [ [ {
				width : 135,
				field : 'name',
				title : '市'
			} ] ],
			onBeforeLoad : function(param) {
				//param.name = province.combogrid('getValue');
			},
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
		distinct.combogrid({
			mode : 'remote',
			url : ctx+'/region/getArea',
			idField : 'id',
			textField : 'name',
			editable:false,
			required:true,
			panelWidth:150,
			columns : [ [ {
				width : 135,
				field : 'name',
				title : '区'
			} ] ],
			onBeforeLoad : function(param) {
				//param.name = city.combogrid('getValue');
			},
			onChange : function(newvalue, oldvalue) {
				
				if (oldvalue != newvalue) {
					details.attr('value', '');
				}
				if (newvalue == "") {
					distinct.combogrid('grid').datagrid('load', {});
				}
			}
		});
	};
	var selectData = function() {

		provinces = $("input[name$='AreaId1']");
		citys = $("input[name$='AreaId2']");
		distincts = $("input[name$='AreaId3']");
		detailss = $("input[name$='Address']");
		if ($("input[name$='areaId1']"))
			provinces.push($("input[name$='areaId1']"));
		if ($("input[name$='areaId2']"))
			citys.push($("input[name$='areaId2']"));
		if ($("input[name$='areaId3']"))
			distincts.push($("input[name$='areaId3']"));
		if ($("input[name$='address']"))
			detailss.push($("input[name$='address']"));
		provinces.each(function(index) {
			getAddress(index);
		});
	}();

});
</script>