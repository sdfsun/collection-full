<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
<%@ include file="/common/taglibs.jsp"%>
var _datagrid;
var _form;
var _color_form;
var _search_form;
var _dialog;
var _color_dialog;
var division_dialog;
var change_state_form;

//动态列
var dynColumns;
//请求基础路径
var baseUrl = ctx+"/collection/case";
$(function () {
    _form = $('#_form').form();
    _color_form = $('#_color_form').form();
    _search_form = $('#_search_form').form();
    
    //获取动态列
    app.ajax({
        type: "POST",
        url: ctx + '/sys/column/caseColumns',
        data: {},
        dataType: "json",
        async: false,
        success: function(data){
        	dynColumns = data;
        }
    });
    loadEntrust('#search_entrustId','all');
    loadOrgs();
    loadCaseAssigned('${CURRENT_USER.orgId}');
   // statistics($.serializeObject(_search_form));
   
    findBacthCode('#batchCode');
    //初始化列
    if(dynColumns){
    	for(var i=0;i<dynColumns.length;i++){
    		var col = dynColumns[i];
    		//案件状态
    		if('state'==col.field){
    			col.formatter=function(value, row, index){
    				return app.dictName('CASE_STATE',value);
        		}
    		}
    		//案件序号
    		else if('caseCode'==col.field){
    			col.formatter=function(value, row, index){
    				var str = '<shiro:hasPermission name="case:detail">';
    				str+= '<span style="text-decoration: underline;cursor:pointer" onclick=window.open("${pageContext.request.contextPath}/collection/casedetail?caseId='+row.id+'")>'+value+'</span>';
    				str+= '</shiro:hasPermission>';
    				str += '<shiro:lacksPermission name="case:detail">';
    				str+= value;
    				str+= '</shiro:lacksPermission>';
    				return str;
        		}
    		}
    		//批次号
    		else if('batchCode'==col.field){
    			col.formatter=function(value, row, index){
    				return openBatchPage(value, row, index);
        		}
    		}
    		//风控状态
    		else if('collecStateId'==col.field){
    			col.formatter=function(value, row, index){
    				return app.dictName('CS_STATE',value);
        		}
    		}
    		//委案日期
    		else if('caseDate'==col.field){
    			col.formatter=function(value, row, index){
					return fmDate(value);
        		}
    		}
    		
    		//退案日期
    		else if('caseBackdate'==col.field){
    			col.formatter=function(value, row, index){
					return fmDate(value);
        		}
    		}
    		
    		//最后通电时间
    		else if('lastPhone'==col.field){
    			col.formatter=function(value, row, index){
					return fmDateTime(value);
        		}
    		}
    		
    		//委案金额
    		else if('caseMoney'==col.field){
    			col.formatter=function(value, row, index){
						return fmmoney(value);
        	}
    		}
    		//剩余还款
    		else if('surplusMoney'==col.field){
    			col.formatter=function(value, row, index){
    				return fmmoney(value);
        	}
    		}
    		//ptpMoney
    		else if('ptpMoney'==col.field){
    			col.formatter=function(value, row, index){
    				return fmmoney(value);
        	}
    		}
    		//cpMoney
    		else if('cpMoney'==col.field){
    			col.formatter=function(value, row, index){
    				return fmmoney(value);
        	}
    		}
    		//paidNum
    		else if('paidNum'==col.field){
    			col.formatter=function(value, row, index){
    				return fmmoney(value);
            	}
    		}
    		else if('agentRate'==col.field){
    			col.formatter=function(value, row, index){
    				if(!value){
    					return '0.00';
    				}
    				return value;
            	}
    		}
    		else if('brokerage'==col.field){
    			col.formatter=function(value, row, index){
    				if(row.agentRate && row.paidNum){
    					return fmmoney(row.agentRate*row.paidNum);
    				}
    				return '0.00';
            	}
    		}
    		
    	}
    	
    }
    
    //数据列表
    _datagrid = $('#_datagrid').datagrid({
        url: '',
        fit: true,
        method:'POST',
        resizeHandle: 'both',
        queryParams:$.serializeObject(_search_form),
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        remoteSort:true,//是否通过远程服务器对数据排序
        idField: 'id',
        pageList :[50,200,500,1000],
        frozenColumns: [
            [
                {field: 'id', checkbox: true},
                <shiro:hasPermission name="case:attach">
                {field:'operator',title:'操作',align:'center',width:80,
                	formatter:function(value, row, index){
        				return '<span style="text-decoration: underline;"  onclick=attachmentManager("'+row.id+'");return false;>附件</span>';
                	}
            	}
                </shiro:hasPermission>
            ]
        ],
        columns: [dynColumns],
        toolbar:'#_toolbar',
        rowStyler: function(index,row){
            var color=app.dictName('CASE_COLOR',row.color);
            if(!color){
            	color='none';
            }
            var rowstyle='color:'+color;
           
            if(row.state==3){
            	rowstyle+=';color:#CACACA;text-decoration:line-through;'
            }
    		return rowstyle;
    	},
        onLoadSuccess: function () {
        	$(this).datagrid('showTooltip').datagrid('columnMoving');
        	app.unCheckAll(this);//取消所有选中
        }
    });

});
//打开修改状态视图
function changeStateView(title,state){
	app.dataGridSelect(_datagrid,function(rows){
		 var isOk = true;
		 var caseIds = new Array();
		 for(var i=0;i<rows.length;i++){
			 if(state==rows[i].state){
	        		eu.showAlertMsg("您选中的数据中包含【"+title+"】的数据，不能重复操作",'warning'); 
	        		isOk = false;
	        		break;
        	 }
		 }
         if(isOk==false){
        	 return;
         }
		_dialog = app.openFormDialog(function(){
			change_state_form.form('submit');
		}, title, 500, 300, baseUrl+"/changeState", 'GET', function(){
			$('#numDisplay').text('共选中【'+rows.length+'】条数据');
			initChangeStateForm(rows, state);
		}, '确定', '取消');
	});
}


