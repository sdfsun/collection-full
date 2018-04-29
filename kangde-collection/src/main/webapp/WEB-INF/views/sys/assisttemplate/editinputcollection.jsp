<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function selectAll(){
	 var checklist = document.getElementsByName ("list");
	   if(document.getElementById("controlAll").checked)
	   {
	   for(var i=0;i<checklist.length;i++)
	   {
	      checklist[i].checked = 1;
	   } 
	 }else{
	  for(var j=0;j<checklist.length;j++)
	  {
	     checklist[j].checked = 0;
	  }
	 }
	}
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
	<input type="hidden"  name="id" value="${id }"/>
		<table>
			<table>
		<br/>
			<tr>
				<label>&nbsp;&nbsp;催记模板名称  </label> <input name="name" type="text" value="${name}"
					class="easyui-validatebox textbox" maxLength="100"
						data-options="required:true,missingMessage:'请输入催记模板名称',validType:'maxLength[32]'">
			
			</tr>
			<br/>
			<br/>
				&nbsp;<input onclick="selectAll()" type="checkbox"   name="controlAll" style="controlAll" id="controlAll"/>全选 
			<tr>
				<c:forEach items="${column}" var="row" varStatus="status">
					<td><input value="${row.id}" id="${status.count}" name="list" type="checkbox" 
						<c:forEach items="${caseColumnsOk }" var="temp">
						<c:if test="${row.id eq temp.id }">
							checked="checked"
						</c:if>
					</c:forEach>
					/></td>
					<td><label for="${status.count}">${row.title}</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<c:if test="${status.count%4==0}">
			</tr>
			<tr>
				</c:if>
				</c:forEach>
			</tr>
		</table>
	</form>
</div>