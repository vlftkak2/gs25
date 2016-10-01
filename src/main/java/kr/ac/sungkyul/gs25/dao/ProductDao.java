package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Repository
public class ProductDao {
	

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public int getTotalCount() {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dataSource.getConnection();

			String sql = "select count(*) from product";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return totalCount;

	}
	

	public List<ProductVo> getList(int page, int pagesize,String keyword) {
		List<ProductVo> list = new ArrayList<ProductVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();
			String sql = (keyword ==null || "".equals(keyword))? 
		    "select * from(select c.*,rownum rn from(select a.name,a.price,to_char(a.reg_date,'yyyy.mm.dd'),TO_CHAR(a.EXPIRY_DATE,'YYYY.MM.DD'),a.maker,a.category,b.IMAGEURL from product a, boardsfilepr b where a.NO=b.NO) c) where ?<=rn and rn<=?"
			: "select * from(select c.*,rownum rn from(select a.NAME,a.PRICE,to_char(a.reg_date,'yyyy.mm.dd'),to_char(a.EXPIRY_DATE,'yyyy-mm-dd'),a.maker,a.category,d.imageurl from product a, productkind b, boardsfilepr d where a.KIND_NO=b.NO and a.NO=d.NO and (a.NAME like ? or b.KIND like ?) order by price desc, a.EXPIRY_DATE asc) c) where ?<=rn and rn<=?";
				
			pstmt = conn.prepareStatement(sql);
			
			if(keyword ==null || "".equals(keyword)){
				
			pstmt.setInt(1, (page - 1) * pagesize + 1);
			pstmt.setInt(2, page * pagesize);
		
			
			}else{
				
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, (page - 1) * pagesize + 1);
			pstmt.setInt(4, page * pagesize);
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name=rs.getString(1);
				Integer price=rs.getInt(2);
				String regdate=rs.getString(3);
				String expirydate=rs.getString(4);
				String maker=rs.getString(5);
				String category=rs.getString(6);
				String imageurl=rs.getString(7);
				
				
				ProductVo vo=new ProductVo();
				vo.setName(name);
				vo.setPrice(price);
				vo.setReg_date(regdate);
				vo.setExpiry_date(expirydate);
				vo.setMaker(maker);
				vo.setCategory(category);
				vo.setImageurl(imageurl);
			

				list.add(vo);
			}
			
		} catch (SQLException ex) {
			
			System.out.println("error: " + ex);
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	
	public Long insert(ProductVo vo) {
		sqlSession.insert("product.insertBoard", vo);
		return vo.getNo();
	}
	
	
	public void insertAttachPrFile(AttachFilePrVo attachFilePrVO) {
		sqlSession.insert("product.insertAttachPrFile", attachFilePrVO);
	}
	
	
//	public List<AttachFilePrVo> selectList(Long fNO) {
//		List<AttachFilePrVo> list=sqlSession.selectList("product.selectList", fNO);
//		return list;
//	}


}
