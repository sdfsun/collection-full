<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<style>
#phone{
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

#phone .idshow{
 	width:50px;
}  

</style>
<div>
	<table id="phone">
		<tr>
			<td class="idshow"><label>&nbsp;通信公司&nbsp;&nbsp;</label></td>
			<td>${telecomName}</td>
		</tr>
		<tr>
			<td class="idshow"><label>&nbsp;邮编&nbsp;&nbsp;</label></td>
			<td>${postcode}</td>
		<tr>
			<td class="idshow"><label>&nbsp;电话区号&nbsp;&nbsp;</label></td>
			<td>${phoneareacode}</td>
		</tr>
		<tr>
			<td class="idshow"><label>&nbsp;城市&nbsp;&nbsp;</label></td>
			<td>${city}</td>
		</tr>
		<tr>
			<td class="idshow"><label>&nbsp;省份&nbsp;&nbsp;</label></td>
			<td>${province}</td>
		</tr>
	</table>
</div>