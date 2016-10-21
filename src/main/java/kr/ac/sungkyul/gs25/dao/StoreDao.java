package kr.ac.sungkyul.gs25.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.StoreVo;

@Repository
public class StoreDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<StoreVo> research() {
		//가게 정보
		List<StoreVo> storevo= sqlSession.selectList("store.getmList");
		return storevo;
	}
	
	public void insertStore(StoreVo storevo){
		sqlSession.insert("store.insertStore", storevo);
	}
	
	public Long deleteSearch(Long no){
		Long map_no = sqlSession.selectOne("store.deleteSearch", no);
		System.out.println("da0: "+map_no);
		return map_no;
	}
	
	public void delete(Long no){
		sqlSession.delete("store.delete", no);
	}

}
