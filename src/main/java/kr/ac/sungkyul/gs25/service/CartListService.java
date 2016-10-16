package kr.ac.sungkyul.gs25.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.CartListDao;
import kr.ac.sungkyul.gs25.vo.CartVo;

/*
2016-10-11 
  작업자 : 최솔빈
  개발 상황 : 완료
*/


@Service
public class CartListService {

	@Autowired
	private CartListDao cartlistDao;

	private static final int LIST_PAGESIZE = 5; // 리스팅되는 게시물의 수
	private static final int LIST_BLOCKSIZE = 5; // 페이지 리스트에서 표시되는 페이지 수

	public Map<String, Object> list(Long page, Long user_no,Long store_no) {

		// 2.
		long totalCount = cartlistDao.getCal(store_no); // 전체 게시물 갯수
		long pageCount = (long) Math.ceil((double) totalCount / LIST_PAGESIZE); // 페이지
		long blockCount = (long) Math.ceil((double) pageCount / LIST_BLOCKSIZE); // 블록
		long currentBlock = (long) Math.ceil((double) page / LIST_BLOCKSIZE); // 현재

		// 3. page값 검증
		if (page < 1) {
			page = 1L;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}

		// 4. 페이지를 그리기 위한 값 계산
		long startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		long endPage = (startPage - 1) + LIST_BLOCKSIZE;
		long prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		long nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
		long nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		long prevtoPage = (currentBlock > 1) ? startPage - 3 : page;

		List<CartVo> list = cartlistDao.getList(page, LIST_PAGESIZE, user_no,store_no);

		Map<String, Object> map = new HashMap<String, Object>();

		// 3. Map 객체에 저장
		map.put("sizeList", LIST_PAGESIZE); // 리스트 되는 갯 수
		map.put("firstPage", startPage); // 시작 페이지
		map.put("lastPage", endPage); // 끝 페이지
		
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		
		map.put("currentPage", page);// 현재 페이지
		map.put("pageCount", pageCount); // 페이지 갯 수
		
		map.put("list", list); // 리스트 정보
		map.put("totalCount", totalCount); // 전체 게시글 정보
		
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);

		return map;
	}

	//카트 찜목록 등록
	public String write(Long user_no, Long product_no,Long store_no) {
		Integer resultInt = cartlistDao.insert(user_no, product_no,store_no);
		String result = String.valueOf(resultInt);
		return result;
	}

	//카트 찜목록 해제
	public String relieve(Long user_no, Long product_no,Long store_no) {
		Integer resultInt = cartlistDao.delete(user_no, product_no,store_no);
		String result = String.valueOf(resultInt);
		return result;
	}

	//카트 찜목록 해제
	public void deleteCart(Long user_no, Long product_no,Long store_no) {
		cartlistDao.deleteCart(user_no, product_no,store_no);
	}
	
	//찜목록 총 개수
	public int getCount(Long store_no){
		int TotalCount=cartlistDao.getCal(store_no);
		return TotalCount;
	}
	
	//상품 리스트 삭제 시 찜목록 삭제
	public void deleteCart(Long product_no,Long store_no){
		cartlistDao.delete(product_no, store_no);
	}
	
	//본사 상품 삭제 시 찜목록 삭제
	public void deleteCart(Long no){
		cartlistDao.delete(no);
	}
	
}
