package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRep;
	public User getUser(String username) {
		Optional<User> userData = userRep.findById(username);
		if(userData.isPresent()) return userData.get();
		return null;
	}
	
	public ResponseEntity<String> register(User user) {
		User u = getUser(user.getUsername());
		if(u!=null) 
			return new ResponseEntity<>(
		        "User already exists login into your account", 
		          HttpStatus.BAD_REQUEST);
		userRep.save(user);
		return new ResponseEntity<>("User Registered successfully", HttpStatus.OK);
	}
	
	public User login(User user){
		User u = userRep.findUser(user.getUsername(), user.getPassword());
		return u;
	}
}
