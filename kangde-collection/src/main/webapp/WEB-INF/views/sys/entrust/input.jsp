<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<div>
	<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="id" />
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
		<input type="hidden" name="_method" value="post" />
<div></div>
		<!-- 多选    字典？ -->
		<div> 
			<label>&nbsp;&nbsp;&nbsp;合同名称</label> 
			<input name="contractName" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入合同名称.',validType:['minLength[1]','legalInput']">
			<!-- <input name="caseTypeId" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入案件类型.',validType:['minLength[1]','legalInput']"> -->
                   &nbsp;&nbsp;
         </div>
		<div>
			<label>委托方</label>
			<!-- <input id="search_entrustId" name="id"/> -->
			<input name="name" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入委托方名称.',validType:['minLength[1]','legalInput']"> 
                   &nbsp;&nbsp;
        </div>
        <div>
			<label>简码</label> <input name="code" type="text" class="easyui-validatebox textbox"
                   maxLength="2" data-options="required:true,missingMessage:'请输入委托方简码.',validType:['minLength[2]','maxLength[2]','legalInput']">
		</div>
         <div>
            <label>&nbsp;&nbsp;&nbsp;备注</label>&nbsp;<input type="text" id="remark" name="remark"
					maxLength="255" class="easyui-textbox"
					data-options="missingMessage:'请输入备注...',validType:['minLength[1]']" />
			</div>

		
		<!-- <div>
			 &nbsp;&nbsp;
                   <label>产品名称:</label> 
			<k:dictionary id="productName" constName="PROD_NAME" width="260" 
							name="productName" required="true" value="1" multiple="true"/>
			<input id="productName" class="easyui-combobox" name="productName"   
    		data-options="valueField:'id',textField:'text',url:'get_data.php'" multiple/> -->
			<!-- <input name="productName" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:true,missingMessage:'请输入产品名称.',validType:['minLength[1]','legalInput']"> -->
		
			<%-- <label>委托方联系地址:</label> <input name="address" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委托方联系地址.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>委托方客服热线:</label> <input name="serviceHotline" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委托方客服热线.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>委托方对接人:</label> <input name="abutmentPerson" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委托方对接人.',validType:['minLength[1]','legalInput']">
		</div>
		<!-- 邮箱验证。。。 -->
		<div> 
			<label>对接邮箱:</label> <input name="abutmentEmail" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委托方客服热线.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>对接电话/传真:</label> <input name="abutmentPhone" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委托方对接人.',validType:['minLength[1]','legalInput']">
		</div>
		<!-- 委案频率  字典。。。。。。 -->
		<div> 
			<label>委案频率:</label> 
			<k:dictionary id="caseFrequency" constName="CYCLE" width="260" 
							name="caseFrequency" required="true" value="1" />
			<!-- <input name="caseFrequency" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入委案频率.',validType:['minLength[1]','legalInput']"> -->
                   &nbsp;&nbsp;
			<label>有效还款期段:</label> <input name="effecRepayPeriod" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入有效还款期段.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>风控名义:</label> <input name="collecNominal" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入风控名义.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>风控期限:</label> <input name="collecTerm" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入风控期限.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>留案要求:</label> <input name="stayRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入留案要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>退案要求:</label> <input name="backRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入退案要求.',validType:['minLength[1]','legalInput']">
		</div>
		<!-- 下拉  字典。。。。。。。 -->
		<div> 
			<label>报告周期:</label> 
			<k:dictionary id="reportPeriod" constName="CYCLE" width="260" 
							name="reportPeriod" required="true" value="1" />
			<!-- <input name="reportPeriod" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入报告周期.',validType:['minLength[1]','legalInput']"> -->
                   &nbsp;&nbsp;
			<label>结算周期:</label>
			<k:dictionary id="settlementPeriod" constName="CYCLE" width="260" 
							name="settlementPeriod" required="true" value="1" />
			 <!-- <input name="settlementPeriod" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入结算周期.',validType:['minLength[1]','legalInput']"> -->
		</div>
		
		<div> 
			<label>持卡人还款方式:</label> <input name="repaymentMethod" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入持卡人还款方式.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>折扣减免:</label> <input name="discountReduction" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入折扣减免.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>分期还款要求:</label> <input name="periodRepaymentRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入分期还款要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>如何销卡/销户:</label> <input name="cancelAccount" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入如何销卡/销户.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>源资取调要求:</label> <input name="resourceRetrieveRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入源资取调要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>催记提交要求:</label> <input name="collecSubmitRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入催记提交要求.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>投诉处理要求:</label> <input name="complaintHandleRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入投诉处理要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>录音提交要求:</label> <input name="tapeSubmitRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入录音提交要求.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>资料销毁要求:</label> <input name="dataDestrucRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入资料销毁要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>信函回收要求:</label> <input name="letterRecoveryRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入信函回收要求.',validType:['minLength[1]','legalInput']">
		</div>
		
		<div> 
			<label>报告/报表要求:</label> <input name="reportRequire" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入报告/报表要求.',validType:['minLength[1]','legalInput']">
                   &nbsp;&nbsp;
			<label>客户特别指示:</label> <input name="cusSpecialInstruc" type="text" class="easyui-validatebox textbox"
                   maxLength="100" data-options="required:false,missingMessage:'请输入客户特别指示.',validType:['minLength[1]','legalInput']">
		</div> --%>
		
	</form>
</div>