//暂停案件
function pause(){
	changeStateView('暂停案件', 1);
}
//撤回案件
function revocation(){
	changeStateView('撤回案件', 5);
}
//恢复案件
function recover(){
	changeStateView('恢复案件', 0);
}
//结清案件
function settle(){
	changeStateView('结清案件', 4);
}

//所选分案
function selectDivision(){
	app.dataGridSelect(_datagrid, function(rows){
		division_dialog=app.openFormDialog(function(){
			//数据校验
			var node = $('#userId').tree('getSelected');
			if(node){
				if(node.attributes.isUser){
					var caseIds = new Array();
	                $.each(rows, function (i, row) {
	                	caseIds[i] = row.id;
	                });
	                app.ajax({
	                    url: baseUrl+"/division",
	                    type: 'post',
	                    data: {'caseIds': caseIds,'userId':node.id},
	                    dataType: 'json',
	                    success: function (data) {
	                    	//渲染结果
	                        app.renderAjax(data,function(json){
	                        	 _datagrid.datagrid('load');
	                        	 division_dialog.dialog('destroy');//销毁对话框
	                        });
	                    }
	                });
				}else{
					eu.showAlertMsg("请选择用户！",'warning');
				}
			}else{
				eu.showAlertMsg("请选择用户！",'warning');
			}
			
		},'所选分案', 500, 400, baseUrl+"/division", 'GET', function(){
			orgUserCombotree();
		},'确定','取消');
	});
} 
//查询结果分案
function divisionForQuery(){

	app.datagridHasRow(_datagrid, function(){
		division_dialog=app.openFormDialog(function(){
			//数据校验
			var node = $('#userId').tree('getSelected');
			if(node){
				if(node.attributes.isUser){
					var queryParams = _datagrid.datagrid("options").queryParams;
					queryParams.userId = node.id;
				
	                app.ajax({
	                    url: baseUrl+"/divisionForQuery",
	                    type: 'post',
	                    data: queryParams,
	                    dataType: 'json',
	                    success: function (data) {
	                    	//渲染结果
	                        app.renderAjax(data,function(json){
	                        	 _datagrid.datagrid('load');
	                        	 division_dialog.dialog('destroy');//销毁对话框
	                        });
	                    }
	                });
				}else{
					eu.showAlertMsg("请选择用户！",'warning');
				}
			}else{
				eu.showAlertMsg("请选择用户！",'warning');
			}
			
		},'查询结果分案', 500, 400, baseUrl+"/queryDivision", 'GET', function(){
			orgUserCombotree();
		},'确定','取消');
	});
}

