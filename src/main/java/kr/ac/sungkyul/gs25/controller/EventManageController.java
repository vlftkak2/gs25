package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.EventManageService;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/manage")
public class EventManageController {
	
	@Autowired
	EventManageService eventmanageService;
	
	@RequestMapping("/eventList")
	public String eventList(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword,
			HttpSession session,
			@RequestParam("store_no") Long store_no){
		
		if (session == null) {
			return "redirect:/sub/main";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		// 이벤트 목록 가져오기
		Map<String, Object> map = eventmanageService.getEventList(page, keyword, store_no);

		model.addAttribute("map", map);
		return "/Sub_Page/event_manage";
	}
	
	// 이벤트 번호를 가지고 삭제
	@RequestMapping("/eventDelete")
	public String eventDelete(@RequestParam("no") Long no, 
			@ModelAttribute EventBoardVo vo,
			@RequestParam("store_no") Long store_no){
		eventmanageService.eventDelete(no); // 첨부파일 삭제
		eventmanageService.eventDelete(vo); // 게시물 삭제
		return "redirect:/sub/main?store_no="+store_no;
	}
	
	

}
