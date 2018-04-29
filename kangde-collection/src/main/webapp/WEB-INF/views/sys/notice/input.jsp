<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<style>
.layout_table{
	margin-top:20px;
}

.layout_table label {
	display: inherit;
	height: 100%;
	padding: 0px;
	margin-left: 20px;
	width:50px;
	text-align: right;
}
</style>
<div>
	<form id="_form" class="dialog-form" method="post">
		<table class='layout_table'>
			<tr>
				<td><label>标题</label></td>
				<td><input id='title' name="title" type="text"
					class="easyui-validatebox easyui-textbox" maxLength="100"
					style="width: 130px;"
					data-options="required:true,missingMessage:'请输入标题.',validType:['minLength[1]','legalInput']"></td>
			</tr>
			<tr>
				<td><label>内容</label></td>
				<td>
				<input id="content" name="content" class="easyui-textbox"
						style="width: 420px; height: 128px;"
						data-options="required:true,multiline:true,missingMessage:'请输入公告内容'" />
				</td>
			</tr>
		</table>
	</form>
</div>
