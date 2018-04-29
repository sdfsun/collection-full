<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" id="id" name="id" />
        <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version" />
        <!-- restful -->
        <input type="hidden" name="_method" value="post"/>
        <div>
            <label>上级资源</label>
            <input id="parentId" name="parentId"/>
        </div>
        <div>
            <label>资源类型</label>
            <k:dictionary constName="RESOURCE_TYPE" name="type" required="true" value="0"/>
        </div>
        <div>
            <label>资源图标</label>
            <input id="iconCls" name="iconCls"
                   data-options="tipPosition:'left',required:true,missingMessage:'请选择资源图标.',url:'${ctxStatic}/js/json/resource.json'" />
        </div>
        <div>
            <label>资源名称</label>
            <input type="text" id="name" name="name"
                   maxLength="30" class="easyui-validatebox textbox"
                   data-options="required:true,missingMessage:'请输入资源名称.',validType:['minLength[1]']" />
        </div>
        <div>
            <label>资源编码</label>
            <input type="text" id="code" name="code"
                   maxLength="60" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[1]']" />
            <%--提示小图标--%>
            <span class="tree-icon tree-file easyui-icon-tip easyui-tooltip"
                  title="资源识别的唯一标识;主要用于[功能]类型的资源能够根据编码进行权限控制." ></span>
        </div>
        <div>
            <label style="vertical-align: top;">链接地址:</label>
            <input name="url" class="easyui-textbox" maxLength="255" data-options="multiline:true" style="height:75px;">
        	 <%--提示小图标--%>
            <span class="tree-icon tree-file easyui-icon-tip easyui-tooltip"
                  title="菜单类型的链接地址"></span>
        </div>
        <div>
            <label>排序</label>
            <input type="text" id="orderNo" name="orderNo" class="easyui-numberspinner"
                   data-options="min:1,max:99999999,size:9,maxlength:9" />
        </div>
        <div>
            <label>状态</label>
                <input type="radio" name="status" value="1" checked="checked" /> 启用
                <input type="radio" name="status" value="0" /> 停用
        </div>
    </form>
</div>