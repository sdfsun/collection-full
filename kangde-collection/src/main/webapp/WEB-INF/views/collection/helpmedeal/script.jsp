<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>	
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _form_shebao;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/helpmedeal";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadOrgs();//风控方
    loadEntrust('#search_entrustId');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/querydeal',
        fit: true,
        method:'GET',
        queryParams:$.serializeObject(_search_form),
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
            				
            				
                   
				{field:'cz',title:'操作',width:60,
						formatter:function(value, row, index){
							if(row.applyType==4){
								return "<span  class='icon icon-tianjia' onclick=huji();>&nbsp;&nbsp;</span>";//
							}else if(row.applyType==12){
								return "<span  class='icon icon-tianjia' onclick=dianxin();>&nbsp;&nbsp;</span>";
							}else if(row.applyType==6){
								return "<span  class='icon icon-tianjia' onclick=shebao();>&nbsp;&nbsp;</span>"; 
							}else if(row.applyType==15){
								return "<span  class='icon icon-tianjia' onclick=kuaidi();>&nbsp;&nbsp;</span>"; 
							}
		        		}},
        ]], 
        toolbar: [
				<shiro:hasPermission name="helpmedeal:Export">
                  {
	                      text: '导出查资记录',
	                      iconCls: 'eu-icon-daochusuoxuan',
	                      handler: function () {
	                    	  Export()
                      }},'-',
                 </shiro:hasPermission>    
                 <shiro:hasPermission name="helpmedeal:upload">     
                   {
                          text: '导入查资记录',
                          iconCls: 'eu-icon-daoru',
                          handler: function () {
                          	upload()
                       }},'-',
                       </shiro:hasPermission>   
                       <shiro:hasPermission name="helpmedeal:bohui">     
                     {
                           text: '驳回',
                           iconCls: 'eu-icon-butongyi',
                           handler: function () {
                           	bohui()
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

//驳回
function bohui(){
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
							  var ids = new Array();
				              $.each(rows, function (i, row) {
				                	ids[i] = row.id;
				                });
				        	var RoleUrl = baseUrl+"/input";
				       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
				       	    	_dialog = app.openFormDialog(function(){
				       	    	_form.submit();
				       	   	}, '驳回意见',460, 280,RoleUrl+"/"+ids, 'GET',function(){
				       	   	bohuiIn();
				       	   		_form.form('load',row);
				       	   	},"驳回","关闭");
    		 }
    	}



//导出所选案件
function Export(){
	 //选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = rows[0];
	var applyType;
	var applyTypes = new Array();
   	var tf=true;
   	
	var ids = new Array();
    $.each(rows, function (i, row) {
    	ids[i] = row.id;
    });
   	
    if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}
    else if (row) {
    	applyType=row.applyType;
        $.each(rows, function (i, row) {
        	applyTypes[i] = row.applyType;
        	if(applyType!=applyTypes[i]){
        		tf=false;
        	}
        }); 
        if(tf==false){
        	eu.showAlertMsg("您选择的数据类型不一致,请从新选择！",'warning');
        }else
    	if(row.applyType==4){
    		 app.downLoadFile({
 	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        	data:{'ids':ids},
 	        });
		}else if(row.applyType==12){
			 app.downLoadFile({
		        	url:baseUrl+'/exportSelectedExcelDianXIn', //请求的url
		        	data:{'ids':ids},
		        });
		}else if(row.applyType==6){
			 app.downLoadFile({
		        	url:baseUrl+'/exportSelectedExcelSeBao?id', //请求的url
		        	data:{'ids':ids},
		        });
		}else if(row.applyType==15){
			 app.downLoadFile({
		        	url:baseUrl+'/exportSelectedExcelKuaiDi?id', //请求的url
		        	data:{'ids':ids},
		        });
		}
    }
	
	
};
	
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
        },
		onChange : function(nv, ov) {
			//重新渲染用户列表
			loadCaseAssigned(nv);
		}
	});
}

//户籍 弹出录入页面
function huji(){
	var editRoleUrl = baseUrl+"/huji";
	var rows = _datagrid.datagrid('getSelections');
	var row = rows[0];
	var caseApplyId=row.id;
	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
	    _dialog = app.openFormDialog(function(){
	    	_form.submit();//提交表单
	 	}, 'HJ',840, 440,editRoleUrl+"/"+caseApplyId,'GET', function() {
		//初始化会话页面组件
			formInit(1);
		_form.form('load',row);
	});
}

//社保 弹出录入页面
function shebao(){
	var editRoleUrl = baseUrl+"/shebao";
	var rows = _datagrid.datagrid('getSelections');
	var row = rows[0];
	var id=row.id;
	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
	    _dialog = app.openFormDialog(function(){
	    	_form.submit();//提交表单
	 	}, 'SHB',750, 400,editRoleUrl+"/"+id,'GET', function() {
		//初始化会话页面组件
			formInit(2);
			_form.form('load',row);
	});
}


//快递 弹出录入页面
function kuaidi(){
	var editRoleUrl = baseUrl+"/kuaidi";
	var rows = _datagrid.datagrid('getSelections');
	var row = rows[0];
	var id=row.id;
	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
	    _dialog = app.openFormDialog(function(){
	    	_form.submit();//提交表单
	 	}, 'KD',900, 500,editRoleUrl+"/"+id,'GET', function() {
		//初始化会话页面组件
			formInit(4);
			_form.form('load',row);
	});
}


//电信   弹出录入页面
function dianxin(){
	var editRoleUrl = baseUrl+"/dianxin";
	var rows = _datagrid.datagrid('getSelections');
	var row = rows[0];
	var id=row.id;
	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
	    _dialog = app.openFormDialog(function(){
	    	_form.submit();//提交表单
	 	}, 'YYS',820, 455,editRoleUrl+"/"+id,'GET', function() {
		//初始化会话页面组件
		formInit(3);
		_form.form('load',row);
	});
}

//初始化表单
function formInit(i) {
	var url;
	if(i==1){
		url="/saveAll";
	}else if(i==2){
		url="/saveshebao";
	}else if(i==3){
		url="/savedianxin";
	}else if(i==4){
		url="/savekuaidi";
	}
	_form = $('#_form').form({
		//表单提交地址
		url : baseUrl+url,
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
function bohuiIn() {
	_form = $('#_form').form({
		//表单提交地址
		url : baseUrl+"/bohui",
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
//导入查资
function upload() {

		var upUrl = baseUrl + '/upload' ;
		//弹出对话窗口
		upload_dialog = $('<div/>').dialog({
		
			title : '导入查资',top : 20,width : 700,height : 150,
			modal : true,maximizable : true,resizable : true,
			href : upUrl,method : 'GET',
			buttons : [ {
				text : '导入',
				handler : function() {
					$('#upload_form').form('submit');
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
					url : baseUrl + '/applyEexcelImport',
					//表单提交数据之前回调函数
					onSubmit : function(param) {
						/* if(!isExcel()){
							alert('e');
							return false;
						} */
						//获取之前的查询条件信息
						var queryParams = _datagrid.datagrid("options").queryParams;
						for(var name in queryParams){
							var val = queryParams[name];
							// $('#upload_form').datagrid('getData'); 
							if(undefined != val &&val!=null &&val.trim()!=''){
								param[name] = queryParams[name];
							}
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
