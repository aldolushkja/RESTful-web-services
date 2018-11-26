package com.alushkja.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alushkja.rest.webservices.restfulwebservices.exception.CannotCreateUserException;
import com.alushkja.rest.webservices.restfulwebservices.exception.ConflictCreateUserException;
import com.alushkja.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.alushkja.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.alushkja.rest.webservices.restfulwebservices.exception.UsersNotFoundException;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	private static int attemps = 0;

	// GET /users
	// retrieveAllUsers
	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers() {
		List<User> bufferList = new ArrayList<>();
		bufferList = userRepository.findAll();
		if (bufferList.isEmpty()) {
			throw new UsersNotFoundException("/users is empty");
		}
		return userRepository.findAll();
	}

	// GET /users/{id}
	// retrieveUser(int id)
	@GetMapping(path = "/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		// HATEOS
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	// DELETE /users/{id}
	// retrieveUser(int id)
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);

	}

	// input - details of user
	// output - CREATED & return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User buffUser : users) {

			if (user.getId() == buffUser.getId() && attemps == 0) {
				++attemps;
				throw new CannotCreateUserException(
						String.format("id %s does already exists or name field is empty", user.getId()));
			}
			if (user.getId() == buffUser.getId() && attemps > 0) {
				throw new ConflictCreateUserException(
						String.format("id %s has requested multiple times", user.getId()));
			} else {
				User savedUser = userRepository.save(user);
				// 200 OK - CREATED
				// /users/{id} savedUser.getId()

				// HATEOS (Hypermedia as the Engine of the Application State)
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedUser.getId()).toUri();

				return ResponseEntity.created(location).build();
			}
		}

		return null;

	}

	// GET /users/{id}/posts
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsersPosts(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
		return user.get().getPosts();
	}

	// input - details of post
	// output - 201 CREATED & return the created URI
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}		
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	// GET /users/{id}/posts
	@GetMapping(path = "/jpa/users/{id}/posts/{post_id}")
	public Post retrieveUserPost(@PathVariable int id , @PathVariable int post_id) {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}
	
		Optional<Post> selectedPost = postRepository.findById(post_id);
		
		if(!selectedPost.isPresent()) {
			throw new PostNotFoundException("post_id - " + post_id);
		}
		
		return selectedPost.get();
	}

}
