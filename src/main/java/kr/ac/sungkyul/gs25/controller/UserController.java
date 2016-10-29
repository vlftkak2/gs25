package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.dao.ProductDao;
import kr.ac.sungkyul.gs25.service.GificonService;
import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GificonService gifticonservice;
	
	@Autowired
	private ProductDao productdao;
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "user/joinform";
	}
	
	@RequestMapping("/Subjoinform")
	public String Subjoinform(){
		return "user/sub_joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/Subjoin")
	public String Subjoin(@ModelAttribute UserVo vo){
		userService.join(vo);
		return "redirect:/user/Subjoinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/Subjoinsuccess")
	public String SubjoinSuccess(){
		return "user/sub_joinsuccess";
	}
	
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "user/loginform";
	}
	
	
	@RequestMapping("/Subloginform")
	public String Subloginform(){
		return "user/sub_loginform";
	}
	
	//1. Ajax 사용 시 - DB (로그인 정보 비교)
	@ResponseBody
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public String checkId(String email, String password, HttpSession session) {	//Request 객체받음, script or DB 객체 분별
		
		
		UserVo authUser =  userService.login(email,  password);

		String result = "true";
		
		if(authUser == null){
			result = "false";
		}
		else {
			//인증성공
			session.setAttribute("authUser",authUser);
			result = "true";
		}
		return result;
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();	//로그아웃 처리 시 세션을 지워줌
		
		return "redirect:/main";
	}
	
	@RequestMapping("/Sublogout")
	public String Sublogout(HttpSession session, @RequestParam("store_no") Long store_no){
		session.removeAttribute("authUser");
		session.invalidate();	//로그아웃 처리 시 세션을 지워줌
		return "redirect:/sub/main?store_no="+store_no;
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, Model model){
		UserVo temp = (UserVo)session.getAttribute("authUser");
		
		Long no = temp.getNo();
		
		UserVo nvo = userService.get(no);
		model.addAttribute("userVo", nvo);
		
		return "user/modifyform";
	}
	
	@RequestMapping("/Submodifyform")
	public String Submodifyform(HttpSession session, Model model){
		UserVo temp = (UserVo)session.getAttribute("authUser");
		
		Long no = temp.getNo();
		
		UserVo nvo = userService.get(no);
		model.addAttribute("userVo", nvo);
		
		return "user/sub_modifyform";
	}
	
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		vo.setNo(no);
		
		userService.update(vo);
		return "user/modifysuccess";
	}
	

	@RequestMapping("/Submodify")
	public String Submodify(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		vo.setNo(no);
		
		userService.update(vo);
		return "user/sub_modifysuccess";
	}
	
	@RequestMapping("/findInfo")	//찾기 폼
	public String findInfo(){
		return "user/findInfo";
	}
	
	@RequestMapping("/SubfindInfo")	//찾기 폼
	public String SubfindInfo(){
		return "user/sub_findinfo";
	}

	@RequestMapping("/idFind")	// 아이디 찾기
	public String idFind(@ModelAttribute UserVo vo, Model model){

		String email = userService.idfind(vo);

		if(email == null){	//계정 정보가 없을 경우
			Boolean result = false;
			model.addAttribute("result", result);
			return "user/findInfo";
		}
		
		model.addAttribute("email",email);
		return "user/idresult";
	}
	
	@RequestMapping("/SubidFind")	// 아이디 찾기
	public String SubidFind(@ModelAttribute UserVo vo, Model model){

		String email = userService.idfind(vo);

		if(email == null){	//계정 정보가 없을 경우
			Boolean result = false;
			model.addAttribute("result", result);
			return "user/findInfo";
		}
		
		model.addAttribute("email",email);
		return "user/sub_idresult";
	}
		
	@ResponseBody
	@RequestMapping(value = "checkPass", method = RequestMethod.POST)	//비밀번호 찾기 검사
	public String checkPass(@RequestBody UserVo userVo, HttpSession session) {	//Request 객체받음, script or DB 객체 분별
		String email = userService.checkPass(userVo);
		String result = "true";
		
		if(email == null){
			result = "false";
		}
		else {
			try {
				result = userService.sendEmail(email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/{domain}/repassword", method = RequestMethod.GET)
	public String abc(@PathVariable String domain, Model model){
		Long no = userService.passlink(domain);
//		비밀번호 변경창으로 보냄 (어떤 멤버인지는 알아야 함)
		model.addAttribute("userno", no);
		
		return "user/repassword";
	}
	
	@ResponseBody
	@RequestMapping(value ="/setPass", method = {RequestMethod.GET, RequestMethod.POST})	//재설정 비번 저장
	public String setPassword(Long no, String password){
		System.out.println("cont "+no +" "+password);
		//state 1로 변경
		String result = userService.setpass(no,password);
		return result;
	}
	
	@RequestMapping("/passresult")	//메일 전송 완료
	public String passResult(){
		return "user/passresult";
	}
	
	@RequestMapping("/Subpassresult")	//메일 전송 완료
	public String SubpassResult(){
		return "user/sub_passresult";
	}
	
	@RequestMapping("/repasswordSuccess")	//비번 재설정 완료
	public String repasswordSuccess(){
		return "user/repasswordSuccess";
	}
	
	@ResponseBody	// 아이디 중복 검사
	@RequestMapping(value = "CheckEmail", method = RequestMethod.POST)
	public Map<String, Object> checkEmail(String email) {	//Request 객체받음, script or DB 객체 분별
		
		Map<String, Object> map = userService.checkEmail(email);
		
		return map;
	}
	
	//회원 관리 (본사관리자)
	@RequestMapping(value = "mlist")
	public String mlist(Model model, 
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword){//Request 객체받음, script or DB 객체 분별
		
		Map<String, Object> map = userService.userManageC(page, keyword);	//customer
		Map<String, Object> map2 = userService.userManageB(page);	//branch
		Integer total =userService.totalMember();
		
		model.addAttribute("total",total);
		model.addAttribute("map", map);
		model.addAttribute("map2", map2);
		
		return "user/mlist";
	}
	
	//포인트로 상품 결제
	@ResponseBody
	@RequestMapping(value="/pointuse", method = RequestMethod.POST)
	public String pointuse(HttpSession session, Model model,
							Long storeproduct_no, Integer product_price, Long store_no,
							Integer remaindercountdate,Integer countprice,Integer halfprice,
							Integer point){
		
//		System.out.println("원래 가격 :"+product_price);
//		System.out.println("남은 일자 :"+remaindercountdate);
//		System.out.println("할인 가격 :"+countprice);
		System.out.println("포인트 :"+point );
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		
		//포인트 차감
		String result = userService.pointuse(no, point, product_price,remaindercountdate,countprice,halfprice);
		System.out.println(result);
		
		//기프티콘 전송
		gifticonservice.insert2(no, storeproduct_no, store_no);
		
		//수량 감소
		productdao.cutmount(storeproduct_no);
		
		return result;
	}
}