package com.alushkja.rest.webservices.restfulwebservices.user;

public class Post {
	
	private Integer id;
	private String message;
	
	
	// used to make POST request
	protected Post() {
		
	}
	
	public Post(Integer id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, message=%s]", id, message);
	}
	
	
	
	
	

}
