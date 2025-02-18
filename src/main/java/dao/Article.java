package dao;

import java.util.Date;

import lombok.Data;

@Data
public class Article {
	private long id;
	private String writeDate;
	private String content;
	private String title;
	private int viewCount;
	private int likeCount;
	private int dislikeCount;
	private long tblMovieId;
	private long tblUserId;
	private long tblArticleTypeId;
	public Article(
			String writeDate, String content, 
			String title, int viewCount, long tblUserId, 
			int likeCount, int dislikeCount, long tblMovieId, 
			long tblArticleTypeId) {
		this.writeDate = writeDate;
		this.content = content;
		this.title = title;
		this.viewCount = viewCount;
		this.tblUserId = tblUserId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.tblMovieId = tblMovieId;
		this.tblArticleTypeId = tblArticleTypeId;
	}
	
	public Article(
			long id, String writeDate, String content, 
			String title, int viewCount, long tblUserId, 
			int likeCount, int dislikeCount, long tblMovieId, 
			long tblArticleTypeId) {
		this.id = id;
		this.writeDate = writeDate;
		this.content = content;
		this.title = title;
		this.viewCount = viewCount;
		this.tblUserId = tblUserId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.tblMovieId = tblMovieId;
		this.tblArticleTypeId = tblArticleTypeId;
	}
}
