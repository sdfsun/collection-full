<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">

    <%-- 左边部分 菜单树形 --%>
    <div data-options="region:'west',title:'字典列表',split:false,collapsed:false,border:false"
         style="width: 180px; text-align: left;padding:5px;">
        <ul id="dictionary_tree"></ul>
    </div>
    
    <!-- 中间部分 列表 -->
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
           <!--  <div data-options="region:'center',split:true" style="overflow: hidden;"> -->
                <table id="_treegrid" ></table>
         <!-- </div> -->
        </div>
  <!--   </div> -->
    <%-- 列表右键 --%>
    <div id="_menu" class="easyui-menu" style="width:120px;display: none;">
        <div onclick="showDialogTree();" data-options="iconCls:'eu-icon-xinzeng'">新增</div>
        <div onclick="editTree();" data-options="iconCls:'eu-icon-bianji'">编辑</div>
		<shiro:hasPermission name="dictionary:delete">        
        <div onclick="deltree();" data-options="iconCls:'eu-icon-shanchu'">删除</div>
        </shiro:hasPermission>
    </div>
</div>