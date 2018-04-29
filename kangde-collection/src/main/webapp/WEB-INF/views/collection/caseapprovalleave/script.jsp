<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>		
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/caseapprovalleave";
$(function() {
	_search_form = $('#_search_form').form();
	  loadOrgs();
	    loadCaseAssigned('${CURRENT_USER.orgId}');
	    findBacthCode('#batchCode');
    loadEntrust('#search_entrustId','all');//加载委托方
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/queryforLeave',
        fit: true,
        method:'POST',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',

        columns:[[
		{field : 'id',checkbox : true,width:1},
		{field : 'caseModel.caseCode',title : '案件序列号',width : 180,sortable:true,
			formatter:function(value, row, index){
				return '<span style="text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.caseId+'")>'+value+'</span>';
  		}},
		{field : 'batchmodel.batchCode',title : '批次号',width : 120}, 
		{field : 'stayReason',title : '留案原因',width : 280,
				formatter : function(value, row, index) {
					if (value) {
						return value;
					}
					return value;
				}},	
				
     			 {field : 'caseModel.collecStateId',title : '风控状态',width : 95,sortable:true,
     	     				formatter : function(value, row, index) {
     	 						return app.dictName('CS_STATE', value)
     	 					}}, 
     				{field : 'caseModel.caseDate',title : '委案日期',width : 95,sortable:true,
     				formatter : function(value, row, index) {
     					if (value) {
     						return $.formatDate(value, 'yyyy-MM-dd');
     					}
     					return value;
     				}},
     				{field : 'caseModel.caseMoney',title : '委案金额',width : 95,sortable:true,
     					formatter:function(value, row, index){
                    		return $.fmoney(value); 
     					}	
     				},
     				{field:'employeeInfo.userName',title:'风控员',width:95,sortable:true},
     				{field : 'caseModel.agentRate',title : '业绩率',width : 60},
     				{field : 'caseModel.caseName',title : '姓名',width : 95},
     				{field : 'caseModel.caseNum',title : '证件号',width : 180,sortable:true},
     				{field : 'caseModel.caseCard',title : '卡号',width : 180,sortable:true},
     				{field : 'caseModel.mobile1',title : '手机号',width : 95},
     				
     				
     				{field : 'caseModel.familyAddress',title : '地址',width : 280},
     				
     				
     				{field : 'applyTime',title : '申请时间',width : 130,sortable:true,
     						formatter:function(value, row, index){
     	                		if(value){
     	                			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
     	                		}
     	                		return value;
     	                	}},	
     				
        ]],
        toolbar: [
<shiro:hasPermission name="caseapprovalleave:approvalYes">
            {
                text: '通过',
                iconCls: 'eu-icon-tongyi',
                handler: function () {
                	approvalYes()
                }
            },
            </shiro:hasPermission> 
            <shiro:hasPermission name="caseapprovalleave:approvalNo">  
            {
                text: '不通过',
                iconCls: 'eu-icon-butongyi',
                handler: function () {
                	approvalNo()
                }
            },
            </shiro:hasPermission> 
            
            <shiro:hasPermission name="caseapprovalleave:choose">
            {
                text: '导出所选留案',
                iconCls: 'eu-icon-daochusuoxuan',
                handler: function () {
                	exportCase(1)
                }
            },
            
            </shiro:hasPermission>
            <shiro:hasPermission name="caseapprovalleave:select">
            {
                text: '导出所查留案',
                iconCls: 'eu-icon-daochusuocha',
                handler: function () {
                	exportCase(2)
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
    		return 'color:'+color;
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
    });  
});



function exportCase(type){
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
	if(1==type){
		var ids = new Array();
	    $.each(rows, function (i, row) {
	    	ids[i] = row.id;
	    });
		 app.downLoadFile({
	        	url:baseUrl+'/exportForIds', //请求的url
	        	data:{'ids':ids},
	        });
	}else if(2==type){
		var queryParams = _datagrid.datagrid("options").queryParams;
		app.downLoadFile({
        	url:baseUrl+'/exportQueryExcel',
        	data:queryParams,
        });
	
	}
}


function approvalYes() {
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    var tf=false;
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		 $.each(rows, function (i, row) {
					if(row.approveState==1 || row.approveState==2){
						tf=true;
					}		 
                });
    		 if(true==tf){
    			 eu.showAlertMsg("您选中的数据中存在已完成审批的数据，不能重复审批！",'warning');
    		 }else if(false==tf){
    			  var ids = new Array();
	              $.each(rows, function (i, row) {
	                	ids[i] = row.id;
	                });
	        	var RoleUrl = baseUrl+"/input";
	       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
	       	    	_dialog = app.openFormDialogApproval(function(){
	       	    	_form.submit();
	       	   	}, '留案审批',460, 280,RoleUrl+"/"+ids, 'GET',function(){
	       	 		formInit();
	       	   		_form.form('load',row);
	       	   	});
    		 }
    	}
    	
}


