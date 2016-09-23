package kr.ac.sungkyul.gs25.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.UserDao;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao usersdao;
	
	public void join(UserVo vo){
		System.out.println(vo);
		usersdao.insert(vo);
	}
	
	public UserVo login(String email, String password){
		UserVo authUser = usersdao.get(email,password);
		return authUser;
	}
	
	public UserVo get(Long no){	//회원정보 수정 시 정보 출력
		UserVo uservo = usersdao.get(no);
		return uservo;
	}
	
	public void update(UserVo vo){
		usersdao.update(vo);
	}
	
	public void update(String tempPass){
		usersdao.update(tempPass);
	}
	
	public void updateInfo(UserVo vo){
	
			usersdao.update(vo);
		
	}
	
	public String idfind(UserVo vo){	//아이디 찾기
		String email = usersdao.find(vo);
		return email;
	}
	
	public void setpass(String email,String password){	//비밀번호 찾기 후 재설정
		usersdao.setPass(email,password);
	}
	
	public Map<String, Object> checkEmail(String email){	//아이디 유효성 검사
		Long no = usersdao.checkEmail(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", no != null);
		
		return map;
	}
	
}
