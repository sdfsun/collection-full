<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
<%@ include file="/common/taglibs.jsp"%>
var _treegrid;
var _form;
var _dialog;
var dictionary_tree;
//请求基础路径
var baseUrl = ctx+"/sys/dictionary";
$(function() {
	//字典树
    var selectedNode = null;//存放被选中的节点对象 临时变量
    dictionary_tree = $("#dictionary_tree").tree({
        url : baseUrl+"/parentTree",
        onClick:function(node){
            search();
        },
        onContextMenu:function(e, node){
        	e.preventDefault();
        	dictionary_tree.tree('select',node.target);
        	search();
    		// 显示快捷菜单
    		$('#_menu').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
			
        },
        onLoadSuccess:function(node, data){
            $(this).tree("expandAll");
        }
    });
	
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
        idField : 'id',
        treeField:"name",
        frozenColumns:[[
            {field:'name',title:'字典名称',width:200},
            {field:'dictKey',title:'字典键',width:200},
            {field:'value',title:'字典值',width:120},
        ]],
        columns:[[
            {field:'remark',title:'备注',align:'center',width:200},
            {field:'path',title:'字典路径',align:'left',width:200,hidden:true},
            {field:'orderNo',title:'排序',align:'center',width:60,sortable:true,hidden:true},
            {field:'createTime',title:'创建时间',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            },
            {field:'modifyTime',title:'修改时间',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            }
        ]],
        toolbar:[
		<shiro:hasPermission name="dictionary:save">
        {
            text:'新增',
            iconCls:'eu-icon-xinzeng',
            handler:function(){showDialog()}
        },'-',
        </shiro:hasPermission>
        <shiro:hasPermission name="dictionary:update">
        {
            text:'编辑',
            iconCls:'eu-icon-bianji',
            handler:function(){edit()}
        },'-',
        </shiro:hasPermission>
        <shiro:hasPermission name="dictionary:delete">
        {
            text:'删除',
            iconCls:'eu-icon-shanchu',
            handler:function(){del()}
        }
        </shiro:hasPermission>
        ],
        //每列数据双击事件
        onDblClickRow:function(row){
            edit(row);
        }
    }).datagrid('showTooltip');

});

//删除树节点
function deltree(){
	var node = dictionary_tree.tree('getSelected');
	if(node){
		$.messager.confirm('确认提示！','您确定要删除(如果存在子节点，子节点也一起会被删除)？',function(r){
	        if (r){
	            $.post(baseUrl+'/'+node.id,{'_method':'DELETE'},function(data){
	            	//渲染结果
	                app.renderAjax(data,function(json){
	                	dictionary_tree.tree('reload');
	               	  	_treegrid.treegrid('unselectAll');//取消选择 1.3.6bug
	                    _treegrid.treegrid('load');	// reload the user data
	                });
	            },'json');

	        }
	    });
	}
}

//搜索
function search(){
    var node = dictionary_tree.tree('getSelected');
    var dictPath = '';
    if(node != null){
    	dictPath = node.attributes.path;
    }
    _treegrid.treegrid('load',{'dictPath':dictPath});
    _treegrid.treegrid("clearSelections");//取消所有的已选择项
}

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
            	dictionary_tree.tree('reload');
           	  	_treegrid.treegrid('unselectAll');//取消选择 1.3.6bug
                _treegrid.treegrid("clearSelections");//取消所有的已选择项
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
        }else{
        	 var selectedNode = dictionary_tree.tree('getSelected');
        	 if(selectedNode != undefined && selectedNode.id != undefined){
                 inputUrl +="?parentId="+selectedNode.id;
             }
        }
    }

   	//弹出对话窗口
   	_dialog = app.openFormDialog(function(){
   		_form.submit();
   	}, '字典详细信息', 500, 350, inputUrl, 'GET', function(){
   		//初始化会话页面组件,表单,图表,机构类型下拉
   		formInit();
        if(row){
        	  //不在从数据库检,直接获取页面数据
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
            }else{
            	 var selectedNode = dictionary_tree.tree('getSelected');
            	 if(selectedNode){
            		 var initFormData = {'parentId':[selectedNode.id]};
                     _form.form('load',initFormData );
                 }
            }
        }
   	});

}
//显示弹出窗口 新增：row为空 编辑:row有值
function showDialogTree(row){
    var inputUrl = baseUrl+"/input";
    if(row != undefined && row.id){
        inputUrl = inputUrl+"/"+row.id;
    }else{
        var selectedNode = dictionary_tree.tree('getSelected');
        if(selectedNode != undefined && selectedNode.id != undefined){
            inputUrl +="?parentId="+selectedNode.id;
        }
    }

   	//弹出对话窗口
   	_dialog = app.openFormDialog(function(){
   		_form.submit();
   	}, '字典详细信息', 500, 350, inputUrl, 'GET', function(){
   		//初始化会话页面组件,表单,图表,机构类型下拉
   		formInit();
        if(row){
     		 loadParent(row.id);
     		 $.ajax({
 		        url: baseUrl+"/"+row.id,
 		        type: 'GET',
 		        dataType: 'json',
 		        success: function (data) {
 		        	data._method = "PUT";//修改表单提交方式为PUT,修改请求
    	 		  	_form.form('load', data);
 		        }
 		    });
        } else{
         setSortValue();//设置排序
         loadParent();
         	var selectedNode = dictionary_tree.tree('getSelected');
            if(selectedNode){
                var initFormData = {'parentId':[selectedNode.id]};
                _form.form('load',initFormData );
            }
        }
   	});

}

//编辑
function editTree() {
	var node = dictionary_tree.tree('getSelected');
    showDialogTree(node);
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
                    	dictionary_tree.tree('reload');
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
//加载父级字典
function loadParent(parentId){
	var url = baseUrl+'/parentDictionary';
	if(parentId){
		url = url+"?parentId="+parentId;
	}
	$('#parentId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
        valueField:'id'

	});
}
</script>
