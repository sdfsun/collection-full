<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _treegrid;
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/statisticsOrganizationCaseState";

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
	  		  /*
	          	tj.new_case_count,#新案数量
	          	tj.lose_case_count,#失联数量
	          	tj.involve_case_count,#涉案数量
	          	tj.negotiation_case_count,#谈判数量
	          	tj.family_case_count,#中家人数量
	          	tj.someone_case_count,#中第三人数量
	          	tj.oneself_case_count#中本人数量* */
              {field : 'new_case_count', title : '新案数量', width : 110,sortable:true }, 
              {field : 'lose_case_count', title : '失联数量', width : 110,sortable:true }, 
              {field : 'involve_case_count', title : '涉案数量', width : 110,sortable:true }, 
              {field : 'negotiation_case_count', title : '谈判数量', width : 110,sortable:true }, 
              {field : 'family_case_count', title : '中家人数量', width : 110,sortable:true }, 
              {field : 'someone_case_count', title : '中第三人数量', width : 110,sortable:true }, 
              {field : 'oneself_case_count', title : '中本人数量', width : 110,sortable:true },
              /*  #暂停催收 25  要求退案 26 承诺 31 疑似欺诈 32 */
              {field : 'stop_case_count', title : '暂停催收数量', width : 110,sortable:true }, 
              {field : 'back_case_count', title : '要求退案数量', width : 110,sortable:true }, 
              {field : 'promise_case_count', title : '承诺数量', width : 110,sortable:true }, 
              {field : 'cheat_case_count', title : '疑似欺诈数量', width : 110,sortable:true },
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
			options.url = baseUrl + "/queryOrganizationCaseState";
		 
		//重新加载_treegrid
		_treegrid.treegrid("load", $.serializeObject(_search_form));
		_treegrid.treegrid("clearSelections");//取消所有的已选择项
		_treegrid.treegrid("clearChecked");//取消checked状态
		_treegrid.treegrid("unselectAll");//取消全选按钮为全选状态 
	}
</script>
