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
				
<!-- 				<select style="border: solid 0px #000;">  -->
				<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="easyui-combobox"> 
				<option id="beginD">委案日期&nbsp; </option>
				<option id="paidT" style="width:58px; height:23px;">还款日期</option>
				<option id="normalD" style="width:58px; height:23px;">统计日期&nbsp; </option>
				</select>&nbsp -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;统计日期&nbsp;  <span id="dj2"><input
						id="normalDate" name="normalDate" required="required"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="var normalDate1=$dp.$('normalDate1');WdatePicker({onpicked:function(){normalDate1.focus();},maxDate:'#F{$dp.$D(\'normalDate1\')}'})" />
						&nbsp;至&nbsp; <input id="normalDate1" name="normalDate1" required="required"
						style="width: 150px; height:23px;" class="Wdate textbox"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'normalDate\')}'})" /></span>
			
				<!-- <span id="dj">
				<input id="beginDate" name="beginDate" class="easyui-datebox" width="105" />
				&nbsp;至 &nbsp;
				<input id="beginDate1" name="beginDate1" class="easyui-datebox" width="105" /></span> -->
				
				<!-- <span id="dj1">
				<input id="paidTime" name="paidTime" class="easyui-datebox" width="100" />
				&nbsp;至 &nbsp;
				<input id="paidTime1" name="paidTime1" class="easyui-datebox" width="100" /></span> -->
				
				<!-- <span id="dj2">
				<input id="normalDate" name="normalDate" class="easyui-datebox" width="100"/>
				&nbsp;至 &nbsp;
				<input id="normalDate1" name="normalDate1" class="easyui-datebox" width="100"/></span> -->
				
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
<p> <span style="color:red;">外呼总次数：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼的次数；</p>
<p> <span style="color:red;">外呼接通次数：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼，外呼结果为双方接听的次数；</p>
<p> 以上统计数据均为最新数据，历史数据仅供参考</p>
	</div>
	

	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid">
		 <thead frozen="true">
                <tr>
                    <th data-options="field:'empName',title:'风控员',width:80"></th>
                    <th data-options="field:'cno', title:'坐席号', width:80,sortable:true"></th>
                </tr>
            </thead>
          
            <thead>
                <tr>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'0分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'1分钟以内',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'1-2分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'2-3分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'3-5分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'5-10分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'10-15分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'15-20分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'20-30分钟',width:80"></th>
<th colspan="1" data-options="field:'timeLen',align:'center',title:'30分钟以上',width:80"></th>
<!-- <th colspan="2" data-options="field:'timeLen',align:'center',title:'10-11分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'11-12分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'12-13分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'13-14分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'14-15分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'15-16分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'16-17分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'17-18分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'18-19分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'19-20分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'20-21分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'21-22分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'22-23分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'23-24分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'24-25分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'25-26分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'26-27分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'27-28分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'28-29分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'29-30分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'30-31分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'31-32分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'32-33分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'33-34分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'34-35分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'35-36分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'36-37分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'37-38分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'38-39分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'39-40分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'40-41分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'41-42分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'42-43分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'43-44分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'44-45分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'45-46分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'46-47分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'47-48分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'48-49分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'49-50分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'50-51分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'51-52分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'52-53分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'53-54分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'54-55分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'55-56分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'56-57分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'57-58分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'58-59分钟',width:80"></th>
<th colspan="2" data-options="field:'timeLen',align:'center',title:'59-60分钟',width:80"></th> -->

                </tr>
                <tr>
               <!--  <th data-options="field:'callOutCount',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutCount',title:'未接通次数',align:'center',width:80"></th>
               
                <!-- <th data-options="field:'callOutCount',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount1',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount1',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount2',title:'电催总次数',width:80"></th> -->
              <th data-options="field:'callOutConnectCount2',title:'接通次数',align:'center',width:80"></th>
                
                <!-- <th data-options="field:'callOutCount',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount3',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount10',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount5',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount15',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount10',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount20',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount15',title:'接通次数',align:'center',width:80"></th>
                
                <!-- <th data-options="field:'callOutCount8',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount20',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount9',title:'电催总次数',width:80"></th> -->
                <th data-options="field:'callOutConnectCount30',title:'接通次数',align:'center',width:80"></th>
                
               <!--  <th data-options="field:'callOutCount10',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount10',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount11',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount11',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount12',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount12',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount13',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount13',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount14',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount14',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount15',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount15',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount16',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount16',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount17',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount17',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount18',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount18',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount19',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount19',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount20',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount20',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount21',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount21',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount22',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount22',title:'接通次数',align:'center',width:80"></th>
                
                <th data-options="field:'callOutCount23',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount23',title:'接通次数',align:'center',width:80"></th>
                
<th data-options="field:'callOutCount24',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount24',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount25',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount25',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount26',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount26',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount27',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount27',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount28',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount28',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount29',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount29',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount30',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount30',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount31',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount31',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount32',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount32',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount33',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount33',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount34',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount34',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount35',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount35',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount36',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount36',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount37',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount37',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount38',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount38',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount39',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount39',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount40',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount40',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount41',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount41',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount42',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount42',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount43',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount43',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount44',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount44',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount45',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount45',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount46',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount46',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount47',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount47',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount48',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount48',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount49',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount49',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount50',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount50',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount51',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount51',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount52',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount52',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount53',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount53',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount54',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount54',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount55',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount55',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount56',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount56',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount57',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount57',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount58',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount58',title:'接通次数',align:'center',width:80"></th>
<th data-options="field:'callOutCount59',title:'电催总次数',width:80"></th>
<th data-options="field:'callOutConnectCount59',title:'接通次数',align:'center',width:80"></th>    -->
                </tr>
            </thead>
		</table>
	</div>
</div>



