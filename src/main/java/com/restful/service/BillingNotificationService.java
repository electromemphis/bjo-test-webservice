package com.restful.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.restful.dao.ProductDetailsDAO;
import com.restful.mail.EmailService;
import com.restful.model.EmailData;
import com.restful.model.NotificationStatus;
import com.restful.model.ProductDetails;

@RestController
@RequestMapping("/billing")
public class BillingNotificationService {
	
	private final String SUCCESS = "SUCCESS";
	private final String FAILED = "FAILED";
	private final String ERROR = "ERROR";
	
	@Autowired
	EmailService emailService;
	
	
	
    @RequestMapping("/{accountNum}")
    public @ResponseBody NotificationStatus emailNotify(@PathVariable(value="accountNum")  String accountNum) {
    	
    	EmailData emailData = new EmailData();
    	emailData.setDate(new Date());
    	emailData.setId("0001");
    	emailData.setMessageBody("Billing has been posted for  <br> account number:"+accountNum+".");
    	emailData.setFooterAddress("Farmington Hills, MI");
    	emailData.setFooterLLC("Mercedes Benz Financial Services");
    	emailData.setRecipientname("James Octura");
    	emailData.setRecipientEmail("bienjames.octura@gmail.com");
    	emailData.setSubject("Billing Notification");
    	
    	NotificationStatus notifStatus = new NotificationStatus();
		try{
			emailService.sendEmail("dummy", new Locale("en"), emailData);
			notifStatus.setStatusCode(SUCCESS);
			notifStatus.setMessage("Email sent successfully.");
		}catch(MessagingException mex){
			notifStatus.setStatusCode(ERROR);
			notifStatus.setMessage(mex.getMessage());
			mex.printStackTrace();
		}catch (Exception e) {
			notifStatus.setStatusCode(ERROR);
			notifStatus.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		return notifStatus;
		
    }
    
    @RequestMapping("/getProduct/{itemcode}")
    public @ResponseBody ProductDetails getProductMethod(@PathVariable(value="itemcode")  String itemcode) {
    	System.out.println("getProductMethod");
    	
//    	mailTest.sendMail("dear@dear.com", "blah blah");
    	ProductDetailsDAO pdDao=new ProductDetailsDAO();
          return  pdDao.getProductDetails(itemcode);
    }
    
     
    @RequestMapping(value = "/pass/{param}", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public @ResponseBody ProductDetails restProxyPostAudit(@PathVariable("param") String restUrlPath,
			@RequestBody String body) {
			
    		System.out.println("postings...");

    		ProductDetailsDAO pdDao=new ProductDetailsDAO();
            return  pdDao.getProductDetails("1");
			
	}
    
    @RequestMapping(value="/chatFuel")
    public @ResponseBody String getChatFuelResponse(){
    	
    	return "{\"messages\": "
    			+ "[{\"text\": \"Welcome to the Chatfuel Rockets!\"},"
    			+ "{\"text\": \"What are you up to?\"}]}";


    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload( 
            @RequestParam("file") MultipartFile file){
            String name = "test11";
            System.out.println("upload File==>"+ file);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	

}
