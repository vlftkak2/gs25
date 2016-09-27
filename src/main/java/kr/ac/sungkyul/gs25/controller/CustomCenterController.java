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
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword)
	{
		Map<String, Object> map = customservice.list(page, keyword);		
		model.addAttribute("map", map);
	
		return "Main_Page/custom_board";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeform(HttpSession session) {

		if (session == null) {
			return "redirect:/main";
		}

		return "/Main_Page/custom_write";
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
	public String delete(HttpSession session, 
			@RequestParam("groupNo") Integer no, 
			@RequestParam("groupOrderNo") Integer orderno,
			@ModelAttribute CustomBoardVo vo) {

		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		customservice.delete(no,orderno);

		customservice.delete(vo);

		return "redirect:/custom/list";
	}

	@RequestMapping("/viewform")
	public String viewfrom(HttpSession session,
			@RequestParam(value = "no", required = false, defaultValue = "1") Long no,
			@RequestParam("groupNo") Integer groupNo,
			Model model) {
		
		System.out.println("그룹번호"+groupNo);
		if (session == null) {
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		Long authno = authUser.getNo();
		if( authno ==null){
			return "redirect:/main/list";
		}
		
		CustomBoardVo DeterminVo =customservice.userno(groupNo); // 사용자 그룹 번호 판별
		Long userno=DeterminVo.getUserNo();
		
		CustomBoardVo vo = customservice.boardinfo(no);

		AttachFileVO attachFileVO = customservice.selectAttachFileByNO(no);

		if (vo == null) {
			return "redirect:/custom/list";
		}
		
		customservice.viewcountup(no);
		
	    if(authno==1){ //사용자가 관리자일 때
	    	vo = customservice.boardinfo(no);
			attachFileVO = customservice.selectAttachFileByNO(no);
			model.addAttribute("vo", vo);
			model.addAttribute("attachFileVO", attachFileVO);
			return "/Main_Page/custom_view";
		}
	    
		if(authno!=userno){ //로그인한 사용자번호와 작성자의 번호가 다를때 권한 위배 표시
			return "redirect:/custom/right";
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("attachFileVO", attachFileVO);
		
		return "/Main_Page/custom_view";
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

		return "/Main_Page/custom_modify";
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


		return "/Main_Page/custom_reply";
	}
	
	@RequestMapping("/reply")
	public String reply(
			HttpSession session,
			@ModelAttribute CustomBoardVo vo,
			MultipartFile file) throws Exception {
		
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
				
		customservice.updatereplyCount(groupNo, groupOrderno);
		
		customservice.reply(vo,file);
		
		
		return "redirect:/custom/list";
	}
	
	@RequestMapping("/right")
	public String right(){
		return "/Main_Page/custom_right";
	}
	
	//파일다운로드
		@RequestMapping(value = "download", method = RequestMethod.GET)
		public void downloadFile(Long fNO, HttpServletResponse res) throws Exception {
			
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
