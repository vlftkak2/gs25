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

@Service
public class CustomCenterService {

	private static final int LIST_PAGESIZE = 10; // 리스팅 되는 게시물 수
	private static final int LIST_BLOCKSIZE = 5; // 페이지 리스트에 표시되는 페이지 수

	@Autowired
	private CustomCenterDao customdao;

	public Map<String, Object> list(String spage, String keyword) {

		int page = Integer.parseInt(spage);

		int totalCount = customdao.getTotalCount();
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);

		// 4. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}

		// 5. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (page >= startPage) ? (page - 1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page + 1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage - 3 : page;

		List<CustomBoardVo> list = customdao.getList(page, LIST_PAGESIZE, keyword);
		Map<String, Object> map = new HashMap<String, Object>();
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

	public void write(CustomBoardVo vo, MultipartFile file) throws Exception{
		       Long no=customdao.insert(vo);
		
		       
		       //3.orgName
				String orgName ="이미지";
				
			  //file.getOriginalFilename();
				
				//4.fileSize
				long fileSize =file.getSize();
				
				//5.saveName
				String saveName = UUID.randomUUID().toString()+ "_" + orgName;
				
				//6.path 
				String path ="c:\\upload";
				
				
				AttachFileVO attachFileVO = new AttachFileVO();
				attachFileVO.setNo(no);
				attachFileVO.setPath(path);
				attachFileVO.setOrgName(orgName);
				attachFileVO.setSaveName(saveName);
				attachFileVO.setFileSize(fileSize);
				
				System.out.println(attachFileVO.toString());
				
				customdao.insertAttachFile(attachFileVO);
				
				
				File target = new File(path, saveName);
				FileCopyUtils.copy(file.getBytes(),target);
		
	}
	
	public void delete(Long no){
		customdao.delete(no);
	}

	public void delete(CustomBoardVo vo) {
		customdao.delete(vo);
	}

	public CustomBoardVo boardinfo(Long no) {

		CustomBoardVo vo = customdao.get(no);
		return vo;
	}

	public void viewcountup(Long no) {

		customdao.updateViewCount(no);
	}
	
	public void modify(CustomBoardVo vo){
		customdao.update(vo);

	}
	
	public void updatereplyCount(int groupno, int ordernumber){
		
		customdao.updatereplyCount(groupno, ordernumber);
	}
	
	public void reply(CustomBoardVo vo){
		customdao.reply(vo);
	}
	
	public AttachFileVO selectAttachFileByNO(Long no){
		return customdao.selectAttachFileByNO(no);
	}
	
	public AttachFileVO selectAttachFileByFNO(Long fNO){
		return customdao.selectAttachFileByFNO(fNO);
	}
	
	
	
	

}
