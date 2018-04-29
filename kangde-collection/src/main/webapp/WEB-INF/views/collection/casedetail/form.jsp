<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>	
<script type="text/javascript" src="${ctxStatic}/app/app.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/js/easyui-${ev}/extend/icon/eu-icon.css" />
<script type="text/javascript" src="${ctxStatic}/js/easyui-${ev}/extend/js/easyui-extend.js" charset="utf-8"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var isBatch = false;
	var caseIds_comment = new Array();
	caseIds_comment.push("${caseId}");

	$().ready(
			function() {
				$.each($("input[money=true]"), function() {
					var money = $(this).val();
					if (money) {
						$(this).val($.fmoney(money));
					}
				});

				$("input").mouseover(function() {
					$(this).attr("title", $(this).val());
				});

				var color = "${caseDetailVo.color}";
				$("input[name=caseCommentColor]:eq(" + color + ")").attr(
						"checked", 'checked');
				
				$('#basics').panel({
				    title:'基础信息',
				    tools:[{
				    iconCls:'eu-icon-jianli',
				     handler:function(){
				    	//简历
				    	var cityKw="${caseDetailVo.cityName}";
				    	if(cityKw==""){
				    		alert("案件区域-市 为空不可查询",'warning');
				    	}else{
				    	var RoleUrl =ctx+ "/collection/casedetail/resumeshow?name=${caseDetailVo.caseName}&cityKw=${caseDetailVo.cityName}";
						app.openViewDialogNoBtn(100,'简历信息', 820, 400, RoleUrl, 'GET',
								function() {
								});
				    	}
				    }
				    },{
				    iconCls:'eu-icon-shixin',
				    handler:function(){
				    	//失信
				    	var RoleUrl =ctx+ "/collection/casedetail/Dishonestyshow?name=${caseDetailVo.caseName}&idCode=${caseDetailVo.caseNum}";
						app.openViewDialogNoBtn(100,'失信信息', 680, 400, RoleUrl, 'GET',
								function() {
								});
				    	} 
				    }]
				}); 
				
			});
	//证件号
	function caseNum(){
		var RoleUrl =ctx+ "/collection/casedetail/idshow?idCar=${caseDetailVo.caseNum}";
		app.openViewDialogNoBtn(150,'证件号信息', 500, 130, RoleUrl, 'GET',
				function() {
				});
	}
	//工资
	function casePosition(){
		var RoleUrl =ctx+ "/collection/casedetail/casePositionshow?jobKw=${caseDetailVo.casePosition}&cityKw=${caseDetailVo.cityName}";
		app.openViewDialogNoBtn(833,'职位预计工资', 500, 130, RoleUrl, 'GET',
				function() {
				});
	}
	//根据id查找简历
	function detailed(id){
		var RoleUrl =ctx+ "/collection/casedetail/resumeshowForId?id="+id;
		app.openViewDialogNoBtn(100,'简历详细信息', 680, 400, RoleUrl, 'GET',
				function() {
				});
	}
	//根据id查找失信
		function dishonestyshowForId(id){
		var RoleUrl =ctx+ "/collection/casedetail/dishonestyshowForId?id="+id;
		app.openViewDialogNoBtn(100,'失信详细信息', 820, 400, RoleUrl, 'GET',
				function() {
				});
	}

</script>

