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
						class="easyui-validatebox textbox" value="${caseCode}"
						readonly="readonly" style="width: 155px; height: 22px"></td>
					<td><div align='right'>案件人姓名</div></td>
					<td><input name="caseName" type="text"
						class="easyui-validatebox textbox" value="${caseName}"
						readonly="readonly" style="width: 155px; height: 22px"></td>
					<td><div align='right'>证件号</div></td>
						<td><input name="caseNum" type="text"
						class="easyui-validatebox textbox" value="${caseNum}"
						readonly="readonly" style="width: 155px; height: 22px"></td>
				</tr>
			<tr>
				<td><div align='right'>户籍地址</div></td>
				<td><input name="crAddress" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"
					data-options="required:true,missingMessage:'请输入户籍地址',validType:['minLength[1]','legalInput']">
				</td>
				<td><div align='right'>住址</div></td>
				<td><input name="address" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
					<td><div align='right'>婚姻状态</div></td>
				<td><k:dictionary constName="GAM" name="married" selectType="1"
						width="150" /></td>
			</tr>
			<tr>
				<td><div align='right'>配偶姓名</div></td>
				<td><input name="mate" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
						<td><div align='right'>文化程度</div></td>
				<td><k:dictionary constName="WENHUA" name="culture"
						selectType="大学" width="150" /></td>
				<td><div align='right'>职业</div></td>
				<td><input name="career" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
			</tr>
			<tr>
				<td><div align='right'>家庭电话</div></td>
				<td><input name="familyPhone" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
				<td><div align='right'>手机号</div></td>
				<td><input name="mobile" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
			</tr>
			<tr>
				<td><div align='right'>同户人员1</div></td>
				<td><input name="personnel1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
				<td><div align='right'>关系</div></td>
				<td><input name="relation1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
				<td><div align='right'>证件号</div></td>
				<td><input name="caseNum1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
			</tr>
			<tr>
				<td><div align='right'>同户人员2</div></td>
				<td><input name="personnel2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
				<td><div align='right'>关系</div></td>
				<td><input name="relation2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
				<td><div align='right'>证件号</div></td>
				<td><input name="caseNum2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 155px; height: 22px"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注</td>
				<td><div style="text-align: center;">
						<input id="remark" name="remark" class="easyui-textbox" data-options="multiline:true"
							style="width: 630px; height: 100px" value="${approveOpinion }" />
					</div></td>
			</tr>
		</table>
	</form>
</div>