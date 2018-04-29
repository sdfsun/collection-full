<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<jsp:include page="script.jsp"></jsp:include>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">

	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 165px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
			<table>
				<tr>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款状态&nbsp;
					</td>
					<td><k:dictionary constName="CP_TYPE" width="150" name="state"
							required="true" selectType="all" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回款风控方&nbsp; <input
						type="text" id="orgId" name="orgId" /></td>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号&nbsp; <input type="text" class="easyui-validatebox textbox eu-input" name="caseNum" onkeydown="if(event.keyCode==13)search()"
					maxLength="25" style="width: 150px; height:23px;" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cp日期&nbsp;
						<input id="cpTime" name="cpTime"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="var cpTime1=$dp.$('cpTime1');WdatePicker({onpicked:function(){cpTime1.focus();},maxDate:'#F{$dp.$D(\'cpTime1\')}'})" />
						&nbsp;至&nbsp; <input id="cpTime1" name="cpTime1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'cpTime\')}'})" />
					</td>
						 <td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  class="easyui-combobox" id="selectByNum" name="selectByNum"
							style="width: 200px; height: 23px;">
							<option  value="1" >案件序列号</option>
							<option  value="2" >档案号</option>
							<option  value="3" >证件号</option>
							<option  value="4" >卡号</option>
					</select></td> 

				</tr>
				<tr>
					<!-- <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡号&nbsp; </td><td><input type="text" class="easyui-validatebox textbox eu-input" name="caseCard" onkeydown="if(event.keyCode==13)search()"
					maxLength="25" style="width: 150px; height:23px;" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp;</td>
					<td><input id="batchCode" name="batchCode" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回款风控员&nbsp; <input
						id="caseAssignedId" name="caseAssignedId" width="150"
						style="width: 150px;" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款日期&nbsp; <input
						id="paidTime" name="paidTime" style="width: 150px; height: 23px;"
						class="Wdate textbox"
						onFocus="var paidTime1=$dp.$('paidTime1');WdatePicker({onpicked:function(){paidTime1.focus();},maxDate:'#F{$dp.$D(\'paidTime1\')}'})" />
						&nbsp;至&nbsp; <input id="paidTime1" name="paidTime1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'paidTime\')}'})" />
					</td>
					<td rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="content" name="content" style="width: 200px; height: 50px;" class="easyui-textbox" 
					data-options="multiline:true"/></td>
				</tr>
				<tr>

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;
					</td>
					<td><input id="search_entrustId" name="entrustId" /></td>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; <input type="text" class="easyui-validatebox textbox eu-input" name="caseCode" onkeydown="if(event.keyCode==13)search()"
					maxLength="25" style="width: 150px; height:23px;" /></td> -->
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号&nbsp; <input
						type="text" class="easyui-validatebox textbox eu-input"
						name="mobile1" onkeydown="if(event.keyCode==13)search()"
						maxLength="25" style="width: 150px; height: 23px;" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认日期&nbsp; <input
						id="surTime" name="surTime" style="width: 150px; height: 23px;"
						class="Wdate textbox"
						onFocus="var surTime1=$dp.$('surTime1');WdatePicker({onpicked:function(){surTime1.focus();},maxDate:'#F{$dp.$D(\'surTime1\')}'})" />
						&nbsp;至&nbsp; <input id="surTime1" name="surTime1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'surTime\')}'})" />
					</td>
				</tr>

				<tr>
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;
					</td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" name="caseName"
						onkeydown="if(event.keyCode==13)search()" maxLength="25"
						style="width: 150px; height: 23px;" /></td>
					<td id="a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件状态&nbsp;
						<select class="easyui-combobox" name="casestatus" id="casestatus"
						style="width: 150px; height: 23px;">
							<option value="">全部</option>
							<option value="0">正常</option>
							<option value="1">暂停</option>
							<option value="3">退案</option>
							<option value="4">结清</option>

					</select>
					</td>


					<td id="b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件状态&nbsp;
						<select class="easyui-combobox" name="casestatus1"
						id="casestatus1" style="width: 150px; height: 23px;">
							<option value="">全部</option>
							<option value="0">正常</option>
							<option value="1">暂停</option>
							<option value="4">结清</option>


					</select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a></td>
				</tr>

			</table>
		</form>
		<div
			style="padding-top: 0px; margin-top: 1px; padding-left: 30px; border: 1px solid #D4D4D4; border-bottom: 0px; line-height: 30px; background-color: #eff7fa;">
			还款列表 &nbsp; &nbsp; &nbsp; 已还款总额 :<span id="total_already_money">0</span>&nbsp;
			&nbsp; &nbsp; 未确认还款总额 :<span id="total_cp_money">0</span>&nbsp; &nbsp;
			&nbsp; &nbsp; 总佣金 :<span id="total_paidMoney">0</span>&nbsp; &nbsp;
			&nbsp; 总业绩 :<span id="total_achieve">0</span>&nbsp;

		</div>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th
						data-options="field:'state',title:'还款状态',width:95,
					formatter : function(value, row, index) {
 						return app.dictName('CP_TYPE', value)
 					}"></th>
					<th
						data-options="field:'caseModel.caseCode',title:'案件序列号',width:180,sortable:true,
                	formatter: function(value,row,index){
                		return openCaseDetailed(value, row, index);
    			    }"></th>
					<th
						data-options="field : 'batchModel',title : '批次号',width : 120,
       				formatter : function(value, row, index) {
       					if (value) {
       						return value.batchCode;
       					}
       					return value;
       				}"></th>
					<th
						data-options="field : 'batchModel.entrustName',title : '委托方',width : 120,sortable:true,"></th>
					<th
						data-options="field : 'caseModel.caseName',title : '姓名',width : 95"></th>
					<th
						data-options="field : 'caseModel.caseNum',title : '证件号',width : 180,sortable:true,"></th>
					<th
						data-options="field : 'caseModel.caseCard',title : '卡号',width : 180,sortable:true,"></th>
					<th
						data-options="field : 'caseModel.caseMoney',title : '委案金额',width : 95,sortable:true,
  					formatter : function(value, row, index) {
 						return fmmoney(value);
 					} "></th>
					<th
						data-options="field : 'cpMoney',title:'CP金额',width:95,sortable:true,
       				formatter : function(value, row, index) {
 						return fmmoney(value);
 					} 	"></th>
					<th
						data-options="field : 'paidNum',title:'已还款',width:95,sortable:true,
  					formatter : function(value, row, index) {
 						return fmmoney(value); 			
 								} "></th>
					<th
						data-options="field : 'entrustPaidRate',title:'委托费率',width:60,
       				formatter : function(value, row, index) {
 						return fmmoney(value);
 					} "></th>
					<th
						data-options="field : 'entrustPaid',title:'佣金',width:95,sortable:true,
       				formatter : function(value, row, index) {
 						return fmmoney(value);
 					},
 					styler: function(value,row,index){
					    if(row.state==1){
					    	return 'color:#CACACA;';
					    }
					} 	"></th>
					<th
						data-options="field : 'backPaidRate',title : '业绩率',width : 60,sortable:true,
 					formatter : function(value, row, index) {
 						return fmmoney(value); 			
 								} "></th>
					<th
						data-options="field : 'commission',title : '业绩',width : 95,sortable:true,
 					formatter : function(value, row, index) {
 						return fmmoney(value); 			
 								},
 					styler: function(value,row,index){
					    if(row.state==1){
					    	return 'color:#CACACA;';
					    }
					}"></th>
					<th
						data-options="field : 'caseModel.mobile1',title : '手机号',width : 95"></th>

					<!-- 	<th data-options="field : 'outDerateNew',title : '外部减免',width : 95,
  					formatter : function(value, row, index) {
 						return fmmoney(value);
 					} 	"></th>
 					<th data-options="field : 'inDerateNew',title : '内部减免',width : 95,
  					formatter : function(value, row, index) {
 						return fmmoney(value);
 					} 	"></th> -->



					<th
						data-options="field  :'cpTime',title:'CP日期',width:95,sortable:true,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>
					<th
						data-options="field  :'paidTime',title:'还款日期',width:95,sortable:true,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>
					<th
						data-options="field  :'surTime',title:'确认日期',width:95,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>

					<th
						data-options="field  :'actual_visit_time',title:'最后外访日期',width:95,sortable:true,
       				formatter : function(value, row, index) {
    					return fmDate(value);    				}"></th>
					<th
						data-options="field  :'visit_user',title:'最后外访员',width:95,sortable:true,"></th>

					<th
						data-options="field:'organization.soName',title:'回款风控方',width:95,sortable:true,"></th>
					<th
						data-options="field:'cpName',title:'回款风控员',width:95,sortable:true,"></th>
					<th
						data-options="field:'operateName',title:'登账人',width:95"></th>
					<th data-options="field:'cancelReason',title:'作废原因',width:180"></th>
					<th data-options="field:'surRemark',title:'备注',width:180"></th>
					<th
						data-options="field:'repayType',title:'登账类型',width:95,
 					formatter: function(value,row,index){
 					if(value==2){
 						return '自来账';
 					}else{
	       				return '正常';
	       				}
	       			}"></th>
					<th
						data-options="field:'op',title:'还款凭证',width:95,
	       			formatter: function(value,row,index){
	       				return openEnclosure(value,row,index);
	       			}"></th>
				</tr>
			</thead>
		</table>
	</div>
</div>



