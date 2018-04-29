<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
</script>
<div>
    <form id="_form" class="dialog-form" method="post">
        <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version" />
        <!-- Restful 请求 -->
        <c:if test="${!empty id }">
       	<input type="hidden" name="_method" value="PUT" />
        </c:if>
        <input type="hidden"  name="id" />
    	<table>
    		<tr>
    			
    			<td>
	    			<div class="easyui-panel" title="权限分配" style="width: 300px;height: 380px;">
			            <ul name="resourceIds" id="resourceIds"></ul>
			        </div>
    			</td>
    			<td>
    			<br><br>
    				<div style="position: relative; top: -98px;">
			            <label style="text-align: right;">角色名称 </label>
			            <input name="name" type="text" class="easyui-validatebox textbox"
			                   maxLength="100" data-options="required:true,missingMessage:'请输入角色名称.',validType:['minLength[1]','legalInput']">
			        </div>
    				<div style="position: relative; top: -90px;">
			            <label style="vertical-align: top;text-align: right;">角色编码 </label>
			            <input name="roleCode" type="text" class="easyui-validatebox textbox"
			                   maxLength="100">
			        </div>
    				<div style="position: relative; top: -90px;">
			            <label style="vertical-align: top;text-align: right;">角色描述 </label>
			            <input name="description" class="easyui-textbox" data-options="multiline:true" style="height:160px;">
			        </div>
    			</td>
    		</tr>
    	</table>
        
    </form>
    <script type="text/javascript">
	    $(function() {
	        loadTree();
	    });
	
	    var roleTree;
	    //加载资源
	    function loadTree(){
	    	roleTree=$('#resourceIds').tree({    
	    		url:ctx+'/sys/resource/parentResource',
	    		onlyLeafCheck:false,
	    		cascadeCheck:false,
	    		checkbox:true,
	    		lines:true,
	    		onLoadSuccess:function(){
	    			leodResourceIds();
	    			$('#resourceIds').tree('expandAll');
	    		  $('#resourceIds').tree("options").cascadeCheck=true;
	    			
	    		}
	    	}); 
	    }
	    
	    //加载角色关联资源
	    function leodResourceIds(){
	    	var roleId = "${id}";
	    	if(roleId){//修改
	    		$.ajax({
	     		   url: ctx+"/sys/role/getResourceIds",
	     		   type: "POST",
	     		   dataType:'json',
	     		   async: false,
	     		   data: {"roleId": roleId},
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
	    }
	    
	</script>
    
</div>