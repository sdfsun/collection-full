<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" id="id" name="id" />
		<c:if test="${!empty id }">
        <input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<div></div>
        <div>
            <label>上级职位 </label>
            <input id="parentId" name="parentId"/>
        </div>
        <div>
            <label>职位类型 </label>
            <k:dictionary constName="POSITION_TYPE" name="type" selectType="select" required="true"/>
        </div>
        <div>
            <label>职位名称 </label>
            <input type="text" id="name" name="name"
                   maxLength="20" class="easyui-validatebox textbox"
                   data-options="required:true,missingMessage:'请输入职位名称.',validType:['minLength[1]']" />
        </div>
        <div>
            <label>职位描述 </label>
            <input type="text" name="description"
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