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
		<br>
		<div>
			<label>区域名称 </label> <input name="name" type="text" class="easyui-validatebox textbox"
                   maxLength="100"  data-options="required:true,missingMessage:'请输入区域名称.',validType:['minLength[1]','legalInput']" style="width: 150px; height: 20px; ">
		</div>
		<div>
			<label>系统状态 </label> <k:dictionary constName="CASE_STATUS_SYSTEN" name="status" required="true" width="150"/>
		</div>
		
	</form>
</div>