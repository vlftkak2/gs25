package kr.ac.sungkyul.gs25.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.service.CartListService;
import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.CartVo;
import kr.ac.sungkyul.gs25.vo.NblogVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

/*
 2016-10-11 
   작업자 : 최형민
   개발 상황 : 수정
*/


@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productservice;
	
	@Autowired
	CartListService cartservice;
	
	@Autowired
	UserService userservice;

	/*
	 2016-10-14
	   작업자 : 최형민
	   개발 상황 : 추가
	*/
	//메인 상품 검색 리스트
		@RequestMapping("/Mainlist")
		public String productMainlist(Model model,
				@RequestParam(value = "p", required = true, defaultValue = "1") String page,
				@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword) {
						
			//상품 리스트
			Map<String, Object> map = productservice.listBoard(page, keyword);

			model.addAttribute("map", map);

			return "/Main_Page/product_search";
		}
		
		//메인 상품 등록 페이지 이동
		@RequestMapping(value="/Maininsert", method=RequestMethod.GET)
		public String productMaininsertForm(){
			
			return "/Main_Page/product_insert";
			}
		
		//메인페이지 상품 등록
		@RequestMapping(value="/Maininsert", method=RequestMethod.POST)
		public String productMaininsert(@ModelAttribute ProductVo vo, MultipartFile file) throws Exception{

			productservice.insert(vo,file);
			
			return "redirect:/product/Mainlist";
			}
		
		//메인 상품 삭제
		@RequestMapping("/Maindelete")
		public String productdelete(
				@RequestParam("no") Long no,
				HttpSession session){
			
			if (session == null) {
				return "redirect:/main";
			}

			//사용자 세션 정보 얻어오기
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				return "redirect:/main";
			}
			
			//찜목록 상품 삭제
			cartservice.deleteCart(no);
			
			//매장상품 삭제
			productservice.Storedelete(no);
			
			//상품 첨부파일 삭제
			productservice.deletefile(no);

			
			//상품 삭제
			productservice.delete(no);
			
		
			
			
			return "redirect:/product/Mainlist";
		}
	
	//서브 상품 검색 리스트
	@RequestMapping("/list")
	public String productlist(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword,
			HttpSession session,
			@RequestParam("store_no") Long StoreNo) {
		
		model.addAttribute("StoreNo", StoreNo);
		
		//상품 리스트
		Map<String, Object> map2 = productservice.listBoard(page, keyword,StoreNo);
		
		model.addAttribute("map2", map2);

		return "/Sub_Page/product_search";
	}
	
	
	//서브 상품 등록 페이지 이동
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String productinsertForm(
			Model model,
			@RequestParam("store_no") Long store_no){
		
		//매장 이름  정보얻기
		StoreProductVo StoreVo=productservice.getStoreName(store_no);
		model.addAttribute("StoreVo", StoreVo);
		
		//본사 상품 리스트 정보 가져오기
		 List<ProductVo> Productlist=productservice.listBoard();
		 model.addAttribute("Productlist", Productlist);
		 
		//매장 번호 정보 얻기
		model.addAttribute("store_no", store_no);
		return "/Sub_Page/product_insert";
		}
	
	//서브페이지 상품 등록
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String productinsert(@ModelAttribute StoreProductVo vo,
			@RequestParam("store_no") Long store_no) throws Exception{

		productservice.insert(vo);
		
		return "redirect:/product/list?store_no="+store_no;
		}
	
	//상품 삭제
	@RequestMapping("/Subdelete")
	public String productSubdelete(
			@RequestParam("product_no") Long product_no,
			@RequestParam("store_no") Long store_no,
			HttpSession session){
		
		if (session == null) {
			return "redirect:/sub/main";
	}

		//사용자 세션 정보 얻어오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		
		
		//상품 삭제
		productservice.Subdelete(product_no,store_no);
		
		//등록된 찜목록 삭제
		cartservice.deleteCart(product_no, store_no);
		
		return "redirect:/product/list?store_no="+store_no;
	}
	
	
	
	/*
	 2016-10-05 
	   작업자 : 최재은
	   개발 상황 : 추가
	*/
	
	//상품 상세 페이지 이동
		@RequestMapping(value="/view", method=RequestMethod.GET)
		public String productView(Model model,
				@RequestParam(value= "no") Long no,
				@RequestParam(value="name") String name,
				@RequestParam(value="store_no") Long store_no,
				HttpSession session){
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser != null){
			Long user_no = authUser.getNo();
			
			//매장번호 넘기기
			model.addAttribute("store_no", store_no);
			
			CartVo checkVo = new CartVo();
			checkVo = productservice.maintainCheck(user_no, no);
			model.addAttribute("checkVo", checkVo);
			
			StoreProductVo vo = productservice.productInfo(no,store_no);
			model.addAttribute("prodvo", vo);
			

			//사용자 포인트 정보 얻기
			UserVo uservo=userservice.getPoint(user_no);
			model.addAttribute("uservo", uservo);
			
			}else{
				
			//매장번호 넘기기
			model.addAttribute("store_no", store_no);	
			
			//상품 정보
			StoreProductVo vo = productservice.productInfo(no,store_no);
			
			model.addAttribute("prodvo", vo);
			}
			
			List<NblogVo> nvo = productservice.searchNBlog(name);
			model.addAttribute("nvo", nvo);
			
			//조회 수 증가
			productservice.viewcountup(no);
			
			return "/Sub_Page/product_view";
		}
		

		@ResponseBody
		@RequestMapping("/random1000")	//출석체크 클릭 시
		public StoreProductVo random1000(HttpSession session, Long store_no){
			
			UserVo uservo = (UserVo)session.getAttribute("authUser");
			Long user_no = uservo.getNo();
			
			StoreProductVo storeproductvo = productservice.random1000(user_no, store_no);
			
			return storeproductvo;
		}
		
		@ResponseBody
		@RequestMapping("/random2000")	//출석체크 클릭 시
		public StoreProductVo random2000(HttpSession session, Long store_no){
			
			UserVo uservo = (UserVo)session.getAttribute("authUser");
			Long user_no = uservo.getNo();
			
			StoreProductVo storeproductvo = productservice.random2000(user_no, store_no);
			return storeproductvo;
		}
		
}
