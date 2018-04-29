<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var editRole_dialog;
	var editResource_dialog;
	//请求基础路径
	var baseUrl = ctx + "/sys/employeeInfo";

	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadOrganization();
		loadEntrust('#attachEntrustId', 'all');
		loadAttachOrgId();
		loadOrganization1();
		loadPosition();
		//数据列表
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/query',
							fit : true,
							method : 'GET',
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : false,//自适应列宽
							striped : true,//显示条纹
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							frozenColumns : [ [
									{
										field : 'ck',
										checkbox : true
									},
									{
										field : 'status',
										title : '员工状态',
										width : 100,
										formatter : function(value, row, index) {
											return app.dictName('EMP_STATUS',
													value)
										}
									},
									{
										field : 'loginName',
										title : '账号',
										width : 100,
										formatter : function(value, row, index) {
											return '<a style="color:blue;text-decoration: underline;" onclick="detail()">'
													+ value + '</a>';
										}
									},
									{
										field : 'userName',
										title : '姓名',
										width : 100
									},
									{
										field : 'sex',
										title : '性别',
										width : 80,
										formatter : function(value, row, index) {
											return app.dictName('SEX', value)
										}
									}, ] ],
							columns : [ [
									{
										field : 'joinTime',
										title : '入职时间',
										align : 'center',
										width : 100,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'posiName',
										title : '岗位',
										align : 'center',
										width : 100
									},
									{
										field : 'orgId',
										title : '机构',
										align : 'center',
										width : 100,
										formatter : function(value, row, index) {
											if (value) {
												return getOrgName(value);
											}
											return value;
										}
									},
									   
									  {field:'createTime',title:'录入时间',align:'left',width:150,
									  	formatter:function(value, row, index){
									  		if(value){
									  			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
									  		}
									  		return value;
									  	}
									  },
									{
										field : 'modifyTime',
										title : '修改时间',
										align : 'left',
										width : 150,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd HH:mm:ss');
											}
											return value;
										}
									} ] ],
							toolbar : [ 
							<shiro:hasPermission name="employeeInfo:save">
							    {
								text : '新增',
								iconCls : 'eu-icon-xinzeng',
								handler : function() {
									showDialog()
								}
							}, '-', 
							</shiro:hasPermission>
							<shiro:hasPermission name="employeeInfo:update">
							{
								text : '编辑',
								iconCls : 'eu-icon-bianji',
								handler : function() {
									edit()
								}
							},
							
							/*  '-',
							{
							    text: '删除',
							    iconCls: 'easyui-icon-edit',
							    handler: function () {
							        del()
							    }
							}, */ 
							'-', 
							</shiro:hasPermission>
							<shiro:hasPermission name="employeeInfo:enable">
							{
								text : '启用',
								iconCls : 'eu-icon-qiyong',
								handler : function() {
									start()
								}
							}, '-', 
							</shiro:hasPermission>
							<shiro:hasPermission name="employeeInfo:disable">
							{
								text : '停用',
								iconCls : 'eu-icon-tingyong',
								handler : function() {
									stop()
								}
							}
							, '-', 
							</shiro:hasPermission>
							/* <shiro:hasPermission name="employeeInfo:right">
							{
								text : '设置权限',
								iconCls : 'eu-icon-quanxian',
								handler : function() {
									editResources()
								}
							} 
							</shiro:hasPermission> */
							],
							onLoadSuccess : function() {
								app.unCheckAll(this);//取消所有选中
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								app.unCheckAll(this);//取消所有选中
								$(this).datagrid('selectRow', rowIndex);
								$('#_datagrid_menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							}//,
						/* onDblClickRow : function(rowIndex, rowData) {
							edit(rowIndex, rowData);
						} */
						}).datagrid('showTooltip');

	});

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
	//显示弹出窗口 新增：row为空 编辑:row有值
	function showDialog(row) {
		var inputUrl = baseUrl + "/input";
		if (row != undefined && row.id) {
			inputUrl = inputUrl + "/" + row.id;
		}
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '员工信息',810, 250, inputUrl, 'GET', function() {
			//初始化会话页面组件
// 			alert("111");
// 			alert(row.ccPwd);
			
			 loadForm(row);
		});
	}

	
	function loadForm(row){
		loadOrganization();
		loadOrganization1();
		loadPosition();
		formInit();
		//加载机构
		if (row) {
			if (row.birthday) {
				row.birthday = $.formatDate(row.birthday, 'yyyy-MM-dd');
			}
			if (row.joinTime) {
				row.joinTime = $.formatDate(row.joinTime, 'yyyy-MM-dd');
			}
			_form.form('load', row);
			 $("#ccPwd").textbox('setValue','******');
			 $("#password").textbox('setValue','******');
			loadUserRole(row, 220);
		} else {
			loadUserRole(null, 220);
			
		}
		loadAttachOrgId();
		loadEntrust('#attachEntrustId', 'select');
	}
	//编辑
	function edit(rowIndex, rowData) {

		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		//选中的行（第一次选择的行）
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			//多行校验
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			} else {
				showDialog(row);
			}
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}
	
	
	

	//详情
	function detail(rowIndex, rowData) {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		//选中的行（第一次选择的行）
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			//多行校验
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			} else {
				showDialog2(row);
			}
		}
	}
	
	
	// 新增：row为空 编辑:row有值
	function showDialog2(row) {
		var inputUrl = baseUrl + "/input" + "/" + row.id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openViewDialog('员工详情', 500, 460, inputUrl, 'GET',
				function() {
					loadForm(row);
					_form.form('disable', true);
				});
	}

	//启用
	function start(rowIndex) {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.messager.confirm('确认提示！', '您确定要启用所选员工吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					delete row['joinTime'];
					delete row['birthday'];
					delete row['positionModel'];
					var ids = new Array();
					$.each(rows, function(i, row) {
						ids[i] = row.id;
					});
					$.post(baseUrl + '/updateForStatus', {
						ids : ids
					}, function(date) {
						//渲染结果
						app.renderAjax(date, function(json) {
							app.load(_datagrid);
						}, false);
					}, 'json');
				}
			});
		}
	}

	//停用
	function stop(rowIndex) {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.messager.confirm('确认提示！', '您确定要停用所选员工吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					delete row['joinTime'];
					delete row['birthday'];
					delete row['positionModel'];
					var ids = new Array();
					$.each(rows, function(i, row) {
						ids[i] = row.id;
					});
					$.post(baseUrl + '/updateForStatusNo', {
						ids : ids
					}, function(date) {
						//渲染结果
						app.renderAjax(date, function(json) {
							app.load(_datagrid);
						}, false);
					}, 'json');
				}
			});
		}
	}


	//删除
	function del(rowIndex) {
		var row;
		if (rowIndex == undefined) {
			row = _datagrid.datagrid('getSelected');
		}
		if (row != undefined) {
			$.messager.confirm('确认提示！', '您确定要删除该员工吗？', function(r) {
				if (r) {
					$.post(baseUrl + '/' + row.id, {
						'_method' : 'DELETE'
					}, function(data) {
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
	function loadParent(parentId) {
		var url = baseUrl + '/parentPosition?selectType=select';
		if (parentId) {
			url = url + "&parentId=" + parentId;
		}
		$('#parentId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			width : 260,
			valueField : 'id'

		});
	}
	//加载岗位
	function loadPosition() {
		var url = ctx + '/sys/position/parentPosition?selectType=select';
		$('#positionId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			//validType:['combotreeRequired'],
			width : 150,
			valueField : 'id'
		});
	}

	//加载父级机构
	function loadOrganization() {
		var url = ctx
				+ '/sys/organization/parentOrganization?selectType=select';
		$('#orgId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			//validType:['combotreeRequired'],
			width : 150,
			valueField : 'id'
		});
	}
	//加载父级机构
	function loadAttachOrgId() {
		var url = ctx + '/sys/organization/parentOrganization';
		$('#attachOrgId').combotree({
			url : url,
			multiple : true,//是否可多选
			editable : true,//是否可编辑
			width : 150,
			valueField : 'id'
		});
	}
	//加载父级机构
	function loadOrganization1() {
		var url = ctx + '/sys/organization/parentOrganization';
		$('#orgId1').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			width : 150,
			valueField : 'id'
		});
	}

	//修改用户角色
	function editRoles() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		//多行校验
		if (rows.length > 1) {
			eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			return;
		} else {
			if (rows.length == 0) {
				eu.showAlertMsg("请选择要操作的数据！", 'warning');
			} else {
				var row = rows[0];
				var editRoleUrl = baseUrl + "/editRole?userId=" + row.id;
				//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				editRole_dialog = app.openFormDialog(function() {
					$("#user_role_form").form('submit');//提交表单
				}, '设置权限组', 800, 200, editRoleUrl, 'GET', function() {
					loadUserRole(row, 460);
					$('#user_role_form').form({
						//表单提交地址
						url : baseUrl + "/editRole",
						//表单提交数据之前回调函数
						onSubmit : function() {
							$.messager.progress({
								title : '提示信息！',
								text : '数据处理中，请稍后....'
							});
							return true;
						},
						//表单成功提交回调函数
						success : function(data) {
							$.messager.progress('close');
							//渲染结果
							app.renderAjax(data, function(json) {
								editRole_dialog.dialog('destroy');//销毁对话框
							});
						}
					});
				});
			}
		}
	}
	//修改用户权限(资源)
	function editResources() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		//多行校验
		if (rows.length > 1) {
			eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			return;
		} else {
			if (rows.length == 0) {
				eu.showAlertMsg("请选择要操作的数据！", 'warning');
			} else {
				var row = rows[0];
				var editRoleUrl = baseUrl + "/editResource?userId=" + row.id;
				//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				editResource_dialog = app.openFormDialog(function() {
					$("#user_resource_form").form('submit');//提交表单
				}, '设置权限', 400, 500, editRoleUrl, 'GET', function() {
					$('#user_resource_form').form(
							{
								//表单提交地址
								url : baseUrl + "/editResource",
								//表单提交数据之前回调函数
								onSubmit : function(param) {
									$.messager.progress({
										title : '提示信息！',
										text : '数据处理中，请稍后....'
									});
									var nodes1 = $('#resourceIds').tree(
											'getChecked', 'indeterminate');
									var nodes2 = $('#resourceIds').tree(
											'getChecked');
									var resourceIds = [];
									$.merge(nodes1, nodes2);
									for (var i = 0; i < nodes1.length; i++) {
										resourceIds.push(nodes1[i].id);
									}
									param.resourceIds = resourceIds;
									return true;
								},
								//表单成功提交回调函数
								success : function(data) {
									$.messager.progress('close');
									//渲染结果
									app.renderAjax(data, function(json) {
										editResource_dialog.dialog('destroy');//销毁对话框
									});
								}
							});
				});
			}
		}
	}
	//获取机构名称
	var cacheOrgs = new Object();
	function getOrgName(orgId) {
		if (orgId != undefined) {
			var orgUrl = ctx + "/sys/organization/" + orgId;
			var text = "";
			var org = cacheOrgs[orgId];
			//从缓存获取
			if (org == undefined) {
				//远程获取数据
				$.ajax({
					type : 'get',
					url : orgUrl,
					cache : false,
					async : false,
					dataType : 'json',
					success : function(data) {
						cacheOrgs[orgId] = data;//放入缓存
					}
				});
				org = cacheOrgs[orgId];
			}
			if (org) {
				text = org.name;
			}
			return text;
		}
	}
	//加載用户角色信息
	function loadUserRole(row, width) {
		$('#user_role_form-roleIds').combobox(
				{
					multiple : true,
					width : 150,
					editable : false,
					url : ctx + '/sys/role/combobox',
					onLoadSuccess : function() {
						var uid = null;
						if (row) {
							uid = row.id;
						}
						$.ajax({
							url : ctx + '/sys/employeeInfo/getRoleIds',
							type : "POST",
							dataType : 'json',
							async : true,
							data : {
								"userId" : uid
							},
							success : function(data) {
								var ids = data.obj;
								if (ids.length > 0) {
									for (var i = 0; i < ids.length; i++) {
										$('#user_role_form-roleIds').combobox(
												'select', ids[i]);
									}
								}
							}
						});
					}
				});
	}

	//加载委托方
	function loadEntrust(domId, type) {
		var url = ctx + '/sys/entrust/combobox?selectType=' + type;
		$(domId).combobox({
			url : url,
			multiple : true,//是否可多选
			editable : false,//是否可编辑
			width : 150,
			valueField : 'value',
			textField : 'text'
		});
	}
	//搜索
	function search() {
		//重新加载datagrid
		app.load(_datagrid, $.serializeObject(_search_form));
	}
</script>
