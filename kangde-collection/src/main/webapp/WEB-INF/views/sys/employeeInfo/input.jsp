<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div>
    <form id="_form" class="dialog-form" method="post" novalidate>
        <input type="hidden"  name="id" />
        <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version" />
        <!-- Restful 请求 -->
        <c:if test="${!empty id }">
        	<input type="hidden" name="_method" value="PUT" />
        </c:if>
        
        <table>
        <tr height="10"></tr>
		
			 <tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;账号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="loginName" name="loginName" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" data-options="required:true,missingMessage:'请输入账号.',validType:['minLength[1]','legalInput']"></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;初始密码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>	<input id="password" name="password" type="text" class="easyui-textbox" style="width:150px;" 
		                  maxLength="32" data-options="required:true,missingMessage:'请输入密码...',validType:['minLength[1]']"></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="userName" name="userName" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" data-options="required:true,missingMessage:'请输入员工姓名.'"></td>
			</tr> 
		
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;性别 &nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td> <input type="radio" name="sex" checked="checked" value="1"/>男
           		<input type="radio" name="sex" value="0"/>女</td>
				<td align="right"><label>&nbsp;&nbsp;&nbsp;&nbsp;联系方式&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
				<td><input id="contactMode" name="contactMode" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" /></td>
				<td align="right"><label>&nbsp;&nbsp;&nbsp;&nbsp;入职日期&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
				<td><input class="easyui-datebox" 
					style="width:150px;" name="joinTime" 
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;坐席用户&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="ccLogin" name="ccLogin" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" /></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;坐席密码&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="ccPwd" name="ccPwd" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" /></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;服务地址&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="ccServer" name="ccServer" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" /></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;坐席号&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="ccPhone" name="ccPhone" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;所属机构&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="orgId" name="orgId"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;岗位&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="positionId" name="positionId"/></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;附加机构&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" id="attachOrgId" name="attachOrgId"/></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;关联角色&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="user_role_form-roleIds" name="roleIds" /></td>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input id="attachEntrustId" name="attachEntrustId" /></td>
			</tr>
		</table>
		<!-- ////////////////////////////////////////////////////////////////////////////
        <div>
        	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号 </label>
        	<input id="loginName" name="loginName" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" data-options="required:true,missingMessage:'请输入账号.',validType:['minLength[1]','legalInput']">
		       	<label>初始密码 </label>
		       	<input id="password" name="password" type="text" class="easyui-textbox" style="width:150px;" 
		                  maxLength="32" data-options="required:true,missingMessage:'请输入密码...',validType:['minLength[1]']">
        	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名 </label>
        	<input id="userName" name="userName" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" data-options="required:true,missingMessage:'请输入员工姓名.'">
           </div>
	    <div>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别 </label>
            <input type="radio" name="sex" checked="checked" value="1"/>男
            <input type="radio" name="sex" value="0"/>女
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系方式 </label>
        	<input id="contactMode" name="contactMode" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" />
        
        	<label>坐席用户 </label>
        	<input id="ccLogin" name="ccLogin" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" />
        </div>
	    <div>
        	<label>坐席密码 </label>
        	<input id="ccPwd" name="ccPwd" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" />
       
        	<label>服务地址 </label>
        	<input id="ccServer" name="ccServer" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32" />
      
        	<label>&nbsp;&nbsp;&nbsp;&nbsp;坐席号 </label>
        	<input id="ccPhone" name="ccPhone" type="text" class="easyui-textbox" style="width:150px;" 
                   maxLength="32"/>
       </div>
	    <div>
            <label>所属机构 </label>
            <input type="text" id="orgId" name="orgId"/>
        
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;岗位 </label>
            <input type="text" id="positionId" name="positionId"/>
       
            <label>附加机构 </label>
            <input type="text" id="attachOrgId" name="attachOrgId"/>
        </div>
        <div>
			<label >关联角色 </label>
			<input id="user_role_form-roleIds" name="roleIds" />
		
			<label >委托方</label>
			<input id="attachEntrustId" name="attachEntrustId" />
		
			<label >入职日期</label>
			<input class="easyui-datebox" 
					style="width:150px;" name="joinTime" 
					data-options="required:false" />
		</div>
 -->
    </form>
    
</div>