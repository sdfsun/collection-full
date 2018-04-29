<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var already_datagrid;
	var _form;
	var _form1;
	var _search_form;
	var _dialog;
	var already_dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/visitrecordappointed";
	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form({
			novalidate : true
		});
		loadEntrust('#already_entrustId', 'all');
		loadVisitUser();
		already_datagrid = $('#already_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryAppointed',
							fit : true,
							method : 'GET',
							/* singleSelect : true, */
							queryParams : $.serializeObject(_search_form),
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : false,//自适应列宽
							striped : true,//显示条纹
							remoteSort : true,//是否通过远程服务器对数据排序
							idField : 'id',
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
										field : 'visitUser',
										title : '外访员',
										width : 95
									},
									{
										field : 'visitTime',
										title : '预计外访日期',
										width : 95,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									}, ] ],
							toolbar : [ 
							    {
								text : '导出所选外访单',
								iconCls : 'eu-icon-daochusuoxuan',
								handler : function() {
									exportVisitRecord(1)//外访
								}
							},"-",
							{
								text : '导出所查外访单',
								iconCls : 'eu-icon-daochusuocha',
								handler : function() {
									exportVisitRecord(2)//外访
								}
							}, 
							{
								text : '完成外访',
								iconCls : 'eu-icon-tongyi',
								handler : function() {
									finishToVisit();
								}
							}, 
							{
								text : '返回排程',
								iconCls : 'eu-icon-fanhuipaicheng',
								handler : function() {
									backToAppoint();
								}
							},
							{
								text : '撤销外访',
								iconCls : 'eu-icon-chexiaowaifang',
								handler : function() {
									cancelVisit();
								}
							}
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
	//初始化表单
	function formInitCancel() {
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
					app.reload(already_datagrid);//重新加载列表数据
				});
			}
		});
	}

	//---------------------------------撤销外访-----------------------------------------------------------------
