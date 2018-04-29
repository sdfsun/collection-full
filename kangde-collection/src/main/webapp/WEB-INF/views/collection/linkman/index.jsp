<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<style>
.icon
{
  	background: url(${ctxStatic}/img/icon/icon_tel.png) no-repeat;
    width: 18px;
    line-height: 18px;
    display: inline-block;
    cursor:pointer;
    margin-left:10px;
}
.icon-tel
{
 /* background-position: -31px -235px; */
}

</style>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid"></table>
	</div>
</div>



