package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String url;
	private String user;
	private String password;
	
	public DBCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.url = "jdbc:mysql://localhost:3306/db_jspSqlProject";
	        this.user = "user_jspSqlProject";
	        this.password = "1234";
	        
	        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
	        
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("db객체 종료 실패");
		}
	}
	
	public void closeAll() {
		try {
			this.rs.close();
			this.ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("db객체 종료 실패");
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	
	
}
