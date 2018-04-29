package com.kangde.collection.service;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;
import org.junit.Test;

public class FileTypeTest {
	@Test
	public void filetype() throws IOException {

		Tika tika = new Tika();

		System.out.println("pdf:" + tika.detect(new File("d://annotation-highlight.pdf")));
		System.out.println("pdf:" + tika.detect(new File("d://lidengwen.pdf")));
		System.out.println("rar:" + tika.detect(new File("d://svn.rar")));
		System.out.println("zip:" + tika.detect(new File("d://3.zip")));
		System.out.println("mp3:" + tika.detect(new File("d://老鼠爱大米.mp3")));
		System.out.println("mp3:" + tika.detect(new File("d://bbb.MP3")));
		System.out.println("wav:" + tika.detect(new File("d://2.wav")));
		System.out.println("mp4:" + tika.detect(new File("d://bicycle.mp4")));
		System.out.println("doc:" + tika.detect(new File("d://信用卡术语中英文对照.doc")));
		System.out.println("docx:" + tika.detect(new File("d://我的信息.docx")));
		System.out.println("xls:" + tika.detect(new File("d://20140915.xls")));
		System.out.println("xlsx:" + tika.detect(new File("d://员工档案.xlsx")));
		System.out.println("png:" + tika.detect(new File("d://ybr.png")));
		System.out.println("png:" + tika.detect(new File("d://1.png")));
		System.out.println("jpg:" + tika.detect(new File("d://2.jpg")));

	}
}
