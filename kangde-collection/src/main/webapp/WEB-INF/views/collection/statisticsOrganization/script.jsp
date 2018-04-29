<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _treegrid;
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/statisticsOrganization";

	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadEntrust('#search_entrustId');
		loadOrgs();
		//数据列表
		_treegrid = $('#_treegrid').treegrid({
			url:'',
			queryParams:$.serializeObject(_search_form),
			fit:true,
	        fitColumns:false,//自适应列宽
	        striped:true,//显示条纹
	        rownumbers:true,//显示行数
	        showFooter: true,
	        nowrap : false,
	        border : true,
	        singleSelect:false,
	        remoteSort:true,//是否通过远程服务器对数据排序
	        idField : 'orgId',
	        treeField:"name",
			columns : [ [ 
              {field : 'name', title : '风控方', width : 150,sortable:true }, 
              {field : 'case_count', title : '案件数量', width : 110,sortable:true },
              {field : 'total_case_money',title : '案件金额',width : 120,sortable:true,
	 			formatter : function(value, row, index) {
 					if (null==value) {
 						return "0.00";
 					}else if (''==value) {
 						return "0.00"; 
 					}else{
 				      	return $.fmoney(value); 
 					}
 					return value;
	  		    } 	
	  		  },
	  		 {field : 'cp_case_count',title : '未确认还款数量',width : 110,sortable:true},
	  		 {field : 'cp_money', title : '未确认还款', width : 110,sortable:true,
		 			formatter : function(value, row, index) {
	 					if (null==value) {
	 						return "0.00";
	 					}else if (''==value) {
	 						return "0.00"; 
	 					}else{
	 				      	return $.fmoney(value); 
	 					}
	 					return value;
		  		    } 	 }, 
	              {field : 'cp_back_paid', title : '未确认业绩', width : 110,sortable:true,
		 			formatter : function(value, row, index) {
	 					if (null==value) {
	 						return "0.00";
	 					}else if (''==value) {
	 						return "0.00"; 
	 					}else{
	 				      	return $.fmoney(value); 
	 					}
	 					return value;
		  		    } 	 }, 
		  		  {field : 'paid_case_count',title : '已还款数量',width : 110,sortable:true},
	              {field : 'paid_num', title : '已确认还款', width : 110,sortable:true,
		 			formatter : function(value, row, index) {
	 					if (null==value) {
	 						return "0.00";
	 					}else if (''==value) {
	 						return "0.00"; 
	 					}else{
	 				      	return $.fmoney(value); 
	 					}
	 					return value;
		  		    } 	 }, 
		  		   {field : 'back_paid', title : '已确认业绩', width : 110,sortable:true,
			 			formatter : function(value, row, index) {
		 					if (null==value) {
		 						return "0.00";
		 					}else if (''==value) {
		 						return "0.00"; 
		 					}else{
		 				      	return $.fmoney(value); 
		 					}
		 					return value;
			  		    } 	 },
	  		  
	  		     {field : 'average_rate', title : '平均佣金率', width : 110,sortable:true,
					formatter : function(value, row, index) {
						if (null == value) {
							return "0.00%";
						} else if ('' == value) {
							return "0.00%";
						}else if ('—' == value) {
							return "—";
						} else {
							return $.fmoney(value)+"%";
						}
						return value;
					} 	 
	  		    },     
		  		{field : 'hun_average_out', title : '亿元均产出', width : 110,sortable:true,
			 			formatter : function(value, row, index) {
							if (null==value) {
								return "0.00";
							}else if (''==value) {
								return "0.00"; 
							}else{
						      	return $.fmoney(value); 
							}
							return value;
			  		    } 	
	  		    },     
			  	{field : 'part_average_out', title : '件均产出', width : 110,sortable:true,
				 			formatter : function(value, row, index) {
								if (null==value) {
									return "0.00";
								}else if (''==value) {
									return "0.00"; 
								}else{
							      	return $.fmoney(value); 
								}
								return value;
				  		    } 	 }
              ] ],
              /* toolbar : [  {
 				text : '导出所选统计数据',
 				iconCls : 'eu-icon-daochusuoxuan',
 				handler : function() {
 					daochu(1)
 				}
 			}, {
 				text : '导出',
 				iconCls : 'eu-icon-daochusuoxuan',
 				handler : function() {
 					daochu(2)
 				}
 			} ], */
			  onContextMenu : function(e, row) {
		            e.preventDefault();
		            $(this).treegrid('select', row.orgId);
		            $('#_menu').menu('show', {
		                left : e.pageX,
		                top : e.pageY
		            });

		        }
		}).datagrid('showTooltip');
	});

	
	function daochu(type){
		if(type==1){
			excelSelect();
		}else if(type==2){
			exportQuery();
		}
	}
		function excelSelect(){
			 //选中的所有行
		    var rows = _treegrid.datagrid('getSelections');
		    //选中的行（第一次选择的行）
		    var row = rows[0];
			var ids = new Array();
			//循环拿到所有的选中id
		    $.each(rows, function (i, row) {
	        	ids[i] = row.id;
	        }); 
		    if (row) {
		        	    app.downLoadFile({
		   	        	url:baseUrl+'/exportSelected', //请求的url
		           		data:{'ids':ids},
		   	        });
		    } else {
		    	eu.showAlertMsg("请选择要操作的数据！",'warning');
		    }
		}
		
		//导出所查件
		function exportQuery(){
			
				var queryParams =_treegrid.treegrid("options").queryParams;
				app.downLoadFile({
					url:baseUrl+'/exportQueryExcel',
		        	data:queryParams,  
		        });	
		
		}
	//加载组织机构
	//加载组织机构
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
//委托方 
function loadEntrust(domId){
	var url = ctx+'/sys/entrust/combobox2';
	$(domId).combobox({
        url:url,
        width:150,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    valueField:'value',
        textField:'text'
	});
} 
	//搜索
	function search() {
		// _treegrid.treegrid("url", baseUrl + "/queryOrganization");
		
		 var options = _treegrid.treegrid('options');
			//url 需要自己定义
			options.url = baseUrl + "/queryOrganization";
		 
		//重新加载_treegrid
		_treegrid.treegrid("load", $.serializeObject(_search_form));
		_treegrid.treegrid("clearSelections");//取消所有的已选择项
		_treegrid.treegrid("clearChecked");//取消checked状态
		_treegrid.treegrid("unselectAll");//取消全选按钮为全选状态 
	}
</script>
