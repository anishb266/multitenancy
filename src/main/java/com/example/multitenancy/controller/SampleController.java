package com.example.multitenancy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.multitenancy.dao.UserRepository;
import com.example.multitenancy.entity.User;

@RestController
@Transactional
public class SampleController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		Optional<User> user = userRepository.findById(Long.valueOf(id));
		User userDemo = user.get();
		return ResponseEntity.ok(userDemo);
	}

	@PostMapping(value = "/create/user")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok("User is saved");
	}
}
