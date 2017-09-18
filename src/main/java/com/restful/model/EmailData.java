package com.restful.model;

import java.util.Date;

public class EmailData {
	
	private String recipientEmail;
	private String recipientname;
	private String footerAddress;
	private String footerLLC;
	private Date date;
	private String messageBody;
	private String id;
	private String subject;
	
	
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getRecipientname() {
		return recipientname;
	}
	public void setRecipientname(String recipientname) {
		this.recipientname = recipientname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFooterAddress() {
		return footerAddress;
	}
	public void setFooterAddress(String footerAddress) {
		this.footerAddress = footerAddress;
	}
	public String getFooterLLC() {
		return footerLLC;
	}
	public void setFooterLLC(String footerLLC) {
		this.footerLLC = footerLLC;
	}
	
	

}
