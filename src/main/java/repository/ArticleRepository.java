package repository;

import java.sql.SQLException;
import java.util.HashMap;

import common.DBCon;
import dao.Article;
import dao.UserType;

public class ArticleRepository {
	private DBCon dbCon;
	private String sql;
	private Article article;
	private HashMap<Long, Article> articles;
	
	public ArticleRepository() {
		this.dbCon = new DBCon();
		this.articles = new HashMap<Long, Article>();
	}
	
	public boolean insert(Article article) {
		try {
			sql = "insert into tbl_article(id, writeDate, content, title, viewCount, tbl_user_id, likeCount, dislikeCount, tbl_movie_id, tbl_articleType_id) value(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, article.getWriteDate());
			dbCon.getPs().setString(2, article.getContent());
			dbCon.getPs().setString(3, article.getTitle());
			dbCon.getPs().setInt(4, article.getViewCount());
			dbCon.getPs().setLong(5, article.getTblUserId());
			dbCon.getPs().setInt(6, article.getLikeCount());
			dbCon.getPs().setInt(7, article.getDislikeCount());
			dbCon.getPs().setLong(8, article.getTblMovieId());
			dbCon.getPs().setLong(9, article.getTblArticleTypeId());
			
			if(dbCon.getPs().executeUpdate() == 1) {
				return true;
			}
			
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("article insert fail");
			return false;
		}finally {
			dbCon.close();			
		}
	}
	
	public HashMap<Long, Article> selectAll() {
		try {
			sql = "select * from tbl_article";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				article = new Article(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("writeDate"), dbCon.getRs().getString("content"),
						dbCon.getRs().getString("title"), dbCon.getRs().getInt("viewCount"), dbCon.getRs().getLong("tbl_user_id"),
						dbCon.getRs().getInt("likeCount"), dbCon.getRs().getInt("dislikeCount"), dbCon.getRs().getLong("tbl_movie_id"),
						dbCon.getRs().getLong("tbl_articleType_id")
						);
				articles.put(dbCon.getRs().getLong("id"), article);
			}
			
			return articles;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("article selectAll fail");
			return null;
		}finally {
			dbCon.close();			
		}
	}
	public boolean updateview(long id) {
		boolean result=false;
		try {
			sql="update `tbl_article` set viewCount = viewCount+1 where id = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, id);
			int updatecount=dbCon.getPs().executeUpdate();
			if(updatecount==1) {
				result=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("article viewupdate fail");
		}
		return result;
	}
	
	
	public boolean updatelike() {
		boolean result=false;
		sql="update `tbl_article` set viewCount = viewCount+1 where id = ?";
		return result;
	}
	
	public boolean updatedislike() {
		boolean result=false;
		sql="update `tbl_article` set viewCount = viewCount+1 where id = ?";
		return result;
	}
	
	public Article selectById(long id) {
		try {
			sql = "select * from tbl_article where id = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, id);
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			if(dbCon.getRs().next()) {
				article = new Article(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("writeDate"), dbCon.getRs().getString("content"),
						dbCon.getRs().getString("title"), dbCon.getRs().getInt("viewCount"), dbCon.getRs().getLong("tbl_user_id"),
						dbCon.getRs().getInt("likeCount"), dbCon.getRs().getInt("dislikeCount"), dbCon.getRs().getLong("tbl_movie_id"),
						dbCon.getRs().getLong("tbl_articleType_id")
						);
			}
			return article;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("article selectById fail");
			return null;
		}finally {
			dbCon.close();			
		}
	}
}
