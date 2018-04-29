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
		<table align=center valign=middle>
		<tr>
					<td><div align='right'>案件序列号</div></td>
					<td><input name="caseCode" type="text"
						class="easyui-validatebox textbox" value="${caseCode}" readonly="readonly" style="width: 150px; height: 22px"></td>
					<td><div align='right'>案件人姓名</div></td>
					<td><input name="caseName" type="text"
						class="easyui-validatebox textbox" value="${caseName}" readonly="readonly" style="width: 150px; height: 22px"></td>
					<td><div align='right'>证件号</div></td>
					<td><input name="caseNum" type="text"
						class="easyui-validatebox textbox" value="${caseNum}" readonly="readonly" style="width: 150px; height: 22px"></td>
					</tr>
			<tr>
				<td><div align='right'>手机号</div></td>
				<td><input name="mobile" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					data-options="required:true,missingMessage:'请输入手机号',validType:['minLength[1]','legalInput']">
				</td>
				<td><div align='right'>运营商</div></td>
				<td><input name="operator" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>状态</div></td>
				<td><k:dictionary constName="YUNXINGSHANG" name="mobileStatus"
						 selectType="1" width="150" />
				</td>
			</tr>
			<tr>
				<td><div align='right'>宽带号</div></td>
				<td><input name="wideBand" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>网络提供商</div></td>
				<td><input name="networkProvide" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>状态</div></td>
				<td><k:dictionary constName="WANGLUO" name="wideStatus"
						 selectType="1" width="150" />
				</td>
			</tr>
			<tr>
				<td><div align='right'>户籍地址</div></td>
				<td><input name="crAddress" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>住址</div></td>
				<td><input name="address" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
			</tr>
			<tr>
				<td><div align='right'>联系人1</div></td>
				<td><input name="linkman1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>关系</div></td>
				<td><input name="relation1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>证件号</div></td>
				<td><input name="caseNum1" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
			</tr>
			<tr>
				<td><div align='right'>联系人2</div></td>
				<td><input name="linkman2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>关系</div></td>
				<td><input name="relation2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
				<td><div align='right'>证件号</div></td>
				<td><input name="caseNum2" type="text"
					class="easyui-validatebox textbox" maxLength="50"
					style="width: 150px; height: 22px"
					>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注</td>
				<td><div style="text-align: center;">
						<input id="remark" name="remark" class="easyui-textbox" data-options="multiline:true"
							style="width: 610px; height: 100px" value="${approveOpinion }" />
					</div></td>
			</tr>
		</table>
	</form>
</div>