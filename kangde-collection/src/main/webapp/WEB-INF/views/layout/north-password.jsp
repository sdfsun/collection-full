<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="login_password_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" id="login_password_form_id" name="id" value="<shiro:principal property="id"/>" />
		<div>
			<label style="text-align: right;">原始密码</label> <input type="password" id="password"
				name="password" class="easyui-validatebox textbox" required="true"
				validType="minLength[3]"/>
		</div>
		<div>
			<label style="text-align: right;">新密码</label> <input type="password" id="newPassword"
				name="newPassword" class="easyui-textbox" />
		</div>
		<div>
			<label style="text-align: right;">确认新密码</label> <input type="password" name="newPassword2"
				id="newPassword2" class="easyui-textbox"
				validType="equalTo['#newPassword']"
				invalidMessage="两次输入密码 不一致." />
		</div>
		<!-- <div>
			<label style="text-align: right;">坐席密码</label> <input type="password" id="password"
				name="ccPwd" class="easyui-textbox" />
		</div>
		<div style="color:red;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：修改坐席密码时需与天润登录密码保持一致！
		</div> -->
	</form>
</div>