<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
    maxLength: {    
        validator: function(value, param){    
            return value.length <= param[0];    
        },    
        message: '输入文字数量不可多于500！'
    }    
});
</script>
<div style='margin-top:30px;'>
	<form id="change_state_form" class="dialog-form" method="post" novalidate>
	     <div>
            <label id="numDisplay" style="color: blue;width: 200px;text-align: left;"></label>
            <input name="approvalOpinion" class="easyui-textbox" data-options="required:true,missingMessage:'请输入意见.',validType:'maxLength[500]',multiline:true"
            style="width: 400px; height: 120px">
        </div>
	</form>
</div>