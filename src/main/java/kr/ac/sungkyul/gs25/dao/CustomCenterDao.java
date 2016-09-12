package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.CustomCenterVo;

@Repository
public class CustomCenterDao {
	

	@Autowired
	private DataSource dataSource;
	
	public List<CustomCenterVo> getList(int page, int pagesize,String keyword) {
		List<CustomCenterVo> list = new ArrayList<CustomCenterVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();
			String sql = (keyword ==null || "".equals(keyword))? 
		    "select * from(select c.*,rownum rn from(select a.no,a.title,b.name,a.user_no,a.view_count,to_char(a.reg_date, 'yyyy-mm-dd pm hh:mi:ss'),a.depth from boards a, users b where a.user_no=b.no order by group_no desc, order_no asc) c) where ?<=rn and rn<=?"
			: "select * from(select c.*,rownum rn from(select no,name,address from store where name like ? or address like ?) c) where ?<=rn and rn<=?";
				
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
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Long userNo = rs.getLong(4);
				Integer viewCount = rs.getInt(5);
				String regDate = rs.getString(6);
				Integer depth = rs.getInt(7);
			
				
			     CustomCenterVo vo=new CustomCenterVo();
			     vo.setNo(no);
			     vo.setTitle(title);
			     vo.setName(name);
			     vo.setUserNo(userNo);
			     vo.setCount(viewCount);
			     vo.setDate(regDate);
			     vo.setDepth(depth);
			     
				

				list.add(vo);
			}
			
			return list;
			
		} catch (SQLException ex) {
			
			System.out.println("error: " + ex);
			return list;
			
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
		
	}
	
	public int getTotalCount() {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dataSource.getConnection();

			String sql = "select count(*) from boards";
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

}
