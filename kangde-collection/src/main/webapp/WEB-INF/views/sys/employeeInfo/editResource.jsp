<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
	    loadTree();
	});
	
	//加载资源
	function loadTree(){
		$('#resourceIds').tree({    
			url:ctx+'/sys/resource/parentResource',
			onlyLeafCheck:false,
			cascadeCheck:false,
			checkbox:true,
			lines:true,
			onLoadSuccess:function(){
				leodResourceIds();
				$('#resourceIds').tree("options").cascadeCheck=true;
				$('#resourceIds').tree('expandAll');
			}
		}); 
	}
	
	//加载角色关联资源
	function leodResourceIds(){
		$.ajax({
		   url: ctx+"/sys/employeeInfo/getResourceIds",
		   type: "POST",
		   dataType:'json',
		   async: false,
		   data: {"userId": '${userId}'},
		   success: function(data){
		     var arr = data.obj;
		  	 ids = data.obj;
    	   	 for(var i=0;i<arr.length;i++){
    	   		var node = $('#resourceIds').tree('find', arr[i]);
    	   		if('open'==node.state){//不是父元素
    	   			$('#resourceIds').tree('check', node.target);
    	   		}
    	   	 }
		   }
		 });
	}
</script>
<div>
	<form id="user_resource_form" class="dialog-form" method="post" novalidate>
		<input type="hidden" name="userId" value="${userId}"/><br>
	    <ul name="resourceIds" id="resourceIds"></ul>
	</form>
</div>