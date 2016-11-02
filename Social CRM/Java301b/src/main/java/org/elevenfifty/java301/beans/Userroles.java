package org.elevenfifty.java301.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")

public class Userroles {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	private String userId;
	private String role;
	private int key;
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getKey() {
		return key;
	}



	public void setKey(int key) {
		this.key = key;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
