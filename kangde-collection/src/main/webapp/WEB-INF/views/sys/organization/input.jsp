<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
    <form id="_form" class="dialog-form" method="post">
        <input type="hidden" id="id" name="id" />
        <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version" />
        <!-- restful -->
		<c:if test="${!empty id }">
        <input type="hidden" name="_method" value="PUT"/>
		</c:if>
        <div>
            <label>上级机构</label>
            <input id="parentId" name="parentId"/>
        </div>
        <div>
            <label>机构类型</label>
            <input id="orgType" name="type"/>
            <%-- <k:dictionary constName="ORG_TYPE" name="type" required="true" value="1"/> --%>
        </div>
        <div>
            <label>负责人</label>
            <input id="principalId" name="principalId"/>
        </div>
        <div>
            <label>机构名称</label>
            <input type="text" id="name" name="name"
                   maxLength="20" class="easyui-validatebox textbox"
                   data-options="required:true,missingMessage:'请输入机构名称.',validType:['minLength[1]']" />
        </div>
        <div id="areaDiv" style="display: none;">
            <label>业务覆盖区域</label>
            <input type="text" id="areaIds" name="areaIds"/>
        </div>
        <div>
            <label>地址</label>
            <input type="text" id="address" name="address"
                   maxLength="255" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[1]']" />
        </div>
        <div>
            <label>电话号码 </label>
            <input type="text" id="phone" name="phone"
                   maxLength="255" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[5]']" />
        </div>
        <div>
            <label>邮政编码</label>
            <input type="text" id="postCode" name="postCode"
                   maxLength="255" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[5]']" />
        </div>
        <div>
            <label>传真号</label>
            <input type="text" id="fax" name="fax"
                   maxLength="255" class="easyui-validatebox textbox"
                   data-options="validType:['minLength[5]']" />
        </div>
        <div>
            <label>排序</label>
            <input type="text" id="orderNo" name="orderNo" class="easyui-numberspinner"
                   data-options="min:1,max:99999999,size:9,maxlength:9" />
        </div>
    </form>
</div>