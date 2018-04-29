<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

</script>
<div>
	<form id="view_form" class="dialog-form" method="post" novalidate>
			<TABLE style="BORDER-COLLAPSE: collapse" borderColor=#000000 height=40 cellPadding=1  align=center border=1>

					<c:forEach items="${model}" var="row" varStatus="status">
					<tr>
					<td style="width: 150px; height: 22px">客户联系人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td style="font-weight:bold;width: 130px; height: 22px">${row.contactName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					
					<td style="width: 60px; height: 22px">时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td style="font-weight:bold"><fmt:formatDate value="${row.contactTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					
					<td style="width: 120px; height: 22px">沟通方式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<c:if test="${row.communicateMode==1}">
						<td style="font-weight:bold">电话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</c:if>
						<c:if test="${row.communicateMode==2}">
						<td style="font-weight:bold">来访&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</c:if>
						<c:if test="${row.communicateMode==3}">
						<td style="font-weight:bold">拜访&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</c:if>
					
					
					<td>下次跟进时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td style="font-weight:bold"><fmt:formatDate value="${row.contactTime}" pattern="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					<tr>
					
						<td>洽谈目标(简介)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="7" style="font-weight:bold;width: 150px; height: 20px">${row.negotiationTarget}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						
					</tr>
					
					<tr>
					
						<td>洽谈进度(结果)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="7" style="font-weight:bold;width: 150px; height: 100px">${row.negotiationSchedule}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						
					</tr>
					
					
				</c:forEach>
				</table>	
	</form>
</div>
