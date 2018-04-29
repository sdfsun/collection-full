<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input name='id' value="${caseApply.id }" type='hidden'/>
		<div>
			<label style="margin-left: 12px;">类别</label> 
			<select name="applyType" disabled="disabled"
				 data-options="editable:false;" style="width: 200px;">
				<option value="4" <c:if test="${caseApply.applyType=='4'}">selected="true"</c:if>>HJ</option>
				<option value="6" <c:if test="${caseApply.applyType=='6'}">selected="true"</c:if>>SHB</option>
				<option value="12" <c:if test="${caseApply.applyType=='12'}">selected="true"</c:if>>YYS</option>
				<option value="15" <c:if test="${caseApply.applyType=='15'}">selected="true"</c:if>>KD</option>
			</select>
			
		</div>
		<div>
			<label style="vertical-align: top; margin-left: 12px;">内容</label>
			<input name="applyContent" value="${caseApply.applyContent }" readonly="readonly" maxLength="100" type="text" class="easyui-textbox"  multiline="true" style="width: 400px; height: 120px"  data-options="required:true,missingMessage:'请输入申请内容'">
		</div>
	</form>
</div>