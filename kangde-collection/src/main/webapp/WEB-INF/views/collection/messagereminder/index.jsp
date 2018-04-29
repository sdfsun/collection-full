<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width:50%; overflow-y: hidden;">
		
	<!-- 
		<div style='height:100px;'>
			<table id="messagereminder_datagrid"></table>
		</div>
		
		 -->
		
		
		<div style='overflow-y:scroll;'>
		
		  <div>${tomorrowVisitCount }个案件拟定于明日进行外访</div>
		    <c:forEach var="item" items="${caseToFollow}" varStatus="status">
		                 <div>案件[${item.caseCode }]拟定于今日进行跟进</div>
			</c:forEach> 
			
		  <c:if test="${targetArchive > 0}">
					   <div>目标业绩录入</div>
			</c:if>
	
		           
					
		            
		           
		            <c:forEach var="item" items="${caseToUploadReport}" varStatus="status">
					        
		                <div>案件[${item.caseCode}] 是否已完成外访，请尽快上传外访报告</div>
					</c:forEach> 
		            
		                <div>${divisionCase }个案件待分案</div>
		                <div>${visitreportToApprove }个外访待审批</div>
		                <div>${visitreportToAppoint }个外访待排程</div>
		                <div>${letterToApprove }个信函待审批</div>
		                <div>${letterToPost }个信函待邮寄</div>
		                <div>${xiecuiTasklistCount }个协催待回复</div>
		                <div>${chaziToApproveCount }个查资待审批</div>
		                <div>${querydealCount }个查资待回复</div>
		                <div>${toConfirmPayCount }个还款待确认</div>
	</div>
		
</div>
