<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">	
 function approvalLeaveInput(sum){
	var a;
	if(sum==1){//同意
		 a=baseUrl+'/approvalLeave';
	}else if(sum==2){
		 a=baseUrl+'/approvalLeaveNo';
	}
 $('#user_role_form').form("submit", {
	    	//表单提交地址
	  	  	url : a,
	        //表单提交数据之前回调函数
	        onSubmit: function(){
	        	$.messager.progress({
	                title : '提示信息！',
	                text : '数据处理中，请稍后....'
	            });
	        	//$(this).form('enableValidation');
	            var isValid = $(this).form('validate');
	            if (!isValid) {
	                $.messager.progress('close');
	            }
	            return isValid;
	        },
	        //表单成功提交回调函数
	        success: function(data){
  	        	 $.messager.progress('close');
  	            //渲染结果
  	            app.renderAjax(data,function(json){
  	            	editRole_dialog.dialog('destroy');//销毁对话框
  	          		app.reload(_datagridleave);//重新加载列表数据
  	            });
  	        }
	    });
}


 function approvalforgoInput(sum){
	var a;
	if(sum==1){//同意
		 a=baseUrl+'/approvalForgo';
	}else if(sum==2){
		 a=baseUrl+'/approvalForgoNo';
	}
 $('#user_role_form').form("submit", {
	    	//表单提交地址
	  	  	url : a,
	        //表单提交数据之前回调函数
	        onSubmit: function(){
	        	$.messager.progress({
	                title : '提示信息！',
	                text : '数据处理中，请稍后....'
	            });
	        	//$(this).form('enableValidation');
	            var isValid = $(this).form('validate');
	            if (!isValid) {
	                $.messager.progress('close');
	            }
	            return isValid;
	        },
	        //表单成功提交回调函数
	        success: function(data){
  	        	 $.messager.progress('close');
  	            //渲染结果
  	            app.renderAjax(data,function(json){
  	            	editRole_dialog.dialog('destroy');//销毁对话框
  	          		app.reload(_datagrid);//重新加载列表数据
  	            });
  	        }
	    });
}  
 $.extend($.fn.validatebox.defaults.rules, {    
	    maxLength: {    
	        validator: function(value, param){    
	            return value.length <= param[0];    
	        },    
	        message: '输入文字数量不可多于500！'   
	    }    
	});
</script>
<div>
	<form id="user_role_form" class="dialog-form" method="post" novalidate>
		<input type="hidden"  name="caseId" value="${caseId }"/>
		<div >
			<label >审批意见</label>
		<div style="text-align:center;">
			<input id="approveOpinion" name="approveOpinion" class="easyui-textbox" data-options="required:true,missingMessage:'请输入审批意见.',validType:'maxLength[500]',multiline:true"
			style="width:290px;height:150px" value="${approveOpinion }"/> 
		</div>
		</div>
		<!-- 外访和留案用的同一个页面，所以给的按钮不同  0是留案， 1是外访  -->
		<!-- i 是从 script传到后台   再由后台截取字符串的到的 -->
			<c:if test="${i=='0'}"> 
		 	<div style="text-align:center;">
         	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="approvalLeaveInput(1)">通过</a>
          	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="approvalLeaveInput(2)">不通过</a>
       		 </div>
       		  </c:if>
       		   	<c:if test="${i=='1'}"> 
		 	<div style="text-align:center;">
         	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="approvalforgoInput(1)">通过</a>
          	   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="approvalforgoInput(2)">不通过</a>
       		 </div>
       		  </c:if> 
	</form>
</div>