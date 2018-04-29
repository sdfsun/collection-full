<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<style>
.easyui-linkbutton {
	background-color: #d9e9ef;
	text-color:red;
	color:black;
}
#filediv  .l-btn {
	color: #444;
	background-repeat: repeat-x;
	background: none;
	background-color: #2987cb;
	background-repeat: repeat-x;
	color:white;
}
</style>
<div>
	<form id="_form" class="dialog-form" method="post"
		<c:if test="${empty id }">enctype='multipart/form-data'</c:if>
		novalidate>

		<input type="hidden" name="id" />
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<c:if test="${!empty id }">
			<input type="hidden" name="_method" value="PUT" />
		</c:if>
		<table border="0" style="align:center;">
		<tr height="10"></tr>
			<tr >
				<td width="80px" align="right">案件类型&nbsp; </td>
				<td width="180px"><k:dictionary constName="CASE_TYPE" name="typeId" required="true" width="150"/></td>
				<td width="96px" align="right">委托方</td>
				<td width="180px">&nbsp;<input id="entrustId"
					class="easyui-combobox" name="entrustId" required="required" />
				</td>
			</tr>
			<tr>
				<td align="right">委案日期&nbsp; </td>
				<td><input type="text" id="begin" name="beginDate"
					style="width: 150px;" maxLength="255" class="easyui-datebox"
					placeholder="请输入委托开始日期..."
					data-options="editable:false,required:true,missingMessage:'请输入委托开始日期...',validType:['minLength[1]']" />
				</td>
				<td align="right">目标回款率</td><td>&nbsp;<input type="text" id="target" name="target" maxLength="255"
					class="easyui-numberbox" placeholder="请输入目标回款率..."
					style="width: 150px;"
					data-options="min:0,max:100,missingMessage:'请输入目标回款率...'" />%

				</td>

			</tr>
			<tr>
				<td align="right">退案日期&nbsp; </td>
				<td><input type="text" id="end" name="endDate"
					style="width: 150px;" maxLength="255" class="easyui-datebox"
					placeholder="请输入委托截止日期..."
					data-options="editable:false,required:true,missingMessage:'请输入委托截止日期...',validType:['minLength[1]']" />
				</td>
			<!-- 	<td >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业绩率&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="achieve" name="achieve" maxLength="255"
					class="easyui-numberbox" placeholder="请输入业绩率..."
					style="width: 150px;"
					data-options="min:0,max:100,missingMessage:'请输入业绩率...'" />%

				</td> -->
				<td align="right">批次号</td><td>&nbsp;<input id="batchCode" name="batchCode" type="text"
					class="easyui-textbox" maxLength="150" style="width: 150px;"
					data-options="required:true,missingMessage:'请输入批次号',validType:'maxLength[32]'">
				</td>
			</tr>
			<tr>
			
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手次&nbsp; </td>
				<td>
				<k:dictionary constName="HANDLE" id="handle" name="handle" width="150"/></td>
				
					<c:if test="${empty id }">
				
					<td align="right">模板类型</td><td><input type="hidden" name="caseBatchId" value="${caseBatchId }" />
					&nbsp;<k:dictionary constName="CASE_TEMP_TYPE"
							name="templateType" required="true" value="1" width="150" /></td>
							

			

				<tr>
					<td align="right">导入文件&nbsp; </td>
					<td id="filediv" colspan="3"><input id='excelFile' name="excelFile"
						class="easyui-filebox"
						data-options="buttonText:'选择案件Excel文件',required:true"
						style="width: 440px;text-color:red;"></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><span style="width:100px;">①导入前<span style="color: #ff6600;">请检查</span>excel表格内数值格式正确，如果格式不正确将无法成功导入<br>&nbsp;&nbsp;&nbsp;数据
						</br> ②已导入案件重新导入时<span style="color: #ff6600;">请保证</span>excel内案件顺序与第一次上传时一致，<span
						style="color: #ff6600;">不要</span> </br>
						&nbsp;&nbsp;&nbsp;删除原excel内案件，否则会导致数据混乱或导入失败。 </br> ③如需要新新增案件，请在已导入表格的<span
						style="color: #ff6600;">最后一行</span>开始新增,并将已导入案件 </br>
						&nbsp;&nbsp;&nbsp;的个案序列号清空。
					</span>
					</td>
				</tr>
			</c:if>
			</tr>
		

			<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp; </td>
				<td colspan="3"><input id="remark" name="remark"
					class="easyui-textbox"
					data-options="missingMessage:'请输入审批意见.',validType:'maxLength[500]',multiline:true"
					style="width: 441px; height: 60px" value="${remark }" /></td>
			</tr>
			<c:if test="${empty id }">
				<tr>
					<td align="right">模板下载&nbsp; </td>
					<td colspan="3"><a class="easyui-linkbutton"
						href="http://${ip }/P2PCaseTemplate.xlsx"
						style="color: blue; text-decoration: underline;"><span style="color:black;">P2P模板下载</span></a>&nbsp;
						&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton"
						href="http://${ip }/BankCaseTemplate.xlsx"
						style="color: blue; text-decoration: underline;"><span style="color:black;">银行模板下载</span></a>
						&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton"
						href="http://${ip }/CarLoanCaseTemplate.xlsx"
						style="color: blue; text-decoration: underline;"><span style="color:black;">车贷模板下载</span></a></td>
				</tr>
			</c:if>
		</table>

		<c:if test="${!empty id }">
			<div style="display: none">
				<div>
					<label>模板类型 </label>
					<k:dictionary constName="CASE_TEMP_TYPE" name="type"
						required="true" value="1" />
				</div>
				<div>
					<input id='excelFile' name="excelFile" class="easyui-filebox"
						data-options="buttonText:'选择案件Excel文件',required:true"
						style="width: 500px">
				</div>
				<div>
					①导入前<span style="color: #ff6600;">请检查</span>excel表格内数值格式正确，如果格式不正确将无法成功导入数据。
					②已导入案件重新导入时<span style="color: #ff6600;">请保证</span>excel内案件顺序与第一次上传时一致，<span
						style="color: #ff6600;">不要删除</span>原excel内案件，否则会导致数据混乱或导入失败。
					③如需要新新增案件，请在已导入表格的<span style="color: #ff6600;">最后一行</span>开始新增，并将已导入案件的个案序列号清空。
				</div>
				<!-- </form> -->
			</div>
		</c:if>

		<c:if test="${!empty id }">
			<div style="display: none">
				模板下载 &nbsp;&nbsp; <a class="easyui-linkbutton"
					href="http://${ip }/P2PCaseTemplate.xlsx" >P2P模板下载</a>&nbsp;
				&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton"
					href="http://${ip }/BankCaseTemplate.xlsx">银行模板下载</a>
			</div>
		</c:if>
	</form>
</div>