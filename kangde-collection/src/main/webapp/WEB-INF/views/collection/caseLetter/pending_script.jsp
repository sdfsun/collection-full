<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
var change_state_form;
var already_dialog;
//请求基础路径
var baseUrl = ctx+"/collection/caseLetter";
$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadOrgs();
    loadEntrust('#search_entrustId');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/query',
        fit: true,
        method:'GET',
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        queryParams:$.serializeObject(_search_form),
        frozenColumns : [ [ {field : 'id', checkbox : true,width:1} ] ],
        columns: [
            [
                {field: 'caseCode', title: '案件序列号', width: 180,sortable:true},
                {field: 'caseName', title: '姓名', width: 95},
                {field: 'caseNum', title: '证件号', width: 180,sortable:true},
                {field: 'address', title: '地址', width: 280},
                {field: 'caseMoney', title: '委案金额', width: 95,sortable:true,
                	formatter:function(value, row, index){
                		return $.fmoney(value); 
                	}
                },
                {field: 'paidNum', title: '已还款', width: 95,sortable:true,
                	formatter:function(value, row, index){
                		return $.fmoney(value); 
                	}
                },
                {field: 'orgName', title: '风控方', width: 95,sortable:true},
                {field: 'applyUserName', title: '申请人', width: 95,sortable:true,
                	formatter:function(value, row, index){
                		if(value){
                			return value;
                		}
                		return row.applyUserName2;
                	}	
                },
                {field:'appTime',title:'申请时间',align:'left',width:150,sortable:true,
                	formatter:function(value, row, index){
                		if(value){
                			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
                		}
                		return value;
                	}
                }
               
            ]
        ],
        toolbar: [
			<shiro:hasPermission name="caseLetter:export">
            {
                text: '导出信函',
                iconCls: 'eu-icon-daochusuoxuan',
                handler: function () {
                	exportVisitRecord();
                }
            },
            '-',
            </shiro:hasPermission>
			<shiro:hasPermission name="caseLetter:post">
            {
            	text: '邮寄信函',
                iconCls: 'eu-icon-butongyi',
                handler: function () {
                	postLetter();
                }
            }
            </shiro:hasPermission>
        ],
        rowStyler: function(index,row){
            color=app.dictName('CASE_COLOR',row.color);
    		return 'color:'+color;
    	},
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
    });

});
//加载组织机构
function loadOrgs(){
	var url = ctx+'/sys/organization/getOrganizationList';
	$('#orgId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
        valueField:'id',
        width:150,
        value:'${CURRENT_USER.orgId}',
        loadFilter: function(rows){
            return convert(rows);
        }
	});
}

//初始化改变状态表单
function initChangeStateForm(rows,state){
	change_state_form = $('#change_state_form').form({
       url: baseUrl+"/changeState",
       onSubmit: function (param) {
           $.messager.progress({
               title: '提示信息！',
               text: '数据处理中，请稍后....'
           });
           var isValid = $(this).form('validate');
           if (!isValid) {
               $.messager.progress('close');
           }
           var ids = new Array();
           $.each(rows, function (i, row) {
        	   ids[i] = row.id;
           });
           param.ids = ids;
           param.state = state;
           return isValid;
       },
       success: function (data) {
           $.messager.progress('close');
           //渲染结果
           app.renderAjax(data,function(json){
	       	 _dialog.dialog('destroy');
           	 _datagrid.datagrid('load');
           });
       }
   });
}
//邮寄信函
function postLetter(){
	app.dataGridSelect(_datagrid, function(rows){
		$.messager.confirm('确认提示！','您共选中了【'+rows.length+'】条数据，确定要邮寄信函吗？',function(r){
            if (r){
            	var ids = '';
                $.each(rows, function (i, row) {
                	ids += row.id+",";
                });
             	ids = ids.substring(0,ids.length-1);
                app.ajax({
    		        url: baseUrl+"/changeState",
    		        type: 'post',
    		        data: {'ids':ids,'state':1},
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
	});
}

//导出信函
function exportVisitRecord() {
	var url = ctx + '/sys/visitTemplate/exportVisitExcel?name=信函模板&type=1';
	app.dataGridSelect(_datagrid, function(rows) {
		already_dialog = app.openFormDialog(function() {
			exportSelected();
		}, '导出信函', 600, 160, url, 'GET', function() {
		}, '导出', '取消');
	});
}
//导出所选
function exportSelected() {
	app.dataGridSelect(_datagrid, function(rows) {
		var isValid = $('#template_form').form('validate');
		if (isValid) {
			var ids = new Array();
			$.each(rows, function(i, row) {
				ids[i] = row.id;
			});
			var templateId = $('#templateId').combobox('getValue');
			app.downLoadFile({
				url : ctx + '/collection/caseLetter/exportLetter', //请求的url
				data : {
					'ids' : ids,
					'templateId' : templateId,
					'type':2
				},//要发送的数据
				callback : function() {
					already_dialog.dialog('destroy');
				}
			});
			
		}
	});
};
//委托方 
function loadEntrust(domId){
	var url = ctx+'/sys/entrust/entrustlist';
	$(domId).combobox({
        url:url,
        width:150,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text'
	});
}
//搜索
function search() {
	//重新加载datagrid
	app.load(_datagrid,$.serializeObject(_search_form));
}
</script>
