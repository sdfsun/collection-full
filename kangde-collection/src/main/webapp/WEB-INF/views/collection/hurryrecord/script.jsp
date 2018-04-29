<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _dialog;
	var caseId = "${caseId}";
	//请求基础路径
	var baseUrl = ctx + "/collection/hurryrecord";

	$(function() {
		
		//数据列表
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/query' + "?caseId=" + caseId,
			fit : true,
			method : 'GET',
			 pagination: true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			idField : 'caseId',
			columns : [ [ {
				field : 'createTime',
				title : '时间',
				width : 130,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
					}
					return value;
				}
			}, {
				field : 'hurCat',
				title : '类别',
				width : 100,
				formatter : function(value, row, index) {
					return app.dictName('OPER_TYPE', value);
				}
				
			}, {
				field : 'content',
				title : '操作内容',
				width : 950
			}, {
				field : 'operatorName',
				title : '操作人',
				width : 110
			} ] ],
			onLoadSuccess : function() {
				app.unCheckAll(this);//取消所有选中
			}
		}).datagrid('showTooltip');

		
		$('#hurCat').combobox({
		    onChange:function(newValue,oldValue){
		    	_datagrid.datagrid('load', {
		    		caseId: caseId,
		    	    hurCat: newValue
		    	});
		    }
		});
		
		
		
	});
</script>
