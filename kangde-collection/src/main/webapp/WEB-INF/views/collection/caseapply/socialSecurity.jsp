<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
			<td  class='tdvalueclass'>${caseCode}</td>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;&nbsp;案件人姓名&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseName}</td>
			<td class='tdlableclass'><label style="text-align: right;">&nbsp;&nbsp;证件号&nbsp;&nbsp;</label></td>
			<td class='tdvalueclass'>${caseNum}</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;单位名称&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.unitName }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;单位地址&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.unitAddress }
			
			<c:if test="${socialSecurity.unitAddress!=''}">
						<span class='icon icon-tel'
							onclick='saveAddress("${caseName}","本人","${socialSecurity.unitAddress}","单位地址")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</c:if>
		
			</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;最后缴费日期&nbsp;&nbsp;</label></td>
			<td>
				<fmt:formatDate value="${socialSecurity.lastRenewDate }" pattern="yyyy-MM-dd"/> </td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;单位电话1&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.unitPhone1 }
			
				<c:if test="${socialSecurity.unitPhone1!=''}">
				<span class='icon icon-tel'
				onclick='savePhone("${caseName}","${caseNum}","本人","单位电话","${socialSecurity.unitPhone1 }")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</c:if>
			
			
			
			<td><label style="text-align: right;">&nbsp;&nbsp;单位电话2&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.unitPhone2 }
				<c:if test="${socialSecurity.unitPhone2!=''}">
				
								<span class='icon icon-tel'
								onclick='savePhone("${caseName}","${caseNum}","本人","单位电话","${socialSecurity.unitPhone2 }")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
								
				</c:if>
				</td>
		</tr>
		<tr>
			<td><label style="text-align: right;">&nbsp;&nbsp;联系人&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.linkman }</td>
			<td><label style="text-align: right;">&nbsp;&nbsp;联系人电话&nbsp;&nbsp;</label></td>
			<td>${socialSecurity.phone }
			<c:if test="${socialSecurity.phone!=''}">
			
				<span class='icon icon-tel'
				onclick='savePhone("${caseName}","${caseNum}","同事","联系人电话","${socialSecurity.phone }")'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				
				</c:if>
				
			</td>
		</tr>


		<tr>
			<td><label>备注</label></td>
			<td colspan="5">${socialSecurity.remark }</td>
		</tr>


	</table>
</div>