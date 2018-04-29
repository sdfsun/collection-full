<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _treegrid;
var _form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/organization";
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
        idField : 'id',
        treeField:"name",
        frozenColumns:[[
            {field:'name',title:'机构名称',width:200},
            {field:'type',title:'机构类型',width:120,
            	formatter:function(value, row, index){
            		return app.dictName('ORG_TYPE',value)
            	}	
            },
            {field:'code',title:'机构编码',width:120}
        ]],
        columns:[[
            {field:'principalName',title:'负责人',align:'left',width:120},
            {field:'areaNames',title:'业务覆盖区域',align:'left',width:185},
            {field:'address',title:'地址',align:'center',width:60},
            {field:'phone',title:'电话号码',align:'center',width:60},
            {field:'postCode',title:'邮政编码',align:'center',width:60},
            {field:'fax',title:'传真号',align:'center',width:60},
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
   	}, '机构详细信息', 500, 450, inputUrl, 'GET', function(){
   		//初始化会话页面组件,表单,图表,机构类型下拉
   		formInit();
        if(row){
        	  if(row.type=='2'){
        		  $('#areaDiv').show();
        	  }
     		  loadParent(row.id);
			  var temp = row.areaIds;
     		  delete row['areaIds'];
			  var vals;
 	   	  	  loadAreas(temp);
     		  loadUser(row.principalId);
     		  loadOrgType(row);
     		  if(temp){
     			 vals = temp.split(",");
			  }
     		  _form.form('load', row);
     		 if(vals){
	     		  $('#areaIds').combobox('setValues',vals);
	     		 row.areaIds = temp;
     		 }
        } else{
         setSortValue();//设置排序
         loadParent();
         loadUser();
         loadAreas();
         loadOrgType();
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
        $.messager.confirm('确认提示！','您确定删除该机构吗？删除后其子机构将被一起删除。',function(r){
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
//加载父级机构
function loadParent(parentId){
	var url = baseUrl+'/parentOrganization?selectType=select';
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
//加载区域
function loadAreas(areaIds){
	var url = ctx+'/collection/area/combobox';
	if(areaIds){
		url += "?incluedeIds="+areaIds;
	}
	$('#areaIds').combobox({
        url:url,
	    multiple:true,//是否可多选
	    editable:false,//是否可编辑
	    valueField:'value',
        textField:'text'
	});
}
//加载机构类型
function loadOrgType(row){
	var type = null;
	if(row){
		type = row.type;
	}
	var url = ctx+'/sys/dictionary/combobox?path=SYS_CONFIG/DEPT_CONFIG/ORG_TYPE';
	var dicts;
	$.ajax({
        url: url,
        dataType: "json",
        async: false,
        success: function(data){
        	dicts = data;
        }
    });
	$('#orgType').combobox({
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    required:true,
	    valueField:'value',
        textField:'text',
        value:type,
        data:dicts,
        onChange:function(newValue, oldValue){
        	if(newValue=='2'){
        		$('#areaDiv').show();
        		$('#areaIds').combobox({required:true});
        	}else{
        		$('#areaDiv').hide();
        		$('#areaIds').combobox({required:false,value:null});
        	}
        }
	});
}

//加载用户
function loadUser(userId){
/* 	var isChange = false; //标识所属组织机构是否变更
	var url = ctx+'/sys/employeeInfo/combogrid';
	if(userId){
		url+='?incluedeId='+userId;
	}
	$('#principalId').combogrid({    
	    mode: 'remote',    
	    url: url,    
	    idField: 'id',
	    async:false,
	    textField: 'userName',
	    editable:false,
	    required:false,
	    columns: [[    
	        {field:'userName',title:'姓名',width:100},    
	        {field:'loginName',title:'登录名',width:100}    
	    ]]
	}); */
	
	$('#principalId').combobox({
		url : ctx + '/sys/employeeInfo/orgusersByOrg?orgId=${CURRENT_USER.orgId}',
		required:true,
		editable : false,//是否可编辑
		width : 200,
		valueField : 'value',
		textField : 'text',
		editable : true,
		delay : 0,
		separator : ',',
		filter: function(q, row){  
			    var opts = $(this).combobox('options');  
			    return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配  
		}, 
	});

}

</script>
