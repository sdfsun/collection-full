<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _search_form;
	var _dialog;
	//请求基础路径
	var baseUrl = ctx + "/sys/visitTemplate";
	$(function() {
		_search_form = $('#_search_form').form();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query',
			fit : true,
			method : 'GET',
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			striped : true,//显示条纹
			border : false,
			remoteSort : false,//是否通过远程服务器对数据排序
			idField : 'id',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, ] ],
			columns : [ [{
				field : 'state',
				title : '启用/禁用',
				width : 100,
				formatter : function(value, row, index) {
					if (value == 0) {
						return "停用";
					} else if (value == 1) {
						return "启用";
					}
				}
			}, {
				field : 'name',
				title : '模板名称',
				width : 200
			}, {
				field : 'type',
				title : '模板类型',
				width : 120,
				formatter : function(value, row, index) {
					return app.dictName('TEMPLATE_TYPE', value);
				}
			} ] ],
			toolbar : [ 
			<shiro:hasPermission name="visitTemplate:addVisit">
			            {
				text : '新增',
				iconCls : 'eu-icon-xinzeng',
				handler : function() {
					addVist()
				}
			}, '-', 
			</shiro:hasPermission>
			<shiro:hasPermission name="visitTemplate:editVisit">
			{
				text : '编辑',
				iconCls : 'eu-icon-bianji',
				handler : function() {
					editVisit()
				}
			}, '-', 
			</shiro:hasPermission>
			<shiro:hasPermission name="visitTemplate:delVist">
			{
				text : '删除',
				iconCls : 'eu-icon-shanchu',
				handler : function() {
					delVist()
				}
			}, '-', 
			</shiro:hasPermission>
			<shiro:hasPermission name="visitTemplate:state">
			{
				text : '启用',
				iconCls : 'eu-icon-qiyong',
				handler : function() {
					state()
				}
			}, '-', 
			</shiro:hasPermission>
			<shiro:hasPermission name="visitTemplate:stop">
			{
				text : '停用',
				iconCls : 'eu-icon-tingyong',
				handler : function() {
					stop()
				}
			} 
			</shiro:hasPermission>
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
			},
			onDblClickRow : function(rowIndex, rowData) {
				show(rowIndex, rowData);
			}
		}).datagrid('showTooltip');

	});

	//新增外访模板 
	function addVist() {
		showVisitDialog();
	}
	//修改外访模板
	function editVisit(rowIndex, rowData) {
		//响应双击事件
		if (rowIndex != undefined) {
			showVisitDialog(rowData);
			return;
		}
		app.dataGridSelectOne(_datagrid, function(row) {
			showVisitDialog(row);
		});
	}
	//删除外访模板
	function delVist() {
		app.dataGridSelect(_datagrid, function(rows) {
			$.messager.confirm('提示信息', '你确定要删除选中的所有数据么?', function(r) {
				if (r) {
					var ids = '';
					$.each(rows, function(i, row) {
						ids += row.id + ',';
					});
					ids = ids.substring(0, ids.length - 1);
					$.ajax({
						type : "POST",
						url : baseUrl,
						data : {
							'_method' : 'DELETE',
							'ids' : ids
						},
						dataType : "json",
						success : function(data) {
							app.renderAjax(data, function() {
								app.reload(_datagrid);
							});
						}
					});
				}
			});
		});
	}
	//显示弹出窗口 新增：row为空 编辑:row有值
	function showVisitDialog(row) {
		var inputUrl = baseUrl + "/input";
		if (row != undefined && row.id) {
			inputUrl = inputUrl + "/" + row.id;
		}
		_dialogVisit = app.openMaxFormDialog(function() {
			_visit_form.submit();//提交表单
		}, '外访模板', inputUrl, 'GET', function() {
			loadType(row);
			visitFormInit();
			if (content_kindeditor) {
				content_kindeditor.sync();
			}
		});
	}

	//初始化init外访模板表单
	function visitFormInit() {
		_visit_form = $('#_visit_form').form({
			url : baseUrl,
			onSubmit : function(param) {
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});
				if (content_kindeditor) {
					content_kindeditor.sync();
				}
				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
				}
				return isValid;
			},
			success : function(data) {
				$.messager.progress('close');
				//渲染结果
				app.renderAjax(data, function(json) {
					_dialogVisit.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}

	//案件启用
	function state() {
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.messager.confirm('确认提示！', '您确定要启用所选模板吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					var ids = new Array();
					$.each(rows, function(i, row) {
						ids[i] = row.id;
					});
					$.post(baseUrl + '/updateForStart', {
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

	//案件停用
	function stop() {
		var rows = _datagrid.datagrid('getSelections');
		var row = rows[0];
		if (rows.length == 0) {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		} else {
			$.messager.confirm('确认提示！', '您确定要停用所选模板吗？', function(r) {
				if (r) {
					delete row['createTime'];
					delete row['modifyTime'];
					var ids = new Array();
					$.each(rows, function(i, row) {
						ids[i] = row.id;
					});
					$.post(baseUrl + '/updateForStop', {
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
	
	//加载机构类型
	function loadType(row){
		var type = 0;
		if(row){
			type = row.type;
		}
		if(type==0){
			$('#show_illustrate').show();
			$('#show_illustrate2').hide();
		}else{
			$('#show_illustrate').hide();
			$('#show_illustrate2').show();
		}
		var url = ctx+'/sys/dictionary/combobox?path=TEMPLATE_CONF/TEMPLATE_TYPE';
		var dicts;
		$.ajax({
	        url: url,
	        dataType: "json",
	        async: false,
	        success: function(data){
	        	dicts = data;
	        }
	    });
		$('#type').combobox({
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    required:true,
		    valueField:'value',
	        textField:'text',
	        value:type,
	        data:dicts,
	        onChange:function(newValue, oldValue){
	        	if(newValue==0){
	    			$('#show_illustrate').show();
	    			$('#show_illustrate2').hide();
	    		}else{
	    			$('#show_illustrate').hide();
	    			$('#show_illustrate2').show();
	    		}
	        }
		});
	}
</script>
