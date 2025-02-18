package repository;

import java.sql.SQLException;
import java.util.HashMap;

import common.DBCon;
import dao.ArticleType;
import dao.User;

public class ArticleTypeRepository {
	private DBCon dbCon;
	private String sql;
	private HashMap<Long, ArticleType> articleTypes;
	private ArticleType articleType;
	
	public ArticleTypeRepository() {
		this.dbCon = new DBCon();
		this.articleTypes = new HashMap<Long, ArticleType>();
	}
	
	
	public HashMap<Long, ArticleType> selectAll() {
		try {
			sql = "select * from tbl_articleType";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));			
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				articleType = new ArticleType(dbCon.getRs().getLong("id"), dbCon.getRs().getString("type"));
				articleTypes.put(dbCon.getRs().getLong("id"), articleType);
			}
			
			return articleTypes;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("tbl_user select fail");
			return null;
		}finally {
			dbCon.closeAll();			
		}
	}
	
}