function initDivisionDiglog(){
	//获取当前第几步
	var $stepMark = $('#stepMark');
	var nodes = $('#userIds').tree('getChecked');
	if($stepMark.val()=='1'){
		//数据校验
		if(nodes.length > 0){
			step2(nodes);
			$stepMark.val('2');
		}else{
			eu.showAlertMsg("请选择用户！",'warning');
		}
	}else if($stepMark.val()=='2'){
		var $step2 = $('#step2');
		var boxs = $step2.find('div');
		var res = 0;
		for(var i=0;i<boxs.length;i++){
			var $div = $(boxs[i]);
			var val = $div.find('input').val();
			res += parseInt(val);
			//var userId = $div.attr('id');
		}
		if(res){
			if(res==100){
				//创建用户集合
				var userList = [];
				for(var i=0;i<boxs.length;i++){
					var $div = $(boxs[i]);
					var val = $div.find('input').val();
					var userId = $div.attr('id');
					var user = new Object();
					user.id = userId;
					user.rate = val;
					user.userName = $div.find('span').text();
					userList.push(user);
				}
				
				var queryParams = _datagrid.datagrid("options").queryParams;
				queryParams.userList = JSON.stringify(userList);
				queryParams.hasNotDivision = $("#hasNotDivision").is(":checked");
				var divisionWay = $('input[name=divisionWay][type=radio]:checked').val();
				queryParams.divisionWay = divisionWay;
				autoDivisionCompute(queryParams);
			}else{
				eu.showAlertMsg('当前总值为:'+res+',输入的总值之和必须等于100','error');
			}
		}
		
	}else if($stepMark.val()=='3'){
		submitAutoDivision();
	}
	
}

