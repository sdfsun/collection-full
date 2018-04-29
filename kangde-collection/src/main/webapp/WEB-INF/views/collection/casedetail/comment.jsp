<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.colorlist{
margin:0px 0px; padding:0px;text-align: left;
}
</style>
	<script>
	function cancel() {	
	
	 $('#commentdialog').dialog('close', true);
	}
	function createcomment() {
		var isValid = $('#comment_form').form('validate');
		if (!isValid) {
			return;
		}

		var content = $("#comment_content").textbox("getValue");
		var color = $("input:radio[name=caseCommentColor]:checked").val();
		$.ajax({
			type : "POST",
			url : ctx+'/collection/casedetail/saveCommentAndUpdateCaseColor',
			data : {
				ids : caseIds_comment,
				content : content,
				color : color,
				isBatch: isBatch
			},
			dataType : "json",
			async : false,
			success : function(data) {

				//渲染结果
				renderAjax(data, function(json) {
					if (json.code == '1') {
						$('#commentframe').attr('src',$('#commentframe').attr('src'));
						$('#_datagrid').datagrid("reload");
						cancel();
					}
				});
			}
		});
	}

	function renderAjax(data, successFun, showMessage) {
		var sms = true;
		if (showMessage != undefined && false == showMessage) {
			sms = false;
		}
		// 0:失败,1:成功,2:未登录或session过期,3:无权限 
		if (data) {
			if (typeof data == "string") {
				try {
					data = $.parseJSON(data);
				} catch (e) {
					eu.showAlertMsg(data, 'error');
					return;
				}
			}
			if (data.code == 0) {
				eu.showAlertMsg(data.msg, 'error');
			} else if (data.code == 1) {
				if (successFun) {
					successFun(data);
				}
				if (sms) {
					eu.showMsg(data.msg);//操作结果提示
				}
			} else if (data.code == 2) {
				eu.showAlertMsg('登录超时请重新登录', 'error');
			} else if (data.code == 3) {
				eu.showAlertMsg('没有对应的权限,如有疑问,请与管理员联系', 'error');
			} else {
				eu.showAlertMsg(data.msg, 'error');
			}
		}
	};

	
	</script>
<div id="commentdialog" class="easyui-dialog" title="新增评语"
		style="width: 600px; height: 270px;margin:0px; padding:0px"
		data-options="top:50,resizable:true,closed:true,modal:true,
		buttons : [ {
            text : '发布',
            handler : function() {
            	createcomment('${caseId }');
            }
        },{
            text : '取消',
            handler : function() {
                cancel();
            }
        }]
		">
			<div style="margin: 20px;text-align: left;">
				<div style='margin:0px;padding:0px'>
				
				<form id="comment_form" class="dialog-form" method="post">
					<div>
						<input id='comment_content' name="content" class="easyui-textbox"
							data-options="required:true,missingMessage:'请填写评语内容',validType:'maxLength[500]',multiline:true"
							style="width: 100%; height: 100px; border: 1px solid #D4D4D4;" />
					</div>
				</form>
				</div>

				<div class='colorlist'>
				<form:form  method="post" commandName="caseCommentVo">
				&nbsp;&nbsp;&nbsp;	<form:radiobuttons  htmlEscape="false" path="caseCommentColor"
						items="${commentColor}"  delimiter="&nbsp;" />
				</form:form>
				</div>
				
			</div>
	</div>