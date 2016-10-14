package kr.ac.sungkyul.gs25.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.dao.CustomCenterDao;
import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;

/*
 2016-10-01 
  작업자 : 최형민
  개발 상황 : 완료
*/

@Service
public class CustomCenterService {

	// 리스팅 되는 게시물 수
	private static final int LIST_PAGESIZE = 10; 
	
	// 페이지 리스트에 표시되는 페이지 수
	private static final int LIST_BLOCKSIZE = 5; 

	@Autowired
	private CustomCenterDao customdao;

	public Map<String, Object> list(String spage, String keyword) {
		
		// 1. 페이지 값 받기
		int page = Integer.parseInt(spage);

		// 2. 페이지를 그리기 위한 기초 작업
		int totalCount = customdao.getTotalCount();
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);

		// 3. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}

		// 4. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage - 3 : page;

		List<CustomBoardVo> list = customdao.getList(page, LIST_PAGESIZE, keyword);

		Map<String, Object> map = new HashMap<String, Object>();
		
		// 5. map에 객체 담기
		map.put("sizeList", LIST_PAGESIZE);
		map.put("firstPage", startPage);
		map.put("lastPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);
		map.put("pageCount", pageCount);
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("keyword", keyword);
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);

		return map;
	}

	//고객센터 글쓰기 작성
	public void write(CustomBoardVo vo, MultipartFile file) throws Exception{
		        
				// 1. 게시물의 번호 얻기
				Long no=customdao.insert(vo);
				CustomBoardVo vo1 = customdao.get(no);
				// 2. 그룹넘버 정보 얻어오기
				int groupno=vo1.getGroupNo(); 
				int orderno=vo1.getGroupOrderNo();

				
		       // 3. orgName
				String orgName ="이미지";
				
				// 4. fileSize
				long fileSize =file.getSize();
				
				// 5. saveName
				String saveName = UUID.randomUUID().toString()+ "_" + orgName;
				
				// 6. path 경로 정하기
				String path ="c:\\upload";
				
				//7. 첨부파일 객체에 담기
				AttachFileVO attachFileVO = new AttachFileVO();
				attachFileVO.setNo(no);
				attachFileVO.setPath(path);
				attachFileVO.setOrgName(orgName);
				attachFileVO.setSaveName(saveName);
				attachFileVO.setFileSize(fileSize);
				attachFileVO.setGroupno(groupno);
				attachFileVO.setOrderno(orderno);
												
				//8. 첨부파일 삽입
				customdao.insertAttachFile(attachFileVO);
				
				//9. 파일 복사 및 이동
				File target = new File(path, saveName);
				FileCopyUtils.copy(file.getBytes(),target);
		
	}
	
	//첨부파일 삭제
	public void delete(Integer no, int orderno){
		customdao.delete(no,orderno);
	}

	//게시물 삭제
	public void delete(CustomBoardVo vo) {
		customdao.delete(vo);
	}

	//게시물 정보 얻기
	public CustomBoardVo boardinfo(Long no) {

		CustomBoardVo vo = customdao.get(no);
		return vo;
	}
	
	//게시물 조회수 증가
	public void viewcountup(Long no) {

		customdao.updateViewCount(no);
	}
	
	//게시물 수정
	public void modify(CustomBoardVo vo){
		customdao.update(vo);

	}
	
	//중간에 들어오는 게시물 순서 정렬
	public void updatereplyCount(int groupno, int ordernumber){
		
		
		customdao.updatereplyCount(groupno, ordernumber);
	}
	
	//게시물 답글 달기
	public void reply(CustomBoardVo vo,MultipartFile file) throws Exception{
		
		// 1. 게시물의 번호 얻기
		Long no=customdao.reply(vo);
		// 2. 그룹 정보 얻기
		CustomBoardVo vo1 = customdao.get(no);
		int groupno=vo1.getGroupNo(); //그룹넘버 정보 얻어오기
		int orderno=vo1.getGroupOrderNo();
		
		 // 3. orgName
		String orgName ="이미지";
		
		// 4. fileSize
		long fileSize =file.getSize();
		
		// 5. saveName
		String saveName = UUID.randomUUID().toString()+ "_" + orgName;
		
		// 6. path 
		String path ="c:\\upload";
		
		//7. 첨부파일 객체에 담기
		AttachFileVO attachFileVO = new AttachFileVO();
		attachFileVO.setNo(no);
		attachFileVO.setPath(path);
		attachFileVO.setOrgName(orgName);
		attachFileVO.setSaveName(saveName);
		attachFileVO.setFileSize(fileSize);
		attachFileVO.setGroupno(groupno);
		attachFileVO.setOrderno(orderno);
				
		//8. 첨부파일 삽입
		customdao.insertAttachFile(attachFileVO);
		
		// 9. 파일 복사 및 이동
		File target = new File(path, saveName);
		FileCopyUtils.copy(file.getBytes(),target);

	}
	
	// 첨부파일 정보 얻기
	public AttachFileVO selectAttachFileByNO(Long no){
		return customdao.selectAttachFileByNO(no);
	}
	
	//첨부파일 정보 얻기
	public AttachFileVO selectAttachFileByFNO(Long fNO){
		return customdao.selectAttachFileByFNO(fNO);
	}
	
	//그룹 번호 얻기
	public int getgroupno(AttachFileVO vo){
		int groupno=customdao.getgroupno(vo);
		
		return groupno;
	}
	
	////사용자에게 댓글 달린 것을 확인 하기 위한 객체 얻기
	public CustomBoardVo userno(int groupNo) {
		CustomBoardVo vo  = customdao.getList(groupNo);
		return vo;
	}
	

}
