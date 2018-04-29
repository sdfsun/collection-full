<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="departmentscript.jsp"></jsp:include>
<style>
.panel-header,.layout-expand {
    background-color: #F2F2F2;
    /* background: -webkit-linear-gradient(top,#ffffff 0,#F2F2F2 100%); */
    background: -moz-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
    background: -o-linear-gradient(top,#ffffff 0,#F2F2F2 100%);
    background: linear-gradient(to bottom,#ffffff 0,#F2F2F2 100%);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,endColorstr=#F2F2F2,GradientType=0);
    border:0px;

}
</style>
<div class="easyui-layout" data-options="fit:true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div data-options="region:'center',split:false,border:false" style="padding: 0px; height: 100%; width: 100%;">
		<div class="easyui-layout" data-options="fit:true"
			style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
			<div
				data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false" style="padding: 5px 0 0 0; height: 180px; width: 100%;overflow-y:hidden;">
				<form id="_search_form">

					<input type="hidden" id="batchCode" name="batchCode"
						value="${batchCode}" />

					<table border="0">
				<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件状态&nbsp; 
					   <select
						class="easyui-combobox" name="casestatus" id="casestatus"
						style="width: 150px; height: 23px;">
							<option value="0">正常</option>
							<option value="">全部</option>
							<option value="1">暂停</option>
							<shiro:hasRole name="ta"> 
							<option value="3">退案</option>
							</shiro:hasRole>
							<option value="4">结清</option>
						</select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;留案审批状态&nbsp; <k:dictionary
							width="150" constName="CASE_APPROVAL" name="approveState"
							selectType="all" /></td>

	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案日期&nbsp; <input
						id="caseDate" name="caseDate" style="width: 150px; height: 23px;"
						class="Wdate textbox"
						onFocus="var caseDate1=$dp.$('caseDate1');WdatePicker({onpicked:function(){caseDate1.focus();},maxDate:'#F{$dp.$D(\'caseDate1\')}'})" />
						&nbsp;至&nbsp; <input id="caseDate1" name="caseDate1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'caseDate\')}'})" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select  class="easyui-combobox" id="selectByNum" name="selectByNum"
							style="width: 200px; height: 23px;">
							<option  value="1" >案件序列号</option>
							<option  value="2" >档案号</option>
							<option  value="3" >证件号</option>
							<option  value="4" >卡号</option>

					</select></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件类型&nbsp; <k:dictionary
							constName="CASE_TYPE" width="150" name="caseType"
							selectType="all" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外访审批状态&nbsp; <k:dictionary
							width="150" constName="CASE_APPROVAL" name="visitState"
							selectType="all" /></td>

					<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号&nbsp; <input name="caseNum" style="width: 150px; height: 23px;"
						class="easyui-textbox" /></td> -->

					<!-- 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退案日期&nbsp; <input
						id="caseBackdate" name="caseBackdate" style="width: 150px;height:23px;"
						class="Wdate textbox"
						onFocus="var caseBackdate1=$dp.$('caseBackdate1');WdatePicker({onpicked:function(){caseBackdate1.focus();},maxDate:'#F{$dp.$D(\'caseBackdate1\')}'})" />
						&nbsp;至 &nbsp;<input id="caseBackdate1" name="caseBackdate1"
						style="width: 150px;height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'caseBackdate\')}'})" />
					</td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退案日期&nbsp; <input
						id="caseBackdate" name="caseBackdate"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="var caseBackdate1=$dp.$('caseBackdate1');WdatePicker({onpicked:function(){caseBackdate1.focus();},maxDate:'#F{$dp.$D(\'caseBackdate1\')}'})" />
						&nbsp;至 &nbsp;<input id="caseBackdate1" name="caseBackdate1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'caseBackdate\')}'})" />
					</td>
					
				<td rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="content" name="content" style="width: 200px; height: 60px;" class="easyui-textbox" 
					data-options="multiline:true" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控状态&nbsp; <k:dictionary
							constName="CS_STATE" width='150' name="collecStateId"
							selectType="all" /></td>


					
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逾期账龄&nbsp;
						<k:dictionary constName="OVERDUEAGE" width='150' name="overdueAge"
							selectType="all" />
					</td>
					<!-- 	
							<td>&nbsp;&nbsp;案件序列号&nbsp; <input name="caseCode" style="width: 330px; height: 23px;"
						class="easyui-textbox" prompt="多个案件序列号请用英文逗号隔开"/></td> -->
				
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓 名&nbsp; <input name="caseName" style="width: 150px; height: 23px;"
						class="easyui-textbox" /></td>
				
				
				</tr>
				<tr>

			
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;
						<input id="search_entrustId" name="entrustId"
						style="width: 150px; height: 23px;" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款情况&nbsp; <k:dictionary
							constName="REPAYMENT_STATUS" width="150" name="circumstance"
							selectType="all" /></td>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号&nbsp;
						<input name="phone" style="width: 150px; height: 23px;"
						class="easyui-textbox" />
					</td>
					
				 	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="easyui-linkbutton" href="#"
						style='color: white, margin-left:20px;'
						data-options="width:70,height:23,onClick:search">查&nbsp;&nbsp;询</a>
						<a class="easyui-linkbutton" href="#"
						style='color: white, margin-right:20px;'
						data-options="width:75,height:23"
						onclick="javascript:_search_form.form('reset');">重置查询</a>
					</td> 

				</tr>
				
				<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件标色&nbsp;
					 <select
						class="easyui-combobox" name="color" id="color"
						style="width: 150px; height: 23px;">
							<option value="">全部</option>
							<option value="0">正常</option>
							<option value="1">标红</option>
							<option value="2">标蓝</option>
							<option value="4">标紫</option>
						</select></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			</table>

				</form>
			</div>
			<%-- 中间部分 列表 --%>
			<div data-options="region:'center',split:false,border:false" style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
				
				
				<div data-options='fit:true' class="easyui-layout" style="width:100%;height:100%;">
				    <div data-options="region:'north',split:false,border:false" style="height:30px;width:100%; overflow-y:hidden;">
				    		<div
								style="padding-left: 30px; border: 1px solid #D4D4D4; border-bottom: 0px; line-height: 30px; background-color: #eff7fa;">
								案件列表 &nbsp; &nbsp; &nbsp; 案件总数 :<span id="total_case_count">0</span>&nbsp;
								&nbsp; &nbsp; 案件总金额 :<span id="total_case_money">0</span>&nbsp;
								&nbsp; &nbsp; 已还款案件量 :<span id="already_case_count">0</span>&nbsp;
								&nbsp; &nbsp; 已还款金额 :<span id="total_already_money">0</span>&nbsp;
								&nbsp; &nbsp; CP :<span id="total_cp_money">0</span>&nbsp; &nbsp;
								&nbsp; PTP :<span id="total_ptp_money">0</span>
								&nbsp; 总业绩 :<span id="total_brokerage">0</span>
							</div>
				    </div>
				    <div data-options="region:'center',split:false,border:false" style="height:100%;width:100%">
				    		
				<table id="_datagrid">
					<thead>
						<tr>
							<th
								data-options="field : 'state',
										title : '案件状态',width : 95,sortable:true,
										formatter : function(value, row, index) {
											return app.dictName('CASE_STATE',
													value)
										}"></th>
							<th
								data-options="field : 'collecStateId',sortable:true,
										title : '风控状态',width : 95,
										formatter : function(value, row, index) {
											return app.dictName('CS_STATE',
													value)
										}"></th>
							<th
								data-options="field : 'orgName',
										title : '风控方',width : 95"></th>
							<th
								data-options="field : 'caseAssignedName',sortable:true,
										title : '风控员',width : 95"></th>
							<th
								data-options="field : 'caseCode',
										title : '案件序列号',width : 180,sortable : true,
										formatter : function(value, row, index) {
											return openCasePage(value, row, index);
										}"></th>
							<th
								data-options="field : 'batchCode',width : 120,sortable : true,
										title : '批次号',
										formatter : function(value, row, index) {
											return openBatchPage(value, row, index);
										}"></th>
							<th
								data-options="field : 'caseName',sortable : true,
										title : '姓名',width : 95"></th>
							<th
								data-options="field : 'caseNum',
										title : '证件号',width : 180,sortable : true"></th>
							<th
								data-options="field : 'caseCard',
										title : '卡号',width : 180,sortable : true,"></th>
							<th
								data-options="field : 'mobile1',
										title : '手机号1',width : 95"></th>
							<th
								data-options="field : 'mobile2',
										title : '手机号2',width : 95"></th>
							<th
								data-options="field : 'entrustName',
										title : '委托方',width : 120,sortable : true"></th>
							<th
								data-options="field : 'beginDate',
										title : '委案日期',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmDate(value);
										}"></th>

							<th
								data-options="field : 'endDate',
										title : '退案日期',width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmDate(value);
										}"></th>
							<th
								data-options="field : 'caseMoney',
										title : '委案金额',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmmoney(value);
										}"></th>
							<th
								data-options="field : 'surplusMoney',
										title : '剩余还款',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmmoney(value);
										}"></th>
							<th
								data-options="field : 'ptpMoney',
										title : 'PTP金额',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmmoney(value);
										}"></th>
							<th
								data-options="field : 'cpMoney',
										title : 'CP金额',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmmoney(value);
										}"></th>
							<th
								data-options="field : 'paidNum',
										title : '已还款',
										width : 95,
										sortable : true,
										formatter : function(value, row, index) {
											return fmmoney(value);
										}"></th>


							<th
								data-options="field : 'agentRate',
										title : '业绩率',
										width : 60,
										formatter : function(value, row, index) {
											if(!value){
						    					return '0.00';
						    				}
						    				return value;
										}"></th>
							<th
								data-options="field : 'brokerage',
										title : '业绩',
										width : 95,
										
										formatter : function(value, row, index) {
											if(row.agentRate && row.paidNum){
						    					return fmmoney(row.agentRate*row.paidNum);
						    				}
						    				return '0.00';
										}"></th>
							<th
								data-options="field : 'lastPhone',
										title : '最后通电时间',
										width : 130,
										sortable : true,
										formatter : function(value, row, index) {
											if (value) {
												return $.formatDate(value,
														'yyyy-MM-dd HH:mm:ss');
											}
											return value;
										}"></th>


						</tr>
					</thead>
				</table>
			
				    </div>
				</div>
		
				
				
				
			
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
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