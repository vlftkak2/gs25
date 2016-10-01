package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFileEvVO;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;

/* 
2016-10-01  
작업자 : 최솔빈
개발 상황 : 완료   
*/

@Repository
public class EventDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 이벤트 게시물 총 수 구하기
	public int getCal(){
		int totalCount = sqlSession.selectOne("eventboard.getCal");
		return totalCount;
	}
	
	// 이벤트 리스트 가져오기
	public List<EventBoardVo> getList(Long page,int pagesize, String keyword){
		Map<String,Object> map = new HashMap<String, Object>();
		
		// 키워드 값에 따라 리스트 가져오기
		if(keyword ==null || "".equals(keyword)){
			map.put("page_top", (page-1)*pagesize + 1);
			map.put("page_bottom", page * pagesize);
			List<EventBoardVo> list = sqlSession.selectList("eventboard.getList", map);
			return list;
		}else{
			map.put("keyword", "%"+keyword+"%");
			map.put("page_top", (page-1)*pagesize + 1);
			map.put("page_bottom", page * pagesize);
			List<EventBoardVo> list = sqlSession.selectList("eventboard.getListKeyword", map);
			return list;
		}
	}
	// 이벤트 게시글 보기
	public EventBoardVo view(Long no){
		EventBoardVo vo = sqlSession.selectOne("eventboard.selectBoard", no);
		return vo;
	}
	// 조회수 증가시키기
	public void updateViewCount(Long no){
		sqlSession.update("eventboard.updateCount", no);
	}
	// 선택된 이벤트의 첨부파일 삭제
	public void delete(Long no){
		sqlSession.delete("eventboard.deleteFile", no);
	}
	// 선택된 이벤트 삭제
	public void delete(EventBoardVo vo){
		sqlSession.delete("eventboard.deleteVo", vo);
	}
	
	public Long insert(EventBoardVo vo){ // 이벤트 게시글 : 번호, 제목, 날짜 삽입
		sqlSession.insert("eventboard.insert", vo);
		return vo.getNo();
	}
	
	public void insertAttachEvFile(AttachFileEvVO attachFileEvVO){ // 이벤트 게시글 : 첨부파일 삽입
		sqlSession.insert("eventboard.insertAttachEvFile", attachFileEvVO);
	}
}