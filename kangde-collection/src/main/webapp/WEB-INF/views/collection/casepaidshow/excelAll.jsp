<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function selectAll() {
		var checklist = document.getElementsByName("field");
		if (document.getElementById("controlAll").checked) {
			for (var i = 0; i < checklist.length; i++) {
				checklist[i].checked = 1;
			}
		} else {
			for (var j = 0; j < checklist.length; j++) {
				checklist[j].checked = 0;
			}
		}
	}
</script>
<div>
	<form id="excel_form" class="dialog-form" method="post" novalidate>
		<table align="center">
			<tr>
				<td><input onclick="selectAll()" type="checkbox"
					name="controlAll" style="" id="controlAll" />全选</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td><input name="field" id="state" type="checkbox"
					value="state" />还款状态&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseModel.caseCode" type="checkbox"
					value="caseModel.caseCode" />案件序列号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="batchModel.batchCode"
					type="checkbox" value="batchModel.batchCode" />批次号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="batchModel.entrustName"
					type="checkbox" value="batchModel.entrustName" />委托方&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseModel.caseName" type="checkbox"
					value="caseModel.caseName" />姓名</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseModel.caseNum" type="checkbox"
					value="caseModel.caseNum" />证件号</td>
				<td><input name="field" id="caseModel.caseCard" type="checkbox"
					value="caseModel.caseCard" />卡号</td>
				<td><input name="field" id="caseModel.mobile1" type="checkbox"
					value="caseModel.mobile1" />手机号</td>
				<td><input name="field" id="backPaidRate" type="checkbox"
					value="backPaidRate" />业绩率</td>
				<td><input name="field" id="caseModel.caseMoney"
					type="checkbox" value="caseModel.caseMoney" />委案金额</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="paidNum" type="checkbox"
					value="paidNum" />已还款</td>
				<td><input name="field" id="caseModel.caseFileNo"
					type="checkbox" value="caseModel.caseFileNo" />档案号</td>
				<td><input name="field" id="cpMoney" type="checkbox"
					value="cpMoney" />cp金额</td>
				<td><input name="field" id="cpTime" type="checkbox"
					value="cpTime" />cp日期</td>
				<td><input name="field" id="paidTime" type="checkbox"
					value="paidTime" />还款日期</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="organization.soName"
					type="checkbox" value="organization.soName" />回款风控方</td>
				<td><input name="field" id="cpName" type="checkbox"
					value="cpName" />回款风控员</td>
				<td><input name="field" id="entrustPaidRate"
					type="checkbox" value="entrustPaidRate" />委托费率</td>
				<td><input name="field" id="compBorker" type="checkbox"
					value="compBorker" />佣金</td>
				<td><input name="field" id="commission" type="checkbox"
					value="commission" />业绩</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td><input name="field" id="caseModel.accountNo"
					type="checkbox" value="caseModel.accountNo" />账号</td>
				<td><input name="field" id="caseModel.loanContract"
					type="checkbox" value="caseModel.loanContract" />贷款合同号</td>
				<td><input name="field" id="caseModel.cusNo" type="checkbox"
					value="caseModel.cusNo" />客户号</td>
				<td><input name="field" id="caseModel.currency" type="checkbox"
					value="caseModel.currency" />币种</td>
				<td><input name="field" id="caseModel.overdueDays"
					type="checkbox" value="caseModel.overdueDays" />逾期天数</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
				<tr>
				<td><input name="field" id="caseModel.overdueAge"
					type="checkbox" value="caseModel.overdueAge" />账龄</td>
					<td><input name="field" id="caseModel.endDate"
					type="checkbox" value="caseModel.caseBackdate" />退案日期</td>
					<td><input name="field" id="caseModel.remark1"
					type="checkbox" value="surRemark" />备注</td>
					<td><input name="field" id="caseModel.salesDep"
					type="checkbox" value="caseModel.salesDep" />营业部名称</td>
					<td><input name="field" id="caseModel.caseDate"
					type="checkbox" value="caseModel.caseDate" />委案日期</td>
				
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseModel.caseAppNo"
					type="checkbox" value="caseModel.caseAppNo" />申请单号</td>
					<td><input name="field" id="actual_visit_time"
					type="checkbox" value="actual_visit_time" />最后外访日期</td>
					
					<td><input name="field" id="visit_user"
					type="checkbox" value="visit_user" />最后外访员</td>
					<td></td>
					<td></td>
				
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>

	</form>
</div>