<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
<form id="_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" id="id" name="id" value="${id}" /> 
		<input type="hidden" id="caseId" name="caseId" value="${caseId}" /> 
		<input type="hidden" name="state"/>
		<!-- 用户版本控制字段 version -->
		<input type="hidden" id="version" name="version" />
		<!-- Restful 请求 -->
        <c:if test="${!empty id }">
        	<input type="hidden" name="_method" value="PUT" />
        </c:if>
		<div>
			<label>实际外访时间 </label> 
			<input id="actualVisitTime"
				name="actualVisitTime"  class="easyui-datebox" style="width: 130px;" data-options="editable:false,readonly:false,required:true" /> 
		</div>
		<div>
			<label>地址是否有效</label>
			<input checked='checked' name="isEffective" type="radio" value="0" />是 
			<input name="isEffective" type="radio" value="1" />否
		</div>
		<div>
			<label>外访结果</label>
			<k:dictionary constName="VISIT_RESULT"  id='visitState' name="visitState"  width="130"  required="true"
					selectType="select" />
		</div>
		
		<div>
			<label>外访报告 </label>
			<input id="visitReport" name="visitReport" class="easyui-textbox"
				style="width: 350px; height: 100px" value="${visitReport }" maxlength="500"
				data-options="required:true,multiline:true,missingMessage:'请输入外访报告',validType:['length[1,500]']"
				 />
		</div>
		<br>
		<div>
			<label>外访附件 </label>
			<a href="javascript:void(0)" style="color:blue;" class="easyui-linkbutton" onclick="viewDoc()">点击上传附件</a>(支持上传图片,PDF,音频,视频,压缩包)
		</div>
		

		

	</form>

</div>