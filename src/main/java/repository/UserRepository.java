package repository;

import java.sql.SQLException;

import common.DBCon;
import dao.User;

public class UserRepository {
	private DBCon dbCon;
	private String sql;
	private User user;
	
	public UserRepository() {
		this.dbCon = new DBCon();
	}
	
	public boolean insert(User user) {
		try {
			sql = "insert into tbl_user(id, email, password, name, age, phoneNumber, tbl_userType_id) value(null, ?, ?, ?, ?, ?, ?)";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, user.getEmail());
			dbCon.getPs().setString(2, user.getPassword());
			dbCon.getPs().setString(3, user.getName());
			dbCon.getPs().setInt(4, user.getAge());
			dbCon.getPs().setString(5, user.getPhoneNumber());
			dbCon.getPs().setLong(6, user.getTblUserTypeId());
			
			if(dbCon.getPs().executeUpdate() == 1) {
				return true;
			}
			
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("user insert fail");
			return false;
		}finally {
			dbCon.close();			
		}
	}
	
	public User selectByEmail(String email) {
		try {
			sql = "select * from tbl_user where email = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, email);
			
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			if(dbCon.getRs().next()) {
				user = new User(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("email"), 
						dbCon.getRs().getString("password"), dbCon.getRs().getString("name"), 
						dbCon.getRs().getInt("age"), dbCon.getRs().getString("phoneNumber"), 
						dbCon.getRs().getLong("tbl_userType_id")
						);
				return user;
			}
			
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("user select fail");
			return null;
		}finally {
			dbCon.closeAll();			
		}
	}
	
	public User selectById(long id) {
		try {
			sql = "select * from tbl_user where id = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, id);
			
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			if(dbCon.getRs().next()) {
				user = new User(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("email"), 
						dbCon.getRs().getString("password"), dbCon.getRs().getString("name"), 
						dbCon.getRs().getInt("age"), dbCon.getRs().getString("phoneNumber"), 
						dbCon.getRs().getLong("tbl_userType_id")
						);
				return user;
			}
			
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("user selectById fail");
			return null;
		}finally {
			dbCon.closeAll();			
		}
	}
}
