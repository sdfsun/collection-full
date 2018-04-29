<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/timerTask";
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
        idField: 'id',
        frozenColumns:[[
			{field: 'ck', checkbox: true},
            {field:'name',title:'作业名称',width:100},
            {field:'state',title:'作业状态',width:100,align:'center',
            	formatter:function(value, row, index){
            		return value==1?'启用':'暂停';
            	}
            }
        ]],
        columns:[[
            {field:'description',title:'作业描述',width:60},
            {field:'errorEmail',title:'当调度发生错误时通知的邮件地址',width:260},
            {field:'params',title:'参数',width:120},
            {field:'jobClass',title:'作业类',width:120},
            {field:'cronExpression',title:'cron表达式',width:120},
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
                text: '立即调度一次',
                iconCls: 'eu-icon-liji',
                handler: function () {
                	runJob()
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
//表单初始化
function formInit() {
    _form = $('#_form').form({
        url: baseUrl,
        onSubmit: function (param) {
            $.messager.progress({
                title: '提示信息！',
                text: '数据处理中，请稍后....'
            });
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

   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, "调度信息",500, 480,inputUrl, 'GET', function(){
   		//初始化会话页面组件
   		formInit();
        if(row){
    		_form.form('load', row);
        }
   	});
}

//编辑
function edit(rowIndex, rowData) {
    //响应双击事件
    if (rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
    app.dataGridSelectOne(_datagrid, function(row){
		showDialog(row);
    });
}


//删除
function del() {
	app.dataGridSelectOne(_datagrid, function(row){
		$.messager.confirm('确认提示！','您确定要删除【'+row.name+'】么？', function (r) {
			if(r){
				$.ajax({
		            url: baseUrl+"/"+row.id,
		            type: 'post',
		            //发生delete请求
		            data: {'_method':'DELETE'},
		            dataType: 'json',
		            success: function (data) {
		            	//渲染结果
		                app.renderAjax(data,function(json){
		                	 _datagrid.datagrid('load');
		                });
		            },
		            error:function(XMLHttpRequest, textStatus, errorThrown){
		            	//渲染结果
		                app.renderAjax(XMLHttpRequest.responseText,function(json){
		                });
		            }
		        });
			}
		});
		
    });
}

//立即执行一次选中的任务
function runJob(){
	app.dataGridSelectOne(_datagrid, function(row){
		$.messager.confirm('确认提示！','您确定要立即调度一次【'+row.name+'】么？', function (r) {
			if(r){
				delete row['createTime'];
				delete row['modifyTime'];
				$.ajax({
		            url: baseUrl+"/runJob",
		            type: 'post',
		            //发生delete请求
		            data: row,
		            dataType: 'json',
		            success: function (data) {
		            	//渲染结果
		                app.renderAjax(data,function(json){
		                	app.unCheckAll(_datagrid);
		                });
		            },
		            error:function(XMLHttpRequest, textStatus, errorThrown){
		            	//渲染结果
		                app.renderAjax(XMLHttpRequest.responseText,function(json){
		                });
		            }
		        });
			}
		});
	});
}

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
