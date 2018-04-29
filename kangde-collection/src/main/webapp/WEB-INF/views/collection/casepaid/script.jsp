<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var ptp_datagrid;
	var cp_datagrid;
	var _form;
	var _search_form;
	var _dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/casepaid";
	var caseId = "${caseId}";
	$(function() {
		//PTP
		ptp_datagrid = $('#ptp_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryPTP' + "?caseId=" + caseId,
							fit : true,
							method : 'GET',
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : true,
							striped : true,//显示条纹
							singleSelect : true,
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							columns : [ [
									{
										field : 'ptpMoney',
										title : 'PTP金额',
										width : 200,
										formatter : function(value, row, index) {
											if ("" == value) {
												return "0.00";
											} else {
												return $.fmoney(value);
											}
											return value;
										}
									},
									{
										field : 'ptpTime',
										title : 'PTP日期',
										width : 200,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'employeeInfo',
										title : '操作人',
										width : 200,
										formatter : function(value, row, index) {
											if (value) {
												return value.userName;
											}
											return '';
										}
									},
									{
										field : 'op',
										title : '操作',
										width : 200,
										formatter : function(value, row, index) {
											if (row.ptpMoney > 0
													&& row.state == 0) {
												var id = row.id;
												return '<a name="toCP" class="easyui-linkbutton" href="javascript:void(0)"  onclick=toCP("'
														+ id + '")>转CP</a>';
											}

										}
									}] ],

							onLoadSuccess : function(data) {

							}
						}).datagrid('showTooltip');

		//CP
		cp_datagrid = $('#cp_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryCP' + "?caseId=" + caseId,
							fit : true,
							method : 'GET',
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : true,
							striped : true,//显示条纹
							singleSelect : true,
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							columns : [ [
									{
										field : 'state',
										title : '还款状态',
										width : 200,
										formatter : function(value, row, index) {
											return app.dictName('CP_TYPE',
													value)
										}
									},
									{
										field : 'cpMoney',
										title : 'CP金额',
										width : 200,
										formatter : function(value, row, index) {
											if ("" == value) {
												return "0.00";
											} else {
												return $.fmoney(value);
											}
											return value;
										}
									},
									{
										field : 'cpTime',
										title : 'CP日期',
										width : 200,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},

									{
										field : 'paidNum',
										title : '确认还款金额',
										width : 300,
										formatter : function(value, row, index) {
											if ("" == value) {
												return "0.00";
											} else {
												return $.fmoney(value);
											}
											return value;
										}
									},
									{
										field : 'paidTime',
										title : '还款日期',
										width : 200,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'surTime',
										title : '确认时间',
										width : 200,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd HH:mm:ss');
											}
											return value;
										}
									},

									{
										field : 'pingzhen',
										title : '凭证',
										width : 200,
										formatter : function(value, row, index) {
											return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=attachmentManager("'
													+ row.id + '")>查看/上传</a>';
										}
									},
									{
										field : 'cancelReason',
										title : '作废原因',
										width : 200
										
									},
									{
										field : 'surRemark',
										title : '备注',
										width : 200
										
									}

							] ],

							onLoadSuccess : function(data) {

							}
						}).datagrid('showTooltip');

	});

	//显示弹出窗口 新增：row为空 编辑:row有值
	function showDialog(row) {
		var inputUrl = baseUrl + "/input?caseId=" + caseId;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '新增还款', 500, 230, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
			if (row) {
				row._method = "PUT";//修改表单提交方式为PUT,修改请求
				row.birthday = $.formatDate(row.birthday, 'yyyy-MM-dd');
				_form.form('load', row);
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
					app.reload(cp_datagrid);//重新加载列表数据
				});
			}
		});
	}

	function toCP(id) {

		$.ajax({
			type : "POST",
			url : baseUrl + '/toCP',
			data : {
				id : id
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//渲染结果
				app.renderAjax(data, function(json) {
					app.reload(ptp_datagrid);
					app.reload(cp_datagrid);
				});
			}
		});
	}

	function upload(id) {
		var href = ctx
				+ '/collection/upload/index?action=edit&&businessType=cp&&businessId='
				+ id;
		window.open(href);
	}

	//附件管理
	function attachmentManager(id) {
		var href = ctx
				+ '/collection/upload/index?action=edit&&businessType=cp&&businessId='
				+ id;
		var content = '<iframe scrolling="no" frameborder="0"  src="' + href
				+ '" style="width:100%;height:100%;"></iframe>';
		window.open(href);
	}
</script>
