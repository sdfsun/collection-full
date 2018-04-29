<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" name="id" />
        <div>
            <label>日志标题</label>
            <input value="${model.title }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>操作结果</label>
            <input value="${model.state==1?'成功':'失败' }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>操作起始时间</label>
            <input value='<fmt:formatDate value="${model.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>操作耗时ms</label>
            <input value="${model.timeConsuming }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>用户登录名称</label>
            <input value="${model.loginName }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>用户中文名称</label>
            <input value="${model.userName }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>用户IP地址</label>
            <input value="${model.remoteAddr }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>请求方式</label>
            <input value="${model.httpMethod }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>请求地址</label>
            <input value="${model.requestUrl }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>请求提交数据</label>
            
            <input value='<c:out value="${model.params }"/>' class="easyui-textbox"
            style="width:450px;height:150px;" data-options="multiline:true"/>
        </div>
        <div>
            <label>用户代理信息</label>
            <input value="${model.userAgent }" readonly="readonly" class="easyui-textbox"
            style="width:450px;height:150px;" data-options="multiline:true"/>
        </div>
        <div>
            <label>后台方法名称</label>
            <input value="${model.methodName }" readonly="readonly" class="easyui-textbox"
            style="width:450px;height:150px;" data-options="multiline:true"/>
        </div>
		<div>
		 	<label>异常信息</label>
			<input value="${model.exceptionInfo }" readonly="readonly" class="easyui-textbox" 
			style="width:450px;height:150px;" data-options="multiline:true">
		</div>
		 <div>
            <label>日志创建时间</label>
            <input value='<fmt:formatDate value="${model.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>服务器主机名称</label>
            <input value="${model.serverHostName }" readonly="readonly" style="width:450px;" class="easyui-validatebox textbox"/>
        </div>
        <div>
            <label>服务器主机地址:</label>
            <input value="${model.serverHostAddr }" style="width:450px;" readonly="readonly" class="easyui-validatebox textbox"/>
        </div>
    </form>
</div>