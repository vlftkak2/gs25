package kr.ac.sungkyul.gs25.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.dao.ProductDao;
import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;


/*
 2016-10-01 
   작업자 : 최형민
   개발 상황 : 완료
*/

@Service
public class ProductService {
	
	// 리스팅 되는 게시물 수
	private static final int LIST_PAGESIZE = 12; 
	
	// 페이지 리스트에 표시되는 페이지 수
	private static final int LIST_BLOCKSIZE = 5; 
	
	@Autowired
	private ProductDao productdao;
	
    public Map<String, Object> listBoard(String spage, String keyword){
    	
    	// 1. 페이지 값 받기
		int page=Integer.parseInt(spage);
		
		// 2. 페이지를 그리기 위한 기초 작업
		int totalCount = productdao.getTotalCount();
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
		int prevPage = (page >= startPage) ? (page-1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page+1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage-3  : page;
		
		List<ProductVo> list=productdao.getList(page, LIST_PAGESIZE, keyword);
		
		// 5. map에 객체 담기
		Map<String, Object> map=new HashMap<String, Object>();
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
    
    //상품 등록
	public void insert(ProductVo vo, MultipartFile file) throws Exception{

			// 1. 게시물의 번호 얻기
			Long no=productdao.insert(vo);

	       // 2. orgName
			String orgName =file.getOriginalFilename();
		
			// 3. fileSize
			long fileSize =file.getSize();
			
			// 4. saveName
			String saveName = orgName;
			
			// 5. path 경로 정하기
		    String path ="C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\product";

		    // 6. imageurl 경로
			String imageurl="/gs25/assets/images/product/"+saveName;
			
			//7. 첨부파일 객체에 담기
		    AttachFilePrVo attachFilePrVO = new AttachFilePrVo();
			attachFilePrVO.setNo(no);
			attachFilePrVO.setPath(path);
			attachFilePrVO.setOrgName(orgName);
			attachFilePrVO.setSaveName(saveName);
			attachFilePrVO.setFileSize(fileSize);
			attachFilePrVO.setImageurl(imageurl);
			System.out.println(attachFilePrVO);
			
			//8. 첨부파일 삽입
			productdao.insertAttachPrFile(attachFilePrVO);
			
			//9. 파일 복사 및 이동
			File target = new File(path, saveName);
			FileCopyUtils.copy(file.getBytes(),target);
		
	}
	
	//상품 첨부파일 삭제
	public void deletefile(Long no){
		productdao.deletefile(no);
	}
	
	//상품 삭제
	public void delete(Long no){
		
		productdao.delete(no);
	}

}
