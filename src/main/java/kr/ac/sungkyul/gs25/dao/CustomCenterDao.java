package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;

@Repository
public class CustomCenterDao {

	
	@Autowired
	private SqlSession sqlSession;

	public List<CustomBoardVo> getList(int page, int pagesize, String keyword) { //게시판 리스트
		Map<String, Object> map=new HashMap<>();
		
		if (keyword == null || "".equals(keyword)) {

			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			
			List<CustomBoardVo> list=sqlSession.selectList("board.getList",map);
			return list;

		} else {
			System.out.println(keyword);
			map.put("keyword", "%" + keyword + "%");
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			
			List<CustomBoardVo> list=sqlSession.selectList("board.getListKeyword",map);
			return list;
		}
	}

	public int getTotalCount() { //Total Count값 구하기

	return sqlSession.selectOne("board.totalCount");
	}

	public Long insert(CustomBoardVo vo) { //게시판 삽입
		sqlSession.insert("board.insertBoard", vo);
		return vo.getNo();
	}

	public Long reply(CustomBoardVo vo) { //댓글 달기
		sqlSession.insert("board.replyBoard", vo);
		return vo.getNo();
	}

	public void delete(int no, int orderno) { //첨부파일 삭제
		Map<String, Object> map=new HashMap<>();
		map.put("no", no);
		map.put("orderno", orderno);
		
		sqlSession.delete("board.deleteAttachFile",map);
		
	}

	public void delete(CustomBoardVo vo) { //게시판 삭제
		sqlSession.delete("board.deleteBoard",vo);

	}

	public CustomBoardVo get(Long no1) { //게시판 정보 얻기
		System.out.println(no1);
		CustomBoardVo vo=sqlSession.selectOne("board.listByNo",no1);
		return vo;
	}

	public void updateViewCount(Long no) { //뷰 카운트 증가
		sqlSession.update("board.updateViewCount",no);
	}

	public void update(CustomBoardVo vo) { //게시판 수정

		sqlSession.update("board.update",vo);
	}

	public void updatereplyCount(int groupNo, int orderNo) { //그룹 내 순서 증가
		Map<String, Object> map=new HashMap<>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		
		sqlSession.update("board.updatereplyCount",map);
	}

	public AttachFileVO selectAttachFileByFNO(Long fNO) { //첨부파일 정보
		return sqlSession.selectOne("board.selectAttachFileByFNO", fNO);
	}

	public void insertAttachFile(AttachFileVO attachFileVO) { //첨부파일 삽입
		sqlSession.insert("board.insertAttachFile", attachFileVO);
	}

	public AttachFileVO selectAttachFileByNO(Long no) { //해당 첨부파일 번호 가져오기
		return sqlSession.selectOne("board.selectAttachFileByNO", no);
	}

	public int getgroupno(AttachFileVO vo) { //첨부파일 그룹번호 반환
		sqlSession.selectList("board.list");
		System.out.println(vo.getGroupno());
		return vo.getGroupno();
	}

	public CustomBoardVo getList(int groupNo) { //사용자에게 댓글 달린 것을 확인 하기 위해서
		
		CustomBoardVo vo=sqlSession.selectOne("board.ListByGroupNo",groupNo);
		return vo;
	
}
}
