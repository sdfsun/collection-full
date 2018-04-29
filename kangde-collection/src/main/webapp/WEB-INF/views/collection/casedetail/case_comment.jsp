<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<script>
	function showCommentDialog() {
		//$('input:radio[name=caseCommentColor]')[0].checked = true;
		$('input:radio[name=caseCommentColor]')[0].checked = true;
		$("#comment_content").textbox("reset");
		$('#commentdialog').dialog('open',true);
		
	}
	</script>
<jsp:include page="../casedetail/comment.jsp"></jsp:include>	
	