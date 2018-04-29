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
				<td><input name="field" id="caseName" type="checkbox"
					value="caseName" />姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanContract" type="checkbox"
					value="loanContract" />贷款合同号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseSex" type="checkbox"
					value="caseSex" />性别&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseBirthday" type="checkbox"
					value="caseBirthday" />生日&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="caseNum" type="checkbox"
					value="caseNum" />证件号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseMoney" type="checkbox"
					value="caseMoney" />委案金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="entrustRate" type="checkbox"
					value="entrustRate" />委托费率%&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueDays" type="checkbox"
					value="overdueDays" />逾期天数&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
				<td><input name="field" id="monthRepayment" type="checkbox"
					value="monthRepayment" />每月还款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overduePeriods" type="checkbox"
					value="overduePeriods" />逾期期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="repaymentPeriods" type="checkbox"
					value="repaymentPeriods" />已还期数&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overduePrincipal" type="checkbox"
					value="overduePrincipal" />逾期本金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="mobile1" type="checkbox"
					value="mobile1" onclick="clickMobile()" />本人手机1&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="mobile2" type="checkbox"
					value="mobile2" onclick="clickMobile2()" />本人手机2&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyPhone" type="checkbox"
					value="familyPhone" />家庭号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyName" type="checkbox"
					value="companyName" />单位名称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyPhone" type="checkbox"
					value="companyPhone" />单位号码&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
				<td><input name="field" id="repayDate" type="checkbox"
					value="repayDate" />还款日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseFileNo" type="checkbox"
					value="caseFileNo" />档案号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseAppNo" type="checkbox"
					value="caseAppNo" />申请单号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="socialSecurityNo" type="checkbox"
					value="socialSecurityNo" />社保电脑号&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="socialCardNo" type="checkbox"
					value="socialCardNo" />社保卡号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="policyExpire" type="checkbox"
					value="policyExpire" />保单到期日&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanMoney" type="checkbox"
					value="loanMoney" />贷款金额&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="creditId" type="checkbox"
					value="creditId" />信贷分类&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="loanRate" type="checkbox"
					value="loanRate" />贷款利率&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="casePosition" type="checkbox"
					value="casePosition" />案人职位&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="caseDepartment" type="checkbox"
					value="caseDepartment" />案人部门&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="bankAccount" type="checkbox"
					value="bankAccount" />开户行&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="accountNo" type="checkbox"
					value="accountNo" />账号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="cusIntroduc" type="checkbox"
					value="cusIntroduc" />客户简介&nbsp;&nbsp;&nbsp;&nbsp;</td>

			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="dealer" type="checkbox"
					value="dealer" />经销商&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="price" type="checkbox"
					value="price" />车价&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="number" type="checkbox"
					value="number" />车型&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="liceNo" type="checkbox"
					value="liceNo" />牌照号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="brand" type="checkbox"
					value="brand" />品牌&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="vinNo" type="checkbox"
					value="vinNo" />车架号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="engineNo" type="checkbox"
					value="engineNo" />发动机号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collecRemark" type="checkbox"
					value="collecRemark" />催收小结&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="email" type="checkbox"
					value="email" />邮箱&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="currency" type="checkbox"
					value="currency" />币种&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="oldCollecRecord" type="checkbox"
					value="oldCollecRecord" />原催收记录&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueAge" type="checkbox"
					value="overdueAge" />逾期账龄&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="overdueDate" type="checkbox"
					value="overdueDate" />逾期开始日期&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="repaymentTerm" type="checkbox"
					value="repaymentTerm" />还款期限&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="collecType" type="checkbox"
					value="collecType" />催收分类&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="overdueInterest" type="checkbox"
					value="overdueInterest" />逾期利息&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="lateFee" type="checkbox"
					value="lateFee" />滞纳金&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="companyAddress" type="checkbox"
					value="companyAddress" />单位地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="familyAddress" type="checkbox"
					value="familyAddress" />家庭地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="domicile" type="checkbox"
					value="domicile" />户籍地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input name="field" id="domicileZipcode" type="checkbox"
					value="domicileZipcode" />户籍地邮编&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="billAddress" type="checkbox"
					value="billAddress" />对账单地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="billZipcode" type="checkbox"
					value="billZipcode" />对账单邮编&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input name="field" id="billZipcode" type="checkbox"
					value="agentRate" />业绩率&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
			</tr>


		</table>

	</form>
</div>