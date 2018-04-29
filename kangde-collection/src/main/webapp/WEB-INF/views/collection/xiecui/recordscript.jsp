<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var _datagrid;
	var _dialog;
	var caseId = "${caseId}";
	var _form;
	var _search_form;
	//请求基础路径
	var baseUrl = ctx + "/collection/xiecui";

	$(function() {
		_form = $('#_form').form();
		_search_form = $('#_search_form').form();
		loadEntrust('#search_entrustId','all');
		loadOrgs();
		_datagrid = $('#_datagrid').datagrid({
			url : baseUrl + '/recordlist',
			fit : true,
			border : true,
			  queryParams:$.serializeObject(_search_form),
			method : 'GET',
			pagination : true,//底部分页
			rownumbers : true,//显示行数
			singleSelect : true,
			striped : true,//显示条纹
			remoteSort : true,//是否通过远程服务器对数据排序
			idField : 'id',
			 frozenColumns: [
			                  [
			                   
			                      {field:'operator',title:'附件管理',align:'center',width:80,
			                      	formatter:function(value, row, index){
			              				return '<span style="text-decoration: underline;"  onclick=attachmentManager("'+row.caseId+'");return false;>附件</span>';
			                      	}
			                  	}
			                 
			                  ]
			              ],
			columns : [ [ {
				field : 'id',
				title : 'id',
				width : 100,
				hidden : true
			},  {
				field : 'caseModel.caseCode',
				title : '案件序列号',
				width : 180,
				sortable:true,
				formatter: function(value,row,index){
            		return '<span style="text-decoration: underline;" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.caseId+'")>'+value+'</span>';
			    }

			},
			{
				field : 'caseModel.orgName',
				title : '风控方',
				width : 95,
				sortable:true
			},
			{field : 'entrustModel.name',title : '委托方',width : 120,sortable:true},
			
			{field : 'caseModel.caseMoney',title : '委案金额',width : 95,sortable:true,
				formatter:function(value, row, index){
            		return $.fmoney(value); 
					}	},
				{field : 'caseModel.caseName',title : '姓名',width : 95,sortable:true},
			
			{
				field : 'applyType',
				title : '协催类型',
				width : 95,
				sortable:true,
				formatter : function(value, row, index) {
					if (value == '11') {
						return '客服咨询';
					}
					if (value == '7') {
						return '公安协助';
					}
					if (value == '5') {
						return '法院协助';
					}
					if (value == '10') {
						return '主管协催';
					}
					return value;
				}
			},

			{
				field : 'applyContent',
				title : '申请内容',
				width : 280
			},
			{
				field : 'employeeInfo',
				title : '申请人',
				width : 95,
				formatter : function(value, row, index) {
					if (value) {
						return value.userName;
					}
					return row.applyUserName;
				}
			},
			{
				field : 'appTime',
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
			}, {
				field : 'hurryContent',
				title : '回复',
				width : 280,
			}, 
			{
				field : 'surUserName',
				title : '回复人',
				width : 95,
				sortable:true
			}, {
				field : 'surTime',
				title : '回复日期',
				width : 95,
				sortable:true,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatDate(value, 'yyyy-MM-dd');
					}
					return value;
				}
			}

			] ],
			  rowStyler: function(index,row){
		        	var color1=0;
		        	if (row.caseModel) {
							color1= row.caseModel.color;
						}
		            color=app.dictName('CASE_COLOR',color1);
		    		return 'color:'+color;
		    	},
		    	  onLoadSuccess: function () {
			        	$(this).datagrid('showTooltip').datagrid('columnMoving');
			        	app.unCheckAll(this);//取消所有选中
			        },
			onRowContextMenu : function(e, rowIndex, rowData) {
			},
			onDblClickRow : function(rowIndex, rowData) {
				app.dataGridSelectOne(_datagrid, function(row) {
					showDetail(row.id);
				});
			}
		})

	});
	function reply(id) {
		var inputUrl = baseUrl + "/input?id=" + id;
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_dialog = app.openFormDialog(function() {
			_form.submit();//提交表单
		}, '回复', 700, 300, inputUrl, 'GET', function() {
			//初始化会话页面组件
			formInit();
		});
	}

	//初始化表单
	function formInit() {
		_form = $('#_form').form({
			//表单提交地址
			url : baseUrl + '/updateXiecui',
			//表单提交数据之前回调函数
			onSubmit : function() {
				$.messager.progress({
					title : '提示信息！',
					text : '数据处理中，请稍后....'
				});

				var isValid = $(this).form('validate');
				if (!isValid) {
					$.messager.progress('close');
					return false;
				}

				return true;
			},
			//表单成功提交回调函数
			success : function(data) {
				$.messager.progress('close');
				//渲染结果
				app.renderAjax(data, function(json) {
					_dialog.dialog('destroy');//销毁对话框
					app.reload(_datagrid);//重新加载列表数据
				});
			}
		});
	}

	//搜索
	function search() {
		var params = $.serializeObject(_search_form);
		app.load(_datagrid, params);

	}

	//加载组织机构
	function loadOrgs() {
		var url = ctx + '/sys/organization/getOrganizationList';
		$('#orgId').combotree({
			url : url,
			multiple : false,//是否可多选
			editable : false,//是否可编辑
			width : 150,
			valueField : 'id',
			value : '${CURRENT_USER.orgId}',
			loadFilter: function(rows){
	            return convert(rows);
	        },
			onChange : function(nv, ov) {
				//重新渲染用户列表
				loadCaseAssigned(nv);
			}
			
		});
	}
	//加载委托方
	function loadEntrust(domId,type){
		var url = ctx+'/sys/entrust/entrustlist?selectType='+type;
		$(domId).combobox({
	        url:url,
		    multiple:false,//是否可多选
		    editable:true,//是否可编辑
		    width:145,
		    valueField:'value',
	        textField:'text'
		});
	}
	//附件管理
	function attachmentManager(caseId){
		var href = ctx+'/collection/upload/index?action=edit&&businessType=bankCase&&businessId='+caseId;
		var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'; 
		window.open(href);
	}
</script>
