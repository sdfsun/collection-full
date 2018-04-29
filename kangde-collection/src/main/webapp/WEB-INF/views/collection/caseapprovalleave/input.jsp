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
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" value="${id }" />

		<table>
			<tr>
				<td>留案时间&nbsp;&nbsp;<input type="text" id="stayPeriod" name="stayPeriod"
					style="width: 150px;" maxLength="255" class="easyui-datebox"/></td>
			</tr>
			<tr></tr>
			<tr>
				<td><div style="text-align: center;">
						<input id="approveOpinion" name="approveOpinion"
							class="easyui-textbox"
							data-options="required:true,missingMessage:'请输入审批意见.',validType:'maxLength[500]',multiline:true"
							style="width: 400px; height: 120px" value="${approveOpinion }" />
					</div></td>
			</tr>
		</table>


	</form>
</div>
