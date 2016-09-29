package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	@RequestMapping("/main")
	public String SubMain(){
		
		return "Sub_Page/sub_index";
	}

}
