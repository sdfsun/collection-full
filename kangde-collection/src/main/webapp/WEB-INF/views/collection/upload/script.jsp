<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script charset="utf-8">
	var baseUrl = ctx + "/collection/upload";
	var downloadUrl = ctx + "/collection/download/download?id=";
	function toplupload() {
		
		var files = [];
		var errors = [];
		if ($("#uploader")) {
			//init the uploader begin
			$("#uploader")
					.pluploadQueue(
							{
								runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
								url : baseUrl + "/execute",
								unique_names : false,
								rename : true,
								init : {
									FileUploaded : function(uploader, file,
											response) {
										if (response.response) {
											var rs = JSON
													.parse(response.response);
										}
									},
									UploadComplete : function(uploader, fs) {
										//var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
										//$.messager.alert("提示", "上传完成！共"+fs.length+"个。成功"+files.length+e);
										toplupload();
										$.messager.alert("提示", "上传完成");
										this.refresh();
										query();

									},
									FilesAdded : function(up, files) {

									}

								},
								chunk_size : '10mb',
								rename : true,
								dragdrop : true,
								multipart_params : {
									businessId : "${businessId}",
									businessType : "${businessType}"
								},
								filters : {
									max_file_size : '9999mb',
									mime_types : [ {
										title : "案件资料",
										extensions : "jpg,gif,png,MP3,mp3,WAV,wav,WMA,wma,RA,ra,MIDI,midi,OGG,ogg,APE,ape,FLAC,flac,AAC,acc,pdf,mp4,MP4,mov,MOV,m4a,M4A,zip,ZIP,rar,RAR,7-Zip,7-zip,7-ZIP,MPEG,MPG,DAT,BMP,wav,AVI,PCX,MOV,TIFF,ASF,ra,rma,WMV,JPEG,wma,asf,NAVI,TGA,MID,3GP,EXIF,MIDI,REAL,VIDEO,FPX,RMI,MKV,SVG,XMI,FLV,PSD,OGG,F4V,CDR,vqf,tvq,RMVB,PCD,mod,WebM,DXF,ape,HDDVD,UFO,aiff,qsv,EPS,aiff,qsv,EPSau,AI,PNG,HDRI,RAW"
									} ]
								},
								// Resize images on clientside if we can         
								//	resize: {             
								//		width : 200,              
								//		height : 200,              
								//		quality : 90,             
								//		crop: true 
								// crop to exact dimensions         
								//	},             // Flash settings         
								flash_swf_url : ctxStatic
										+ '/js/upload/js/Moxie.swf',
								silverlight_xap_url : ctxStatic
										+ '/js/upload/js/Moxie.xap'
							});
			//init the uploader end
		}

	}

	function upload() {
		$('#frmUpload').form('submit', {
			url : baseUrl + '/submit',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(data) {
				alert(data);
			}
		});

	}

	function query() {
		$.ajax({
			type : 'post',
			url : baseUrl
					+ "/queryAttachmentsByBusinessId?businessId=${businessId}",
			dataType : 'json',
			success : function(json) {
				if (json.state == 'false') {
					$.messager.alert("提示", json.msg);
					return;
				}
				fillData(json);
			}
		});
	}

	function fillData(json) {
		var docNum = json.data.docNum;
		var action="${action}";
		if(docNum=='0' && action=='view'){
			$.messager.alert("提示", "没有附件");
		}
		var uuu = json.data.docIp;
		var content = '';
		for ( var p in json.data.attach) {
			var data = json.data.attach[p];
			if (p == 'IMAGE' && data.length > 0) {
				content += showImage(data, uuu);
			} else if (p == 'AUDIO' && data.length > 0) {
				content += showAudio(data, uuu);
			} else if (p == 'VIDEO' && data.length > 0) {
				content += showVideo(data, uuu);
			} else if (p == 'PDF' && data.length > 0) {
				content += showPDF(data, uuu);
			} else if (p == 'ZIP' && data.length > 0) {
				content += showZIP(data, uuu);
			} else if (p == 'OTHER' && data.length > 0) {
				content += showOTHER(data, uuu);
			}
		}

		$("#fileContent").html(content);
	}

	function showImage(data, uuu) {
	
		var html = '<div  class="itemstyle">' + '影像资料' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
		
			html += "<div style='float:left; margin:10px 10px;'><a class='fancybox-buttons' style='' data-fancybox-group='button' href='"+uuu+item.path+
						"'><img style='padding:2px; width:86px; height:86px; border:1px solid #CCC'  name='item' src='"+uuu+item.path+"' title='"+item.name+"' file_mid_name='"+item.path+
						"'/></a><div style='margin-left:0px;overflow:hidden;width:86px;white-space: nowrap;text-overflow:ellipsis;' title='"+item.name+"'><input id='check"+item.id+
						"' type='checkbox' name='cekitems' value='"+item.id+"'/><label for='check"+item.id+"'>"
					+ item.name + "</label></div></div>";
		}
		;
		return html;
	};

	function showAudio(data, uuu) {

		var html = '<div style="clear:both;margin:20px 5px 2px 15px; font-size: 14px;background: #EFF7FA;">'
				+ '音频资料' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			html += "<div class='audio'><audio src='"+uuu+item.path+"' controls='controls'>您的浏览器不支持此种音频格式</audio>"
					+ "<span style='font-size:18'><input id='check"+item.id+
					"' type='checkbox' name='cekitems' value='"+item.id+"'/>"
					+"<label for='check"+item.id+"'>"+item.name+"</label>" +'<a style="margin-left:20px;" href="'+downloadUrl+item.id+'"><img src="'+ctxStatic+'/img/icon/download.png"/></a>'+ "</span>" + "</div>";
		}
		return html;
	};

	function showVideo(data, uuu) {
		var html = '<div style="clear:both;margin:20px 5px 2px 15px; font-size: 14px;background: #EFF7FA;">'
				+ '视频资料' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			html += "<div style='float:left; margin:10px 10px;'><video src='"+uuu+item.path+"' width=500 controls='controls'>您的浏览器不支持此种视频格式</video>"
					+ "<span style='font-size:18'><input id='check"+item.id+
					"' type='checkbox' name='cekitems' value='"+item.id+"'/>"
					+ "<label for='check"+item.id+"'>"+item.name+"</label>" +'<a style="margin-left:20px;" href="'+downloadUrl+item.id+'"><img src="'+ctxStatic+'/img/icon/download.png"/></a>'+ "</span>" + "</div>";
		}
		return html;
	};
	function showPDF(data, uuu) {
		var html = '<div style="clear:both;margin:20px 5px 2px 15px; font-size: 14px;background: #EFF7FA;">'
				+ 'PDF资料' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var url = uuu + item.path;
			html += "<div style='margin:10px 10px;'><input id='check"+item.id+
					"' type='checkbox' name='cekitems' value='"+item.id+"'/>"
					+ '<a style="color:blue;"  href="javascript:void(0)"  onclick=showPDFPage("'
					+ url + '")>' + item.name + '</a>' + '</div>';
		}
		return html;
	};

	function showZIP(data, uuu) {
		
		
		var html = '<div style="clear:both;margin:20px 5px 2px 15px; font-size: 14px;background: #EFF7FA;">'
				+ '压缩包资料' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var url = uuu + item.path;
			html += "<div style='margin:10px 10px; line-height:30px; border-bottom:2px solid #EFEFEF;'>	<input id='check"+item.id+
					"' type='checkbox' name='cekitems' value='"+item.id+"'/>"
					+"<label for='check"+item.id+"'>"+item.name+"</label>"
					 + '<a style="margin-left:20px;" href="'+downloadUrl+item.id+'"><img src="'+ctxStatic+'/img/icon/download.png"/></a></div>';
		}
		return html;
	};

	function showOTHER(data, uuu) {
		var html = '<div style="clear:both;margin:20px 5px 2px 15px; font-size: 14px;background: #EFF7FA;">'
			+ '其他' + '</div>';
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var url = uuu + item.path;
			html += "<div style='margin:10px 10px;'><input id='check"+item.id+
					"' type='checkbox' name='cekitems' value='"+item.id+"'/>"
					+ '<a style="color:blue;"  href="'+url+'">'
					+ item.name
					+ '</a>' + '</div>';
		}
		return html;
	};

	function showPDFPage(url) {
		var address = ctxStatic + '/js/pdfjs/generic/web/viewer.html?file='
				+ url;
		var content = '<iframe scrolling="no" frameborder="0"  src="' + address
				+ '" style="width:100%;height:100%;"></iframe>';

		//弹出对话窗口
		var _dialog = $('<div/>').dialog({
			title : '',
			top : 20,
			width : '80%',
			height : '90%',
			modal : true,
			maximizable : true,
			resizable : true,
			buttons : [ {
				text : '关闭',
				handler : function() {
					_dialog.dialog('destroy');
				}
			} ],
			content : content,
			onClose : function() {
				_dialog.dialog('destroy');
			}
		});

	}

	
	
	function viewImage(){
		$('.fancybox-buttons').fancybox({
			openEffect : 'none',
			closeEffect : 'none',
			prevEffect : 'none',
			nextEffect : 'none',
			closeBtn : false,

			helpers : {
				title : {
					type : 'inside'
				},
				buttons : {}
			},

			afterLoad : function() {
				this.title = 'Image ' + (this.index + 1) + ' of '
						+ this.group.length
						+ (this.title ? ' - ' + this.title : '');
			}
		});
	}
	function delAttachment(){
		var array = [];
		$('input[name=cekitems]:checked').each(function(i) {
			array.push($(this).val());
		})
		if (array.length == 0) {
			$.messager.alert("提示", "请选择要删除的资料");
			return;
		}

		$.messager.confirm('提示', '确定要删除吗？', function(r) {
			if (r) {
				$.ajax({
					type : 'post',
					url : baseUrl + "/del",
					data : {
						"ids" : array
					},
					dataType : 'json',
					success : function(data) {
						query();
					}
				});
			}
		});
}
</script>