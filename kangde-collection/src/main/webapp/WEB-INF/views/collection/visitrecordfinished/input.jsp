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
	
$.extend($.fn.validatebox.defaults.rules, {    
    maxLength: {    
        validator: function(value, param){    
            return value.length <= param[0];    
        },    
        message: '输入文字数量不可多于32！'   
    }    
});
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<table align="center">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input onclick="selectAll()" type="checkbox"   name="controlAll" style="controlAll" id="controlAll"/>全选 
				<tr>
				<c:forEach items="${column}" var="row" varStatus="status">
					<td><input value="${row.field}" name="list" id="${status.count}" type="checkbox" onclick="setSelectAll();"/></td>
					<td><label for="${status.count}" style='float:left;'>${row.title}</label></td>
					<c:if test="${status.count%4==0}">
			</tr>
			<tr>
				</c:if>
				</c:forEach>
			</tr>
		</table>
	</form>
</div>