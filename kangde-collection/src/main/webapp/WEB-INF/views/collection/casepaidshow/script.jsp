<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var cancel_form;
var add_form;
var _search_form;
var _dialog;
var cancel_dialog;
var add_dialog;
var paid_dialog;
var paid1_dialog;
var paid2_dialog;
//请求基础路径
var baseUrl = ctx+"/collection/casepaidshow";

$(function () {
	
    _form = $('#_form').form();
    cancel_form = $('#cancel_form').form();
    add_form = $('#add_form').form();
    _search_form = $('#_search_form').form();
    loadEntrust('#search_entrustId','all');
    loadOrgs();
    loadCaseAssigned('${CURRENT_USER.orgId}');
    //statistics($.serializeObject(_search_form)); 
    caseState();
    findBacthCode('#batchCode');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: '',
        fit: true,
        method:'POST',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        toolbar: [
        	<shiro:hasPermission name="casepaidshow:affirm">
            {
                text: '确定还款',
                iconCls: 'eu-icon-querenhuankuan',
                handler: function () {
                    affirm()
                }
            },
            '-',
            </shiro:hasPermission>
            <shiro:hasPermission name="casepaidshow:cancel">
            {
                text: '作废还款',
                iconCls: 'eu-icon-zuofeihuankuan',
                handler: function () {
                    cancel()
                }
            },
            '-',
            </shiro:hasPermission>
            <shiro:hasPermission name="casepaidshow:choose">
            {
                text: '导出所选还款',
                iconCls: 'eu-icon-daochusuoxuan',
                handler: function () {
                	exportCase(1)
                }
            },
            '-',
            </shiro:hasPermission>
            <shiro:hasPermission name="casepaidshow:select">
            {
                text: '导出所查还款',
                iconCls: 'eu-icon-daochusuocha',
                handler: function () {
                	exportCase(2)
                }
            },
            '-',
            </shiro:hasPermission>
            <shiro:hasPermission name="casepaidshow:addInsert">
            {
                text: '新增自来账',
                iconCls: 'eu-icon-daochusuocha',
                handler: function () {
                	add()
                }
            }
            </shiro:hasPermission>
        ],
        rowStyler: function(index,row){
        	var color1=0;
        	if (row.caseModel) {
					color1= row.caseModel.color;
				}
            color=app.dictName('CASE_COLOR',color1);
         
            if(!color){
            	color='none';
            }
            var rowstyle='color:'+color;
            if(row.state==3 || row.state==4){
            	rowstyle+=';color:#CACACA;text-decoration:line-through;'
            }
    		return rowstyle;
    	},
    	   onLoadSuccess: function () {
	        	$(this).datagrid('showTooltip').datagrid('columnMoving');
	        	app.unCheckAll(this);//取消所有选中
	        },
        onRowContextMenu: function (e, rowIndex, rowData) {
            e.preventDefault();
            app.unCheckAll(this);//取消所有选中
            $(this).datagrid('selectRow', rowIndex);
            $('#_datagrid_menu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        onDblClickRow: function (rowIndex, rowData) {
            edit(rowIndex, rowData);
        }
    }).datagrid('showTooltip');

  //  _datagrid.datagrid('showTooltip');
    
});
//初始化表单
/* function formInit(){
    _form = $('#_form').form({
    	//表单提交地址
        url: baseUrl+'/updateForState',
        //表单提交数据之前回调函数
        onSubmit: function(row){
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
        	//alert(data);
        	
        	 $.messager.progress('close');
            //渲染结果
            app.renderAjax(data,function(json){
            	paid_dialog.dialog('destroy');//销毁对话框
            	app.reload(_datagrid);//重新加载列表数据
            }); 	
            	row.paidNum = $("#paidNum").val();
				row.surplusMoney = $("#surplusMoney").val();
				//alert(row.surplusMoney);
				if(row.paidNum >= row.surplusMoney){
					
					$.messager.confirm('结清确认！', '该案件已全额还款，是否结清该案件？', function(r) {
						if (r) {
							//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
							$.post(ctx+"/collection/case/changeState", {'caseIds': caseIds,'state':4}, function(
									data) {
								//渲染结果
								app.renderAjax(data, function(json) {
									app.load(_datagrid);
								});
							}, 'json');
						}
						_form.form('load',row);
					});
				
				}else {
					
					//alert(1);
				}
           
        }
    });
} */

