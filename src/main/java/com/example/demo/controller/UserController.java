package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.*;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userSer;
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody User user) {
		return userSer.register(user);
	}
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody User user) {
		User u = userSer.login(user);
		if(u==null)
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>("redirect:"+u.getUsername()+"/blogs", HttpStatus.OK);
	}
	
}
