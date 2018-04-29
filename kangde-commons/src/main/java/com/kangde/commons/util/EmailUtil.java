package com.kangde.commons.util;

import java.io.File;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.kangde.commons.CoreConst;

/***
 * 邮件发送工具类
 * 
 * @author lisuo
 *
 */
public abstract class EmailUtil {
	
	/** 日志 */
	private static Logger log = Logger.getLogger(EmailUtil.class);

	private static String hostName = CoreConst.getString("mail.server.host");
	private static Integer smtpPort = CoreConst.getInteger("mail.server.smtp.port");
	private static Boolean auth = CoreConst.getBoolean("mail.server.smtp.auth");
	private static Integer timeout = CoreConst.getInteger("mail.server.smtp.timeout");
	private static String username = CoreConst.getString("mail.server.username");
	private static String password = CoreConst.getString("mail.server.password");
	private static String nickname = CoreConst.getString("mail.server.nickname");

	// 根据配置文件创建邮件信息
	private static HtmlEmail buildEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setCharset(CoreConst.UTF_8.name());
		email.setHostName(hostName);
		email.setSmtpPort(smtpPort);
		email.setSSLOnConnect(auth);
		email.setSocketConnectionTimeout(timeout);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setFrom(username, nickname);
		email.setSocketConnectionTimeout(timeout);
		return email;
	}

	/**
	 * @param topic 主题
	 * @param body 内容
	 * @param sendTo 接收人
	 */
	public static boolean sendEmail(String topic, String body,String ... sendTo){
		try {
			HtmlEmail email = buildEmail();
			email.addTo(sendTo);
			email.setSubject(topic);
			email.setHtmlMsg(body);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			log.error(e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * param topic 主题
	 * @param body 内容
	 * @param sendTo 接收人
	 * @param file 附加文件
	 * @return
	 */
	public static boolean sendEmail(String topic, String body,String [] sendTo,File file){
		return sendEmail(topic, body, sendTo, new File[]{file});
	}
	
	/**
	 * 
	 * @param topic 主题
	 * @param body 内容
	 * @param sendTo 接收人
	 * @param file 附加文件
	 * @return
	 */
	public static boolean sendEmail(String topic, String body,String [] sendTo,String fileContent,String fileName){
		try {
			HtmlEmail email = buildEmail();
			email.addTo(sendTo);
			email.setSubject(topic);
			email.setHtmlMsg(body);
			ByteArrayDataSource ds = new ByteArrayDataSource(fileContent,"text/html");
			email.attach(ds, fileName,fileName);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return false;
		}
		return true;
	}
	
	
	/**
	 * @param topic 主题
	 * @param body 内容
	 * @param sendTo 接收人
	 * @param files 附加文件
	 * @return 发送结果
	 */
	public static boolean sendEmail(String topic, String body,String [] sendTo,File [] files){
		try {
			HtmlEmail email = buildEmail();
			email.addTo(sendTo);
			email.setSubject(topic);
			email.setHtmlMsg(body);
			if(ArrayUtils.isNotEmpty(files)){
				for(File file:files){
					email.attach(file);
				}
			}
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			log.error(e);
			return false;
		}
		return true;
	}
	
	

	/*public static void main(String[] args) throws Exception {
		sendEmail("来自JAVA", "Hello", "852666851@qq.com");
		//sendEmail("来自JAVA", "Hello", new String[]{"852666851@qq.com"}, new File("C:/Users/Administrator/Desktop/test.txt"));
		//sendEmail("来自JAVA", "Hello", new String[]{"852666851@qq.com"}, "java.lang.NullPointerException", "ExcelDetail.txt");
	}*/

}
