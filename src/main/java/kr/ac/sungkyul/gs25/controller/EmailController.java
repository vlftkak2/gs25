package kr.ac.sungkyul.gs25.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import kr.ac.sungkyul.gs25.service.SenderEmail;
import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.EmailVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
	UserService userService;
	
	@Autowired
    private SenderEmail senderEmail;
     
	public String random(){
		StringBuffer buffer = new StringBuffer();
		for(int i =0;i<=6;i++){
			int n = (int)(Math.random()*10);
			buffer.append(n);
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
    @RequestMapping("/send")
    public String sendEmailAction(@ModelAttribute UserVo vo, HttpSession session) throws Exception {
//    	System.out.println("emailController: "+vo.toString());;
    	
    	String id = vo.getEmail();
    	String ranNum= random();
    	
    	EmailVo email = new EmailVo();
         
        String receiver = "vlftkak2@naver.com"; //받을사람의 이메일입니다.
        String subject = "GS25편의점 회원님의 임시 비밀번호입니다.";
        String content = "안녕하세요. GS25편의점입니다. 회원님의 임시 비밀번호는 "+ranNum+" 입니다. \n"
        				+ "http://localhost:8088/gs25/user/repassword"+"?ranNum="+ranNum+"&userid="+id;
        
        //random, id 값 session 전송
        
        session.setAttribute("rannum",ranNum);
        session.setAttribute("userid",id);
        
        email.setReceiver(receiver);
        email.setSubject(subject);
        email.setContent(content);
        senderEmail.SendEmail(email);

//	    return new ModelAndView("success");
        return "redirect:/user/passresult";
    }
	
	
//	@RequestMapping("/send2")
//    public String navermailtest(HttpServletRequest request, ModelMap mo) throws Exception{
//		String ranNum="";
//		ranNum = random();
//		// 메일 관련 정보
//        String host = "smtp.gmail.com";
//        final String username = "";       //이메일 아이디
//        final String password = "";		//이메일 비밀번호
//        int port= 465;
//        
//        // 메일 내용
//        String recipient = "beckyi@naver.com";    //메일을 발송할 이메일 주소를 기재해 줍니다.
//        String subject = "GS25편의점 회원님의 임시 비밀번호입니다.";
//        String body = "안녕하세요. GS25편의점입니다. 회원님의 임시 비밀번호는 "+ranNum+"입니다.";
//        
//        Properties props = System.getProperties();
//        
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.ssl.trust", host);
//           
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//            String un=username;
//            String pw=password;
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(un, pw);
//            }
//        });
//        session.setDebug(true); //for debug
//           
//        Message mimeMessage = new MimeMessage(session);
//        mimeMessage.setFrom(new InternetAddress("mong40@naver.com"));	//발신자 설정
//        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));	//받는 이
//        mimeMessage.setSubject(subject);	//제목
//        mimeMessage.setText(body);	//내용
//        Transport.send(mimeMessage);
//        
//        return "user/passresult";
//    }
}