//所选自动分案
function selectDivisionForAuto(){
	app.dataGridSelect(_datagrid, function(rows){
		division_dialog=openAutoCaseDialog(initDivisionDiglog,'所选自动分案', 500, 400, baseUrl+"/autoDivision", 'GET', function(){
			//统计数据,案件总量,金额,已分配为分配数量
			var queryParams = _datagrid.datagrid("options").queryParams;
			var caseIds = '';
            $.each(rows, function (i, row) {
            	caseIds += row.id+',';
            });
            caseIds = caseIds.substring(0,caseIds.length-1);
			queryParams.caseIds = caseIds;
			$.ajax({
		        url: baseUrl+"/statisticsCaseCounts",
		        type: 'post',
		        data: queryParams,
		        dataType: 'json',
		        success: function (data) {
		        	//渲染结果
		            app.renderAjax(data,function(json){
		            	var obj=json.obj;
		            	$('#caseTotalCount').html(obj.caseTotalCount);
		            	$('#caseTotalMoney').html($.fmoney(obj.caseTotalMoney));
		            	$('#assignedCount').html(obj.assignedCount);
		            	$('#undistributedCount').html(obj.undistributedCount);
		            },false);
		        }
		    });
			
			orgUserCombotreeForMulti();
		},function(){
			//获取当前第几步
			var $stepMark = $('#stepMark');
			var $step2 = $('#step2');
			if($stepMark.val()=='2'){
				$('#pre').linkbutton('disable');
				$step2.hide();
				$('#step1').show();
				$stepMark.val('1');
			}else if($stepMark.val()=='3'){
				var $step3 = $('#step3');
				$('#nextBtn span:last').html('下一步');
				$step2.show();
				$step3.hide();
				$stepMark.val('2');
				var $table = $step3.find('table');
				$table.find('tr:not(:first)').remove();
				$('#hasNotDivision').removeAttr('disabled');
            	$('[name=divisionWay]').removeAttr('disabled');
			}
		});
	});
}
//所查自动分案
function resultAutoDivision(){
	app.datagridHasRow(_datagrid, function(){
		division_dialog=openAutoCaseDialog(initDivisionDiglog,'所查自动分案', 500, 400, baseUrl+"/autoDivision", 'GET', function(){
			//统计数据,案件总量,金额,已分配为分配数量
			var queryParams = _datagrid.datagrid("options").queryParams;
			$.ajax({
		        url: baseUrl+"/statisticsCaseCounts",
		        type: 'post',
		        data: queryParams,
		        dataType: 'json',
		        success: function (data) {
		        	//渲染结果
		            app.renderAjax(data,function(json){
		            	var obj=json.obj;
		            	$('#caseTotalCount').html(obj.caseTotalCount);
		            	$('#caseTotalMoney').html($.fmoney(obj.caseTotalMoney));
		            	$('#assignedCount').html(obj.assignedCount);
		            	$('#undistributedCount').html(obj.undistributedCount);
		            },false);
		        }
		    });
			
			orgUserCombotreeForMulti();
		},function(){
			//获取当前第几步
			var $stepMark = $('#stepMark');
			var $step2 = $('#step2');
			if($stepMark.val()=='2'){
				$('#pre').linkbutton('disable');
				$step2.hide();
				$('#step1').show();
				$stepMark.val('1');
			}else if($stepMark.val()=='3'){
				var $step3 = $('#step3');
				$('#nextBtn span:last').html('下一步');
				$step2.show();
				$step3.hide();
				$stepMark.val('2');
				var $table = $step3.find('table');
				$table.find('tr:not(:first)').remove();
				$('#hasNotDivision').removeAttr('disabled');
            	$('[name=divisionWay]').removeAttr('disabled');
			}
		});
	});
} 

//自动分案第二步
function step2(nodes){
	$('#step1').hide();
	$('#pre').linkbutton('enable');
	var $step2 = $('#step2');
	$step2.find('div').remove();
	$.each(nodes, function(i, node){
		var $divDom = $('<div id='+node.id+'></div>');
		$divDom.append('<span style="width:120px;display:inline-block;">'+node.text+'</span>')
		$divDom.append('&nbsp;')
		$divDom.append('<input style="width:130px;" class="easyui-numberbox" data-options="min:1,max:100,required:true"/>');
		$divDom.append('%')
		$step2.append($divDom)
		$.parser.parse('#step2');
	});
	$step2.show();
	
}

