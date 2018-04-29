<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var plan_form;
	var _search_form;
	var _already_form;
	var already_form;
	var _finish_form;
	var _dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/visitrecordfinished";
	$(function() {
		_finish_form = $('#_finish_form').form({
			novalidate : true
		});

		_form = $('#_form').form();
		loadEntrust('#finish_entrustId', 'all');
		// loadOrgs();
		// loadCaseAssigned('${CURRENT_USER.orgId}');
		loadVisitUser();

		finish_datagrid = $('#finish_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryFinish',
							fit : true,
							method : 'GET',
							singleSelect : false,
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
										field : 'visitState',
										title : '外访结果',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return app.dictName('VISIT_RESULT',
													value)
										}
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
										field : 'actualVisitTime',
										title : '实际外访日期',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
								/* 	{
										field : 'countSize',
										title : '是否有效还款',
										width : 100,
										formatter : function(value, row, index) {
											if(0==value){
												return "无还款记录";
											}else{
												return "有效";
											}
										}
									}, */
									{
										field : 'isEffective',
										title : '地址有效',
										width : 60,
										sortable:true,
										formatter : function(value, row, index) {
											if (value == '0') {
												return '是';
											} else if (value == '1') {
												return '否';
											}
											return value;
										}
									},
									{
										field : 'visitReport',
										title : '外访报告',
										width : 280
									},
									{
										field : 'op',
										title : '附件',
										width : 95,
										formatter : function(value, row, index) {
											return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=viewDocu("'
													+ row.id + '")>查看</a>';
										}
									} ] ],

							toolbar : [
							<shiro:hasPermission name="visitrecordfinished:exportSelected"> 
							   {
								text : '导出所选外访',
								iconCls : 'eu-icon-daochusuoxuan',
								handler : function() {
									exportCase(1)
								}
							}, '-', 
							</shiro:hasPermission>
							<shiro:hasPermission name="visitrecordfinished:exportSelected1"> 
							{
								text : '导出所查外访',
								iconCls : 'eu-icon-daochusuocha',
								handler : function() {
									exportCase(2)
								}
							} 
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

	//导出案件
	function exportCase(type) {
		var url = ctx + '/collection/visitrecordfinished/input';
		if (type == 1) {
			app.dataGridSelect(finish_datagrid, function(rows) {
				_dialog = app.openFormDialog(function() {
					exportSelected();
				}, '导出内容', 530, 280, url, 'GET', function() {
				}, '导出', '取消');
			});
		} else {

			_dialog = app.openFormDialog(function() {
				exportSelected1();
			}, '导出内容', 530, 280, url, 'GET', function(rows) {
			}, '导出', '取消');

		}
	}

	//导出所选案件
	function exportSelected() {
		app.dataGridSelect(finish_datagrid, function(rows) {
			var isValid = $('#_form').form('validate');//表单提交 ，勾选字段页面。
			var array = [];//定义数组 ，把选中的字段的值放入。
			$('input[name=list]:checked').each(function(i) {
				array.push($(this).val());
			})
			if (array.length == 0) {
				eu.showAlertMsg("请勾选字段，至少一个！", 'warning');
			} else {
				if (isValid) {
					var ids = new Array();
					$.each(rows, function(i, row) {
						ids[i] = row.id;
					});
					app.downLoadFile({
						url : baseUrl + '/exportSelectedExcel', //请求的url
						data : {
							'ids' : ids,
							'array' : array
						},//要发送的数据  所选的批次id
						callback : function() {
							_dialog.dialog('destroy');
						}
					});
				}
			}
		});
	}

	//导出所选案件
	function exportSelected1() {
		var isValid = $('#_form').form('validate');//表单提交 ，勾选字段页面。
		var array = [];//定义数组 ，把选中的字段的值放入。
		$('input[name=list]:checked').each(function(i) {
			array.push($(this).val());
		})
		var opts = finish_datagrid.datagrid("options");
		var queryParams = opts.queryParams;
		queryParams.sort = opts.sortName;
		queryParams.order = opts.sortOrder;
		if (array.length == 0) {
			eu.showAlertMsg("请勾选字段，至少一个！", 'warning');
		} else {
			app.downLoadFile({
				url : baseUrl + '/exportQueryExcel?array=' + array, //请求的url
				data : queryParams,
				callback : function() {
					_dialog.dialog('destroy');
				}
			});
		}

	}

	//---------------------------外访排程-----------------------------------------------

	//搜索
	function search() {
		//重新加载datagrid
		app.load(finish_datagrid, $.serializeObject(_finish_form));
		//app.load(_datagridleave,$.serializeObject(_search_form));

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
			width : 150,
			valueField : 'value',
			textField : 'text',
			editable : true,
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
				text : '完成',
				iconCls : 'easyui-icon-cancel',
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
	//附件上传
	function viewDocu(id) {
		var url = ctx
				+ '/collection/upload/index?action=view&&businessType=vis&&businessId='
				+ id;
		var content = '<iframe scrolling="no" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';

		//弹出对话窗口
		var _dialog = $('<div/>').dialog({
			title : '查看附件',
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
		_finish_form.form('reset');
		$("#visitUser").val("");
	}
</script>
