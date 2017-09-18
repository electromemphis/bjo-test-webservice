package com.restful.mail;


import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.restful.model.EmailData;

public class EmailService {

     private static final String LOGO = "templates/images/logo.jpg";
    
     private static final String JPG_MIME = "image/jpg";

  @Autowired
   private JavaMailSender mailSender;
 
  @Autowired
  private SpringTemplateEngine templateEngine;
    
    /* 
     * Send HTML mail  
     */
    public void sendEmail( String documentName,final Locale locale, EmailData data) throws MessagingException,Exception {
        
        // Prepare the Thymeleaf evaluation context
        final Context thymeContext = new Context(locale);
        thymeContext.setVariable("data", data);
          
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
        message.setSubject(data.getSubject());
 
        // could have CC, BCC, will also take an array of Strings
        message.setTo(data.getRecipientEmail());
        
        message.setFrom(new InternetAddress("noreply@daimler.net"));

        // Create the HTML body using Thymeleaf..template is hotelManagementMail.html
        final String htmlContent = this.templateEngine.process("notificationMail", thymeContext);
        message.setText(htmlContent, true /* isHtml */);
   
        // Add the logo
        message.addInline("logo", new ClassPathResource(LOGO), JPG_MIME);
        
        
        // Add attachment
//        message.addAttachment(documentName, new File("src/main/resources/reports/"+documentName));
        // Send email
        this.mailSender.send(mimeMessage);

    }

 
}
