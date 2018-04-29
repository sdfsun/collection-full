<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var batch_dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/casebatch";
	
	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadEntrust('#search_entrustId','all');
		 loadOrgs();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query',
			fit : true,
			method : 'GET',
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			idField : 'id',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [ [ 
			              
			/* {
				field : 'collectionId',
				title : '风控方',
				width : 100,
				formatter : function(value, row, index) {
					return app.dictName('CASE_COLLECTION', value)
				}
			},  */
			{
				field : 'state',
				title : '批次状态',
				width : 100,
				formatter : function(value, row, index) {
					return app.dictName('CASE_STATUS', value)
				}
			}, {
				field : 'batchCode',
				title : '批次号',
				width : 150,
				formatter:function(value, row, index){
    				return '<a style="color:blue;text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/case?batchCode='+row.batchCode+'")>'+value+'</a>';
        		}
			}, {
				field : 'entrustName',
				title : '委托方',
				width : 100
			}, {
				field : 'beginDate',
				title : '委案日期',
				width : 100,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd');
					}
					return value;
				}
			}, {
				field : 'totalNumber',
				title : '总户数',
				width : 100
			}, {
				field : 'totalMoney',
				title : '总金额 ',
				width : 100
			}, {
				field : 'achieve',
				title : '业绩率 ',
				width : 100
			}, {
				field : 'createEmpId',
				title : '上传人 ',
				width : 100
			}, {
				field : 'remark',
				title : '备注 ',
				width : 100
			}, {
				field : 'wdcDesc',
				title : '撤案说明 ',
				width : 100
			}, {
				field : 'typeId',
				title : '案件类型',
				width : 100,
				formatter : function(value, row, index) {
					return app.dictName('CASE_TYPE', value)
				}
			},

			{
				field : 'createTime',
				title : '创建时间',
				align : 'left',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
					}
					return value;
				}
			}, {
				field : 'modifyTime',
				title : '修改时间',
				align : 'left',
				width : 150,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
					}
					return value;
				}
			}

			] ],
			toolbar : [ {
				text : '撤回案件',
				iconCls : 'easyui-icon-edit',
				handler : function() {
					edit()
				}
			}, '-', {
				text : '恢复案件',
				iconCls : 'easyui-icon-add',
				handler : function() {
					regain()
				}
			}/* , '-', {
				text : '导出所选批次催记',
				iconCls : 'easyui-icon-remove',
				handler : function() {
					excelAll()
				}
			} */

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
				//edit(rowIndex, rowData);
			}
		}).datagrid('showTooltip');
	});
	//加载组织机构
	function loadOrgs(){
		var url = ctx+'/sys/organization/parentOrganization';
		$('#orgId').combotree({
	        url:url,
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    width:145,
	        valueField:'id',
	        value:'${CURRENT_USER.orgId}'
		});
	}
	//初始化表单
	function formInit() {
		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl+'/updateForStatus',
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
					batch_dialog.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}
	//显示弹出窗口 新增：row为空 编辑:row有值
	function showDialog(row) {
	    var inputUrl = baseUrl+"/input";
	    if (row != undefined && row.id) {
	    	
	    	if(row.state == 1){
				alert("已撤案！");
			}else if(row.state != 1){
    		
	        batch_dialog = app.openFormDialog(function() {
    			_form.submit();//提交表单
    		}, '撤回案件', 500, 460, inputUrl+"/"+row.id, 'GET', function() {
    			//初始化会话页面组件
    			formInit();
    			row.state = 1;
    			_form.form('load',row);
    		});
	    }
	}}
function loadEntrust(domId,type){
		var url = ctx+'/sys/entrust/entrustlist?selectType='+type;
		$(domId).combobox({
	        url:url,
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    width:145,
		    valueField:'value',
	        textField:'text'
		});
	} 
	//撤回案件
	function edit() {
	    //选中的所有行
	    var rows = _datagrid.datagrid('getSelections');
	    //选中的行（第一次选择的行）
	    var row = _datagrid.datagrid('getSelected');
	    if (row) {
	    	//多行校验
	        if (rows.length > 1) {
	            row = rows[rows.length - 1];
	            eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
	        }else{
		        showDialog(row);
	        }
	    } else {
	    	eu.showAlertMsg("请选择要操作的数据！",'warning');
	    }
	}

	//恢复案件
	function regain(rowIndex) {
		var row;
		if (rowIndex == undefined) {
			row = _datagrid.datagrid('getSelected');
		}
		if (row != undefined) {
			//设置标示   后台用于判断是退案还是暂停。。。。。 
			if(row.state == 0){
				alert("已是正常案件，无需恢复！");
			}else if(row.state != 0){
    			row.state = 0;
			$.messager.confirm('确认提示！',
					'您确定要恢复批次号：' + row.batchCode + '下的案件吗？', function(r) {
						if (r) {
							//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
							delete row['beginDate'];
							delete row['createTime'];
							delete row['endDate'];
							delete row['modifyTime'];
							delete row['uploadTime'];
							
							//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
							$.post(baseUrl + '/updateForStatus', row, function(
									data) {
								//渲染结果
								app.renderAjax(data, function(json) {
									app.load(_datagrid);
								});
							}, 'json');
						}
						_form.form('load',row);
					});
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}}
	
	/* //导出所有批次
	function excelAll() {
		//路径是controller的路径       由 controller 再访问页面
		var editRoleUrl = baseUrl + "/excelall";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		editRole_dialog = app.openFormDialog(function() {
		}, '选择导出字段', 500, 300, editRoleUrl, 'GET', function() {

		});
	} */

	//搜索
	function search() {
		//重新加载datagrid
		app.load(_datagrid, $.serializeObject(_search_form));
	}
</script>
