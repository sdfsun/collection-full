<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
#phone {
	border-collapse: collapse;
	cellspacing: 0;
	cellpadding: 0;
	border: 0;
	border-spacing: 0px;
	cellspacing: 0;
	width: 100%;
}

#phone tr td {
	padding: 0px;
	margin: 0px;
	width: 150px;
	border: 1px solid #E6E6E6;
	text-align: center;
}

#phone tr td label {
	background-color: #EFEFEF;
	display: block;
	height: 100%;
	padding: 5px;
	text-align: right;
}

#phone .idshow {
	width: 50px;
}
#phone .idshow1 {
	width: 60px;
}
</style>
<div>
	<table id="phone">

		<c:forEach items="${objArr}" var="row" varStatus="status">
			<tr>
				<td class="idshow1" rowspan="6" >${row.name}</td>
			</tr>
			<tr>
				<td class="idshow"><label>&nbsp;区名&nbsp;&nbsp;</label></td>
				<td>${row.adname}</td>
			<tr>
				<td class="idshow"><label>&nbsp;详细地址&nbsp;&nbsp;</label></td>
				<td>${row.address}</td>
			</tr>

			<tr>
				<td class="idshow"><label>&nbsp;城市名&nbsp;&nbsp;</label></td>
				<td>${row.cityname}</td>
			</tr>
			<tr>
			<td class="idshow"><label>&nbsp;电话&nbsp;&nbsp;</label></td>
				<c:if test="${row.tel!='[]' }">
				<td>${row.tel}</td>
				</c:if>
				<c:if test="${row.tel=='[]' }">
				<td>暂无</td>
				</c:if>
			</tr>
			<tr>
				<td class="idshow"><label>&nbsp;GPS坐标&nbsp;&nbsp;</label></td>
				<td>${row.location}</td>
			</tr>
		</c:forEach>
	</table>
</div>