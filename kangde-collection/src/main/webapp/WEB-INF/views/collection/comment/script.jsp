<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var caseId = "${caseId}";
	//请求基础路径
	var baseUrl = ctx + "/collection/comment";

	$(function() {
		_form = $('#_form').form();
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query' + "?caseId=" + caseId,
			nowrap:false,
			fit : true,
			border : false,
			method : 'GET',
			singleSelect : true,
			pagination : true,//底部分页
			fitColumns : true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			showHeader:false,
			columns : [ [
			{
				field : 'content',
				title : '评语',
				width : '100%',
				formatter : function(value, row, index) {
					return '<'+$.formatDate(row.commentTime, 'yyyy-MM-dd HH:mm:ss')+'>'+row.userName+' : '+row.content;
				}
			}] ],
			rowStyler: function(index,row){
					return 'font-weight:bold;'; 
				}
			
		});

	});

	function createcomment(caseId) {
		
		
		var isValid =_form.form('validate');
		if (!isValid) {
			return ;
		}

		var content=$("#content").textbox("getValue");
		$.ajax({
			type : "POST",
			url : baseUrl,
			data : {
				caseId : "${caseId}",
				content : content
			},
			dataType : "json",
			async : false,
			success : function(data) {

				//渲染结果
				app.renderAjax(data, function(json) {
					app.load(_datagrid);
					if(json.code=='1'){
						$("#content").textbox("reset");
					}
				});

				
				//_datagrid.datagrid('reload'); 
				//$.messager.alert('提示', data.msg);
			}
		});
	}
</script>
