<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/index.css">
<style>
.datagrid-row-selected {
    background: none;
    color: #000;
}
</style>
<jsp:include page="script.jsp"></jsp:include>
<script type="text/javascript">
$().ready(function(){
	
	$('#hhh').hide();
	$("#help").hover(function(){
		$('#hhh').show();
	},function(){
		$('#hhh').hide();
	});
	
	
	$('#dj2').show();
	var myDate = new Date(); 
	var date = myDate.getFullYear()+'-'+(myDate.getMonth()+1)+'-'+'01';
	$("#normalDate").val($.formatDate(date, 'yyyy-MM-dd'));
	$("#normalDate1").val($.formatDate(new Date(), 'yyyy-MM-dd'));
	/* $('#dj2').hide();
	   $('#dj1').hide();
	$("#beginD").click(function(){
		$('#dj').show();
		$('#dj1').hide();
		$('#dj2').hide();
	});
	$("#paidT").click(function(){
		$('#dj1').show();
		$('#dj').hide();
		$('#dj2').hide();
	});
	$("#normalD").click(function(){
		$('#dj2').show();
		$('#dj1').hide();
		$('#dj').hide();
	}); */
	
});
</script>
<style>
#hhh p {
padding-left:10px;
}

</style>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	
	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 40px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
<!-- 		<table id="table_query"> -->
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; <input id="orgId" name="orgId" /></td>
				<td>
				
<!-- 				<select style="border: solid 0px #000;">  -->
				<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="easyui-combobox"> 
				<option id="beginD">委案日期&nbsp; </option>
				<option id="paidT" style="width:58px; height:23px;">还款日期</option>
				<option id="normalD" style="width:58px; height:23px;">统计日期&nbsp; </option>
				</select>&nbsp; --> 
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;统计日期&nbsp;  <span id="dj2"><input
						id="normalDate" name="normalDate"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var normalDate1=$dp.$('normalDate1');WdatePicker({onpicked:function(){normalDate1.focus();},maxDate:'#F{$dp.$D(\'normalDate1\')}'})" />
						&nbsp;至&nbsp; <input id="normalDate1" name="normalDate1"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'normalDate\')}'})" /></span>
					<!-- <span id="dj">
				<input id="beginDate" name="beginDate" class="easyui-datebox" width="105" />
				&nbsp;至 &nbsp;
				<input id="beginDate1" name="beginDate1" class="easyui-datebox" width="105" /></span> -->
				
			<!-- 	<span id="dj1">
				<input id="paidTime" name="paidTime" class="easyui-datebox" width="100" />
				&nbsp;至 &nbsp;
				<input id="paidTime1" name="paidTime1" class="easyui-datebox" width="100" /></span> -->
				
				<!-- <span id="dj2">
				<input id="normalDate" name="normalDate" class="easyui-datebox" width="100"/>
				&nbsp;至 &nbsp;
				<input id="normalDate1" name="normalDate1" class="easyui-datebox" width="100"/></span> -->
				
				</td>
				
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" 
				href="#" data-options="width:73,height:23,onClick:search">
				<span style="color:white;">查&nbsp;&nbsp;询</span></a>
					<a class="easyui-linkbutton" href="#" data-options="width:73,height:23"
					onclick="javascript:_search_form.form('reset');"><span style="color:white;">重置查询</span></a></td>
				
			<td width="320" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="qq" style="width:30px;height:23px;">
				
				<a style="padding:5px;" id="help" href="#"
				 data-options="iconCls:'easyui-icon-gengxin1',width:73,height:23">
				<span style="color:#7CABCF;"><img alt="" style="vertical-align: middle" src="${pageContext.request.contextPath}/static/img/explain.png"> 注释</span></a></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="hhh" style="background-color:white;border-color:#7CABCF;border: 2px solid #7CABCF;position:absolute;z-index:9999;top:30;right:65;">
<p> <span style="color:red;">基础还款统计：</span>若日期筛选区间为D1-D2</p>
<p> <span style="color:red;">案件数量/案件金额：</span>在D1-D2内在催的案件，即委案日期≤D2且退案日期≥D1的案件</p>
<p> <span style="color:red;">PTP数量/PTP金额：</span>在D1-D2内录入PTP的案件，即D1≤ptp日期≤D2的案件；</p>
<p> <span style="color:red;">未确认还款数量/未确认还款金额：</span>在D1-D2内CP且未确认的案件，即D1≤CP日期≤D2且还款状态为CP正常的案件；</p>
<p> <span style="color:red;">自来账数量/自来账金额：</span>在D1-D2内登账的自来账案件，即D1≤还款日期≤D2的登账类型为自来账的还款；</p>
<p> <span style="color:red;">已还款数量/已还款金额：</span>在D1-D2内所有还款的案件，D1≤还款日期≤D2的还款状态为已确认的还款（包括自来账）；</p>
<p> <span style="color:red;">已还款数量占比=</span>已还款数量/案件数量；</p>
<p> <span style="color:red;">已还款金额占比=</span>已还款金额/案件金额；</p>
<p> <span style="color:red;">平均案件金额=</span>案件金额/案件数量；</p>
<p> <span style="color:red;">确认还款率=</span>（已还款案件数量-自来账数量）/（未确认还款数量+已还款案件数量-自来账数量）</p>
<p> 以上统计数据均为最新数据，历史数据仅供参考</p>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_treegrid"></table>
	</div>
</div>
