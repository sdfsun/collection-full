<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _search_form;
	var _dialog;
	var baseUrl = ctx + "/collection/visitrecordtoapprove";
	$(function() {
		_search_form = $('#_search_form').form({
			novalidate : true
		});
		loadEntrust('#search_entrustId', 'all');//加载委托方
		findBacthCode('#batchCode');
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryToApprove',
							fit : true,
							method : 'GET',
							singleSelect : false,
							queryParams : $.serializeObject(_search_form),
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : false,//自适应列宽
							striped : true,//显示条纹
							remoteSort : true,//是否通过远程服务器对数据排序
							idField : 'id',
							striped : true,
							columns : [ [
									{
										field : 'id',
										checkbox : true
									},
									{
										field : 'caseModel.collecStateId',
										title : '风控状态',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return app.dictName('CS_STATE',
													value)
										}
									},
									{
										field : 'caseModel.caseCode',
										title : '案件序列号',
										width : 180,
										sortable:true,
										formatter : function(value, row, index) {
											if (row.state == 1) { //1为暂停状态， 不允许查看详情
												return value;
											}
											return '<span style="text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='
													+ row.caseId
													+ '")>'
													+ value + '</span>';
										}
									},
									{
										field : 'caseModel.caseMoney',
										title : '委案金额',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return $.fmoney(value);
										}
									},
									{
										field : 'paidNum',
										title : '已还款',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return $.fmoney(value);
										}
									},
									{
										field : 'name',
										title : '外访姓名',
										width : 95
									},
									{
										field : 'addressModel.relation',
										title : '关系',
										width : 60
									},

									
									{
										field : 'adrCat',
										title : '地址类别',
										width : 95
									},
									{
										field : 'visitAddress',
										title : '外访地址',
										width : 280,
										sortable:true
									},
									{
										field : 'require',
										title : '外访要求',
										width : 280
									},
									{
										field : 'employeeInfo.userName',
										title : '风控员',
										width : 95
									},

									{
										field : 'applyTime',
										title : '申请时间',
										width : 150,
										sortable:true,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd HH:mm:ss');
											}
											return value;
										}
									}, ] ],
							toolbar : [
							<shiro:hasPermission name="visitrecordtoapprove:approvalYes">
							{
								text : '通过',
								iconCls : 'eu-icon-tongyi',
								handler : function() {
									approvalYes()//外访
								}
							}, 
							 '-', 
							</shiro:hasPermission>
							<shiro:hasPermission name="visitrecordtoapprove:approvalNo">
							{
								text : '不通过',
								iconCls : 'eu-icon-butongyi',
								handler : function() {
									approvalNo()//外访
								}
							},
							</shiro:hasPermission>
							],
							rowStyler : function(index, row) {
								var color1 = 0;
								if (row.caseModel) {
									color1 = row.caseModel.color;
								}
								color = app.dictName('CASE_COLOR', color1);
								return 'color:' + color;
							},
							onLoadSuccess : function() {
								$(this).datagrid('showTooltip').datagrid(
										'columnMoving');
								app.unCheckAll(this);//取消所有选中
							}

						});

	});

	function approvalYes() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		var tf = false;
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.each(rows, function(i, row) {
				if (row.approveState == 1 || row.approveState == 2) {
					tf = true;
				}
			});
			if (true == tf) {
				eu.showAlertMsg("您选中的数据中存在已完成审批的数据，不能重复审批！", 'warning');
			} else if (false == tf) {
				var ids = new Array();
				$.each(rows, function(i, row) {
					ids[i] = row.id;
				});
				var RoleUrl = baseUrl + "/input";
				//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				_dialog = app.openFormDialogApproval(function() {
					_form.submit();
				}, '外访审批', 460, 280, RoleUrl + "/" + ids, 'GET', function() {
					formInit();
					_form.form('load', row);
				});
			}
		}
	}

	function approvalNo() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		var tf = false;
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.each(rows, function(i, row) {
				if (row.approveState == 1 || row.approveState == 2) {
					tf = true;
				}
			});
			if (true == tf) {
				eu.showAlertMsg("您选中的数据中存在已完成审批的数据，不能重复审批！", 'warning');
			} else if (false == tf) {
				var ids = new Array();
				$.each(rows, function(i, row) {
					ids[i] = row.id;
				});
				var RoleUrl = baseUrl + "/input";
				//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				_dialog = app.openFormDialogApprovalNo(function() {
					_form.submit();
				}, '外访审批', 460, 280, RoleUrl + "/" + ids, 'GET', function() {
					formInit();
					_form.form('load', row);
				});
			}
		}
	}
	//初始化表单
	function formInit() {
		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl + '/approvalForgo',
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
	function formInitNo() {
		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl + '/approvalForgoNo',
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

	app.openFormDialogApproval = function(saveFun, title, width, height, url,
			method, onLoadFun) {
		//弹出对话窗口
		var _dialog = $('<div/>').dialog({
			title : title,
			top : 20,
			width : width,
			height : height,
			modal : true,
			maximizable : true,
			resizable : true,
			href : url,
			method : method,
			buttons : [ {
				text : '通过',
				handler : function() {
					//如果有函数,执行,没有略过
					if (saveFun) {
						saveFun();
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					_dialog.dialog('destroy');
				}
			} ],
			onClose : function() {
				_dialog.dialog('destroy');
			},
			onLoad : function() {
				//如果有函数,执行,没有略过
				if (onLoadFun) {
					formInit();
				}
			}
		});
		return _dialog;
	};

	app.openFormDialogApprovalNo = function(saveFun, title, width, height, url,
			method, onLoadFun) {
		//弹出对话窗口
		var _dialog = $('<div/>').dialog({
			title : title,
			top : 20,
			width : width,
			height : height,
			modal : true,
			maximizable : true,
			resizable : true,
			href : url,
			method : method,
			buttons : [ {
				text : '不通过',
				handler : function() {
					//如果有函数,执行,没有略过
					if (saveFun) {
						saveFun();
					}
				}
			}, {
				text : '关闭',
				handler : function() {
					_dialog.dialog('destroy');
				}
			} ],
			onClose : function() {
				_dialog.dialog('destroy');
			},
			onLoad : function() {
				//如果有函数,执行,没有略过
				if (onLoadFun) {
					formInitNo();
				}
			}
		});
		return _dialog;
	};

	//加载组织机构
	function loadOrgs() {
		var url = ctx + '/sys/organization/getOrganizationTreeJoinAttachedOrgs';
		$('#orgId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			width : 150,
			height : 22,
			valueField : 'id',
			value : '${CURRENT_USER.orgId}',
			onChange : function(nv, ov) {
				//重新渲染用户列表
				loadCaseAssigned(nv);
			}
		});
	}

	//加载用户
	function loadCaseAssigned(orgId, id) {
		if (id == undefined) {
			id = '#caseAssignedId';
		}
		var url = ctx + '/sys/employeeInfo/orgusersByOrg?orgId=' + orgId;
		$('#caseAssignedId').combobox({
			url : url,
			multiple : false,//是否可多选
			editable : true,//是否可编辑
			width : 150,
			height : 22,
			valueField : 'value',
			textField : 'text',
			delay : 0,
			separator : ','
		});

	}

	//加载委托方
	function loadEntrust(domId, type) {
		var url = ctx + '/sys/entrust/entrustlist?selectType=' + type;
		$(domId).combobox({
			url : url,
			multiple : false,//是否可多选
			editable:true,//是否可编辑
			width : 150,
			height : 22,
			valueField : 'value',
			textField : 'text'
		});
	}
	
	
	function findBacthCode(domId){
		var url = ctx+'/collection/caseImport/Codecombobox';
		$(domId).combobox({
	        url:url,
		    multiple:true,//是否可多选
		    editable:true,//是否可编辑
		    width:150,
		    valueField:'value',
	        textField:'text'
		});
	}

	//搜索
	function search() {
		//重新加载datagrid
		app.load(_datagrid, $.serializeObject(_search_form));
	}
</script>
