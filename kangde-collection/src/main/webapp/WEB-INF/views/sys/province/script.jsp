<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/province";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/query',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'provinceId',
        frozenColumns: [
                        [
							{field : 'ck',checkbox : true},
                        ]
                    ],
      
        columns:[[
                  {field:'status',title:'启用/禁用',width:100,
 						formatter: function(value, row, index) { 
 							if(value==1){
 								return "启用";
 							}else if(value==0){
 								return "禁用";
 							}	
 						}
 				 },
 				   {field:'provinceName',title:'省份名称',width:100}
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
                  {
                      text: '启用/禁用',
                      iconCls: 'eu-icon-qiyongrotingyong',
                      handler: function () {
                    	  start()
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
//省份启用/禁用
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
function showDialog() {
    var inputUrl = baseUrl+"/input";
    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, '外访区域-新添省份', 350, 120, inputUrl, 'GET', function(){
   	    _form = $('#_form').form({
   	    	//表单提交地址
   	        url:baseUrl+'/save',
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
   		//初始化会话页面组件
        if(row){
          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
          	  _form.form('load', row);
        }
   	});
}
//编辑
function edit(rowIndex, rowData) {
	 var inputUrl = baseUrl+"/input";
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
        	        inputUrl = inputUrl + "/" + row.provinceId;
        	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
        	   	_dialog = app.openFormDialog(function(){
        	   		_form.submit();//提交表单
        	   	}, '外访区域-编辑省份', 350, 120, inputUrl, 'GET', function(){
        	   	  _form = $('#_form').form({
        	   	    	//表单提交地址
        	   	        url:baseUrl+'/update',
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
        	   		//初始化会话页面组件
        	        if(row){
        	          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
        	          	  _form.form('load', row);
        	        }
        	   	});
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

//删除
function del(rowIndex){
    var row;
    if (rowIndex == undefined) {
        row = _datagrid.datagrid('getSelected');
    }
    if (row != undefined) {
        $.messager.confirm('确认提示！','您确定要删除该省份['+row.provinceName+']吗?',function(r){
            if (r){
                $.post(baseUrl+'/'+row.provinceId,{'_method':'DELETE'},function(data){
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
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
