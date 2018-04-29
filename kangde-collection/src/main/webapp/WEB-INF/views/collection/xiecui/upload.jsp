<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
.easyui-linkbutton {
	background-color: #d9e9ef;
	text-color:red;
	color:black;
}
#filediv  .l-btn {
	color: #444;
	background-repeat: repeat-x;
	background: none;
	background-color: #2987cb;
	background-repeat: repeat-x;
	color:white;
}
</style>
<script type="text/javascript">
</script>
<div>
	<form id="upload_form" class="dialog-form" method="post" enctype="multipart/form-data" novalidate>
        <br>
		<div id="filediv">
			<input id="excelFile" name="excelFile"  class="easyui-filebox" data-options="buttonText:'选择案件Excel文件',required:true" style="width:600px">
		</div>
	</form>
</div>