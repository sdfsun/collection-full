var login_about_dialog;
var login_password_dialog;
var login_password_form;
function showAbout(){
    //弹出对话窗口
    login_about_dialog = $('<div/>').dialog({
        title:'关于我们',
        width : 420,
        height : 240,
        modal : true,
        iconCls:'eu-icon-essh',
        href : ctx+'/common/layout/about',
        buttons : [{
            text : '关闭',
            iconCls : 'easyui-icon-cancel',
            handler : function() {
                login_about_dialog.dialog('destroy');
            }
        }],
        onClose : function() {
            login_about_dialog.dialog('destroy');
        }
    });
//  	$(".panel-title").css('text-align', 'center');
}

function initLoginPasswordForm(){
    login_password_form = $('#login_password_form').form({
        url: ctx+'/sys/employeeInfo/updateUserPassword',
        onSubmit: function(param){
            return $(this).form('validate');
        },
        success: function(data){
        	app.renderAjax(data,function(json){
           	  login_about_dialog.dialog('destroy');//销毁对话框
           });
        }
    });
}

function editLoginUserPassword(){
    //弹出对话窗口
    login_about_dialog = $('<div/>').dialog({
        title:'修改密码',
        width : 460,
        height : 240,
        modal : true,
        href : ctx+'/common/layout/north-password',
        buttons : [{
            text : '保存',
            handler : function() {
                login_password_form.submit();;
            }
        },{
            text : '关闭',
            handler : function() {
                login_about_dialog.dialog('destroy');
            }
        }],
        onClose : function() {
            login_about_dialog.dialog('destroy');
        },
        onLoad:function(){
            initLoginPasswordForm();
        }
    });
}
//注销
function logout(clearCookie) {
    $.messager.confirm('确认提示！', '您确定要退出系统吗？', function(r) {
        if (r) {
            if(clearCookie){
                $.cookie('autoLogin', "", {
                    expires : 7
                });
            }
            window.location.href = ctx+"/logout";
        }
    });
}
