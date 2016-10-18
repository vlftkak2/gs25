package kr.ac.sungkyul.gs25.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.CheckeventVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Repository
public class CheckEventDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Integer getCount(CheckeventVo checkeventvo){
		
		Integer count = sqlSession.selectOne("checkevent.getCount",checkeventvo);
		return count;
	}
	
	public Integer setCheck(CheckeventVo checkeventvo){
		Integer resultInt = sqlSession.insert("checkevent.setCheck", checkeventvo);
		
		UserVo uservo = new UserVo();
		
		Integer point = 3;
		Long user_no = checkeventvo.getUser_no();

		uservo.setNo(user_no);
		uservo.setPoint(point);

		sqlSession.update("user.setPoint",uservo);
		
		return resultInt;
	}
	
	public List<CheckeventVo> checkList(CheckeventVo checkeventvo){
		List<CheckeventVo> checkvo = sqlSession.selectList("checkevent.checkList",checkeventvo);
		System.out.println(checkvo);
		return checkvo;
	}

}
