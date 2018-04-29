<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="region.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" value="${id }"/>
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<c:if test="${empty id }">
		<input type="hidden" name="_method" value="post" />
		</c:if>
		<c:if test="${!empty id }">
		<input type="hidden" name="_method" value="PUT" />
		</c:if>
<%-- 	 <c:if test="${!empty id }"> --%>
	<table border="0">
	<tr height="10"></tr>
	<tr>
	        <c:if test="${!empty id }">
				<td align="right">委托方</td>
				<td><input id="update_entrustId" value="${entrustId }" name="entrustId" disabled="disabled" required="required"/></td>
			</c:if>
	        <c:if test="${empty id }">
				<td align="right">委托方</td>
				<td><input id="entrustId" name="entrustId" required="required"/></td>
			</c:if>
			
			<c:if test="${!empty id }">
				<td align="right">案源地</td>
				<td><input id="caseSourceId_update" disabled="disabled" name="caseSourceId" style="width:150px;" required="required"/></td> 
			</c:if>
			<c:if test="${empty id }">
				<td align="right">案源地</td>
				<td><input id="caseSourceId" name="caseSourceId" style="width:150px;" required="required"/></td> 
			</c:if>
			<c:if test="${!empty id }">
				<td align="right">案件类型</td>
				<td><input id="update_caseTypeId" name="caseTypeId" disabled="disabled" required="true" style="width:150px;"/></td>
			</c:if>
			<c:if test="${empty id }">
				<td align="right">案件类型</td>
				<td><input id="caseTypeId" name="caseTypeId" required="true" style="width:150px;"/></td>
			</c:if>
			<input type="hidden" id="caseTypeId1" name="caseTypeName" style="width:150px;"/>
		</tr>
		 <tr>
			 <c:if test="${!empty id }">
				<td align="right">手次</td>
				<td><input id="update_handle" name="handle" disabled="disabled" style="width:150px;"/></td>
			</c:if>
			 <c:if test="${empty id }">
				<td align="right">手次</td>
				<td><input id="handle" name="handle" style="width:150px;"/></td>
			</c:if>
			<input type="hidden" id="handle1" name="handleName" style="width:150px;"/>
		<td align="right">全简码</td>
			<td><input id="code" name="code" type="text"  readonly="readonly" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"
                   data-options="required:true,missingMessage:'',
                   validType:['minLength[1]','legalInput']"></td>
			
			<td align="right">委托方简称</td>
			<td><input id="entrustAbbrevia" name="entrustAbbrevia" class="easyui-textbox"
                   maxLength="100" style="width:150px;" data-options="required:true"></td>
		</tr>	

		<tr>
			<td align="right">产品类型 主体</td>
			<td><k:dictionary constName="MAIN" id="productCategoryId" 
			name="productCategoryId" width="150" required="true"/></td>
			
			<td align="right">担保方式</td>
			<td><k:dictionary constName="TEE_WAY" id="guaranteeWayId" 
			name="guaranteeWayId" width="150" required="true"/></td>
			
			<td align="right">用途</td>
			<td><k:dictionary constName="PURPOSE" id="purposeId" 
			name="purposeId" width="150" required="true"/></td>
		</tr>
		
		<tr>
			<td align="right">费率</td>
			<td><input name="rate" type="text" class="easyui-numberbox"
                   maxLength="100" style="width:150px;" 
                   data-options="min:0,precision:2"></td>
			
			<td align="right">营业员</td>
			<td><input id="staff" name="staff" type="text" class="easyui-textbox"
                   maxLength="100" style="width:150px;" ></td>
			
			<td align="right">合同周期</td>
			<td><input name="contractCycle" type="text" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"
                   data-options="missingMessage:'',
                   validType:['minLength[1]','legalInput']"></td>
		</tr>
		<tr>
			<td align="right">服务项目</td>
			<td><k:dictionary constName="SER_PRO" id="serviceProject" name="serviceProject" width="150"/></td>
			<td align="right">客户类型</td>
			<td><k:dictionary constName="CUSTYPE" id="cusType" name="cusType" width="150"/></td>
			<td align="right">合同签约日期</td>
			<td><input class="easyui-datebox" 
					style="width:150px;" name="contractAwardDate" 
					data-options="required:false" /></td>
			
		</tr>
		<tr>
			<td align="right">地址 省</td>
			<td><input id="areaId1" name="areaId1" type="text"
					class="easyui-validatebox textbox" style="width: 150px;"
					placeholder='省' /> </td>
			
			<td align="right">市</td>
			<td><input id="areaId2" name="areaId2" type="text"
					style="width: 150px;" placeholder='市' /></td>
			
			<td align="right">县</td>
			<td><input id="areaId3"
					name="areaId3" type="text" style="width: 150px;" placeholder='县' /></td>
		</tr>
		<tr>
			<td align="right">详细地址</td>
			<td colspan="3"><input id='address' name="address" type="text"
					class="easyui-textbox" style="width: 360px;"
					data-options="missingMessage:'请输入地址.',validType:['minLength[1]']"></td>
			<td align="right">客服总窗</td>
			<td><input id="customServiceTotal" name="customServiceTotal" type="text" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"></td> 
		</tr> 
	
	</table>
