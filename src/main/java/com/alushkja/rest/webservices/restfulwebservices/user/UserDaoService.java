package com.alushkja.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static List<Post> userPosts = new ArrayList<>();
	
	private static PostDaoService postService;
	
	private static int usersCount = 3;
	
	static {
//		userPosts.add(postService.savePost(new Post(1,"This is 1st")));
//		userPosts.add(postService.savePost(new Post(2,"This is 2st")));
		users.add(new User(1, "Adam", new Date()));
//		userPosts.add(postService.savePost(new Post(1,"This is 1st")));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	 public List<User> findAll() {
		 return users;
	 }
	 
	 public User save(User user) {
		 if(user.getId() == null) {
			 user.setId(++usersCount);
		 }
		 users.add(user);
		 return user;
	 }
	 
	 public User findOne(int id) {
		 //TODO: refactor in Java 8
		 for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		 return null;
	 }
}
