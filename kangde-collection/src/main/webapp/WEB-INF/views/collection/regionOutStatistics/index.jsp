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

	
	 var myDate = new Date(); 
	var date = myDate.getFullYear()+'-'+(myDate.getMonth()+1)+'-'+'01';
	$("#normalDate").val($.formatDate(date, 'yyyy-MM-dd'));
	$("#normalDate1").val($.formatDate(new Date(), 'yyyy-MM-dd')); 
	
});
</script>
<style>
#hhh p {
padding-left:10px;
padding-right:5px;
}

</style>
<div class="easyui-layout" fit="true"
	style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
	
	<div
		data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 40px; width: 100%; overflow-y: hidden;">
		<form id="_search_form" style="padding: 5px;">
<!-- 		<table id="table_query"> -->
		<table border="0">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;风控方&nbsp; <input id="orgId" name="orgId" /></td>
				<td>
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;统计日期&nbsp;  <span id="dj2"><input
						id="normalDate" name="normalDate" required="required"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var normalDate1=$dp.$('normalDate1');WdatePicker({onpicked:function(){normalDate1.focus();},maxDate:'#F{$dp.$D(\'normalDate1\')}'})" />
						&nbsp;至&nbsp; <input id="normalDate1" name="normalDate1" required="required"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'normalDate\')}'})" /></span>
			
				</td>
				
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" 
				href="#" data-options="width:73,height:23,onClick:search"><span style="color:white;">查&nbsp;&nbsp;询</span></a>
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
<p> <span style="color:red;">外呼地域统计：</span>日期筛选只能选择一个月内，如D1-D2</p>
<p> <span style="color:red;">外呼总次数：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼的次数；</p>
<p> <span style="color:red;">外呼接通次数：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼，外呼结果为双方接听的次数；</p>
<p> <span style="color:red;">外呼通话时长：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼的通话时长汇总；</p>
<p> <span style="color:red;">平均外呼通话时长：</span>外呼通话时长/外呼接通次数；</p>
<p> <span style="color:red;">外呼接通率：</span>外呼接通次数/外呼次数*100%；</p>
<p> <span style="color:red;">平均每日通话时长：</span>通话时长/天数；</p>
<p> 以上统计数据均为最新数据，历史数据仅供参考</p>
	</div>
	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid"></table>
	</div>
</div>



