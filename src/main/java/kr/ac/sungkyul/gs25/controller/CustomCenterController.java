package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.CustomCenterService;

@Controller
@RequestMapping("/custom")
public class CustomCenterController {
	
	@Autowired
	CustomCenterService customservice;
	
	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(value="p", required=true, defaultValue="1") String page,
			@RequestParam(value="kwd", required=false, defaultValue="") String keyword){
		Map<String, Object> map = customservice.list(page, keyword);
		model.addAttribute("map",map);
		
		return "customcenter/customboardlist";
	}

}
