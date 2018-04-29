<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx + "/sys/casetemplate";
$(function() {
	_search_form = $('#_search_form').form();
	//数据列表
	  
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
					 {field : 'ck',checkbox : true},
					 {field:'state',title:'启用/禁用',width:100,
							formatter: function(value, row, index) { 
								if(value==0){
									return "停用";
								}else if(value==1){
									return "启用";
								}	
							}
					 },
                     {field:'name',title:'案件模板',width:100},
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
                  {
                      text: '删除',
                      iconCls: 'eu-icon-shanchu',
                      handler: function () {
                    	  del()
                      }
                  },
                  '-',
                  {
                      text: '启用',
                      iconCls: 'eu-icon-qiyong',
                      handler: function () {
                    	  start()
                      }
                  },
                  '-',
                  {
                      text: '停用',
                      iconCls: 'eu-icon-tingyong',
                      handler: function () {
                    	  stop()
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
});

//案件启用
function start(){
	 var rows = _datagrid.datagrid('getSelections');
	    var row = rows[0];
	   	if(rows.length == 0){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    	}else{
	    		$.messager.confirm('确认提示！',
						'您确定要启用所选模板吗？', function(r) {
	    			if(r){
	    		   delete row['createTime'];
	     		   delete row['modifyTime'];
	     		  var ids = new Array();
	              $.each(rows, function (i, row) {
	                	ids[i] = row.id;
	                });
	               $.post(baseUrl + '/updateForStart', {ids:ids},function(date){
	               	//渲染结果
	                   app.renderAjax(date,function(json){
	                   	app.load(_datagrid);
	                   },false);
	               },'json');
	    			}
	    		});
	    	}
	}
	
//案件停用
function stop(){
	 var rows = _datagrid.datagrid('getSelections');
	    var row = rows[0];
	   	if(rows.length == 0){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    	}else{
	    		$.messager.confirm('确认提示！',
						'您确定要停用所选模板吗？', function(r) {
	    			if(r){
	    		   delete row['createTime'];
	     		   delete row['modifyTime'];
	     		  var ids = new Array();
	              $.each(rows, function (i, row) {
	                	ids[i] = row.id;
	                });
	               $.post(baseUrl + '/updateForStop', {ids:ids},function(date){
	               	//渲染结果
	                   app.renderAjax(date,function(json){
	                   	app.load(_datagrid);
	                   },false);
	               },'json');
	    			}
	    		});
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
     	   	}, '编辑案件模板',630, 390,editRoleUrl+"/"+id, 'GET',function(){
     	   	formInit(num,1);
     	   	});
  	}
}

//删除
function del(rowIndex){
	 var rows = _datagrid.datagrid('getSelections');
	    var row = rows[0];
	   	if(rows.length == 0){
	    		eu.showAlertMsg("请选择要操作的数据！",'warning');
	    	}else{
	    		$.messager.confirm('确认提示！',
						'您确定要删除所选模板吗？', function(r) {
	    		   delete row['createTime'];
	     		   delete row['modifyTime'];
	     		  var ids = new Array();
	              $.each(rows, function (i, row) {
	                	ids[i] = row.id;
	                });
	               $.post(baseUrl + '/deleteAll', {ids:ids},function(date){
	               	//渲染结果
	                   app.renderAjax(date,function(json){
	                   	app.load(_datagrid);
	                   },false);
	               },'json');
	    		});
	    	}
	}

function showDialog(row) {
	var num=0;//新增标示
    var inputUrl = baseUrl+"/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "/" + row.id;
    }

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, '新添案件模板', 630, 390, inputUrl, 'GET', function(){
   		//初始化会话页面组件
   		formInit(num,1);
        if(row){
          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
          	  row.birthday = $.formatDate(row.birthday,'yyyy-MM-dd');
          	  _form.form('load', row);
        }
   	});
}

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
            	app.reload(_datagrid);//重新加载列表数据
            });
        }
    });
}


//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