//撤销外访
	function cancelVisit() {
		 var rows = already_datagrid.datagrid('getSelections');
		 var row = rows[0];
		 if(rows.length == 0){
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}else{
			if (row) {
				 var ids = new Array();
	              $.each(rows, function (i, row) {
	                	ids[i] = row.id;
	                });
				var RoleUrl = baseUrl+"/inputpoint";
				_dialog = app.openFormDialog(function(){
	       	    	_form1.submit();
	       	   	}, '撤销外访',460, 280,RoleUrl+"/"+ids, 'GET',function(){
	       	   		 inputpoint();
	       	   		_form.form('load',row);
	       	   	},"撤销","关闭");	
			}
		}
	}
	//初始化表单
	 function inputpoint() {
	 	_form1 = $('#_form').form({
	 		//表单提交地址
	 		url : baseUrl+"/cancelVisit",
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
					app.reload(already_datagrid);//重新加载列表数据
	             });
	         }
	 	});
	 }

	//----------------------返回排程---------------------

	function backToAppoint() {
		
		//选中的所有行
		var rows = already_datagrid.datagrid('getSelections');
		//选中的行（第一次选择的行）
		var row = already_datagrid.datagrid('getSelected');
		
	 if(rows.length == 0){
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}else{
			if (row != undefined) {
				$.messager.confirm('确认提示！', '确定要将本案件返回待排程，重新排程？', function(r) {
					if (r) {
						
						  var ids = new Array();
			              $.each(rows, function (i, row) {
			                	ids[i] = row.id;
			                });
						
						$.post(baseUrl + '/backToAppoint?ids='+ids+'',{} , function(data) {
							//渲染结果
							app.renderAjax(data, function(json) {
								app.reload(already_datagrid);// 重新加载列表数据
							});
						}, 'json');
					}
				});
				
			}
		}
	}
	//----------------------完成外访---------------------
	//初始化表单
	function formInitAlready() {
		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl + '/finishVisit',
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
					app.reload(already_datagrid);//重新加载列表数据
				});
			}
		});
	}
	//显示弹出窗口 新增：row为空 编辑:row有值
	function showDialogFinish(row) {
		var finishUrl = baseUrl + "/finish?id=" + row.id + "&caseId="
				+ row.caseId;
		if (row != undefined && row.id) {

			_dialog = app.openFormDialog(function() {
				_form.submit();//提交表单
			}, '完成外访', 600, 360, finishUrl, 'GET', function() {
				//初始化会话页面组件
				formInitAlready();
			});

		}
	}
	function finishToVisit(rows) {
		//选中的所有行
		var rows = already_datagrid.datagrid('getSelections');
		//选中的行（第一次选择的行）
		var row = already_datagrid.datagrid('getSelected');
		if (row) {
			//多行校验
			if (rows.length > 1) {
				row = rows[rows.length - 1];
				eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			} else {
				showDialogFinish(row);
			}
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}

	//导出外访
	function exportVisitRecord(type) {
		var url = ctx + '/sys/visitTemplate/exportVisitExcel?name=外访模板&type=0';
		if (type == 1) {
			app.dataGridSelect(already_datagrid, function(rows) {
				already_dialog = app.openFormDialog(function() {
					exportSelected();
				}, '导出所选外访单', 600, 160, url, 'GET', function() {
				}, '导出', '取消');
			});
		} else {
			app.datagridHasRow(already_datagrid, function() {
				already_dialog = app.openFormDialog(function() {
					exportQuery();
				}, '导出所查外访单', 600, 160, url, 'GET', function() {
				}, '导出', '取消');
			});
		}
	}
	//导出所选案件
	function exportSelected() {
		app.dataGridSelect(already_datagrid, function(rows) {
			var isValid = $('#template_form').form('validate');
			if (isValid) {
				var visitRecordIds = new Array();
				$.each(rows, function(i, row) {
					visitRecordIds[i] = row.id;
				});
				var templateId = $('#templateId').combobox('getValue');
				app.downLoadFile({
					url : baseUrl + '/exportSelectedExcel', //请求的url
					data : {
						'ids' : visitRecordIds,
						'templateId' : templateId,
						'type' : 1
					},//要发送的数据
					callback : function() {
						already_dialog.dialog('destroy');
					}
				});
			}
		});
	};

	//导出所查件
	function exportQuery() {
		var isValid = $('#template_form').form('validate');
		if (isValid) {
			var queryParams = already_datagrid.datagrid("options").queryParams;
			var templateId = $('#templateId').combobox('getValue');
			queryParams.templateId = templateId;
			app.downLoadFile({
				url : baseUrl + '/exportQueryExcel',
				data : queryParams,
				callback : function() {
					already_dialog.dialog('destroy');
				}
			});
		}
	}
	//搜索
	function search() {
		//重新加载datagrid
		app.load(already_datagrid, $.serializeObject(_search_form));
	}

	//-----------以下方法为通用的加载方法-------------------------------------------
	//加载委托方
	function loadEntrust(domId, type) {
		var url = ctx + '/sys/entrust/entrustlist?selectType=' + type;
		$(domId).combobox({
			url : url,
			multiple : false,//是否可多选
			editable:true,//是否可编辑
			width : 150,
			valueField : 'value',
			textField : 'text'
		});
	}
	//加载组织机构
	function loadOrgs() {
		var url = ctx + '/sys/organization/getOrganizationTreeJoinAttachedOrgs';
		$('#orgId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			width : 150,
			valueField : 'id',
			value : '${CURRENT_USER.orgId}',
			onChange : function(nv, ov) {
				//重新渲染用户列表

				loadCaseAssigned(nv);
			}
		});
	}

	//加载风控员
	function loadCaseAssigned(orgId) {
		var url = ctx + '/sys/employeeInfo/orgusersByOrg?orgId=' + orgId
				+ '&selectType=select';
		$('#caseAssignedId').combobox({
			url : url,
			width : 150,
			valueField : 'value',
			textField : 'text',
			editable : false
		});
	}

	//加载外访员
	function loadVisitUser() {
		var url = ctx + '/collection/visitrecordappointed/getCurrentOrgVisitor';
		$("#visitUserId").combobox({
			url : url,
			multiple : true,
			width : 150,
			valueField : 'value',
			textField : 'text',
			onChange : function(newValue, oldValue) {
				if (newValue == '') {
					$("#visitUser").val('');
				} else {
					$("#visitUser").val($(this).combobox("getText"));
				}
			}
		});

	}

	//附件上传
	function viewDoc() {

		var id = $("#id").val();
		var url = ctx
				+ '/collection/upload/index?action=edit&&businessType=vis&&businessId='
				+ id;
		window.open(url);
		

	}
	//附件上传
	function viewDocu(id) {
		var url = ctx
				+ '/collection/upload/index?action=view&&businessType=vis&&businessId='
				+ id;
		var content = '<iframe scrolling="no" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';

		//弹出对话窗口
		var _dialog = $('<div/>').dialog({
			title : '',
			top : 20,
			width : 800,
			height : 500,
			modal : true,
			maximizable : true,
			resizable : true,
			buttons : [ {
				text : '关闭',
				handler : function() {
					_dialog.dialog('destroy');
				}
			} ],
			content : content,
			onClose : function() {
				_dialog.dialog('destroy');
			}
		});

	}

	function reset() {
		_search_form.form('reset');
		$("#visitUser").val("");
	}
</script>
