package kr.ac.sungkyul.gs25.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.CustomCenterService;
import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
public class AndroidDispatcher {
	

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CustomCenterService customservice;
	
	//안드로이드 로그인
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> androidLogin(HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("password") String password){
		
		System.out.println("이메일"+email);
		System.out.println("비밀번호"+password);
	
	     Map<String, String> result = new HashMap<String, String>();
	     
	 	UserVo authUser =  userService.login(email,  password);
	 	
		if(authUser == null){
			 result.put("result", "false");
		}
	     
		else {
			//인증성공
			 result.put("result", "true");
		}
	     return result;
	        
	}
	
	//안드로이드 회원가입
			@RequestMapping(value = "txtSignUp", method = RequestMethod.POST)
			@ResponseBody
			public Map<String, String>  androidSignUp(HttpServletRequest request,
					@RequestParam("name") String name,
					@RequestParam("email") String email,
					@RequestParam("password") String password,
					@RequestParam("Birth") String birth,
					@RequestParam("gender") String gender,
					@RequestParam("address") String address,
					@RequestParam("phone") String phone){
				
				System.out.println("name : "+name);
				System.out.println("email :"+email);
				System.out.println("password : "+password);
				System.out.println("birth :"+birth);
				System.out.println("gender : "+gender);
				System.out.println("address : "+address);
				System.out.println("phone : "+phone);

				
				UserVo vo=new UserVo();
				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setBirth(birth);
				vo.setGender(gender);
				vo.setAddress(address);
				vo.setPhone(phone);
				
				int Check=userService.Androidjoin(vo);
				System.out.println(Check);
				
			     Map<String, String> result = new HashMap<String, String>();
			     
			     if(Check == 0){
					 result.put("result", "false");
				}
			     
				else {
					//인증성공
					 result.put("result", "true");
				}
				return result;
			}
			
			
			//안드로이드 상품 리스트
			@RequestMapping(value="getProductList",method=RequestMethod.GET)
			@ResponseBody
			public List<StoreProductVo> getProductList(){
				
				List<StoreProductVo> list=productservice.getListBoard();
				System.out.println(list);				
				
				return list;
			}
			
			//안드로이드 고객센터 리스트
			@RequestMapping(value="getCustomList", method=RequestMethod.GET)
			@ResponseBody
			public List<CustomBoardVo> getCustomList(){
				
				List<CustomBoardVo> list=customservice.getListCustomBoard();
				System.out.println(list);
				
				return list;
			}
			
}