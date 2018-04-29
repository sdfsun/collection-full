<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
#resumeshow{width:92%;margin:30px auto}
#resumeshow thead{
height:28px;
line-height:28px;

}
#resumeshow thead label{
padding:0;
margin:0;
}
#resumeshow tr td:nth-child(1){
width:100px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
}
#resumeshow tr td:nth-child(2){
width:60px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
}
#resumeshow tr td:nth-child(3){
width:60px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
	
}
#resumeshow tr td:nth-child(4){
width:150px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
	
}
#resumeshow tr td:nth-child(5){
width:150px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
	
}
#resumeshow tr td:nth-child(6){
width:80px;
	collapse:0;
	padding:0;
	margin:0;
	height:28px;
}




</style>
<div>
	<c:choose>
		<c:when test="${mess==null}">
			<table id="resumeshow">
				<thead>
					<tr>
						<td><label style="text-align:center">&nbsp;姓名&nbsp;&nbsp;</label></td>
						<td><label style="text-align:center">&nbsp;性别&nbsp;&nbsp;</label></td>
						<td><label style="text-align:center">&nbsp;年龄&nbsp;&nbsp;</label></td>
						<td><label style="text-align:center">&nbsp;工作经验&nbsp;&nbsp;</label></td>
						<td><label style="text-align:center">&nbsp;期待薪资&nbsp;&nbsp;</label></td>
						<td><label style="text-align:center">&nbsp;操作&nbsp;&nbsp;</label></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${objArr}" var="row" varStatus="status">
						<tr>

							<td>${row.name}</td>

							<td>${row.sex}</td>

							<td>${row.age}</td>

							<td>${row.experience}</td>

							<td>${row.salary}</td>
							<td><a target="_blank"
								style="text-decoration: underline; cursor: pointer"
								onclick="detailed('${row._id}')"> <font color=blue>[查看]</font></a></td>
						</tr>
					</c:forEach>
				</tbody>

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
