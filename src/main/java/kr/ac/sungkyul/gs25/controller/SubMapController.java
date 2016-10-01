package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.MapService;

/*
 2016-10-01 
   작업자 : 최형민
   개발 상황 : 완료
*/

@Controller
@RequestMapping("/submap")
public class SubMapController {
	
	@Autowired
	MapService mapservice;
	
	//매장 검색 리스트
	@RequestMapping("/sublist")
	public String maplist(Model model,
			@RequestParam(value="p",required=true,defaultValue="1") String page,
			@RequestParam(value="kwd",required=true, defaultValue="") String keyword,
			@RequestParam(value="no", required=true, defaultValue="1") Long no){
		
		//게시판 리스트
		Map<String, Object> map=mapservice.list(page, keyword); 
		
		//지도 리스트
		Map<String, Object> map2=mapservice.maplist(keyword, no); 

		
		model.addAttribute("map", map);
		model.addAttribute("map2", map2);

		
		return "/Sub_Page/store_search";
	}

}
