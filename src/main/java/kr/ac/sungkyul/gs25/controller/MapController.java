package kr.ac.sungkyul.gs25.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.MapService;
import kr.ac.sungkyul.gs25.service.RegionService;
import kr.ac.sungkyul.gs25.service.StoreService;
import kr.ac.sungkyul.gs25.vo.InsertStoreVo;
import kr.ac.sungkyul.gs25.vo.RegionVo;

/*
  2016-10-01 
     작업자 : 최형민
     개발 상황 : 완료
 */


@Controller
@RequestMapping("/map")
public class MapController {
	

	@Autowired
	MapService mapservice;
	
	@Autowired
	StoreService storeservice;
	
	@Autowired
	RegionService regionservice;
	
	
	//매장 검색 리스트
	@RequestMapping("/list")
	public String maplist(Model model,
			@RequestParam(value="p",required=true,defaultValue="1") String page,
			@RequestParam(value="kwd",required=true, defaultValue="") String keyword,
			@RequestParam(value="no", required=true, defaultValue="1") Long no){
		
		//게시판 리스트
		Map<String, Object> map=mapservice.list(page, keyword); 
		
		//지도 리스트
		Map<String, Object> map2=mapservice.maplist(keyword, no); 

		//게시판 리스트 객체 넘기기
		model.addAttribute("map", map); 
		 
		//지도 리스트 객체 넘기기
		model.addAttribute("map2", map2);

		return "/Main_Page/store_search";
	}
	
	//본사관리자  지도 조회
		@RequestMapping(value = "mlist")
		public String mlist(Model model, 
				@RequestParam(value = "p", required = true, defaultValue = "1") String page,
				@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword,
				@RequestParam(value="no", required=true, defaultValue="1") Long no){//Request 객체받음, script or DB 객체 분별
			
			Map<String, Object> map = mapservice.mlist(page, keyword);
			Map<String, Object> map2=mapservice.maplist(keyword, no); //지도 리스트
			
			model.addAttribute("map", map);
			model.addAttribute("map2", map2);
			
			return "/Main_Page/mapManager";
		}
		
		//본사관리자 지점 삽입
		@RequestMapping(value = "insertForm")
		public String insertForm(Model model){
			
			List<RegionVo> regionvo = regionservice.research();
			System.out.println(regionvo.toString());
			model.addAttribute("regionvo",regionvo);
			
			return "/Main_Page/insertStore";
		}
		
		@RequestMapping(value = "insert")
		public String insert(@ModelAttribute InsertStoreVo vo){
			
			System.out.println(vo.toString());
			mapservice.insert(vo);
			
			return "redirect:/map/mlist";
		}
		
		@RequestMapping(value = "delete")
		public String delete(@RequestParam(value = "no", required = true) Long no){
			
			storeservice.delete(no);		
			return "redirect:/map/mlist";
		}
	
	
}
