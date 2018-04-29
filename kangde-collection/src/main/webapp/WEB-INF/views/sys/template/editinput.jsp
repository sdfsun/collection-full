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
			<div>
				<label>案件模板名称: </label> <input name="name" value="${name}"
					type="text" class="easyui-validatebox textbox" maxLength="100"
					data-options="required:true,missingMessage:'请输入催记模板名称',validType:['minLength[1]','legalInput']">
				<span style="color: red">*</span>
			</div>
			全选：<input onclick="selectAll()" type="checkbox"   name="controlAll" style="controlAll" id="controlAll"/>
			<tr>
				<c:forEach items="${column}" var="row" varStatus="status">
					<td><label for="${status.count}">${row.title}</label></td>
					<td><input value="${row.id}"   id="${status.count}" name="list" type="checkbox" 
						<c:forEach items="${caseColumnsOk }" var="temp">
						<c:if test="${row.id eq temp.id }">
							checked="checked"
						</c:if>
					</c:forEach>
					/></td>
					<c:if test="${status.count%4==0}">
			</tr>
			<tr>
				</c:if>
				</c:forEach>
			</tr>
		</table>
	</form>
</div>