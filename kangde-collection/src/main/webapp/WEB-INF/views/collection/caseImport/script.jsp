<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var upload_dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/caseImport";

	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		upload_form = $('#upload_form').form();
		loadEntrust('#search_entrustId');
		findBacthCode('#batchCode1');
		loadOrgs();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : ctx + '/collection/casebatch/query',
			fit : true,
			method : 'GET',
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			striped : true,//显示条纹
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			toolbar : [
			<shiro:hasPermission name="caseImport:save">
			{
				text : '新增',
				iconCls : 'eu-icon-xinzeng',
				handler : function() {
					showDialog()
				}
			}, '-', 
			</shiro:hasPermission>
            <shiro:hasPermission name="caseImport:update">
			{
				text : '编辑',
				iconCls : 'eu-icon-bianji',
				handler : function() {
					edit()
				}
			}, '-', 
			</shiro:hasPermission>
            <shiro:hasPermission name="caseImport:delete">
			{
				text : '删除',
				iconCls : 'eu-icon-shanchu',
				handler : function() {
					del()
				}
			},
			'-', 
			</shiro:hasPermission>
            <shiro:hasPermission name="caseImport:export">
			{
				text : '导出批次',
				iconCls : 'eu-icon-daochusuoxuan',
				handler : function() {
					exportCase()
				}
			}, '-', 
			</shiro:hasPermission>
            <shiro:hasPermission name="caseImport:upload">
			{
				text : '更新批次',
				iconCls : 'eu-icon-gengxin1',
				handler : function() {
					upload()
				}
			} 
			</shiro:hasPermission>
			],
			   onLoadSuccess: function () {
		        	$(this).datagrid('showTooltip').datagrid('columnMoving');
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

	//初始化表单
	function formInit(row) {
		if (row) {
			_form = $('#_form').form({
				//表单提交地址
				url : ctx + '/collection/casebatch',
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
		}else{
			_form = $('#_form').form({
				//表单提交地址
				url : ctx + '/collection/caseImport/excelImport',
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
		
		
		
	}
	//添加批次 新增：row为空 编辑:row有值
	function showDialog(row) {
		var inputUrl = baseUrl + "/input";
		if (row != undefined && row.id) {
			inputUrl = inputUrl + "/" + row.id;
		}
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			if(!checkDate()){
				return;
			}
			if(!isExcel()){
				return;
			}
			_form.submit();//提交表单
		}, '添加批次', 610, 460, inputUrl, 'GET', function() {
			loadEntrust('#entrustId');
			//loadCaseType(true);
			
			//初始化会话页面组件
			if (row) {
				row.beginDate = $.formatDate(row.beginDate, 'yyyy-MM-dd');
				row.endDate = $.formatDate(row.endDate, 'yyyy-MM-dd');
				
				formInit(row);
				_form.form('load', row);
			}else{
				formInit();
			}
		});
	}
	//添加批次 新增：row为空 编辑:row有值
	function showDialog2(row) {
		var inputUrl = baseUrl + "/input";
		if (row != undefined && row.id) {
			inputUrl = inputUrl + "/" + row.id;
		}
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			if(!checkDate()){
				return;
			}
			_form.submit();//提交表单
		}, '编辑批次', 620, 300, inputUrl, 'GET', function() {
			loadEntrust('#entrustId');
			//loadCaseType(false,row.typeId);
			//初始化会话页面组件
			if (row) {
				row.beginDate = $.formatDate(row.beginDate, 'yyyy-MM-dd');
				row.endDate = $.formatDate(row.endDate, 'yyyy-MM-dd');
				
				formInit(row);
				_form.form('load', row);
			}else{
				formInit();
			}
		});
	}
	/* //批次 新增：row为空 编辑:row有值
	function showDialog2(row) {
		var inputUrl = baseUrl + "/input";
		if (row != undefined && row.id) {
			inputUrl = inputUrl + "/" + row.id;
		}

		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openViewDialog('批次详情', 500, 460, inputUrl, 'GET',
				function() {
					//初始化会话页面组件
					formInit();
					if (row) {
						row._method = "PUT";//修改表单提交方式为PUT,修改请求
						row.beginDate = $.formatDate(row.beginDate,
								'yyyy-MM-dd');
						row.endDate = $.formatDate(row.endDate, 'yyyy-MM-dd');
						_form.form('load', row);
						_form.form('disable', true);
						$('#entrustId').combobox('disable');
						$('#type').combobox('disable');
						$('.easyui-datebox').datebox('disable');

					}
				});
	}
	//批次详情
	function batch(rowIndex, rowData) {
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
	} */
	//编辑批次
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
				showDialog2(row);
			}
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}
	//删除
	function del(rowIndex){
		 //选中的所有行
	    var rows = _datagrid.datagrid('getSelections');
	     var row = rows[0];
	   //  alert(row.id);
	       // var id=row.id;
	    //多行校验
	    if (rows.length > 1) {
	        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
	    } else if(rows.length < 1){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    }else if (row != null) { 
	    			  $.messager.confirm('确认提示！', '您确定要删除' + row.batchCode
	    						+ '这一批次吗(如果批次下有案件，案件将会一并删除)？', function(r) {
	    							if (r) {
	    								//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
	    								delete row['beginDate'];
	    								delete row['createTime'];
	    								delete row['endDate'];
	    								delete row['modifyTime'];
	    								//设置标示   后台用于判断是退案还是暂停。。。。。 
	    								//row.status = '-1';
	    								row._method = "PUT";
	    								row.uploadTime = $.formatDate(row.uploadTime, 'yyyy-MM-dd');
	    								//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
	    								$.post( ctx + '/collection/casebatch/softDelete', row, function(
	    										data) {
	    									//渲染结果
	    								
	    									app.renderAjax(data, function(json) {
	    										app.load(_datagrid);
	    									});
	    								}, 'json');
	    							}
	    							_form.form('load',row);
	    						}); 
	    		    }
	    	}
	
	/* function del(rowIndex) {
		var row;
		if (rowIndex == undefined) {
			row = _datagrid.datagrid('getSelected');
		}
		if (row != undefined) {
			$.messager.confirm('确认提示！', '您确定要删除' + row.batchCode
					+ '这一批次吗(如果批次下有案件，案件将会一并删除)？', function(r) {
						if (r) {
							//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
							delete row['beginDate'];
							delete row['createTime'];
							delete row['endDate'];
							delete row['modifyTime'];
							//设置标示   后台用于判断是退案还是暂停。。。。。 
							//row.status = '-1';
							row._method = "PUT";
							row.uploadTime = $.formatDate(row.uploadTime, 'yyyy-MM-dd');
							//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
							$.post( ctx + '/collection/casebatch/softDelete', row, function(
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
	} */
	/* function del(rowIndex) {
		var row;
		if (rowIndex == undefined) {
			row = _datagrid.datagrid('getSelected');
		}
		if (row != undefined) {
			$.messager.confirm('确认提示！', '您确定要删除' + row.batchCode
					+ '这一批次吗(如果批次下有案件，案件将会一并删除)？', function(r) {
				if (r) {
					$.post(ctx + '/collection/casebatch/' + row.id, {
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
	} */
    //更新案件
	function upload() {
		//选中的所有行
		var rows = _datagrid.datagrid('getSelections');
		//选中的行（第一次选择的行）
		var row = _datagrid.datagrid('getSelected');
		if (row) {
			//多行校验
			if (rows.length > 1) {
				eu.showAlertMsg("只能选中一条数据进行操作！", 'warning');
			} else {
				//alert(row.type);
				var upUrl = baseUrl + '/upload?caseBatchId=' + row.id+'&type='+row.type;
				//alert(row.type+'111');
				//弹出对话窗口
				upload_dialog = $('<div/>').dialog({
				
					title : '更新批次',
					top : 20,
					width : 527,
					height : 300,
					modal : true,
					maximizable : true,
					resizable : true,
					href : upUrl,
					method : 'GET',
					buttons : [ {
						text : '更新',
						
						handler : function() {
							$('#upload_form').submit();
						}
					}, {
						text : '关闭',
						
						handler : function() {
							upload_dialog.dialog('destroy');
						}
					} ],
					onClose : function() {
						upload_dialog.dialog('destroy');
					},
					onLoad : function() {
						$('#upload_form').form({
							//表单提交地址
							url : baseUrl + '/excelUpdate',
							//表单提交数据之前回调函数
							onSubmit : function() {
								 if(!isExcel()){
									return false;
								} 
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
									upload_dialog.dialog('destroy');//销毁对话框
									app.reload(_datagrid);
								});
							}
						});
					}
				});
			}
		} else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	} 

