<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@ tagliburi ="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _dialog;
	var caseId = "${caseId}";
	var _form;
	//请求基础路径
	var baseUrl = ctx + "/collection/caseapply";

	$(function() {
		_form = $('#_form').form();
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/querycasedetailxiecui' + "?caseId="
									+ caseId,
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
									{
										
									<shiro:hasPermission name="detail:xiecui:save">
										text : '协催申请',
										iconCls : 'eu-icon-xiecuishenqing',
										handler : function() {
											showDialog();
										}
									</shiro:hasPermission>
									} ],
							columns : [ [
									{
										field : 'id',
										title : 'id',
										width : 100,
										hidden : true
									},
									{
										field : 'caseId',
										title : 'caseId',
										width : 100,
										hidden : true

									},

									{
										field : 'applyContent',
										title : '申请内容',
										width : 100
									},
									{
										field : 'employeeInfo',
										title : '申请人',
										width : 100,
										formatter : function(value, row, index) {
											if (value) {
												return value.userName;
											}
											return '';
										}
									},
									{
										field : 'appTime',
										title : '申请时间',
										width : 100,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd HH:mm:ss');
											}
											return value;
										}
									},

									{
										field : 'applyType',
										title : '类型',
										width : 100,
										formatter : function(value, row, index) {

											if (value == '11') {
												return '客服咨询';
											}
											if (value == '7') {
												return '公安协助';
											}
											if (value == '5') {
												return '法院协助';
											}
											if (value == '10') {
												return '主管协催';
											}
											return value;
										}
									},

									{
										field : 'hurryContent',
										title : '回复内容',
										width : 100
									},

									{
										field : 'surUserName',
										title : '回复人',
										width : 100
									},

									{
										field : 'surTime',
										title : '回复日期',
										width : 100,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value, 'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'op',
										title : '操作',
										width : 100,
										formatter : function(value, row, index) {
											//申请状态  
											if (row.state == -2) { //-2待审批    查看申请  删除
												var r = "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=showDetail('"
														+ row.id
														+ "')>查看申请 </a><a href='javascript:void(0)' class='easyui-linkbutton' onclick=del('"
														+ row.id + "')>删除</a>"
												return r;
											} else if (row.state == -1) {//-1审批失败 查看申请  删除

												var r = "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=showDetail('"
														+ row.id
														+ "')>查看申请 </a><a href='javascript:void(0)' class='easyui-linkbutton' onclick=del('"
														+ row.id + "')>删除</a>"
												return r;

											} else if (row.state == 0) {//0审批通过
												var r = "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=showDetail('"
														+ row.id
														+ "')>查看申请 </a>"
												return r;
											} else if (row.state == 1) {// 1已完成
												var r = "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=showDetail('"
														+ row.id
														+ "','"
														+ row.applyType
														+ "','"
														+ row.caseId
														+ "')>查看申请 </a>"
												return r;
											}
										}
									} ] ],
							onLoadSuccess : function() {
							},
							onRowContextMenu : function(e, rowIndex, rowData) {
							},
							onDblClickRow : function(rowIndex, rowData) {
								app.dataGridSelectOne(_datagrid, function(row) {
									showDetail(row.id);
								});
							}
						}).datagrid('showTooltip');

	});
	function showDialog() {
		var inputUrl = baseUrl + "/casedetailapplyxiecui?caseId=${caseId}";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '协催申请', 700, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
		});
	}
	function showDetail(id) {
		var inputUrl = baseUrl + "/casedetailviewxiecui?id=" + id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openViewDialog('查看申请', 700, 300, inputUrl, 'GET',
				function() {

				});
	}

	function del(id) {
		$.messager.confirm('确认提示！', '您确定要删除？', function(r) {
			if (r) {
				$.post(baseUrl + '/deleteById?id=' + id, {}, function(data) {
					//渲染结果
					app.renderAjax(data, function(json) {
						app.load(_datagrid);
					});
				}, 'json');

			}
		});
	}
	function showMaterial(id, applyType, caseId) {
		var inputUrl;
		if (applyType == '6') { //社保
			inputUrl = baseUrl + '/socialSecurity?caseApplyId=' + id
					+ "&caseId=" + caseId;
		} else if (applyType == '12') {//电信
			inputUrl = baseUrl + '/telecom?caseApplyId=' + id + "&caseId="
					+ caseId;
		}
		if (applyType == '4') { //户籍
			inputUrl = baseUrl + '/censusRegister?caseApplyId=' + id
					+ "&caseId=" + caseId;
		}
		//title, width, height, url,method,onLoadFun
		_dialog = app.openViewDialog('查看资料', 1200, 360, inputUrl, 'GET',
				function() {

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

	function savePhone(name, cardNo, relation, phoneType, phone) {
		if (phone == '') {
			$.messager.alert('提示', '电话号码为空');
			return;
		}
		$.ajax({
			type : "POST",
			url : ctx + "/collection/linkman/saveCaseApplyInfo",
			data : {
				caseId : "${caseId}",
				phone : phone,
				cardNo : cardNo,
				relation : relation,
				phoneType : phoneType,
				name : name
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//渲染结果
				app.renderAjax(data, function(json) {

				});
			}
		});
	}

	function saveAddress(name, relation, address, adrCat) {
		if (name == '' || relation == '') {
			$.messager.alert('提示', '姓名和关系不能为空');
			return;
		}
		$.ajax({
			type : "POST",
			url : ctx + "/collection/address/saveCaseApplyInfo",
			data : {
				caseId : "${caseId}",
				name : name,
				relation : relation,
				address : address,
				adrCat : adrCat
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//渲染结果
				app.renderAjax(data, function(json) {

				});
			}
		});
	}
</script>
