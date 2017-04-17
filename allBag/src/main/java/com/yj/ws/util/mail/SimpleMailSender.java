package com.yj.ws.util.mail;

import java.io.File;
import java.util.Date;    
import java.util.Properties;   

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;    
import javax.mail.BodyPart;    
import javax.mail.Message;    
import javax.mail.MessagingException;    
import javax.mail.Multipart;    
import javax.mail.Session;    
import javax.mail.Transport;    
import javax.mail.internet.InternetAddress;    
import javax.mail.internet.MimeBodyPart;    
import javax.mail.internet.MimeMessage;    
import javax.mail.internet.MimeMultipart; 

import org.apache.log4j.Logger;


/**
 * 简单邮件（不带附件的邮件）发送器   
 * @author LX
 *
 */
public class SimpleMailSender {
	
	private final static Logger log = Logger.getLogger(SimpleMailSender.class);
	
	 
	/**   
	  * 以文本格式发送邮件   
	  * @param mailInfo 待发送的邮件的信息   
	  */    
	    public static boolean sendTextMail(MailSenderInfo mailInfo) {    
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;    
	      Properties pro = mailInfo.getProperties();   
	      if (mailInfo.isValidate()) {    
	      // 如果需要身份认证，则创建一个密码验证器    
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
	      }   
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());    
	      // 设置邮件消息的主要内容    
	      String mailContent = mailInfo.getContent();    
	      mailMessage.setText(mailContent);    
	      // 发送邮件    
	      Transport.send(mailMessage);   
	      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      }    
	      return false;    
	    }    
	       
	    /**   
	      * 以HTML格式发送邮件   
	      * @param mailInfo 待发送的邮件信息   
	      */    
	    public static boolean sendHtmlMail(MailSenderInfo mailInfo){    
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;   
	      Properties pro = mailInfo.getProperties();   
	      //如果需要身份认证，则创建一个密码验证器     
	      if (mailInfo.isValidate()) {    
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());  
	      }    
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      // Message.RecipientType.TO属性表示接收者的类型为TO    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());    
	      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
	      Multipart mainPart = new MimeMultipart();    
	      // 创建一个包含HTML内容的MimeBodyPart    
	      BodyPart html = new MimeBodyPart();    
	      // 设置HTML内容    
	      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
	      mainPart.addBodyPart(html);    
	      // 将MiniMultipart对象设置为邮件内容    
	      mailMessage.setContent(mainPart);    
	      // 发送邮件    
	      Transport.send(mailMessage);    
	      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      }    
	      return false;    
	    }    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public static boolean sendTextAndPic(MailSenderInfo mailInfo) throws Exception {
	    	
	    	// 判断是否需要身份认证    
		      MyAuthenticator authenticator = null;   
		      Properties pro = mailInfo.getProperties();   
		      //如果需要身份认证，则创建一个密码验证器     
		      if (mailInfo.isValidate()) {    
		        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());  
		      }    
		      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
		      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);  
		      
		      Message mailMessage = new MimeMessage(sendMailSession);    
		      // 创建邮件发送者地址    
		      Address from = new InternetAddress(mailInfo.getFromAddress());    
		      // 设置邮件消息的发送者    
		      mailMessage.setFrom(from);    
		      // 创建邮件的接收者地址，并设置到邮件消息中    
		      Address to = new InternetAddress(mailInfo.getToAddress());    
		      // Message.RecipientType.TO属性表示接收者的类型为TO    
		      mailMessage.setRecipient(Message.RecipientType.TO,to);      //TO 收件    Message.RecipientType.CC 抄送    ，Message.RecipientType.BCC 密送;
		      mailMessage.setSubject(mailInfo.getSubject());    
		      // 设置邮件消息发送的时间    
		      mailMessage.setSentDate(new Date());  
		      
	    	 // 我就当这是一个消息包，类型是混杂的  
	        MimeMultipart msgMultipart = new MimeMultipart();  
