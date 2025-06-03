package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.template.JDBCTemplate;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Member;

/** 
 * Service의 요청에 맞는 sql문을 실행할 클래스.
 * 단, sql문은 resources/query.xml에 보관/관리한다.
 * */
public class BoardDao {
	Properties prop = new Properties();
	
	public BoardDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int login(Connection conn, String memberId, String memberPwd) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			/*
			stmt = conn.prepareStatement(prop.getProperty("login"));
			stmt.setString(1, memberId);
			stmt.setString(2, memberPwd);
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				System.out.println(rset.getString("MEMBER_NAME"));
				result++;
			}
			*/
			stmt = conn.prepareStatement(prop.getProperty("selectMember"));
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				
				if (m.getMemberId().equals(memberId) && m.getMemberPwd().equals(memberPwd)) {
					result++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	public int insertBoard(Connection conn, Board b, int bno) {
		PreparedStatement stmt = null;
		int updateCount = 0;
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("insert"));
			stmt.setInt(1, ++bno);
			stmt.setString(2, b.getTitle());
			stmt.setString(3, b.getContent());
			stmt.setString(4, b.getWriter());
			updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}
		return updateCount;
	}

	public int getBno(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		int bno = 0;
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("select"));
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				bno = rset.getInt("BNO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return bno;
	}

	public List<Board> selectBoardList(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("select"));
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				Board b = new Board();
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setWriter(rset.getString("WRITER"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public Board selectBoard(Connection conn, int boardNo) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Board b = null;
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("selectBoard"));
			stmt.setInt(1, boardNo);
			rset = stmt.executeQuery();
			
			if (rset.next()) {
				b = new Board();
				b.setBno(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				
				// updateBoard, deleteBoard 사용자 확인용
				b.setWriter(rset.getString("WRITER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return b;
	}

	public int updateBoard(Connection conn, int boardNo, Board b) {
		PreparedStatement stmt = null;
		int updateCount = 0;
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("updateBoard"));
			stmt.setString(1, b.getTitle());
			stmt.setString(2, b.getContent());
			stmt.setInt(3, boardNo);
			updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}
		return updateCount;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		PreparedStatement stmt = null;
		int updateCount = 0;
		
		try {
			stmt = conn.prepareStatement(prop.getProperty("disableBoard"));
			stmt.setInt(1, boardNo);
			updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}
		return updateCount;
	}

}
