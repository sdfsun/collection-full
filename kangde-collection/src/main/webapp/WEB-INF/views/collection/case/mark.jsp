<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 

<div style="margin:20px;">

<div>
	<form:form id='_color_form' method="post" commandName="caseModel">
		<form:radiobuttons  htmlEscape="false" path="color" items="${colorMap}" delimiter="&nbsp;" />
	</form:form>

</div>
</div>

