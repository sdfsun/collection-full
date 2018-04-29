<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ tagliburi ="http://shiro.apache.org/tags" prefix="shiro"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _applyStaydatagrid;
var _form;
var _applyStayform;
var caseId="${caseId}";
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/caseapproval";
$(function () {
    _search_form = $('#_search_form').form();
    _search_form1 = $('#_search_form1').form();
    loadOrgs();//风控方
    loadOrgs1();//风控方
    loadCaseAssigned();//风控员
    loadCaseAssigned1();//风控员
    loadEntrust('#search_entrustId','all');//加载委托方
    loadEntrust1('#search_entrustId1','all');//加载委托方
		$('#tt').tabs({    
		    border:false,    
		    onSelect:function(title){    
		        if(title=='留案审批'){
		        	  //------------------------------留案-----------------------------------------------
		            _datagridleave = $('#_datagridleave').datagrid({
		                url: baseUrl + '/queryforLeave',
		                fit: true,
		                method:'GET',
		                pagination: true,//底部分页
		                rownumbers: true,//显示行数
		                fitColumns: false,//自适应列宽
		                striped: true,//显示条纹
		                remoteSort:false,//是否通过远程服务器对数据排序
		                idField: 'id',
		                frozenColumns:[[
		                                {field : 'ck',checkbox : true},
		                                {field : 'batchmodel',title : '批次号',width : 100,
				             				formatter : function(value, row, index) {
				             					if (value) {
				             						return value.batchCode;
				             					}
				             					return value;
				             				}}, 
				             				{field : 'caseCode',title : '案件序列号',width : 100,
				             					formatter:function(value, row, index){
				     	 		    				return '<a style="color:blue;text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.id+'")>'+value+'</a>';
				     	 		        		}},
		                                ]],
		                columns:[[
		                          {field : 'approverecordmodel.approveState',title : '审批状态',width : 100,
		             					formatter : function(value, row, index) {
		             						return app.dictName('CASE_APPROVAL', value)
		             					}}, 
		             			 {field : 'collecStateId',title : '风控状态',width : 100,
		             	     				formatter : function(value, row, index) {
		             	 						return app.dictName('CS_STATE', value)
		             	 					}}, 
		             				{field : 'caseDate',title : '委案日期',width : 100,
		             				formatter : function(value, row, index) {
		             					if (value) {
		             						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
		             					}
		             					return value;
		             				}},
		             				{field : 'caseName',title : '姓名',width : 100},
		             				{field : 'caseNum',title : '证件号',width : 100},
		             				{field : 'caseCard',title : '卡号',width : 100},
		             				{field : 'mobile1',title : '手机号',width : 100},
		             				{field : 'caseMoney',title : '委案金额',width : 100,
		             					formatter : function(value, row, index) {
		             						if (""==value) {
		             							return "0.00";
		             						}else{
		                                    	return $.fmoney(value); 
		             						}
		             						return value;
		             					} 
		             				},
		             				{field : 'remainMoney',title : '剩余还款',width : 100,
		             					formatter : function(value, row, index) {
		             						if (""==value) {
		             							return "0.00";
		             						}else{
		                                    	return $.fmoney(value); 
		             						}
		             						return value;
		             					} 	
		             				},
		             				{field : 'companyAddress',title : '地址',width : 100},
		             				{field : 'casepaidmodel.sumMoney',title:'已还款',width:100,
		             					formatter : function(value, row, index) {
		             						if (""==value) {
		             							return "0.00";
		             						}else{
		                                    	return $.fmoney(value); 
		             						}
		             						return value;
		             					} 
		             				},
		             					{field:'employeeInfo',title:'风控员',width:100,
		                 					formatter : function(value, row, index) {
		                 						if (value) {
		                 							return value.userName;
		                 						}
		                 						return value;
		                 					}},
		             				{field : 'approverecordmodel.stayReason',title : '留案原因',width : 100,
		             					formatter : function(value, row, index) {
		             						if (value) {
		             							return value;
		             						}
		             						return value;
		             					}},	
		             				{field : 'approverecordmodel.stayTime',title : '留案时间',width : 100,
		             						formatter:function(value, row, index){
		             	                		if(value){
		             	                			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
		             	                		}
		             	                		return value;
		             	                	}},	
		             				
		                ]],
		                toolbar: [
		                    {
		                        text: '留案审批',
		                        iconCls: 'easyui-icon-add',
		                        handler: function () {
		                        	approvalLeave()//外访
		                        }
		                    },

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
		                onDblClickRow: function (rowIndex, rowData) {
		                    edit(rowIndex, rowData);
		                }
		            }).datagrid('showTooltip');
		        }
		    }    
		});  
//------------------------------外访-----------------------------------------------
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/queryforGo',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'id',
        frozenColumns:[[
                        {field : 'ck',checkbox : true},
                        {field : 'batchmodel.batchCode',title : '批次号',width : 100,
         					formatter : function(value, row, index) {
         						if (value) {
         							return value;
         						}
         						return value;
         			 	}}, 
         				{field : 'caseCode',title : '案件序列号',width : 100,
     							formatter:function(value, row, index){
     	 		    				return '<a style="color:blue;text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.id+'")>'+value+'</a>';
     	 		        		}},
                        ]],
        columns:[[
                
     			 // {field:'collecStateId',title:'附件',width:100},
     			  {field : 'visitrecordmodel.vrState',title : '审批状态',width : 100,
     					formatter : function(value, row, index) {
     						return app.dictName('CASE_APPROVAL', value)
     					}}, 
     			 {field : 'collecStateId',title : '风控状态',width : 100,
     				formatter : function(value, row, index) {
 						return app.dictName('CS_STATE', value)
 					}}, 
     			/* 	{field:'batchmodel.entrustId',title:'委托方',width:100,
     					formatter : function(value, row, index) {
     						if (value) {
     							return value;
     						}
     						return value;
     			 	}}, */
     				{field : 'caseDate',title : '委托开始日期',width : 100,
     				formatter : function(value, row, index) {
     					if (value) {
     						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
     					}
     					return value;
     				}},
     				{field : 'caseName',title : '姓名',width : 100},
     				{field : 'caseNum',title : '证件号',width : 100},
     				{field : 'caseCard',title : '卡号',width : 100},
     				{field : 'mobile1',title : '手机号',width : 100},
     				{field : 'caseMoney',title : '委案金额',width : 100},
     				{field : 'remainMoney',title : '剩余还款',width : 100},
     				{field : 'companyAddress',title : '地址',width : 100},
     				{field : 'casepaidmodel',title:'已还款',width:100,
     					formatter : function(value, row, index) {
     						if (value) {
     							return value.sumMoney;
     						}
     						return value;
     					}},
     				{field:'visitrecordmodel',title:'风控员',width:100,
     					formatter : function(value, row, index) {
     						if (value) {
     							return value.visitUser;
     						}
     						return value;
     					}},
     			 	{field:'visitrecordmodel.applyReason',title:'申请原因',width:100,
     						formatter : function(value, row, index) {
     	 						return app.dictName('VISIT_REASON', value)
     	 					}},
     			 {field:'visitrecordmodel.applyEmpId',title:'申请人',width:100,
     			 		formatter : function(value, row, index) {
     						if (value) {
     							return value;
     						}
     						return value;
     			 }},
        ]],
        toolbar: [
            {
                text: '外访审批',
                iconCls: 'easyui-icon-add',
                handler: function () {
                	approvalforgo()//外访
                }
            },

        ],
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


 
	//数据列表
	_applyStaydatagrid = $('#_applyStaydatagrid')
			.datagrid(
					{
						url : baseUrl + '/queryStayByCaseId' + "?caseId=" + caseId,
						fit : true,
						border:false,
						method : 'GET',
						pagination : true,//底部分页
						rownumbers : true,//显示行数
						fitColumns : true,//自适应列宽
						singleSelect : true,
						striped : true,//显示条纹
						remoteSort : false,//是否通过远程服务器对数据排序
						idField : 'id',
						toolbar : [ 
							<shiro:hasPermission name="detail:stay:save">
							{
								text : '申请留案',
								iconCls : 'eu-icon-shenqingliuan',
								handler : function() {
									applyStay();
								}
							}
							</shiro:hasPermission>			            
						          ],
						columns : [ [
								{
									field : 'id',
									title : 'id',
									hidden:true
								},

								{
									field : 'stayReason',
									title : '原因',
									width : 100
								},
								{
									field : 'stayTime',
									title : '留案时间',
									width : 100,
									formatter : function(value, row, index) {
		             					if (value) {
		             						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
		             					}
		             					return value;
		             				}
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
								},{
									field : 'applyTime',
									title : '申请时间',
									width : 100,
									formatter : function(value, row, index) {
		             					if (value) {
		             						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
		             					}
		             					return value;
		             				}
								},
								{
									field : 'approveState',
									title : '状态',
									width : 100,
									formatter : function(value, row, index) {
											if (value=='0') {
												return '待审批';
											}
											if (value=='1') {
												return '审批通过';
											}
											if (value=='2') {
												return '审批不通过';
											}
											return '未知';
			             				}
								},
								{
									field : 'approveOpinion',
									title : '审批意见',
									width : 100,
								} 
								] ],
						onLoadSuccess : function() {
							app.unCheckAll(this);//取消所有选中
						}
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
//-------外访--------
function approvalforgo() {
	var i=1;
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else if(row.visitrecordmodel.vrState==1 || row.visitrecordmodel.vrState==2){
  		  eu.showAlertMsg("以审批过数据，不能重复审批",'warning');
    	}else{
		   
		    var caseId=row.id;
        	var editRoleUrl = baseUrl+"/input";
       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
       	    editRole_dialog = app.openFormDialogApproval(function(){
       	   	}, '外访审批',500, 300,editRoleUrl+"/"+caseId+i, 'GET',function(){
       	   		_form.form('load',row);
       	   	});
    	}
}
//----------------------留案审批---------------------
function approvalLeave() {
	var i=0
	//选中的所有行
    var rows = _datagridleave.datagrid('getSelections');
	  var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
        return;
    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else if(row.approverecordmodel.approveState==1 || row.approverecordmodel.approveState==2){
    		  eu.showAlertMsg("以审批过数据，不能重复审批",'warning');
    	}else{
		    var caseId=row.id;
        	var editRoleUrl = baseUrl+"/input";
       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
       	    editRole_dialog = app.openFormDialogApproval(function(){
       	   	}, '留案审批',500, 300,editRoleUrl+"/"+caseId+i,'GET', function() {
    			//初始化会话页面组件
    			_form.form('load',row);
    		});
    	}
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
//加载组织机构
function loadOrgs1(){
	var url = ctx+'/sys/organization/parentOrganization';
	$('#orgId1').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    width:145,
        valueField:'id',
        value:'${CURRENT_USER.orgId}'
	});
}

 

//加载委托方
function loadEntrust(domId,type){
	var url = ctx+'/sys/entrust/combobox?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:160,
	    valueField:'value',
        textField:'text'
	});
} 
//加载委托方
function loadEntrust1(domId,type){
	var url = ctx+'/sys/entrust/combobox?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:160,
	    valueField:'value',
        textField:'text'
	});
} 
//加载风控员
function loadCaseAssigned(){
	var url = ctx+'/sys/employeeInfo/orgusers';
	$('#caseAssignedId').combogrid({    
	    method: 'get',
	    url: url,    
	    idField: 'id',
	    width:150,
	    textField: 'userName',
	    editable:true,
	    multiple:true,
	    separator:',',
	    columns: [[    
	        {field:'userName',title:'姓名',width:120},    
	        {field:'orgName',title:'机构名称',width:120}    
	    ]]
	});
}
//加载风控员
function loadCaseAssigned1(){
	var url = ctx+'/sys/employeeInfo/orgusers';
	$('#caseAssignedId1').combogrid({    
	    method: 'get',
	    url: url,    
	    idField: 'id',
	    width:150,
	    textField: 'userName',
	    editable:true,
	    multiple:true,
	    separator:',',
	    columns: [[    
	        {field:'userName',title:'姓名',width:120},    
	        {field:'orgName',title:'机构名称',width:120}    
	    ]]
	});

}
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
function search1() {
	//重新加载datagrid
	app.load(_datagridleave,$.serializeObject(_search_form1));
}

	//申请留案
	function applyStay() {
		var url = baseUrl + "/checkCaseStatus?caseId="+caseId;
		$.ajax({
			type : "POST",
			url : url,
			dataType : "json",
			async : false,
			success : function(data) {
				if(data.code==0){
					eu.showAlertMsg(data.msg);
					return;
				}
				showApplyStayDialog();
			}
		});
	
		

	
		
	}

	//显示弹出窗口 新增：row为空 编辑:row有值
	function showApplyStayDialog() {
		var inputUrl = baseUrl + "/applyStay?caseId="+caseId;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_applyStayform.submit();//提交表单
		}, '留案申请', 600, 230, inputUrl, 'GET', function() {
			//初始化会话页面组件
			stayformInit();
			
		});
	}

	function stayformInit() {
		_applyStayform = $('#_applyStayform').form({
			//表单提交地址
			url : baseUrl + "/applyStay",
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
					app.reload(_applyStaydatagrid);//重新加载列表数据
				});
			}
		});
	}
</script>
