package kr.ac.sungkyul.gs25.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.RegionVo;

@Repository
public class RegionDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<RegionVo> research() {
		
		//지역 정보
		List<RegionVo> regionvo= sqlSession.selectList("region.selectList");
		return regionvo;
	}

}
