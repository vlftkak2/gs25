package kr.ac.sungkyul.gs25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.UserDao;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao usersdao;
	
	public void join(UserVo vo){
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
	
	public void updateInfo(UserVo vo){
			usersdao.update(vo);
		
	}
	
	public String idfind(UserVo vo){	//회원정보 수정 시 정보 출력
		String email = usersdao.find(vo);
		return email;
	}
}
