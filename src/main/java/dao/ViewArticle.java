package dao;

public class ViewArticle {
	private long id;
	private String articleType;
	private String title;
	private String userName;
	private int viewCount;
	private int likeCount;
	private int dislikeCount;
	private String movieTitle;
	private String writeDate;
	
	public ViewArticle(
			String articleType, String title, 
			String userName, int viewCount, int likeCount, 
			int dislikeCount, String movieTitle, String writeDate) {
		this.articleType = articleType;
		this.title = title;
		this.userName = userName;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.movieTitle = movieTitle;
		this.writeDate = writeDate;
	}
	
	public ViewArticle(
			long id, String articleType, String title, 
			String userName, int viewCount, int likeCount, 
			int dislikeCount, String movieTitle, String writeDate) {
		this.id = id;
		this.articleType = articleType;
		this.title = title;
		this.userName = userName;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.movieTitle = movieTitle;
		this.writeDate = writeDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

}
