package repository;

import java.sql.SQLException;
import java.util.HashMap;

import common.DBCon;
import dao.UserType;

public class UserTypeRepository {
	private DBCon dbCon;
	private String sql;
	private UserType userType;
	private HashMap<Long, UserType> userTypes;
	
	public UserTypeRepository() {
		this.dbCon = new DBCon();
		this.userTypes = new HashMap<Long, UserType>();
	}
	
	public HashMap<Long, UserType> selectAll() {
		try {
			sql = "select * from tbl_userType";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				userType = new UserType(dbCon.getRs().getLong("id"), dbCon.getRs().getString("type"));
				System.out.println(userType);
				userTypes.put(dbCon.getRs().getLong("id"), userType);
			}
			
			return userTypes;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("db조회 실패");
			return null;
		}finally {
			dbCon.closeAll();			
		}
	}
}
