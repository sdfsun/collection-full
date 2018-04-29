<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/statisticsBatch";

	$(function() {

		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : '',
			fit : true,
			method : 'GET',
			queryParams:$.serializeObject(_search_form),
			//pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			showFooter: true,
			striped : true,//显示条纹
			//pageSize : 50,//每页记录数
			//pageList : [ 20, 50, 100 ],
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			columns : [ [ 
            {field : 'batch_code', title : '批次号', width : 170,sortable:true},
 			{field : 'case_count', title : '案件数量', width : 110,sortable:true},
             {field : 'total_case_money',title : '案件金额',width : 110,sortable:true,
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
             {field : 'paid_achieve', title : '已还款佣金', width : 110,sortable:true,
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
             {field : 'back_paid', title : '已还款业绩', width : 110,sortable:true,
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
 	  		{field : 'cp_case_count',title : '未还款数量',width : 110,sortable:true}, 
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
             {field : 'cp_achieve', title : '未还款佣金', width : 110,sortable:true,
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
             {field : 'cp_back_paid', title : '未还款业绩', width : 110,sortable:true,
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
 	  		  /*   -- 批次号
 	            -- 案件数量
 	            -- 委案金额
 	            -- 已确认还款
 	            -- 业绩
 	            -- 佣金
 	            -- 平均佣金率=佣金/已确认还款
 	            -- 亿元均产出=佣金/委案金额
 	            -- 件均产出=佣金/案件数量 */
 	  		     {field : 'average_rate', title : '平均佣金率', width : 110,sortable:true,
 		 			formatter : function(value, row, index) {
 						if (null==value) {
 							return "0.00%";
 						}else if (''==value) {
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
       			onLoadSuccess : function() {
       				app.unCheckAll(this);//取消所有选中
       			},
       			onRowContextMenu : function(e, rowIndex, rowData) {
       				e.preventDefault();
       				app.unCheckAll(this);//取消所有选中
       				$(this).datagrid('selectRow', rowIndex);
       				$('#_datagrid_menu').menu('show', {
       					left : e.pageX,
       					top : e.pageY
       				});
       			},
       			onDblClickRow : function(rowIndex, rowData) {
       				edit(rowIndex, rowData);
       			}
       		}).datagrid('showTooltip');

       		//  _datagrid.datagrid('showTooltip');

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
       	    var rows = _datagrid.datagrid('getSelections');
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
       		app.datagridHasRow(_datagrid, function(){
       			var queryParams = _datagrid.datagrid("options").queryParams;
       			app.downLoadFile({
       				url:baseUrl+'/exportQueryExcel',
       	        	data:queryParams,  
       	        });	
       		});
       	}
       	//搜索
       	function search() {
      /// 	 $("#_datagrid").datagrid({url:baseUrl + baseUrl + '/queryBatch'});
       var options = _datagrid.datagrid('options');
			//url 需要自己定义
			options.url = baseUrl + "/queryBatch";
			//触发搜索
			 //_datagrid.datagrid(options);
       		//重新加载datagrid
       		app.load(_datagrid, $.serializeObject(_search_form));
       	}
       </script>