function approvalNo() {
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    var tf=false;
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		 $.each(rows, function (i, row) {
					if(row.approveState==1 || row.approveState==2){
						tf=true;
					}		 
                });
    		 if(true==tf){
    			 eu.showAlertMsg("您选中的数据中存在已完成审批的数据，不能重复审批！",'warning');
    		 }else if(false==tf){
							  var ids = new Array();
				              $.each(rows, function (i, row) {
				                	ids[i] = row.id;
				                });
				        	var RoleUrl = baseUrl+"/inputno";
				       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				       	    	_dialog = app.openFormDialogApprovalNo(function(){
				       	    	_form.submit();
				       	   	}, '留案审批',460, 280,RoleUrl+"/"+ids, 'GET',function(){
				       	 		formInit();
				       	   		_form.form('load',row);
				       	   	});
    		 }
    	}
}

//初始化表单
function formInitNo(){
    _form = $('#_form').form({
    	//表单提交地址
       url : baseUrl+'/approvalNo',
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
app.openFormDialogApprovalNo=function(saveFun,title, width, height, url,method,onLoadFun){
	//弹出对话窗口
    var _dialog = $('<div/>').dialog({
        title:title,
        top:20,
        width : width,
        height:height,
        modal : true,
        maximizable:true,
        resizable:true,
        href : url,
        method:method,
        buttons :  [ {
			text : '不通过',
			handler : function() {
				//如果有函数,执行,没有略过
				if(saveFun){
            		saveFun();
            	}
			}
		},{
			text : '关闭',
			 handler : function() {
	                _dialog.dialog('destroy');
	            }
		}],
        onClose : function() {
            _dialog.dialog('destroy');
        },
        onLoad:function(){
        	//如果有函数,执行,没有略过
        	if(onLoadFun){
        		formInitNo();
        	}
        }
    });
    return _dialog;
};
 
app.openFormDialogApproval=function(saveFun,title, width, height, url,method,onLoadFun){
	//弹出对话窗口
    var _dialog = $('<div/>').dialog({
        title:title,
        top:20,
        width : width,
        height:height,
        modal : true,
        maximizable:true,
        resizable:true,
        href : url,
        method:method,
        buttons :  [ {
			text : '通过',
			handler : function() {
				//如果有函数,执行,没有略过
				if(saveFun){
            		saveFun();
            	}
			}
		},{
			text : '关闭',
			 handler : function() {
	                _dialog.dialog('destroy');
	            }
		}],
        onClose : function() {
            _dialog.dialog('destroy');
        },
        onLoad:function(){
        	//如果有函数,执行,没有略过
        	if(onLoadFun){
        		formInit();
        	}
        }
    });
    return _dialog;
};

//初始化表单
function formInit(){
    _form = $('#_form').form({
    	//表单提交地址
       url : baseUrl+'/approvalYes',
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
//加载委托方
function loadEntrust(domId,type){
	var url = ctx+'/sys/entrust/entrustlist?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
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


//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
