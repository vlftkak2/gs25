package kr.ac.sungkyul.gs25.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.CheckEventDao;
import kr.ac.sungkyul.gs25.vo.CheckeventVo;

@Service
public class CheckeventService {
	
	@Autowired
	CheckEventDao checkeventdao;

	public Integer getCount(Long user_no, Long store_no) { // 출첵 횟수 가져오기
		CheckeventVo checkeventvo = new CheckeventVo();
		checkeventvo.setUser_no(user_no);
		checkeventvo.setStore_no(store_no);
		System.out.println("서비스: "+checkeventvo);
		Integer count = checkeventdao.getCount(checkeventvo);
		System.out.println("service count: "+count);
		return count;
	}
	
	public String setCheck(Long user_no, Long store_no) { // 출첵 횟수 가져오기
		CheckeventVo checkeventvo = new CheckeventVo();
		checkeventvo.setUser_no(user_no);
		checkeventvo.setStore_no(store_no);
		
		Integer resultInt = checkeventdao.setCheck(checkeventvo);
		
		String result = String.valueOf(resultInt);
		
		return result;
	}
	
	public List<CheckeventVo> checkList(Long user_no, Long store_no){
		CheckeventVo checkeventvo = new CheckeventVo();
		checkeventvo.setUser_no(user_no);
		checkeventvo.setStore_no(store_no);
		
		List<CheckeventVo> checkvo = checkeventdao.checkList(checkeventvo);
		return checkvo;
	}
	

}
