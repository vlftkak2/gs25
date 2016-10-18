package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.GuestManageService;
import kr.ac.sungkyul.gs25.vo.GuestbookVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/guestmanage")
public class GuestManageController {
	
	@Autowired
	GuestManageService guestmanageService;

	@RequestMapping("/guestList")
	public String guestbookList(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword, HttpSession session,
			@RequestParam("store_no") Long store_no) {
		if (session == null) {
			return "redirect:/sub/main";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		// 방명록 목록 가져오기
		Map<String, Object> map = guestmanageService.getGuestbookList(page, keyword, store_no);
		model.addAttribute("map", map);
		model.addAttribute("store_no", store_no);
		System.out.println(store_no);
		return "/Sub_Page/guest_manage";
	}

	// 선택된 방명록 삭제
	@RequestMapping("/guestDelete")
	public String delete(@ModelAttribute GuestbookVo vo, 
			@RequestParam("store_no") Long store_no) {

		guestmanageService.delete(vo);
		return "redirect:/guestmanage/guestList?store_no="+store_no;
	}

}