//计算自动分案表单结果
function autoDivisionCompute(params){
    app.ajax({
        url: baseUrl+"/autoDivisionCompute",
        type: 'post',
        data: params,
        dataType: 'json',
        success: function (data) {
        	//渲染结果
            app.renderAjax(data,function(json){
            	$('#step2').hide();
            	//禁用表单所有可以编辑项..
            	$('#stepMark').val('3');
            	$('#hasNotDivision').attr('disabled','disabled');
            	$('[name=divisionWay]').attr('disabled','disabled');
            	//获取按钮,重置按钮名称
            	$('#nextBtn span:last').html('&nbsp;&nbsp;完成&nbsp;&nbsp;');
            	var btn = division_dialog.dialog('options').buttons[0];
            	var $step3 = $('#step3');
            	var $table = $step3.find('table');
            	$.each(json.obj, function(i, result){
            		var $tr = $('<tr></tr>');
            		$tr.attr('userId',result.userId);
            		$tr.attr('caseIds',result.caseIds);
            		$tr.append('<td>'+result.userName+'</td>');
            		$tr.append('<td>'+result.caseNum+'</td>');
            		$tr.append('<td>'+result.caseMoney+'</td>');
            		$tr.append('<td>'+result.rate+'%</td>');
            		$tr.find('td').attr('align','center');
            		$table.append($tr)
            	});
            	$step3.show();
            },false);
        }
    });
}
//step3
function submitAutoDivision(){
	var listUserGrop = [];
	//提交自动分案表单
	var $step3 = $('#step3');
	var $table = $step3.find('table');
	var $trs = $table.find('tr:not(:first)');
	for(var i=0;i<$trs.length;i++){
		var $temp =$($trs[i]);
		var userGrop = new Object();
		userGrop.caseIds = $temp.attr('caseIds');
		userGrop.userId = $temp.attr('userId');
		listUserGrop.push(userGrop);
	}
	var jsonStr = JSON.stringify(listUserGrop);
	app.ajax({
        url: baseUrl+"/autoDivision",
        type: 'post',
        data: {"listUserGrop":jsonStr},
        dataType: 'json',
        success: function (data) {
        	//渲染结果
            app.renderAjax(data,function(json){
            	 var caseIds = _datagrid.datagrid("options").queryParams.caseIds;
            	 if(caseIds){
            		 _datagrid.datagrid("options").queryParams.caseIds = null;
            	 }
            	 _datagrid.datagrid('load');
            	 division_dialog.dialog('destroy');//销毁对话框
            });
        }
    });
	
}

//加载用户(单选)
function orgUserCombotree(){
	$('#userId').tree({    
		url:ctx+"/sys/employeeInfo/orgUserCombotree",
		onSelect:function(node){
			if(!node.attributes.isUser){
				$('#userId').tree('toggle', node.target);
			}
		},
		onLoadSuccess:function(){
			//设置悬浮
			var children = $('#userId').tree('getChildren');
			for(var i=0;i<children.length;i++){
				var node = children[i];
				if(node.attributes.isUser){
					$(node.target).attr('title', function () {
						var text = node.text+'-'+node.attributes.number;
						return text; 
					});
				}
			}
		}
	}); 
}
//加载用户(多选)
function orgUserCombotreeForMulti(){
	$('#userIds').tree({    
		url:ctx+"/sys/employeeInfo/orgUserCombotree",
		onlyLeafCheck:true,
		cascadeCheck:false,
		checkbox:true,
		lines:true,
		onSelect:function(node){
			if(!node.attributes.isUser){
				$('#userIds').tree('toggle', node.target);
			}else{
				//$('#userIds').tree('uncheck',node.target);
			}
		},
		onLoadSuccess:function(){
			//设置悬浮AND删除非用户节点的checkbox
			var children = $('#userIds').tree('getChildren');
			for(var i=0;i<children.length;i++){
				var node = children[i];
				if(node.attributes.isUser){
					$(node.target).attr('title', function () {
						var text = node.text+'-'+node.attributes.number;
						return text; 
					});
				}else{
					$('#userIds').tree('removeCheck',node.target);//移除checkbox
				}
			}
		}
	}); 
}

//加载组织机构
function loadOrgs(){
	var url = ctx+'/sys/organization/getOrganizationList';
	$('#orgId').combotree({
        url:url,
	    multiple:false,//是否可多选
	    editable:false,//是否可编辑
	    width:140,
	    panelWidth:200,
	    panelHeight:400,
        valueField:'id',
        value:'${CURRENT_USER.orgId}',
        loadFilter: function(rows){
            return convert(rows);
        },
        onChange:function(nv, ov){
        	//重新渲染用户列表
        	loadCaseAssigned(nv);
        }
	});
}



