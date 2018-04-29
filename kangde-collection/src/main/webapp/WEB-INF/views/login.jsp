<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.kangde.commons.CoreConst" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${ctx}/static/img/loginPicture/reset.css">
<link rel="stylesheet" href="${ctx}/static/img/loginPicture/kangde.css">
    <title>用户登录</title>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript">
 		// 判断时候在Iframe框架内,在则刷新父页面
	    if (self != top) {
	        parent.location.reload(true);
	        if (!!(window.attachEvent && !window.opera)) {
	            document.execCommand("stop");
	        } else {
	            window.stop();
	        }
	    }
        var loginForm;
        $(function () {
            loginForm = $('#loginForm').form();
        });
        // 登录
        function login() {
            if (loginForm.form('validate')) {
                $.post('${ctx}/login',$.serializeObject(loginForm), function (data) {
                    if (data.code == 1) {
                        window.location = data.obj;//操作结果提示
                    } else {
                        eu.showAlertMsg(data.msg,'error');
                    }
                }, 'json');
            }
        }
    </script>
</head>
<body>
	<!-- <div class="box"> -->
	<div class="box_1">
    		<img src="${ctx}/static/img/loginPicture/big_beijing_02.png"/>
    	</div>
   		<div class="wrap"> 
   			<p class="logo"><img src="${ctx}/static/img/loginPicture/logo_guigu.png"></p> 
   			<div class="form ">
   			
   			    <script type="text/javascript">
		   			 if (window != top){
		   				top.location.href = location.href; 
		   			 }
   			    </script>
   				 <form id="loginForm" method="post" novalidate>
   					<h2>风控管理系统</h2>
   					<span>Debt Collection & Management System</span>
                       <ul class="shuru clearfix">
                       
                       	<li class="one"><img src="${ctx}/static/img/loginPicture/zhanghao.png">
                       	<input id="loginName" name="username"/></li>
                           <li class="one"><img src="${ctx}/static/img/loginPicture/mima.png">
                           <input id="password" name="password" type="password"
	                           onkeydown="if(event.keyCode==13)login();"/></li>
                          <p style="color:#fff;">本系统仅支持<a href="http://<%=CoreConst.getString("docIp")%>/firefox.exe" target="blank"><font color="red" style="text-decoration:underline;">火狐</font></a>和<a href="http://<%=CoreConst.getString("docIp")%>/chrome.exe" target="blank"><font color="red" style="text-decoration:underline;">谷歌</font></a>浏览器，没有安装请点击链接下载！</p>
                           <li class="two">
                           <a id="login_linkbutton" href="#" class="button" onclick="login()">登 录</a>
                       </ul>
   				</form>
   			</div>
   		</div>
   	</div>
</body>
</html>
