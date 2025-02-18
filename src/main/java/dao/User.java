package dao;

import java.util.Date;

import lombok.Data;

@Data
public class User{
	private long id;
	private String email;
	private String password;
	private String name;
	private int age;
	private String phoneNumber;
	private long tblUserTypeId;
	public User(String email, String password, String name, int age, String phoneNumber, long tblUserTypeId) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.tblUserTypeId = tblUserTypeId;
	}
	
	public User(long id, String email, String password, String name, int age, String phoneNumber, long tblUserTypeId) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.tblUserTypeId = tblUserTypeId;
	}
}
