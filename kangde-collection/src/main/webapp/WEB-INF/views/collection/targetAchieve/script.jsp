<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var cancel_form;
var add_form;
var _search_form;
var _dialog;
var cancel_dialog;
var add_dialog;
var paid_dialog;
var paid1_dialog;
var paid2_dialog;
//请求基础路径
var baseUrl = ctx+"/collection/targetAchieve";

$(function () {
	
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadOrgs();
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: '',
        fit: true,
        method:'GET',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        onClickCell: onClickCell,
       // onEndEdit: onEndEdit,
        singleSelect: true,
        idField: 'id',
        /* toolbar: [
            {
                text: '保存',
                iconCls: 'eu-icon-bianji',
                handler: function () {
                	accept()
                }
            }
        ], */
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
        },
        //编辑完成后调用
        onAfterEdit: function(rowIndex, rowData, changes){
        	accept();
        }
    }).datagrid('showTooltip');

  //  _datagrid.datagrid('showTooltip');
    
});
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#_datagrid').datagrid('validateRow', editIndex)){
        $('#_datagrid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field){
    if (editIndex != index){
        if (endEditing()){
            $('#_datagrid').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
            var ed = $('#_datagrid').datagrid('getEditor', {index:index,field:field});
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editIndex = index;
        } else {
            setTimeout(function(){
                $('#_datagrid').datagrid('selectRow', editIndex);
            },0);
        }
    }
}
/* function accept(){
	var a = $('#_datagrid').datagrid('getRowIndex',index);
	alert(a)
    if (endEditing()){
        $('#_datagrid').datagrid('acceptChanges');
    }
} */
function accept(rowIndex, field, value) {
	 var row = $('#_datagrid').datagrid('getSelected');
	  //var row = rows[index];
	 /*  accept1(); */
	  //getChanges();
	  var achieve = row.achieve;
	  var id = row.id;
	  var empName = row.empName; 
	  
		$.ajax({
	        url: baseUrl+"/create",
	        type: 'POST',
	        data: {id:id,empName:empName,achieve:achieve},
	        dataType: 'json',
	        success: function (data) {
	        /* 	app.load(_datagrid,$.serializeObject(_search_form)); */
	        	//渲染结果
	           /*  app.renderAjax(data,function(json){
	            	var obj=json.obj;
	            	//alert(obj.state);
	            	$('#total_paidMoney').html($.fmoney(obj.total_paidMoney));
	            	$('#total_achieve').html($.fmoney(obj.total_achieve));
	            	$('#total_already_money').html($.fmoney(obj.total_already_money));
	            	$('#total_cp_money').html($.fmoney(obj.total_cp_money));
	            },false); */
	        }
	    }); 
    
	/* 	$.extend($.fn.datagrid.defaults.editors, {      
		        getValue: function(target){ 
		        	var a = $(target).val();
		        	alert(a);
		            return a;    
		        }   
		             
		}); */ 
	}
	
function accept1(){
    if (endEditing()){
        $('#_datagrid').datagrid('acceptChanges');
    }
}
function getChanges(){
    var rows = $('#_datagrid').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
}
/* function onEndEdit(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'productid'
    });
    row.productname = $(ed.target).combobox('getText');
} */
function loadOrgs(){
	var url = ctx+'/sys/organization/parentOrganization';
	$('#orgId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:145,
        valueField:'id',
        value:'${CURRENT_USER.orgId}'
	});
}
//搜索
function search() {
	var options = _datagrid.datagrid('options');
	//url 需要自己定义
	options.url = baseUrl + "/query";
	//重新加载datagrid
	//$('#_datagrid').datagrid('reload'); 
	app.load(_datagrid, $.serializeObject(_search_form));
	statistics($.serializeObject(_search_form));
}

</script>
