package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;

	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn=dataSource.getConnection();
			String sql = "insert into users "
					+ "values(seq_users.nextval, ?, ?, ?, to_date(?,'yyyy-mm-dd'), ?, ?, ?, 0,'관리자','1')";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getPhone());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserVo get(String email, String password) {	//login
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dataSource.getConnection();

			String sql = "select no, name from users where email=? and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				System.out.println(no + " " + name);

				vo = new UserVo();

				vo.setNo(no);
				vo.setName(name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

		return vo;
	}
	   
	   public UserVo get(Long userNo){
		   UserVo vo = null;
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs= null;
		   
		   try{
				conn=dataSource.getConnection();
			   
			   String sql = "select no, name, to_char(birth,'yyyymmdd'), gender, address, phone from users where no=?";
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setLong(1, userNo);
			   
			   rs = pstmt.executeQuery();
			
			   if(rs.next()){
				   Long no = rs.getLong(1);
				   String name = rs.getString(2);
				   String birth = rs.getString(3);
				   String gender = rs.getString(4);
				   String address = rs.getString(5);
				   String phone = rs.getString(6);
				   
				   vo = new UserVo();
				   
				   vo.setNo(no);
				   vo.setName(name);
				   vo.setBirth(birth);
				   vo.setGender(gender);
				   vo.setAddress(address);
				   vo.setPhone(phone);
			   }
			   
		   } catch(SQLException e){
			   e.printStackTrace();
		   } finally{
			   try{
				   if(pstmt != null ){
					   pstmt.close();
				   }
				   if(conn != null ){
					   conn.close();
				   }
			   } catch(SQLException e){
				   e.printStackTrace();
			   }
		   }
		   
		   return vo;
	   }
	   
	   public UserVo update(UserVo vo){
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   
		   try{
				conn=dataSource.getConnection();
			   
			   Long no = vo.getNo();
			   String name = vo.getName();
			   String password = vo.getPassword();
			   String birth = vo.getBirth();
			   String gender = vo.getGender();
			   String address = vo.getAddress();
			   String phone = vo.getPhone();
			   
			   boolean isPasswordEmpty = "".equals(password);
			   
			   String sql = null;
			   
			   if(isPasswordEmpty == true){
				   sql = "update users set name = ?, birth = ?, gender = ?, address = ?, phone = ? where no = ?";
			   } else{
				   sql= "update users set name = ?, password = ?, birth = ?, gender = ?, address = ?, phone = ? where no = ?";
			   }
			   
			   pstmt = conn.prepareStatement(sql);
			   
			   if(isPasswordEmpty == true){
				   pstmt.setString(1, name);
				   pstmt.setString(2, birth);
				   pstmt.setString(3, gender);
				   pstmt.setString(4, address);
				   pstmt.setString(5, phone);
				   pstmt.setLong(6, no);
			   } else{
				   pstmt.setString(1, name);
				   pstmt.setString(2, password);
				   pstmt.setString(3, birth);
				   pstmt.setString(4, gender);
				   pstmt.setString(5, address);
				   pstmt.setString(6, phone);
				   pstmt.setLong(7, no);
			   }
			   
			   pstmt.executeUpdate();
			   
		   } catch(SQLException e){
			   e.printStackTrace();
		   } finally{
			   try{
				   if(pstmt != null ){
					   pstmt.close();
				   }
				   if(conn != null ){
					   conn.close();
				   }
			   } catch(SQLException e){
				   e.printStackTrace();
			   }
		   }
		   
		   return vo;
	   }
	   
	   public UserVo get(String email){
		   UserVo vo = null;
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   try{
				conn=dataSource.getConnection();
			   String sql = "select no, name, email from users where email = ?";
			   pstmt= conn.prepareStatement(sql);
			   
			   pstmt.setString(1, email);
			   
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()){
				   vo = new UserVo();
				   vo.setNo(rs.getLong(1));
				   vo.setName(rs.getString(2));
				   vo.setEmail(rs.getString(3));
			   }
		   } catch(SQLException e){
			   e.printStackTrace();
		   } finally{
			   try{
				   if(rs != null){
					   rs.close();
				   }
				   if(pstmt != null ){
					   pstmt.close();
				   }
				   if(conn != null ){
					   conn.close();
				   }
			   } catch(SQLException e){
				   e.printStackTrace();
			   }
		   }
		   
		   return vo;
	   }
	   
	   public String find(UserVo vo) {	//login
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String email = null;

			try {
				conn=dataSource.getConnection();
				
				String name = vo.getName();
				String birth = vo.getBirth();
				String phone = vo.getPhone();

				String sql = "select email from users where name=? and birth= to_date(?,'yyyy-mm-dd') and phone=?;";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, phone);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					email = rs.getString(1);

					System.out.println(email);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
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

			return email;
		}
}
