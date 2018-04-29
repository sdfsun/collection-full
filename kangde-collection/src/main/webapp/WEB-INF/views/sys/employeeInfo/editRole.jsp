<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="user_role_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="userId" value="${userId}"/><br>
		<div >
			<label >关联角色:</label>
			<input id="user_role_form-roleIds" name="roleIds" />
		</div>
	</form>
</div>