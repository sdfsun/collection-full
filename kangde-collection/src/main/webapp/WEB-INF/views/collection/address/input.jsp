<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="../region/region.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<style>
.layout_table{
	margin-top:20px;
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

		<input name="caseId" type='hidden' value="${caseId }" /> <input
			name="id" type='hidden' value="${id }" />

		<table class='layout_table'>
			<tr>
				<td><label>姓名</label></td>
				<td><input id='inputname' name="name" type="text"
					class="easyui-validatebox easyui-textbox" maxLength="50"
					style="width: 130px;"
					data-options="required:true,missingMessage:'请输入姓名.',validType:['minLength[1]','legalInput']"></td>
				
				<td><label>关系</label></td>
				<td><k:dictionary constName="RELATION" id='relation'
						name="relation" width="130" required="true" selectType="select" /></td>
						
				
				<td><label>类别</label></td>
				<td><k:dictionary
						constName="ADDRESS_TYPE" id='adrCat' name="adrCat" width="130"
						required="true" selectType="select" /></td>
			</tr>
		


			<tr>
				<td><label>地址</label></td>
				<td>
				<input id="areaId1" name="areaId1" type="text"
					class="easyui-validatebox textbox" style="width: 130px;"
					placeholder='省' /> 
				</td>
				<td>
			
				</td>
				<td>
				<input id="areaId2" name="areaId2" type="text"
					style="width: 130px;" placeholder='市' /> 
					
				
				</td>
				<td>
			
				</td>
				<td>
				<input id="areaId3"
					name="areaId3" type="text" style="width: 130px;" placeholder='县' />
				</td>
				
			</tr>
			<tr>
				<td></td>
				<td colspan="5"><input id='address' name="address" type="text"
					class="easyui-validatebox easyui-textbox" style="width: 515px;"
					data-options="required:true,missingMessage:'请输入地址.',validType:['minLength[1]']">
				</td>

			</tr>

			<tr>
				<td><label>备注</label></td>
				<td colspan="5"><input id='remark' name="remark" type="text"
					class="easyui-textbox" style="width: 515px; height: 80px"
					multiline="true"></td>
			</tr>




		</table>
	</form>
</div>
