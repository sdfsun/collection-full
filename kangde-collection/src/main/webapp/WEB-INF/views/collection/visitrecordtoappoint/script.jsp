<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	
	var _search_form;
	var plan_dialog;
	//请求基础路径
	var baseUrl = ctx + "/collection/visitrecordtoappoint";
	$(function() {
		_search_form = $('#_search_form').form({
			novalidate : true
		});
		loadEntrust('#search_entrustId', 'all');
		loadVisitUser();
		loadDataGrid();
	});

	function loadDataGrid() {
		_datagrid = $('#_datagrid')
				.datagrid(
						{
							url : baseUrl + '/queryToAppoint',
							fit : true,
							method : 'GET',
							singleSelect : false,
							queryParams : $.serializeObject(_search_form),
							pagination : true,//底部分页
							rownumbers : true,//显示行数
							fitColumns : false,//自适应列宽
							striped : true,//显示条纹
							remoteSort : true,//是否通过远程服务器对数据排序
							idField : 'id',
							columns : [ [
									{
										field : 'id',
										checkbox : true
									},
									{
										field : 'caseModel.collecStateId',
										title : '风控状态',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return app.dictName('CS_STATE',
													value)
										}
									},
									{
										field : 'caseModel.caseCode',
										title : '案件序列号',
										width : 180,
										sortable:true,
										formatter : function(value, row, index) {
											if (row.state == 1) { //1为暂停状态， 不允许查看详情
												return value;
											}
											return '<span style="text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='
													+ row.caseId
													+ '")>'
													+ value + '</span>';
										}
									},
									{
										field : 'caseModel.caseMoney',
										title : '委案金额',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return $.fmoney(value);
										}
									},
									{
										field : 'paidNum',
										title : '已还款',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											return $.fmoney(value);
										}
									},
									{
										field : 'name',
										title : '外访姓名',
										width : 95
									},
									{
										field : 'addressModel.relation',
										title : '关系',
										width : 60
									},

									
									{
										field : 'adrCat',
										title : '地址类别',
										width : 95
									},
									{
										field : 'visitAddress',
										title : '外访地址',
										width : 280,
										sortable:true
									},
									{
										field : 'require',
										title : '外访要求',
										width : 280
									},
									
									{
										field : 'employeeInfo.userName',
										title : '风控员',
										width : 95
									},

									{
										field : 'applyTime',
										title : '申请日期',
										width : 95,
										sortable:true,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd');
											}
											return value;
										}
									}, ] ],
							toolbar : [ 
							  <shiro:hasPermission name="visitrecordtoappoint:PlanToVisit">
							    {
								text : '外访排程',
								iconCls : 'eu-icon-waifangpaicheng',
								handler : function() {
									PlanToVisit()//外访排程
								}
							},
							  </shiro:hasPermission>
							<shiro:hasPermission name="visitrecordtoappoint:cancelVisit">	
							{
								text : '撤销外访',
								iconCls : 'eu-icon-chexiaowaifang',
								handler : function() {
									cancelVisit();
								}
							}
							</shiro:hasPermission>
							],
							rowStyler : function(index, row) {
								var color1 = 0;
								if (row.caseModel) {
									color1 = row.caseModel.color;
								}
								color = app.dictName('CASE_COLOR', color1);
								return 'color:' + color;
							},
							onLoadSuccess : function() {
								$(this).datagrid('showTooltip').datagrid(
										'columnMoving');
								app.unCheckAll(this);//取消所有选中
							}
						});
	}

	
	
	//外访排程
	function PlanToVisit(rows) {
		
		var rows = _datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			var ids = [];
			$.each(rows, function(i, row) {
				ids.push(row.id);
			});
			
			plan_dialog = app.openFormDialog(function() {
				
	 			var isValid = $('#plan_form').form('validate');
	 			if (!isValid) {
	 				return;
	 			}
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});
				var userid=$('#visitUserId').combobox("getValues")+"";
					$.ajax({
							type : 'post',
							url : baseUrl + '/appointVisitor',
							data : {
								"ids" : ids,
								"visitTime":$('#visitTime').datebox("getValue"),
								"userid":userid
							},
							dataType : 'json',
							success : function(data) {
								$.messager.progress('close');
								app.renderAjax(data, function(json) {
									plan_dialog.dialog('destroy');
									app.reload(_datagrid);
								});
							}
						});
				
			}, '外访排程', 500, 200, baseUrl + "/input/" , 'GET', function() {
				loadVisitUser();
			});
			
		} else {
			eu.showAlertMsg("请选择要操作的数据！",'warning');
		}
		
	
	}
	
	//撤销外访
	function cancelVisit() {
		var rows = _datagrid.datagrid('getSelections');
		if (rows.length > 0) {
			
			var ids = [];
			$.each(rows, function(i, row) {
				ids.push(row.id);
			});
			
			_dialog = app.openFormDialog(function(){
				
				var isValid = $('#_form').form('validate');
	 			if (!isValid) {
	 				return;
	 			}
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});
				var Opinion= $('#approveOpinion').textbox("getValue");
				$.ajax({
					type : 'post',
					url : baseUrl + '/cancelVisit',
					data : {
						"ids" : ids,
						"approveOpinion":Opinion
					},
					dataType : 'json',
					success : function(data) {
						$.messager.progress('close');
						app.renderAjax(data, function(json) {
							_dialog.dialog('destroy');
							app.reload(_datagrid);
						});
					}
				});
       	    	
       	    	
       	   	}, '撤销外访',460, 280,baseUrl+"/inputpoint/", 'GET',function(){
       	   	
       	   	},"撤销","关闭");	
		}else {
			eu.showAlertMsg("请选择要操作的数据！", 'warning');
		}
	}
		
//初始化表单
 function inputpoint() {
 	_form = $('#_form').form({
 		//表单提交地址
 		
 		//表单提交数据之前回调函数
 		onSubmit : function() {
 			$.messager.progress({
 				title : '提示信息！',
 				text : '数据处理中，请稍后....'
 			});
 			//$(this).form('enableValidation');
 			var isValid = $(this).form('validate');
 			if (!isValid) {
 				$.messager.progress('close');
 			}
 			return isValid;
 		},
 		   //表单成功提交回调函数
         success: function(data){
         	 $.messager.progress('close');
             //渲染结果
             app.renderAjax(data,function(json){
             	_dialog.dialog('destroy');//销毁对话框
             	app.reload(_datagrid);//重新加载列表数据
             });
         }
 	});
 }
	//搜索
	function search() {
		app.load(_datagrid, $.serializeObject(_search_form));
	}

	//-----------以下方法为通用的加载方法-------------------------------------------
	//加载委托方
	function loadEntrust(domId, type) {
		var url = ctx + '/sys/entrust/entrustlist?selectType=' + type;
		$(domId).combobox({
			url : url,
			multiple : false,//是否可多选
			editable:true,//是否可编辑
			width : 150,
			valueField : 'value',
			textField : 'text'
		});
	}
	


	function loadVisitUser() {
		$('#visitUserId').combobox({
			url : ctx + '/sys/employeeInfo/orgusersByOrg?orgId=${CURRENT_USER.orgId}',
			required:true,
			multiple : true,//是否可多选
			editable : false,//是否可编辑
			width : 220,
			valueField : 'value',
			textField : 'text',
			editable : true,
			delay : 0,
			separator : ',',
			filter: function(q, row){  
				    var opts = $(this).combobox('options');  
				    return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配  
			}, 
		});
	}
</script>
