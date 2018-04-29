<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagridArea;
var _formArea;
var _search_form;
var _dialogArea;
//请求基础路径
var baseUrlArea = ctx+"/sys/sysarea";

$(function () {
	_formArea = $('#_formArea').form();
    _search_form = $('#_search_form').form();
    //数据列表
    _datagridArea = $('#_datagridArea').datagrid({
        url: baseUrlArea + '/query',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'areaId',
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
						{field:'provinceModel',title:'省份名称',width:100,
							 formatter : function(value, row, index) {
		    						if (value) {
		    							return value.provinceName;
		    						}
		    						return value;
		    					}},
						{field:'cityModel',title:'城市名称',width:100,
							formatter : function(value, row, index) {
	    						if (value) {
	    							return value.cityName;
	    						}
	    						return value;
	    					}},
						{field:'areaName',title:'县名称',width:100}
        ]],
        toolbar: [
                  {
                      text: '新增',
                      iconCls: 'eu-icon-xinzeng',
                      handler: function () {
                          showDialogArea()
                      }
                  },
                  '-',
                  {
                      text: '编辑',
                      iconCls: 'eu-icon-bianji',
                      handler: function () {
                    	  editArea()
                      }
                  },
                  '-',
                  {
                      text: '删除',
                      iconCls: 'eu-icon-shanchu',
                      handler: function () {
                          delArea()
                      }
                  },
                  {
                      text: '启用/禁用',
                      iconCls: 'eu-icon-qiyongrotingyong',
                      handler: function () {
                    	  startArea()
                      }
                  }
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
	_formArea = $('#_formArea').form({
    	//表单提交地址
        url: baseUrlArea,
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
            	_dialogArea.dialog('destroy');//销毁对话框
            	app.reload(_datagridArea);//重新加载列表数据
            });
        }
    });
}

//县启用/禁用
function startArea(){
	//选中的所有行
    var rows = _datagridArea.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		   delete row['createTime'];
     		   delete row['modifyTime'];
               $.post(baseUrlArea + '/updateForStatus', row,function(date){
               	//渲染结果
                   app.renderAjax(date,function(json){
                   	app.load(_datagridArea);
                   },false);
               },'json');
    	}
}

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialogArea(row) {
	  var inputUrl = ctx+"/sys/sysarea/inputArea";
	   	_dialogArea = app.openFormDialog(function(){
	   		_formArea.submit();//提交表单
	   	}, '外访区域-县管理', 350, 155, inputUrl, 'GET', function(){
	   		loadEntrustArea('#search_cityId','select');
	   		//初始化会话页面组件
	   	 	_formArea = $('#_formArea').form({
	   	    	//表单提交地址
	   	        url:baseUrlArea+'/save',
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
	   	            	_dialogArea.dialog('destroy');//销毁对话框
	   	            	app.reload(_datagridArea);//重新加载列表数据
	   	            });
	   	        }
	   	    });
	   		//初始化会话页面组件
	        if(row){
	          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
	          	  _formArea.form('load', row);
	        }
	   	});
	}
	
//编辑
function editArea(rowIndex, rowData) {
	 var inputUrl = baseUrlArea+"/inputArea";
    //响应双击事件
    if (rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
    //选中的所有行
    var rows = _datagridArea.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = _datagridArea.datagrid('getSelected');
    if (row) {
    	//多行校验
        if (rows.length > 1) {
            row = rows[rows.length - 1];
            eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
        }else{
        	        inputUrl = inputUrl + "/" + row.areaId;
        	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
        	   	_dialogArea = app.openFormDialog(function(){
        	   		_formArea.submit();//提交表单
        	   	}, '外访区域-县管理-编辑', 350, 155, inputUrl, 'GET', function(){
        	   		loadEntrustArea('#search_cityId','select');
        	   	  _formArea = $('#_formArea').form({
        	   	    	//表单提交地址
        	   	        url:baseUrlArea+'/update',
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
        	   	            	_dialogArea.dialog('destroy');//销毁对话框
        	   	            	app.reload(_datagridArea);//重新加载列表数据
        	   	            });
        	   	        }
        	   	    });
        	   		//初始化会话页面组件
        	        if(row){
        	          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
        	          	  _formArea.form('load', row);
        	        }
        	   	});
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

//删除
function delArea(rowIndex){
  var row;
  if (rowIndex == undefined) {
      row = _datagridArea.datagrid('getSelected');
  }
  if (row != undefined) {
      $.messager.confirm('确认提示！','您确定要删除？',function(r){
          if (r){
              $.post(baseUrlArea+'/'+row.areaId,{'_method':'DELETE'},function(data){
              	//渲染结果
                  app.renderAjax(data,function(json){
                  	app.load(_datagridArea);
                  });
              },'json');

          }
      });
  } else {
  	eu.showAlertMsg("请选择要操作的数据！",'warning');
  }
}
	
//省份下拉 
function loadEntrustArea(domId,type){
	var url = ctx+'/sys/city/combobox?selectType='+type;
	$(domId).combobox({
      url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    width:150,
	    valueField:'value',
      textField:'text'
	});
} 
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagridArea,$.serializeObject(_search_form));
}
</script>
