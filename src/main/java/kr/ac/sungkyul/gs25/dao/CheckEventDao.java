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
	
	public Integer getCount(Long user_no){
		Integer count = sqlSession.selectOne("checkevent.getCount",user_no);
		return count;
	}
	
	public Integer setCheck(Long user_no){
		Integer resultInt = sqlSession.insert("checkevent.setCheck",user_no);
		
		UserVo uservo = new UserVo();
		Integer point = 3;

		uservo.setNo(user_no);
		uservo.setPoint(point);

		sqlSession.update("user.setPoint",uservo);
		
		return resultInt;
	}
	
	public List<CheckeventVo> checkList(Long user_no){
		List<CheckeventVo> checkeventvo = sqlSession.selectList("checkevent.checkList",user_no);
		System.out.println(checkeventvo);
		return checkeventvo;
	}

}
