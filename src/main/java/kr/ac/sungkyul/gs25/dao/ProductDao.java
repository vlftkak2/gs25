package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.CartVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;

/*
 2016-10-15 
   작업자 : 최형민
   개발 상황 : 추가
*/

@Repository
public class ProductDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 메인 상품 리스트 총 개수 구하기
	public int getTotalCount() {

		int totalCount = sqlSession.selectOne("product.getMainTotalCount");
		return totalCount;

	}
	
	//본사 상품 리스트 가져오기
	public List<ProductVo> getList(){
		List<ProductVo> Productlist=sqlSession.selectList("product.getMainShopList");
		return Productlist;
	}
	
	// 메인 상품 리스트
		public List<ProductVo> getList(int page, int pagesize, String keyword) {

			Map<String, Object> map = new HashMap<>();

			// 키보드가 null or 비어있을 때 리스트 가져오기
			if (keyword == null || "".equals(keyword)) {

				map.put("page_start", (page - 1) * pagesize + 1);
				map.put("page_end", page * pagesize);

				// 검색된 리스트 가져오기
				List<ProductVo> list = sqlSession.selectList("product.getMainList", map);
				return list;
			} else {

				map.put("keyword", "%" + keyword + "%");
				map.put("page_start", (page - 1) * pagesize + 1);
				map.put("page_end", page * pagesize);
				
				List<ProductVo> list = sqlSession.selectList("product.getMainListKeyword", map);
				return list;
			}
		}
		
		// 상품 등록
		public Long insert(ProductVo vo) {
			sqlSession.insert("product.insertMainBoard", vo);
			return vo.getNo();
		}
		
		// 메인 상품 삭제
		public void delete(Long no) {
			sqlSession.delete("product.deleteproduct", no);
		}
	
	//서브 상품 리스트 총 개수 구하기
	public int getTotalCount(Long StoreNo) {

		int totalCount = sqlSession.selectOne("product.getTotalCount",StoreNo);
		return totalCount;

	}

	//서브 상품 리스트
	public List<StoreProductVo> getList(int page, int pagesize, String keyword,Long StoreNo) {

		Map<String, Object> map = new HashMap<>();

		// 키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {

			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			map.put("store_no", StoreNo);

			// 게시물 리스트 가져오기
			List<StoreProductVo> list = sqlSession.selectList("product.getList", map);
			return list;
		} else {

			map.put("keyword", "%" + keyword + "%");
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			map.put("store_no", StoreNo);
			
			//검색된 리스트 가져오기
			List<StoreProductVo> list = sqlSession.selectList("product.getListKeyword", map);
			return list;
		}
	}
	

	//서브 상품 등록
	public void Subinsert(StoreProductVo vo){
		sqlSession.insert("product.insertSubProduct",vo);
	}
	
	//서브 상품 삭제
	public void subdelete(Long product_no,Long store_no){
		
		Map<String, Object> map = new HashMap<>();
		map.put("product_no", product_no);
		map.put("store_no", store_no);
		
		sqlSession.delete("product.deleteSubProduct",map);
	}

	// 상품 첨부파일 등록
	public void insertAttachPrFile(AttachFilePrVo attachFilePrVO) {
		sqlSession.insert("product.insertAttachPrFile", attachFilePrVO);
	}

	// 상품 첨부파일 삭제
	public void deletefile(Long no) {
		sqlSession.delete("product.deleteproductfile", no);
	}

	// 서브메인 - 유통기한별 4개
	public List<StoreProductVo> getSubDate(Long store_no) {

		List<StoreProductVo> list = sqlSession.selectList("product.getSubDate",store_no);
		return list;
	}

	// 서브메인 - 신상품별 4개
	public List<StoreProductVo> getSubNew(Long store_no) {

		List<StoreProductVo> list = sqlSession.selectList("product.getSubNew",store_no);
		return list;
	}

	// 서브메인 - 인기상품4개
	public List<StoreProductVo> getSubPopular(Long store_no) {

		List<StoreProductVo> list = sqlSession.selectList("product.getSubPopular",store_no);
		return list;
	}

	// 서브메인 - 추천별 4개
	public List<StoreProductVo> getSubReco(Long store_no) {

		List<StoreProductVo> list = sqlSession.selectList("product.getSubReco",store_no);
		return list;
	}

	// 상품 상세정보 출력
	public StoreProductVo productInfo(Long no,Long store_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("store_no", store_no);
		StoreProductVo vo = sqlSession.selectOne("product.searchproduct", map);
		return vo;
	}

	// 상품 조회수 증가
	public void updateViewCount(Long no) {
		sqlSession.update("product.updateViewCount", no);
	}
	
	
	// 1000원 이하 랜덤 상품 (출석체크 상품 증정)
		public ProductVo random1000(){
			
			ProductVo vo = sqlSession.selectOne("product.random1000");
			return vo;
		}
		
		// 2000원 이하 랜덤 상품 (출석체크 상품 증정)
		public ProductVo random2000(){
			
			ProductVo vo = sqlSession.selectOne("product.random2000");
			return vo;
		}
		
		public CartVo maintainCheck(Long user_no, Long product_no){
			CartVo checkVo = new CartVo();
			checkVo.setUser_no(user_no);
			checkVo.setProduct_no(product_no);
			checkVo = sqlSession.selectOne("product.maintainCheck", checkVo);
			return checkVo;
		}

		public StoreProductVo getStoreName(Long store_no){
			
			StoreProductVo StoreVo=sqlSession.selectOne("product.getStoreName",store_no);
			return StoreVo;
		}
	
}
