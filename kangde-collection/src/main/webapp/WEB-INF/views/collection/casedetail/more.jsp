<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
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

.panel-tool {
	left: 55px;
	width: auto;
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

	<!-- 操作台 -->
	<shiro:hasPermission name="detail:workspace">
		<script type="text/javascript">
			var urls = new Array();
		</script>
		<div id='czt'>
			<div class="easyui-panel" title="操作台"
				style="width: 100%; padding: 0px;">
				<div id="operation" class="easyui-tabs"
					style="width: 100%; height: 400px">
					<shiro:hasPermission name="detail:linkmanphone:view">
						<div title="电话" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/linkman/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:address:view">
						<div title="地址" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/address/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:phonerecord:view">
						<div title="风控记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/phonerecord/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:visitrecord:view">
						<div title="外访记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/visitrecord/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:casepaid:view">
						<div title="登账记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/casepaid/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:repair:view">
						<div title="查资记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/caseapply/casedetailRepair?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:jointdebt:view">
						<div title="共债案件" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/jointdebt/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:stay:view">
						<div title="留案记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/caseapproval/casedetailstay?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:xiecui:view">
						<div title="协催记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/caseapply/casedetailxiecui?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:letter:view">
						<div title="信函记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/caseapply/casedetailletter?caseId=${caseId}")
						</script>
					</shiro:hasPermission>
					<shiro:hasPermission name="detail:hurryrecord:view">
						<div title="操作记录" style="padding: 0px"></div>
						<script type="text/javascript">
							urls
									.push("/collection/hurryrecord/index?caseId=${caseId}")
						</script>
					</shiro:hasPermission>


					<!-- <div title="操作记录" style="padding: 0px"></div> -->
					<!-- <div title="附件" style="padding: 0px"></div> -->
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$().ready(function() {
				loadTab(getFrameURL(urls[0]));
				initTab();
			});

			function initTab() {
				$("#operation")
						.tabs(
								{
									onSelect : function(title, index) {
										if (title == '电话') { //电话
											loadTab(initContent);
										} else if (title == '地址') { //地址
											loadTab(getFrameURL('/collection/address/index?caseId=${caseId}'));
										} else if (title == '风控记录') { //风控记录
											loadTab(getFrameURL('/collection/phonerecord/index?caseId=${caseId}'));
										} else if (title == '外访记录') { //外访记录
											loadTab(getFrameURL('/collection/visitrecord/index?caseId=${caseId}'));
										} else if (title == '登账记录') { //登账记录
											loadTab(getFrameURL('/collection/casepaid/index?caseId=${caseId}'));
										} else if (title == '查资记录') { //查资记录
											loadTab(getFrameURL('/collection/caseapply/casedetailRepair?caseId=${caseId}'));
										} else if (title == '共债案件') {//共债案件
											loadTab(getFrameURL('/collection/jointdebt/index?caseId=${caseId}'));
										} else if (title == '留案记录') {//留案记录
											loadTab(getFrameURL('/collection/caseapproval/casedetailstay?caseId=${caseId}'));
										} else if (title == '协催记录') {//协催记录
											loadTab(getFrameURL('/collection/caseapply/casedetailxiecui?caseId=${caseId}'));
										} else if (title == '信函记录') {//信函记录
											loadTab(getFrameURL('/collection/caseapply/casedetailletter?caseId=${caseId}'));
										} else if (title == '操作记录') {//操作记录
											loadTab(getFrameURL('/collection/hurryrecord/index?caseId=${caseId}'));
										}
									}
								});
			}
			function getFrameURL(url) {
				return '<iframe style="border: solid 0px #7ec8ea" src="${ctx}'
						+ url
						+ '" width="100%" height="100%" frameborder="no" marginwidth="0" marginheight="0"></iframe>';
			};

			function loadTab(content) {
				var tab = $('#operation').tabs('getSelected');
				$('#operation').tabs('update', {
					tab : tab,
					options : {
						content : content
					}
				});
			}
		</script>

	</shiro:hasPermission>

	<shiro:hasPermission name="detail:myphonerecord:view">
		<!-- 催收记录 -->
		<div id='jl'>
			<div class="easyui-panel" title="记录"
				style="width: 100%; padding: 0px;">
				<iframe id='iframephonerecord'
					style="width: 100%; height: 350px; scrolling: yes; border: none"
					src="${ctx }/collection/phonerecord/input?caseId=${caseId }"></iframe>
			</div>
		</div>
	</shiro:hasPermission>

</body>
<html>