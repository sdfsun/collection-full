<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="_form" class="dialog-form" method="post">
		<input name='id' value="${id }" type='hidden'/>
		<br/><br/>
		<div>
			<input name="hurryContent" maxLength="100" type="text" class="easyui-textbox"  multiline="true" style="width: 400px; height: 120px"  data-options="required:true,missingMessage:'请输入回复内容'">
		</div>
	</form>
</div>