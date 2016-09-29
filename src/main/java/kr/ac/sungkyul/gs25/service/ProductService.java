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

import kr.ac.sungkyul.gs25.dao.ProductDao;
import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Service
public class ProductService {
	
	private static final int LIST_PAGESIZE = 12; // 리스팅 되는 게시물 수
	private static final int LIST_BLOCKSIZE = 5; // 페이지 리스트에 표시되는 페이지 수
	
	@Autowired
	private ProductDao productdao;
	
    public Map<String, Object> listBoard(String spage, String keyword){
		
		int page=Integer.parseInt(spage);
		
		int totalCount = productdao.getTotalCount();
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
		int prevPage = (page >= startPage) ? (page-1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page+1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage-3  : page;
		
		List<ProductVo> list=productdao.getList(page, LIST_PAGESIZE, keyword);
		
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
    
    
	public void insert(ProductVo vo, MultipartFile file) throws Exception{
		  Long no=productdao.insert(vo);

	       //3.orgName
			String orgName =file.getOriginalFilename();
//		  //file.getOriginalFilename();
		
			//4.fileSize
			long fileSize =file.getSize();
			
			//5.saveName
			String saveName = orgName;
			
			//UUID.randomUUID().toString()+ "_" + orgName;
			//6.path 
		    String path ="C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\product";
		    //C:\\Users\\형민\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\webapps\\gs25\\webapp\\assets\\images\\product
		    
			String imageurl="/gs25/assets/images/product/"+saveName;
			//"/gs25/assets/images/product/"+saveName;
			
		    AttachFilePrVo attachFilePrVO = new AttachFilePrVo();
			attachFilePrVO.setNo(no);
			attachFilePrVO.setPath(path);
			attachFilePrVO.setOrgName(orgName);
			attachFilePrVO.setSaveName(saveName);
			attachFilePrVO.setFileSize(fileSize);
			attachFilePrVO.setImageurl(imageurl);
			System.out.println(attachFilePrVO);
			
			productdao.insertAttachPrFile(attachFilePrVO);
			
			File target = new File(path, saveName);
			FileCopyUtils.copy(file.getBytes(),target);
		
	}
	
    
//    public List<AttachFilePrVo> selectList(attachFilePrVO vo){
//		List<AttachFilePrVo> list= productdao.selectList(attachFilePrVO vo);
//		
//    	return list;
//	}

}
