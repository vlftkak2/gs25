package kr.ac.sungkyul.gs25.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
public class testController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> androidTestWithRequest(HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("password") String password){
		
		System.out.println("이메일"+email);
		System.out.println("비밀번호"+password);
	
	     Map<String, String> result = new HashMap<String, String>();
	     
	 	UserVo authUser =  userService.login(email,  password);
	 	
		if(authUser == null){
			 result.put("result", "false");
		}
	     
		else {
			//인증성공
			 result.put("result", "true");
		}
	     return result;
	        
	}
}
