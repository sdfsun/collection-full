<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/helpme";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadOrgs();//风控方
    loadEntrust('#search_entrustId');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/query',
        fit: true,
        queryParams:$.serializeObject(_search_form),
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        frozenColumns: [[
                         {field: 'id', checkbox: true,width:1}
                        ]],
        columns: [[
               	{field:'caseModel.caseCode',title:'案件序列号',width:180,sortable:true,
                	formatter: function(value,row,index){
                		return openCaseDetailed(value, row, index);
    			    }},
    			 {field:'caseModel.caseName',title:'姓名',width:95,
    					formatter : function(value, row, index) {
         					if (value) {
         						return value;
         					}
         					return value;
         				}},
				{field:'caseModel.caseNum',title:'证件号',width:180,sortable:true,
					formatter : function(value, row, index) {
     					if (value) {
     						return value;
     					}
     					return value;
     				}},
     				{field:'caseModel.caseMoney',title:'委案金额',width:95,sortable:true,
     					formatter : function(value, row, index) {
     						if (""==value) {
     							return "0.00";
     						}else{
                            	return $.fmoney(value); 
     						}
     						return value;
     					} },
			    {field:'surplusMoney',title:'已还款',width:95,sortable:true,
     						formatter : function(value, row, index) {
         						if (""==value) {
         							return "0.00";
         						}else{
                                	return $.fmoney(value); 
         						}
         						return value;
         					} }, 
				
				{field:'caseModel.agentRate',title:'业绩率',width:60},
				{field:'applyType',title:'类型',width:60,sortable:true,
					formatter : function(value, row, index) {
						return app.dictName('HELPME', value)
					}},
				{field:'applyContent',title:'申请内容',width:280},
				{field:'entrustModel.name',title:'风控方',width:95,sortable:true,
					formatter : function(value, row, index) {
     					if (value) {
     						return value;
     					}
     					return value;
     				}},
     				{field:'employeeInfoModel.userName',title:'申请人',width:95,sortable:true,
    					formatter : function(value, row, index) {
         					if (""==value) {
         						return row.applyUserName;
         					}else{
         					return value;
         					}
         				}},
				{field:'appTime',title:'申请日期',width:95,sortable:true,
					formatter : function(value, row, index) {
     					if (value) {
     						return $.formatDate(value, 'yyyy-MM-dd');
     					}
     					return value;
     				}},
				
				
        ]],
        toolbar: [
				<shiro:hasPermission name="helpme:agree">
                  {
	                      text: '通过',
	                      iconCls: 'eu-icon-tongyi',
	                      handler: function () {
	                    	  agree()
                      }},'-',
                 </shiro:hasPermission>
                 <shiro:hasPermission name="helpme:noagree">
                   {
                          text: '不通过',
                          iconCls: 'eu-icon-butongyi',
                          handler: function () {
                          	noagree()
                       }},
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


//通过
function agree(){
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    var tf=false;
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		 $.each(rows, function (i, row) {
					if(row.state==1 || row.state==2){
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
				       	   	}, '查资审批',460, 280,RoleUrl+"/"+ids, 'GET',function(){
				       	 		formInit();
				       	   		_form.form('load',row);
				       	   	});
    		 }
    	}
}

//不通过
function noagree(){
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    var tf=false;
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		 $.each(rows, function (i, row) {
					if(row.state==1 || row.state==2){
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
				       	    	_dialog = app.openFormDialogApprovalNo(function(){
				       	    	_form.submit();
				       	   	}, '查资审批',460, 280,RoleUrl+"/"+ids, 'GET',function(){
				       	 		formInit();
				       	   		_form.form('load',row);
				       	   	});
    		 }
    	}
}
//初始化表单
function formInit(){
    _form = $('#_form').form({
    	//表单提交地址
       url : baseUrl+'/agree',
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

//初始化表单
function formInitNo(){
    _form = $('#_form').form({
    	//表单提交地址
       url : baseUrl+'/noagree',
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
function loadOrgs() {
	var url = ctx + '/sys/organization/getOrganizationList';
	$('#orgId').combotree({
		url : url,
		multiple : false,//是否可多选
		editable : false,//是否可编辑
		width : 150,
		valueField : 'id',
		value : '${CURRENT_USER.orgId}',
		loadFilter: function(rows){
            return convert(rows);
        }
	
	});
}

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
//委托方 
function loadEntrust(domId){
	var url = ctx+'/sys/entrust/entrustlist';
	$(domId).combobox({
        url:url,
        width:150,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
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
