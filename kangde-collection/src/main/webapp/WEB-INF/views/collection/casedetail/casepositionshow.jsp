<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="opendiv">
	<c:choose>

		<c:when test="${mess==null}">

			<table>
				<tr>
					<td class="idshow"><label>&nbsp;职位名称&nbsp;&nbsp;</label></td>
					<td>${job}</td>
				</tr>
				<tr>
					<td class="idshow"><label>&nbsp;预计薪资&nbsp;&nbsp;</label></td>
					<td>${salary}</td>
				<tr>
					<td class="idshow"><label>&nbsp;城市&nbsp;&nbsp;</label></td>
					<td>${city}</td>
				</tr>
			</table>
		</c:when>

		<c:when test="${mess!=null}">
			<table>
				<tr>
					<td class="idshow"><label>&nbsp;提示消息：&nbsp;&nbsp;</label></td>
					<td>${mess}</td>
				</tr>
			</table>


		</c:when>
	</c:choose>
</div>