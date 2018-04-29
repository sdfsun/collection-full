<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/timerTaskLog";
$(function () {
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
            {field:'taskName',title:'调度任务名称',width:100},
            {field:'state',title:'调度状态',width:100,align:'center',
            	formatter:function(value, row, index){
            		return value==1?'成功':'失败';
            	}
            }
        ]],
        columns:[[
            {field:'startTime',title:'本次调度时间',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            },
            {field:'previousFireTime',title:'调度上一次触发时间 ',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            },
            {field:'nextFireTime',title:'调度下一次触发时间 ',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            },
            {field:'serverHostName',title:'服务主机名称',width:160},
            {field:'serverHostAddr',title:'服务主机地址',width:160},
            {field:'timeConsuming',title:'调度耗时ms',align:'left',width:150,
            	formatter:function(value, row, index){
            		return value+"ms";
            	}
            },
            {field:'createTime',title:'日志创建时间',align:'left',width:150,
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
                text: '查看异常信息',
                iconCls: 'eu-icon-chakanyichan',
                handler: function () {
                    show()
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
            show(rowIndex, rowData);
        }
    }).datagrid('showTooltip');

});

//详情
function show(rowIndex, rowData) {
    //响应双击事件
    if (rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
    app.dataGridSelectOne(_datagrid, function(row){
    	showDialog(row);
    });
}

function showDialog(row){
	if(row.state==1){
		eu.showAlertMsg("您选中的【"+row.taskName+"】调度为正常状态,没有可显示的异常信息",'warning');
	}else{
		_dialog = app.openViewDialog("异常信息详情",500, 450,baseUrl+"/show/"+row.id, 'GET', function(){});
	}
}

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
