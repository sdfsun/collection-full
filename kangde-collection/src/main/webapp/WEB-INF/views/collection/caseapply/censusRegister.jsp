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
			<td><label style="text-align: right;">&nbsp;&nbsp;户籍地址&nbsp;&nbsp;</label></td>
			<td>${censusRegister.crAddress}<c:if
					test="${censusRegister.crAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${caseName}","本人","${censusRegister.crAddress}","户籍地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>


			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;住址&nbsp;&nbsp;</label></td>
			<td>${censusRegister.address}<c:if
					test="${censusRegister.address!='' }">

					<span class='icon icon-tel'
						onclick='saveAddress("${caseName}","本人","${censusRegister.address}","家庭地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>
			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;文化程度&nbsp;&nbsp;</label></td>
			<td>${censusRegister.culture}</td>




		</tr>

		<tr>

			<td><label style="text-align: right;">&nbsp;&nbsp;职业&nbsp;&nbsp;</label></td>
			<td>${censusRegister.career}</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;家庭电话&nbsp;&nbsp;</label></td>
			<td>${censusRegister.familyPhone}<c:if
					test="${censusRegister.familyPhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${caseName}","${caseNum}","本人","家庭电话","${censusRegister.familyPhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;手机号&nbsp;&nbsp;</label></td>
			<td>${censusRegister.mobile}<c:if
					test="${censusRegister.mobile!='' }">

					<span class='icon icon-tel'
						onclick='savePhone("${caseName}","${caseNum}","本人","手机","${censusRegister.mobile}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>
			</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;婚姻状态&nbsp;&nbsp;</label></td>
			<td>
					<k:dictionary constName="GAM" name="married" readonly="true"
						value="${censusRegister.married}" width="150" />
				</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;配偶姓名&nbsp;&nbsp;</label></td>
			<td>${censusRegister.mate}</td>
		</tr>

		<tr>
			<td><label style="text-align: right;">同户人员1</label></td>
			<td>${censusRegister.personnel1}<c:if
					test="${censusRegister.personnel1!='' }">

					<span class='icon icon-tel'
						onclick='saveAddress("${censusRegister.personnel1}","${censusRegister.relation1}","${censusRegister.crAddress}","户籍地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>


				</c:if>




			</td>
			<td><label style="text-align: right;">关系</label></td>
			<td>${censusRegister.relation1}</td>
			<td><label style="text-align: right;">证件号</label></td>
			<td>${censusRegister.caseNum1}</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">同户人员2</label></td>
			<td>${censusRegister.personnel2}<c:if
					test="${censusRegister.personnel2!='' }">

					<span class='icon icon-tel'
						onclick='saveAddress("${censusRegister.personnel2}","${censusRegister.relation2}","${censusRegister.crAddress}","户籍地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>


				</c:if>







			</td>
			<td><label style="text-align: right;">关系</label></td>
			<td>${censusRegister.relation2}</td>
			<td><label style="text-align: right;">证件号</label></td>
			<td>${censusRegister.caseNum2}</td>
		</tr>

		<tr>
			<td><label>备注</label></td>
			<td colspan="5">${censusRegister.remark}</td>
		</tr>
	</table>
</div>