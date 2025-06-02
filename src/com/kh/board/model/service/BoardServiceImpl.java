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
		return 0;
	}

	@Override
	public List<Board> selectBoardList() {
		return null;
	}

	@Override
	public Board selectBoard(int boardNo) {
		return null;
	}

	@Override
	public int updateBoard(int boardNo, Board b) {
		return 0;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return 0;
	}
	
}
