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
            <label>上级字典 </label>
            <input id="parentId" name="parentId"/>
        </div>
        <div>
            <label>字典名称 </label>
            <input type="text" id="name" name="name"
                   maxLength="100" class="easyui-validatebox textbox"
                   data-options="required:true,validType:['minLength[1]']" />
        </div>
        <div>
            <label>字典键 </label>
            <input type="text" id="dictKey" name="dictKey"
                   maxLength="100" class="easyui-validatebox textbox"
                   data-options="required:true,validType:['minLength[1]']" />
        </div>
        <%-- 不是新增隐藏 --%>
        <c:if test="${!empty id }">
	        <div>
	            <label>字典路径 </label>
	            <input type="text" name="path" maxLength="100" class="easyui-validatebox textbox"
	            readonly="readonly" />
	        </div>
        </c:if>
         <div>
            <label>字典值 </label>
            <input type="text" id="value" name="value"
                   maxLength="100" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[1]']" />
        </div>
        <div>
            <label>备注 </label>
            <input type="text" id="remark" name="remark"
                   maxLength="255" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[1]']" />
        </div>
        <div>
            <label>排序 </label>
            <input type="text" id="orderNo" name="orderNo" class="easyui-numberspinner"
                   data-options="min:1,max:99999999,size:9,maxlength:9" />
        </div>
    </form>
</div>