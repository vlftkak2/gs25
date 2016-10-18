package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.GuestbookVo;

@Repository
public class GuestManageDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 방명록
	public int getGuestCount() {
		return sqlSession.selectOne("submanage.totalCountGuest");
	}

	public List<GuestbookVo> getGuestbookList(int page, int pagesize, String keyword, Long store_no) {
		Map<String, Object> map = new HashMap<>();

		// 키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {

			map.put("page_top", (page - 1) * pagesize + 1);
			map.put("page_bottom", page * pagesize);
			map.put("store_no", store_no);

			List<GuestbookVo> list = sqlSession.selectList("submanage.getListGuest", map);
			return list;

			// 검색된 리스트 가져오기
		} else {
			map.put("keyword", "%" + keyword + "%");
			map.put("page_top", (page - 1) * pagesize + 1);
			map.put("page_bottom", page * pagesize);
			map.put("store_no", store_no);

			List<GuestbookVo> list = sqlSession.selectList("submanage.getListGuestKeyword", map);
			return list;
		}
	}
	
	// 선택된 글 삭제
	public void delete(GuestbookVo vo){
			sqlSession.delete("submanage.delete",vo);
	}

}
