<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
<div>
	<form id="division_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" id="stepMark" value="1"/>
		<table id="_detail_form" style="align:center;">
		<tr height="20"></tr>
				<tr align="center" height="23" bgcolor="#F5F5F5">
					<td style="font-weight:bold;">案件总量  </td>
					<td style="font-weight:bold;">案件总金额  </td>
					<td style="font-weight:bold;">已分配  </td>
					<td style="font-weight:bold;">未分配  </td>
				</tr>
				<tr>
					<td><strong id="caseTotalCount" style="width:100px;display: inline-block;"></strong></td>
					<td><strong id="caseTotalMoney" style="width:100px;display: inline-block;"></strong> </td>
					<td><strong id="assignedCount" style="width:100px;display: inline-block;"></strong></td>
					<td><strong id="undistributedCount" style="width:100px;display: inline-block;"></strong></td>
				</tr>
		
		</table>
		<table border="0">
		<tr height="10"></tr>
			<tr>
				<td>分配方式  </td>
				<td><input type="radio" name="divisionWay" value="1" checked="checked"/> 按照数量</td>
				<td><input type="radio" name="divisionWay" value="2"/> 按照金额</td>
				<td>&nbsp;&nbsp;<span style="color:#488EC1;font-weight:bold;">|</span>&nbsp;&nbsp;<input type="checkbox" id="hasNotDivision"/> 是否包含已分配？</td>
			</tr>
			<tr height="10"></tr>
		</table>
		<div>
			<!-- <input type="radio" name="divisionWay" value="3"/> 综合分配 -->
			<div id="step1">
			    <ul name="userIds" id="userIds"></ul>
			</div>
			<div id="step2" style="display: none;">
				</br>
				<span>选中人员 </span>
				</br>
			</div>
			<div id="step3" style="display: none;">
				</br>
				</br>
				<table border='1'cellspacing="0" cellpadding="0">
					<tr>
						<th width="90">风控员</th>
						<th width="90">案件数量</th>
						<th width="90">案件金额</th>
						<th width="90">分配比例</th>
					</tr>
				</table>
			</div>
	    </div>
	</form>
</div>