package com.kangde.collection.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.model.AttachmentModel;
import com.kangde.collection.service.AttachmentService;
import com.kangde.collection.util.PluploadUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.web.controller.SimpleController;

@Controller
@RequestMapping("collection/download")
public class DownloadController extends SimpleController {
	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String id) throws UnsupportedEncodingException  {    
	    	AttachmentModel attachmentModel=attachmentService.findById(id);
	        String path= PluploadUtil.root+attachmentModel.getPath();  
	        File file=new File(path);  
	        HttpHeaders headers = new HttpHeaders();    
	        String fileName=new String(attachmentModel.getName().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        try {
	        	if(!file.exists()){
	        		throw new ServiceException("文件不存在");
	        	}
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
				                                  headers, HttpStatus.CREATED);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}    
	    }    
}