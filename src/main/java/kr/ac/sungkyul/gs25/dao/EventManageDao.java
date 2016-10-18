package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.EventBoardVo;

@Repository
public class EventManageDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 이벤트
	public int getTotalCount() {
		return sqlSession.selectOne("submanage.totalCountEvent");
	}

	// 이벤트 (번호, 제목, 시작일, 종료일, 작성자)
	public List<EventBoardVo> getEventList(int page, int pagesize, String keyword, Long store_no) {
		Map<String, Object> map = new HashMap<>();

		// 키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {

		map.put("page_top", (page - 1) * pagesize + 1);
		map.put("page_bottom", page * pagesize);
		map.put("store_no", store_no);
		List<EventBoardVo> list = sqlSession.selectList("submanage.getListEvent", map);
		return list;

	// 검색된 리스트 가져오기
	} else {
		map.put("store_no", store_no);
		map.put("keyword", "%" + keyword + "%");
		map.put("page_top", (page - 1) * pagesize + 1);
		map.put("page_bottom", page * pagesize);
		List<EventBoardVo> list = sqlSession.selectList("submanage.getListEventKeyword", map);
		return list;
				}
	}

	public void eventDelete(Long no) {
		sqlSession.delete("submanage.eventdelete", no);
	}

	public void eventDelete(EventBoardVo vo) {
		sqlSession.delete("submanage.eventdeleteVo", vo);
	}

}
