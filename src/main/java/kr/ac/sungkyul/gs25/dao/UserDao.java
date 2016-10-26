package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//안드로이드 로그인 삽입
	public int androidinsert(UserVo vo){
		int check=sqlSession.insert("user.androidinsert",vo);
		return check;
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
	
	//본사관리자 조회 - 총 인원수
		public int totalMember(){
			return sqlSession.selectOne("user.totalCount");
		}
		
		//본사관리자 조회 - CUSTOMER 인원수
		public int getTotalCountC(){
			return sqlSession.selectOne("user.totalCountC");
		}
		
		//본사관리자 조회 - BRANCH 인원수
		public int getTotalCountB(){
			return sqlSession.selectOne("user.totalCountB");
		}
		
		public List<UserVo> getListC(int page, int pagesize, String keyword) {
			Map<String, Object> map = new HashMap<>();

			// 키보드가 null or 비어있을 때 리스트 가져오기
			if (keyword == null || "".equals(keyword)) {

				map.put("page_top", (page - 1) * pagesize + 1);
				map.put("page_bottom", page * pagesize);

				List<UserVo> list = sqlSession.selectList("user.getListC", map);
				System.out.println(list);
				return list;

				// 검색된 리스트 가져오기
			} else {
				map.put("keyword", "%" + keyword + "%");
				map.put("page_top", (page - 1) * pagesize + 1);
				map.put("page_bottom", page * pagesize);

				List<UserVo> list = sqlSession.selectList("user.getListKeyword", map);
				return list;
			}
		}
		
		public List<UserVo> getListB(int page, int pagesize) {
			Map<String, Object> map = new HashMap<>();
			
			map.put("page_top", (page - 1) * pagesize + 1);
			map.put("page_bottom", page * pagesize);

			List<UserVo> listB = sqlSession.selectList("user.getListB", map);
			System.out.println(listB);
			return listB;
		}
		
		public Integer pointuse(Long no, Integer point){
			Map<String, Object> map = new HashMap<>();
			map.put("no", no);
			map.put("point", point);
			
			Integer result = sqlSession.update("user.pointuse",map);
			return result;
		}
		
		public UserVo getPoint(Long user_no){
			
			UserVo vo=sqlSession.selectOne("user.getpoint",user_no);
			return vo;
		}
}
