<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
 		<input name='caseId' value="${caseId }" type='hidden'/>
	
	<div><label style="text-align: left;">申请原因:</label></div>
	<div>
			<input name="reason" maxLength="100" type="text" class="easyui-textbox"  multiline="true" style="width: 400px; height: 120px"  data-options="required:true,missingMessage:'请输入留案申请原因'">
	</div>
	
	
	</form>
</div>