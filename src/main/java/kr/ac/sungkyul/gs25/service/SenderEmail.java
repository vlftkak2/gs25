package kr.ac.sungkyul.gs25.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import kr.ac.sungkyul.gs25.vo.EmailVo;

@Component
public class SenderEmail {
	
	 @Autowired
     protected JavaMailSender  mailSender;
	 
     public void SendEmail(EmailVo email) throws Exception {
          
         MimeMessage msg = mailSender.createMimeMessage();
         try {
             msg.setSubject(email.getSubject());
             msg.setText(email.getContent());
             msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email.getReceiver()));
            
         }catch(MessagingException e) {
             System.out.println("MessagingException");
             e.printStackTrace();
         }
         try {
        	 System.out.println("msg: "+msg);
             mailSender.send(msg);
         }catch(Exception e) {
             System.out.println("MailException발생");
             e.printStackTrace();
         }
     }
}
