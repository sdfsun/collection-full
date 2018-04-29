<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<style>
table tr td {
	height: 100%;
	padding: 5px;
	text-align: center;
}
</style>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="caseApplyId" value="${caseApplyId}" />
		<table>
			
				<tr>
					<td><div align='right'>案件序列号</div></td>
					<td><input name="caseCode" type="text"
						class="easyui-validatebox textbox" value="${caseCode}" readonly="readonly"></td>
					<td><div align='right'>案件人姓名</div></td>
					<td><input name="caseName" type="text"
						class="easyui-validatebox textbox" value="${caseName}" readonly="readonly"></td>
					<td><div align='right'>证件号</div></td>
					<td><input name="caseNum" type="text"
						class="easyui-validatebox textbox" value="${caseNum}" readonly="readonly"></td>
				</tr>
				<tr>
					<td><div align='right'>单位名称</div></div></td>
					<td><input name="unitName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"
						data-options="required:true,missingMessage:'请输入单位名称',validType:['minLength[1]','legalInput']"></td>
					<td><div align='right'>单位地址</div></td>
					<td><input name="unitAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
				<tr>
					<td><div align='right'>单位电话1</div></td>
					<td><input name="unitPhone1" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>单位电话2</div></td>
					<td><input name="unitPhone2" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
				<tr>
					<td><div align='right'>联系人</div></td>
					<td><input name="linkman" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>电话</div></td>
					<td><input name="phone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
			</table>
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注</td>
				<td><div style="text-align: center;">
						<input id="remark" name="remark" class="easyui-textbox" data-options="multiline:true"
							style="width: 600px; height: 100px" value="${approveOpinion }" />
					</div></td>
			</tr>
		</table>
	</form>
</div>