package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		System.out.println("join: "+vo.toString());
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "user/loginform";
	}
	
	@RequestMapping(value= "/login", method= RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value= "email", required=false, defaultValue="") String email,
			@RequestParam(value="password", required=false, defaultValue="") String password){
		
		UserVo authUser =  userService.login(email,  password);
		
		if(authUser == null){
			return "redirect:/user/loginform";
		}
		
		//인증성공
		session.setAttribute("authUser",authUser);
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();	//로그아웃 처리 시 세션을 지워줌
		
		return "redirect:/main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, Model model){
		UserVo temp = (UserVo)session.getAttribute("authUser");
		
		Long no = temp.getNo();
		
		UserVo nvo = userService.get(no);
		model.addAttribute("userVo", nvo);
		
		return "user/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		vo.setNo(no);
		
		userService.update(vo);
		return "redirect:/user/modifyform";
	}
	
	@RequestMapping("/findInfo")
	public String findInfo(){
		return "user/findInfo";
	}

	@RequestMapping("/idFind")
	public String idFind(@ModelAttribute UserVo vo,Model model){
		String email = userService.idfind(vo);
		model.addAttribute("email",email);
		return "user/idresult";
	}
	
	@RequestMapping("/repassword")
	public String repasswordForm(){
		return "user/repassword";
	}
	
	@RequestMapping("/setPass")
	public String setPassword
			(@RequestParam(value= "email", required=false, defaultValue="") String email,
			 @RequestParam(value= "password", required=false, defaultValue="") String password){
		
		userService.setpass(email,password);
		
		return "user/send";
	}
	
	@RequestMapping("/passresult")
	public String passResult(){
		return "user/passresult";
	}
	
	@ResponseBody
	@RequestMapping(value = "CheckEmail", method = RequestMethod.POST)
	public Map<String, Object> checkEmail(String email) {	//Request 객체받음, script or DB 객체 분별
		
		Map<String, Object> map = userService.checkEmail(email);
		
		return map;
	}
}