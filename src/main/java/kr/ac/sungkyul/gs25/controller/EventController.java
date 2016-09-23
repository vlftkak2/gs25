package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@RequestMapping("/check")
	public String EventCheck(){
		
		return "/SubPage/event_check";
	}

}
