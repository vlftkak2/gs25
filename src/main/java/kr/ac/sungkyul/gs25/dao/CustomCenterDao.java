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

import kr.ac.sungkyul.gs25.vo.AttachFileVO;
import kr.ac.sungkyul.gs25.vo.CustomBoardVo;

@Repository
public class CustomCenterDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;

	public List<CustomBoardVo> getList(int page, int pagesize, String keyword) {
		List<CustomBoardVo> list = new ArrayList<CustomBoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = (keyword == null || "".equals(keyword))
					? "select * from(select c.*,rownum rn from(select a.no,a.title,b.name,a.user_no,a.view_count,to_char(a.reg_date, 'yyyy-mm-dd pm hh:mi:ss'),a.depth,a.GROUP_NO,a.ORDER_NO from boards a, users b where a.user_no=b.no order by group_no desc, order_no asc) c) where ?<=rn and rn<=?"
					: "select * from(select c.*,rownum rn from(select a.no,a.title,b.name,a.user_no,a.view_count,to_char(a.reg_date, 'yyyy-mm-dd pm hh:mi:ss'),a.depth,a.GROUP_NO,a.ORDER_NO from boards a, users b where a.user_no=b.no and (title like ? or content like ?) order by group_no desc, order_no asc) c) where ?<=rn and rn<=?";

			pstmt = conn.prepareStatement(sql);

			if (keyword == null || "".equals(keyword)) {

				pstmt.setInt(1, (page - 1) * pagesize + 1);
				pstmt.setInt(2, page * pagesize);

			} else {

				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
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
				Integer groupno = rs.getInt(8);
				Integer ordeno = rs.getInt(9);

				CustomBoardVo vo = new CustomBoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setUserNo(userNo);
				vo.setCount(viewCount);
				vo.setDate(regDate);
				vo.setDepth(depth);
				vo.setGroupNo(groupno);
				vo.setGroupOrderNo(ordeno);

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

	public int getTotalCount() {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

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

	public Long insert(CustomBoardVo vo) {
		sqlSession.insert("board.insertBoard", vo);
		return vo.getNo();
	}

	public Long reply(CustomBoardVo vo) {
		sqlSession.insert("board.replyBoard", vo);
		return vo.getNo();
	}

	public void delete(int no, int orderno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "delete from boardsfile where groupno=? and orderno>=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
			pstmt.setInt(2, orderno);

			// 5. sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 오류 : " + e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

	}

	public void delete(CustomBoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "delete from boards where GROUP_NO=? and ORDER_NO>=?";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setInt(1, vo.getGroupNo());
			pstmt.setInt(2, vo.getGroupOrderNo());

			// 5. sql 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 오류 : " + e);
		} finally {
			try {

				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

	}

	public CustomBoardVo get(Long no1) {

		CustomBoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = dataSource.getConnection();

			String sql = "select no,title,content,user_no,depth,order_no,group_no from boards where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no1);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userno = rs.getLong(4);
				Integer depth = rs.getInt(5);
				Integer orderno = rs.getInt(6);
				Integer groupno = rs.getInt(7);

				vo = new CustomBoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userno);
				vo.setDepth(depth);
				vo.setGroupOrderNo(orderno);
				vo.setGroupNo(groupno);

			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		return vo;

	}

	public void updateViewCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "update boards set view_count = view_count + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
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

	public void update(CustomBoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = dataSource.getConnection();

			String sql = "update boards set title=?, content=? where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {

			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
	}

	public void updatereplyCount(int groupNo, int orderNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "update boards set order_no=order_no+1 where group_no=? and order_no>=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, orderNo);

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

	public AttachFileVO selectAttachFileByFNO(Long fNO) {
		return sqlSession.selectOne("board.selectAttachFileByFNO", fNO);
	}

	public void insertAttachFile(AttachFileVO attachFileVO) {
		sqlSession.insert("board.insertAttachFile", attachFileVO);
	}

	public AttachFileVO selectAttachFileByNO(Long no) {
		return sqlSession.selectOne("board.selectAttachFileByNO", no);
	}

	public int getgroupno(AttachFileVO vo) {
		sqlSession.selectList("board.list");
		System.out.println(vo.getGroupno());
		return vo.getGroupno();
	}

	public CustomBoardVo getList(int groupNo) {

		CustomBoardVo vo= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT user_no FROM BOARDS WHERE group_no = ? and depth = 1";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long userno = rs.getLong(1);

				vo =new CustomBoardVo();
				vo.setUserNo(userno);
				
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
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
				System.out.println("error : " + e);
			}
		}
		return vo;
	}
}