function exportCase(type){
	var url = ctx+'/collection/casepaidshow/excelAll';
	if(1==type){
		app.dataGridSelect(_datagrid, function(rows){
			_dialog = app.openFormDialog(function(){
				exportSelected();
			}, '导出内容', 650, 350, url, 'GET', function(rows){}, '导出', '取消');
		});
	}else if(2==type){
		_dialog = app.openFormDialog(function() {
			exportSelectedall();
		}, '导出内容', 650, 350, url, 'GET', function(rows) {}, '导出', '取消');
	
}
}
 //导出还款
function exportSelected(){
	app.dataGridSelect(_datagrid, function(rows){
        var isValid = $('#excel_form').form('validate');//表单提交 ，勾选字段页面。
        var array = [];//定义数组 ，把选中的字段的值放入。
		$('input[name=field]:checked').each(function(i) {
			//alert($(this).val());
			array.push($(this).val());
		})
		if (array.length == 0) {
				eu.showAlertMsg("请勾选字段，至少一个！", 'warning');
			}else{
        if(isValid){
        	var ids = new Array();
            $.each(rows, function (i, row) {
            	ids[i] = row.id;
            });
	        app.downLoadFile({
	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        	data:{'ids':ids,'array':array},//要发送的数据  所选的批次id
	        	callback:function(){
	        		_dialog.dialog('destroy');
	        	}
	        });
        }
			}
	});
};

//导出还款
function exportSelectedall(){

        var isValid = $('#excel_form').form('validate');//表单提交 ，勾选字段页面。
        var array = [];//定义数组 ，把选中的字段的值放入。
		$('input[name=field]:checked').each(function(i) {
			//alert($(this).val());
			array.push($(this).val());
		})
		var opts = _datagrid.datagrid("options");
		var queryParams = opts.queryParams;
		queryParams.sort = opts.sortName;
		queryParams.order = opts.sortOrder;
		if (array.length == 0) {
			eu.showAlertMsg("请勾选字段，至少一个！", 'warning');
		}else{
        if(isValid){
	        app.downLoadFile({
	        	url:baseUrl+'/exportAllExcel?array=' + array, //请求的url
	        	data : queryParams,
	        	callback:function(){
	        		_dialog.dialog('destroy');
	        	}
	        });
        }}
	
};

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog1(row) {
    var inputUrl = baseUrl+"/input";
    if(row.state == 2){
    	eu.showAlertMsg("已还款，不能重复还款!",'warning');
	}else if(row.state != 2){
        //inputUrl = inputUrl + "/" + row.id;
      //  var caseId=row.id;
        paid_dialog = app.openFormDialog(function() {
        	
        	var e = $('#paidNum2').val();
        	if(e>0){
        		
        	   if($('#outDerate').val()>0 || $('#inDerate').val()>0){
                   
               	//alert($('#surplusMoney').val());
               
               	var a = $('#paidNum2').val();
               	var b = $('#outDerate').val();
               //	var c = $('#inDerate').val();
               	
               	var url = baseUrl + "/sum";
           		$.ajax({
           			type : "POST",
           			url : url,
           			dataType : "json",
           			data:{paidNum:a,
           				outDerate:b},
           			async : false,
           			success : function(data) {
           				if(data.sum< row.surplusMoney){
           					eu.showAlertMsg("确认金额与外部减免金额的和不能小于剩余还款",'warning');
           					return;
           				}
           				_form.submit();//提交表单
           			}
           		});
           		
               }else{
            	   
        	   _form.submit(); 
               }
        	   
        	}else{
        		eu.showAlertMsg("确认金额必须大于零",'warning');
        	}
		 
		}, '确定还款', 550, 300, inputUrl+"/"+row.id, 'GET', function() {
			//初始化会话页面组件
		_form = $('#_form').form({
    	//表单提交地址
        url: baseUrl+'/updateForState',
        
        //表单提交数据之前回调函数
        onSubmit: function(row){
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
            	
            	paid_dialog.dialog('destroy');//销毁对话框
            	app.reload(_datagrid);//重新加载列表数据
            	
            	if(json.code==1){
            		
                	row.paidNum = json.obj;
    				if(row.paidNum >= row.surplusMoney){
    					
    					var url = baseUrl + "/clear";
    	            	var a = row.caseId;
    	           		$.ajax({
    	           			type : "POST",
    	           			url : url,
    	           			dataType : "json",
    	           			data:{caseId:a},
    	           			async : false,
    	           			success : function(data) {
    	           				if(data.state!=4){
    	           					$.messager.confirm('结清确认！', '该案件已全额还款，是否结清该案件？', function(r) {
    	        						if (r) {
    	        							//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
    	        							row.caseModel.state=4;
    	        							var state = row.caseModel.state;
    	        							var caseIds = new Array(1);
    	    			            	    caseIds[0] = row.caseId;
    	    			            	    caseIds=caseIds[0];
    	        							$.post(ctx+"/collection/case/changeState",{'caseIds': caseIds,'state':state,'remark':'结清'}, function(
    	        									data) {
    	        								
    	        								//渲染结果
    	        								/* app.renderAjax(data, function(json) {
    	        									app.load(_datagrid);
    	        								}); */
    	        							}, 'json');
    	        						}
    	        					});
    	           				}
    	           				//_form.submit();//提交表单
    	           			}
    	           		});
    					
    					
    				
    				}
                }
            
            });
        }
    });
			//row.state = 1;
			
			_form.form('load',row);
		});
    }
}

