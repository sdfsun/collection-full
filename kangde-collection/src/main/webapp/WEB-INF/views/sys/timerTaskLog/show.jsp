<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" name="id" />
		<div>
			<input value="${model.exceptionInfo }" readonly="readonly" class="easyui-textbox" 
			style="width:450px;height:340px;" data-options="multiline:true">
		</div>
    </form>
</div>