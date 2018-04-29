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
<div></div>
		<div>
			<label>委托方</label>
			<input id="entrustId" name="entrustId" required="required"/>
        </div>
		<div>
			<label>案源地</label>
			<!-- <input id="search_entrustId" name="id"/> -->
			<input name="name" type="text" style="width:150px;" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入案源地.',validType:['minLength[1]','legalInput']"> 
                   &nbsp;&nbsp;
        </div>
        <div>
			<label>简码</label> <input name="code" type="text" style="width:150px;" class="easyui-validatebox textbox"
                   maxLength="2" data-options="required:true,missingMessage:'请输入案源地简码.',validType:['minLength[2]','maxLength[2]','legalInput']">
		</div>
	
		
	</form>
</div>