package kr.ac.sungkyul.gs25.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

/*
 2016-10-01 
   작업자 : 최형민
   개발 상황 : 완료
*/


@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productservice;

	//상품 검색 리스트
	@RequestMapping("/list")
	public String productlist(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword) {
		Map<String, Object> map = productservice.listBoard(page, keyword);

		model.addAttribute("map", map);

		return "/Sub_Page/product_search";
	}
	
	//상품 등록 페이지 이동
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String productinsertForm(){
		
		return "/Sub_Page/product_insert";
		}
	
	//상품 등록
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String productinsert(@ModelAttribute ProductVo vo, MultipartFile file) throws Exception{

		productservice.insert(vo,file);
		
		return "redirect:/product/list";
		}
	
	//상품 삭제
	@RequestMapping("/delete")
	public String productdelete(
			@RequestParam("no") Long no,
			HttpSession session){
		
		if (session == null) {
			return "redirect:/sub/main";
		}

		//사용자 세션 정보 얻어오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		
		//상품 첨부파일 삭제
		productservice.deletefile(no);
		
		//상품 삭제
		productservice.delete(no);
		
		return "redirect:/product/list";
	}

}
