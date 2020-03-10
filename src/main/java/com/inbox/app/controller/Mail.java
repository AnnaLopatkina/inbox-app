package com.inbox.app.controller;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class Mail {

	private Session session = null ;
	final String myAccountEmail = "inboxapp237@gmail.com";
	final String passwort = "inboxapp237237";
	
	public void sendMail(String recepient) throws Exception {
		System.out.println("Preparing to send Mail");
		
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "false");
		properties.put("mail.smtp.starttls.enable","true"); 
		properties.put("mail.smtp.EnableSSL.enable","true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587"); // 465 is false 
		
		
		createSession(properties);
		session.setDebug(true);
		
		Message message = prepareMessage(session, myAccountEmail , recepient);
		
		Transport.send(message);	
		System.out.println("Message sent succesfully");
	}
	
	
	private void createSession(Properties properties) {
			session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, passwort);
			}
		});
	}
	private Message prepareMessage(Session session, String myAccountEmail , String recepient ) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress((myAccountEmail)));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));	           
			message.setSubject("Account opening");
			message.setText("The Inbox Team thanks you for registering on our application. "
						+   "Inbox - All together against loneliness.");
			
			return message ;
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	} 
}