package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.GuestbookVo;

/* 
2016-10-01  
작업자 : 최솔빈
개발 상황 : 완료   
*/

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
		// 방명록 총 갯 수
		public int getCal(){
			int totalCount = sqlSession.selectOne("guestbook.getCal");
			return totalCount;
		}
		
		// 방명록 리스트
		public List<GuestbookVo> getList(Long page,int pagesize, Long store_no){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("page_top", (page-1)*pagesize + 1); // 처음 페이지 그리기
			map.put("page_bottom", page * pagesize); // 마지막 페이지 그리기
			map.put("store_no", store_no); // 해당 매장 번호
			
			List<GuestbookVo> list = sqlSession.selectList("guestbook.getList", map);
			return list;
		}
		
		// 방명록 삽입
		public void insert(GuestbookVo vo){
			sqlSession.insert("guestbook.insert", vo);
		}
		// 선택된 글 삭제
		public void delete(GuestbookVo vo){
			sqlSession.delete("guestbook.delete",vo);
		}
	

}
