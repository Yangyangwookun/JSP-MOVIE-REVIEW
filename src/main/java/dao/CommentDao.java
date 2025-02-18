package dao;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDao {
	private long id;
	private String content;
	private Date writeDate;
	private int likeCount;
	private int dislikeCount;
	private long tbl_user_id;
	private long tbl_article_id;
	
	public CommentDao() {
		
	}
	
	public CommentDao(String content, Date writeDate, long tblUserId, long tblArticleId, int likeCount, int dislikeCount) {
		this.content = content;
		this.writeDate = writeDate;
		this.tbl_user_id = tblUserId;
		this.tbl_article_id = tblArticleId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
	
	public CommentDao(long id, String content, Date writeDate, long tblUserId, long tblArticleId, int likeCount, int dislikeCount) {
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.tbl_user_id = tblUserId;
		this.tbl_article_id = tblArticleId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
}
