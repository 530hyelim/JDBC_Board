package com.kh.board.model.vo;

import java.sql.Date;

/**
 * Board 테이블의 한행의 정보를 보관할 클래스
 *  */
public class Board {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date createDate;
	// 필요한 데이터 컬럼만 최소한으로 기술하는 것이 정석
	// 따라서 selectBoardList와 selectBoard용 dto를 따로 생성
	
	public Board() {
		
	}
	
	// insertBoard, updateBoard 용 생성자
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	// selectBoardList 용 toString 메서드
	@Override
	public String toString() {
		return bno + "\t" + title + "\t" + writer + "\t" + createDate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
