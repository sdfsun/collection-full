<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
var _datagrid;
var _form;
var _search_form;
var _dialog;
//请求基础路径
var baseUrl = ctx+"/collection/helpmerecord";

$(function () {
    _form = $('#_form').form();
    _search_form = $('#_search_form').form();
    loadOrgs();//风控方
    loadEntrust('#search_entrustId');
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: baseUrl + '/queryrecord',
        fit: true,
        method:'GET',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        frozenColumns: [[
                         {field: 'id', checkbox: true,width:1}
                        ]],
                        columns: [[
                           	{field:'caseModel.caseCode',title:'案件序列号',width:180,sortable:true,
                            	formatter: function(value,row,index){
                            		return openCaseDetailed(value, row, index);
                			    }},
                			 {field:'caseModel.caseName',title:'姓名',width:95,
                					formatter : function(value, row, index) {
                     					if (value) {
                     						return value;
                     					}
                     					return value;
                     				}},
            				{field:'caseModel.caseNum',title:'证件号',width:180,sortable:true,
            					formatter : function(value, row, index) {
                 					if (value) {
                 						return value;
                 					}
                 					return value;
                 				}},
                 				{field:'caseModel.caseMoney',title:'委案金额',width:95,sortable:true,
                 					formatter : function(value, row, index) {
                 						if (""==value) {
                 							return "0.00";
                 						}else{
                                        	return $.fmoney(value); 
                 						}
                 						return value;
                 					} },
            			    {field:'surplusMoney',title:'已还款',width:95,sortable:true,
                 						formatter : function(value, row, index) {
                     						if (""==value) {
                     							return "0.00";
                     						}else{
                                            	return $.fmoney(value); 
                     						}
                     						return value;
                     					} }, 
                     		{field:'state',title:'查资状态',width:60,
                     			formatter : function(value, row, index) {
                 					if (value==1) {
                 						return "已完成";
                 					}else if(value==3){
                 						return "驳回";
                 					}
                 					return value;
                 				}},
            				{field:'caseModel.agentRate',title:'业绩率',width:60},
            				{field:'applyType',title:'类型',width:60,sortable:true,
            					formatter : function(value, row, index) {
            						return app.dictName('HELPME', value)
            					}},
            				{field:'applyContent',title:'申请内容',width:280},
            				{field:'entrustModel.name',title:'风控方',width:95,sortable:true,
            					formatter : function(value, row, index) {
                 					if (value) {
                 						return value;
                 					}
                 					return value;
                 				}},
                 				{field:'employeeInfoModel.userName',title:'申请人',width:95,sortable:true,
                					formatter : function(value, row, index) {
                     					if (""==value) {
                     						return row.applyUserName;
                     					}else{
                     					return value;
                     					}
                     				}},
            				{field:'appTime',title:'申请日期',width:95,sortable:true,
            					formatter : function(value, row, index) {
                 					if (value) {
                 						return $.formatDate(value, 'yyyy-MM-dd');
                 					}
                 					return value;
                 				}},
            				
            				
                
					{field:'surTime',title:'查资日期',width:100,sortable:true,
						formatter : function(value, row, index) {
							if (value) {
								return $.formatDate(value, 'yyyy-MM-dd');
							}
							return value;
						}},
        ]],
    
        rowStyler: function(index,row){
        	var color1=0;
        	if (row.caseModel) {
					color1= row.caseModel.color;
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
//导出所选案件
function Export(){
	        app.downLoadFile({
	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        });

};
	
//加载组织机构
function loadOrgs() {
	var url = ctx + '/sys/organization/getOrganizationList';
	$('#orgId').combotree({
		url : url,
		multiple : false,//是否可多选
		editable : false,//是否可编辑
		width : 150,
		valueField : 'id',
		value : '${CURRENT_USER.orgId}',
		loadFilter: function(rows){
            return convert(rows);
        },
		onChange : function(nv, ov) {
			//重新渲染用户列表
			loadCaseAssigned(nv);
		}
	});
}

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
