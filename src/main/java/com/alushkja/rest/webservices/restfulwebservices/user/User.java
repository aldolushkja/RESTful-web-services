package com.alushkja.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.mapping.Array;

public class User {

	private Integer id;

	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;

	@Past
	private Date birthDate;

	private List<Post> posts = new ArrayList<>();

	// used to make POST request
	protected User() {

	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public User(Integer id, String name, Date birthDate, List<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.posts = posts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
