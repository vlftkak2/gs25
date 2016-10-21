package kr.ac.sungkyul.gs25.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.GifticonVo;

@Repository
public class GifticonDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 기프티콘 삽입 (출첵 상품증정)
	public void insert(GifticonVo gifticonvo) {
	
		
		sqlSession.insert("gifticon.insert",gifticonvo);
	}

}
