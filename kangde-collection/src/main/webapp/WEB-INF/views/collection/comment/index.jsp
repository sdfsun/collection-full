<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<style>
body{
margin: 0px;
padding: 0px;
}
.datagrid-header, .datagrid-toolbar, .datagrid-pager, .datagrid-footer-inner {
    border-color: none;
    border:0px;
}
.panel-body {
    color: #fff;
}

.datagrid-header td, .datagrid-body td, .datagrid-footer td {
    border-style: none;
}


.datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber {
    word-wrap: break-word;
}

</style>
<jsp:include page="script.jsp"></jsp:include>
<table id="_datagrid"></table>