<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<div>
	<form id="_formCity" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" />
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<input type="hidden" name="_method" value="post" />
			<input type="hidden" name="cityId" value="${cityId}" />
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;城市名称 &nbsp;&nbsp;&nbsp;<input name=cityName type="text" class="easyui-validatebox textbox"
                   maxLength="100"  data-options="required:true,missingMessage:'请输入区域名称.',validType:['minLength[1]','legalInput']" style="width: 150px; height: 20px; ">
		<span style="color:red">*</span>
		</div>
		<div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属省份 &nbsp;&nbsp;&nbsp;<input id="search_provinceId" name="provinceId"/>
		</div>
	</form>
</div>