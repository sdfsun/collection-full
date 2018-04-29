<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
/* $(function () {
	$('#caseEmployeeId').combobox({
		url : ctx + '/sys/employeeInfo/orgusersByOrg?orgId=${CURRENT_USER.orgId}',
		required:true,
		editable : false,//是否可编辑
		width : 200,
		valueField : 'value',
		textField : 'text',
		editable : true,
		delay : 0,
		separator : ',',
		filter: function(q, row){  
			    var opts = $(this).combobox('options');  
			    return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配  
		}, 
	});
}); */
</script>
<div>
	<form id="division_form" class="dialog-form" method="post" novalidate>
	     <ul name="userId" id="userId"></ul>
	   <!-- <div>
            <label>员工姓名</label>
            <input id="caseEmployeeId" name="caseEmployeeId"/>
        </div> -->
	</form>
</div>