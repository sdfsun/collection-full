<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _datagrid_collector;
	var _form;
	var _search_form;
	var _dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/phonerecord";

	$(function() {
	
		//查询当前风控员风控记录
		initCollectorPhoneRecord();
		//案件所有催记
		initCasePhoneRecord();
		_form = $('#_form').form();
		
		$('#ptpTime').datebox({
		})
		$('#nextFollowTime').datebox({
			buttons: buttons
		})
		
		
	});
	
	
	
	function toCP(id) {
		$.ajax({
			type : "POST",
			url : baseUrl + '/toCP',
			data : {
				id : id
			},
			dataType : "json",
			async : false,
			success : function(data) {
				//渲染结果
				app.renderAjax(data, function(json) {
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}

	function submitForm() {

		$('#_form').form(
				"submit",
				{
					//表单提交地址
					url : baseUrl + '/phoneRecordAction?caseId=${caseId}',
					//表单提交数据之前回调函数
					onSubmit : function() {
						$.messager.progress({
							title : '提示信息！',
							text : '数据处理中，请稍后....'
						});
						
						var isValid = $(this).form('validate');

						//PTP金额和PTP日期要么同时填写, 要么都不要填写
						var ptpMoney = $("#ptpMoney").val();
						var ptpTime = $("#ptpTime").datebox("getValue");
						if ((ptpMoney == '' && ptpTime != '')
								|| (ptpMoney != '' && ptpTime == '')) {
							isValid = false;
							$.messager.alert('提示', '请填写PTP金额或PTP日期!', 'info');
						}
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					//表单成功提交回调函数
					success : function(data) {
						$.messager.progress('close');
						_datagrid_collector.datagrid('reload');
						//渲染结果
						var result = $.parseJSON(data);
						if(result.code=='0'){
							app.renderAjax(data, function(json) {
								app.reload(_datagrid);//重新加载列表数据
							});
						}else{
							app.renderAjax(data, function(json) {
								app.reload(_datagrid);//重新加载列表数据
							});
							clearForm();
						}
						
					}
				});

	}
	function clearForm() {
		_form.form('reset');
	}

	function initCollectorPhoneRecord() {
		_datagrid_collector = $('#_datagrid_by_collector').datagrid({
			url : baseUrl + '/queryByCollector?caseId=${caseId}',
			method : 'GET',
			nowrap:false,
			fit : true,
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : false,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
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
			},
			{
				field : 'name',
				title : '姓名',
				width : 60
			}, {
				field : 'relation',
				title : '关系',
				width : 60
			}, {
				field : 'contact',
				title : '电话',
				width : 100
			}, {
				field : 'content',
				title : '记录',
				width : 220
			} ] ],
			onLoadSuccess : function(data) {
				//$(this).datagrid('showTooltip');
			}
		});
	}

	function initCasePhoneRecord() {
		_datagrid = $('#_datagrid_byCaseId').datagrid({
			url : baseUrl + '/query' + "?caseId=${caseId}",
			nowrap:false,
			method : 'GET',
			fit : true,
			border:false,
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			fitColumns : true,
			striped : true,//显示条纹
			remoteSort : false,//是否通过远程服务器对数据排序
			columns : [ [ {
				field : 'id',
				title : 'ID',
				hidden : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100,
			}, {
				field : 'createTime',
				title : '时间',
				width : 140,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
					}
					return value;
				}
			},
			{
				field : 'contact',
				title : '电话/地址',
				width : 200
			},

			{
				field : 'typeId',
				title : '风控结果',
				width : 110,
				formatter : function(value, row, index) {
 					return app.dictName('COLLECTION_RESULT', value)
 				}
			}, {
				field : 'collecStateId',
				title : '风控状态',
				width : 150,
				formatter : function(value, row, index) {
					return app.dictName('COLLECTION_STATE', value)
				}
			},

			{
				field : 'content',
				title : '记录',
				width : 420
			},

			{
				field : 'collectorName',
				title : '风控员',
				width : 100
			} ,
			{
				field : 'creatorName',
				title : '操作人',
				width : 100
			} 
			] ],
		});
	}
	
	//导出所选案件
	function exportCase(){
	    var rows = _datagrid.datagrid('getSelections');
	    var row = rows[0];
	    var caseId=row.caseId;
		app.dataGridSelect(_datagrid, function(rows){
		        app.downLoadFile({
		        	url:baseUrl+'/exportSelectedExcel', //请求的url
		        	data:{'caseId':caseId},//要发送的数据
		        });
		});
	};
	
	function formatPhonerecordPrCat(value, row, index){
		//	if (value == '0') {
		//		return '电催';
		//	}
		//	if (value == '1') {
		//		return '外访';
		//	}
			return '电催';
	}
	
	
	function callOut() {
		
		var tel = $("#contactId").textbox('getValue');
		if (tel == null || tel == '') {
			$.messager.alert('Warning', '请输入电话号码！');
			return;
		}
		var url=ctx+"/collection/linkman/callOut?tel=" + tel+"&caseId=${caseId}";
		$.ajax({
					type : "POST",
					url :  url,
					dataType : "json",
					async : true,
					success : function(data) {
						$.messager.alert('提示', data.msg);
					}
				});
	}
	
	
</script>
