package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 2016-09-15 
  작업자 : 최솔빈
  개발 상황 : 완료
*/

@Controller
public class MainController {
	
	//메인 페이지 이동
	@RequestMapping("/main")
	public String main(){
		
		return "/Main_Page/index";
	}
	
	//멤머십 페이지 이동
	@RequestMapping("/membership")
	public String membership(){
		return "/Main_Page/membership";
	}
	
	//회사 소개 이동
	@RequestMapping("/companyintro")
	public String companyintro(){
		return "/Main_Page/companyintro";
	}
	


}
