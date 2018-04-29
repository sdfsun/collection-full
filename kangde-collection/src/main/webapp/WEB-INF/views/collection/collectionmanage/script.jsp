<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/collectionmanage";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadEntrust('#search_entrustId');
    loadOrgs();
    loadCaseAssigned('${CURRENT_USER.orgId}');
    findBacthCode('#batchCode');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
    	
    	url : '',
       /*  url: baseUrl + '/query', */
       fit: true,
        method:'POST',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        columns:[[
                  {field : 'id',checkbox : true,width:1},
                  {field : 'casemodel.caseCode',title : '案件序列号',width : 180,sortable:true,
                	  formatter : function(value, row, index) {
         					if (value) {
         						 return value;
         					}
         					return value;
         				}},
         				{field : 'collecStateId',title : '风控状态',width : 95,sortable:true,
                      	  formatter : function(value, row, index) {
            					return app.dictName('COLLECTION_STATE', value)
            				}},
            				 {field : 'typeId',title : '风控结果',width : 95,sortable:true,
                          	  formatter : function(value, row, index) {
                  					return app.dictName('COLLECTION_RESULT', value)
                  				}},			
                  {field : 'casemodel.caseName',title : '姓名',width : 95,sortable:true,
                	  formatter : function(value, row, index) {
       					if (value) {
       						return value;
       					}
       					return value;
       				}},
       			  {field : 'name',title : '对象姓名',width : 95,sortable:true},
                  {field : 'relation',title : '关系',width : 60,
                	  formatter : function(value, row, index) {
        					return app.dictName('RELATION', value)
        				}},
                  {field : 'contact',title : '电话/地址',width : 280,sortable:true},
                  {field : 'ptpMoney',title : 'PTP金额',width : 95,sortable:true,
              		formatter : function(value, row, index) {
   						if (null==value) {
   							return "0.00";
   						}else{
                          	return $.fmoney(value); 
   						}
   						return value;
   					} 
             	},
             	{field : 'ptpTime',title : 'PTP日期',width : 95,sortable:true,
  					formatter:function(value, row, index){
                		if(value){
                			return $.formatDate(value,'yyyy-MM-dd');
                		}
                		return value;
                	}},
                	  {field:'casemodel.agentRate',title:'业绩率',width:60},
                	  {field : 'content',title : '风控记录',width : 280},
                	  {field : 'employeeInfoModel.userName',title : '风控员',width : 95,sortable:true,
                    	  formatter : function(value, row, index) {
             					if (value) {
             						return value;
             					}
             					return value;
             				}},
          
                	  {field : 'createTime',title : '催记日期',width : 95,sortable:true,
                		formatter : function(value, row, index) {
         					if (value) {
         						return $.formatDate(value, 'yyyy-MM-dd');
         					}
         					return value;
         				}}
                 
                 
                  
                  
                 
                 
                  
            
        ]],
        toolbar: [
			<shiro:hasPermission name="collectionmanage:exportCaseId">
            {
                text: '导出所选催记',
                iconCls: 'eu-icon-daochusuoxuan',
                handler: function () {
                	exportCase(1)
                }
            },
            '-', 
            </shiro:hasPermission>
            <shiro:hasPermission name="collectionmanage:exportCaseIdAll">
            {
                text: '导出所查催记',
                iconCls: 'eu-icon-daochusuocha',
                handler: function () {
                	exportCase(2)
                }
            },
            </shiro:hasPermission>
        ],
        rowStyler: function(index,row){
        	var color1=0;
        	if (row.casemodel) {
					color1= row.casemodel.color;
				}
            color=app.dictName('CASE_COLOR',color1);
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
    })

});

//导出案件
function exportCase(type){
	var url = ctx+'/sys/template/exportExcel?gropNo=phone_record&name=催记模板';
	if(type==1){
		app.dataGridSelect(_datagrid, function(rows){
			_dialog = app.openFormDialog(function(){
				exportSelected();
			}, '导出所选催记', 600, 160, url, 'GET', function(){}, '导出', '取消');
		});
	}else{
		app.datagridHasRow(_datagrid, function(){
			_dialog = app.openFormDialog(function(){
				exportQuery();
			}, '导出所查催记', 600, 160, url, 'GET', function(){}, '导出', '取消');
		});
	}
}

//导出所选案件
function exportSelected(){
	app.dataGridSelect(_datagrid, function(rows){
        var isValid = $('#template_form').form('validate');
        if(isValid){
        	var caseIds = new Array();
            $.each(rows, function (i, row) {
            	caseIds[i] = row.id;
            });
			var templateId = $('#templateId').combobox('getValue');
	        app.downLoadFile({
	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        	data:{'caseIds':caseIds,'templateId':templateId},//要发送的数据
	        	callback:function(){
	        		_dialog.dialog('destroy');
	        	}
	        });
        }
	});
};

//导出所查件
function exportQuery(){
	   var isValid = $('#template_form').form('validate');
	   if(isValid){
		var queryParams = _datagrid.datagrid("options").queryParams;
		var templateId = $('#templateId').combobox('getValue');
		queryParams.templateId = templateId;
		app.downLoadFile({
        	url:baseUrl+'/exportQueryExcel',
        	data:queryParams,
        	callback:function(){
        		_dialog.dialog('destroy');
        	}
        });	
    }
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
            	app.reload(_datagrid);//重新加载列表数据
            });
        }
    });
}

//加载组织机构
function loadOrgs(){
	var url = ctx+'/sys/organization/getOrganizationList';
	$('#orgId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    width:150,
	    height:22,
        valueField:'id',
        value:'${CURRENT_USER.orgId}',
        loadFilter: function(rows){
            return convert(rows);
        },
        onChange:function(nv, ov){
        	//重新渲染用户列表
        	loadCaseAssigned(nv);
        }
	});
}

//加载用户
function loadCaseAssigned(orgId){
	var url = ctx+'/sys/employeeInfo/orgusersByOrg?orgId='+orgId;
	$('#caseAssignedId').combobox({    
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}


function loadEntrust(domId){
	var url = ctx+'/sys/entrust/entrustlist';
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text'
	});
} 

function findBacthCode(domId){
	var url = ctx+'/collection/caseImport/Codecombobox';
	$(domId).combobox({
        url:url,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    valueField:'value',
        textField:'text'
	});
}

//搜索
function search() {
	var createTimeBegin=$('#createTime').val()
	var createTimeEnd=$('#createTime1').val()
	if(!checkDate(createTimeBegin,createTimeEnd)){
		return false;
	}
	//重新加载datagrid
	var options = _datagrid.datagrid('options');
	options.url =baseUrl + '/query';
	app.load(_datagrid,$.serializeObject(_search_form));
}



function checkDate(s1,  s2){    //sDate1和sDate2是2006-12-18格式  
	
	if(s1==''){
		eu.showAlertMsg("催记开始日期不能为空", 'warning');
		return false;
	}
	if(s2==''){
		eu.showAlertMsg("催记截止日期不能为空", 'warning');
		return false;
	}

	var endDate = new Date(s1);  
	endDate.setMonth(endDate.getMonth()+4);  
	endDate.setDate(0);  
	//alert($.formatDate(endDate, 'yyyy-MM-dd'));
	//return false;
	if(s2>$.formatDate(endDate, 'yyyy-MM-dd')){
		eu.showAlertMsg("催记日期跨度不能超过4个月", 'warning');
		return false;
	}
	return true;

}    
</script>
