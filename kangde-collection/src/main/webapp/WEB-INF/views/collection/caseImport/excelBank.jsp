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

	function clickMobile() {
		var checklist = document.getElementById("mobile2");
		if (document.getElementById("mobile1").checked) {
			checklist.checked = 1;
		} else {
			checklist.checked = 0;
		}
	}

	function clickMobile2() {
		var checklist = document.getElementById("mobile1");
		if (document.getElementById("mobile2").checked) {
			checklist.checked = 1;
		} else {
			checklist.checked = 0;
		}
	}
</script>
<div>
	<form id="excel_form" class="dialog-form" method="post" novalidate>
		<table align="center">
			<tr>
				<td><input onclick="selectAll()" type="checkbox"
					name="controlAll" id="controlAll" />全选</td>
				<td></td>
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
				<td><input name="field" id="caseName" type="checkbox"
					value="caseName" />姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="principal" type="checkbox"
					value="principal" />本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="debitAccount" type="checkbox"
					value="debitAccount" />扣款账号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanStartdate" type="checkbox"
					value="loanStartdate" />贷款日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanEnddate" type="checkbox"
					value="loanEnddate" />贷款截止日&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="bill" type="checkbox" value="bill" />账单日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="startCardDate" type="checkbox"
					value="startCardDate" />开卡日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="stopCardDate" type="checkbox"
					value="stopCardDate" />停卡日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="creditLimit" type="checkbox"
					value="creditLimit" />信用额度&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="surplusPrincipal" type="checkbox"
					value="surplusPrincipal" />剩余本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="entrustRate" type="checkbox"
					value="entrustRate" />委托费率%&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="achieve" type="checkbox"
					value="agentRate" />业绩率%&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanMoney" type="checkbox"
					value="loanMoney" />贷款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanRate" type="checkbox"
					value="loanRate" />贷款利率&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueDate" type="checkbox"
					value="overdueDate" />逾期开始日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="repaymentPeriods" type="checkbox"
					value="repaymentPeriods" />已还期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overduePeriods" type="checkbox"
					value="overduePeriods" />逾期期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseMoney" type="checkbox"
					value="caseMoney" />委案金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueInterest" type="checkbox"
					value="overdueInterest" />逾期利息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseNum" type="checkbox"
					value="caseNum" />证件号&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseNumId" type="checkbox"
					value="caseNumId" />证件类型&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseSex" type="checkbox"
					value="caseSex" />性别&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="debitBankCode" type="checkbox"
					value="debitBankCode" />扣款账号银行代码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="debitBankName" type="checkbox"
					value="debitBankName" />扣款账号银行名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="reliefPolicy" type="checkbox"
					value="reliefPolicy" />减免政策&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseAge" type="checkbox"
					value="caseAge" />年龄&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="domicile" type="checkbox"
					value="domicile" />户籍地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyAddress" type="checkbox"
					value="familyAddress" />家庭地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="creditId" type="checkbox"
					value="creditId" />信贷分类&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueMoney" type="checkbox"
					value="overdueMoney" />逾期金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="overdueDays" type="checkbox"
					value="overdueDays" />逾期天数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="onceOverduePeriods" type="checkbox"
					value="onceOverduePeriods" />曾逾期次数&nbsp;&nbsp;&nbsp;&nbsp;</td>

				<td><input name="field" id="mobile1" type="checkbox"
					value="mobile1" onclick="clickMobile()" />本人手机1&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="mobile2" type="checkbox"
					value="mobile2" onclick="clickMobile2()" />本人手机2&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="actualLoanMoney" type="checkbox"
					value="actualLoanMoney" />放款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="familyPhone" type="checkbox"
					value="familyPhone" />家庭号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyName" type="checkbox"
					value="companyName" />单位名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyAddress" type="checkbox"
					value="companyAddress" />单位地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="casePositionLevel" type="checkbox"
					value="casePositionLevel" />职位级别&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="casePosition" type="checkbox"
					value="casePosition" />案人职位&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="companyPhone" type="checkbox"
					value="companyPhone" />单位号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="bankAccount" type="checkbox"
					value="bankAccount" />开户行&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseHouseId" type="checkbox"
					value="caseHouseId" />案人房产类型&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseCard" type="checkbox"
					value="caseCard" />卡号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueAge" type="checkbox"
					value="overdueAge" />逾期账龄&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="areaName" type="checkbox"
					value="areaName" />风控区域&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="markId" type="checkbox"
					value="markId" />标ID&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="userId" type="checkbox"
					value="userId" />用户ID&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="userName" type="checkbox"
					value="userName" />用户名&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanArea" type="checkbox"
					value="loanArea" />贷款所属地&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="overduePrincipal" type="checkbox"
					value="overduePrincipal" />逾期本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueLoan" type="checkbox"
					value="overdueLoan" />逾期借款&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="penaltyReference" type="checkbox"
					value="penaltyReference" />罚息金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueExpense" type="checkbox"
					value="overdueExpense" />逾期管理费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="stayPeriods" type="checkbox"
					value="stayPeriods" />待还期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="stayPrincipal" type="checkbox"
					value="stayPrincipal" />待还本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="stayInterest" type="checkbox"
					value="stayInterest" />待还利息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="stayExpense" type="checkbox"
					value="stayExpense" />待还管理费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="accountMoney" type="checkbox"
					value="accountMoney" />账户余额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="repayDate" type="checkbox"
					value="repayDate" />还款日&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="initialRepay" type="checkbox"
					value="initialRepay" />最后还款日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="monthRepayment" type="checkbox"
					value="monthRepayment" />每月还款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="lastRepayment" type="checkbox"
					value="lastRepayment" />最后还款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="oldCollecRecord" type="checkbox"
					value="oldCollecRecord" />原风控记录&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="regione" type="checkbox"
					value="regione" />大区名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="subCenter" type="checkbox"
					value="subCenter" />分中心名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="salesDep" type="checkbox"
					value="salesDep" />营业部名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="quotaManager1" type="checkbox"
					value="quotaManager1" />客户经理&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="managerPhone" type="checkbox"
					value="managerPhone" />客户经理电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="contractMoney" type="checkbox"
					value="contractMoney" />签约金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="consultFee" type="checkbox"
					value="consultFee" />咨询费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="auditFee" type="checkbox"
					value="auditFee" />审核费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="serviceFee" type="checkbox"
					value="serviceFee" />服务费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="adviserFee" type="checkbox"
					value="adviserFee" />顾问费&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="feeTotal" type="checkbox"
					value="feeTotal" />费用总和&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>

				<td><input name="field" id="duePeriods" type="checkbox"
					value="duePeriods" />应还期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="breach" type="checkbox"
					value="breach" />违约金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="penaltyDays" type="checkbox"
					value="penaltyDays" />日罚息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="remainHistory" type="checkbox"
					value="remainHistory" />历史余留&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="surplusPrincipal" type="checkbox"
					value="surplusPrincipal" />剩余本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
			<td><input name="field" id="caseLastMoney" type="checkbox"
					value="caseLastMoney" />最新欠款&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input name="field" id="caseLastDate" type="checkbox"
					value="caseLastDate" />欠款更新日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseCode" type="checkbox"
					disabled="disabled" readonly="readonly" checked="checked"
					value="caseCode" />案件序列号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="id" type="checkbox" value="id"
					readonly="readonly" checked="checked" />id&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>

	</form>
</div>