//加载用户
function loadCaseAssigned(orgId){
	var url = ctx+'/sys/employeeInfo/orgusersByOrg?orgId='+orgId;
	$('#caseAssignedId').combobox({    
		url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:140,
	    valueField:'value',
        textField:'text',
	    delay:0,
	    separator:','
	});

}

//搜索
function search() {
	var options = _datagrid.datagrid('options');
	//url 需要自己定义
	options.url =baseUrl + '/query';
	
	//app.load(_datagrid,$.serializeObject(_search_form));
	
	var params = $.serializeObject(_search_form);
	//重新加载datagrid
	app.load(_datagrid,params);
	statistics(params);
}
//统计
function statistics(params){
	//统计重置
	$.ajax({
        url: baseUrl+"/statistics",
        type: 'post',
        data: params,
        dataType: 'json',
        success: function (data) {
        	//渲染结果
            app.renderAjax(data,function(json){
            	var obj=json.obj;
            	$('#total_case_count').html(obj.total_case_count);
            	$('#total_case_money').html($.fmoney(obj.total_case_money));
            	$('#already_case_count').html(obj.already_case_count);
            	$('#total_already_money').html($.fmoney(obj.total_already_money));
            	$('#total_cp_money').html($.fmoney(obj.total_cp_money));
            	$('#total_ptp_money').html($.fmoney(obj.total_ptp_money));
            },false);
        }
    });
}

//附件管理
function attachmentManager(caseId){
	var href = ctx+'/collection/upload/index?action=edit&&businessType=bankCase&&businessId='+caseId;
	var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>'; 
	window.open(href);
}
//初始化改变状态表单
function initChangeStateForm(rows,state){
	change_state_form = $('#change_state_form').form({
       url: baseUrl+"/changeState",
       onSubmit: function (param) {
           $.messager.progress({
               title: '提示信息！',
               text: '数据处理中，请稍后....'
           });
           var isValid = $(this).form('validate');
           if (!isValid) {
               $.messager.progress('close');
           }
           var caseIds = new Array();
           $.each(rows, function (i, row) {
           	caseIds[i] = row.id;
           });
           param.caseIds = caseIds;
           param.state = state;
           return isValid;
       },
       success: function (data) {
           $.messager.progress('close');
           //渲染结果
           app.renderAjax(data,function(json){
	       	 _dialog.dialog('destroy');
           	 _datagrid.datagrid('load');
           });
       }
   });
}

//更新案件状态
function changeState(rows,state){
	var caseIds = new Array();
    $.each(rows, function (i, row) {
    	caseIds[i] = row.id;
    });
	app.ajax({
        url: baseUrl+"/changeState",
        type: 'post',
        data: {'caseIds': caseIds,'state':state},
        dataType: 'json',
        success: function (data) {
        	//渲染结果
            app.renderAjax(data,function(json){
            	 _datagrid.datagrid('load');
            });
        }
    });
}

//委托方 
function loadEntrust(domId,type){
	var url = ctx+'/sys/entrust/entrustlist?selectType='+type;
	$(domId).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:140,
	    valueField:'value',
        textField:'text'
	});
} 

//弹出自动分案对话窗口
function openAutoCaseDialog(saveFun,title, width, height, url,method,onLoadFun,preFun){
    var _dialog = $('<div/>').dialog({
        title:title,
        top:20,
        width : width,
        height:height,
        modal : true,
        maximizable:true,
        resizable:true,
        href : url,
        method:method,
        buttons : [ {
        	id:'pre',
            text : '上一步',
            disabled:true,
            align:'left',
            //iconCls : 'easyui-icon-save',
            handler : function() {
            	//如果有函数,执行,没有略过
            	if(preFun){
            		preFun();
            	}
            }
        },{
            text : '下一步',
            id:'nextBtn',
            //iconCls : 'easyui-icon-save',
            handler : function() {
            	//如果有函数,执行,没有略过
            	if(saveFun){
            		saveFun();
            	}
            }
        },{
            text : '取&nbsp;&nbsp;消',
            handler : function() {
                _dialog.dialog('destroy');
            }
        }],
        onClose : function() {
            _dialog.dialog('destroy');
        },
        onLoad:function(){
        	//如果有函数,执行,没有略过
        	if(onLoadFun){
        		onLoadFun();
        	}
        }
    });
    return _dialog;
};