function loadEntrust(domId){
	var url = ctx+'/sys/entrust/entrustlist';
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    valueField:'value',
        textField:'text'
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
//导出案件-----------------------------------------------------------------

function exportCase(){
	var rows = _datagrid.datagrid('getSelections');
	var row = rows[0];
	var url;
	var bs;

	  //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		
    		if(row.templateType==1){//p2p
    			url= ctx+'/collection/caseImport/excelP2p';
    			bs=1;
    		}else if(row.templateType==2){//银行
    			url= ctx+'/collection/caseImport/excelBank';
    			bs=2;
    		}else if(row.templateType==3){//车贷
    			url= ctx+'/collection/caseImport/excelCar';
    			bs=3;
    		}
    		
		app.dataGridSelect(_datagrid, function(rows){
			_dialog = app.openFormDialog(function(){
				exportSelected(bs);
			}, '导出内容', 750, 450, url, 'GET', function(){}, '导出', '取消');
		});
}
}

//导出所选案件
function exportSelected(bs){
	app.dataGridSelect(_datagrid, function(rows){
        var isValid = $('#excel_form').form('validate');//表单提交 ，勾选字段页面。
        var array = [];//定义数组 ，把选中的字段的值放入。
		$('input[name=field]:checked').each(function(i) {
			//alert($(this).val());
			array.push($(this).val());
		})
        if(isValid){
        	var batchIds = new Array();
            $.each(rows, function (i, row) {
            	batchIds[i] = row.id;
            });
	        app.downLoadFile({
	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        	data:{'batchIds':batchIds,'array':array,"bs":bs},//要发送的数据  所选的批次id
	        	callback:function(){
	        		_dialog.dialog('destroy');
	        	}
	        });
        }
	});
};

