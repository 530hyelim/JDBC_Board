package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.template.JDBCTemplate;
import com.kh.board.model.vo.Board;

// BoardService 인터페이스를 구현하는 클래스.
// 각 메서드의 설명에 맞게 기능을 작성.
public class BoardServiceImpl implements BoardService{
	private BoardDao dao = new BoardDao();
	
	@Override
	public int login(String memberId, String MemberPwd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.login(conn, memberId, MemberPwd);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int insertBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBoard(conn, b, dao.getBno(conn));
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public List<Board> selectBoardList() {
		Connection conn = JDBCTemplate.getConnection();
		List<Board> result = dao.selectBoardList(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public Board selectBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board result = dao.selectBoard(conn, boardNo);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int updateBoard(int boardNo, Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBoard(conn, boardNo, b);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteBoard(conn, boardNo);
		JDBCTemplate.close(conn);
		return result;
	}
	
}
