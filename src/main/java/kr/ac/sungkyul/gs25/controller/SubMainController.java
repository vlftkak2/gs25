package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
2016-10-01 
  작업자 : 최형민
  개발 상황 : 완료
*/

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	//서브 메인 페이지 이동
	@RequestMapping("/main")
	public String SubMain(){
		
		return "Sub_Page/sub_index";
	}

}
