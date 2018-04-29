<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input name='caseId' value="${caseId }" type='hidden'/>
		<div>
			<label style="margin-left: 12px;">类别</label> 
			<select id='applyType' name="applyType"  class="easyui-combobox" data-options="editable:false"  style="width: 200px;">
				<option value="4">HJ</option>
				<option value="6">SHB</option>
				<option value="12">YYS</option>
				<option value="15">KD</option>
			</select>
		
		</div>
		<div>
			<label style="vertical-align: top; margin-left: 12px;">内容</label>
			<input id='applyContent' name="applyContent" maxLength="100" type="text" class="easyui-textbox"  multiline="true" style="width: 400px; height: 120px"  data-options="required:true,missingMessage:'请输入申请内容'">
		</div>
	</form>
</div>