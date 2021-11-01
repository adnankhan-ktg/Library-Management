package com.intelliatech.LibraryManagement.service.helper;

import com.intelliatech.LibraryManagement.dto.MailRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;


@Service
public class MailService {

	public void sendMail(MailRequestDto mailRequestDto)
	{
		// set variable for g-mail
		String host="smtp.gmail.com";
		//get the System Properties
		Properties property = System.getProperties();
		// setting important information to properties object
		 
		// host set
		property.put("mail.smtp.host", host);
		property.put("mail.smtp.port", "465");
		property.put("mail.smtp.ssl.enable", "true");
		property.put("mail.smtp.auth", "true");
		
		// step 1: to get the session 
		
		 Session session= Session.getInstance(property,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("adnan@gmail.com","123456****");
			}
		
		 });
		 session.setDebug(true);
	
		 // step 2: compose the message [test, multi media]
		 
		 MimeMessage m=new MimeMessage(session);
		 try {
			 // from email 
			 m.setFrom("adnank.bca2019@ssism.org");
			 
			 //adding receipt to message
			 //m.addRecipient(msg.RecipientType.to, null);
			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRequestDto.getReceiverAddress()));
			// adding subject to message
			 m.setSubject(mailRequestDto.getSubject());
			 // adding text to message
			 m.setText(mailRequestDto.getMessage());
			 
			 // send 
			 
			 // step 3 : send the message using Transport class
			 
			 Transport.send(m);
			  System.out.println("Send Successfully");
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
	}
	
//	//For send a file
//	public void attech(MailRequest mailRequest,MultipartFile multipartFile)
//	{
//		String from="coder455336@gmail.com";
//		// set variable for g-mail
//		String host="smtp.gmail.com";
//		//get the System Properties
//		Properties propertie= System.getProperties();
//		// setting important information to properties object
//
//		// host set
//
//		propertie.put("mail.smtp.host", host);
//		propertie.put("mail.smtp.port", "465");
//		propertie.put("mail.smtp.ssl.enable", "true");
//		propertie.put("mail.smtp.auth", "true");
//
//		// step 1: to get the session
//
//		 Session session= Session.getInstance(propertie,new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication("coder455336@gmail.com","*coder+programmer*");
//			}
//
//		 });
//		 session.setDebug(true);
//
//		 // step 2: compose the message [test, multi media]
//
//		 MimeMessage m=new MimeMessage(session);
//		 try {
//			 // from email
//			 m.setFrom(from);
//
//			 //adding reciptent to message
//			 //m.addRecipient(msg.RecipientType.to, null);
//			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRequest.getReceiverAddress()));
//			// adding subject to message
//			 m.setSubject(mailRequest.getSubject());
//			 // attechment
//			 String path="D:\\abc.txt";
//			 // main multipart
//			 MimeMultipart mimeMultipart=new MimeMultipart();
//
//
//			  File convFile = new File( multipartFile.getOriginalFilename() );
//		        FileOutputStream fos = new FileOutputStream( convFile );
//		        fos.write( multipartFile.getBytes() );
//		        fos.close();
//
//
//
//			 //
//			 MimeBodyPart textmime=new MimeBodyPart();
//			 MimeBodyPart filemime=new MimeBodyPart();
//			 try {
//				 textmime.setText(mailRequest.getMessage());
//				 File file =new File(path);
//				 filemime.attachFile(convFile);
//				 mimeMultipart.addBodyPart(textmime);
//				 mimeMultipart.addBodyPart(filemime);
//			 }catch(Exception e)
//			 {
//				 e.printStackTrace();
//			 }
//			 m.setContent(mimeMultipart);
//
//
//			 // send
//
//			 // step 3 : send the message using Transport class
//
//			 Transport.send(m);
//			  System.out.println("Send Successfully");
//
//		 }
//		 catch(Exception e)
//		 {
//			 e.printStackTrace();
//		 }
//
//	}
//
//

}
