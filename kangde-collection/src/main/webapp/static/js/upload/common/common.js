	function uploadImage() {
		var applyId = $('#applyId').val();
		var productTypeId = $('input[name=productTypeId]').val();
		if (applyId == "" || applyId == undefined) {
			$.messager.alert('提示', '进件号为空');
		}else if (productTypeId == "" || productTypeId == undefined) {
			$.messager.alert('提示', '请选择产品类型');
		} else {
			var title = "上传影像";
			var href = ctx+"/upload/luru?applyId="+applyId+"&optype=7&productTypeId="+productTypeId;
			window.parent.addNewTab(title, href);
		}
	}
	
	function uploadImageNoPid() {
		var applyId = $('#applyId').val();
		var productTypeId = $('input[name=productTypeId]').val();
		if (applyId == "" || applyId == undefined) {
			$.messager.alert('提示', '进件号为空');
		} else {
			var title = "上传影像";
			var href = ctx+"/upload/luru?applyId="+applyId+"&optype=7&system=shenpi";
			window.parent.addNewTab(title, href);
		}
	}
	
	//查看影像
	function viewImage() {
		var applyId = $('#applyId').val();
		var productTypeId = $('input[name=productTypeId]').val();
		if (applyId == "" || applyId == undefined) {
			$.messager.alert('提示', '进件号为空');
			return;
		}
		
		
		if (productTypeId == "" || productTypeId == undefined) {
			productTypeId= $('#applyProductTypeId').val(); 
			if (productTypeId == "" || productTypeId == undefined) {
				$.messager.alert('提示', '产品类型为空');
				return;
			}
		} 
			var title = "查看影像";
			var href = ctx+"/upload/luru?applyId="+applyId+"&optype=1&productTypeId="+productTypeId;
			window.parent.addNewTab(title, href);
		
	}
	