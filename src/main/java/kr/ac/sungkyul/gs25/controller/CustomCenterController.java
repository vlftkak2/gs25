package kr.ac.sungkyul.gs25.controller;


import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.service.CustomCenterService;
import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

/*
 2016-10-01 
   작업자 : 최형민
   개발 상황 : 완료
*/

@Controller
@RequestMapping("/custom")
public class CustomCenterController {

	@Autowired
	CustomCenterService customservice;

	//고객센터 게시물 리스트
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword)
	{
		//게시물 리스트
		Map<String, Object> map = customservice.list(page, keyword);		
		model.addAttribute("map", map);
	
		return "Main_Page/custom_board";
	}

	//고객센터 글쓰기 폼 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeform(HttpSession session) {

		if (session == null) {
			return "redirect:/main";
		}

		return "/Main_Page/custom_write";
	}

	//고객센터 글쓰기 이벤트
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute CustomBoardVo vo, HttpSession session, MultipartFile file) throws Exception {

		if (session == null) {
			return "redirect:/main";
		}
		
		//사용자 세션 정보 얻어오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		
		vo.setUserNo(authUser.getNo());
		
		//글쓰기를 작업할 때 객체와,file정보를 넘김
		customservice.write(vo,file);

		return "redirect:/custom/list";
	}

	//고객센터 글쓰기 삭제
	@RequestMapping("/delete")
	public String delete(HttpSession session, 
			@RequestParam("groupNo") Integer no, 
			@RequestParam("groupOrderNo") Integer orderno,
			@ModelAttribute CustomBoardVo vo) {

		if (session == null) {
			return "redirect:/main";
		}

		//사용자 세션 정보 얻어오기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		
		//게시물 첨부파일 삭제
		customservice.delete(no,orderno);

		//게시물 삭제
		customservice.delete(vo);

		return "redirect:/custom/list";
	}
	
	//관리자 고객센터 글쓰기 삭제
		@RequestMapping("/deleteManager")
		public String deleteManager(HttpSession session, 
				@RequestParam("groupNo") Integer no, 
				@RequestParam("groupOrderNo") Integer orderno,
				@ModelAttribute CustomBoardVo vo) {

			if (session == null) {
				return "redirect:/main";
			}

			//사용자 세션 정보 얻어오기
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				return "redirect:/main";
			}
			
			//게시물 첨부파일 삭제
			customservice.delete(no,orderno);

			//게시물 삭제
			customservice.delete(vo);

			return "redirect:/custom/list";
		}

	//고객센터 보기 폼 이동
	@RequestMapping("/viewform")
	public String viewfrom(HttpSession session,
			@RequestParam(value = "no", required = false, defaultValue = "1") Long no,
			@RequestParam("groupNo") Integer groupNo,
			Model model) {
		
		System.out.println("그룹번호"+groupNo);
		if (session == null) {
			return "redirect:/main";
		}
		
		//사용자 세션 정보 얻어오기
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		//사용자 세션의 번호값을 얻기
		Long authno = authUser.getNo();
		if( authno ==null){
			return "redirect:/main/list";
		}
		
		// 사용자 그룹 번호 판별
		CustomBoardVo DeterminVo =customservice.userno(groupNo); 
		Long userno=DeterminVo.getUserNo();
		
		//게시물의 정보 얻기
		CustomBoardVo vo = customservice.boardinfo(no);

		//첨부파일의 정보 얻기
		AttachFileVO attachFileVO = customservice.selectAttachFileByNO(no);

		if (vo == null) {
			return "redirect:/custom/list";
		}
		
		//게시물 뷰 카운트 증가
		customservice.viewcountup(no);
		
		 //사용자가 관리자일 때
	    if(authno==100){
	    	vo = customservice.boardinfo(no);
			attachFileVO = customservice.selectAttachFileByNO(no);
			model.addAttribute("vo", vo);
			model.addAttribute("attachFileVO", attachFileVO);
			return "/Main_Page/custom_view";
		}
	    
	    //로그인한 사용자번호와 작성자의 번호가 다를때 권한 위배 표시
		if(authno!=userno){ 
			return "redirect:/custom/right";
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("attachFileVO", attachFileVO);
		
		return "/Main_Page/custom_view";
	}

	//고객센터 수정 폼 이동
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, @RequestParam("no") Long no, Model model) {

		if (session == null) {
			return "redirect:/main";
		}

		//사용자 세션의 정보 얻기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}

		//게시물의 정보 얻기
		CustomBoardVo vo = customservice.boardinfo(no);
		model.addAttribute("vo", vo);

		return "/Main_Page/custom_modify";
	}

	//고객센터 글쓰기 수정 이벤트
	@RequestMapping("/modify")
	public String modify(HttpSession session, @RequestParam("no") Long no, @ModelAttribute CustomBoardVo vo) {

		if (session == null) {
			return "redirect:/main";
		}

		//사용자 세션의 정보 얻기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setNo(no);

		customservice.modify(vo);

		return "redirect:/custom/list";
	}

	//고객센터 답글 달기 폼 이동
	@RequestMapping("/replyform")
	public String replyform(HttpSession session, @RequestParam("no") Long no, Model model) {

		if (session == null) {
			return "redirect:/main";
		}

		//사용자 세션의 정보 얻기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		
		//게시물 정보 얻기
		CustomBoardVo vo = customservice.boardinfo(no);
		model.addAttribute("vo", vo);


		return "/Main_Page/custom_reply";
	}
	
	//고객센터 답글 달기 이벤트
	@RequestMapping("/reply")
	public String reply(
			HttpSession session,
			@ModelAttribute CustomBoardVo vo,
			MultipartFile file) throws Exception {
		
		if(session==null){
			return "redirect:/main";
		}
		
		//사용자 세션의 정보 얻기
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		//게시물 깊이, 그룹 내 순서 1씩 증가
		int depth=vo.getDepth()+1;
		int groupOrderno=vo.getGroupOrderNo()+1;
		int groupNo=vo.getGroupNo();
		

		vo.setDepth(depth);
		vo.setGroupOrderNo(groupOrderno);
		vo.setGroupNo(groupNo);
		
		//중간에 들어오는 게시물 순서 정렬
		customservice.updatereplyCount(groupNo, groupOrderno);
		
		//답글 달기 이벤트 시 객체와 파일 정보 넘김
		customservice.reply(vo,file);
		
		return "redirect:/custom/list";
	}
	
	//권한 위배시 발생되는 이벤트
	@RequestMapping("/right")
	public String right(){
		return "/Main_Page/custom_right";
	}
	
		//파일다운로드
		@RequestMapping(value = "download", method = RequestMethod.GET)
		public void downloadFile(Long fNO, HttpServletResponse res) throws Exception {
			
			//첨부파일에 대한 정보를 가져오기
			AttachFileVO attachFileVO = customservice.selectAttachFileByFNO(fNO);
			
			String saveName = attachFileVO.getSaveName();
			String orgName = attachFileVO.getOrgName();
			
			//다운로드 세팅
			res.setContentType("application/download");
			res.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(orgName,"UTF-8") +"\"");
			OutputStream resOut = res.getOutputStream();
			
			FileInputStream fin = new FileInputStream("c:\\upload\\"+saveName);
			FileCopyUtils.copy(fin, resOut);
				
			fin.close();
			    
		}
		
		//고객센터 관리자
		//고객센터 관리자
		@RequestMapping("/customboardManager")
		public String customboardManager(Model model, @RequestParam(value = "p", required = true, defaultValue = "1") String page,
				@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword){
			//게시물 리스트
			Map<String, Object> map = customservice.list(page, keyword);		
			model.addAttribute("map", map);
			return "/Main_Page/customboardManager";   
		}
}
