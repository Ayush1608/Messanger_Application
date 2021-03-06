package com.self.restfulwebservices.user.controller;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.self.restfulwebservices.exception.UserNotFoundException;
import com.self.restfulwebservices.user.PostJPARepostory;
import com.self.restfulwebservices.user.UserJPARepository;
import com.self.restfulwebservices.user.model.Post;
import com.self.restfulwebservices.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/jpa")
public class UserJPAController {

	@Autowired
	private PostJPARepostory postJPARepostory;

	@Autowired
	private UserJPARepository userJPARepository;


	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userJPARepository.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userJPARepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(webMvcLinkBuilder.withRel("all-users"));

		return entityModel;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userJPARepository.deleteById(id);
	}

	// input - details of user
	// output - CREATED & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userJPARepository.save(user);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

	@GetMapping("/users/{id}/posts")
//	public ResponseEntity<CollectionModel<Post>> getPosts(@PathVariable int id) { Commenting HATEOAS from this method
	public ResponseEntity<List<Post>> getPosts(@PathVariable int id) {
		Optional<User> user = userJPARepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

//		CollectionModel<Post> postCollectionModel = CollectionModel.of(user.get().getPosts());
//		WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(id));
//		postCollectionModel.add(webMvcLinkBuilder.withRel("user"));
//		return ResponseEntity.ok(postCollectionModel);

		return ResponseEntity.ok(user.get().getPosts());
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> getPosts(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userJPARepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		post.setUser(user.get());
		Post savedPost = postJPARepostory.save(post);

		URI location = ServletUriComponentsBuilder
										 .fromCurrentRequest()
										 .path("/{id}")
										 .buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