<%-- 	</c:if> --%>
	<%--  <c:if test="${empty id }">
	<table>
	<tr height="10"></tr>
	<tr>
	        <c:if test="${!empty id }">
				<td align="right">委托方</td>
				<td><input id="update_entrustId" value="${entrustId }" name="entrustId" disabled="disabled" required="required"/></td>
			</c:if>
	        <c:if test="${empty id }">
				<td align="right">委托方</td>
				<td><input id="entrustId" name="entrustId" required="required"/></td>
			</c:if>
			
			<c:if test="${!empty id }">
				<td align="right">案源地</td>
				<td><input id="caseSourceId_update" disabled="disabled" name="caseSourceId" required="required"/></td> 
			</c:if>
			<c:if test="${empty id }">
				<td align="right">案源地</td>
				<td><input id="caseSourceId" name="caseSourceId" required="required"/></td> 
			</c:if>
			<c:if test="${!empty id }">
				<td align="right">案件类型</td>
				<td><input id="update_caseTypeId" name="caseTypeId" disabled="disabled" required="true" style="width:150px;"/></td>
			</c:if>
			<c:if test="${empty id }">
				<td align="right">案件类型</td>
				<td><input id="caseTypeId" name="caseTypeId" required="true" style="width:150px;"/></td>
			</c:if>
			<input type="hidden" id="caseTypeId1" name="caseTypeName" style="width:150px;"/>
		</tr>
		 <tr>
			 <c:if test="${!empty id }">
				<td align="right">手次</td>
				<td><input id="update_handle" name="handle" disabled="disabled" style="width:150px;"/></td>
			</c:if>
			 <c:if test="${empty id }">
				<td align="right">手次</td>
				<td><input id="handle" name="handle" style="width:150px;"/></td>
			</c:if>
			<input type="hidden" id="handle1" name="handleName" style="width:150px;"/>
		<td align="right">全简码</td>
			<td><input id="code" name="code" type="text" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"
                   data-options="required:true,missingMessage:'请输入全简码.',
                   validType:['minLength[1]','legalInput']"></td>
			
			<td align="right">客户类型</td>
			<td><k:dictionary constName="CUSTYPE" id="cusType" name="cusType" width="150"/></td>
		</tr>	
		
		<tr>
			<td align="right">费率</td>
			<td><input name="rate" type="text" class="easyui-numberbox"
                   maxLength="100" style="width:150px;" 
                   data-options="min:0,precision:2"></td>
			
			<td align="right">营业员</td>
			<td><input id="staff" name="staff" type="text" class="easyui-textbox"
                   maxLength="100" style="width:150px;" ></td>
			
			<td align="right">合同周期</td>
			<td><input name="contractCycle" type="text" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"
                   data-options="missingMessage:'',
                   validType:['minLength[1]','legalInput']"></td>
		</tr>
		<tr>
			<td align="right">服务项目</td>
			<td><k:dictionary constName="SER_PRO" id="serviceProject" name="serviceProject" width="150"/></td>
			
			<td align="right">客服总窗</td>
			<td><input id="customServiceTotal" name="customServiceTotal" type="text" class="easyui-validatebox textbox"
                   maxLength="100" style="width:150px;"></td> 
			
			<td align="right">合同签约日期</td>
			<td><input class="easyui-datebox" 
					style="width:150px;" name="contractAwardDate" 
					data-options="required:false" /></td>
			
		</tr>
		<tr>
			<td align="right">地址 省</td>
			<td><input id="areaId1" name="areaId1" type="text"
					class="easyui-validatebox textbox" style="width: 150px;"
					placeholder='省' /> </td>
			
			<td align="right">市</td>
			<td><input id="areaId2" name="areaId2" type="text"
					style="width: 150px;" placeholder='市' /></td>
			
			<td align="right">县</td>
			<td><input id="areaId3"
					name="areaId3" type="text" style="width: 150px;" placeholder='县' /></td>
		</tr>
		<tr>
			<td align="right">详细地址</td>
			<td colspan="5"><input id='address' name="address" type="text"
					class="easyui-textbox" style="width: 600px;"
					data-options="missingMessage:'请输入地址.',validType:['minLength[1]']"></td>
		</tr> 
	
	</table>
	</c:if>
	
	
	 --%>
	
	
		
	</form>
</div>