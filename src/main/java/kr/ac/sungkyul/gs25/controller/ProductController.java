package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	@RequestMapping("/list")
	public String productlist(
			Model model,
			@RequestParam(value="p",required=true,defaultValue="1") String page,
			@RequestParam(value="kwd",required=false,defaultValue="") String keyword){
		
		Map<String, Object> map=productservice.listBoard(page, keyword);
		System.out.println(map);
		model.addAttribute("map", map);
		
		return "/SubPage/product_search";
	}
	
}
