<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<style>
.ptpclass {
	float: left;
	width: 45%;
	height: 320px;
	margin: 10 0;
	padding:0px;
}

.cpclass {
	float: left;
	width: 45%;
	height: 320px;
	margin: 10;
	padding:0px 10px;
}

</style>


<div>
	<div  class='ptpclass' style='height:8px;'>
	<span style='float:left;'>PTP记录</span>
	</div>
	<div  class='cpclass'  style='height:8px;'>
	<span style='float:left;'>登账记录</span>
	
	<shiro:hasPermission name="detail:casepaid:save">
			<div style='float:left;'>	<a style='width: 80px; margin: -4 0 0 5;' class="easyui-linkbutton"
				href="javascript:void(0)" onclick='showDialog()'>新增CP</a></div>
	</shiro:hasPermission>
	
	
				
	</div>
</div>

	<div class='ptpclass'>
		
		<div style="height: 96%; width: 100%">
			<table id="ptp_datagrid"></table>
		</div>
	</div>
	<div class='cpclass'>
	   
		<div style="height:96%;width:110%;">
			<table id="cp_datagrid"></table>
		</div>
	</div>
