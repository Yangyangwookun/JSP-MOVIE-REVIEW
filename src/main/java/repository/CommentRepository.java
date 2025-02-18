package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import common.DBCon;
import dao.Comment;
import dao.CommentDao;

public class CommentRepository {

	private DBCon dbCon;
	private CommentDao comment;
	private HashMap<Long, Comment> comments;
	
	public static CommentRepository repository=new CommentRepository();
	
	public CommentRepository() {
		this.dbCon = new DBCon();
		this.comments = new HashMap<Long, Comment>();
	}
	
	public static CommentRepository getInstance() {
		return repository;
	}
	// 모든 댓글 목록 가져오기
	public List<CommentDao> GetCommentList_All(){
		List<CommentDao> commentlist=new ArrayList<>();
		String sql="SELECT * FROM `tbl_comment`";

		try {

			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.setRs(dbCon.getPs().executeQuery());
			while(dbCon.getRs().next()) {
				CommentDao comment=new CommentDao();
				
				long id=dbCon.getRs().getLong(1);
				String content=dbCon.getRs().getString(2);
				Date writeDate=dbCon.getRs().getDate(3);
				int likeCount=dbCon.getRs().getInt(4);
				int dislikeCount=dbCon.getRs().getInt(5);
				long tbl_user_id=dbCon.getRs().getLong(6);
				long tbl_article_id=dbCon.getRs().getLong(7);
				
				comment.setId(id);
				comment.setContent(content);
				comment.setWriteDate(writeDate);
				comment.setLikeCount(likeCount);
				comment.setDislikeCount(dislikeCount);
				comment.setTbl_user_id(tbl_user_id);
				comment.setTbl_article_id(tbl_article_id);
				
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentlist;
	}
	
	//해당 게시글의 댓글 목록 가져오기
	public List<CommentDao> GetCommentList_Article(long tbl_article_id){
		List<CommentDao> commentlist=new ArrayList<>();
		String sql="SELECT * FROM `tbl_comment` WHERE ( tbl_article_id = ? ) "
				+ "ORDER BY id";

		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1,tbl_article_id);
			dbCon.setRs(dbCon.getPs().executeQuery());
			while(dbCon.getRs().next()) {
				CommentDao comment=new CommentDao();
				
				long id=dbCon.getRs().getLong(1);
				String content=dbCon.getRs().getString(2);
				Date writeDate=dbCon.getRs().getDate(3);
				int likeCount=dbCon.getRs().getInt(4);
				int dislikeCount=dbCon.getRs().getInt(5);
				
				long tbl_user_id=dbCon.getRs().getLong(6);
				
				comment.setId(id);
				comment.setContent(content);
				comment.setWriteDate(writeDate);
				comment.setLikeCount(likeCount);
				comment.setDislikeCount(dislikeCount);
				comment.setTbl_user_id(tbl_user_id);
				
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentlist;
	}
	
	//해당 게시글의 특정 페이지의 댓글 목록 가져오기
	public List<CommentDao> GetCommentList_ArticlePage(long tbl_article_id, int num, int pagenum){
		int IgnoreNum=(num*(pagenum-1));		
		List<CommentDao> commentlist=new ArrayList<>();
		String sql="SELECT * FROM `tbl_comment` WHERE ( tbl_article_id = ? ) "
				+ " ORDER BY id LIMIT ? OFFSET ?";

		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, tbl_article_id);
			dbCon.getPs().setInt(2, num);
			dbCon.getPs().setInt(3, IgnoreNum);
			dbCon.setRs(dbCon.getPs().executeQuery());
			while(dbCon.getRs().next()) {
				CommentDao comment=new CommentDao();
				
				long id=dbCon.getRs().getLong(1);
				String content=dbCon.getRs().getString(2);
				Date writeDate=dbCon.getRs().getDate(3);
				int likeCount=dbCon.getRs().getInt(4);
				int dislikeCount=dbCon.getRs().getInt(5);
				
				long tbl_user_id=dbCon.getRs().getLong(6);
				
				comment.setId(id);
				comment.setContent(content);
				comment.setWriteDate(writeDate);
				comment.setLikeCount(likeCount);
				comment.setDislikeCount(dislikeCount);
				comment.setTbl_user_id(tbl_user_id);
				
				commentlist.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentlist;
	}
	
	//댓글 입력하기
	public boolean InsertComment(CommentDao input_comment) {
		boolean result=false;
		String sql="INSERT INTO `tbl_comment` (`content`,`writeDate`,`likeCount`,"
				+ "`dislikeCount`,`tbl_user_id`,`tbl_article_id`) "
				+ "VALUES(?,now(),0,0,?,?)";
		
		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, input_comment.getContent());
			dbCon.getPs().setLong(2, input_comment.getTbl_user_id());
			dbCon.getPs().setLong(3, input_comment.getTbl_article_id());
			
			int insertcount=dbCon.getPs().executeUpdate();
			
			if(insertcount>0) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//댓글 수정하기
	public boolean UpdateComment(CommentDao update_coment) {
		boolean result=false;
		String sql="UPDATE `tbl_comment` SET content = ? WHERE (id = ?)";
		

		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, update_coment.getContent());
			dbCon.getPs().setLong(2, update_coment.getId());
			int updatecount=dbCon.getPs().executeUpdate();
			
			if(updatecount>0) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	//댓글 삭제하기
	public boolean DeleteComment(long delete_comment_id) {
		boolean result=false;
		String sql="DELETE FROM `tbl_comment` WHERE ( id = ? )";

		try {
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, delete_comment_id);
			
			int deletecount=dbCon.getPs().executeUpdate();
			if(deletecount>0) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public HashMap<Long, Comment> selectByTblArticleId(long tblArticleId) {
		try {
			Comment comment;
			comments.clear();
			String sql = "select * from tbl_comment where tbl_article_id = ?";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setLong(1, tblArticleId);
			dbCon.setRs(dbCon.getPs().executeQuery());
			
			while(dbCon.getRs().next()) {
				comment = new Comment(
						dbCon.getRs().getLong("id"), dbCon.getRs().getString("content"), dbCon.getRs().getString("writeDate"),
						dbCon.getRs().getLong("tbl_user_id"), dbCon.getRs().getLong("tbl_article_id"), dbCon.getRs().getInt("likeCount"),
						dbCon.getRs().getInt("dislikeCount")
						);
				comments.put(dbCon.getRs().getLong("id"), comment);
			}
			
			return comments;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("article selectAll fail");
			return null;
		}finally {
			dbCon.close();			
		}
	}
	
	public boolean insert(Comment comment) {
		try {
			String sql = "insert into tbl_comment(id, content, writeDate, tbl_user_id, tbl_article_id, likeCount, dislikeCount) value(null, ?, ?, ?, ?, ?, ?)";
			dbCon.setPs(dbCon.getConn().prepareStatement(sql));
			dbCon.getPs().setString(1, comment.getContent());
			dbCon.getPs().setString(2, comment.getWriteDate());
			dbCon.getPs().setLong(3, comment.getTblUserId());
			dbCon.getPs().setLong(4, comment.getTblArticleId());
			dbCon.getPs().setInt(5, comment.getLikeCount());
			dbCon.getPs().setInt(6, comment.getDislikeCount());
			
			if(dbCon.getPs().executeUpdate() == 1) {
				return true;
			}
			
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("comment insert fail");
			return false;
		}finally {
			dbCon.close();			
		}
	}
}
