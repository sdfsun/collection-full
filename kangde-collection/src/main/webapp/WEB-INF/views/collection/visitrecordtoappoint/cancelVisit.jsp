<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator : function(value, param) {
				return value.length <= param[0];
			},
			message : '输入文字数量不可多于500！'
		}
	});
</script>
<div>
	<form id="_form" class="dialog-form" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<br/><br/>
		<div>
						<input id="approveOpinion" name="approveOpinion"
							class="easyui-textbox"
							data-options="required:true,missingMessage:'请输入撤销意见.',validType:'maxLength[500]',multiline:true"
							style="width: 400px; height: 120px" value="${approveOpinion }" />
		</div>


	</form>
</div>
