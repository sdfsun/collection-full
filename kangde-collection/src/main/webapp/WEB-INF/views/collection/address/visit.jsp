<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.div-a {
	float: left;
	width: 49%;
	margin-top: 10px;
}

.div-b {
	float: left;
	width: 49%;
	margin-top: 10px;
}

.div-c {
	float: left;
}
</style>
<div>

	<form id="visit_form" class="dialog-form" method="post" novalidate>

		<input name='addressId' value="${addressInfo.id }" type='hidden' />
		<input name='caseId' value="${addressInfo.caseId }" type='hidden' />

		<div>
			<label style="text-align: right;margin:3px;">地址</label> 
			${addressDetail }

		</div>

		<div>
			<label style="text-align: right;margin:3px;">原因</label> 
			<k:dictionary constName="VISIT_REASON" name="applyReason"
					selectType="select" />

		</div>
		
		<div>
			<label style="text-align: right;vertical-align:top;margin:3px;">目标</label> 
			<input name="require"
				type="text" class="easyui-textbox"
				multiline="true" style="width: 400px; height: 100px"  data-options="required:true,missingMessage:'请输入目标'">
		</div>
	</form>
</div>

<jsp:include page="../region/region.jsp"></jsp:include>
