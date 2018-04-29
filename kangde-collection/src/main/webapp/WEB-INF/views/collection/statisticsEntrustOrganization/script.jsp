<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var paid_dialog;
	var paid1_dialog;
	var paid2_dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/statisticsEntrustOrganization";

	$(function() {

		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadEntrust('#search_entrustId');
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
		    url:'',
			fit : true,
			method : 'GET',
			queryParams:$.serializeObject(_search_form),
			//pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			striped : true,//显示条纹
			showFooter: true,
			//pageSize : 50,//每页记录数
			//pageList : [ 20, 50, 100 ],
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			columns : [ [ 
              {field : 'name', title : '委托方', width : 110,sortable:true }, 
              {field : 'case_count', title : '案件数量', width : 110,sortable:true },
              
              {field : 'phone_count', title : '电催次数', width : 110,sortable:true }, 
              {field : 'visit_count', title : '外访次数', width : 110,sortable:true }, 
              {field : 'assist_count', title : '协催次数', width : 110,sortable:true }, 
              {field : 'apply_count', title : '查资次数', width : 110,sortable:true }, 
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
			/* onLoadSuccess : function() {
				app.unCheckAll(this);//取消所有选中
			}, */
			/* onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				app.unCheckAll(this);//取消所有选中
				//$(this).datagrid('selectRow', rowIndex);
				$('#_datagrid_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			} *//* ,
			onDblClickRow : function(rowIndex, rowData) {
				edit(rowIndex, rowData);
			} */
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
		/* function excelSelect(){
			 //选中的所有行
		    var rows = _datagrid.datagrid('getSelections');
		    //选中的行（第一次选择的行）
		    var row = rows[0];
			var ids = new Array();
			//循环拿到所有的选中id
		    $.each(rows, function (i, row) {
	        	ids[i] = row.entrustId;
	        }); 
		    if (row) {
		        	    app.downLoadFile({
		   	        	url:baseUrl+'/exportSelected', //请求的url
		           		data:{'ids':ids},
		   	        });
		    } else {
		    	eu.showAlertMsg("请选择要操作的数据！",'warning');
		    }
		} */
		
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
		
	//委托方 
	function loadEntrust(domId){
		var url = ctx+'/sys/entrust/entrustlist';
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
		 var options = _datagrid.datagrid('options');
			//url 需要自己定义
			options.url = baseUrl + '/queryEntrustOrganization';
		//重新加载datagrid
		app.load(_datagrid, $.serializeObject(_search_form));
	}
</script>
