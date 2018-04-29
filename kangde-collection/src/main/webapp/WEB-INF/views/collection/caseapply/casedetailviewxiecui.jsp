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
				<option value="11" <c:if test="${caseApply.applyType=='11'}">selected="true"</c:if>>客服咨询</option>
				<option value="7" <c:if test="${caseApply.applyType=='7'}">selected="true"</c:if>>公安协助</option>
				<option value="5" <c:if test="${caseApply.applyType=='5'}">selected="true"</c:if>>法院协助</option>
				<option value="10" <c:if test="${caseApply.applyType=='10'}">selected="true"</c:if>>主管协催</option>
				
			</select>
			
		</div>
		<div>
			<label style="vertical-align: top; margin-left: 12px;">内容</label>
			<input name="applyContent" value="${caseApply.applyContent }" readonly="readonly" maxLength="100" type="text" class="easyui-textbox"  multiline="true" style="width: 400px; height: 120px"  data-options="required:true,missingMessage:'请输入申请内容'">
		</div>
	</form>
</div>