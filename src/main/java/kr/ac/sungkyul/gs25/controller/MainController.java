package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main(){
		
		return "/main/index";
	}
	
	@RequestMapping("/membership")
	public String membership(){
		return "/main/membership";
	}
	
	@RequestMapping("/companyintro")
	public String companyintro(){
		return "/main/companyintro";
	}
	
	@RequestMapping("/test")
	public String test(){
		
		return "/test/test";
	}

}
