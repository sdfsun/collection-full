<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
#resumeshowForId{width:92%;margin:30px auto}
#resumeshowForId tr td:nth-child(1){magin:10px;background:#EFEFEF;}
#resumeshowForId tr td:nth-child(3){magin:10px;background:#EFEFEF;}
#resumeshowForId tr td:nth-child(2){text-align:left;padding-left:10px;}
</style>
<div>
	<c:choose>
		<c:when test="${mess==null}">
			<table id="resumeshowForId">
				<tr>
					<td><label>&nbsp;&nbsp;被执行人姓名/名称&nbsp;</label></td>
					<td>${name}</td>
					<td><label>&nbsp;性别&nbsp;&nbsp;</label></td>
					<td>${sex}</td>
				</tr>
				<tr>
					<td><label>&nbsp;身份证/组织就够代码&nbsp;&nbsp;</label></td>
					<td>${idcard}</td>
					<td><label>&nbsp;执行法院&nbsp;&nbsp;</label></td>
					<td>${excute_court}</td>
				</tr>
				<tr>
					<td><label>&nbsp;省份&nbsp;&nbsp;</label></td>
					<td>${province}</td>
					<td><label>&nbsp;执行依据文号&nbsp;&nbsp;</label></td>
					<td>${excute_basis_num}</td>
				</tr>
				<tr>
					<td><label>&nbsp;立案时间&nbsp;&nbsp;</label></td>
					<td>${case_created_time}</td>
					<td><label>&nbsp;案号&nbsp;&nbsp;</label></td>
					<td>${case_num}</td>
				</tr>
				<tr>
				<td ><label>&nbsp;做出执行依据单位&nbsp;&nbsp;</label></td>
					<td>${excute_basis_organization}</td>
					<td ><label>&nbsp;发布时间&nbsp;&nbsp;</label></td>
					<td>${publish_time}</td>
				</tr>
				<tr>
					<td><label>&nbsp;被执行人的履行情况&nbsp;&nbsp;</label></td>
					<td>${be_excuted_fulfil_state}</td>
					<td><label>&nbsp;失信被执行人行为具体情形&nbsp;&nbsp;</label></td>
					<td>${be_excuted_behaviour_situation}</td>
				</tr>
				<tr>
				<td><label>&nbsp;生效法律文书确定的义务&nbsp;&nbsp;</label></td>
					<td colspan="3">${duty}</td>
				</tr>
			</table>
		</c:when>

		<c:when test="${mess!=null}">
			<table>
				<tr>
					<td ><label>&nbsp;提示消息：&nbsp;&nbsp;</label></td>
					<td>${mess}</td>
				</tr>
			</table>


		</c:when>
	</c:choose>
</div>
