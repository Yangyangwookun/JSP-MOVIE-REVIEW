package dao;

import java.util.Date;

import lombok.Data;

@Data
public class UserType {
	private long id;
	private String type;
	public UserType(String type) {
		this.type = type;
	}
	
	public UserType(long id, String type) {
		this.id = id;
		this.type = type;
	}
}
