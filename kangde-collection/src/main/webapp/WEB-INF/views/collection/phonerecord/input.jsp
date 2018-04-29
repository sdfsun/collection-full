
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<style>
table tr td label {
	display: block;
	text-align: right;
}

#savePhoneRecordTable {
	border-collapse: separate;
	border-spacing: 8px;
}


.div-left {
	float: left;
	width: 45%;
	height: 290px;
	margin: 10 0;
	padding:0px;
	margin: -2px 0px;
}

.div-right {
	float: left;
	width: 45.5%;
	height: 290px;
	margin: 10;
	padding-left:10px;
}


.easyui-linkbutton {
	color: #fff;
}


.dialog-form {
	margin: 0;
	padding: 0; 
}

.datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber,
	.datagrid-cell-rownumber {
	word-wrap: break-word;
}

.icon
{
  	background: url(${ctxStatic}/img/icon/icon_tel.png) no-repeat;
    width: 18px;
    line-height: 18px;
    display: inline-block;
    cursor:pointer;
    margin-left:10px;
}
.icon-tel
{
 /* background-position: -31px -235px; */
}


</style>

<jsp:include page="script.jsp"></jsp:include>
	
	<div class='div-left'>
		
		<div style="height:100%;width:100%;border:1px">
			<form id="_form" class="dialog-form" method="post">

			<table id='savePhoneRecordTable'>

				<tr>
					<td><label>电话</label></td>
					<td><input type="hidden" name="id" /> <!-- 用户版本控制字段 version -->
						<input type="hidden" id="version" name="version" /> <!-- Restful 请求 -->
						<input type="hidden" name="_method" value="post" /> 
						<input type="hidden" id='linkmanId' name="linkmanId" /> 
						<input  name="prCat" value='0' type="hidden"/>

 					<input name="contact"
					id='contactId' style="width: 150px; height: 20px;" type="text"
					class="easyui-textbox" maxLength="100"
					data-options="required:true,missingMessage:'请输入电话',validType:['numorhyphen']">						
					<span id="callOut" class="icon icon-tel" onclick="callOut()">&nbsp;&nbsp;</span>
					</td>
					<td><label>时间</label></td>
					<td><input type="text" id='createTime' name="createTime"
						value="${createTime }" class='textbox'
						style="width: 150px; height: 20px;" readonly='readonly' /></td>
				</tr>
				<tr>
					<td><label>PTP金额</label></td>
					<td><input id="ptpMoney" name="ptpMoney" type="text"
						class="easyui-validatebox easyui-numberbox" maxLength="100"
						data-options="validType:['intOrFloat'],min:0,precision:2"
						style="width: 150px; height: 20px;"></td>
					<td><label>电催结果</label></td>
					<td><k:dictionary constName="COLLECTION_RESULT" name="typeId"
							required="true" selectType="select" width="150" /></td>
				</tr>
				<tr>
					<td><label>PTP日期</label></td>
					<td><input type="text" id="ptpTime" name="ptpTime"
						maxLength="255" class="easyui-datebox" placeholder="请输入委ptp日期"
						style="width: 150px; height: 20px;" data-options="editable:false" /></td>

					<td><label>风控状态</label></td>
					<td><k:dictionary constName="COLLECTION_STATE"
							name="collecStateId" required="true" selectType="select"
							width="150" value='${caseModle.collecStateId }' /></td>
				</tr>
				<tr>

					<td><label>下次跟进时间</label></td>
					<td><input type="text" id="nextFollowTime"
						name="nextFollowTime" maxLength="255" class="easyui-datebox"
						data-options="editable:false,required:true" value="${nextFollowTime}"
						style="width: 150px; height: 20px;" /></td>



				</tr>
				<tr>
					<td valign="top"><label>记录</label></td>
					<td colspan="3"><input type="hidden" id="phone" name='phone' />
						<input id="content" name="content" class="easyui-textbox"
						style="width: 420px; height: 128px;"
						data-options="required:true,multiline:true,missingMessage:'请输入风控内容'" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div style="text-align: right;">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								onclick="submitForm()">保存</a> <a href="javascript:void(0)"
								class="easyui-linkbutton" onclick="clearForm()">清空</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	<div class='div-right'>
		
		
		<div style="height:100%;width:110%;">
			<table id="_datagrid_by_collector" class="easyui-datagrid" data-options="nowrap:false"></table>
		</div>
	</div>
</div>
