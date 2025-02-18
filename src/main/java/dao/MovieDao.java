package dao;

import java.util.Date;

import lombok.Data;

@Data
public class MovieDao {
	private long id;
	private String title;
	private Date makeDate;
	private String makeCountry;
	private String director;
	private String company;
	public MovieDao() {}
	public MovieDao(long id, String title, Date makeDate, String makeCountry, String director, String company) {
		this.id = id;
		this.title = title;
		this.makeDate = makeDate;
		this.makeCountry = makeCountry;
		this.director = director;
		this.company = company;
	}
}
