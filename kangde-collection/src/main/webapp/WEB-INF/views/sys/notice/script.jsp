<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	//请求基础路径
	var baseUrl = ctx + "/sys/notice";

	$(function() {
		_form = $('#_form').form();
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/list',
			nowrap:false,
			fit : true,
			border : true,
			method : 'GET',
			singleSelect : true,
			pagination : true,//底部分页
			fitColumns : true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			showHeader:true,
			columns : [ [
			{
				field : 'id',
				title : 'id',
				hidden:true
			},
			{
				field : 'title',
				title : '标题',
				width : '10%'
			},
			{
				field : 'content',
				title : '公告内容',
				width : '75%'
			},
			{
				field : 'createTime',
				title : '创建时间',
				width : '15%',
				formatter : function(value, row, index) {
					return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
				}
			}
			
			] ],
			toolbar : [
				{
					text : '新增',
					iconCls : 'eu-icon-xinzeng',
					handler : function() {
						create()
					}
				},
				{
					text : '编辑',
					iconCls : 'eu-icon-bianji',
					handler : function() {
						edit()
					}
				},
				{
					text : '删除',
					iconCls : 'eu-icon-shanchu',
					handler : function() {
						del()
					}
				} 
			]
			
		});

	});


	function create(row) {
		var inputUrl = baseUrl + "/input";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '新增公告', 700, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
			if (row) {
				row._method = "PUT";//修改表单提交方式为PUT,修改请求
				row.birthday = $.formatDate(row.birthday, 'yyyy-MM-dd');
				_form.form('load', row);
			}
		});
	}

	

	function edit() {
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
		}, '编辑公告', 600, 345, inputUrl, 'GET', function() {
			//初始化会话页面组件
			_form = $('#_form').form({
				//表单提交地址
				url : baseUrl + '/update?id='+row.id,
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
		
		
			$("#title").textbox('setValue', row.title);
			$("#content").textbox('setValue', row.content);
			
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

</script>
