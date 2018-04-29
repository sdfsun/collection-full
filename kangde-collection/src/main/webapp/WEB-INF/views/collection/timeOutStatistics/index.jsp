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
<p> <span style="color:red;">外呼通话时长：</span>D1≤电话进入系统时间≤D2中的呼叫类型为点击外呼的通话时长汇总；</p>
<p> <span style="color:red;">平均外呼通话时长：</span>外呼通话时长/外呼接通次数；</p>
<p> <span style="color:red;">外呼接通率：</span>外呼接通次数/外呼次数*100%；</p>
<p> <span style="color:red;">平均每日通话时长：</span>通话时长/天数；</p>
<p> 以上统计数据均为最新数据，历史数据仅供参考</p>
	</div>

	<%-- 中间部分 列表 --%>
	<div data-options="region:'center',split:false,border:false"
		style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
		<table id="_datagrid">
		 <thead frozen="true">
                <tr>
                    <th data-options="field:'empName',title:'风控员',width:80"></th>
                    <th data-options="field:'cno', title:'坐席号', width:110,sortable:true"></th>
                </tr>
            </thead>
          
            <thead>
                <tr>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'0-8点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'8-9点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'9-10点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'10-11点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'11-12点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'12-13点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'13-14点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'14-15点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'15-16点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'16-17点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'17-18点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'18-19点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'19-20点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'20-21点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'21-24点',width:80"></th>
<!-- <th colspan="4" data-options="field:'timeLen',align:'center',title:'22-23点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'23-24点',width:80"></th> -->


<!-- <th colspan="4" data-options="field:'timeLen',align:'center',title:'1-2点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'2-3点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'3-4点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'4-5点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'5-6点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'6-7点',width:80"></th>
<th colspan="4" data-options="field:'timeLen',align:'center',title:'7-8点',width:80"></th> -->

                </tr>
                <tr>
                <th data-options="field:'callOutCount',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>  -->
                <th data-options="field:'callOutConnectRate',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                <th data-options="field:'callOutCount8',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount8',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration8',title:'总通话时长',width:80"></th>
                 <!-- <th data-options="field:'callOutConnectDurationAvg8',title:'平均外呼通话时长',width:110"></th>  -->
                <th data-options="field:'callOutConnectRate8',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg8',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount9',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount9',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration9',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg9',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate9',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg9',title:'平均每日通话时长',width:110"></th> -->
                
               <th data-options="field:'callOutCount10',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount10',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration10',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg10',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate10',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg10',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount11',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount11',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration11',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg11',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate11',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg11',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount12',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount12',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration12',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg12',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate12',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg12',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount13',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount13',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration13',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg13',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate13',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg13',title:'平均每日通话时长',width:110"></th> -->
                
               <th data-options="field:'callOutCount14',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount14',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration14',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg14',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate14',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg14',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount15',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount15',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration15',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg15',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate15',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg15',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount16',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount16',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration16',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg16',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate16',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg16',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount17',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount17',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration17',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg17',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate17',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg17',title:'平均每日通话时长',width:110"></th> -->
                
                <th data-options="field:'callOutCount18',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount18',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration18',title:'总通话时长',width:80"></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg18',title:'平均外呼通话时长',width:110"></th> -->
                <th data-options="field:'callOutConnectRate18',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg18',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
                <th data-options="field:'callOutCount19',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount19',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration19',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg19',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>  -->
                <th data-options="field:'callOutConnectRate19',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg19',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
                <th data-options="field:'callOutCount20',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount20',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration20',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg20',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>  -->
                <th data-options="field:'callOutConnectRate20',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg20',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
                <th data-options="field:'callOutCount21',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount21',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration21',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'callOutConnectDurationAvg21',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>  -->
                <th data-options="field:'callOutConnectRate21',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <!-- <th data-options="field:'bridgeDurationDayAvg21',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
               <!--  <th data-options="field:'callOutCount22',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount22',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration22',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg22',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate22',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg22',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount23',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount23',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration23',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg23',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>  
                <th data-options="field:'callOutConnectRate23',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th> -->
                <!-- <th data-options="field:'bridgeDurationDayAvg23',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
                
                
                <!-- <th data-options="field:'callOutCount1',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount1',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration1',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg1',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate1',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg1',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount2',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount2',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration2',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg2',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate2',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg2',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount3',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount3',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration3',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg3',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate3',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg3',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
               <th data-options="field:'callOutCount4',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount4',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration4',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg4',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate4',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg4',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount5',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount5',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration5',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg5',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate5',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg5',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount6',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount6',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration6',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg6',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate6',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg6',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                
                <th data-options="field:'callOutCount7',title:'电催总次数',width:80"></th>
                <th data-options="field:'callOutConnectCount7',title:'总接通次数',width:80"></th>
                <th data-options="field:'callOutConnectDuration7',title:'总通话时长',width:80,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th>
                <th data-options="field:'callOutConnectDurationAvg7',title:'平均外呼通话时长',width:110,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> 
                <th data-options="field:'callOutConnectRate7',title:'外呼接通率',width:80,
                formatter : function(value, row, index) {
 					if (null == value) {
						return '0.00%';
					} else if ('' == value) {
						return '0.00%';
					}else if ('—' == value) {
						return '—';
					} else {
						return $.fmoney(value)+'%';
					}
					return value;
				}
                "></th>
                <th data-options="field:'bridgeDurationDayAvg7',title:'平均每日通话时长',width:110,
                 formatter : function(value, row, index) {
 					if (null == value) {
						return '00:00:00';
					} else if ('0' == value) {
						return '00:00:00';
					} else {
						return value;
					}
					return value;
				}
                "></th> -->
                
               

                </tr>
            </thead>
		</table>
	</div>
</div>



