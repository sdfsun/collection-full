<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" />
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<input type="hidden" name="_method" value="post" />
			<input type="hidden" name="provinceId" value="${provinceId}" />
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省份名称&nbsp;&nbsp;&nbsp;<input name=provinceName type="text" class="easyui-validatebox textbox"
                   maxLength="100"  data-options="required:true,missingMessage:'请输入省份名称.',validType:['minLength[1]','legalInput']" style="width: 150px; height: 20px; ">
		<span style="color:red">*</span>
		</div>
	</form>
</div>