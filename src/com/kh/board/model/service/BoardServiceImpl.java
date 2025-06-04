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
		//int result = dao.insertBoard(conn, b, dao.getBno(conn));
		int result = dao.insertBoard(conn, b);
		
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
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
	public int updateBoard(/*int boardNo, */Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBoard(conn, /*boardNo, */b);
		
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	// 실제로는 에러가 발생되는 경우에 롤백
	// 다양한 트랜잭션 관리 기법이 있음
	// 스프링 프레임워크같은거 사용하게 되면 전체를 try-catch로 감싸고
	// 어노테이션으로 예외처리

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteBoard(conn, boardNo);
		
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}
