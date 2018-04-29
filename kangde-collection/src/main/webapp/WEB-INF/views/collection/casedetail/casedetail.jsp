<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>${caseDetailVo.caseName}${caseDetailVo.caseCode}</title>
<%@ include file="/common/taglibs.jsp"%>
<c:set var="ev" value="1.4" />
<meta http-equiv="X-UA-Compatible" content="IE=EDGE;chrome=1" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/default.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/easyui-${ev}/themes/bootstrap/easyui.css" />
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

table {
	border-collapse: collapse;
	cellspacing: 0;
	cellpadding: 0;
	border: 0;
	border-spacing: 0px;
	cellspacing: 0;
	width: 100%;
}

table tr td {
	padding: 0px;
	margin: 0px;
	width: 150px;
	border: 1px solid #E6E6E6;
	text-align: center;
}

table tr td label {
	background-color: #EFEFEF;
	display: block;
	height: 100%;
	padding: 5px;
	text-align: right;
}

table tr td input {
	display: block;
	height: 100%;
	width: 150px;
	padding: 0px;
	margin: 0px;
	text-align: center;
	text-overflow: ellipsis;
}

#_detail_form .textbox {
	border: 0px;
}

.panel-header {
	background: #488EC1;
}

.panel-header, .panel-body {
	border-color: #488EC1;
}

#czt .panel-title, #jl .panel-title {
	color: #FFFFFF;
}

.detailHeader {
	background: #488EC1;
	padding: 10px;
}

#info  .panel-header, .panel-body {
	border-color: #EFF7FA;
}

#info  .panel-header {
	background: #EFF7FA;
}

#otherInfo  .panel-header, .panel-body {
	border-color: #EFF7FA;
}

#otherInfo  .panel-header {
	background: #EFF7FA;
}

.otherInfo .panel-tool {
	left: 55px;
	width: auto;
}


.textbox .textbox-text {
  font-size: 13px;
}
.idshow{
 	width:50px;
}  


</style>

<script type="text/javascript" charset="utf-8">
	var ctx = "${ctx}";
	var ctxStatic = "${ctxStatic}";
</script>

<script type='text/javascript'
	src='${ctxStatic}/js/jquery/jquery-2.1.0.min.js' charset='utf-8'></script>
<script type="text/javascript"
	src="${ctxStatic}/js/easyui-${ev}/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="${ctxStatic}/js/jquery/jquery-extend.js" charset="utf-8"></script>
</head>
<body>

	<!-- 基础信息 -->	
	<shiro:hasPermission name="detail:caseinfo">
		<%@ include file="form.jsp"%>
	</shiro:hasPermission>
	<!-- 操作台和催收记录 -->
	
	<iframe id='iframephonerecord'
					style="width: 100%; height: 820px; scrolling: yes; border: none"
					src="${ctx }/collection/casedetail/more?caseId=${caseId }"></iframe>

	<%@ include file="detail_comment.jsp"%>

<style>
.colorlist{
margin:0px -20px; padding:0px;text-align: left;
}
</style>
</body>
<html>