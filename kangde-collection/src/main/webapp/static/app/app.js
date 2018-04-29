/**
 * 定义全局对象，APP（应用）的扩展命名空间.
 */
var app = $.extend({}, app);

//------------------------------eu 扩展 ----------------------//
/**
 * 自适应Jquery#选择器,查看是否遗漏#表达式,如果有则跳过,没有则追加
 * @param str
 */
app.appendIdPrefix=function(str){
	var temp = str.substring(0,1);
	if('#'!=temp){
		str='#'+str;
	}
	return str;
};

/**
 * 打开表单会话框,包含保存和关闭按钮
 * @param saveFun 会话框保存按钮的函数,可以为空,但是保存按钮什么也不会做
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 请求地址
 * @param method 请求地址使用的方法,如果为null,则使用post方式
 * @onLoadFun easyui,dialog onLoad事件函数,可以为空
 * @param saveBtnName 自定义保存按钮名称
 * @returns 会话对象
 */
app.openFormDialog=function(saveFun,title, width, height, url,method,onLoadFun,saveBtnName,closeBtnName){
	var btnName = '保存';
	if(saveBtnName){
		btnName = saveBtnName;
	}
	var btnName2 = '关闭';
	if(closeBtnName){
		btnName2 = closeBtnName;
	}
	
	//弹出对话窗口
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
        border:'thick',
        buttons : [ {
            text : btnName,
            handler : function() {
            	//如果有函数,执行,没有略过
            	if(saveFun){
            		saveFun();
            	}
            }
        },{
            text : btnName2,
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
/**
 * 打开表单会话框,包含关闭按钮
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 请求地址
 * @param method 请求地址使用的方法,如果为null,则使用post方式
 * @onLoadFun easyui,dialog onLoad事件函数,可以为空
 * @returns 会话对象
 */
app.openViewDialog=function(title, width, height, url,method,onLoadFun){
	//弹出对话窗口
    var _dialog = $('<div/>').dialog({
        title: title,
        top:20,
        width : width,
        height:height,
        modal : true,
        maximizable:true,
        resizable:true,
        href : url,
        method:method,
        buttons : [{
            text : '关闭',
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

/**
 * 打开表单会话框,不包含关闭按钮
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 请求地址
 * @param method 请求地址使用的方法,如果为null,则使用post方式
 * @param top 
 * @onLoadFun easyui,dialog onLoad事件函数,可以为空
 * @returns 会话对象
 */
app.openViewDialogNoBtn=function(top,title, width, height, url,method,onLoadFun){
	//弹出对话窗口
    var _dialog = $('<div/>').dialog({
        title: title,
        top:top,
        width : width,
        height:height,
        modal : true,
        resizable:true,
        href : url,
        method:method,
    });
    return _dialog;
};


/**
 * 打开最大化的表单会话框（适应父容器的大小）,包含保存和关闭按钮
 * @param saveFun 会话框保存按钮的函数,可以为空,但是保存按钮什么也不会做
 * @param title 标题
 * @param url 请求地址
 * @param method 请求地址使用的方法,如果为null,则使用post方式
 * @onLoadFun easyui,dialog onLoad事件函数,可以为空
 * @returns 会话对象
 */
app.openMaxFormDialog=function(saveFun,title,url,method,onLoadFun){
	//弹出对话窗口
	var _dialog = $('<div/>').dialog({
		title:title,
		modal : true,
		fit:true,
		href : url,
		method:method,
		buttons : [ {
			text : '保存',
			handler : function() {
				//如果有函数,执行,没有略过
				if(saveFun){
					saveFun();
				}
			}
		},{
			text : '关闭',
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

/**
 * 处理Ajax响应结果
 * @param data
 * @param successFun 执行成功调用的函数,可以为空,会把对于的data（json）参数注入给该方法,
 * @param 是否显示成功的提示信息:默认为true
 */
app.renderAjax=function(data,successFun,showMessage){
	var sms = true;
	if(showMessage != undefined && false==showMessage){
		sms = false;
	}
	// 0:失败,1:成功,2:未登录或session过期,3:无权限 
	if(data){
		if(typeof data == "string"){
			try{
				data = $.parseJSON(data);
			}catch(e){
				eu.showAlertMsg(data,'error');
				return;
			}
		}
		if(data.code == 0){
			eu.showAlertMsg(data.msg,'error');
		}else if (data.code ==1){
			if(successFun){
				successFun(data);
			}
			if(sms){
				eu.showMsg(data.msg);//操作结果提示
			}
        }else if(data.code == 2){
        	 eu.showAlertMsg('登录超时请重新登录','error');
        }else if(data.code == 3){
        	 eu.showAlertMsg('没有对应的权限,如有疑问,请与管理员联系','error');
        }else {
            eu.showAlertMsg(data.msg,'error');
        }
	}
};
/**
 * 发送ajax,带遮罩效果
 * @param options jquery.ajax 参数
 */
app.ajax=function(options){
	if(options){
		options.beforeSend = function(xhr,tt){
			$.messager.progress({
		         title:'提示信息！',
		         text: '数据处理中，请稍后....'
		    });
		}
		options.complete = function(xhr, status){
			$.messager.progress('close');
		}
		$.ajax(options);
	}else{
		alert('操作错误:options没有定义');
	}
}

/**
 * 取消所有选中
 * @param dataGridId
 */
app.unCheckAll=function(datagrid){
	$(datagrid).datagrid("clearSelections");//取消所有的已选择项
	$(datagrid).datagrid("clearChecked");//取消checked状态
	$(datagrid).datagrid("unselectAll");//取消全选按钮为全选状态
};
/**
 * 刷新,保持在当前页
 * @param dataGridId
 */
app.reload=function(datagrid,params){
	$(datagrid).datagrid("reload",params);
	app.unCheckAll(datagrid);//取消所有选中
};
/**
 * 刷新,回到第一页
 * @param dataGridId
 */
app.load=function(datagrid,params){
	$(datagrid).datagrid("load",params);
	app.unCheckAll(datagrid);
};



//--------字典相关-------
//缓存对象,全局对象
var cacheDicts = new Object();
/**
 * @param constName 常量名称
 * @param value	字典值
 * @returns 字典对应值的名称
 */
app.dictName=function(constName,value){
	var text = "";
	var dicts = cacheDicts[constName];
	//从缓存获取
	if(dicts == undefined){
		//远程获取数据
		$.ajax({
			type: 'post',
			url: ctx+"/sys/dictionary/subs?constName="+constName,
			cache: false,
			async: false,
			dataType: 'json',
			success: function(data){
				cacheDicts[constName]=data;//放入缓存
			}
		});
		dicts = cacheDicts[constName];
	}
	if(dicts.length>0){
		for(var i=0;i<dicts.length;i++){
			if(value==dicts[i].value){
				text = dicts[i].name;
				break;
			}
		}
	}
	return text;
};

//--------------业务datagrid方法扩展--------------//
/**
 * 判断datagrid是否选中1条数据,如果选中,执行函数
 * @param datagrid easyuiDatagrid 对象
 * @param fun 如果选中1条数据条件成立,需要执行的函数,会把选中的数据注入给该方法
 */
app.dataGridSelectOne=function(datagrid,fun){
	//选中的所有行
    var rows = datagrid.datagrid('getSelections');
    if(rows.length == 1){
    	if(fun){
        	fun(rows[0]);
        }else{
        	alert('参数错误,没有传入fun(row) 回调函数');
        }
    }else if (rows.length > 1) {
    	//多行校验
        eu.showAlertMsg("只能选中一条数据进行操作！",'warning');
    } else {
    	eu.showAlertMsg("请选择要操作的数据！",'warning');
    }
};
/**
 * 判断datagrid是否选中大于1条数据,如果选中,执行函数
 * @param datagrid easyuiDatagrid 对象
 * @param fun 如果选中多条数据条件成立,需要执行的函数,会把选中的数据注入给该方法
 */
app.dataGridSelect=function(datagrid,fun){
	//选中的所有行
	var rows = datagrid.datagrid('getSelections');
	if (rows.length > 0) {
		if(fun){
        	fun(rows);
        }else{
        	alert('参数错误,没有传入fun(rows) 回调函数');
        }
	} else {
		eu.showAlertMsg("请选择要操作的数据！",'warning');
	}
};
/**
 * 判断datagrid是否包含数据
 * @param datagrid easyuiDatagrid 对象
 * @param fun 如果包含数据条件成立,需要执行的函数
 */
app.datagridHasRow=function(datagrid,fun,showErrMsg){
	var len = datagrid.datagrid('getRows').length;
	if(len>0){
		if(fun){
			fun();
		}else{
			alert('没有传入含有行数需要执行的函数');
		}
	}else{
		if(showErrMsg==undefined || showErrMsg == true){
			eu.showAlertMsg("没有可操作数据！",'warning');
		}
	}
};

/**
 * 下载文件,以POST的方式提交
 * @param options{url,data}
 * 使用方式
 * app.downLoadFile({
 *      url:'xxx.download', //请求的url
 *      data:{name:xxx,age:xxx}//要发送的数据
 *      callback:function(){}
 * });
 * 
 */
app.downLoadFile = function (options) {
    var config = $.extend(true, { method: 'post' }, options);
    var $form = $('<form target="_blank" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $(document.body).append($form);
    if(config.callback != undefined){
    	config.callback();
    }
    $form[0].submit();
}

//缓存对象,全局对象
var cacheRoles = new Object();

/**
 * 检查用户是否是指定的角色
 */
app.hasRole = function (roleCode){
	var rs = cacheRoles[roleCode];
	if(rs == undefined){
		$.ajax({
			type: 'post',
			url: ctx+"/sys/employeeInfo/hasRole",
			data:{'roleCode':roleCode},
			async: false,
			dataType: 'json',
			success: function(data){
				rs = data.obj;
				cacheRoles[roleCode]=rs;
			}
		});
	}
	return rs;
	
}