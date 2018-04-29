<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>

		<input name='id' value="${id }" type='hidden' /> <input name='caseId'
			value="${caseId }" type='hidden' />
		<div>
			<label style="margin-left: 12px;">CP金额</label> <input name="cpMoney"
				type="text" class="easyui-validatebox easyui-numberbox" maxLength="50"
				style="width: 150px;"
				data-options="required:true,missingMessage:'请输入CP金额.',validType:['intOrFloat'],min:0,precision:2">
		</div>
		<div>
			<label style="vertical-align: top; margin-left: 12px;">CP日期</label>
			<input type="text" name="cpTime" maxLength="255" value="${cpTime }"
				class="easyui-datebox" placeholder="请输入CP日期" style="width: 150px;"
				data-options="editable:false,readonly:true, required:true,missingMessage:'请输入CP日期...',validType:['minLength[1]']" />
		</div>
		<div>
			<label style="vertical-align: top; margin-left: 12px;">备注</label>
			<input type="text" name="surRemark" maxLength="255" 
				class="easyui-textbox" style="width:150px;"
			 />
		</div>
		<div>
	
		 	<label style="vertical-align: top; margin-left: 12px;">凭证</label> 
		 	
			<a id="btn" href="#" class="easyui-linkbutton" onclick="upload('${id}')">上传</a>
			
		</div>
	</form>
</div>