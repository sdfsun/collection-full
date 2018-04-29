<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _treegrid;
var _form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/resource";
$(function() {
    //数据列表
    _treegrid = $('#_treegrid').treegrid({
        url:baseUrl+'/treegrid',
        fit:true,
        fitColumns:false,//自适应列宽
        striped:true,//显示条纹
        singleSelect:false,//单选模式
        rownumbers:true,//显示行数
        nowrap : false,
        border : false,
        singleSelect:true,
        remoteSort:false,//是否通过远程服务器对数据排序
        striped:true,
        idField : 'id',
        treeField:"name",
        frozenColumns:[[
            {field:'name',title:'资源名称',width:200},
            {field:'code',title:'资源编码',width:120}
        ]],
        columns:[[
            {field:'url',title:'链接地址',width:260},
            {field:'orderNo',title:'排序',align:'center',width:60,sortable:true},
            {field:'type',title:'资源类型',align:'center',width:100,
            	formatter:function(value, row, index){
            		return app.dictName('RESOURCE_TYPE',value)
            	}	
            },
            {field:'status',title:'状态',align:'center',width:60},
            {field:'createTime',title:'创建时间',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            }
        ]],
        toolbar:'#_toolbar',
        //每列数据右键
        onContextMenu : function(e, row) {
            e.preventDefault();
            $(this).treegrid('select', row.id);
            $('#_menu').menu('show', {
                left : e.pageX,
                top : e.pageY
            });
        },
        //每列数据双击事件
        onDblClickRow:function(row){
            edit(row);
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
                _treegrid.treegrid('reload');//重新加载列表数据
            });
        }
    });
}

//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row){
    var inputUrl = baseUrl+"/input";
    if(row != undefined && row.id){
        inputUrl = inputUrl+"/"+row.id;
    }else{
        var selectedNode = _treegrid.treegrid('getSelected');
        if(selectedNode != undefined && selectedNode.id != undefined){
            inputUrl +="?parentId="+selectedNode.id;
        }
    }

   	//弹出对话窗口
   	_dialog = app.openFormDialog(function(){
   		_form.submit();
   	}, '资源详细信息', 500, 450, inputUrl, 'GET', function(){
   		//初始化会话页面组件,表单,图表,资源类型下拉
   		formInit();
   		loadIco();
        if(row){
     		  loadParent(row.id);
         	  row._method = "PUT";//修改表单提交方式为PUT,修改请求
     		  _form.form('load', row);
        } else{
         setSortValue();//设置排序
         loadParent();
            var selectedNode = _treegrid.treegrid('getSelected');
            if(selectedNode){
                var initFormData = {'parentId':[selectedNode.id]};
                _form.form('load',initFormData );
            }
        }
   	});

}

//编辑
function edit(row) {
    if (row == undefined) {
        row = _treegrid.treegrid('getSelected');
    }
    if (row != undefined) {
        showDialog(row);
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

//删除
function del(rowIndex){
    var row;
    if (rowIndex == undefined) {
        row = _treegrid.treegrid('getSelected');
    }
    if (row != undefined) {
        $.messager.confirm('确认提示！','您确定要删除(如果存在子节点，子节点也一起会被删除)？',function(r){
            if (r){
                $.post(baseUrl+'/'+row.id,{'_method':'DELETE'},function(data){
                	//渲染结果
                    app.renderAjax(data,function(json){
                   	  	_treegrid.treegrid('unselectAll');//取消选择 1.3.6bug
                        _treegrid.treegrid('load');	// reload the user data
                    });
                },'json');

            }
        });
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}
//设置排序默认值
function setSortValue() {
    $.get(baseUrl+'/maxSort', function(data) {
        if (data.code == 1) {
            $('#orderNo').numberspinner('setValue',data.obj+1);
        }
    }, 'json');
}
//加载父级资源
function loadParent(parentId){
	var url = baseUrl+'/parentResource?selectType=select';
	if(parentId){
		url = url+"&parentId="+parentId;
	}
	$('#parentId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
        valueField:'id'

	});
}
//加载资源图标
function loadIco(){
	$('#iconCls').combobox({
        method:'GET',
		url:ctxStatic+'/js/json/resource.json',
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
        formatter:function(row){    
        	return $.formatString('<span class="tree-icon tree-file {0}"></span>{1}', row.value, row.text);
        }
	});
}
</script>
