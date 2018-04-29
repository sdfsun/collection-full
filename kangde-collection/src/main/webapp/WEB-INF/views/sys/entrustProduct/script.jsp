<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var dia;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/entrustProduct";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadEntrustSearch("#search_entrustId");
    loadCaseAssigned('3');
    loadCaseAssignedSearch('3');
    loadStaff('bd952570dda5472f830c8d43ca6a23c5');
    loadStaffSearch('bd952570dda5472f830c8d43ca6a23c5');
  //  loadStaff('${CURRENT_USER.positionId}');
    loadSource("#search_entrustId");
    loadSourceSearch("#search_entrustId");
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/queryProduct',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'id',
      
        columns:[[
			{field : 'ck', checkbox : true },
 			{field:'state',title:'客户状态',width:100,
				 formatter : function(value, row, index) {
					return app.dictName('CUS_TYPE', value)
				}
 			},
 			{field:'en_name',title:'委托方',width:100},
 			{field:'ecs_name',title:'案源地',width:100},
 		   	{field:'caseTypeId',title:'案件类型',align:'center',width:100,
            	formatter : function(value, row, index) {
 					return app.dictName('CASE_TYPE', value)
 				}	
            },
 		   	{field:'handle',title:'手次',align:'center',width:100,
            	formatter : function(value, row, index) {
 					return app.dictName('HANDLE', value)
 				}	
            },
 			{field:'code',title:'全简码',width:100},
 			{field:'serviceProject',title:'服务项目',width:100,
            	formatter : function(value, row, index) {
 					return app.dictName('SER_PRO', value)
 				}
 			},
 			{field:'createTime',title:'接触日期',width:100,
 				formatter:function(value, row, index){
             		if(value){
             			return $.formatDate(value,'yyyy-MM-dd');
             		}
             		return value;
             	}	
 			},
 			{field:'createEmpName',title:'初始营业员',width:100},
 			{field:'staffName',title:'营业员',width:100},
 			{field:'contractAwardDate',title:'合同签约日期',width:100,
 				formatter:function(value, row, index){
             		if(value){
             			return $.formatDate(value,'yyyy-MM-dd');
             		}
             		return value;
             	}
 			},
			{field:'contractCycle',title:'合同周期',width:100},
			{field:'rate',title:'费率',width:100,
        		formatter : function(value, row, index) {
						if (null==value) {
							return "0.00";
						}else{
                    	return $.fmoney(value); 
						}
						return value;
					} 
			},
			{field:'userCus_name',title:'客服总窗',width:100},
			{field:'cusType',title:'客户类型',width:100,
            	formatter : function(value, row, index) {
 					return app.dictName('CUSTYPE', value)
 				}	
			},
			{field:'bt',title:'拜访记录',width:100,
				formatter:function(value,row,index){
					//<span  class='icon icon-tianjia' onclick=huji();>&nbsp;&nbsp;</span>
    				return "<a style='color:blue;text-decoration: underline;' onclick=show("+"'"+row.id+"'"+")>拜访记录</a>";  
/*     				return '<a onclick=window.open("${pageContext.request.contextPath}/sys/entrustVisit/visitView?id='+row.id+'")>拜访记录</a>'; */
        		}
			},
            {field:'modifyTime',title:'修改时间',align:'left',width:150,
             	formatter:function(value, row, index){
             		if(value){
             			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
             		}
             		return value;
             	}
            } 
        ]],
        toolbar: [
             {
                text: '新增',
                iconCls: 'eu-icon-xinzeng',
                handler: function () {
                    showDialog()
                }
            },
            '-',
           
            {
                text: '编辑',
                iconCls: 'eu-icon-bianji',
                handler: function () {
                    edit()
                }
            },
            '-',
          
            /* {
                text: '删除',
                iconCls: 'eu-icon-shanchu',
                handler: function () {
                    del()
                }
            },
            '-', */
            <shiro:hasPermission name="entrust:start">
            {
                text: '启用',
                iconCls: 'eu-icon-qiyong',
                handler: function () {
                    start()
                }
            },
            '-',
            </shiro:hasPermission> 
            <shiro:hasPermission name="entrust:stop">
            {
                text: '停用',
                iconCls: 'eu-icon-tingyong',
                handler: function () {
                    stop()
                }
            },
            </shiro:hasPermission> 
        ],
        onLoadSuccess: function () {
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
       /*  onDblClickRow: function (rowIndex, rowData) {
            edit(rowIndex, rowData);
        } */
    }).datagrid('showTooltip');

});
//初始化表单
function formInit(){
    _form = $('#_form').form({
    	//表单提交地址
        url: baseUrl,
        //表单提交数据之前回调函数
        onSubmit: function(){
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
            	app.reload(_datagrid);//重新加载列表数据
            });
        }
    });
}
function show(id) {
	var inputUrl='${pageContext.request.contextPath}/sys/entrustVisit/visitView?id='+id; 
	dia=app.openViewDialog('拜访记录', 1000, 500, inputUrl, 'GET', function(){
   		//初始化会话页面组件
   	});
	
}
//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row) {
    var inputUrl = baseUrl+"/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "/" + row.id;
    }

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		//alert($("#entrustId").combobox('getvalue'));
   		_form.submit();//提交表单
   	}, '产品', 760, 330, inputUrl, 'GET', function(){
   		//初始化会话页面组件
   		formInit();
     	loadWholeCode(true);
   	
   		loadEntrust('#entrustId');
   		//loadEntrust('#update_entrustId');
   		loadCaseAssigned('3');
   		loadStaff('bd952570dda5472f830c8d43ca6a23c5');
   		//loadStaff('${CURRENT_USER.positionId}');
   		loadSource('#entrustId');  
   		//loadSourceUpdate('#caseSourceId_update',row.entrustId); 
        if(row){
        //	loadWholeCode(false,row.caseTypeId,row.handle);
          	row._method = "PUT";//修改表单提交方式为PUT,修改请求
        	if(row.createTime){
           	  	row.createTime = $.formatDate(row.createTime,'yyyy-MM-dd');
           	  } 
        	if(row.modifyTime){
           	  	row.modifyTime = $.formatDate(row.modifyTime,'yyyy-MM-dd HH:mm:ss');
           	  } 
        	if(row.contractAwardDate){
           	  	row.contractAwardDate = $.formatDate(row.contractAwardDate,'yyyy-MM-dd');
           	  } 
        	 _form.form('load', row);
        }
   	});
}

