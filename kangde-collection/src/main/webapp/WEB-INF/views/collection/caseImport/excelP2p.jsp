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
					name="controlAll" id="controlAll" />全选 &nbsp;&nbsp;&nbsp;&nbsp;</td>
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
			<tr>
			<tr>
				<td><input name="field" id="caseAppNo" type="checkbox"
					value="caseAppNo" />申请单号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="socialSecurityNo" type="checkbox"
					value="socialSecurityNo" />社保电脑号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseNumId" type="checkbox"
					value="caseNumId" />证件类型&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseNum" type="checkbox"
					value="caseNum" />证件号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="userId" type="checkbox"
					value="userId" />客户号&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			<tr>
				<td><input name="field" id="cusNo" type="checkbox"
					value="cusNo" />客户编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseFileNo" type="checkbox"
					value="caseFileNo" />档案号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseName" type="checkbox"
					value="caseName" />姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="creditId" type="checkbox"
					value="creditId" />信贷分类&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
				<td><input name="field" id="overdueDays" type="checkbox"
					value="overdueDays" />逾期天数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overduePeriods" type="checkbox"
					value="overduePeriods" />逾期期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueMoney" type="checkbox"
					value="overdueMoney" />逾期金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseMoney" type="checkbox"
					value="caseMoney" />委案金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="principal" type="checkbox"
					value="principal" />本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="surplusPrincipal" type="checkbox"
					value="surplusPrincipal" />剩余本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="entrustRate" type="checkbox"
					value="entrustRate" />委托费率%&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="achieve" type="checkbox"
					value="agentRate" />业绩率%&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanMoney" type="checkbox"
					value="loanMoney" />贷款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanStartdate" type="checkbox"
					value="loanStartdate" />贷款日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="loanEnddate" type="checkbox"
					value="loanEnddate" />贷款截止日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="bill" type="checkbox" value="bill" />账单日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="startCardDate" type="checkbox"
					value="startCardDate" />开卡日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="stopCardDate" type="checkbox"
					value="stopCardDate" />停卡日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="creditLimit" type="checkbox"
					value="creditLimit" />信用额度&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="commodity" type="checkbox"
					value="commodity" />商品&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="bankAccount" type="checkbox"
					value="bankAccount" />开户行&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseCard" type="checkbox"
					value="caseCard" />卡号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseSex" type="checkbox"
					value="caseSex" />性别&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseAge" type="checkbox"
					value="caseAge" />年龄&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseIsMarriage" type="checkbox"
					value="caseIsMarriage" />婚否&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseBirthday" type="checkbox"
					value="caseBirthday" />生日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyName" type="checkbox"
					value="companyName" />单位名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyPhone" type="checkbox"
					value="companyPhone" />单位号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyAddress" type="checkbox"
					value="companyAddress" />单位地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="companyZipcode" type="checkbox"
					value="companyZipcode" />单位邮编&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="mobile1" type="checkbox"
					value="mobile1" onclick="clickMobile()" />本人手机1&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="mobile2" type="checkbox"
					value="mobile2" onclick="clickMobile2()" />本人手机2&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyAddress" type="checkbox"
					value="familyAddress" />家庭地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyZipcode" type="checkbox"
					value="familyZipcode" />家庭邮编&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="domicile" type="checkbox"
					value="domicile" />户籍地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="accountNo" type="checkbox"
					value="accountNo" />账号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanContract" type="checkbox"
					value="loanContract" />贷款合同号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="isVerify" type="checkbox"
					value="isVerify" />是否核销&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="verifyDate" type="checkbox"
					value="verifyDate" />核销日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="repaymentType" type="checkbox"
					value="repaymentType" />还款方式&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="protocolNo" type="checkbox"
					value="protocolNo" />协议编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanArea" type="checkbox"
					value="loanArea" />放款所属地&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="areaName" type="checkbox"
					value="areaName" />风控区域&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="oldCollecRecord" type="checkbox"
					value="oldCollecRecord" />原风控记录&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
				<td><input name="field" id="overduePrincipalBalance"
					type="checkbox" value="overduePrincipalBalance" />逾期本金余额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueInterest" type="checkbox"
					value="overdueInterest" />逾期利息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overduePenalty" type="checkbox"
					value="overduePenalty" />逾期罚息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueCompound" type="checkbox"
					value="overdueCompound" />逾期复利&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="interestMoney" type="checkbox"
					value="interestMoney" />欠息余额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="penaltyReference" type="checkbox"
					value="penaltyReference" />罚息参照&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="compoundInterestReference"
					type="checkbox" value="compoundInterestReference" />复息参照&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="quotaNo" type="checkbox"
					value="quotaNo" />根额度编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="quotaManager1" type="checkbox"
					value="quotaManager1" />(根额度)主办客户经理&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="managerPhone" type="checkbox"
					value="managerPhone" />客户经理电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="quotaManager2" type="checkbox"
					value="quotaManager2" />(根额度)协办客户经理&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collateralNo" type="checkbox"
					value="collateralNo" />押品编号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collateralId" type="checkbox"
					value="collateralId" />押品类型&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collateralName" type="checkbox"
					value="collateralName" />押品名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="collateralAddress" type="checkbox"
					value="collateralAddress" />押品地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collateralEvalua" type="checkbox"
					value="collateralEvalua" />押品评估值&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="totalConstruc" type="checkbox"
					value="totalConstruc" />建筑面积合计&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="repayDate" type="checkbox"
					value="repayDate" />还款日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="initialRepay" type="checkbox"
					value="initialRepay" />最后还款日&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="lastRepayment" type="checkbox"
					value="lastRepayment" />最后还款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyPhone" type="checkbox"
					value="familyPhone" />家庭号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseCode" type="checkbox"
					value="monthRepayment" />每月还款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="surplusPrincipal" type="checkbox"
					value="surplusPrincipal" />剩余本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="monthRepayment" type="checkbox"
					disabled="disabled" readonly="readonly" checked="checked"
					value="caseCode" />案件序列号&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
				<td><input name="field" id="id" type="checkbox" value="id"
					readonly="readonly" checked="checked" />id&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td></td>
				<td></td>
			</tr>

		</table>

	</form>
</div>