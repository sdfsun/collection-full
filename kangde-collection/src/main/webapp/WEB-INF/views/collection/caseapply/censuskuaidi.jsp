<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
width:120px;
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
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;案件序列号&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseCode}</td>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;案件人姓名&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseName}</td>

			<td class='tdlableclass'><label style="text-align: right;">&nbsp;证件号&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseNum}</td>

		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;历史用户名&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.hisUserName}</td>
			<td><label style="text-align: right;">&nbsp;手机&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.mobile}
				<c:if test="${censuskuaidi.mobile!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${caseName}","${caseNum}","本人","手机","${censuskuaidi.mobile}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if></td>
			<td><label style="text-align: right;">&nbsp;家庭电话&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.familyPhone}
			<c:if test="${censuskuaidi.familyPhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${caseName}","${caseNum}","本人","家庭电话","${censuskuaidi.familyPhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if></td>
		</tr>
			<tr>
			<td><label style="text-align: right;">&nbsp;家庭地址&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.familyAddress}
				<c:if
					test="${censuskuaidi.familyAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${caseName}","本人","${censuskuaidi.familyAddress}","家庭地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				</td>
			<td><label style="text-align: right;">&nbsp;邮箱&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.email}</td>
			<td><label style="text-align: right;">&nbsp;qq&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.qq}</td>
		</tr>
				<tr>
			<td><label style="text-align: right;">&nbsp;qq群&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.qqGroup}</td>
			<td><label style="text-align: right;">&nbsp;微信&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.wechat}</td>
			<td><label style="text-align: right;">&nbsp;微博&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.blog}</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;收货人姓名&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.consigneeName}</td>
			<td><label style="text-align: right;">&nbsp;收货人电话&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.consigneePhone}
				<c:if test="${censuskuaidi.consigneePhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${censuskuaidi.consigneeName}","","其他","其他电话","${censuskuaidi.consigneePhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			</td>
			<td><label style="text-align: right;">&nbsp;收货人地址&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.consigneeAddress}
				<c:if
					test="${censuskuaidi.consigneeAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${censuskuaidi.consigneeName}","其他","${censuskuaidi.consigneeAddress}","其他地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>
				</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;单位名称&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.unitName}</td>
			<td><label style="text-align: right;">&nbsp;单位地址&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.unitAddress}
					<c:if
					test="${censuskuaidi.unitAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${censuskuaidi.consigneeName}","其他","${censuskuaidi.unitName}/${censuskuaidi.unitAddress}","单位地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>

				</c:if>
			</td>
			<td><label style="text-align: right;">&nbsp;单位电话&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.unitPhone}
				<c:if test="${censuskuaidi.unitPhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${censuskuaidi.consigneeName}","","其他","单位电话","${censuskuaidi.unitPhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;重要联系人名称&nbsp;</label></td>
			<td>${censuskuaidi.importantLinkmanName}</td>
			<td><label style="text-align: right;">&nbsp;重要联系人电话&nbsp;</label></td>
			<td>${censuskuaidi.importantLinkmanPhone}
				<c:if test="${censuskuaidi.importantLinkmanPhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${censuskuaidi.importantLinkmanName}","${censuskuaidi.importantLinkmanCardNum}","其他","其他电话","${censuskuaidi.importantLinkmanPhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			</td>
			<td><label style="text-align: right;">&nbsp;重要联系人地址&nbsp;</label></td>
			<td>${censuskuaidi.importantLinkmanAddress}
				<c:if
					test="${censuskuaidi.importantLinkmanAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${censuskuaidi.importantLinkmanName}","其他","${censuskuaidi.importantLinkmanAddress}","其他地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			</td>
		</tr>
				<tr>
			<td><label style="text-align: right;">重要联系人身份证</label></td>
			<td>${censuskuaidi.importantLinkmanCardNum}</td>
			<td><label style="text-align: right;">&nbsp;联系人名称&nbsp;</label></td>
			<td>${censuskuaidi.linkmanName}</td>
			<td><label style="text-align: right;">&nbsp;联系人电话&nbsp;</label></td>
			<td>${censuskuaidi.linkmanPhone}
				<c:if test="${censuskuaidi.linkmanPhone!='' }">
					<span class='icon icon-tel'
						onclick='savePhone("${censuskuaidi.linkmanName}","${censuskuaidi.linkmanCardNum}","其他","其他电话","${censuskuaidi.linkmanPhone}")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				</td>
		</tr>
				<tr>
			<td><label style="text-align: right;">&nbsp;联系人身份证&nbsp;</label></td>
			<td>${censuskuaidi.linkmanCardNum}</td>
			<td><label style="text-align: right;">&nbsp;联系人地址&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.linkmanAddress}
				<c:if
					test="${censuskuaidi.linkmanAddress!='' }">
					<span class='icon icon-tel'
						onclick='saveAddress("${censuskuaidi.linkmanName}","其他","${censuskuaidi.linkmanAddress}","其他地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
				</td>
			<td><label style="text-align: right;">&nbsp;联系人工作单位&nbsp;</label></td>
			<td>${censuskuaidi.linkmanUnitName}</td>
		</tr>
				<tr>
			<td><label style="text-align: right;">&nbsp;获取时间&nbsp;&nbsp;</label></td>
			<td><fmt:formatDate value="${censuskuaidi.obtainTime}"  pattern="yyyy/MM/dd" /> </td>
			<td><label style="text-align: right;">&nbsp;备注&nbsp;&nbsp;</label></td>
			<td>${censuskuaidi.remark}</td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>