package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.CartListService;
import kr.ac.sungkyul.gs25.vo.CartVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

/* 
2016-10-10
작업자 : 최솔빈
개발 상황 :
*/

@Controller
@RequestMapping("/cart")
public class CartListController {
	@Autowired
	private CartListService cartlistService;
	
	//찜목록 리스트
	@RequestMapping("/list")
	public String list(Model model,
	        @RequestParam(value = "p", required = true, defaultValue = "1") Long page,
	        @RequestParam(value="store_no") Long store_no,
	        HttpSession session){
		
		
		if (session == null) {
			return "redirect:/sub/main";
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser!=null){
			Long user_no = authUser.getNo();
			Map<String, Object> map = cartlistService.list(page,user_no,store_no);
		     model.addAttribute("map",map);
			
		}else{
			return "redirect:/sub/main?store_no="+store_no;
		}
	     
	     return "/Sub_Page/cart_list";
   }
	
	//찜목록 등록
	@ResponseBody
	@RequestMapping("/write")
	public String write(Long product_no, HttpSession session,Long store_no){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long user_no = authUser.getNo();

		String result = cartlistService.write(user_no,product_no,store_no);
		return result;
	}
	
	//찜목록 해제
	@ResponseBody
	@RequestMapping("/relieve")
	public String relieve(Long product_no, HttpSession session,Long store_no){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long user_no = authUser.getNo();
		
		String result = cartlistService.relieve(user_no,product_no,store_no);
		
		return result;
	}
	
	//상품 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "") Long product_no, 
			HttpSession session,
			@RequestParam("store_no") Long store_no){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long user_no = authUser.getNo();
		cartlistService.deleteCart(user_no,product_no,store_no);
		return "redirect:/cart/list?store_no="+store_no;
	}
}
