<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ tagliburi ="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var caseId = "${caseId}";
	//请求基础路径
	var baseUrl = ctx + "/collection/linkman";

	$(function() {
		_form = $('#_form').form();
		//数据列表
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/query' + "?caseId=" + caseId,
							fit : true,
							border : false,
							method : 'GET',
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : true,//自适应列宽
							singleSelect : true,
							striped : true,//显示条纹
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							toolbar : [
							           
<shiro:hasPermission  name="detail:linkmanphone:save">
							  {
								text : '新增',
								iconCls : 'eu-icon-xinzeng',
								handler : function() {
									showDialog(caseId);
								}
							},
</shiro:hasPermission>
							
							
					
							
							<shiro:hasPermission  name="detail:linkmanphone:update">
								{
									text : '编辑',
									iconCls : 'eu-icon-bianji',
									handler : function() {
										editLink();
									}
								},
							</shiro:hasPermission>
							<shiro:hasPermission  name="detail:linkmanphone:delete">
							 {
									text : '删除',
									iconCls : 'eu-icon-shanchu',
									handler : function() {
										del()
									}
								}, 
							</shiro:hasPermission>
								
							<shiro:hasPermission  name="detail:linkmanphone:markyes">
							{
								text : '标记为有效',
								iconCls : 'eu-icon-tongyi',
								handler : function() {
									enable();
								}
							},
							</shiro:hasPermission>
								
							<shiro:hasPermission  name="detail:linkmanphone:markno">
							 {
									text : '标记为无效',
									iconCls : 'eu-icon-butongyi',
									handler : function() {
										invalid();
									}
								} 
							</shiro:hasPermission>
							
						],
							columns : [ [
								
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
										field : 'phoneType',
										title : '类别',
										width : 80,
										formatter : function(value, row, index) {
											return app.dictName('PHONE_TYPE',
													value)
										}
									},
									{
										field : 'status',
										title : '状态',
										width : 100,
										formatter : function(value, row, index) {
											if (value == 1) {
												return "有效";
											} else if (value == 2) {
												return "无效";
											} else if (value == 0) {
												return "未知";
											} else {
												return value;
											}
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
											}else if(value == 3){
												return "查资";
											} else{
												return "贴档";
											} 
										}
									},
									{
										field : 'phone',
										title : '电话',
										width : 120,
										formatter : function(value, row, index) {
											if (value) {
												  var  re = /^1\d{10}$/;
												     if (re.test(value)) {
												    		var p = "<a target='_blank' style='text-decoration:underline;cursor:pointer' onclick=aaa('"+ row.phone+ "')><font color=blue>"+value+"</font></a>"
															+ "<span id='callOut' class='icon icon-tel' onclick=callOut('"
															+ row.phone
															+ "','"
															+ row.name
															+ "','"
															+ row.id
															+ "','"
															+ row.relation
															+ "')>&nbsp;&nbsp;</span>";

													return p;
												     } else {
												    		var p =value
															+ "<span id='callOut' class='icon icon-tel' onclick=callOut('"
															+ row.phone
															+ "','"
															+ row.name
															+ "','"
															+ row.id
															+ "','"
															+ row.relation
															+ "')>&nbsp;&nbsp;</span>";

													return p;
												     }
											}
											return value;
										}
									},
									{
										field : 'remark',
										title : '备注',
										width : 200
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
	
	function aaa(phone){
		var RoleUrl =ctx+ "/collection/casedetail/Phoneshow?phone="+phone;
		app.openViewDialogNoBtn(50,'电话信息', 400, 190, RoleUrl, 'GET',
				function() {
				});
	}
	
	//显示弹出窗口 新增：row为空 编辑:row有值
	function showDialog(caseId) {
		var inputUrl = baseUrl + "/input?caseId=" + caseId;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '新增电话', 600, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
		});
	}

	function showEditDialog(row) {
		var inputUrl = baseUrl + "/input?id=" + row.id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '编辑联系人', 600, 300, inputUrl, 'GET', function() {
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
			row._method = "PUT";//修改表单提交方式为PUT,修改请求
			_form.form('load', row);

		});
	}

	function editLink() {
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			showEditDialog(row);
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
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
	}

	function callOut(tel, name, id, relation) {
		if (tel == null || tel == '') {
			$.messager.alert('Warning', '电话号码不正确！');
			return;
		}
		$.ajax({
					type : "POST",
					url : baseUrl + "/callOut?tel=" + tel,
					dataType : "json",
					async : false,
					success : function(data) {
						var createTime = parent.$("#iframephonerecord")[0].contentWindow.createTime;
						$(createTime).val(data.createDate);
						var linkmanId = parent.$("#iframephonerecord")[0].contentWindow.linkmanId;
						$(linkmanId).val(id);
						var createTime = parent.$("#iframephonerecord")[0].contentWindow.createTime;
						$(createTime).val(data.createDate);
						var contactId = parent.$("#iframephonerecord")[0].contentWindow.$("#contactId");
						contactId.textbox('setValue', tel);
						$.messager.alert('提示', data.msg);
					}
				});
	}

	//删除
	function del(rowIndex) {
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认提示！', '您确定要删除？', function(r) {
				if (r) {
					$.post(baseUrl + '/deleteById?id=' + row.id, function(data) {
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

	//有效无效
	function enable() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		//多行校验
		if (rows.length > 1) {
			eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');

		} else if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else if(1==row.status){
			eu.showAlertMsg("已是有效数据！", 'warning');
		}else{
			$.messager.confirm('确认提示！', '您确定要修改吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					$.post(baseUrl + '/disableCaseLinkman', row, function(date) {
						//渲染结果
						app.renderAjax(date, function(json) {
							app.load(_datagrid);
						});
					}, 'json');

				}
			});

		}
	}

	//有效无效
	function invalid() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		//多行校验
		if (rows.length > 1) {
			eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');

		} else if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else if(2==row.status){
			eu.showAlertMsg("已是无效数据！", 'warning');
		}else{

			$.messager.confirm('确认提示！', '您确定要修改吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					$.post(baseUrl + '/enableCaseLinkman', row, function(
							date) {
						//渲染结果
						app.renderAjax(date, function(json) {
							app.load(_datagrid);
						});
					}, 'json');

				}
			});

		}
	}
</script>