//导出案件encodeURI(url)
function exportCase(type){
	var url = ctx+'/sys/template/exportExcel?gropNo=case_info&name='+encodeURI('案件模板');
	if(type==1){
		app.dataGridSelect(_datagrid, function(rows){
			_dialog = app.openFormDialog(function(){
				exportSelected();
			}, '导出所选案件', 600, 150, url, 'GET', function(){}, '导出案件', '取消');
		});
	}else{
		app.datagridHasRow(_datagrid, function(){
			_dialog = app.openFormDialog(function(){
				exportQuery();
			}, '导出所查案件', 600, 150, url, 'GET', function(){}, '导出案件', '取消');
		});
	}
}
//导出所选案件
function exportSelected(){
	app.dataGridSelect(_datagrid, function(rows){
        var isValid = $('#template_form').form('validate');
        if(isValid){
        	var caseIds = new Array();
            $.each(rows, function (i, row) {
            	caseIds[i] = row.id;
            });
			var templateId = $('#templateId').combobox('getValue');
	        app.downLoadFile({
	        	url:baseUrl+'/exportSelectedExcel', //请求的url
	        	data:{'caseIds':caseIds,'templateId':templateId},//要发送的数据
	        	callback:function(){
	        		_dialog.dialog('destroy');
	        	}
	        });
        }
	});
};
//导出所查件
function exportQuery(){
	   var isValid = $('#template_form').form('validate');
	   if(isValid){
		var opts = _datagrid.datagrid("options");
		var queryParams = opts.queryParams;
		queryParams.sort = opts.sortName;
		queryParams.order = opts.sortOrder;
		var templateId = $('#templateId').combobox('getValue');
		queryParams.templateId = templateId;
		app.downLoadFile({
	       	url:baseUrl+'/exportQueryExcel',
	       	data:queryParams,
	       	callback:function(){
	       		_dialog.dialog('destroy');
	       	}
       });	
    }
}



function markCase(){
	app.dataGridSelect(_datagrid, function(rows) {
		var ids = new Array();
        $.each(rows, function (i, row) {
        	ids[i] = row.id;
        });
		var inputUrl = baseUrl + "/mark";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		_color_dialog = app.openFormDialog(function() {

			$.messager.progress({
				title : '提示信息！',
				text : '数据处理中，请稍后....'
			});
			
			$.ajax({
				type : 'post',
				url : baseUrl +"/updateColor",
				data : {
					"ids" : ids,
					"color":$("input[name=color]:checked").val()
				},
				dataType : 'json',
				success : function(data) {
					$.messager.progress('close');
					//渲染结果
					app.renderAjax(data, function(json) {
						_color_dialog.dialog('destroy');//销毁对话框
						app.reload(_datagrid);//重新加载列表数据
					});
				}
			});
		}, '案件标色', 500,160, inputUrl, 'GET', function() {
			
		});
	});
}






function openBatchPage(value, row, index){
	return '<span style="text-decoration: underline;cursor:pointer" onclick=findRowClick("'+row.batchId+'")>'
	+ value
	+ '</span>'
}