//	        msgMultipart.setSubType("");    //  mixed  ,related   指定为依赖关系    
	        
	        msgMultipart.addBodyPart(createContentAndPic(mailInfo.getContent(), "D:\\123.png"));
	        
	        mailMessage.setContent(msgMultipart); 
	        // 发送邮件    
		    Transport.send(mailMessage);    
			return true;
		}
	    
	    
	    /**
	     * 创建图片和内容
	     * @param content
	     * @param path
	     * @return
	     * @throws Exception
	     */
	    public static MimeBodyPart createContentAndPic(String content,String path) throws Exception{
	    	
	    	MimeMultipart mimeMutiPart = new MimeMultipart("related");
	    	//图片Body
	    	MimeBodyPart picBodyPart = new MimeBodyPart();
	    	FileDataSource fileDataSource = new FileDataSource( new File(path));
	    	picBodyPart.setDataHandler(new DataHandler(fileDataSource));
//	    	picBodyPart.setFileName(MimeUtility.encodeText(fileDataSource.getName()));//解决中文乱码问题
	    	picBodyPart.setHeader("Content-ID", "123png");
	    	
	    	//文本Body
	    	MimeBodyPart contentBodyPart = new MimeBodyPart();
	    	//img的src要和  picBodyPart   setHeader中设置的值一样
	    	contentBodyPart.setContent(content+"<img src='cid:123png'></img>","text/html;charset=gbk");
	    	
	    	mimeMutiPart.addBodyPart(contentBodyPart);
	    	mimeMutiPart.addBodyPart(picBodyPart);
	    	
	    	//图片和文本结合
	    	MimeBodyPart allBodyPart = new MimeBodyPart();
	    	allBodyPart.setContent(mimeMutiPart);
	    	return allBodyPart;
	    }
	    
	    
	    
	    
	    
	    /**
	     * 
	     * @param isText   是否以文本格式发送邮件      true 为文本， false 为html格式
	     * @param mailTitle  邮件标题
	     * @param mailContent  邮件内容
	     * @param toAddress  发送目的邮箱地址
	     * @return
	     */
	    public static boolean sendMail(Boolean isText ,String mailTitle, String mailContent ,String toAddress) {
	    	boolean isSend = false;
	    	isText = false;
	    	try {
	    	//设置邮件   
		      MailSenderInfo mailInfo = new MailSenderInfo();    
		      mailInfo.setMailServerHost("smtp.qiye.163.com");
		      mailInfo.setMailServerPort("25");    
		      mailInfo.setValidate(true);    
		      mailInfo.setUserName("huilcmail@huilc.cn"); 
		      mailInfo.setPassword("rx321654");   //您的邮箱密码    
		      mailInfo.setFromAddress("huilcmail@huilc.cn");    
		      mailInfo.setToAddress(toAddress);    
		      //mailInfo.setSubject(CommonConstant.SMSSIGN+mailTitle);  
		      //String sendMailContent = PropertiesUtil.getProperty("CONTEND_SMSSIGN");
		      //sendMailContent = String.format(sendMailContent,mailContent);
		      //mailInfo.setContent(sendMailContent);
		      
//		      if (isText) {
//		    	  isSend = sendTextMail(mailInfo);
//		      }else {
//		    	  isSend = sendHtmlMail(mailInfo);
//			}
		      isSend = sendHtmlMail(mailInfo);
		      if(!isSend){
		    	    mailInfo.setUserName("mail3@huilc.cn"); 
				    mailInfo.setPassword("rx321654");   //您的邮箱密码    
				    mailInfo.setFromAddress("mail3@huilc.cn");  
				    isSend = sendHtmlMail(mailInfo);
		      }else if(!isSend){
		    	    mailInfo.setUserName("mail2@huilc.cn"); 
				    mailInfo.setPassword("rx321654");   //您的邮箱密码    
				    mailInfo.setFromAddress("mail2@huilc.cn");  
				    isSend = sendHtmlMail(mailInfo);
		      }else if(!isSend){
		    	  mailInfo.setUserName("mail1@huilc.cn"); 
				    mailInfo.setPassword("rx321654");   //您的邮箱密码    
				    mailInfo.setFromAddress("mail1@huilc.cn");  
				    isSend = sendHtmlMail(mailInfo);
		      }
			} catch (Exception e) {
				log.info("邮件发送失败"+e.getMessage().toString());
			}
			return isSend;
		}
	    
	    
	    public static void main(String[] args) throws Exception {
	    	//sendMail(true,"hello test  title", "hello test  content", "626481787@qq.com");
//	    	String domain = "www.ykmaiz.com";  
//	    	int iVisit = 0;  
//	    	;
//	    	System.out.printf(String.format("该域名%s被访问了%s次.",domain , iVisit));
	    	
	    	
	    	 MailSenderInfo mailInfo = new MailSenderInfo();    
		      mailInfo.setMailServerHost("smtp.qiye.163.com");
		      mailInfo.setMailServerPort("25");    
		      mailInfo.setValidate(true);    
		      mailInfo.setUserName("huilcmail@huilc.cn"); 
		      mailInfo.setPassword("rx321654");   //您的邮箱密码    
		      mailInfo.setFromAddress("huilcmail@huilc.cn");    
		      mailInfo.setToAddress("626481788@qq.com");    
		      mailInfo.setSubject("标题文本和图片测试");    
		      mailInfo.setContent("内容 文本和图片测试 ");
		     boolean isSend =  sendTextAndPic(mailInfo);
		     System.out.println(isSend?"发送成功":"发送失败");
	    }

}