//编辑
function edit(rowIndex, rowData) {
    //响应双击事件
    if (rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
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
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

function showDialog2(row) {
    var inputUrl = baseUrl+"/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "/" + row.id;
    }

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   		_dialog = app.openFormDialog(function(){
   		//alert($("#entrustId").combobox('getvalue'));
   		_form.submit();//提交表单
   	},'编辑', 760, 330, inputUrl, 'GET', function(){
   		formInit();
   	   	//loadWholeCode(true);
   	   		loadCaseAssigned('3');
   	   		loadStaff('bd952570dda5472f830c8d43ca6a23c5');
   	   	
   	   		loadEntrustUpdate('#update_entrustId',row.entrustId);
   	   		loadSourceUpdate('#caseSourceId_update',row.entrustId); 
   	   		loadWholeCodeUpdate(false,row.caseTypeId,row.handle);
   	        if(row){
   	          	row._method = "PUT";//修改表单提交方式为PUT,修改请求
   	        	if(row.createTime){
   	           	  	row.createTime = $.formatDate(row.createTime,'yyyy-MM-dd');
   	           	  } 
   	        	if(row.modifyTime){
   	           	  	row.modifyTime = $.formatDate(row.modifyTime,'yyyy-MM-dd HH:mm:ss');
   	           	  } 
   	        	if(row.contractAwardDate){
   	           	  	row.contractAwardDate = $.formatDate(row.contractAwardDate,'yyyy-MM-dd');
   	           	  } 
   	        	$("#entrustAbbrevia").textbox('setValue',row.entrustAbbrevia);
   	        	$("#productCategoryId").combobox('setValue',row.productCategoryId);
   	        	$("#guaranteeWayId").combobox('setValue',row.guaranteeWayId);
   	        	$("#purposeId").combobox('setValue',row.purposeId);
   	        	 _form.form('load', row);
   	        }
   	});
}

//批次详情
function detail(rowIndex, rowData) {
	//响应双击事件
	if (rowIndex != undefined) {
		showDialog2(rowData);
		return;
	}
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
    var row;
    if (rowIndex == undefined) {
        row = _datagrid.datagrid('getSelected');
    }
    if (row != undefined) {
        $.messager.confirm('确认提示！','您确定删除所选委托方吗？',function(r){
            if (r){
                $.post(baseUrl+'/'+row.id,{'_method':'DELETE'},function(data){
                	//渲染结果
                    app.renderAjax(data,function(json){
                    	app.load(_datagrid);
                    });
                },'json');

            }
        });
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

//启用
function start() {
	var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
   	if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
		$.messager.confirm('确认提示！',
				'您确定启用所选委托方吗？', function(r) {
					if (r) {
						//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
						delete row['cooperateDate'];
						delete row['createTime'];
						delete row['modifyTime'];
						  var ids = new Array();
			              $.each(rows, function (i, row) {
			                	ids[i] = row.id;
			                });
						//按钮跳转自己写的方法，updateForState   是控制层@RequestMapping 注解  value等于的值。 row选中一行，作为参数
						$.post(baseUrl + '/updateForStatus', {ids:ids}, function(data) {
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
//停用
function stop() {
	var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
   	if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
		$.messager.confirm('确认提示！',
				'您确定停用所选委托方吗？', function(r) {
					if (r) {
						//删除几个date类型参数，不然转类型会失败，row整体就接受不到。
						delete row['cooperateDate'];
						delete row['createTime'];
						delete row['modifyTime'];
						 var ids = new Array();
			              $.each(rows, function (i, row) {
			                	ids[i] = row.id;
			                });
			               $.post(baseUrl + '/updateForStatusNo', {ids:ids}, function(
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

function loadEntrust(domId) {
	var url = ctx + '/sys/entrust/combobox';
	$(domId).combobox({
		url : url,
		multiple : false,//是否可多选
		editable:true,//是否可编辑
		width : 150,
		valueField : 'value',
		textField : 'text',
		onChange:function(nv, ov){
	        	//重新渲染用户列表
	        	loadSource(nv);
	        	loadSourceSearch(nv);
	        	loadSourceUpdate(nv);
	        }
	});
}
 function loadEntrustUpdate(domId,entrustId) {
	var url = ctx + '/sys/entrust/combobox';
	$(domId).combobox({
		url : url,
		multiple : false,//是否可多选
		editable:true,//是否可编辑
		width : 150,
		valueField : 'value',
		textField : 'text',
		value:entrustId,
		onSelect:function(record){
        	$("#update_entrustId").attr("value",record.text);
        	alert($("#update_entrustId").val());
        },
		onChange:function(nv, ov){
			
	        	//重新渲染用户列表
	        	loadSourceUpdate(nv);
	        }
	});
} 
function loadEntrustSearch(domId) {
	var url = ctx + '/sys/entrust/combobox';
	$(domId).combobox({
		url : url,
		multiple : false,//是否可多选
		editable:true,//是否可编辑
		width : 150,
		valueField : 'value',
		textField : 'text',
		onChange:function(nv, ov){
	        	//重新渲染用户列表
	        	loadSourceSearch(nv);
	        }
	});
}

//加载用户
function loadCaseAssigned(posId){
	var url = ctx+'/sys/employeeInfo/posusersByPos?posId='+posId;
	$('#customServiceTotal').combobox({
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载用户
function loadCaseAssignedSearch(posId){
	var url = ctx+'/sys/employeeInfo/posusersByPos?posId='+posId;
	$('#search_customServiceTotal').combobox({
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载用户
function loadStaff(posId){
	var url = ctx+'/sys/employeeInfo/posusersByPos?posId='+posId;
	$('#staff').combobox({    
		url:url,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载用户
function loadStaffSearch(posId){
	var url = ctx+'/sys/employeeInfo/posusersByPos?posId='+posId;
	$('#search_staff').combobox({    
		url:url,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载案源地
function loadSource(entrustId){
	var url = ctx+'/sys/entrustCaseSource/enSourcesByEnId?entrustId='+entrustId;
	$('#caseSourceId').combobox({
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载案源地
function loadSourceSearch(entrustId){
	var url = ctx+'/sys/entrustCaseSource/enSourcesByEnId?entrustId='+entrustId;
	$('#search_caseSourceId').combobox({
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
//加载案源地
function loadSourceUpdate(domId,entrustId){
	var url = ctx+'/sys/entrustCaseSource/enSourcesByEnId?entrustId='+entrustId;
	$(domId).combobox({
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}

function loadWholeCode(isAdd,value1,value2){
	if(value1==undefined){
		value1 = null;
	} 
	if(value2==undefined){
		value2 = null;
	} 
	var url1 = ctx+'/sys/dictionary/combobox?path=DATA_MGR/CASE_BATCH_MGR/CASE_TYPE';
	var url2 = ctx+'/sys/dictionary/combobox?path=DATA_MGR/CASE_BATCH_MGR/HANDLE';
	var dicts;
	$.ajax({
        url: url1,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts = data;
        }
    });
	$.ajax({
        url: url2,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts2 = data;
        }
    });
	$('#caseTypeId').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    width:150,
	    valueField:'value',
        textField:'text',
        value:value1,
        data:dicts,
        onSelect:function(record){
        	$("#caseTypeId1").attr("value",record.text);
        },
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
        value:value2,
        data:dicts2,
        onSelect:function(record){
        	$("#handle1").attr("value",record.text);
        },
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#caseSourceId').combobox({
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#entrustId').combobox({
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
}
function loadWholeCodeUpdate(isAdd,value1,value2){
	if(value1==undefined){
		value1 = null;
	} 
	if(value2==undefined){
		value2 = null;
	} 
	var url1 = ctx+'/sys/dictionary/combobox?path=DATA_MGR/CASE_BATCH_MGR/CASE_TYPE';
	var url2 = ctx+'/sys/dictionary/combobox?path=DATA_MGR/CASE_BATCH_MGR/HANDLE';
	var dicts;
	$.ajax({
        url: url1,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts = data;
        }
    });
	$.ajax({
        url: url2,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts2 = data;
        }
    });
	$('#update_caseTypeId').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    width:150,
	    valueField:'value',
        textField:'text',
        value:value1,
        data:dicts,
        onSelect:function(record){
        	$("#caseTypeId1").attr("value",record.text);
        },
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#update_handle').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    width:150,
	    valueField:'value',
        textField:'text',
        value:value2,
        data:dicts2,
        onSelect:function(record){
        	$("#handle1").attr("value",record.text);
        },
        onChange:function(newValue, oldValue){
        	getEnAndTy();
        }
	});
	$('#caseSourceId_update').combobox({
        onChange:function(newValue, oldValue){
        	//getEnAndTy();
        }
	});
	$('#update_entrustId').combobox({
        onChange:function(newValue, oldValue){
        	//getEnAndTy();
        }
	});
}

function getEnAndTy(){
	var entrustId=$('#entrustId').combobox('getValue')
	var caseTypeId=$('#caseTypeId').combobox('getValue')
	var handle=$('#handle').combobox('getValue')
	var caseSourceId=$('#caseSourceId').combobox('getValue')
	if(entrustId!='' && caseTypeId!='' && caseSourceId!='' && handle!='' ){
		generatorWholeCode(entrustId, caseTypeId, handle,caseSourceId);
	}
}
//获取批次号的URL， 传递参数
function generatorWholeCode(entrustId, caseTypeId,handle,caseSourceId){
	$.ajax({
        url:baseUrl+'/createWholeCode',
        dataType: "json",
        data:{'caseTypeId':caseTypeId,'entrustId':entrustId,'handle':handle,'caseSourceId':caseSourceId},
        async: false,
        success: function(data){
//         	 alert(data.wholeCode);
        	$("#code").val(data.wholeCode);
        }
    });
}
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
