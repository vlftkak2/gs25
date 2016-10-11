package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.ProductVo;

/*
2016-10-01 
  작업자 : 최형민
  개발 상황 : 완료
*/

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	@Autowired
	ProductService productservice;
	
	//서브 메인 페이지 이동
	@RequestMapping("/main")
	public String SubMain(Model model){
		
		//유통기한
		List<ProductVo> expiryVo = productservice.getSubDate();
		model.addAttribute("expiryVo",expiryVo);
		
		//인기상품
		List<ProductVo> popularity=productservice.getSubPopular();
		model.addAttribute("popularityVo", popularity);
		
		//신상품
		List<ProductVo> newProductVo = productservice.getSubNew();
		model.addAttribute("newProductVo",newProductVo);
		
		//추천상품
		List<ProductVo> recommendVo = productservice.getSubReco();
		model.addAttribute("recommendVo",recommendVo);
		
		
		return "Sub_Page/sub_index";
	}
	
	

}
