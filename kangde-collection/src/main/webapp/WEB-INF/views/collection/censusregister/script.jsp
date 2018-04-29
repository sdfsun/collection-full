<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _dialog;
	var caseId = "${caseId}";
	var _form;
	//请求基础路径
	var baseUrl = ctx + "/collection/caseapply";

	$(function() {
		_form = $('#_form').form();
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query' + "?caseId=" + caseId,
			fit : true,
			method : 'GET',
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : true,//自适应列宽
			singleSelect:true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			idField : 'id',
			toolbar : [ {
				text : '查资申请',
				iconCls : 'easyui-icon-add',
				handler : function() {
					showDialog();
				}
			}, {
				text : '查看资料',
				iconCls : 'easyui-icon-add',
				handler : function() {
					app.dataGridSelectOne(_datagrid, function(row) {
						showDetail(row.id);
					});
				}
			} ],
			columns : [ [ {
				field : 'id',
				title : 'id',
				width : 100,
				hidden : true
			}, {
				field : 'state',
				title : '状态',
				width : 100,
				formatter : function(value, row, index) {
					if (value == '0') {
						return '待核查';
					}
					if (value == '1') {
						return '已完成';
					}
					if (value == '-1') {
						return '被撤销';
					}
					if (value == '-2') {
						return '查资申请';
					}
					return value;
				}
			}, {
				field : 'applyContent',
				title : '申请内容',
				width : 100
			}, {
				field : 'appTime',
				title : '申请时间',
				width : 100,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
					}
					return value;
				}
			}, {
				field : 'employeeInfo',
				title : '申请人',
				width : 100,
				formatter: function(value,row,index){
					if(value){
						return value.userName;
					}
           			return '';
       			}
			}, {
				field : 'applyType',
				title : '类型',
				width : 100,
				formatter : function(value, row, index) {

					if (value == '1') {
						return '信函';
					}
					if (value == '2') {
						return '外访';
					}
					if (value == '3') {
						return '投诉预警';
					}
					if (value == '4') {
						return '户籍查询';
					}
				
					if (value == '6') {
						return '社保查询';
					}
					
					if (value == '8') {
						return '争议咨询';
					}
					if (value == '9') {
						return '移动查询';
					}
				
					if (value == '11') {
						return '客服咨询';
					}
					if (value == '12') {
						return '电信查询';
					}
					if (value == '13') {
						return '短信申请';
					}
					if (value == '14') {
						return '退件查询';
					}
					return value;
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
		var inputUrl = baseUrl + "/input?caseId=${caseId}";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '查资申请', 700, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
		});
	}
	function showDetail(id) {
		var inputUrl = baseUrl + "/detail?id="+id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			
		}, '查看资料', 700, 300, inputUrl, 'GET', function() {
			
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
</script>
