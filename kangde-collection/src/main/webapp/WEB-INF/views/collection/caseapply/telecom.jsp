<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
body {
	margin: 0px;
	padding: 0px;
}

table {
	border-collapse: collapse;
	cellspacing: 0;
	cellpadding: 0;
	border: 0;
	border-spacing: 0px;
	cellspacing: 0;
	width: 100%;
}

table tr td {
	padding: 10px;
	margin: 0px;
	width: auto;
	border: 1px solid #E6E6E6;
	text-align: center;
}


.tdlableclass{
width:100px;
}
.tdvalueclass{
min-width: 100px;
}
table tr td label {
	display: block;
	height: 100%;
	text-align: right;
}

.textbox {
	border: 0px;
}

.icon {
	background: url(${ctxStatic}/img/icon/icon_caseapply.png) no-repeat;
}

.icon-tel {
	background-position: 0px 3px;
	cursor:pointer;
}

.combo-arrow {
	background-color: none;
	background: none;
}
</style>
<div>

	<table>
		<tr>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;&nbsp;案件序列号&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseCode}</td>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;&nbsp;案件人姓名&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseName}</td>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;&nbsp;证件号&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseNum}</td>

		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;手机号&nbsp;&nbsp;</label></td>
			<td>${telecom.mobile }<c:if test="${telecom.mobile!=''}">

					<span class='icon icon-tel'
						onclick='savePhone("${caseName}","${caseNum}","本人","手机","${telecom.mobile }")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>


			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;运行商&nbsp;&nbsp;</label></td>
			<td>${telecom.operator }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;状态&nbsp;&nbsp;</label></td>
			<td><c:if test="${telecom.mobileStatus!='' }">
					<k:dictionary constName="YUNXINGSHANG" name="mobileStatus"
						readonly="true" value="${telecom.mobileStatus }" selectType="1"
						width="150" />
				</c:if></td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;宽带号&nbsp;&nbsp;</label></td>
			<td>${telecom.wideBand }<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><label style="text-align: right;">&nbsp;&nbsp;网络提供商&nbsp;&nbsp;</label></td>
			<td>${telecom.networkProvide }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;状态&nbsp;&nbsp;</label></td>
			<td><c:if test="${telecom.wideStatus!='' }">
					<k:dictionary constName="WANGLUO" name="wideStatus" readonly="true"
						value="${telecom.wideStatus }" selectType="1" width="150" />

				</c:if></td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;户籍地址&nbsp;&nbsp;</label></td>
			<td>${telecom.crAddress }<c:if test="${telecom.crAddress!=''}">
					<span class='icon icon-tel'
						onclick='saveAddress("${caseName}","本人","${telecom.crAddress}","户籍地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>

			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;住址&nbsp;&nbsp;</label></td>
			<td>${telecom.address }<c:if test="${telecom.address!=''}">
					<span class='icon icon-tel'
						onclick='saveAddress("${caseName}","本人","${telecom.address}","家庭地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;联系人1&nbsp;&nbsp;</label></td>
			<td>${telecom.linkman1 }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;关系&nbsp;&nbsp;</label></td>
			<td>${telecom.relation1 }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;证件号&nbsp;&nbsp;</label></td>
			<td>${telecom.caseNum1 }</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;联系人2&nbsp;&nbsp;</label></td>
			<td>${telecom.linkman2 }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;关系&nbsp;&nbsp;</label></td>
			<td>${telecom.relation2 }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;证件号&nbsp;&nbsp;</label></td>
			<td>${telecom.caseNum2 }</td>
		</tr>
		<tr>
			<td><label>备注</label></td>
			<td colspan="5">${telecom.remark }</td>
		</tr>
	</table>
</div>