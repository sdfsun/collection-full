<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.layout_table{
	margin-top:20px;
	margin-left: 35px;
}

.layout_table label {
	display: inherit;
	height: 100%;
	padding: 0px;
	margin-left: 20px;
	width:50px;
	text-align: right;
}
</style>
<div>

	<form id="_form" class="dialog-form" method="post">
		<input name="id"  value="${id }" type ='hidden' />
		<input name="caseId"  value="${caseId }" type ='hidden' />
		
		<table class='layout_table'>
			<tr>
				<td><label>姓名</label></td>
				<td><input name="name"
					type="text" class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px;height:23px;"
					data-options="required:true,missingMessage:'请输入姓名',validType:['minLength[1]','legalInput']"></td>
				<td><label>关系</label>
				</td>
				<td><k:dictionary constName="RELATION" name="relation" required="true" width='150'
					selectType="select" /></td>
			</tr>
			<tr>
				<td><label>类别</label></td>
				<td><k:dictionary constName="PHONE_TYPE" name="phoneType" width="150"
					required="true" selectType="select" /></td>
				<td><label style="text-align: right;">电话</label></td>
				<td><input name="phone"
					id='phone' style="width: 150px;height:23px;" type="text"
					class="easyui-validatebox textbox" maxLength="100"
					data-options="required:true,missingMessage:'请输入电话',validType:['mobileOrPhone']"></td>
			</tr>
			<tr>
				<td><label>备注 </label></td>
				<td colspan="3">
						 <input id="description"
				name="remark" type="text" class="easyui-textbox"
				style="width: 365px; height: 80px" 
				data-options="required:false,missingMessage:'请输描述.',multiline:true">
				</td>
			</tr>
		
		</table>
	

	</form>
</div>