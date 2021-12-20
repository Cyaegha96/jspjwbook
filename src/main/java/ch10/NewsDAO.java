package ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
	public void addNews(News n) throws Exception{
		
		Connection conn = open();
		String sql = "insert into news(title, img, date, content) values(? ,? ,CURRENT_TIMESTAMP() ,? )";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3,n.getContent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}
	}
	
	public List<News> getAll() throws Exception{
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from news";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				
				newsList.add(n);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}
		return newsList;
	}
	
	public News getNews(int aid) throws Exception{
		Connection conn = open();
		News n = new News();
		
		String sql = "select aid, title, img, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from news where aid=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			
			rs =  pstmt.executeQuery();
			
			rs.next();
			
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			
			pstmt.executeQuery();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}
		
		return n;
	}
	
	public void delNews(int aid) throws SQLException {
		Connection conn = open();
		
		String sql ="delete from news where aid=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,aid);
			
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
			
		}catch(Exception e) {
		
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}
	}
}
