<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
var messagereminder_datagrid;
//请求基础路径
var baseUrl = ctx+"/collection/messageReminder";
var assignedUrl = ctx+"collection/casecollect/query?scope=assigned";
$(function () {
    //数据列表
    messagereminder_datagrid = $('#messagereminder_datagrid').datagrid({
        url: baseUrl + '/query',
        fit: true,
        method:'POST',
        pagination: true,//底部分页
        rownumbers: false,//显示行数
        nowrap:true,
        singleSelect:true,
        showHeader:false,
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        columns:[[
                  {field : 'id',hidden:true},
                  {field : 'isRead',hidden:true},
                  
       			  {field : 'content',resizable:false,title: '提醒内容',width : '60%',
                      formatter:function(value, row, index){
              			var str='<span style="cursor:pointer" onclick=viewReminder("'+row.id+'","'+row.isRead+'","'+row.url+'","'+row.title+'")>'+value+'</span>';
              			return str;
              	}},
              	{field : 'createTime',title:'提醒时间', width:'40%',
              	  formatter : function(value, row, index) {
	     					if (value) {
	     						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
	     					}
	     					return value;
	     				}}
        ]],
        rowStyler: function(index,row){
            if(row.isRead==0){
            	return 'font-weight: bold;'
            }
    	
    	},
        onLoadSuccess : function() {
        	//$('#messagereminder_datagrid').datagrid('getPanel').removeClass('lines-both lines-no lines-right lines-bottom').addClass('lines-no');
		}
    })

});


 function viewReminder(id, isread, url, title) {
	if(isread==1){
		showpage(id, isread, url, title);
	}else{
		
	$.post(baseUrl + '/readMessageReminder', {
		id : id
	}, function(data) {
		showpage(id, isread, url, title);
		app.load(messagereminder_datagrid);
	}, 'json');	
	}
 }

 function showpage(id, isread, url, title){
	 if(url){
		 if('案件详情'==title){
				window.open('${ctx}'+url);
			}else{
	 		eu.addTab(parent.layout_center_tabs,title,'${ctx}'+url,true,'');
			}
	 }
 }
</script>
