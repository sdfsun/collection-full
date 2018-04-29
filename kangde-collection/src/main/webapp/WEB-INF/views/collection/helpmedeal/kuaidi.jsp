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
					<td><div align='right'>历史用户名</div></td>
					<td><input name="hisUserName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>手机</div></td>
					<td><input name="mobile" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>家庭电话</div></td>
					<td><input name="familyPhone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
				<tr>
				<td><div align='right'>家庭地址</div></td>
					<td><input name="familyAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>邮箱</div></td>
					<td><input name="email" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>qq</div></td>
					<td><input name="qq" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
					<tr>
					<td><div align='right'>qq群</div></td>
					<td><input name="qqGroup" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>微信</div></td>
					<td><input name="wechat" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>微博</div></td>
					<td><input name="blog" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
					<tr>
						<td><div align='right'>收货人姓名</div></td>
					<td><input name="consigneeName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>收货人电话</div></td>
					<td><input name="consigneePhone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>收货人地址</div></td>
					<td><input name="consigneeAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
				
				<tr>
					<td><div align='right'>单位名称</div></td>
					<td><input name="unitName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>单位地址</div></td>
					<td><input name="unitAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>单位电话</div></td>
					<td><input name="unitPhone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				
				</tr>
				<tr>
					<td><div align='right'>重要联系人名称</div></td>
					<td><input name="importantLinkmanName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>重要联系人电话</div></td>
					<td><input name="importantLinkmanPhone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>重要联系人地址</div></td>
					<td><input name="importantLinkmanAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					
				</tr>
				<tr>
					<td><div align='right'>重要联系人身份证号</div></td>
					<td><input name="importantLinkmanCardNum" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>联系人名称</div></td>
					<td><input name="linkmanName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
						<td><div align='right'>联系人电话</div></td>
					<td><input name="linkmanPhone" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				
				</tr>
				<tr>
					<td><div align='right'>联系人身份证</div></td>
					<td><input name="linkmanCardNum" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>	
					<td><div align='right'>联系人地址</div></td>
					<td><input name="linkmanAddress" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
					<td><div align='right'>联系人工作单位</div></td>
					<td><input name="linkmanUnitName" type="text"
						class="easyui-validatebox textbox" maxLength="50"
						style="width: 150px; height: 22px"></td>
				</tr>
				<tr>
					<td><div align='right'>获取时间</div></td>
							<td><input type="text" name="obtainTime" style="width: 150px; height: 22px"maxLength="255" class="easyui-datebox"/>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注</td>
				<td><div style="text-align: center;">
						<input id="remark" name="remark" class="easyui-textbox" data-options="multiline:true"
							style="width: 690px; height: 100px" />
					</div></td>
			</tr>
		</table>
	</form>
</div>