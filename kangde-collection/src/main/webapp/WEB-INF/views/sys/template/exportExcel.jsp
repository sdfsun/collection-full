<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$(function() {
		loadUserRole();
	});
	// 加載模板信息
	function loadUserRole() {
		$('#templateId').combobox({
			multiple : false,
			required : true,
			width : 350,
			editable : false,
			url : '${ctx}/sys/template/combobox?gropNo=${gropNo}'
		});
	}
</script>
<div>
	<form id="template_form" class="dialog-form" method="post" novalidate>
	<br/>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${name }&nbsp;&nbsp;&nbsp;<input id="templateId" name="templateId" />
		</div>
	</form>
</div>