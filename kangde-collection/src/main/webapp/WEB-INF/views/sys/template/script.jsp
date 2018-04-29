<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
var _datagridcollection;
//请求基础路径
var baseUrl = ctx+"/sys/template";
var _dialogVisit;
var _visit_form;
var _datagridVisit;
//外访请求基础路径
var visitBaseUrl = ctx+'/sys/visitTemplate'
$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
//单机选中 执行查询事件    
    $('#tt').tabs({    
        border:false,    
        onSelect:function(title){    
            if(title=='催记模板'){
//-------------------------------------催记模板----------------
            	    //数据列表
            	    _datagridcollection = $('#_datagridcollection').datagrid({
            	        url: baseUrl + '/querycollection',
            	        fit: true,
            	        method:'GET',
            	        pagination: true,//底部分页
            	        rownumbers: true,//显示行数
            	        fitColumns: false,//自适应列宽
            	        striped: true,//显示条纹
            	        border : false,
            	        remoteSort:false,//是否通过远程服务器对数据排序
            	        idField: 'id',
            	        columns:[[
            					/* 	{ field: 'state', title: '是否启用', width: 70, 
            							formatter: function(value, row, index) { 
            								if (value==1) {
            									return '<input type="checkbox" checked="checked"/>'; 
            	         					}else {
            	         						return '<input type="checkbox"/>'; 
            	         					}
            						}}, */
            						 {field : 'ck',checkbox : true},
            						 {field:'state',title:'启用/禁用',width:100,
            								formatter: function(value, row, index) { 
            									if(value==0){
            										return "启用";
            									}else if(value==1){
            										return "禁用";
            									}	
            								}
            						 },
            	                     {field:'name',title:'案件模板',width:100},
            	       			 ]],
            	        toolbar: [
            	                  {
            	                      text: '启用/禁用',
            	                      iconCls: 'easyui-icon-add',
            	                      handler: function () {
            	                    	  startcollection()
            	                      }
            	                  },
            	                  '-',
            	                  {
            	                      text: '编辑',
            	                      iconCls: 'easyui-icon-add',
            	                      handler: function () {
            	                    	  editcollection()
            	                      }
            	                  },
            	                  '-',
            	                  {
            	                      text: '删除',
            	                      iconCls: 'easyui-icon-add',
            	                      handler: function () {
            	                    	  delcollection()
            	                      }
            	                  },
            	                  '-',
            	            {
            	                text: '新增模板',
            	                iconCls: 'easyui-icon-add',
            	                handler: function () {
            	                    showDialogcollection()
            	                }
            	            }
            	           
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
            	    
            }else if(title=='外访模板'){
            	 //-----------------------外访模板----------------------------------
                _datagridVisit = $('#_datagridVisit').datagrid({
                    url: visitBaseUrl + '/query',
                    fit: true,
                    method:'GET',
                    pagination: true,//底部分页
                    rownumbers: true,//显示行数
                    fitColumns: false,//自适应列宽
                    striped: true,//显示条纹
                    border : false,
                    remoteSort:false,//是否通过远程服务器对数据排序
                    idField: 'id',
                    columns:[[
            					 {field : 'ck',checkbox : true},
            					 {field:'state',title:'启用/禁用',width:100,
            							formatter: function(value, row, index) { 
            								if(value==0){
            									return "启用";
            								}else if(value==1){
            									return "禁用";
            								}	
            							}
            					 },
                                 {field:'name',title:'外访模板',width:100}
                   			 ]],
                    toolbar: [
                              {
                                  text: '启用/禁用',
                                  iconCls: 'easyui-icon-add',
                                  handler: function () {
                                	  changeVisitState()
                                  }
                              },
                              '-',
                              {
                                  text: '编辑',
                                  iconCls: 'easyui-icon-add',
                                  handler: function () {
                                	  editVisit()
                                  }
                              },
                              '-',
                              {
                                  text: '删除',
                                  iconCls: 'easyui-icon-add',
                                  handler: function () {
                                	  delVist()
                                  }
                              },
                              '-',
            	             {
            	                text: '新增模板',
            	                iconCls: 'easyui-icon-add',
            	                handler: function () {
            	                    addVist()
            	                }
            	             }
                       
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
                        //edit(rowIndex, rowData);
                    }
                }).datagrid('showTooltip');
            }
        }    
    });  
    
    
    
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/query',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        border : false,
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'id',
        columns:[[
				/* 	{ field: 'state', title: '是否启用', width: 70, 
						formatter: function(value, row, index) { 
							if (value==1) {
								return '<input type="checkbox" checked="checked"/>'; 
         					}else {
         						return '<input type="checkbox"/>'; 
         					}
					}}, */
					 {field : 'ck',checkbox : true},
					 {field:'state',title:'启用/禁用',width:100,
							formatter: function(value, row, index) { 
								if(value==0){
									return "启用";
								}else if(value==1){
									return "禁用";
								}	
							}
					 },
                     {field:'name',title:'案件模板',width:100},
       			 ]],
        toolbar: [
                  {
                      text: '启用/禁用',
                      iconCls: 'easyui-icon-add',
                      handler: function () {
                    	  start()
                      }
                  },
                  '-',
                  {
                      text: '编辑',
                      iconCls: 'easyui-icon-add',
                      handler: function () {
                    	  edit()
                      }
                  },
                  '-',
                  {
                      text: '删除',
                      iconCls: 'easyui-icon-add',
                      handler: function () {
                    	  del()
                      }
                  },
                  '-',
            {
                text: '新增模板',
                iconCls: 'easyui-icon-add',
                handler: function () {
                    showDialog()
                }
            }
           
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
        }/* ,
        onClickCell:function(rowIndex, field, value){
        	if('state'==field){
        		var rows = _datagrid.datagrid("getRows");
        		var row = rows[rowIndex];
        	  delete row['createTime'];
      		   delete row['modifyTime'];
                $.post(baseUrl + '/updateForStatus', row,function(date){
                	//渲染结果
                    app.renderAjax(date,function(json){
                    	app.load(_datagrid);
                    },false);
                },'json');
        	}
        } */
    }).datagrid('showTooltip');
    
});
//初始化表单
function formInit(num,i){
	var urlTemp;
	if(i==1){
		if(num==0){//新增
			urlTemp=baseUrl+'/saveTemplate';
		}else if(num==1){//修改
			urlTemp=baseUrl+'/updateTemplate';
		}
	}else if(i==2){
		if(num==0){//新增
			urlTemp=baseUrl+'/saveTemplatecollection';
		}else if(num==1){//修改
			urlTemp=baseUrl+'/updateTemplatecollection';
		}
	}
    _form = $('#_form').form({
    	//表单提交地址
       url : urlTemp,
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
            
            	app.reload(_datagridcollection);//重新加载列表数据
            	app.reload(_datagrid);//重新加载列表数据
            });
        }
    });
}


