package dao;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleType {
	private long id;
	private String type;
	public ArticleType(long id, String type) {
		this.id = id;
		this.type = type;
	}
}
