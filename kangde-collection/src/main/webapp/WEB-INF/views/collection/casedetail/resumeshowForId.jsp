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
					<td><label>&nbsp;姓名&nbsp;&nbsp;</label></td>
					<td>${name}</td>
					<td><label>&nbsp;项目/培训机构&nbsp;&nbsp;</label></td>
					<td>${detail.training_organization}</td>
				</tr>
				<tr>
					<td><label>&nbsp;年龄&nbsp;&nbsp;</label></td>
					<td>${age}</td>
					<td><label>&nbsp;项目/培训内容&nbsp;&nbsp;</label></td>
					<td>${detail.training_content}</td>
				</tr>
				<tr>
					<td><label>&nbsp;性别&nbsp;&nbsp;</label></td>
					<td>${sex}</td>
					<td><label>&nbsp;期望薪资&nbsp;&nbsp;</label></td>
					<td>${detail.expected_salary}</td>
				</tr>
				<tr>
					<td><label>&nbsp;工作经验&nbsp;&nbsp;</label></td>
					<td>${experience}</td>
					<td><label>&nbsp;工作时间&nbsp;&nbsp;</label></td>
					<td>${detail.work_time}</td>
				</tr>
				<tr>
				<td ><label>&nbsp;其他职位&nbsp;&nbsp;</label></td>
					<td>${detail.job_target}</td>
					<td><label>&nbsp;学历&nbsp;&nbsp;</label></td>
					<td>${detail.education}</td>
				</tr>
				<tr>
					<td><label>&nbsp;期待职位&nbsp;&nbsp;</label></td>
					<td>${want_job_name}</td>
					<td><label>&nbsp;职位名称&nbsp;&nbsp;</label></td>
					<td>${detail.job_title}</td>
				</tr>
				<tr>
					<td ><label>&nbsp;自我描述&nbsp;&nbsp;</label></td>
					<td>${detail.self_introduce}</td>
					<td><label>&nbsp;教育经历&nbsp;&nbsp;</label></td>
					<td>${detail.education_history}</td>
				</tr>
				<tr>
					<td><label>&nbsp;工作内容&nbsp;&nbsp;</label></td>
					<td>${detail.work_content}</td>
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
