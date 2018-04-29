<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/jointdebt";

	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query' + "?caseId=${caseId}",
			fit : true,
			border:false,
			method : 'GET',
			pagination: true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			idField : 'caseId',
			columns : [ [ {
				field : 'batchCode',
				title : '批次号',
				width : 150
			}, {
				field : 'caseCode',
				title : '案件序列号',
				width : 200,
				formatter: function(value, row, index){
					return '<a style="color:blue;text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.caseId+'")>'+value+'</a>';
				}
			}, {
				field : 'caseDate',
				title : '委案日期',
				width : 100,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd');
					}
					return value;
				}
			}, {
				field : 'caseNum',
				title : '证件号',
				width : 200
			}, {
				field : 'entrustName',
				title : '委托方',
				width : 100
			}, {
				field : 'caseMoney',
				title : '委案金额',
				width : 150,
				formatter : function(value, row, index) {
						if (""==value) {
							return "0.00";
						}else{
                    	return $.fmoney(value); 
						}
						return value;
					} 
			}, {
				field : 'collecStateId',
				title : '风控状态',
				width : 100,
				formatter : function(value, row, index) {
					return app.dictName('COLLECTION_STATE', value)
				}
			}, {
				field : 'paidNum',
				title : '已还款',
				width : 130,
				formatter : function(value, row, index) {
						if (""==value) {
							return "0.00";
						}else{
                    	return $.fmoney(value); 
						}
						return value;
					} 
			}, {
				field : 'userName',
				title : '风控员',
				width : 160
			}

			] ],
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

	});
</script>
