package com.alushkja.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PostDaoService {

	@Autowired
	private UserDaoService service;

	private static List<Post> posts = new ArrayList<>();

	private static int postsCount = 0;

	public List<Post> findAllPosts() {
		return posts;
	}

	public Post savePost(Post post) {
		if (post.getId() == null) {
			post.setId(++postsCount);
		}
		posts.add(post);
		return post;
	}

	public Post findOnePost(int id) {
		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}
		return null;
	}

}
