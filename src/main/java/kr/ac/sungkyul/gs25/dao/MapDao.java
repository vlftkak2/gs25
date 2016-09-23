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

import kr.ac.sungkyul.gs25.vo.MapBoardVo;
import kr.ac.sungkyul.gs25.vo.MapVo;

@Repository
public class MapDao {
	
	@Autowired
	private DataSource dataSource;


	public List<MapBoardVo> getList(int page, int pagesize,String keyword) {
		List<MapBoardVo> list = new ArrayList<MapBoardVo>();
		System.out.println(keyword);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();
			String sql =(keyword ==null || "".equals(keyword))? 
		    "select * from(select c.*,rownum rn from(select no,name,address from store) c) where ?<=rn and rn<=?"
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
				String name=rs.getString(2);
				String address=rs.getString(3);
				
				MapBoardVo vo = new MapBoardVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setAddress(address);
				

				list.add(vo);
			}
			
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
		return list;
	}
	
	public int getTotalCount() {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dataSource.getConnection();

			String sql = "select count(*) from store";
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
	
	public List<MapVo> getList(String keyword, Long reno) {

		List<MapVo> list = new ArrayList<MapVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=dataSource.getConnection();

			String sql =(keyword ==null || "".equals(keyword))? 
			"select no,name,localx,localy,region_no from map where region_no=?"
		:	"select a.no,a.name,a.localx,a.localy,a.region_no from map a, region b where a.REGION_NO=b.NO and (b.NAME like ? or a.NAME like ?)";


			
			pstmt = conn.prepareStatement(sql);
			
			
			if(keyword ==null || "".equals(keyword)){

				pstmt.setLong(1, reno);
				}else{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				}
					
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				Double localx=rs.getDouble(3);
				Double localy=rs.getDouble(4);
				Long regionno=rs.getLong(5);
				
				
				MapVo vo =new MapVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setLocalx(localx);
				vo.setLocaly(localy);
				vo.setRegionno(regionno);
				
				list.add(vo);
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				
				
				if(rs!=null){
					rs.close();
				}
				
				if(pstmt!=null){
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				
				
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		
		
		
		return list;

	}


}
