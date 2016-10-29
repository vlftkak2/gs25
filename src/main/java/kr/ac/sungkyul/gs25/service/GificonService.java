package kr.ac.sungkyul.gs25.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.GifticonDao;
import kr.ac.sungkyul.gs25.dao.ProductDao;
import kr.ac.sungkyul.gs25.vo.GifticonVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;

@Service
public class GificonService {
	
	@Autowired
	private GifticonDao gifticondao;
	
	@Autowired
	private ProductDao productdao;
	
	@Autowired
	private JavaMailSender mailSender; // xml에 등록한 bean autowired
	

	// 기프티콘 삽입
 	public void insert(Long user_no, Long storeproduct_no, Long store_no) {
 		
 		GifticonVo gifticonvo = new GifticonVo();
		
		gifticonvo.setUser_no(user_no);
		gifticonvo.setStoreproduct_no(storeproduct_no);
		
 		
		//기프티콘 테이블 삽입
 		gifticondao.insert(gifticonvo);
 		
 		//이메일에 보낼 상품 정보
 		StoreProductVo storeproductvo = productdao.giftprductInfo(store_no, storeproduct_no);
 		try {
 			emailGift(user_no, storeproductvo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 // 포인트 구매 삽입
  	public void insert2(Long user_no, Long storeproduct_no, Long store_no) {
  		
  		GifticonVo gifticonvo = new GifticonVo();
 		
 		gifticonvo.setUser_no(user_no);
 		gifticonvo.setStoreproduct_no(storeproduct_no);
 		
  		
 		//기프티콘 테이블 삽입
  		gifticondao.insert(gifticonvo);
  		
  		//이메일에 보낼 상품 정보
  		StoreProductVo storeproductvo = productdao.giftprductInfo(store_no, storeproduct_no);
  		try {
  			PointemailGift(user_no, storeproductvo);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
  	}
  	
 	
 	// 기프티콘 이메일 전송
  	public void emailGift(Long user_no, StoreProductVo storeproductvo) throws Exception {
  		 
  		 MimeMessage message = mailSender.createMimeMessage();
         try {
        	 
        	 MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
             messageHelper.setSubject("[GS25] 출석체크 기프티콘 선물 증정");
             String htmlContent = "<div>기프티콘을 발송하였습니다.<strong></strong>꾸준히 출석체크를 달성해주셔서 감사합니다.</div><br>"
            		 			+ "<div style=\"background-image:url('cid:back'); height: 930px; width: 525px; background-repeat: no-repeat;\">"
//            		 			+ "<img style=\"width:300px; margin: 100px 0 0 117px; position: relative;\" src=\"cid:product\">"
								+ "<img style=\"width: 210px; height: 276px; margin: 90px 0 0 148px; position: relative; margin-bottom: 20px;\" src=\"cid:product\"><br>"
            		 			+ "<strong style=\"font-size: 30px; color: #24809c; letter-spacing: -1px; margin-bottom: 10px; margin-left: 40px;\">"+storeproductvo.getName()+"</strong><br>"
            		 			+ "<p style=\"font-size: 16px; margin-left: 55px;\">(주): "+storeproductvo.getMaker()+"</p>"
            		 			+ "<p style=\"font-size: 16px; margin-left: 55px;\">지점: "+storeproductvo.getStorename()+"</p></div>";
//             +"<img src=\"cid:cde\">" ;
             messageHelper.setText(htmlContent, true);
             messageHelper.setFrom("GS25@gmail.com", "GS25");
             messageHelper.setTo(new InternetAddress("beckyi@naver.com", "UTF-8"));
             
             //내장 이미지 전송
             FileSystemResource res = new FileSystemResource(new File("C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\subindex\\giftiBack.png"));
             FileSystemResource res2 = new FileSystemResource(new File("C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\product\\"+storeproductvo.getOrgname()));
             
             messageHelper.addInline("back", res);
             messageHelper.addInline("product", res2);
             
//             FileSystemResource res1 = new FileSystemResource(new File("c:/giftBack.png"));
//             messageHelper.addInline("cde", res1);
             
//             messageHelper.addInline("abc", new FileDataSource("abc.jpg"));
        	 
//             message.setSubject("[GS25] 출석체크 기프티콘 선물 증정", "UTF-8");
//             String htmlContent = "<div>기프티콘을 발송하였습니다.<strong></strong>꾸준히 출석체크를 달성해주셔서 감사합니다.</div><br>"
//                     + "<img src=\"/gs25/assets/images/subindex/giftiBack.png\"><br>"
//                     + "<img src=\"/gs25/webapp/assets/images/subindex/giftiBack.png\"><br>"
//                     + "<img src=\"C:\\Users\\S401-14\\git\\gs25\\webapp\\assets\\images\\subindex\\giftiBack.png\"><br>";
//
//             message.setText(htmlContent, "UTF-8", "html");
//             message.setFrom(new InternetAddress("Gs25@gmail.com"));
//             message.addRecipient(RecipientType.TO, new InternetAddress("beckyi@naver.com"));
//             messageHelper.addInline("abc", new FileDataSource("giftiBack.png"));
             mailSender.send(message);
             
         } catch (MessagingException e) {
             e.printStackTrace();
             return;
         } catch (MailException e) {
             e.printStackTrace();
             return;
         } 
        
        System.out.println("이메일 전송");
  	}
  	
  	
  	
	// 포인트 구매시 기프티콘 이메일 전송
  	public void PointemailGift(Long user_no, StoreProductVo storeproductvo) throws Exception {
  		 
  		 MimeMessage message = mailSender.createMimeMessage();
         try {
        	 
        	 MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
             messageHelper.setSubject("[GS25] 포인트 구매 기프티콘 선물 증정");
             String htmlContent = "<div>기프티콘을 발송하였습니다.<strong></strong>포인트로 구매해주셔서 감사합니다.</div><br>"
            		 			+ "<div style=\"background-image:url('cid:back'); height: 930px; width: 525px; background-repeat: no-repeat;\">"
//            		 			+ "<img style=\"width:300px; margin: 100px 0 0 117px; position: relative;\" src=\"cid:product\">"
								+ "<img style=\"width: 210px; height: 276px; margin: 90px 0 0 148px; position: relative; margin-bottom: 20px;\" src=\"cid:product\"><br>"
            		 			+ "<strong style=\"font-size: 30px; color: #24809c; letter-spacing: -1px; margin-bottom: 10px; margin-left: 40px;\">"+storeproductvo.getName()+"</strong><br>"
            		 			+ "<p style=\"font-size: 16px; margin-left: 55px;\">(주): "+storeproductvo.getMaker()+"</p>"
            		 			+ "<p style=\"font-size: 16px; margin-left: 55px;\">지점: "+storeproductvo.getStorename()+"</p></div>";
//             +"<img src=\"cid:cde\">" ;
             messageHelper.setText(htmlContent, true);
             messageHelper.setFrom("GS25@gmail.com", "GS25");
             messageHelper.setTo(new InternetAddress("beckyi@naver.com", "UTF-8"));
             
             //내장 이미지 전송
             FileSystemResource res = new FileSystemResource(new File("C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\subindex\\giftiBack.png"));
             FileSystemResource res2 = new FileSystemResource(new File("C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\product\\"+storeproductvo.getOrgname()));
             
             messageHelper.addInline("back", res);
             messageHelper.addInline("product", res2);
             
//             FileSystemResource res1 = new FileSystemResource(new File("c:/giftBack.png"));
//             messageHelper.addInline("cde", res1);
             
//             messageHelper.addInline("abc", new FileDataSource("abc.jpg"));
        	 
//             message.setSubject("[GS25] 출석체크 기프티콘 선물 증정", "UTF-8");
//             String htmlContent = "<div>기프티콘을 발송하였습니다.<strong></strong>꾸준히 출석체크를 달성해주셔서 감사합니다.</div><br>"
//                     + "<img src=\"/gs25/assets/images/subindex/giftiBack.png\"><br>"
//                     + "<img src=\"/gs25/webapp/assets/images/subindex/giftiBack.png\"><br>"
//                     + "<img src=\"C:\\Users\\S401-14\\git\\gs25\\webapp\\assets\\images\\subindex\\giftiBack.png\"><br>";
//
//             message.setText(htmlContent, "UTF-8", "html");
//             message.setFrom(new InternetAddress("Gs25@gmail.com"));
//             message.addRecipient(RecipientType.TO, new InternetAddress("beckyi@naver.com"));
//             messageHelper.addInline("abc", new FileDataSource("giftiBack.png"));
             mailSender.send(message);
             
         } catch (MessagingException e) {
             e.printStackTrace();
             return;
         } catch (MailException e) {
             e.printStackTrace();
             return;
         } 
        
        System.out.println("이메일 전송");
  	}

}
