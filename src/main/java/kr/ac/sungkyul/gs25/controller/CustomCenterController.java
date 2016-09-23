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

@Controller
@RequestMapping("/custom")
public class CustomCenterController {

	@Autowired
	CustomCenterService customservice;

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword) {
		Map<String, Object> map = customservice.list(page, keyword);
		model.addAttribute("map", map);

		return "MainPage/customcenter/customboardlist";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeform(HttpSession session) {

		if (session == null) {
			return "redirect:/main";
		}

		return "/MainPage/customcenter/write";
	}

	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute CustomBoardVo vo, HttpSession session, MultipartFile file) throws Exception {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		
		vo.setUserNo(authUser.getNo());
		customservice.write(vo,file);

		return "redirect:/custom/list";
	}

	@RequestMapping("/delete")
	public String delete(HttpSession session, @RequestParam("no") Long no, @ModelAttribute CustomBoardVo vo) {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setNo(no);

		customservice.delete(vo);

		return "redirect:/custom/list";
	}

	@RequestMapping("/viewform")
	public String viewfrom(HttpSession session,
			@RequestParam(value = "no", required = false, defaultValue = "1") Long no, Model model) {

		if (session == null) {
			return "redirect:/main";
		}

		CustomBoardVo vo = customservice.boardinfo(no);
		AttachFileVO attachFileVO = customservice.selectAttachFileByNO(no);
		System.out.println(attachFileVO);

		if (vo == null) {
			return "redirect:/custom/list";
		}
		model.addAttribute("vo", vo);
		model.addAttribute("attachFileVO", attachFileVO);


		customservice.viewcountup(no);
		

		return "/MainPage/customcenter/view";
	}

	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, @RequestParam("no") Long no, Model model) {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}

		CustomBoardVo vo = customservice.boardinfo(no);
		model.addAttribute("vo", vo);

		return "/MainPage/customcenter/modify";
	}

	@RequestMapping("/modify")
	public String modify(HttpSession session, @RequestParam("no") Long no, @ModelAttribute CustomBoardVo vo) {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setNo(no);

		customservice.modify(vo);

		return "redirect:/custom/list";
	}

	@RequestMapping("/replyform")
	public String replyform(HttpSession session, @RequestParam("no") Long no, Model model) {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}

		CustomBoardVo vo = customservice.boardinfo(no);
		model.addAttribute("vo", vo);

		System.out.println(vo);

		return "/MainPage/customcenter/reply";
	}
	
	@RequestMapping("/reply")
	public String reply(
			HttpSession session,
			@ModelAttribute CustomBoardVo vo){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		int depth=vo.getDepth()+1;
		int groupOrderno=vo.getGroupOrderNo()+1;
		int groupNo=vo.getGroupNo();
		

		vo.setDepth(depth);
		vo.setGroupOrderNo(groupOrderno);
		vo.setGroupNo(groupNo);
		
		System.out.println(vo);
		
		customservice.updatereplyCount(groupNo, groupOrderno);
		
		customservice.reply(vo);
		
		
		return "redirect:/custom/list";
	}
	
	@RequestMapping("/right")
	public String right(){
		return "/MainPage/customcenter/right";
	}
	
	//파일다운로드
		@RequestMapping(value = "download", method = RequestMethod.GET)
		public void downloadFile(Long fNO, HttpServletResponse res) throws Exception {
			System.out.println(fNO);
			
			
			AttachFileVO attachFileVO = customservice.selectAttachFileByFNO(fNO);
			
			String saveName = attachFileVO.getSaveName();
			String orgName = attachFileVO.getOrgName();
			    
			    
			    
			res.setContentType("application/download");
			res.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(orgName,"UTF-8") +"\"");
			OutputStream resOut = res.getOutputStream();
			
			FileInputStream fin = new FileInputStream("c:\\upload\\"+saveName);
			FileCopyUtils.copy(fin, resOut);
				
			fin.close();
			    
		}
		
		
	
	

}
