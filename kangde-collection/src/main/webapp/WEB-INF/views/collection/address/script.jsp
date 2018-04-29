<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ tagliburi ="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _dialog;
	var caseId = "${caseId}";
	var visit_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/address";

	$(function() {
		_form = $('#_form').form();
		//数据列表
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryDedail' + "?caseId="
									+ caseId,
							fit : true,
							border : false,
							method : 'GET',
							singleSelect : true,
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : true,
							striped : true,//显示条纹
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							toolbar : [

<shiro:hasPermission  name="detail:address:save">	
{
	text : '新增',
	iconCls : 'eu-icon-xinzeng',
	handler : function() {
		showDialog()
	}
}, 
</shiro:hasPermission>     

<shiro:hasPermission  name="detail:address:update">	
{
	text : '编辑',
	iconCls : 'eu-icon-bianji',
	handler : function() {
		editLink();
	}
}, 
</shiro:hasPermission>  

<shiro:hasPermission  name="detail:address:delete">	
{
	text : '删除',
	iconCls : 'eu-icon-shanchu',
	handler : function() {
		del()
	}
}, 
</shiro:hasPermission>  

<shiro:hasPermission  name="detail:address:markyes">	
{
	text : '标记为有效',
	iconCls : 'eu-icon-tongyi',
	handler : function() {
		updateAddressStatus(1);
	}
},
</shiro:hasPermission>  

<shiro:hasPermission  name="detail:address:markno">	
{
	text : '标记为无效',
	iconCls : 'eu-icon-butongyi',
	handler : function() {
		updateAddressStatus(2);
	}
}, 
</shiro:hasPermission>  
							],
							columns : [ [
									{
										field : 'id',
										title : 'ID',
										width : 100,
										hidden : true
									},

									{
										field : 'name',
										title : '姓名',
										width : 100
									},
									{
										field : 'relation',
										title : '关系',
										width : 100
									},
									{
										field : 'adrCat',
										title : '类别',
										width : 100,
										formatter : function(value, row, index) {
											return app.dictName('ADDRESS_TYPE',
													value)
										}
									},

									{
										field : 'status',
										title : '状态',
										width : 100,
										formatter : function(value, row, index) {
											return app.dictName(
													'ADDRESS_STATUS', value)
										}
									},
									{
										field : 'source',
										title : '来源',
										width : 100,
										formatter : function(value, row, index) {
											if (value == 1) {
												return "新增";
											} else if (value == 0) {
												return "贴档";
											} else if(value == 3){
												return "查资";
											}else{
												return "贴档";
											} 
										}
									},
									{
										field : 'fullAddress',
										title : '名称/地址',
										width : 500,
										formatter : function(value, row, index) {
											if (row.areaName2 && row.areaName3) {
												var p = "<a target='_blank' style='text-decoration:underline;cursor:pointer' onclick=aaa('"+ row.areaName2+ "','"+ row.areaName3+ "')><font color=blue>"+value+"</font></a>"
												return p;
											}else{
												return value;
											}
											}
									},
									{
										field : 'remark',
										title : '备注',
										width : 200
									},
									{
										field : 'wf',
										title : '外访',
										width : 100,
										formatter : function(value, row, index) {
											if (row.visApp == 0) {
												return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=applyToVisit("'
														+ row.id + '","'+row.status+'")>申请</a>';
											}else{
												return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=Revoke("'
												+ row.id + '","'+row.status+'")>撤销</a>';
											}
										}
									},
									{
										field : 'xh',
										title : '信函',
										width : 100,
										formatter : function(value, row, index) {
											if (row.mailApp == 0) {
												return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=applyToLetter("'
														+ row.id + '","'+row.status+'")>申请</a>';
											}
										}
									},
									{field:'createTime',title:'申请日期',width:95,sortable:true,
										formatter : function(value, row, index) {
					     					if (value) {
					     						return $.formatDate(value, 'yyyy-MM-dd');
					     					}
					     					return value;
					     				}}] ],
							onLoadSuccess : function() {
								app.unCheckAll(this);//取消所有选中
							}
						}).datagrid('showTooltip');

	});
	function aaa(areaName2,areaName3){
		if(areaName2==null || areaName3==null){
			eu.showAlertMsg("请完善地址信息", 'warning');
		}else{
		var RoleUrl =ctx+ "/collection/casedetail/PoliceStationShow?areaName2="+areaName2+"&areaName3="+areaName3;
		app.openViewDialogNoBtn(50,'地址当地派出所', 700, 300, RoleUrl, 'GET',
				function() {
				});
		}
	}

	function showDialog(row) {
		var inputUrl = baseUrl + "/input?caseId=" + caseId;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '新增地址', 700, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
			if (row) {
				row._method = "PUT";//修改表单提交方式为PUT,修改请求
				row.birthday = $.formatDate(row.birthday, 'yyyy-MM-dd');
				_form.form('load', row);
			}
		});
	}

	function showVisitDialog(id) {

		var url = baseUrl + "/checkAddress?id=" + id;
		$.ajax({
			type : "GET",
			url : url,
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.state == 0) {
					$.messager.alert('提示', data.msg);
				} else {
					var inputUrl = baseUrl + "/visit?caseId=" + caseId + "&id="
							+ id;
					_dialog = app.openFormDialog(function() {
						visit_form.submit();//提交表单
					}, '外访申请', 900, 310, inputUrl, 'GET', function() {
						//初始化会话页面组件
						visitformInit();
					});
				}

			}
		});

	}
	
	function Revoke(id) {
		var RoleUrl = ctx+"/collection/visitrecordappointed/revoke";
		 _dialog = app.openFormDialog(function(){
   	    	_form1.submit();
   	   	}, '撤销外访',460, 280,RoleUrl+"/"+id, 'GET',function(){
   	   	 inputpoint();
   	   		_form.form('load',row);
   	   	},"撤销","关闭");

	}

	//初始化表单
	 function inputpoint() {
	 	_form1 = $('#_form').form({
	 		//表单提交地址
	 		url : ctx+"/collection/visitrecordappointed/cancelVisit",
	 		//表单提交数据之前回调函数
	 		onSubmit : function() {
	 			$.messager.progress({
	 				title : '提示信息！',
	 				text : '数据处理中，请稍后....'
	 			});
	 			//$(this).form('enableValidation');
	 			var isValid = $(this).form('validate');
	 			if (!isValid) {
	 				$.messager.progress('close');
	 			}
	 			return isValid;
	 		},
	 		   //表单成功提交回调函数
	         success: function(data){
	         	 $.messager.progress('close');
	             //渲染结果
	             app.renderAjax(data,function(json){
	             	_dialog.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
	             });
	         }
	 	});
	 }
	
	//初始化表单
	function formInit() {

		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl,
			//表单提交数据之前回调函数
			onSubmit : function() {
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});
				//$(this).form('enableValidation');
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid;
			},
			//表单成功提交回调函数
			success : function(data) {
				$.messager.progress('close');
				//渲染结果
				app.renderAjax(data, function(json) {
					_dialog.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}

	//初始化表单
	function visitformInit() {
		visit_form = $('#visit_form').form({
			//表单提交地址
			url : baseUrl + "/applyToVisit",
			//表单提交数据之前回调函数
			onSubmit : function() {
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});
				//$(this).form('enableValidation');
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid;
			},
			//表单成功提交回调函数
			success : function(data) {
				$.messager.progress('close');
				//渲染结果
				app.renderAjax(data, function(json) {
					_dialog.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}

	function applyToVisit(id,status) {
		if(1==status){
			showVisitDialog(id);
		}else{
			eu.showAlertMsg("无效地址, 不允许申请外访!", 'warning');
		}
	}
	function applyToLetter(id,status) {
		if(1==status){
		$.messager.confirm('信函申请', '您确认对该地址申请信函邮寄吗？', function(r) {
			if (r) {
				$.post(baseUrl + '/applyToLetter', {
					id : id,
					caseId : "${caseId}"
				}, function(data) {
					//渲染结果
					app.renderAjax(data, function(json) {
						app.load(_datagrid);
					});
				}, 'json');

			}
		});
		}else{
			eu.showAlertMsg("无效地址, 不允许申请信函邮寄!", 'warning');
		}
	}

	function editLink() {
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			showEditDialog(row);
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}
	function showEditDialog(row) {
		var inputUrl = baseUrl + "/input?id=" + row.id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '编辑地址', 600, 345, inputUrl, 'GET', function() {
			//初始化会话页面组件
			_form = $('#_form').form({
				//表单提交地址
				url : baseUrl + '/update',
				//表单提交数据之前回调函数
				onSubmit : function() {
					$.messager.progress({
						title : '提示信息！',
						text : '数据处理中，请稍后....'
					});

					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.progress('close');
						return false;
					}

					return true;
				},
				//表单成功提交回调函数
				success : function(data) {
					$.messager.progress('close');
					//渲染结果
					app.renderAjax(data, function(json) {
						_dialog.dialog('destroy');//销毁对话框
						app.reload(_datagrid);//重新加载列表数据
					});
				}
			});
			$("input[name=id]").val(row.id);
			$("input[name=caseId]").val(row.caseId);
			$("#inputname").textbox('setValue', row.name);
			$("#adrCat").combobox('setValue', row.adrCat);
			$("#relation").combobox('setValue', row.relation);
		
			$("#remark").textbox('setValue', row.remark);
			$("#areaId1").combogrid("setValue", row.areaId1);
			$("#areaId2").combogrid("setValue", row.areaId2);
			$("#areaId3").combogrid("setValue", row.areaId3);
			 var ars=row.address;
		     var result = ars.split("/");
		    $("#address").textbox('setValue', result[result.length-1]);
		});
	}

	//删除
	function del(rowIndex) {
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认提示！', '您确定要删除？', function(r) {
				if (r) {
					$.post(baseUrl + '/deleteById?id=' + row.id, {}, function(
							data) {
						//渲染结果
						app.renderAjax(data, function(json) {
							app.load(_datagrid);
						});
					}, 'json');

				}
			});
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}

	function updateAddressStatus(status) {
		var row = _datagrid.datagrid('getSelected');
		if(status==1){
			if(1==row.status){
				eu.showAlertMsg("已是有效数据！", 'warning');
			}else if (row) {
			$.messager.confirm('确认提示！', '您确定要修改吗？', function(r) {
				if (r) {
					$.post(baseUrl + '/enableAddress?id=' + row.id
							, {}, function(data) {
						//渲染结果
						app.renderAjax(data, function(json) {
							app.load(_datagrid);
						});
					}, 'json');

				}
			});
		} 	}
		else if(status==2){
			if(2==row.status){
				eu.showAlertMsg("已是无效数据！", 'warning');
			}else if (row) {
				$.messager.confirm('确认提示！', '您确定要修改吗？', function(r) {
					if (r) {
						$.post(baseUrl + '/disableAddress?id=' + row.id
								, {}, function(data) {
							//渲染结果
							app.renderAjax(data, function(json) {
								app.load(_datagrid);
							});
						}, 'json');

					}
				});
			} 
		}
		else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}

	}
</script>
