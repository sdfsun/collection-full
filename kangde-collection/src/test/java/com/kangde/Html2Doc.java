package com.kangde;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFOldDocument;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Html2Doc {
	
	private static String path = "C:/Users/Administrator/Desktop/test.txt";
	
	public static void main(String[] args) throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();  
		 params.put("${name}", "张三");
		new Html2Doc().htmlToWord2(params);
	}
	
	public void htmlToWord2(Map<String, Object> params) throws Exception {
		String body = FileUtils.readFileToString(new File(path),"UTF-8");
		body = body.replace("${name}", MapUtils.getString(params, "${name}",""));
		body = body.replace("${IDNo}", MapUtils.getString(params, "${IDNo}",""));
		body = body.replace("${comPho}", MapUtils.getString(params, "${comPho}",""));
		
        //拼一个标准的HTML格式文档
        String content = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head><body>" + body + "</body></html>";
        InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
        OutputStream os = new FileOutputStream("C:/Users/Administrator/Desktop/test.doc");
        this.inputStreamToWord(is, os);
     }
     
     /**
      * 把is写入到对应的word输出流os中
      * @param is
      * @param os
      * @throws IOException
      */
     private void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
        fs.close();
     }
     
}
