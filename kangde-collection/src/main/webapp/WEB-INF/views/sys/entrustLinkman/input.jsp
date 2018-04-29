<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(function () {
	var url = ctx+'/sys/entrust/combobox?selectType=all';
	var entrustNameValue;
	
	$($('#entrustName')).combobox({
        url:url,
	    multiple:false,//是否可多选
	    editable:true,//是否可编辑
	    width:150,
	    height:22,
	    valueField:'value',
        textField:'text',
        onSelect : function(){
        	
        	 entrustNameValue=$('#entrustName').combobox('getValue');
        	 completeBox(entrustNameValue);
        }/* ,
        onLoadSuccess : function(){
        	
        } */
	});
	
	function completeBox(entrustNameValue){
		var url1 = ctx+'/sys/entrustLinkman/combobox?entrustNameValue='+entrustNameValue;
		$($('#entrustProductId')).combobox({
	        url:url1,
		    multiple:false,//是否可多选
		    editable:false,//是否可编辑
		    width:150,
		    height:22,
		    valueField:'value',
	        textField:'text',
		});
	}
});  
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden"  id="id" name="id" value="${id}"/>
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		
		 <c:if test="${!empty id }">
        	<input type="hidden" name="_method" value="PUT" />
        </c:if>
         <c:if test="${empty id }">
        	<input type="hidden" name="_method" value="POST"/>
        </c:if>

		<table>
		 <c:if test="${empty id }">
			 <tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="entrustName" name="entrustName" class="easyui-combobox"  required="required" style="width: 150px; height: 22px"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;委托方全称&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="entrustProductId" name="entrustProductId" class="easyui-combobox"  required="required" style="width: 150px; height: 22px"/></td>
			</tr> 
		 </c:if>
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;客户联系人&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="name" name="name"  class="easyui-validatebox textbox" data-options="required:true,missingMessage:'请输入客户联系人'" style="width: 150px; height: 22px"/></td>
				<td align="right"><label>&nbsp;&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
				<td><input id="phone" name="phone"  class="easyui-validatebox textbox" style="width: 150px; height: 22px"/></td>
			</tr>
			<tr>
				
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;邮箱&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="email" name="email"  class="easyui-validatebox textbox" style="width: 150px; height: 22px"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;QQ&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="qq" name="qq"  class="easyui-validatebox textbox" style="width: 150px; height: 22px"/></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;微信&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="wechat" name="wechat"  class="easyui-validatebox textbox" value="${wechat}" style="width: 150px; height: 22px"/></td>
			</tr>
		</table>

	</form>
</div>
