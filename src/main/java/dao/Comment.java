package dao;

public class Comment {
	private long id;
	private String content;
	private String writeDate;
	private long tblUserId;
	private long tblArticleId;
	private int likeCount;
	private int dislikeCount;
	
	public Comment(String content, String writeDate, long tblUserId, long tblArticleId, int likeCount, int dislikeCount) {
		this.content = content;
		this.writeDate = writeDate;
		this.tblUserId = tblUserId;
		this.tblArticleId = tblArticleId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
	
	public Comment(long id, String content, String writeDate, long tblUserId, long tblArticleId, int likeCount, int dislikeCount) {
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.tblUserId = tblUserId;
		this.tblArticleId = tblArticleId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public long getTblUserId() {
		return tblUserId;
	}

	public void setTblUserId(long tblUserId) {
		this.tblUserId = tblUserId;
	}

	public long getTblArticleId() {
		return tblArticleId;
	}

	public void setTblArticleId(long tblArticleId) {
		this.tblArticleId = tblArticleId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

}
