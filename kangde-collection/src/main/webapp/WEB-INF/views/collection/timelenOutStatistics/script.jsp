<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
    <script type="text/javascript" src="${ctx}/static/datagrid-groupview.js"></script>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/timelenOutStatistics";

	$(function() {

		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadCaseAssigned('${CURRENT_USER.orgId}');
		loadOrgs();
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : '',
			fit : true,
			method : 'GET',
			//queryParams:$.serializeObject(_search_form),
			//pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,//自适应列宽
			showFooter: true,
			striped : true,//显示条纹
            collapsible:true,
		//pageSize : 5,//每页记录数
 			//pageList : [ 5, 20, 50, 100 ],
 			/* view:groupview,
            groupField:'empName', 
            groupFormatter:function(value,rows){
                return value + ' - ' + rows.length + ' 条数据';
            }, */
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			/*  columns : [ [ 
			{field : 'empName', title : '风控员', width : 110,sortable:true}, 
			{field : 'cno', title : '坐席号', width : 110,sortable:true},
            {field : 'timeLen', title : '时间', width : 110,sortable:true,
            	formatter : function(value, row, index) {
 					if (null == value) {
						return "";
					} else if ('—' == value) {
						return "—";
					} else {
						var a =value-1+1+1;
						return value+"-"+a+"分钟";
					}
            	}},
 			{field : 'callOutCount', title : '电催总次数', width : 110,sortable:true},
 			{field : 'callOutConnectCount', title : '总接通次数', width : 110,sortable:true}, 
 			
             ] ], */
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

    	
	//搜索
	function search() {
		
		/* var createTimeBegin=$('#normalDate').val()
		var createTimeEnd=$('#normalDate1').val()
		if(!checkDate(createTimeBegin,createTimeEnd)){
			return false;
		} */
		
	   /// 	 $("#_datagrid").datagrid({url:baseUrl + baseUrl + '/queryBatch'});
	    var options = _datagrid.datagrid('options');
		//url 需要自己定义
		options.url = baseUrl + "/query";
		//触发搜索
	 	//_datagrid.datagrid(options);
	    		//重新加载datagrid
	  	app.load(_datagrid, $.serializeObject(_search_form));
	  	}
	  	
	function checkDate(s1,  s2){    //sDate1和sDate2是2006-12-18格式  
		
		if(s1==''){
			eu.showAlertMsg("统计开始日期不能为空", 'warning');
			return false;
		}
		if(s2==''){
			eu.showAlertMsg("统计截止日期不能为空", 'warning');
			return false;
		}

		var endDate = new Date(s1);  
		endDate.setMonth(endDate.getMonth()+1);  
		endDate.setDate(0);  
		//alert($.formatDate(endDate, 'yyyy-MM-dd'));
		//return false;
		if(s2>$.formatDate(endDate, 'yyyy-MM-dd')){
			eu.showAlertMsg("统计日期跨度不能超过1个月", 'warning');
			return false;
		}
		return true;

	}
 //计算天数差的函数，通用  
function  DateDiff(s1,  s2){    //sDate1和sDate2是2006-12-18格式  
	s1 = new Date(s1.replace(/-/g, "/"));
	s2 = new Date(s2.replace(/-/g, "/"));
	var days = s2.getTime() - s1.getTime();
	var days = parseInt(days / (1000 * 60 * 60 * 24));
  return  days  
}
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
//加载用户
function loadCaseAssigned(orgId){
	var url = ctx+'/sys/employeeInfo/orgusersByOrg?orgId='+orgId;
	$('#emId').combobox({    
		url:url,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}
</script>
