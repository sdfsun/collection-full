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
		<div>
			<label>职位名称:</label> <input name="name" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入职位名称.',validType:['minLength[1]','legalInput']">
		</div>
		<!-- 
		<div>
			<label>上级id:</label> <input id="parentId" name="parentId" type="text"
				class="easyui-validatebox textbox" maxLength="100"
				data-options="required:false,missingMessage:'请上级id.',validType:['minLength[1]','legalInput']">
		</div>
		 -->
		
		<div>
			<label>职位等级:</label> <select id="levelId" name="levelId" 
			 class="easyui-combobox" maxLength="100"  style="width: 200px;">
				<option value="0">请选择</option>
				<option value="1">1级别</option>
				<option value="2">2级别</option>
				<option value="3">3级别</option>
				<option value="4">4级别</option>
				<option value="5">5级别</option>
			</select>
		</div>
		<div>
			<label>描述 :</label> <input id="description" name="description"
				type="text" class="easyui-validatebox textbox" maxLength="100"
				data-options="required:false,missingMessage:'请输描述.',validType:['minLength[1]','legalInput']">
		</div>
	</form>
</div>