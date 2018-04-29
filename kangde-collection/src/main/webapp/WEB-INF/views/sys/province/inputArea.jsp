<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<div>
	<form id="_formArea" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" />
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<input type="hidden" name="_method" value="post" />
			<input type="hidden" name="areaId" value="${areaId}" />
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县名称&nbsp;&nbsp;&nbsp;<input name=areaName type="text" class="easyui-validatebox textbox"
                   maxLength="100"  data-options="required:true,missingMessage:'请输入县名称.',validType:['minLength[1]','legalInput']" style="width: 150px; height: 20px; ">
		<span style="color:red">*</span>
		</div>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属城市&nbsp;&nbsp;&nbsp;<input id="search_cityId" name="cityId"/>
		</div>
	</form>
</div>