function findRowClick(batchId){
	
	var rows=_datagrid.datagrid("getRows")
	for (var i=0,len=rows.length; i<len; i++)
	{
		var row=rows[i];
		if(row.batchId==batchId){
			
			 $.ajax({
			        type: "GET",
			        url: ctx + '/collection/casebatch/'+batchId,
			        data: {},
			        dataType: "json",
			        success: function(data){
						viewBatch(batchId, data.batchCode, data.typeId,data.entrustId,data.beginDate,data.endDate,data.target,data.handle,data.remark)
			        }
			    });
			break;
		}
	}
}



function viewBatch(batchId, batchCode, typeId,entrustId,beginDate,endDate,target,batchHandle,remark) {

	batchUrl= ctx + "/collection/caseImport/show?id="+batchId
	app.openViewDialog('批次详情 ', 600, 400, batchUrl, 'GET', function(){
		$('#entrustId').combobox({
	        url:ctx+'/sys/entrust/entrustlist',
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    width:150,
		    valueField:'value',
	        textField:'text'
		});
		if(typeId){
			$("#typeId").combobox('setValue', typeId).combobox('readonly', true);
		}
		$("#typeId").combobox('readonly', true);
		
		if(entrustId){
			$("#entrustId").combobox('setValue',entrustId).combobox('readonly', true);
		}
		$("#entrustId").combobox('readonly', true);
		
		if(beginDate){
			
			$("#begin").datebox('setValue', $.formatDate(beginDate,'yyyy-MM-dd')).datebox('readonly', true);
		}
		$("#begin").datebox('readonly', true);
		
		if(target){
			$("#target").textbox('setValue', target).textbox('readonly', true);
		}
		$("#target").textbox('readonly', true);
		
		if(endDate){
			$("#end").datebox('setValue', $.formatDate(endDate,'yyyy-MM-dd')).datebox('readonly', true);
		}
		$("#end").datebox('readonly', true);
		
		if(batchCode){
			$("#batchCodeview").textbox('setValue', batchCode).textbox('readonly', true);
		}
		$("#batchCodeview").textbox('readonly', true);
		
		if(batchHandle){
			$("#handle").combobox('setValue', batchHandle).combobox('readonly', true);
		}
		$("#handle").combobox('readonly', true);
		
		if(remark){
			$("#remark").textbox('setValue', remark).textbox('readonly', true);
		}
		$("#remark").textbox('readonly', true);
		
		
   	});
}

function findBacthCode(domId){
	var url = ctx+'/collection/caseImport/Codecombobox';
	$(domId).combobox({
        url:url,
	    multiple:true,//是否可多选
	    editable:true,//是否可编辑
	    width:140,
	    valueField:'value',
        textField:'text'
	});
}

function searchMaterial() {
	
	
	app.dataGridSelect(_datagrid,function(rows){
		var inputUrl = ctx + "/collection/caseapply" + "/input?caseId=${caseId}";
		//弹出对话窗口,保存按钮函数,标题,宽度,高度,请求路径,请求方式,表单加载数据函数（可以为空）
		var caseapply_dialog = app.openFormDialog(function() {
			
			var isValid = $('#_form').form('validate');
			if (!isValid) {
				return false;
			}
			
			$.messager.progress({
				title : '提示信息！',
				text : '数据处理中，请稍后....'
			});
			var caseIds = new Array();
			$.each(rows, function(i, row) {
				caseIds.push(row.id);
			});
			
			var applyType=$("#applyType").combobox('getValue');
			var applyContent=$("#applyContent").textbox('getValue');
			$.ajax({
				type : 'post',
				url : ctx + "/collection/caseapply/batchSave",
				data : {
					"ids" : caseIds,
					"applyContent":applyContent,
					"applyType":applyType
				},
				dataType : 'json',
				success : function(data) {
					$.messager.progress('close');
					//渲染结果
					app.renderAjax(data, function(json) {
						caseapply_dialog.dialog('destroy');//销毁对话框
					});
				}
			});

			
			
			
		}, '查资申请', 700, 300, inputUrl, 'GET', function() {
			
		});
	});
	
	
	
	
	
	
}
	
	
</script>
