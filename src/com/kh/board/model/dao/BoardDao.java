package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.board.model.template.JDBCTemplate;

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
			stmt = conn.prepareStatement(prop.getProperty("login"));
			stmt.setString(1, memberId);
			stmt.setString(2, memberPwd);
			rset = stmt.executeQuery();
			
			while (rset.next()) {
				System.out.println(rset.getString("MEMBER_NAME"));
				
				result++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

}
