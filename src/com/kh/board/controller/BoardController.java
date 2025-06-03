package com.kh.board.controller;

import java.util.List;

import com.kh.board.model.service.BoardServiceImpl;
import com.kh.board.model.vo.Board;

/* 
 * View요청에 맞는 Service를 선택하여 메서드를 실행 한 후 결과값을 돌려주는 클래스.
 * */
public class BoardController {
	
	// service 변수 선언 및 초기화
	BoardServiceImpl bs = new BoardServiceImpl();
	
	// view의 login요청을 담당할 메서드
	public int login(String memberId, String memberPwd) {
		return bs.login(memberId, memberPwd);
	}

	// view의 insertBoard요청을 담당할 메서드
	public int insertBoard(String title, String content, String writer) {
		return bs.insertBoard(new Board(title, content, writer));
	}

	// view의 selectBoardList요청을 담당할 메서드
	public List<Board> selectBoardList() {
		return bs.selectBoardList();
	}

	// view의 selectBoard요청을 담당할 메서드
	public Board selectBoard(int bno) {
		return bs.selectBoard(bno);
	}

	// view의 updateBoard요청을 담당할 메서드
	public int updateBoard(int bno, String title, String content, String writer) {
		return bs.updateBoard(bno, new Board(title, content, writer));
	}

	// view의 deleteBoard요청을 담당할 메서드
	public int deleteBoard(int bno) {
		return bs.deleteBoard(bno);
	}
			
}