//外访启用/禁用
function changeVisitState(){
	//选中的所有行
    var rows = _datagridVisit.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		   delete row['createTime'];
     		   delete row['modifyTime'];
               $.post(visitBaseUrl + '/updateForStatus', row,function(date){
               	//渲染结果
                   app.renderAjax(date,function(json){
                   	app.load(_datagridVisit);
                   },false);
               },'json');
    	}
}

//案件启用/禁用
function start(){
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		   delete row['createTime'];
     		   delete row['modifyTime'];
               $.post(baseUrl + '/updateForStatus', row,function(date){
               	//渲染结果
                   app.renderAjax(date,function(json){
                   	app.load(_datagrid);
                   },false);
               },'json');
    	}
}

//催记启用/禁用
function startcollection(){
	//选中的所有行
    var rows = _datagridcollection.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		   delete row['createTime'];
     		   delete row['modifyTime'];
               $.post(baseUrl + '/updateForStatusCollection', row,function(date){
               	//渲染结果
                   app.renderAjax(date,function(json){
                   	app.load(_datagridcollection);
                   },false);
               },'json');
    	}
}

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row) {
	var num=0;//新增标示
    var inputUrl = baseUrl+"/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "/" + row.id;
    }

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, '新添案件模板', 550, 350, inputUrl, 'GET', function(){
   		//初始化会话页面组件
   		formInit(num,1);
        if(row){
          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
          	  row.birthday = $.formatDate(row.birthday,'yyyy-MM-dd');
          	  _form.form('load', row);
        }
   	});
}


//显示弹出窗口 新增：row为空 编辑:row有值
function showDialogcollection(row) {
	var num=0;//新增标示
  var inputUrl = baseUrl+"/inputcollection";
  if (row != undefined && row.id) {
      inputUrl = inputUrl + "/" + row.id;
  }

  //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
 	_dialog = app.openFormDialog(function(){
 		_form.submit();//提交表单
 	}, '新添催记模板', 550,350, inputUrl, 'GET', function(){
 		//初始化会话页面组件
 		formInit(num,2);
 		_form.form('load', row);
 	});
}

