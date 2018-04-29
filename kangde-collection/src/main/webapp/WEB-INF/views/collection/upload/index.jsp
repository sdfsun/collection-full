<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript"
	src="${ctxStatic}/js/upload/js/plupload.full.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/js/upload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<!-- 国际化中文支持 -->
<script type="text/javascript"
	src="${ctxStatic}/js/upload/js/i18n/zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/upload/js/jquery.plupload.queue/css/jquery.plupload.queue.css">


<!-- Add mousewheel plugin (this is optional) -->
<script type="text/javascript"
	src="${ctxStatic}/js/upload/show/lib/jquery.mousewheel-3.0.6.pack.js"></script>

<!-- Add fancyBox main JS and CSS files -->
<script type="text/javascript"
	src="${ctxStatic}/js/upload/show/source/jquery.fancybox.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/upload/show/source/jquery.fancybox.css"
	media="screen" />

<!-- Add Button helper (this is optional) -->
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/upload/show/source/helpers/jquery.fancybox-buttons.css" />
<script type="text/javascript"
	src="${ctxStatic}/js/upload/show/source/helpers/jquery.fancybox-buttons.js"></script>

<!-- Add Thumbnail helper (this is optional) -->
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/upload/show/source/helpers/jquery.fancybox-thumbs.css" />
<script type="text/javascript"
	src="${ctxStatic}/js/upload/show/source/helpers/jquery.fancybox-thumbs.js"></script>

<!-- Add Media helper (this is optional) -->
<script type="text/javascript"
	src="${ctxStatic}/js/upload/show/source/helpers/jquery.fancybox-media.js"></script>
<jsp:include page="script.jsp"></jsp:include>
<style type="text/css">
.west {
	width: 200px;
	padding: 10px;
}

.north {
	height: 100px;
}

.plupload_header {
	display: none;
}

.plupload_header_content {
	background: none;
	min-height: 50px;
	color: red;
}

#uploader {
	padding: 0px;
	margin: 0px;
}

.plupload_scroll .plupload_filelist {
	height: 100px;
	background: #F5F5F5;
	overflow-y: scroll;
}

.plupload_wrapper {
	font: normal 11px Verdana, sans-serif;
	width: 100%;
}

.audio {
	margin: 10px;
}

.plupload_container {
	padding: 0px;
}

.panel-header {
	background: #488EC1;
}

.panel-title {
	color: #FFFFFF;
}

.plupload_filelist_footer {
	border-bottom: 1px solid #EFF7GA;
	height: 22px;
	line-height: 20px;
	vertical-align: middle;
}

.plupload_filelist_header {
	border-top: 1px solid #EFF7GA;
	border-bottom: 1px solid #EFF7GA;
}

.plupload_filelist_header, .plupload_filelist_footer {
	background: #EFF7FA;
}

.plupload_scroll .plupload_filelist {
	background: #FFF;
}

.itemstyle {
	background: #EFF7FA;
	height: 20px;
	font-size: 14;
	font-weight: bold;
	padding: 2px;
	margin: 2px;
}

.plupload_file_name {
	font-size: 14;
	font-weight: bold;
}

.icon {
	background: url(${ctxStatic}/img/icon/download.png) no-repeat;
	width: 10px;
	height: 15px;
	display: inline-block;
	cursor: pointer;
	margin-left: 10px;
}

.icon-tel {
	background-position: 0 0;
}
</style>

<div id="uplaodAttach" class="easyui-panel" title="附件"
	data-options='noheader:true,border:false,fit:true'>



<%-- 	<c:if test="${action=='edit'}"></c:if> --%>
		<div id="uploader"></div>
		<div style='margin: 5px'>
			<span style="color: #FF9762">提示：</span>支持上传图片、PDF、音频、 视频、 压缩包等】
		</div>
	
	 <script  type="text/javascript">
	    $().ready(function(){
	    	
			toplupload();
			$('#load').click(function() {
				toplupload();

			});
	    })
	 </script>




	<div id='divop' style="margin: 0px;">
	
				<a id='del' href='javascript:void(0)' class='easyui-linkbutton i18n'
					onclick='delAttachment()' style='color: #fff'>删除资料</a>
	</div>



<!-- 		<h1>没有查看附件的权限</h1> -->
	<div style="width: 100%; margin: 0px auto;" id='fileContent'></div>
	 <script  type="text/javascript">
	    $().ready(function(){
	    	query();
	    	viewImage();
	    })
	 </script>
</div>
