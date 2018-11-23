package com.alushkja.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.alushkja.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.alushkja.rest.webservices.restfulwebservices.exception.UsersNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	private static int attemps = 0;

	// GET /users
	// retrieveAllUsers
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		List<User> bufferList = new ArrayList<>();
		bufferList = service.findAll();
		if (bufferList.isEmpty()) {
			throw new UsersNotFoundException("/users is empty");
		}
		return service.findAll();
	}

	// GET /users/{id}
	// retrieveUser(int id)
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}
	
	// DELETE /users/{id}
	// retrieveUser(int id)
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		
	}

	// input - details of user
	// output - CREATED & return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		List<User> users = service.findAll();
		
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
				User savedUser = service.save(user);
				// CREATED
				// /users/{id} savedUser.getId()

				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(savedUser.getId()).toUri();

				return ResponseEntity.created(location).build();
			}
		}
		
		return null;

	}
}
