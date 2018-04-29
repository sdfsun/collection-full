<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
var content_kindeditor;
$(function() {
	content_kindeditor = KindEditor.create('#content_kindeditor', {
		width : '96%',
		height : '345px',
		minWidth:'650px',//默认最小值为"650px"
		resizeType:0,//禁止拖拽
		items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template','cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'table', 'hr',  'pagebreak', 'anchor', 'link', 'unlink' ],
		allowFileManager : true,
		afterCreate:function(){ //加载完成后改变皮肤
	        var color = $('.panel-header').css('background-color');
	        $('.ke-toolbar').css('background-color',color);
	    }
	});
	$('#show_illustrate').css('list-style-type','none').css("padding","0").css("margin","0");
	$('#show_illustrate2').css('list-style-type','none').css("padding","0").css("margin","0");
});
</script>
<div>
    <form id="_visit_form" method="post" class="dialog-form" novalidate>
	    <input type="hidden" name="id" value="${id }"/>
	    <c:if test="${!empty id }">
	    	<input type="hidden" name="_method" value="PUT"/>
	    </c:if>
		<div>
			<label style="width:60px;">模板名称 </label>
		    <input name="name" type="text" class="easyui-validatebox textbox"
		    	value="${model.name }"
				maxLength="100" data-options="required:true,missingMessage:'请输入模板名称.',validType:['minLength[1]','legalInput']">
			<label style="width:60px;">模板类型</label>
		    <input id="type" name="type"/>
		    <%-- <k:dictionary id="type" constName="TEMPLATE_TYPE" name="type" required="true" value="${model.type}"/> --%>
		</div>
        <table style="border: 0px;width: 100%;">
            <tr>
                <td style="width: 74%;"><textarea id="content_kindeditor" name="content">${model.content }</textarea></td>
                <td style="width: 25%;">
                	<div id="visitExplan" class="easyui-panel" style="height:355px;" title="取值说明">
                		<%--外访 --%>
                		<ul id="show_illustrate">
                			<li>&#36;{batchCode} = 批次号</li>                 			
							<li>&#36;{caseCode} = 案件序列号</li>               			
                			<li>&#36;{caseCard} = 卡号</li>
                			<li>&#36;{caseCardHideLast} = 卡号(隐藏后四位)</li>
                			<li>&#36;{caseCardHideMiddle} = 卡号(隐藏中四位)</li>
                			<li>&#36;{accountNo} = 账号</li>
                			<li>&#36;{caseFileNo} = 档案号</li>
                			<li>&#36;{caseMoney} = 委案金额</li>
                			<li>&#36;{caseDate} = 委案日期</li>
                			<li>&#36;{actualEndDate} = 退案日期</li>
                			<li>&#36;{loanStartdate} = 贷款日期</li>
                			<li>&#36;{principal} = 本金</li>
                			<li>&#36;{entrustName} = 委托方</li>
                			<li>&#36;{caseName} = 案人姓名</li>
                			<li>&#36;{caseSex} = 案人性别</li>
                			<li>&#36;{caseAge} = 案人年龄</li>
                			<li>&#36;{caseIsMarriage} = 婚否</li>
                			<li>&#36;{companyName} = 案人单位</li>							
							<li>&#36;{companyAddress} = 单位地址</li>
							<li>&#36;{companyPhone} = 单位号码</li>
							<li>&#36;{companyZipcode} = 单位邮编</li>
							<li>&#36;{familyAddress} = 家庭地址</li>
							<li>&#36;{familyPhone} = 家庭号码</li>
							<li>&#36;{familyZipcode} = 家庭邮编</li>
							<li>&#36;{bill} = 账单日</li>
							<li>&#36;{lastRepayment} = 最后还款额</li>
							<li>&#36;{surplusPrincipal} = 剩余本金</li>
							<li>&#36;{repaymentPeriods} = 已还期数</li>
							<li>&#36;{monthRepayment} = 每月还款</li>
							<li>&#36;{overduePenalty} = 逾期罚息</li>
							<li>&#36;{caseAppNo} = 申请单号</li>
							<li>&#36;{collecStateId} = 催收状态</li>
							<li>&#36;{domicile} = 户籍地址</li>
							<li>&#36;{mobile1} = 本人手机1</li>
                			<li>&#36;{mobile2} = 本人手机2</li>
                			<li>&#36;{caseNum} = 证件号</li>
                			<li>&#36;{caseNumId} = 证件类型</li>
                			<li>&#36;{caseNumHideLast} = 证件号(隐藏后四位)</li>
                			<li>&#36;{caseNumHideMiddle} = 证件号(隐藏中四位)</li>
                			<li>&#36;{overdueAge} = 逾期账龄</li>
                			<li>&#36;{overdueDays} = 逾期天数</li>
                			<li>&#36;{overduePeriods} = 逾期期数</li>
                			<li>&#36;{commodity} = 商品</li>
                			<li>&#36;{userName} = 风控员</li>
							<!-- <li>&#36;{caseAssignedPhone} = 风控员手机号码</li> -->
							<li>&#36;{collateralNo} = 押品编号</li>
                			<li>&#36;{collateralId} = 押品类型</li>
                			<li>&#36;{collateralName} = 押品名称</li>
                			<li>&#36;{collateralAddress} = 押品地址</li>
                			<li>&#36;{name} = 对象姓名</li>
                			<li>&#36;{age} = 对象年龄</li>
                			<li>&#36;{sex} = 对象性别</li>
                			<li>&#36;{oName} = 外访省份</li>
                			<li>&#36;{tName} = 外访城市</li>
                			<li>&#36;{sName} = 外访区县</li>
                			<li>&#36;{reasonTypeId} = 外访原因</li>
                			<li>&#36;{require} = 外访要求</li>
                			<li>&#36;{visitAddress} = 外访地址</li>
                			<li>&#36;{applyTime} = 申请时间</li>
                			<li>&#36;{actualVisitTime} = 实际外访日期</li>
                			<li>&#36;{visitUser} = 外访员</li>
                			<li>&#36;{remark} = 外访备注</li>
                			<li>&#36;{paidNum} = 还款金额</li>
                			<li>&#36;{require} = 外访目标</li>
                		</ul>
                		<%--信函 --%>
                		<ul id="show_illustrate2">
                			<li>&#36;{entrustName} = 委托方</li>
                			<li>&#36;{caseCode} = 案件序列号</li>
                			<li>&#36;{caseCard} = 卡号</li>
                			<li>&#36;{caseCardHideLast} = 卡号(隐藏后四位)</li>
                			<li>&#36;{caseCardHideMiddle} = 卡号(隐藏中四位)</li>
							<li>&#36;{caseAssignedName} = 风控员</li>
							<li>&#36;{caseAssignedPhone} = 风控员手机号码</li>
                			<li>&#36;{caseNum} = 证件号</li>
                			<li>&#36;{caseNumId} = 证件类型</li>
                			<li>&#36;{caseNumHideLast} = 证件号(隐藏后四位)</li>
                			<li>&#36;{caseNumHideMiddle} = 证件号(隐藏中四位)</li>
                			<li>&#36;{caseApplyAddress} = 信函地址</li>
							<li>&#36;{companyName} = 案人单位</li>							
							<li>&#36;{companyAddress} = 单位地址</li>
							<li>&#36;{companyPhone} = 单位号码</li>
							<li>&#36;{companyZipcode} = 单位邮编</li>
							<li>&#36;{familyAddress} = 家庭地址</li>
							<li>&#36;{familyPhone} = 家庭号码</li>
							<li>&#36;{familyZipcode} = 家庭邮编</li>
                			<li>&#36;{caseName} = 姓名</li>
                			<li>&#36;{caseSex} = 性别</li>
                			<li>&#36;{caseAge} = 年龄</li>
                			<li>&#36;{caseIsMarriage} = 婚否</li>
							<li>&#36;{domicile} = 户籍地址</li>
							<li>&#36;{mobile1} = 本人手机1</li>
                			<li>&#36;{mobile2} = 本人手机2</li>
                			<li>&#36;{caseMoney} = 委案金额</li>
                			<li>&#36;{caseDate} = 委案日期</li>
                			<li>&#36;{actualEndDate} = 退案日期</li>
                			<li>&#36;{overdueDate} = 逾期开始日期</li>
                			<li>&#36;{overdueDays} = 逾期天数</li>
                			<li>&#36;{overduePeriods} = 逾期期数</li>
                			<li>&#36;{overdueAge} = 逾期账龄</li>
                			<li>&#36;{collateralNo} = 押品编号</li>
                			<li>&#36;{collateralId} = 押品类型</li>
                			<li>&#36;{collateralName} = 押品名称</li>
                			<li>&#36;{collateralAddress} = 押品地址</li>
                			<li>&#36;{collateralEvalua} = 押品评估值</li>
                			<li>&#36;{year} = 年(当前日期)</li>
							<li>&#36;{month} = 月(当前日期)</li>
							<li>&#36;{day} = 日(当前日期)</li>
							<li>&#36;{accountNo} = 账号</li>
							<li>&#36;{commodity} = 商品</li>
							<li>&#36;{caseFileNo} = 档案号</li>
							<li>&#36;{loanStartdate} = 贷款日期</li>
							<li>&#36;{debitBankName} = 扣款银行名称</li>
							<li>&#36;{principal} = 本金</li>
							<li>&#36;{monthRepayment} = 每月还款</li>
							<li>&#36;{loanEnddate} = 贷款截止日期</li>
							<li>&#36;{bankAccount} = 开户行</li>
							<li>&#36;{caseAppNo} = 申请单号</li>
                		</ul>
                	</div>
                </td>
            </tr>
        </table>
	</form>
</div>