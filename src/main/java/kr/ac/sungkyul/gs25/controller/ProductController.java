package kr.ac.sungkyul.gs25.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productservice;

	@RequestMapping("/list")
	public String productlist(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword) {
		Map<String, Object> map = productservice.listBoard(page, keyword);

		model.addAttribute("map", map);

		return "/Sub_Page/product_search";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String productinsertForm(){
		
		return "/Sub_Page/product_insert";
		}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String productinsert(@ModelAttribute ProductVo vo, MultipartFile file) throws Exception{

		productservice.insert(vo,file);
		
		return "redirect:/product/list";
		}

}
