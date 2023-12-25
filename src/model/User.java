package model;

import java.io.Serializable;

public abstract class User implements Serializable{
	private String email;
	private String password;
	private String name;
	
	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	 @Override
    public String toString() {
        return "Name: " + this.name + " Email: " + this.email; 
	}
}
