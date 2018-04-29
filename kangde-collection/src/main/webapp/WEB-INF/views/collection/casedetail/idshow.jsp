<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="opendiv">


	<c:choose>

		<c:when test="${mess==null}">

			<table>
				<tr>
					<td class="idshow"><label>&nbsp;城市&nbsp;&nbsp;</label></td>
					<td>${place}</td>
				</tr>
				<tr>
					<td class="idshow"><label>&nbsp;性别&nbsp;&nbsp;</label></td>
					<td>${sex}</td>
				<tr>
					<td class="idshow"><label>&nbsp;生日&nbsp;&nbsp;</label></td>
					<td>${birth}</td>
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