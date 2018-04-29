<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _form;
	var _search_form;
	var _dialog;
	var caseId = "${caseId}";
	//请求基础路径
	var baseUrl = ctx + "/collection/visitrecord";

	$(function() {
		_datagrid = $('#visitrecord_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryVisitRecordVoForPage' + "?caseId=" + caseId,
							fit : true,
							nowrap:false,
							method : 'GET',
							border: false,
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : true,//自适应列宽
							striped : true,//显示条纹
							remoteSort : false,//是否通过远程服务器对数据排序
							idField : 'id',
							columns : [ [
									{
										field : 'id',
										title : 'id',
										width : 100,
										hidden : true
									},
									{
										field : 'name',
										title : '姓名',
										width : 100,
										hidden : true
									},
									{
										field : 'relation',
										title : '关系',
										width : 80
									},
									{
										field : 'visitAddress',
										title : '地址',
										width : 300
									},
									{
										field : 'applyReason',
										title : '原因',
										width : 100,
										formatter : function(value, row, index) {
					      					return app.dictName('VISIT_REASON', value)
					      				}
									},
									{
										field : 'actualVisitTime',
										title : '实际外访日期',
										width : 120,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'visitReport',
										title : '外访报告',
										width : 300
									},
									{
										field : 'visitUser',
										title : '外访员',
										width : 100
									},
								 	{
										field : 'contactMode',
										title : '外访员联系方式',
										width : 120
									}, 
									{
										field : 'applyTime',
										title : '申请日期',
										width : 120,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									},
									{
										field : 'state',
										title : '状态',
										width : 100,
										formatter : function(value, row, index) {
					      					return app.dictName('VISIT_STATE', value)
					      				}
									},
									{
										field : 'attachment',
										title : '附件',
										width : 120,
										formatter : function(value, row, index) {
												return '<a href="javascript:void(0)" class="easyui-linkbutton" onclick=attachmentManager("'
														+ row.id + '")>查看/上传</a>';
										}
									}, 
									{
										field : 'approveOpinion',
										title : '审批意见',
										width : 300
										
									},] ],
							onLoadSuccess : function() {
								
							}
							
						});

	});
	
	//附件管理
	function attachmentManager(caseId){
		var href = ctx+'/collection/upload/index?action=edit&&businessType=vis&&businessId='+caseId;
		var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'; 
		window.open(href);
	}
</script>
