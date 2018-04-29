<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="cancel_form" class="dialog-form" method="post" novalidate>
		<input name='id' value="${id }" type='hidden' />
		<br/><br/>
				<div>
			<input id="cancelReason" name="cancelReason" class="easyui-textbox"
				data-options="required:true,missingMessage:'请输入作废原因.',validType:'maxLength[500]',multiline:true"
				style="width: 400px; height: 120px" value="${cancelReason }" />
		</div>
	</form>
</div>