<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/sys/role";
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
        toolbar:'#_toolbar',
        onLoadSuccess: function () {
        	$(this).datagrid('showTooltip').datagrid('columnMoving');
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
            var nodes1 = $('#resourceIds').tree('getChecked', 'indeterminate');
            var nodes2 = $('#resourceIds').tree('getChecked');
            var resourceIds = [];
            $.merge( nodes1, nodes2);
            for(var i=0;i<nodes1.length;i++){
            	resourceIds.push(nodes1[i].id);
            }
            param.resourceIds = resourceIds;
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

    //弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
   	_dialog = app.openFormDialog(function(){
   		_form.submit();//提交表单
   	}, '角色详细信息',800, 500,inputUrl, 'GET', function(){
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


//删除
function del() {
    var rows = _datagrid.datagrid('getSelections');
    if (rows.length > 0) {
        $.messager.confirm('确认提示！', '您确定要删除选中的所有行？', function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows, function (i, row) {
                    ids[i] = row.id;
                });
                $.ajax({
                    url: baseUrl,
                    type: 'post',
                    //发生delete请求
                    data: {'ids': ids,'_method':'DELETE'},
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
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
}

//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
