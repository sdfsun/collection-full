<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" name="id" />
		<c:if test="${!empty id}">
			<input type="hidden" name="_method" value="PUT" />
		</c:if>
		<div>
			<label>作业名称:</label> 
			<input name="name" class="easyui-validatebox textbox"
                   maxLength="64" data-options="required:true,validType:['minLength[1]','legalInput']">
		</div>
		<div>
			<label>作业类:</label> 
			<input name="jobClass" class="easyui-validatebox textbox"
                   maxLength="255" data-options="required:true,validType:['minLength[1]']">
		</div>
		<div>
			<label>cron表达式:</label> 
			<input name="cronExpression" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,validType:['minLength[1]']">
		</div>
		<div>
			<label>调度发生错误时通知的邮件地址:</label> 
			<input name="errorEmail" class="easyui-textbox" maxLength="255" data-options="multiline:true" style="width:260px;height:75px;">
            <span class="tree-icon tree-file easyui-icon-tip easyui-tooltip"
                  title="支持多个邮件，以英文,进行分割" ></span>
		</div>
		<div>
			<label>作业参数(JSON):</label>
			<input name="params" class="easyui-textbox" maxLength="255" data-options="multiline:true" style="width:260px;height:75px;">
			<span class="tree-icon tree-file easyui-icon-tip easyui-tooltip"
                  title="必须是JSON格式的数据" ></span>
		</div>
		<div>
			<label>作业描述 :</label>
			<input name="description" class="easyui-textbox" maxLength="255" data-options="multiline:true" style="width:260px;height:75px;">
		</div>
		<div>
            <label>状态:</label>
            <label style="text-align: left;width: 60px;">
                <input type="radio" name="state" style="width: 20px;" value="1" checked="checked" /> 启用
            </label>
            <label style="text-align: left;width: 60px;">
                <input type="radio" name="state" style="width: 20px;" value="0" /> 暂停
            </label>
        </div>
    </form>
</div>