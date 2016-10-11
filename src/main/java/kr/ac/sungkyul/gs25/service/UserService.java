package kr.ac.sungkyul.gs25.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.UserDao;
import kr.ac.sungkyul.gs25.vo.PassLinkVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao usersdao;
	
	@Autowired
	private MailSender mailSender;

	
	public void join(UserVo vo){
		usersdao.insert(vo);
	}
	
	public UserVo login(String email, String password){
		UserVo authUser = usersdao.get(email,password);
		return authUser;
	}
	
	public Boolean loginCheck(String email, String password){	//로그인 시 검사
		System.out.println("service: "+email);
		UserVo vo =  usersdao.get(email,password);
		Boolean result = (vo != null);
		System.out.println(result);
		return result;
	}
		
	public UserVo get(Long no){	//회원정보 수정 시 정보 출력
		UserVo uservo = usersdao.get(no);
		return uservo;
	}
	
	public void update(UserVo vo){
		usersdao.update(vo);
	}
	
	public void update(String tempPass){
		usersdao.update(tempPass);
	}
	
	public void updateInfo(UserVo vo){
			usersdao.update(vo);	
	}
	
	public Boolean idCheck(UserVo uservo){	//로그인 시 검사
		System.out.println("service: "+uservo);
		String email = usersdao.find(uservo);
		System.out.println("service: "+email);
		Boolean result = (email != null);
		System.out.println(result);
		return result;
	}
	
	public String idfind(UserVo vo){	//아이디 찾기
		String email = usersdao.find(vo);
		return email;
	}
	
	public String setpass(Long no,String password){	//비밀번호 찾기 후 재설정
		System.out.println(no);
		System.out.println("s: "+password);
		
		//state 설정
		Integer state = 1; 
		usersdao.setState(no, state);
		//result 반환
		Integer resultInt = usersdao.setPass(no,password);
		String result = String.valueOf(resultInt);
		System.out.println("Service: "+result);
		return result;
	}
	
	public String checkPass(UserVo uservo){	//비밀번호 찾기 시 검사
//		System.out.println("service: "+uservo);
		String email = usersdao.passfind(uservo);
//		System.out.println("service: "+email);
		return email;
	}
	
	public Map<String, Object> checkEmail(String email){	//아이디 유효성 검사
		System.out.println(email);
		Long no = usersdao.checkEmail(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", no != null);	//존재할 경우 true
		System.out.println(email + " -service- "+ (no != null));
		
		return map;
	}
	
	 public String sendEmail( String email) throws Exception {
	    	System.out.println("emailController: "+email);
	    	
	    	String ranNum= random();	//해시암호화
	    	
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	
	    	//여러 버퍼 이용 시 속도 면에서 빠름
	    	StringBuffer strBuffer1 = new StringBuffer(ranNum);  
	    	
	    	String link= strBuffer1.append(email).toString();
	    	System.out.println("link: "+link);
	    	usersdao.savelink(link,email);	//DB에 링크 저장
	    	
	    	String sender = "GS25_Manager@gs25.com"; 
	    	String receiver = "beckyi@naver.com"; //받을사람의 이메일입니다.
	        String subject = "GS25편의점 회원님의 임시 비밀번호입니다.";
	        String content = "안녕하세요. GS25편의점입니다. 회원님의 비밀번호를 새로 설정하실 수 있으시는 링크 입니다. \n" 
	        				+ "http://localhost:8088/gs25/user/" + link +"/repassword";
	        
	        //random, id 값 session 전송
//	        HttpSession session = new HttpSession(); //컨트롤러에서 전달해줄 수 있으나 세션은 불안정!(외부컴터 X)
//	        session.setAttribute("rannum",ranNum);
//	        session.setAttribute("userid",id);
	    	
	        message.setFrom(sender);
	        message.setTo(receiver);
	        message.setSubject(subject);
	        message.setText(content);
	        mailSender.send(message);
	        
	        System.out.println("이메일 전송");
	        
	        String result = "true";
	        return result;
//	        return "redirect:/user/passresult";
	 }
	 
	public String random() {
		// StringBuffer buffer = new StringBuffer();
		// for(int i =0;i<20;i++){
		// int n = (int)(Math.random()*10);
		// buffer.append(n);
		// }
		// System.out.println(buffer.toString());
		// return buffer.toString();
		
		// (1) Calendar객체를 얻는다.
		Calendar cal = Calendar.getInstance();
		// (2) 출력 형태를 지정하기 위해 Formatter를 얻는다.
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// (3) 출력형태에 맞는 문자열을 얻는다.
		String datetime = sdf1.format(cal.getTime());
		System.out.println("--> " + datetime);
		
		String MD5 = ""; 
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(datetime.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			MD5 = null; 
		}
		System.out.println(MD5);
		return MD5;
	}
	 
	 public Long passlink(String domain){	//비밀번호 찾기 후 재설정
		 System.out.println(domain);

		 PassLinkVo plvo = usersdao.passlink(domain);
		 Long no = null;
		 System.out.println("plvo: "+plvo);
		 
		 if(plvo == null){
			 System.out.println("plvo null");
			 return 0L;
		 } else{
			 System.out.println("plvo not null");
			 Integer state = plvo.getState();
			 System.out.println(state);
			 if(state == 0){
				 System.out.println("State 0");
				 no = plvo.getUser_no();
			 } else {
				 System.out.println("State 1");
				 return 0L;
			 }
		 }
		 
		 return no;
	}
	 
}