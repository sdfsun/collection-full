<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/statisticsApply";

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
			striped : true,//显示条纹
			showFooter: true,
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			columns : [ [ 

              {field : 'sur_time', title : '日期', width : 110}, 
              {field : 'apply_count', title : '协催总次数', width : 110 },
              /*tj.sur_time,#协催时间
          	tj.apply_count,#协催总次数
          	tj.customer_count,#客服咨询次数
          	tj.police_count,#公安协助次数
          	tj.court_count,#法院协助次数
          	tj.charge_count, #主管协催次数
          	tj.average_case_apply_count #平均每案件协催次数（协催总次数/协催过的案件数量）*/
              {field : 'customer_count',title : '客服咨询次数',width : 110},
              {field : 'police_count', title : '公安协助次数', width : 110}, 
              {field : 'court_count', title : '法院协助次数', width : 110}, 
              {field : 'charge_count', title : '主管协催次数', width : 110}, 
              {field : 'average_case_apply_count', title : '平均每案件协催次数', width : 120} 
              ] ],
			toolbar : [/* {
				text : '导出所选统计数据',
				iconCls : 'eu-icon-daochusuoxuan',
				handler : function() {
					daochu(1)
				}
			}, */ {
				text : '导出',
				iconCls : 'eu-icon-daochusuoxuan',
				handler : function() {
					daochu(2)
				}
			} ],
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
		var options = _datagrid.datagrid('options');
		//url 需要自己定义
		options.url = baseUrl + '/queryApply';
		//触发搜索
		//重新加载datagrid
		app.load(_datagrid, $.serializeObject(_search_form));
	}
</script>