//初始化表单
function formInit2() {
	_form = $('#_form').form({
		//表单提交地址
		url : ctx + '/collection/casebatch/updateForStatus',
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
function cheHui(row) {
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else if(row.state==1){
  		  eu.showAlertMsg("已撤案，不能重复撤案!",'warning');
    	}else{
    var inputUrl = ctx + '/collection/casebatch/input';
    if (row != undefined && row.id) {
    	
//     	if(row.state != 1){
		
        batch_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '撤回案件', 500, 460, inputUrl+"/"+row.id, 'GET', function() {
			//初始化会话页面组件
			formInit2();
			_form.form('load',row);
		});
//     }
	}
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
			eu.showAlertMsg("已是正常案件，不能重复恢复!",'warning');
		}else if(row.state != 0){
			
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
						$.post(ctx + '/collection/casebatch/updateForState', row, function(
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

//------------------------------------------------------------------------------	
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid, $.serializeObject(_search_form));
}

//easyui 中filebox中的accept属性不生效， 提交时检查文件格式
function isExcel(){
	  var fileName= $('#excelFile').filebox('getValue');
	   if(fileName==""){     
             $.messager.alert('提示','请选择上传文件！','info');
             return false;
          }
	   //对文件格式进行校验  
         var d1=/\.[^\.]+$/.exec(fileName);   
         if(!(d1==".xls" || d1==".xlsx")){  
      	   $.messager.alert('提示','文件格式错误, 请选择Excel文件！','info');
      	   return false;
        	} 
         return true;
         
}


function checkDate(){
	  var beginDate= $('#begin').datebox('getValue');
	  var endDate= $('#end').datebox('getValue');
	  if(beginDate!='' &&endDate!=''){
		  if(endDate<=beginDate){
			  $.messager.alert('提示','退案日期必须大于委案日期','info');
			  return false;
		  }
	  }
	return true;
}


//加载案件类型
/* function loadCaseType(isAdd,value){
	if(value==undefined){
		value = null;
	} 
	var url = ctx+'/sys/dictionary/combobox?path=DATA_MGR/CASE_BATCH_MGR/CASE_TYPE';
	var dicts;
	$.ajax({
        url: url,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts = data;
        }
    });
	$('#typeId').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    width:150,
	    valueField:'value',
        textField:'text',
        value:value,
        data:dicts,
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#handle').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    width:150,
	    valueField:'value',
        textField:'text',
        value:value,
        data:dicts,
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#entrustId').combobox({
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
} */

/* function getEnAndTy(){
	var entrustId=$('#entrustId').combobox('getValue')
	var typeId=$('#typeId').combobox('getValue')
	if(entrustId!='' && typeId!=''){
		generatorBatchCode(entrustId, typeId);
	}
}
//获取批次号的URL， 传递参数
function generatorBatchCode(entrustId, typeId){
	$.ajax({
        url:baseUrl+'/createBatchCode',
        dataType: "json",
        data:{'typeId':typeId,'entrustId':entrustId},
        async: false,
        success: function(data){
        	// alert(data.batchCode);
        	$("#batchCode").val(data.batchCode);
        }
    });
}
	 */
</script>
