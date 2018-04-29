<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%-- 引入kindEditor插件 --%>
<link rel="stylesheet" href="${ctxStatic}/js/kindeditor-4.1.10/themes/default/default.css">
<script type="text/javascript" src="${ctxStatic}/js/kindeditor-4.1.10/kindeditor-all-min.js" charset="utf-8"></script>
<jsp:include page="script.jsp"></jsp:include>

<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid"></table>
    </div>
</div>


   