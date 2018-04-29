<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function(){
$('#dj').show();
$('#dj1').hide();

$('#cc').combobox({
    editable:false,
    panelWidth:130,
    width:100,
    onSelect:function(record){
    	 var peo = $('#cc').combobox('getValue');
    	 if(peo=="1"){
    		 $('#dj').show();
			 $('#dj1').hide();
    	 }else{
    		 $('#dj1').show();
				$('#dj').hide();
    	 }

    	}
});

});

	
</script>
<div>
	<form id="add_form" class="dialog-form" method="post" novalidate>

		<%-- <input name='id' value="${id }" type='hidden' />  --%>
		<br>
		<!-- <center> -->
		<table>
			<tr>
				<td>
					<select  id ="cc" class="easyui-combobox">   
						<option id="a" value="1" >案件序列号</option>
						<option id="b" value="2" >档案号</option>
					</select>
				</td>
				<td id="dj"><input class="easyui-textbox" name="caseCode" style="width:150px;" /></td>
				<td id="dj1"><input class="easyui-textbox" name="caseFileNo" style="width:150px;" /></td>
			</tr>
			<tr height="15"></tr>
			<tr>
				<td align="right">确认金额&nbsp;&nbsp;</td>
				<td><input id="paidNum" style="width:150px;" 
					class="easyui-numberbox"
					name="paidNum"
					maxLength="100"
					data-options="required:true,min:0,precision:2"></td>
			</tr>
			<tr>
				<td align="right">还款日期&nbsp;&nbsp;</td>
				<td><input id="paidTime" class="easyui-datebox" 
				style="width:150px;" name="paidTime" 
				data-options="required:true,missingMessage:'请选择还款日期'" /></td>
			</tr>
			<tr>
					<td align="right">备注&nbsp;&nbsp;</td>
					<td colspan="2"><input id="surRemark" name="surRemark" maxLength="255" 
				class="easyui-textbox" style="width:150px;" /></td>
					<td></td>
				</tr>
		
		
		</table>
		<!-- </center> -->
			
	</form>
</div>