package kr.ac.sungkyul.gs25.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.dao.EventDao;
import kr.ac.sungkyul.gs25.vo.AttachFileEvVO;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;

/* 
2016-10-01  
작업자 : 최솔빈
개발 상황 : 완료   
*/

@Service
public class EventService {
	@Autowired
	private EventDao eventboardDao;
	
	private final int LIST_PAGESIZE = 5; // 리스팅되는 게시물의 수
	private final int LIST_BLOCKSIZE = 5; // 페이지 리스트에서 표시되는 페이지 수
	
	public Map<String, Object> list(Long page, String keyword){
		long totalCount= eventboardDao.getCal();  // 전체 게시물 갯수
		long pageCount = (long) Math.ceil((double) totalCount / LIST_PAGESIZE); // 페이지 갯수
	    long blockCount = (long) Math.ceil((double) pageCount / LIST_BLOCKSIZE); // 블록 갯수
	    long currentBlock = (long) Math.ceil((double) page / LIST_BLOCKSIZE); // 현재 블록
	    
	    // 1. page값 검증
	    if (page < 1) {
	       page = 1L;
	       currentBlock = 1;
	    } else if (page > pageCount) {
	       page = pageCount;
	       currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
	    }
	    
	    // 2. 페이지를 그리기 위한 값 계산
	    long startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
	    long endPage = (startPage - 1) + LIST_BLOCKSIZE;
		long prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		long nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
	    long nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
	    long prevtoPage = (currentBlock > 1) ? startPage - 3 : page;
	    
		List<EventBoardVo> list = eventboardDao.getList(page, LIST_PAGESIZE, keyword); // no, image, title, count
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 3. Map 객체에 저장
		map.put("sizeList", LIST_PAGESIZE);  // 리스트 되는 갯 수
		map.put("firstPage", startPage); // 시작 페이지
		map.put("lastPage", endPage); // 끝 페이지
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);// 현재 페이지
		map.put("pageCount", pageCount); // 페이지 갯 수
		map.put("list", list);  // 리스트 정보
		map.put("totalCount", totalCount); // 전체 게시글 정보
		map.put("keyword", keyword);
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);

		return map;
	}
	
	// 이벤트 글 보기
	public EventBoardVo view(Long no){
		EventBoardVo vo = eventboardDao.view(no);
		return vo;
	}
	
	// 조회수 증가
	public void viewcountup(Long no){
		eventboardDao.updateViewCount(no);
	}
	
	// 선택된 이벤트의 첨부파일 삭제
	public void delete(Long no){
		eventboardDao.delete(no);
	}
	
	// 선택된 이벤트 정보 삭제
	public void delete(EventBoardVo vo){
		eventboardDao.delete(vo);
	}
	// 이벤트의 첨부파일 작성
	public void write(EventBoardVo vo, MultipartFile file)throws Exception{
		Long no = eventboardDao.insert(vo); // 게시글 삽입 후 해당 번호를 얻어옴
		
		// original name
		String orgName = file.getOriginalFilename();
		
		// file size
		Long fileSize = file.getSize();
		
		// save name
		String saveName = orgName;
		
		// path(경로) 정하기
		String path="C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\eventboard";
		
		// 이미지 URL
		String imageurl = "/gs25/assets/images/eventboard/"+saveName;
		
		// 첨부파일 객체에 담기
		AttachFileEvVO attachFileEvVO = new AttachFileEvVO();
		attachFileEvVO.setNo(no); // FK
		attachFileEvVO.setPath(path);
		attachFileEvVO.setOrgName(orgName);
		attachFileEvVO.setSaveName(saveName);
		attachFileEvVO.setFileSize(fileSize);
		attachFileEvVO.setImageurl(imageurl);
		
		// 첨부파일 삽입
		eventboardDao.insertAttachEvFile(attachFileEvVO);
		
		// 파일 복사 및 이동
		File target = new File(path, saveName);
		FileCopyUtils.copy(file.getBytes(), target); // 문자형의 데이터를 전송하기 위해 .getBytes() 이용해 바이트로 변환
	}
}
