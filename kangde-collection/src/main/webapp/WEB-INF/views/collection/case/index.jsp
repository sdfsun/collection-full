<%@ page contentType="text/html;charset=UTF-8"%>
<%@ tagliburi="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<script type="text/javascript"
	src="${ctxStatic}/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<style>
.panel-header, .layout-expand {
	background-color: #F2F2F2;
	/* background: -webkit-linear-gradient(top,#ffffff 0,#F2F2F2 100%); */
	background: -moz-linear-gradient(top, #ffffff 0, #F2F2F2 100%);
	background: -o-linear-gradient(top, #ffffff 0, #F2F2F2 100%);
	background: linear-gradient(to bottom, #ffffff 0, #F2F2F2 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,
		endColorstr=#F2F2F2, GradientType=0);
	border: 0px;
}
</style>

<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 2px 0 0 0; height: 205px; width: 100%; margin-top: 3px; overflow-y: hidden;">
		<form id="_search_form" style="">
			<table border="0">
				<tr>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp; <input
						id="search_entrustId" name="entrustId" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; <input
						id="orgId" name="orgId" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案日期&nbsp;
					</td>
					<td><input id="entrustDateStart" name="entrustDateStart"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="var entrustDateEnd=$dp.$('entrustDateEnd');WdatePicker({onpicked:function(){entrustDateEnd.focus();},maxDate:'#F{$dp.$D(\'entrustDateEnd\')}'})" />
						&nbsp;至 &nbsp;<input id="entrustDateEnd" name="entrustDateEnd"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'entrustDateStart\')}'})" />
					</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select  class="easyui-combobox" id="selectByNum" name="selectByNum"
							style="width: 200px; height: 23px;">
							<option  value="1" >案件序列号</option>
							<option  value="2" >档案号</option>
							<option  value="3" >证件号</option>
							<option  value="4" >卡号</option>

					</select></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控员&nbsp; <input
						id="caseAssignedId" name="caseAssignedId" /></td>
					<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号&nbsp;&nbsp;<input name="caseNum"
						class="easyui-textbox" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分案状态&nbsp; <k:dictionary
							constName="CASE_ASSIGN_STATE" name="caseAssignState" width="140"
							selectType="all" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款日期&nbsp;
					</td>
					<td><input id="paidTimeStart" name="paidTimeStart"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="var entrustDateEnd=$dp.$('paidTimeEnd');WdatePicker({onpicked:function(){paidTimeEnd.focus();},maxDate:'#F{$dp.$D(\'paidTimeEnd\')}'})" />
						&nbsp;至 &nbsp;<input id="paidTimeEnd" name="paidTimeEnd"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'paidTimeStart\')}'})" />
					</td>
					<td rowspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="content" name="content" style="width: 200px; height: 100px;" class="easyui-textbox" 
					data-options="multiline:true"/></td>
			
				</tr>
				<tr>
					

					<td>&nbsp;&nbsp;&nbsp;还款情况&nbsp; <k:dictionary
							constName="REPAYMENT_STATUS" name="circumstance" selectType="all" width="140"/></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件状态&nbsp; 
					   <select
						class="easyui-combobox" name="casestatus" id="casestatus"
						style="width: 140px; height: 23px;">
							<option value="0">正常</option>
							<option value="">全部</option>
							<option value="1">暂停</option>
							<shiro:hasRole name="ta"> 
							<option value="3">退案</option>
							</shiro:hasRole>
							<option value="4">结清</option>
							<option value="5">撤回</option>
						</select>
					</td>


				

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退案日期&nbsp;
					</td>
					<td><input id="endDate" name="endDate"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="var endDate1=$dp.$('endDate1');WdatePicker({onpicked:function(){endDate1.focus();},maxDate:'#F{$dp.$D(\'endDate1\')}'})" />
						&nbsp;至 &nbsp;<input id="endDate1" name="endDate1"
						style="width: 140px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endDate\')}'})" /></td>

				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp; <input
						id="batchCode" name="batchCode" value="${batchCode}"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逾期账龄&nbsp; <k:dictionary
							constName="OVERDUEAGE" width="140" name="overdueAge"
							selectType="all" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案金额&nbsp;
					</td>
					<td><input name="caseMoneyStart" style="width: 140px;"
						class="easyui-numberbox" /> &nbsp;至&nbsp; <input
						name="caseMoneyEnd" style="width: 140px;" class="easyui-numberbox" /></td>

				</tr>

				<tr>
				
					<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;档案号&nbsp; <input name="caseFileNo"
						class="easyui-textbox" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;案件类型&nbsp; <k:dictionary
							constName="CASE_TYPE" name="caseType" selectType="all" width="140"/></td>
					
					<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td><td><input
						name="caseCode" style="width:330px;" class="easyui-textbox" prompt="多个案件序列号请用英文逗号隔开"
						/>
					</td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp;
					<k:dictionary constName="CS_STATE" name="collecStateId" width="140"
							selectType="all" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户号&nbsp;</td><td> <input
						name="cusNo" class="easyui-textbox" style="width:310px;"/></td>

				</tr>
				<tr>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号&nbsp;&nbsp;<input
						name="phone" class="easyui-textbox"  style="width:140px;"/>
					</td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;&nbsp;<input
						name="caseName" class="easyui-textbox"  style="width:140px;"/>
					</td>
					
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件标色&nbsp;</td>
					<td> <select
						class="easyui-combobox" name="color" id="color"
						style="width: 140px; height: 23px;">
							<option value="">全部</option>
							<option value="0">正常</option>
							<option value="1">标红</option>
							<option value="2">标蓝</option>
							<option value="4">标紫</option>
						</select></td>
						
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="easyui-linkbutton" href="#"
						data-options="width:68,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a>&nbsp;<a
						class="easyui-linkbutton" href="#"
						data-options="width:68,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a> </td>
				</tr>

			</table>
		</form>

	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">




		<div data-options='fit:true' class="easyui-layout"
			style="width: 100%; height: 100%;">
			<div data-options="region:'north',split:false,border:false"
				style="height: 30px; width: 100%; overflow-y: hidden;">
				<div
					style="padding-top: 0px; margin-top: 1px; padding-left: 30px; border: 1px solid #D4D4D4; border-bottom: 0px; line-height: 30px; background-color: #eff7fa;">
					案件列表 &nbsp; &nbsp; &nbsp; 案件总数 :<span id="total_case_count">0</span>&nbsp;
					&nbsp; &nbsp; 案件总金额 :<span id="total_case_money">0</span>&nbsp;
					&nbsp; &nbsp; 已还款案件量 :<span id="already_case_count">0</span>&nbsp;
					&nbsp; &nbsp; 已还款金额 :<span id="total_already_money">0</span>&nbsp;
					&nbsp; &nbsp; CP :<span id="total_cp_money">0</span>&nbsp; &nbsp;
					&nbsp; PTP :<span id="total_ptp_money">0</span>
				</div>
			</div>
			<div data-options="region:'center',split:false,border:false"
				style="height: 100%; width: 100%">
				<table id="_datagrid"></table>
				<%--Grid按钮--%>
				<div id="_toolbar" style="display: none;">
					<shiro:hasPermission name="case:division">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-waifangpaicheng'"
							onclick="selectDivision()">所选分案</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:selectDivisionForAuto">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-suoxuanfenan'"
							onclick="selectDivisionForAuto()">所选自动分案</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:queryDivision">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-suochajieguo'"
							onclick="divisionForQuery()">所查结果分案</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:autoDivision">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-suochazidong'"
							onclick="resultAutoDivision()">所查自动分案</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:pause">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-zanting'" onclick="pause()">暂停案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:revocation">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-chean'" onclick="revocation()">撤回案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:recover">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-huifu'" onclick="recover()">恢复案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:settle">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-jieqing'" onclick="settle()">结清案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:exportSelected">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-daochusuoxuan'"
							onclick="exportCase(1)">导出所选案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:exportQuery">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-daochusuocha'"
							onclick="exportCase(2)">导出所查案件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="case:markcolor">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-biaose'" onclick="markCase()">案件标色</a>
					</shiro:hasPermission>

					<shiro:hasPermission name="case:comment:markcolor">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-tianjia'"
							onclick="showCommentDialog_case()">案件评语</a>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="case:repair:batchsave">
						<a class="easyui-linkbutton"
							data-options="iconCls:'eu-icon-tianjia'"
							onclick="searchMaterial()">查资申请</a>
					</shiro:hasPermission>
					
				</div>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {
	maxLength : {
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : '输入数据行数不可多于200行！'
	}
});
	var caseIds_comment;
	var isBatch = true;
	function showCommentDialog_case() {
		caseIds_comment = [];
		app.dataGridSelect(_datagrid, function(rows) {
			var ids = new Array();
			$.each(rows, function(i, row) {
				caseIds_comment[i] = row.id;
			});
			showCommentDialog();
		});
	}
	
	

</script>
<jsp:include page="../casedetail/case_comment.jsp"></jsp:include>