//删除
function del(rowIndex){
		//选中的所有行
	    var rows = _datagrid.datagrid('getSelections');
	    var row = rows[0];
	    var id=row.id;
	    //多行校验
	    if (rows.length > 1) {
	        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

	    }else if(rows.length == 0){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    	}else{
	    		 if (id != null) {
	    		        $.messager.confirm('确认提示！','您确定要删除该模板？',function(r){
	    		            if (r){
	    		                $.post(baseUrl+'/'+id,{'_method':'DELETE'},function(data){
	    		                	//渲染结果
	    		                    app.renderAjax(data,function(json){
	    		                    	app.load(_datagrid);
	    		                    });
	    		                },'json');

	    		            }
	    		        });
	    		    }
	    	}
	/* alert(id);
    if (id != null) {
        $.messager.confirm('确认提示！','您确定要删除该模板？',function(r){
            if (r){
                $.post(baseUrl+'/'+id,{'_method':'DELETE'},function(data){
                	//渲染结果
                    app.renderAjax(data,function(json){
                    	app.load(_datagrid);
                    });
                },'json');

            }
        });
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    } */
}


//删除
function delcollection(id){
		//选中的所有行
	    var rows = _datagridcollection.datagrid('getSelections');
	    var row = rows[0];
	    var id=row.id;
	    //多行校验
	    if (rows.length > 1) {
	        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

	    }else if(rows.length == 0){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    	}else{
	    		 if (id != null) {
	    		        $.messager.confirm('确认提示！','您确定要删除该模板？',function(r){
	    		            if (r){
	    		                $.post(baseUrl+'/'+id,{'_method':'DELETE'},function(data){
	    		                	//渲染结果
	    		                    app.renderAjax(data,function(json){
	    		                    	app.load(_datagridcollection);
	    		                    });
	    		                },'json');

	    		            }
	    		        });
	    		    } else {
	    		    	eu.showAlertMsg("请选择要操作的数据！",'warning');
	    		    }
	    	}
}

//编辑
function edit(){
	var num=1;//编辑标示
	//选中的所有行
    var rows = _datagrid.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    	    var id=row.id;
        	var editRoleUrl = baseUrl+"/editinput";
       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
       	    _dialog = app.openFormDialog(function(){
       	    	_form.submit();//提交表单
       	   	}, '编辑案件模板',550, 350,editRoleUrl+"/"+id, 'GET',function(){
       	   	formInit(num,1);
       	   	});
    	}
}
function editcollection(){
	var num=1;//编辑标示
	//选中的所有行
    var rows = _datagridcollection.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    	    var id=row.id;
        	var editRoleUrl = baseUrl+"/editinputcollection";
       	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
       	    _dialog = app.openFormDialog(function(){
       	    	_form.submit();//提交表单
       	   	}, '编辑催记模板',550, 350,editRoleUrl+"/"+id, 'GET',function(){
       	   	formInit(num,2);
       	   	});
    	}
}
//新增外访模板 
function addVist(){
	showVisitDialog();
}
//修改外访模板
function editVisit(rowIndex, rowData){
	 //响应双击事件
    if (rowIndex != undefined) {
    	showVisitDialog(rowData);
        return;
    }
    app.dataGridSelectOne(_datagridVisit, function(row){
    	showVisitDialog(row);
    });
}
//删除外访模板
function delVist(){
	app.dataGridSelect(_datagridVisit, function(rows){
		$.messager.confirm('提示信息','你确定要删除选中的所有数据么?',function(r){
			if(r){
				var ids = '';
		        $.each(rows, function (i, row) {
		        	ids+=row.id+',';
		        });
		        ids = ids.substring(0, ids.length-1);
		        $.ajax({
		            type: "POST",
		            url: visitBaseUrl,
		            data: {'_method':'DELETE','ids':ids},
		            dataType: "json",
		            success: function(data){
		            	app.renderAjax(data,function(){
		            		app.reload(_datagridVisit);
		            	});
		            }
		        });
			}
		});
	});
}
//显示弹出窗口 新增：row为空 编辑:row有值
function showVisitDialog(row) {
    var inputUrl = visitBaseUrl+"/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "/" + row.id;
    }
    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialogVisit = app.openMaxFormDialog(function(){
   		_visit_form.submit();//提交表单
   	}, '外访模板',inputUrl, 'GET', function(){
   		visitFormInit();		
		if (content_kindeditor) {
            content_kindeditor.sync();
        }
        /* if(row){
        	$.ajax({
                type: "GET",
                url: visitBaseUrl + '/'+row.id,
                dataType: "json",
                success: function(data){
	        		_visit_form.form('load', data);
                }
            });
        } */
   	});
}

//初始化init外访模板表单
function visitFormInit(){
	_visit_form = $('#_visit_form').form({
	 url:visitBaseUrl,
     onSubmit: function (param) {
         $.messager.progress({
             title: '提示信息！',
             text: '数据处理中，请稍后....'
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
     success: function (data) {
         $.messager.progress('close');
      	//渲染结果
         app.renderAjax(data,function(json){
        	 _dialogVisit.dialog('destroy');//销毁对话框
         	app.reload(_datagridVisit);//重新加载列表数据
         });
     }
 });
}

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
	app.load(_datagridcollection,$.serializeObject(_search_form));
}
</script>
