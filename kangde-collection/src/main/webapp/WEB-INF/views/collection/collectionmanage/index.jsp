<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/nesting.jsp"%>
<jsp:include page="script.jsp"></jsp:include>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; margin-top: 5px; height: 123px; width: 100%; overflow-y: hidden;">
		<form id="_search_form">
			<table>
				<tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp;</td>
					<td><input id="orgId" name="orgId" /></td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托方&nbsp;</td>
					<td><input id="search_entrustId" value="89" name="entrustId" /></td>
					<!-- 	<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="loanContract"
						name="loanContract" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td> -->
					<!--<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件序列号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseCode"
						name="caseCode" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td> -->

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委案日期&nbsp;</td>
					<td><input id="caseDate" name="caseDate"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="var caseDate1=$dp.$('caseDate1');WdatePicker({onpicked:function(){caseDate1.focus();},maxDate:'#F{$dp.$D(\'caseDate1\')}'})" />
						&nbsp; 至 &nbsp; <input id="caseDate1" name="caseDate1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'caseDate\')}'})" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select  class="easyui-combobox" id="selectByNum" name="selectByNum"
							style="width: 200px; height: 23px;">
							<option  value="1" >案件序列号</option>
							<option  value="2" >档案号</option>
							<option  value="3" >证件号</option>
							<option  value="4" >卡号</option>

					</select></td>
				</tr>
				<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控结果&nbsp;
					</td>
					<td><k:dictionary constName="COLLECTION_RESULT" name="typeId"
							required="true" selectType="all" width="150" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次号&nbsp;</td>
						<td><input id="batchCode" name="batchCode"/></td>

					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;催记日期&nbsp; </td>
					<td><input type="text" id="createTime" name="createTime"
						maxLength="255" class="easyui-datebox"
						style="width: 150px; height: 22px" /> 至 <input type="text"
						id="createTime1" name="createTime1" maxLength="255"
						class="easyui-datebox" style="width: 150px; height: 22px" /></td> -->

					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;催记日期&nbsp;</td>
					<td><input id="createTime" name="createTime" value='${createTimeBegin }'
						style="width: 150px; height: 23px;" class="Wdate textbox" 
						onFocus="var createTime1=$dp.$('createTime1');WdatePicker({onpicked:function(){createTime1.focus();},maxDate:'#F{$dp.$D(\'createTime1\')}'})" />
						&nbsp; 至 &nbsp; <input id="createTime1" name="createTime1"  value='${createTimeEnd }'
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createTime\')}'})" />
					</td>
					<td rowspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="content" name="content" style="width: 200px; height: 80px;" class="easyui-textbox" 
					data-options="multiline:true"/></td>
				</tr>
				<tr>
				<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控措施&nbsp;
					</td>
					<td><k:dictionary constName="STRATEGY" name="prCat"
							required="true" selectType="all" width="150" /></td>

					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名&nbsp;
					</td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseName"
						name="caseName" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退案日期&nbsp;</td>
					<td><input id="actualEndDate" name="actualEndDate"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="var actualEndDate1=$dp.$('actualEndDate1');WdatePicker({onpicked:function(){actualEndDate1.focus();},maxDate:'#F{$dp.$D(\'actualEndDate1\')}'})" />
						&nbsp; 至 &nbsp; <input id="actualEndDate1" name="actualEndDate1"
						style="width: 150px; height: 23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'actualEndDate\')}'})" />
					</td>
				</tr>
				<tr>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证件号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseNum"
						name="caseNum" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td>-->
						<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控员&nbsp;
					</td>
					<td><input id="caseAssignedId" name="caseAssignedId"
						style="width: 150px; height: 22px" /></td>
					
					<!-- <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡号&nbsp; </td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="caseCard"
						name="caseCard" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td> -->
					<td align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机&nbsp;
					</td>
					<td><input type="text"
						class="easyui-validatebox textbox eu-input" id="contact"
						name="contact" onkeydown="if(event.keyCode==13)search()"
						maxLength="50" style="width: 150px; height: 22px" /></td>
					<td></td>

					<td><a class="easyui-linkbutton" href="#"
						data-options="width:73,height:23,onClick:search"><span
							style="color: white;">查&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:73,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a></td>
				</tr>
			</table>
		</form>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid"></table>
	</div>
</div>


