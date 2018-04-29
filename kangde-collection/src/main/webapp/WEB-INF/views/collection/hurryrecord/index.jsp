<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
	<div style='margin:5px 0px'>
			<k:dictionary constName="OPER_TYPE" id="hurCat" name="hurCat"
						 selectType="all" width="150" />
	</div>
	
	<div style='height: 90%; width: 100%'>
		<table id="_datagrid"></table>
	</div>


