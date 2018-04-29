<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<jsp:include page="script.jsp"></jsp:include>

<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="noheader:true,region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
		style="padding: 0px; height: 35px; width: 100%; margin-top:3px;">
        <form id="_search_form" style="padding: 5px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色名称 &nbsp;<input type="text" class="easyui-validatebox textbox eu-input" name="name"
                              onkeydown="if(event.keyCode==13)search()"  maxLength="25" style="width: 160px" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" href="#"
						data-options="width:75,height:23,onClick:search"><span
							style="color: white;">查&nbsp;&nbsp;询</span></a> <a
						class="easyui-linkbutton" href="#"
						data-options="width:80,height:23"
						onclick="javascript:_search_form.form('reset');"><span
							style="color: white;">重置查询</span></a>
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="_datagrid">
       		<thead>
	            <tr>
	            	<th data-options="field:'id',checkbox:true"></th>
	            	<th data-options="field:'name',width:200">角色名称</th>
	                <th data-options="field:'roleCode',width:200">角色编码</th>
	                <th data-options="field:'description',width:200">角色描述</th>
	                <th data-options="field:'createTime',width:150,align:'left',formatter:function(value, row, index){
                		if(value){
                			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
                		}
                		return value;
                	}">创建时间</th>
	                <th data-options="field:'modifyTime',width:150,align:'left',formatter:function(value, row, index){
                		if(value){
                			return $.formatDate(value,'yyyy-MM-dd HH:mm:ss');
                		}
                		return value;
                	}">修改时间</th>
	            </tr>
       		</thead>
        </table>
        <%--Grid按钮--%>
	    <div id="_toolbar" style="display: none;">
			<shiro:hasPermission name="role:save">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-xinzeng'" onclick="showDialog()">新增</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:update">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-bianji'" onclick="edit()">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:delete">
			<a class="easyui-linkbutton" data-options="iconCls:'easyui-icon-shanchu'" onclick="del()">删除</a>
			</shiro:hasPermission>
		</div>
    </div>
</div>


   