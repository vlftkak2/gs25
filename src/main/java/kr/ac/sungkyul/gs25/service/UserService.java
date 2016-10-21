package kr.ac.sungkyul.gs25.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
	private MailSender mailSender; // xml에 등록한 bean autowired
	
	// 리스팅 되는 게시물 수
		private static final int LIST_PAGESIZE = 10;
		// 페이지 리스트에 표시되는 페이지 수
		private static final int LIST_BLOCKSIZE = 5;
	
	
	public void join(UserVo vo){ // 가입
		usersdao.insert(vo);
	}
	
	public UserVo login(String email, String password){ // 로그인
		UserVo authUser = usersdao.login(email,password);
		return authUser;
	}
	
	public Boolean loginCheck(String email, String password){	//로그인 시 검사
		UserVo vo =  usersdao.login(email,password); // no, name, email 가져옴
		Boolean result = (vo != null);
		return result;
	}
	
	public UserVo get(Long no){	//회원정보 수정 시 정보 출력
		UserVo uservo = usersdao.get(no);
		return uservo;
	}
	
	public void update(UserVo vo){ // 회원정보 수정 (패스워드 미입력)
		usersdao.update(vo);
	}
	
	public void updateInfo(UserVo vo){ // 회원정보 수정 (패스워드 입력)
		usersdao.update(vo);
		
	}
	
	public Boolean idCheck(UserVo uservo){	//로그인 시 검사
//		System.out.println("service: "+uservo);
		String email = usersdao.find(uservo);
//		System.out.println("service: "+email);
		Boolean result = (email != null); // 존재하면 true
//		System.out.println(result);
		return result;
	}
	
	public String idfind(UserVo vo){ //아이디 찾기
		String email = usersdao.find(vo);
		return email;
	}
	
	public String setpass(Long no,String password){	//비밀번호 찾기 후 재설정
		Integer state = 1; 
		usersdao.setState(no, state); //state 설정
		
		Integer resultInt = usersdao.setPass(no,password); //result 반환
		String result = String.valueOf(resultInt); // String 변환
		return result;
	}
	
	//비밀번호 찾기 시 검사
	public String checkPass(UserVo uservo){	
		String email = usersdao.passfind(uservo);
		return email;
	}
	
	//아이디 유효성 검사
	public Map<String, Object> checkEmail(String email){	
		Long no = usersdao.checkEmail(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", no != null);	//존재할 경우 true
		
		return map;
	}
	
	 public String sendEmail( String email) throws Exception {
	    	
	    	String ranNum= random();	//해시암호화
	    	
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	
	    	//여러 버퍼 이용 시 속도 면에서 빠름
	    	StringBuffer strBuffer1 = new StringBuffer(ranNum);  
	    	
	    	String link= strBuffer1.append(email).toString();
	    	usersdao.savelink(link,email);	//DB에 링크 저장
	    	
	    	String sender = "GS25_Manager@gs25.com"; 
	    	String receiver = "csb6225@naver.com"; //받을사람의 이메일입니다.
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
		return MD5;
	}
	 
	 public Long passlink(String domain){	//비밀번호 찾기 후 재설정
		 PassLinkVo plvo = usersdao.passlink(domain); // no, link, state, user_no 가져옴
		 Long no = null;
		 
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
	 
	 public Map<String, Object> userManageC(String spage, String keyword) {
			// 1. 페이지 값 받기
			int page = Integer.parseInt(spage);

			int totalCount = usersdao.getTotalCountC();
			int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
			int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
			int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);

			// 3. page값 검증
			if (page < 1) {
				page = 1;
				currentBlock = 1;
			} else if (page > pageCount) {
				page = pageCount;
				currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
			}

			// 4. 페이지를 그리기 위한 값 계산
			int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
			int endPage = (startPage - 1) + LIST_BLOCKSIZE;
			int prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
			int nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
			int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
			int prevtoPage = (currentBlock > 1) ? startPage - 3 : page;
			
			
			//회원 position - CUSTOMER & 검색어
			List<UserVo> list = usersdao.getListC(page, LIST_PAGESIZE, keyword);

			Map<String, Object> map = new HashMap<String, Object>();
			
			// 5. map에 객체 담기
			map.put("sizeList", LIST_PAGESIZE);
			map.put("firstPage", startPage);
			map.put("lastPage", endPage);
			map.put("prevPage", prevPage);
			map.put("nextPage", nextPage);
			map.put("currentPage", page);
			map.put("pageCount", pageCount);
			map.put("list", list);
			map.put("totalCount", totalCount);
			map.put("keyword", keyword);
			map.put("nexttoPage", nexttoPage);
			map.put("prevtoPage", prevtoPage);

			return map;
		}
	 
	 public Map<String, Object> userManageB(String spage) {
			// 1. 페이지 값 받기
			int page = Integer.parseInt(spage);

			int totalCount = usersdao.getTotalCountB();
			int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
			int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
			int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);

			// 3. page값 검증
			if (page < 1) {
				page = 1;
				currentBlock = 1;
			} else if (page > pageCount) {
				page = pageCount;
				currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
			}

			// 4. 페이지를 그리기 위한 값 계산
			int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
			int endPage = (startPage - 1) + LIST_BLOCKSIZE;
			int prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
			int nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
			int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
			int prevtoPage = (currentBlock > 1) ? startPage - 3 : page;
			
			//회원 position - BRANCH
			List<UserVo> listB = usersdao.getListB(page, LIST_PAGESIZE);

			Map<String, Object> map2 = new HashMap<String, Object>();
			
			// 5. map에 객체 담기
			map2.put("sizeList", LIST_PAGESIZE);
			map2.put("firstPage", startPage);
			map2.put("lastPage", endPage);
			map2.put("prevPage", prevPage);
			map2.put("nextPage", nextPage);
			map2.put("currentPage", page);
			map2.put("pageCount", pageCount);
			map2.put("list", listB);
			map2.put("totalCount", totalCount);
			map2.put("nexttoPage", nexttoPage);
			map2.put("prevtoPage", prevtoPage);

			return map2;
		}
	 
	 public Integer totalMember(){
		 
		 Integer total= usersdao.totalMember();
		 
		 return total;
	 }
	 
	 //포인트 차감 (상품 구매 사용)
	 public String pointuse(Long no, Integer point, Integer product_price,Integer remaindercountdate, Integer countprice,
			 Integer halfprice){
		 
		System.out.println("포인트"+point);
		System.out.println("남은일자"+remaindercountdate);
		System.out.println("할인가격"+countprice);
		System.out.println("원가격"+product_price);

		 
		 if(remaindercountdate>7){
			 point=point-product_price;
			 
			 System.out.println("포인트:"+point);
		 }
		 else  if(remaindercountdate==7 && (product_price-countprice)>=(product_price*0.5)){
			 point=point-(product_price-countprice);
			 System.out.println("포인트7:"+point);

		 }else if(remaindercountdate==6 && (product_price-(countprice*2))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*2));
			 System.out.println("포인트6:"+point);

		 }else if(remaindercountdate==5 && (product_price-(countprice*3))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*3));
			 System.out.println("포인트5:"+point);

		 }else if(remaindercountdate==4 && (product_price-(countprice*4))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*4));
			 System.out.println("포인트4:"+point);

		 }
		 else  if(remaindercountdate==3 && (product_price-(countprice*5))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*5));
			 System.out.println("남은 포인트3:"+point);

		 }else if(remaindercountdate==2 && (product_price-(countprice*6))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*6));
			 System.out.println("포인트2:"+point);

		 }else if(remaindercountdate==1 && (product_price-(countprice*7))>=(product_price*0.5)){
			 point=point-(product_price-(countprice*7));
			 System.out.println("포인트1:"+point);

		 }else{
			 point=point-halfprice;
		 }
		 
		 Integer resultInt = usersdao.pointuse(no, point);
		 String result = String.valueOf(resultInt); // String 변환
		 
		 return result;
	 }
	 
	 
	 public UserVo getPoint(Long user_no){
		 
		 UserVo vo=usersdao.getPoint(user_no);
		return vo;
	 }
	 
}