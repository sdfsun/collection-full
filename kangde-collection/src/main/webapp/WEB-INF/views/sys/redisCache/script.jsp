<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/redisCache";
$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/list',
        fit: true,
        method:'GET',
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        border : false,
        idField: 'id',
        frozenColumns: [
            [
                {field: 'ck', checkbox: true},
                {field: 'key', title: '缓存名称', width: 200},
            ]
        ],
        toolbar: [
            {
                text: '清空选中缓存',
                iconCls: 'easyui-icon-shanchu',
                handler: function () {
                    clearCache()
                }
            },'-',{
                text:'清空所有缓存',
                iconCls: 'easyui-icon-shanchu',
                handler:function(){
                	flushAll();
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

function clearCache() {
	app.dataGridSelect(_datagrid,function(rows){
		$.messager.confirm('确认提示！', '您确定要清空选中的缓存吗？', function (r) {
            if (r) {
                var keys = new Array();
                $.each(rows, function (i, row) {
                	keys[i] = row.key;
                });
                $.ajax({
                    url: baseUrl+"/clearCache",
                    type: 'post',
                    data: {'keys[]': keys},
                    traditional: true,
                    dataType: 'json',
                    success: function (data) {
                    	//渲染结果
                        app.renderAjax(data,function(json){
                        	 _datagrid.datagrid('load');
                        });
                    }
                });
            }
        });
	})
    
}
//清空Redis
function flushAll() {
    $.messager.confirm('确认提示！', '您确定要清空所有的缓存吗？', function (r) {
        if (r) {
            $.ajax({
                url: baseUrl+"/flushAll",
                type: 'post',
                dataType: 'json',
                success: function (data) {
                	//渲染结果
                    app.renderAjax(data,function(json){
                    	 _datagrid.datagrid('load');
                    });
                }
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