<form:form id="_detail_form" modelAttribute="caseDetailVo">
	<!-- 基础信息 -->
	<div class='detailHeader'>
		<div
			style="text-align: center; margin-top: 0px; color: white; font-weight: 900;">
			<span id='header_caseCode'>${caseDetailVo.caseCode}</span> : <span
				id='header_caseName'>${caseDetailVo.caseName}</span>
		</div>
		<div style="text-align: right; margin-top: 0px; color: white;">
			委案日期:<span id='header_caseDate'><fmt:formatDate
					value='${caseDetailVo.caseDate}' type='date' pattern='yyyy-MM-dd' />
			</span>

		</div>
	</div>

	<div id="info">
		<div class="easyui-panel" title="案件信息"
			data-options="border:false,noheader:true"
			style="width: 100%; padding: 0px;">
			<div id="basics"  title="基础信息"
				style="width: 100%; padding: 0px;">
				<table>
					<tr>
						<td><label>性别 </label></td>
						<td><form:input type="text" path="caseSex" class="textbox"
								readonly="true" /></td>
						<td><label>年龄 </label></td>
						<td><form:input type="text" path="caseAge" class="textbox"
								readonly="true" /></td>
						<td><label>婚否 </label></td>
						<td><form:input type="text" path="caseIsMarriage"
									class="textbox" readonly="true" /></td>
						<td><label> 档案号</label></td>
						<td><form:input type="text" path='caseFileNo'
									class="textbox" readonly="true" /></td>			
					</tr>
					<tr>
						<td><label>批次号 </label></td>
						<td><form:input type="text" path="batchCode" class="textbox"
								readonly="true" /></td>
						<td><label>委托方 </label></td>
						<td><form:input type="text" path='entrustName'
								class="textbox" readonly="true" /></td>
						<td><label>证件类型 </label></td>
						<td><form:input type="text" path="caseNumId" class="textbox"
								readonly="true" /></td>
								
						<%-- <td><label>证件号 </label></td>
						<td><a target="_blank" style="text-decoration:underline;cursor:pointer" onclick="caseNum()" >
						<font color=blue>${caseDetailVo.caseNum}</font></a></td> --%>
					</tr>
					<tr>
					<td><label>委案金额 </label></td>
						<td><form:input type="text" path="caseMoney"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
						<td><label>委案日期</label></td>
						<td><form:input type="text" path="caseDate" class="textbox"
								readonly="true" /></td>
						<td><label>退案日期 </label></td>
						<td><form:input type="text" path="caseBackdate"
								class="textbox" readonly="true" />
					
						<td><label>已还款</label></td>
						<td><form:input type="text" path="paidNum"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
					
						
					</tr>
					<tr>
						<td><label>委托费率 </label>
						<td><form:input type="text" path="entrustRate"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" readonly="true" /></td>	
					<td><label>业绩率 </label></td>
					<td><form:input type="text" path="agentRate" class="textbox"
								readonly="true" /></td>	
							<td><label>最新欠款 </label></td>
						<td><form:input type="text" path="caseLastMoney"  money='true' class="textbox"
								readonly="true" /></td>	
							<td><label>欠款更新日期 </label></td>
						<td><form:input type="text" path="caseLastDate"  money='true' class="textbox"
								readonly="true" /></td>				
					
					</tr>
					<tr>
						<td><label>逾期金额 </label></td>
						<td><form:input type="text" path="overdueMoney"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
						<td><label>逾期天数 </label></td>
						<td><form:input type="text" path="overdueDays"
								class="textbox" readonly="true" /></td>
						<td><label>逾期账龄 </label></td>
						<td><form:input type="text" path="overdueAge" class="textbox"
								readonly="true" /></td>

		<!-- 				<td><label>外部减免</label></td>
						<td><form:input type="text" path="outDerate"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
						<td><label>内部减免</label></td>
						<td><form:input type="text" path="inDerate"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td> -->
					
					<td><label>下次跟进时间</label></td>
					<td><form:input type="text" path="nextFollowTime"
								class="textbox" readonly="true" /></td>
						
								
					</tr>
					<tr>
						
						<td><label>PTP</label></td>
						<td><form:input type="text" path="ptpMoney"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
						<td><label>CP</label></td>
						<td><form:input type="text" path="cpMoney"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
					<td><label>本金 </label></td>
						<td><form:input type="text" path="principal"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
						<td><label>剩余本金 </label></td>
						<td><form:input type="text" path="surplusPrincipal"
								class="textbox easyui-numberbox"
								data-options="min:0,precision:2" money='true' readonly="true" /></td>
					
					</tr>
					<tr>
							<td><label>案件区域-省</label></td>
						<td><form:input type="text" path="provinceName"
								class="textbox" readonly="true" /></td>
						<td><label>案件区域-市</label></td>
						<td><form:input type="text" path="cityName" class="textbox"
								readonly="true" /></td>
						<td><label>最后通电</label></td>
						<td><form:input type="text" path="lastPhone" class="textbox"
								readonly="true" /></td>
						<td><label>最后外访</label></td>
						<td><form:input type="text" path="lastVisit" class="textbox"
								readonly="true" /></td>
					
					</tr>
					
					<c:if test="${caseDetailVo.templateType ==3}">
							<tr>
								<td><label>经销商</label></td>
								<td><form:input type="text" path="dealer" class="textbox"
										readonly="true" /></td>
								<td><label>价格</label></td>
								<td><form:input type="text" path="price" class="textbox"
										readonly="true" /></td>
								<td><label>车牌号</label></td>
								<td><form:input type="text" path="liceNo" class="textbox"
										readonly="true" /></td>
								<td><label>品牌</label></td>
								<td><form:input type="text" path="brand" class="textbox"
										readonly="true" /></td>
							</tr>
							<tr>
								<td><label>型号</label></td>
								<td><form:input type="text" path="number" class="textbox"
										readonly="true" /></td>
								<td><label>车架号</label></td>
								<td><form:input type="text" path="vinNo" class="textbox"
										readonly="true" /></td>
								<td><label>发动机号</label></td>
								<td><form:input type="text" path="engineNo" class="textbox"
										readonly="true" /></td>
								<td><label>&nbsp;</label></td>
								<td></td>
							</tr>
					</c:if>
					<tr>
						<td valign="top"
							style='margin-top: 0px; background-color: #efefef'><label>备注</label></td>
						
						<td  colspan="7">
						<div style='text-align: left; margin-left: 0px; height:100px; margin-top: 0px;word-break:break-all;padding:5px;overflow-y:scroll;'>${caseDetailVo.remark1 }</div>
						</td>
					</tr>
					
						<c:if test="${caseDetailVo.templateType ==3}">
							<tr>
								<td valign="top"
									style='margin-top: 0px; background-color: #efefef'><label>备注2</label></td>
								<td colspan="7" style="margin-left: 0px; margin-top: 0px"><form:textarea
										path="remark2"
										style='resize: none; outline: none; border: 0px; padding-top: 5px; float: left;'
										readonly='true' cols="170" rows="5"></form:textarea></td>
							</tr>
							<tr>
								<td valign="top"
									style='margin-top: 0px; background-color: #efefef'><label>客户简介</label></td>
								<td colspan="7" style="margin-left: 0px; margin-top: 0px"><form:textarea
										path="cusIntroduc"
										style='resize: none; outline: none; border: 0px; padding-top: 5px; float: left;'
										readonly='true' cols="170" rows="5"></form:textarea></td>
							</tr>
						</c:if>
					
					<tr>
						<td valign="top"><label style="height: 150px;"> <a
								class="easyui-linkbutton" data-options=""
								onclick="javascript:showCommentDialog()">[添加]</a> <span>评语
							</span>
						</label></td>
						<td colspan="7"><iframe id='commentframe'
								style="border: solid 0px #7ec8ea; margin: 0px; padding: 0px"
								src="${ctx}/collection/comment/index?caseId=${caseId}"
								width="100%" height="100%"></iframe></td>
					</tr>
				</table>
			</div>
			<!-- 其他信息 -->
			<div class="otherInfo" id="otherInfo">
				<div class="easyui-panel" title="其他信息"
					data-options="border:false,noheader:false, collapsible:true,collapsed:true">
					<table>
						<tr>
							<td><label>信贷分类 </label></td>
							<td><form:input type="text" path="creditId" class="textbox"
									readonly="true" /></td>
							<td><label>贷款截止日</label></td>
							<td><form:input type="text" path="loanEnddate"
									class="textbox" readonly="true" /></td>
							<td><label>每月还款金额 </label></td>
							<td><form:input type="text" path="monthRepayment"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>应还期数 </label></td>
							<td><form:input type="text" path="duePeriods"
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label>逾期期数 </label></td>
							<td><form:input type="text" path="overduePeriods"
									class="textbox" readonly="true" /></td>
							<td><label>已还期数 </label></td>
							<td><form:input type="text" path="repaymentPeriods"
									class="textbox" readonly="true" /></td>
							<td><label>贷款利率 </label></td>
							<td><form:input type="text" path="loanRate" class="textbox"
									readonly="true" /></td>
							<td><label>欠息余额 </label></td>
							<td><form:input type="text" path="interestMoney"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>

						<tr>

							<td><label>罚息参照 </label></td>
							<td><form:input type="text" path="penaltyReference"
									class="textbox" readonly="true" /></td>
							<td><label>复息参照 </label></td>
							<td><form:input type="text" path="compoundInterestReference"
									class="textbox" readonly="true" /></td>
							<td><label>签约金额 </label></td>
							<td><form:input type="text" path="contractMoney"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>咨询费 </label></td>
							<td><form:input type="text" path="consultFee"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>
						<tr>

							<td><label>审核费 </label></td>
							<td><form:input type="text" path="auditFee"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>服务费 </label></td>
							<td><form:input type="text" path="serviceFee"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>顾问费 </label></td>
							<td><form:input type="text" path="adviserFee"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>费用总和 </label></td>
							<td><form:input type="text" path="feeTotal"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>
						<tr>
							<td><label>放款金额 </label></td>
							<td><form:input type="text" path="actualLoanMoney"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>违约金 </label></td>
							<td><form:input type="text" path="breach"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>日罚息 </label></td>
							<td><form:input type="text" path="penaltyDays"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>历史余留 </label></td>
							<td><form:input type="text" path="remainHistory"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>
						<tr>
							<td><label>贷款金额 </label></td>
							<td><form:input type="text" path="loanMoney"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>开户行 </label></td>
							<td><form:input type="text" path='bankAccount'
									class="textbox" readonly="true" /></td>
							<td><label>卡号 </label></td>
							<td><form:input type="text" path="caseCard" class="textbox"
									readonly="true" /></td>
							<td><label>滞纳金</label></td>
							<td><form:input type="text" path='lateFee' class="textbox"
									 money='true' readonly="true" /></td>
						</tr>
						<tr>
							<td><label>最后还款日</label></td>
							<td><form:input type="text" path="initialRepay"
									class="textbox" readonly="true" /></td>
							<td><label>逾期借款 </label></td>
							<td><form:input type="text" path="overdueLoan"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>逾期本金 </label></td>
							<td><form:input type="text" path="overduePrincipal"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>逾期本金余额 </label></td>
							<td><form:input type="text" path="overduePrincipalBalance"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>
						<tr>
							<td><label>逾期利息 </label></td>
							<td><form:input type="text" path="overdueInterest"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>逾期罚息 </label></td>
							<td><form:input type="text" path="overduePenalty"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>逾期复利 </label></td>
							<td><form:input type="text" path="overdueCompound"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>曾逾期次数 </label></td>
							<td><form:input type="text" path="onceOverduePeriods"
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label>待还本金 </label></td>
							<td><form:input type="text" path="stayPrincipal"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>待还期数 </label></td>
							<td><form:input type="text" path="stayPeriods"
									class="textbox" readonly="true" /></td>
							<td><label>待还利息 </label></td>
							<td><form:input type="text" path="stayInterest"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>最后还款金额 </label></td>
							<td><form:input type="text" path="lastRepayment"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
						</tr>
						<tr>
							<td><label>逾期开始日期 </label></td>
							<td><form:input type="text" path="overdueDate"
									class="textbox" readonly="true" /></td>
							<td><label>待还管理费 </label></td>
							<td><form:input type="text" path="stayExpense"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>账户余额 </label></td>
							<td><form:input type="text" path="accountMoney"
									class="textbox easyui-numberbox"
									data-options="min:0,precision:2" money='true' readonly="true" /></td>
							<td><label>扣款账号银行代码</label></td>
							<td><form:input type="text" path='debitBankCode'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label>扣款账号银行名称</label></td>
							<td><form:input type="text" path='debitBankName'
									class="textbox" readonly="true" /></td>
							<td><label>案人房产类型</label></td>
							<td><form:input type="text" path='caseHouseId'
									class="textbox" readonly="true" /></td>
							<td><label>案人部门</label></td>
							<td><form:input type="text" path='caseDepartment'
									class="textbox" readonly="true" /></td>
							<td><label>案人职位</label></td>
							<td><a target="_blank" style="text-decoration:underline;cursor:pointer" onclick="casePosition()" >
						<font color=blue>${caseDetailVo.casePosition}</font></a></td>
								
						</tr>
						<tr>
							<td><label>职位级别</label></td>
							<td><form:input type="text" path='casePositionLevel'
									class="textbox" readonly="true" /></td>
							<td><label> 是否核销</label></td>
							<td><form:input type="text" path='isVerify' class="textbox"
									readonly="true" /></td>
							<td><label> 核销日期</label></td>
							<td><form:input type="text" path='verifyDate'
									class="textbox" readonly="true" /></td>
							<td><label> 还款方式</label></td>
							<td><form:input type="text" path='repaymentType'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 协议编号</label></td>
							<td><form:input type="text" path='protocolNo'
									class="textbox" readonly="true" /></td>
							<td><label> 放款所属地</label></td>
							<td><form:input type="text" path='loanArea' class="textbox"
									readonly="true" /></td>
							<td><label> 押品编号</label></td>
							<td><form:input type="text" path='collateralNo'
									class="textbox" readonly="true" /></td>
							<td><label> 押品类型</label></td>
							<td><form:input type="text" path='collateralId'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 押品名称</label></td>
							<td><form:input type="text" path='collateralName'
									class="textbox" readonly="true" /></td>
							<td><label> 押品地址</label></td>
							<td><form:input type="text" path='collateralAddress'
									class="textbox" readonly="true" /></td>
							<td><label> 建筑面积合计</label></td>
							<td><form:input type="text" path='totalConstruc'
									class="textbox" readonly="true" /></td>
							<td><label> 押品评估值</label></td>
							<td><form:input type="text" path='collateralEvalua'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 根额度编号</label></td>
							<td><form:input type="text" path='quotaNo' class="textbox"
									readonly="true" /></td>
							<td><label> (根额度)主办客户经理</label></td>
							<td><form:input type="text" path='quotaManager1'
									class="textbox" readonly="true" /></td>
							<td><label> 客户经理电话</label></td>
							<td><form:input type="text" path='managerPhone'
									class="textbox" readonly="true" /></td>
							<td><label> (根额度)协办客户经理</label></td>
							<td><form:input type="text" path='quotaManager2'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 大区名称</label></td>
							<td><form:input type="text" path='regione' class="textbox"
									readonly="true" /></td>
							<td><label> 分中心名称</label></td>
							<td><form:input type="text" path='subCenter' class="textbox"
									readonly="true" /></td>
							<td><label> 营业部名称</label></td>
							<td><form:input type="text" path='salesDep' class="textbox"
									readonly="true" /></td>
							<td><label> 账号</label></td>
							<td><form:input type="text" path='accountNo' class="textbox"
									readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 标ID</label></td>
							<td><form:input type="text" path='markId' class="textbox"
									readonly="true" /></td>
							<td><label> 用户名</label></td>
							<td><form:input type="text" path='userName' class="textbox"
									readonly="true" /></td>
							<td><label> 贷款合同号</label></td>
							<td><form:input type="text" path='loanContract'
									class="textbox" readonly="true" /></td>
							<td><label> 申请单号</label></td>
							<td><form:input type="text" path='caseAppNo' class="textbox"
									readonly="true" /></td>
						</tr>
						<tr>
							<td><label> 社保电脑号</label></td>
							<td><form:input type="text" path='socialSecurityNo'
									class="textbox" readonly="true" /></td>
							<td><label> 客户编号</label></td>
							<td><form:input type="text" path='cusNo' class="textbox"
									readonly="true" /></td>
							<td><label> 客户号</label></td>
							<td><form:input type="text" path='userId' class="textbox"
									readonly="true" /></td>
								<td><label>催收分类</label></td>
							<td><form:input type="text" path='collecType'
									class="textbox" readonly="true" /></td>
						</tr>


						<tr>
							<td><label>开卡日</label></td>
							<td><form:input type="text" path='startCardDate'
									class="textbox" readonly="true" /></td>
							<td><label>停卡日</label></td>
							<td><form:input type="text" path='stopCardDate'
									class="textbox" readonly="true" /></td>
							<td><label>信用额度</label></td>
							<td><form:input type="text" path='creditLimit'
									class="textbox" money='true' readonly="true" /></td>
							<td><label>生日</label></td>
							<td><form:input type="text" path='caseBirthday'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label>商品</label></td>
							<td><form:input type="text" path='commodity' class="textbox"
									readonly="true" /></td>
							<td><label>社保卡号</label></td>
							<td><form:input type="text" path='socialCardNo'
									class="textbox" readonly="true" /></td>
							<td><label>保单到期日</label></td>
							<td><form:input type="text" path='policyExpire'
									class="textbox" readonly="true" /></td>
							<td><label>还款日</label></td>
							<td><form:input type="text" path='repayDate' class="textbox"
									readonly="true" /></td>

						</tr>
						<tr>
							<td><label>催收小结</label></td>
							<td><form:input type="text" path='collecRemark'
									class="textbox" readonly="true" /></td>
							<td><label>邮箱</label></td>
							<td><form:input type="text" path='email' class="textbox"
									readonly="true" /></td>
							<td><label>币种</label></td>
							<td><form:input type="text" path='currency' class="textbox"
									readonly="true" /></td>
							<td><label>原风控记录</label></td>
							<td><form:input type="text" path='oldCollecRecord'
									class="textbox" readonly="true" /></td>
						</tr>
						<tr>
							<td><label>还款期限</label></td>
							<td><form:input type="text" path='repaymentTerm'
									class="textbox" readonly="true" /></td>
						<td><label>账单日 </label></td>
						<td><form:input type="text" path="bill" class="textbox"
								readonly="true" /></td>
						<td><label>贷款日期 </label></td>
						<td><form:input type="text" path="loanStartdate"
								class="textbox" readonly="true" /></td> 
							
							
						</tr>
					</table>
				</div>
			</div>
			
			<div class="otherInfo">
				<div class="easyui-panel" title="历史备注"
					data-options="border:false,noheader:false, collapsible:true,collapsed:true">
				<table>
				
				
						<tr>
						<td valign="top"
							style='margin-top: 0px; background-color: #efefef'><label>历史备注</label></td>
						<td colspan="7" style="margin-left: 0px; margin-top: 0px"><form:textarea
								path="hisRemark"
								style='resize: none; outline: none; border: 0px; padding-top: 5px; float: left;'
								readonly='true' cols="200" rows="8"></form:textarea></td>
					</tr>
					</table>
				</div>
		</div>
	</div>
</form:form>