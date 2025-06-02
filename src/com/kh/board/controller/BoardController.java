package com.kh.board.controller;

import com.kh.board.model.service.BoardServiceImpl;

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
	
	// view의 selectBoardList요청을 담당할 메서드
	
	// view의 selectBoard요청을 담당할 메서드
	
	// view의 insertBoard요청을 담당할 메서드
	
	// view의 updateBoard요청을 담당할 메서드
	
	// view의 deleteBoard요청을 담당할 메서드
	
	
			
}
