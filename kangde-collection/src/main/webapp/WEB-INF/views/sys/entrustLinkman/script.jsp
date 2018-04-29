<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/entrustLinkman";

$(function () {
	loadEntrust('#entrustName1','all');
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
        idField: 'id',
      
        columns:[[
			{field : 'ck', checkbox : true },
 			{field:'entrustName',title:'委托方',width:150},
 			{field:'caseSourceName',title:'案源地',width:150},
 			{field:'caseTypeName',title:'案件类型',width:150},
 			{field:'handleName',title:'手次',width:150},
 			
 			{field:'name',title:'客户联系人',width:150},
 			{field:'phone',title:'电话',width:150},
 			{field:'email',title:'邮箱',width:150},
 			{field:'qq',title:'QQ',width:100},
 			{field:'wechat',title:'微信',width:100},
 			{field:'createTime',title:'添加日期',width:100,
 				formatter:function(value, row, index){
             		if(value){
             			return $.formatDate(value,'yyyy-MM-dd');
             		}
             		return value;
             	}	
 			},
 			
 			{field:'modifyTime',title:'修改日期',width:100,
 				formatter:function(value, row, index){
             		if(value){
             			return $.formatDate(value,'yyyy-MM-dd');
             		}
             		return value;
             	}
 			},
        ]],
        toolbar: [
			<shiro:hasPermission name="entrustLinkman:save">
             {
                text: '新增',
                iconCls: 'eu-icon-xinzeng',
                handler: function () {
                    showDialog()
                }
            },
            '-',
            </shiro:hasPermission> 
            <shiro:hasPermission name="entrustLinkman:update">
            {
                text: '编辑',
                iconCls: 'eu-icon-bianji',
                handler: function () {
                    edit()
                }
            },
            '-',
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
//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row) {
    var inputUrl = baseUrl+"/input";
    if (row != undefined && row.id) {
    	inputUrl = inputUrl + "/" + row.id;
    }

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, '联系人管理', 600, 250, inputUrl, 'GET', function(){
   		//初始化会话页面组件
   		formInit();
   		if(row){
   		 row._method = "PUT";//修改表单提交方式为PUT,修改请求
   			_form.form('load', row);
   		}
   	});
}

//编辑
function edit() {
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
	        showDialog(row);
        }
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}


function loadEntrust(domId,type){
	var entrustNameValue;
	var url = ctx+'/sys/entrust/combobox?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text',
        onSelect : function(){
           entrustNameValue=$('#entrustName1').combobox('getValue');
           completeBox(entrustNameValue);
            }
	});
	
	function completeBox(entrustNameValue){
		var url1 = ctx+'/sys/entrustLinkman/combobox?entrustNameValue='+entrustNameValue;
		$($('#entrustProductId1')).combobox({
	        url:url1,
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    width:140,
		    height:22,
		    valueField:'value',
	        textField:'text',
		});
	}
} 

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
