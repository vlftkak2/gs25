package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.CartVo;

@Repository
public class CartListDao {
	@Autowired
	private SqlSession sqlSession;
	
	//  게시물 총 수 구하기
	public int getCal(Long store_no){
		int totalCount = sqlSession.selectOne("cart.getCal",store_no);
		return totalCount;
	}
	
	// 리스트 가져오기
	public List<CartVo> getList(long page,int pagesize, Long user_no,Long store_no){
		Map<String,Object> map = new HashMap<String, Object>();
		

		map.put("user_no", user_no);
		map.put("page_top", (page-1) * pagesize + 1);
		map.put("page_bottom", page * pagesize);
		map.put("store_no", store_no);
		List<CartVo> list = sqlSession.selectList("cart.getList", map);
		return list;
	}
	
	public Integer insert(Long user_no, Long product_no,Long store_no){
		CartVo vo = new CartVo();
		vo.setUser_no(user_no);
		vo.setProduct_no(product_no);
		vo.setStore_no(store_no);
		
		Integer resultInt =sqlSession.insert("cart.insert", vo);
		
		return resultInt;
	}
	 
	public Integer delete(Long user_no, Long product_no,Long store_no){
		CartVo vo = new CartVo();
		vo.setUser_no(user_no);
		vo.setProduct_no(product_no);
		vo.setStore_no(store_no);
		
		Integer resultInt = sqlSession.delete("cart.cartRelieve", vo);
		return resultInt;
	}
	
	//카트 찜목록 해제
	public void deleteCart(Long user_no, Long product_no,Long store_no){
		CartVo vo = new CartVo();
		vo.setUser_no(user_no);
		vo.setProduct_no(product_no);
		vo.setStore_no(store_no);
		
		sqlSession.delete("cart.deleteCart", vo);
	}
	
	//상품 리스트 삭제 시 찜목록 삭제
	public void delete(Long product_no,Long store_no){
		CartVo vo = new CartVo();
		vo.setProduct_no(product_no);
		vo.setStore_no(store_no);
		
		sqlSession.delete("cart.deleteShopCart",vo);
		
	}
	
	//본사 상품 삭제 시 찜목록 삭제
	public void delete(Long no){

		sqlSession.delete("cart.deleteMain",no);
	}

}