//确定还款
function affirm() {
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
	        showDialog1(row);
	       // _form.form('load',row);
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}
//作废还款
function cancel() {
	 //选中的所有行
	// var cancelUrl = baseUrl+"/cancel";
    var rows = _datagrid.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = _datagrid.datagrid('getSelected');
    if (row) {
    	//多行校验
        if (rows.length > 1) {
            row = rows[rows.length - 1];
            eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
        }else if(row.state == 4){
        	eu.showAlertMsg("已作废，不能重复作废!",'warning');
        }else if(row.state != 4){
     			//alert(row.id);
     			showDialog(row);
        
        	
        	/* $.messager.confirm('确认提示！',
					'您确定作废所选择的还款记录吗？', function(r) {
					if (r) {
						//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
						delete row['cpTime'];
						delete row['ptpTime'];
						delete row['paidTime'];
						delete row['surTime'];
						delete row['delTime'];
						delete row['createTime'];
						delete row['modifyTime'];
						delete row['employeeInfo.attachOrgId'];
// 						alert(row.isDerate);
						//alert(row.inDerateNew);
						//按钮跳转自己写的方法，updateForCancelState   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
						$.post(ctx + '/collection/casepaidshow/updateForCancelState',
								{id:row.id,inDerate:row.inDerateNew,outDerate:row.outDerateNew}, 
								function(data) {
							//渲染结果
							app.renderAjax(data, function(json) {
								app.load(_datagrid);
							});
						}, 'json');
					}
					_form.form('load',row);
					}); */
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
	
	
}

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row) {
    var cancelUrl = ctx + '/collection/casepaidshow/cancel';
    if (row != undefined && row.id) {
        cancel_dialog = app.openFormDialog(function() {
			cancel_form.submit();//提交表单
			var outDerate = row.outDerateNew;
			var inDerate = row.inDerateNew;
		}, '作废原因', 460, 280, cancelUrl+"/"+row.id, 'GET', function() {
			//初始化会话页面组件
			formInitCancel();
			_form.form('load',row);
		});
    
}}


//初始化表单
function formInitCancel() {
	cancel_form = $('#cancel_form').form({
		//表单提交地址
		url :ctx + '/collection/casepaidshow/updateForCancelState',
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
				cancel_dialog.dialog('destroy');//销毁对话框
				app.reload(_datagrid);//重新加载列表数据
			});
		}
	});
}

//显示弹出窗口 新增：row为空 编辑:row有值
function add() {
    var addUrl = ctx + '/collection/casepaidshow/add';
        add_dialog = app.openFormDialog(function() {
			add_form.submit();//提交表单
			
		}, '新增', 350, 250, addUrl, 'GET', function() {
			//初始化会话页面组件
		/* 	var paidNum = $("#paidNum").val();
			var paidTime5 = $("#paidTime5").val(); */
			formInitAdd();
			_form.form('load');
		});
    
}


