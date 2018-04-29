<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	
		<form id="_applyStayform" class="dialog-form" method="post" novalidate>
		<input name='caseId' value="${caseId }" type='hidden' />
		<table>
			<tr>
				<td  valign='top'><label style="text-align: left;">申请原因</label></td>
				<td>
				<input
					name="stayReason" maxLength="100" type="text" class="easyui-textbox"
					multiline="true" style="width: 400px; height: 120px"
					data-options="required:true,missingMessage:'请输入留案申请原因'"></td>
			</tr>
		
		</table>
	</form>
</div>