package repository;

import java.sql.SQLException;
import java.util.HashMap;

import common.DBCon;
import dao.ViewArticle;

public class ViewArticleRepository {
	private DBCon dbCon;
	private String sql;
	private ViewArticle viewArticle;
	private HashMap<Long, ViewArticle> viewArticles;
	
	public ViewArticleRepository() {
		this.dbCon = new DBCon();
		this.viewArticles = new HashMap<Long, ViewArticle>();
	}
	
	public HashMap<Long, ViewArticle> selectAll() {
		try {
			viewArticles.clear();
			sql = "select * from view_article";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				viewArticle = new ViewArticle(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("articleType"), dbCon.getRs().getString("title"),
						dbCon.getRs().getString("userName"), dbCon.getRs().getInt("viewCount"), dbCon.getRs().getInt("likeCount"),
						dbCon.getRs().getInt("dislikeCount"), dbCon.getRs().getString("movieTitle"), dbCon.getRs().getString("writeDate")
						);
				System.out.println(dbCon.getRs().getString("articleType"));
				viewArticles.put(dbCon.getRs().getLong("id"), viewArticle);
			}
			
			return viewArticles;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("viewArticle selectAll fail");
			return null;
		}finally {
			dbCon.close();			
		}
	}
	
	public HashMap<Long, ViewArticle> selectByArticleTypeId(long id) {
		try {
			viewArticles.clear();
			sql = "select * from view_article where articleTypeId = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, id);
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				viewArticle = new ViewArticle(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("articleType"), dbCon.getRs().getString("title"),
						dbCon.getRs().getString("userName"), dbCon.getRs().getInt("viewCount"), dbCon.getRs().getInt("likeCount"),
						dbCon.getRs().getInt("dislikeCount"), dbCon.getRs().getString("movieTitle"), dbCon.getRs().getString("writeDate")
						);
				System.out.println(dbCon.getRs().getString("articleType"));
				viewArticles.put(dbCon.getRs().getLong("id"), viewArticle);
			}
			
			return viewArticles;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("viewArticle selectByArticleTypeId fail");
			return null;
		}finally {
			dbCon.close();			
		}
	}
	
}
