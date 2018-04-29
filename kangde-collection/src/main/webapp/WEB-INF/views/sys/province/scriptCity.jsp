<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagridCity;
var _formCity;
var _search_form;
var _dialogCity;
//请求基础路径
var baseUrlCity = ctx+"/sys/city";

$(function () {
	_formCity = $('#_formCity').form();
    _search_form = $('#_search_form').form();
    //数据列表
    _datagridCity = $('#_datagridCity').datagrid({
        url: baseUrlCity + '/query',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:false,//是否通过远程服务器对数据排序
        idField: 'cityId',
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
				   {field:'cityName',title:'城市名称',width:100}
        ]],
        toolbar: [
                  {
                      text: '新增',
                      iconCls: 'eu-icon-xinzeng',
                      handler: function () {
                          showDialogCity()
                      }
                  },
                  '-',
                  {
                      text: '编辑',
                      iconCls: 'eu-icon-bianji',
                      handler: function () {
                    	  editCity()
                      }
                  },
                  '-',
                  {
                      text: '删除',
                      iconCls: 'eu-icon-shanchu',
                      handler: function () {
                          delCity()
                      }
                  },
                  {
                      text: '启用/禁用',
                      iconCls: 'eu-icon-qiyongrotingyong',
                      handler: function () {
                    	  startCity()
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
	_formCity = $('#_formCity').form({
    	//表单提交地址
        url: baseUrlCity,
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
            	_dialogCity.dialog('destroy');//销毁对话框
            	app.reload(_datagridCity);//重新加载列表数据
            });
        }
    });
}
//城市启用/禁用
function startCity(){
	//选中的所有行
    var rows = _datagridCity.datagrid('getSelections');
    var row = rows[0];
    //多行校验
    if (rows.length > 1) {
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');

    }else if(rows.length == 0){
    		eu.showAlertMsg("请选择要操作的数据！",'warning');
    	}else{
    		   delete row['createTime'];
     		   delete row['modifyTime'];
               $.post(baseUrlCity + '/updateForStatus', row,function(date){
               	//渲染结果
                   app.renderAjax(date,function(json){
                   	app.load(_datagridCity);
                   },false);
               },'json');
    	}
}

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialogCity(row) {
	  var inputUrl = ctx+"/sys/city/inputCity";
	   	_dialogCity = app.openFormDialog(function(){
	   		_formCity.submit();//提交表单
	   	}, '外访区域-城市管理', 350, 155, inputUrl, 'GET', function(){
	   		loadEntrust('#search_provinceId','select');
	   		//初始化会话页面组件
	   	 	_formCity = $('#_formCity').form({
	   	    	//表单提交地址
	   	        url:baseUrlCity+'/save',
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
	   	            	_dialogCity.dialog('destroy');//销毁对话框
	   	            	app.reload(_datagridCity);//重新加载列表数据
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
//删除
function delCity(rowIndex){
  var row;
  if (rowIndex == undefined) {
      row = _datagridCity.datagrid('getSelected');
  }
  if (row != undefined) {
      $.messager.confirm('确认提示！','您确定要删除['+row.cityName+']吗？',function(r){
          if (r){
              $.post(baseUrlCity+'/'+row.cityId,{'_method':'DELETE'},function(data){
              	//渲染结果
                  app.renderAjax(data,function(json){
                  	app.load(_datagridCity);
                  });
              },'json');

          }
      });
  } else {
  	eu.showAlertMsg("请选择要操作的数据！",'warning');
  }
}

//编辑
function editCity(rowIndex, rowData) {
	  var inputUrl = ctx+"/sys/city/inputCity";
    //响应双击事件
    if (rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
    //选中的所有行
    var rows = _datagridCity.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = _datagridCity.datagrid('getSelected');
    if (row) {
    	//多行校验
        if (rows.length > 1) {
            row = rows[rows.length - 1];
            eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
        }else{
        	        inputUrl = inputUrl + "/" + row.cityId;
        	    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
        	   	_dialogCity = app.openFormDialog(function(){
        	   		_formCity.submit();//提交表单
        	   	}, '外访区域-城市管理-编辑', 350, 155, inputUrl, 'GET', function(){
        	   		loadEntrust('#search_provinceId','select');
        	   	  _formCity = $('#_formCity').form({
        	   	    	//表单提交地址
        	   	        url:baseUrlCity+'/update',
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
        	   	            	_dialogCity.dialog('destroy');//销毁对话框
        	   	            	app.reload(_datagridCity);//重新加载列表数据
        	   	            });
        	   	        }
        	   	    });
        	   		//初始化会话页面组件
        	        if(row){
        	          	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
        	          	  _formCity.form('load', row);
        	        }
        	   	});
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}


//省份下拉 
function loadEntrust(domId,type){
	var url = ctx+'/sys/province/combobox?selectType='+type;
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
	app.load(_datagridCity,$.serializeObject(_search_form));
}
</script>
