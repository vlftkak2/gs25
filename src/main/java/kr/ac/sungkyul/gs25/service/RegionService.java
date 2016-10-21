package kr.ac.sungkyul.gs25.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.RegionDao;
import kr.ac.sungkyul.gs25.vo.RegionVo;

@Service
public class RegionService {
	
	@Autowired
	private RegionDao regiondao;
	
	public List<RegionVo> research() {
		
		List<RegionVo> regionvo= regiondao.research();
		return regionvo;
	}

}
