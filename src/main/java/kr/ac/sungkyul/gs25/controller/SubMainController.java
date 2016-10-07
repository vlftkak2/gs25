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
		List<ProductVo> vo = productservice.getSubDate();
		model.addAttribute("vo",vo);
		
		//신상품
		List<ProductVo> vo3 = productservice.getSubNew();
		model.addAttribute("vo3",vo3);
		
		List<ProductVo> vo4 = productservice.getSubReco();
		model.addAttribute("vo4",vo4);
		
		return "Sub_Page/sub_index";
	}

}
