<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/userLog";
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
            {field:'type',title:'日志类型',width:100},
        ]],
        columns:[[
            {field:'createTime',title:'操作时间',align:'left',width:150,
            	formatter:function(value, row, index){
            		if(value){
            			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
            		}
            		return value;
            	}
            },
            {field:'loginName',title:'用户登录名称',width:120},
            {field:'userName',title:'用户中文名称',width:120},
            {field:'ipAddr',title:'用户IP地址',width:120},
            {field:'operateContent',title:'操作内容',width:450}
        ]],
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
        }
    }).datagrid('showTooltip');

});

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
