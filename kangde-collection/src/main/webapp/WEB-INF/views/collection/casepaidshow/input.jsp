<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
#_detail_form{
	border-collapse: collapse;
	cellspacing: 0;
	cellpadding: 0;
	border: 0;
	border-spacing: 0px;
	cellspacing: 0;
	width: 100%;
}

#_detail_form tr td {
	padding: 0px;
	margin: 0px;
	width: 150px;
	border: 1.5px solid #E6E6E6;
	text-align: center;
}

#_detail_form tr td input {
	display: block;
	height: 100%;
	width: 150px;
	padding: 0px;
	margin: 0px;
	text-align: center;
	text-overflow: ellipsis;
	border:0;
}
#_detail_form .textbox {
	border: 0px;
}
</style>
<script type="text/javascript">

$().ready(function(){
	alert(11);
	
	$('#outDerate').numberbox({
	    onChange:function(newValue,oldValue){
	    	if(newValue==''){
	    		$(this).numberbox("setValue",'0.00');
	    	}
	    }
	});
	$('#inDerate').numberbox({
	    onChange:function(newValue,oldValue){
	    	if(newValue==''){
	    		$(this).numberbox("setValue",'0.00');
	    	}
	    }
	});
});


</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input id="id" type="hidden" name="id" />
		<input type="hidden" name="caseId" value="${caseId }"/>
		<input type="hidden" name="state"/>
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
        <c:if test="${!empty id }">
			<%-- <input type="test" name="paidNum" value="${paidNum }"/> --%>
        	<input type="hidden" name="_method" value="PUT" />
        </c:if>
        <br>
		<div>
			<table id="_detail_form" style="align:center;">
				<tr align="center" height="23" bgcolor="#F5F5F5">
					<td style="font-weight:bold;">CP金额  </td>
					<td style="font-weight:bold;">委案金额  </td>
					<td style="font-weight:bold;">剩余还款  </td>
				</tr>
				<tr>
					<td><input id="cpMoney" style="width:150px;align:center;" 
					class="easyui-numberbox" readonly="true" 
					name="cpMoney" value="${cpMoney}"
					maxLength="100"
					data-options="required:false,min:0,precision:2"></td>
					
					<td><input id="caseMoney" style="width:150px;align:center;" class="easyui-numberbox" readonly="true" name="caseMoney" value="${caseMoney}"
					maxLength="100"
					data-options="required:false,min:0,precision:2"></td>
					
					<td><input id="surplusMoney" style="border:0; width:150px;align:center;" class="easyui-numberbox" readonly="true" name="surplusMoney" value="${surplusMoney}"
					maxLength="100"
					data-options="required:false,min:0,precision:2"></td>
					<!-- <td width="80" height="50">确认还款 </td>
					<td height="50"><input type="radio" class="aaa" id='a1' name="a" value="1"/>全额还款
            			<input type="radio" class="aaa" id='a2' name="a" value="0"/>非全额还款</td> -->
				</tr>
				</table>
				<table style="align:center;" border="0">
				<tr height="20"><td></td><td></td><td></td></tr>
				 <tr>
					<td width="100"></td>
					<td align="right">还款日期&nbsp;&nbsp;</td>
					<td colspan="2"><input class="easyui-datebox" 
					style="width:150px;" name="paidTime6" 
					data-options="required:true,missingMessage:'请选择还款日期'" /></td>
					<td></td>
				</tr> 
				<tr height="3"></tr>
				
				<tr>
					<td width="100"></td>
					<td align="right">确认金额&nbsp;&nbsp;</td>
					<td colspan="2"><input id="paidNum2" class="easyui-numberbox" 
					style="width:150px;" name="paidNum" 
					data-options="required:true,missingMessage:'请输入 确认金额',min:0,precision:2,validType:['minLength[1]']" /></td>
					<td></td>
				</tr>
				<tr height="3"></tr>
				<tr>
					<td width="100"></td>
					<td align="right">备注&nbsp;&nbsp;</td>
					<td colspan="2"><input id="surRemark" name="surRemark" maxLength="255" 
				class="easyui-textbox" style="width:150px;" /></td>
					<td></td>
				</tr>
				<tr hidden="true">
					<td align="right">外部减免金额</td>
					<td colspan="2"><input hidden="true" id="outDerate" class="easyui-numberbox"
					style="width:150px;" name="outDerate" value="0" onblur="check()"
					data-options="min:0,precision:2,validType:['minLength[1]']" /></td>
				</tr>
				<tr hidden="true">
					<td align="right">内部减免金额</td>
					<td colspan="2"><input hidden="true" id="inDerate" class="easyui-numberbox" 
					style="width:150px;" name="inDerate" value="0" 
					data-options="min:0,precision:2" /></td>
				</tr>
			</table>
		</div>
		<br>
		<%-- <div align="center"> <label>确认金额</label> <input id="paidNum" type="text" name="paidNum" value="${paidNum}"/></div> --%>
		<!-- <div><br/>
			<label>确认还款</label><input type="radio" class="aaa" id='a1' name="a" value="1"/>
           <input type="radio" class="aaa" id='a2' name="a" value="0"/>非全额还款 
          </div>  
          
            <div id='a3'>
            
            </div> -->
           
		
<!-- 		<div><br/>
			<label style="color:black;">备注 :</label> 
			<br/><br/>
			<input id="surRemark" name="surRemark" type="text" multiline="true"
				class="easyui-textbox" maxLength="100" style="height:300px; width: 350px;"
				data-options="required:false,missingMessage:'请输入备注',validType:['minLength[1]','legalInput']">
		</div> -->

		

	</form>
</div>