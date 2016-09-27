package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main(){
		
		return "/Main_Page/index";
	}
	
	@RequestMapping("/membership")
	public String membership(){
		return "/Main_Page/membership";
	}
	
	@RequestMapping("/companyintro")
	public String companyintro(){
		return "/Main_Page/companyintro";
	}
	


}
