package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.PassLinkVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Repository
public class UserDao {
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "gs25", "gs25");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into users "
					+ "values(seq_users.nextval, ?, ?, ?, to_date(?,'yyyy-mm-dd'), ?, ?, ?, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getPhone());
			pstmt.setString(8, vo.getPosition());
			pstmt.setLong(9, vo.getStore_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserVo get(String email, String password) { // login
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, name from users where email=? and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				System.out.println(no + " " + name + "님이 로그인하셨습니다.");

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

	public UserVo get(Long userNo) {
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, name, to_char(birth,'yyyymmdd'), gender, address, phone from users where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, userNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
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

	public UserVo update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			Long no = vo.getNo();
			String name = vo.getName();
			String password = vo.getPassword();
			String birth = vo.getBirth();
			String gender = vo.getGender();
			String address = vo.getAddress();
			String phone = vo.getPhone();

			boolean isPasswordEmpty = "".equals(password);

			String sql = null;

			if (isPasswordEmpty == true) {
				sql = "update users set name = ?, birth = ?, gender = ?, address = ?, phone = ? where no = ?";
			} else {
				sql = "update users set name = ?, password = ?, birth = ?, gender = ?, address = ?, phone = ? where no = ?";
			}

			pstmt = conn.prepareStatement(sql);

			if (isPasswordEmpty == true) {
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, gender);
				pstmt.setString(4, address);
				pstmt.setString(5, phone);
				pstmt.setLong(6, no);
			} else {
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, birth);
				pstmt.setString(4, gender);
				pstmt.setString(5, address);
				pstmt.setString(6, phone);
				pstmt.setLong(7, no);
			}

			pstmt.executeUpdate();

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

	public void update(String tempPass) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = null;
			sql = "update users set password = ? where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, tempPass);

			pstmt.executeUpdate();

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

	}

	public UserVo get(String email) {
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select no, name, email from users where email = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
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

		return vo;
	}

	public String find(UserVo vo) { // id find
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String email = null;

		try {
			conn = getConnection();

			String name = vo.getName();
			String gender = vo.getGender();
			String birth = vo.getBirth();
			String phone = vo.getPhone();

			String sql = "select email from users where name=? and gender=? and birth= to_date(?,'yyyy-mm-dd') and phone=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3, birth);
			pstmt.setString(4, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				email = rs.getString(1);
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

	public String passfind(UserVo vo) { // id find
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String email = null;

		try {
			conn = getConnection();

			String email1 = vo.getEmail();
			String name = vo.getName();
			String birth = vo.getBirth();
			String phone = vo.getPhone();

			String sql = "select email from users where email=? and name=? and birth= to_date(?,'yyyy-mm-dd') and phone=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email1);
			pstmt.setString(2, name);
			pstmt.setString(3, birth);
			pstmt.setString(4, phone);

			rs = pstmt.executeQuery();
			System.out.println("dao: " + email);
			if (rs.next()) {
				email = rs.getString(1);
				System.out.println("dao2: " + email);
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

	public Integer setPass(Long no, String password) { // password 재설정
		Connection conn = null;
		PreparedStatement pstmt = null;
		Integer result = null;
		
		try {
			conn = getConnection();

			String sql = null;
			sql = "update users set password = ? where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, password);
			pstmt.setLong(2, no);
			
			result = pstmt.executeUpdate();
			System.out.println(result);

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
		return result;
	}
	
	public void setState(Long no, Integer state) { // password 재설정
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			System.out.println("dao1: "+no);
			System.out.println("dao2: "+state);
			String sql = null;
			sql = "update passlink set state = ? where user_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, state);
			pstmt.setLong(2, no);

			pstmt.executeUpdate();

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
	}

	public Long checkEmail(String email) { // email 유효성 검사
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long no = null;

		try {
			conn = getConnection();

			String sql = null;
			sql = "select no from users where email = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				no = rs.getLong(1);
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
		return no;
	}
	
	public void savelink(String link, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = null;

			Long no = checkEmail(email); // no 가져옴

			Integer state = 0; // 사용 전
		
			sql = "insert into passlink values(seq_passlink.nextval, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, link);
			pstmt.setInt(2, state);
			pstmt.setLong(3, no);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PassLinkVo passlink(String domain) {
		PassLinkVo plVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = null;
			
			sql = "select no, link, state, user_no from passlink where link = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, domain);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				plVo = new PassLinkVo();
				plVo.setNo(rs.getLong(1));
				plVo.setLink(rs.getString(2));
				plVo.setState(rs.getInt(3));
				plVo.setUser_no(rs.getLong(4));
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
		return plVo;
	}
}
