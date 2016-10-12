package kr.ac.sungkyul.gs25.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.PassLinkVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert",vo);
	}


	public UserVo login(String email, String password) { // login
		
		UserVo userVo = new UserVo();
		userVo.setEmail(email);
		userVo.setPassword(password);

		UserVo vo = sqlSession.selectOne("user.login", userVo);
		
		return vo;
	}

	public UserVo get(Long no) {	//회원 정보 수정 시 정보 가져옴
		
		UserVo vo = sqlSession.selectOne("user.getModify",no);
//		System.out.println("정보수정"+vo.toString());
		return vo;
	}

	public void update(UserVo vo) {	//회원정보 수정
		
		sqlSession.update("user.update",vo);
	}

	public String find(UserVo vo) { // id find
		
		String email = sqlSession.selectOne("user.idFind",vo);
		return email;
	}

	public String passfind(UserVo vo) { // password find check
		
		String email = sqlSession.selectOne("user.passFind",vo);
		return email;
	}

	public Integer setPass(Long no, String password) { // password 재설정
		
		UserVo userVo = new UserVo();
		userVo.setNo(no);
		userVo.setPassword(password);
		
		Integer result = sqlSession.update("user.setPass",userVo);
		return result;
	}
	
	public void setState(Long user_no, Integer state) { // password 재설정
		
		PassLinkVo passlinkvo = new PassLinkVo();
		passlinkvo.setUser_no(user_no);
		passlinkvo.setState(state);
		
		sqlSession.update("user.setState",passlinkvo);
	}

	public Long checkEmail(String email) { // email 유효성 검사
		
		Long no = sqlSession.selectOne("user.checkEmail",email);
		return no;
	}
	
	public void savelink(String link, String email) {
		
		Long user_no = sqlSession.selectOne("user.checkEmail",email); // no 가져옴
		Integer state = 0;
		
		PassLinkVo passlinkvo = new PassLinkVo();
		passlinkvo.setLink(link);
		passlinkvo.setState(state);
		passlinkvo.setUser_no(user_no);
		
		sqlSession.insert("user.savelink",passlinkvo);
	}
	
	public PassLinkVo passlink(String link) {
		
		PassLinkVo plVo = sqlSession.selectOne("user.passlink",link); // no 가져옴
		return plVo;
	}
}
