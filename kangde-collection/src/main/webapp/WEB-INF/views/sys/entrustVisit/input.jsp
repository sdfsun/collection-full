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
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;</td>
				<td><input id="entrustName" name="entrustName" class="easyui-combobox"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;委托方全称&nbsp;</td>
				<td><input id="entrustProductId" name="entrustProductId" class="easyui-combobox" style="width: 150px; height: 22px" required="required"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;客户联系人 &nbsp;</td>
				<td><input id="contactName" name="contactName" class="easyui-validatebox textbox" style="width: 150px; height: 22px" data-options="required:true,missingMessage:'请输入客户联系人'"/></td>
			</tr> 
		
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;时间 &nbsp;</td>
				<td>
				 <input id="contactTime" name="contactTime"
						style="width: 150px; height: 22px" class="easyui-datebox" placeholder="请输入时间..."
					data-options="editable:false,required:true,missingMessage:'请输入时间...',validType:['minLength[1]']"/></td>
				
				<td align="right">&nbsp;&nbsp;&nbsp;沟通方式 &nbsp;</td>
				<!-- <td><input id="" name="communicateMode" class="easyui-validatebox textbox" style="width: 150px; height: 22px" data-options="required:true,missingMessage:'请输入沟通方式'"/></td> -->
				<td><k:dictionary constName="YYGL_GTFS" id="communicateMode" name="communicateMode" required="true" value="1" width="150" /></td>
				
				<td align="right">&nbsp;&nbsp;&nbsp;下次跟进时间 &nbsp;</td>
				<td><!-- <input id="nextFollowTime" name="nextFollowTime" class="easyui-validatebox textbox"/> -->
			<!-- 	<input id="nextFollowTime" name="nextFollowTime"
						style="width: 150px; height: 22px" class="Wdate textbox" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"/></td> -->
					 <input id="nextFollowTime" name="nextFollowTime" style="width: 150px; height: 22px" class="easyui-datebox"/></td>
		
			</tr>
			<table>
			<tr>
				<td valign="top">洽谈目标 &nbsp;</td>
				<td><input id="negotiationTarget" name="negotiationTarget" class="easyui-textbox"  style="width: 645px;height: 80px" data-options="multiline:true,required:true,missingMessage:'请输入洽谈目标'" /></td>
				
			</tr>
			<tr>
				<td valign="top">洽谈进度&nbsp;</td>
				<td><input id="negotiationSchedule" name="negotiationSchedule" class="easyui-textbox" 
				style="width: 645px; height: 150px"
				data-options="multiline:true,required:true,missingMessage:'请输入洽谈进度'"/></td>
				
			</tr>
			</table>
			 </c:if>
			 <c:if test="${!empty id }">
			  <tr>
				<td align="right">&nbsp;&nbsp;&nbsp;客户联系人 &nbsp;</td>
				<td><input id="contactName" name="contactName" class="easyui-validatebox textbox" style="width: 150px; height: 22px" data-options="required:true,missingMessage:'请输入客户联系人'"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;时间 &nbsp;</td>
				<td><input id="contactTime" name="contactTime"
						style="width: 150px; height: 22px" class="easyui-datebox" placeholder="请输入时间..."
					data-options="editable:false,required:true,missingMessage:'请输入时间...',validType:['minLength[1]']"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;沟通方式 &nbsp;</td>
				<!-- <td><input id="" name="communicateMode" class="easyui-validatebox textbox" style="width: 150px; height: 22px" data-options="required:true,missingMessage:'请输入沟通方式'"/></td> -->
				<td><k:dictionary constName="YYGL_GTFS" id="communicateMode" name="communicateMode" required="true" value="1" width="150" /></td>
			 </tr> 
		
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;下次跟进时间 &nbsp;</td>
				<td><!-- <input id="nextFollowTime" name="nextFollowTime" class="easyui-validatebox textbox"/> -->
			<!-- 	<input id="nextFollowTime" name="nextFollowTime"
						style="width: 150px; height: 22px" class="Wdate textbox" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"/></td> -->
					 <input id="nextFollowTime" name="nextFollowTime" style="width: 150px; height: 22px" class="easyui-datebox"/></td>
				<td></td>		
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<table>
			<tr>
				<td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;洽谈目标 &nbsp;</td>
				<td><input id="negotiationTarget" name="negotiationTarget" class="easyui-textbox"  style="width: 587px;height: 80px" data-options="multiline:true,required:true,missingMessage:'请输入洽谈目标'" /></td>
				
			</tr>
			<tr>
				<td valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;洽谈进度&nbsp;</td>
				<td><input id="negotiationSchedule" name="negotiationSchedule" class="easyui-textbox" 
				style="width: 587px; height: 150px"
				data-options="multiline:true,required:true,missingMessage:'请输入洽谈进度'"/></td>
				
			</tr>
			</table>
			 	
			
       		 </c:if>
			 
		</table>

	</form>
</div>
