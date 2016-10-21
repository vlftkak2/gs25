package kr.ac.sungkyul.gs25.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.MapDao;
import kr.ac.sungkyul.gs25.dao.StoreDao;
import kr.ac.sungkyul.gs25.vo.StoreVo;

@Service
public class StoreService {
	
	@Autowired
	private StoreDao storedao;
	
	@Autowired
	private MapDao mapdao;
	
	public List<StoreVo> research() {
		
		List<StoreVo> storevo= storedao.research();
		return storevo;
	}
	
	public void delete(Long no) {
		Long map_no = storedao.deleteSearch(no);
		storedao.delete(no);	//지점삭제
		mapdao.delete(map_no);	//지도 삭제
	}

}
