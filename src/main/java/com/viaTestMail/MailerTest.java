package com.viaTestMail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailerTest {
	
	public static void main(String[] args) throws Exception{
		
		
		Properties props = new Properties();
        props.put("mail.smtp.host", "wksmtphub.wk.dcx.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("MBFS_Insights@daimler.com"));
        
        Address[] addresses = new Address[]{
        		new InternetAddress("kai.dosenbach@daimler.com"),
        		new InternetAddress("james.octura@daimler.com"),
        		new InternetAddress("bienjames.octura@gmail.com"),
        		new InternetAddress("kai.dosenbach@gmx.de")
        };
        
        message.addRecipients(Message.RecipientType.BCC, addresses);
//        message.setRecipient(RecipientType.TO, new InternetAddress("kai.dosenbach@daimler.com"));
//        message.setRecipient(RecipientType.TO, new InternetAddress("james.octura@daimler.com"));
//        message.setRecipient(RecipientType.TO, new InternetAddress("bienjames.octura@gmail.com"));
//        message.setRecipient(RecipientType.TO, new InternetAddress("kai.dosenbach@gmx.de"));
        message.setSubject("Notification");
        message.setText("TESTETSTSTSTSTST  TAKE 3", "UTF-8"); // as "text/plain"
        message.setSentDate(new Date());
        Transport.send(message);
        
        //BCC,
        
	}

}