//初始化表单
function formInitAdd(){
	add_form = $('#add_form').form({
		//表单提交地址
		url :ctx + '/collection/casepaidshow/addInsert',
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
				add_dialog.dialog('destroy');//销毁对话框
				app.reload(_datagrid);//重新加载列表数据
				
		            	if(json.code==1){
		                	var paidNum = json.obj.paidNum; 
		    				if(paidNum >= json.obj.surplusMoney){
		    					var url = baseUrl + "/clear";
		    	            	var a = json.obj.caseId;
		    	           		$.ajax({
		    	           			type : "POST",
		    	           			url : url,
		    	           			dataType : "json",
		    	           			data:{caseId:a},
		    	           			async : false,
		    	           			success : function(data) {
		    	           				if(data.state!=4){
		    	           					$.messager.confirm('结清确认！', '该案件已全额还款，是否结清该案件？', function(r) {
		    	        						if (r) {
		    	        							//按钮跳转自己写的方法，updateForStatus   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
		    	        							json.obj.caseModel.state=4;
		    	        							var state = json.obj.caseModel.state;
		    	        							var caseIds = new Array(1);
		    	    			            	    caseIds[0] = json.obj.caseId;
		    	    			            	    caseIds=caseIds[0];
		    	        							$.post(ctx+"/collection/case/changeState",{'caseIds': caseIds,'state':state,'remark':'结清'}, function(
		    	        									data) {
		    	        								
		    	        								//渲染结果
		    	        								/* app.renderAjax(data, function(json) {
		    	        									app.load(_datagrid);
		    	        								}); */
		    	        							}, 'json');
		    	        						}
		    	        					});
		    	           				}
		    	           				//_form.submit();//提交表单
		    	           			}
		    	           		});
		    					
		    					
		    				
		    				}
		                }
		            
		            });
		}
	});
}



	 /* var rows = _datagrid.datagrid('getSelections');
	    //选中的行（第一次选择的行）
	    var row = _datagrid.datagrid('getSelected');
	    if (row) {
	    	//多行校验
	        if (rows.length > 1) {
	            row = rows[rows.length - 1];
	            eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
	        }else if (row != undefined) {
			//设置标示   后台用于判断是退案还是暂停。。。。。 
			if(row.state == 2){
				eu.showAlertMsg("已作废，不能重复作废!",'warning');
			}else if(row.state != 2){
				$.messager.confirm('确认提示！',
					'您确定作废所选择的还款记录吗？', function(r) {
					if (r) {
						//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
						delete row['beginDate'];
						delete row['createTime'];
						delete row['endDate'];
						delete row['modifyTime'];
						delete row['uploadTime'];
						
						//按钮跳转自己写的方法，updateForCancelState   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
						$.post( baseUrl+'/updateForCancelState', row, function(
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
	}
} */
//作废还款
/* function cancel() {
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
	        showDialog2(row);
	        _form.form('load',row);
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
} */

//加载委托方
function loadEntrust(domId,type){
	var url = ctx+'/sys/entrust/entrustlist?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    valueField:'value',
        textField:'text'
	});
} 
//加载组织机构
function loadOrgs(){
	var url = ctx+'/sys/organization/getOrganizationList';
	$('#orgId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    width:150,
	    height:22,
        valueField:'id',
        value:'${CURRENT_USER.orgId}',
        loadFilter: function(rows){
            return convert(rows);
        },
        onChange:function(nv, ov){
        	//重新渲染用户列表
        	loadCaseAssigned(nv);
        }
	});
}
//加载用户
function loadCaseAssigned(orgId){
	var url = ctx+'/sys/employeeInfo/orgusersByOrg?orgId='+orgId;
	$('#caseAssignedId').combobox({    
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}


//附件上传
function viewDocu(id) {
	var url=ctx+'/collection/upload/index?action=view&&businessType=cp&&businessId='+id;
	window.open(url);
}
//搜索
function search() {
	var options = _datagrid.datagrid('options');
	//url 需要自己定义
	options.url =baseUrl + '/queryPaid';
	
	var params = $.serializeObject(_search_form);
	//重新加载datagrid
	app.load(_datagrid,params);
	statistics(params);
}
//统计
function statistics(params){
	//统计重置
	$.ajax({
        url: baseUrl+"/statistics",
        type: 'post',
        data: params,
        dataType: 'json',
        success: function (data) {
        	//渲染结果
            app.renderAjax(data,function(json){
            	var obj=json.obj;
            	//alert(obj.state);
            	$('#total_paidMoney').html($.fmoney(obj.total_paidMoney));
            	$('#total_achieve').html($.fmoney(obj.total_achieve));
            	$('#total_already_money').html($.fmoney(obj.total_already_money));
            	$('#total_cp_money').html($.fmoney(obj.total_cp_money));
            },false);
        }
    });
}
function caseState(){
	  app.ajax({
        url: baseUrl+"/caseState",
        type: 'post',
        dataType : 'json',
        success: function (data) {
        	if(data.obj==1){
        		 document.getElementById('b').style.display = "none";
        	
        	}else if(data.obj==0){
        		 document.getElementById('a').style.display = "none";
        		
        	}
        }
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

</script>
