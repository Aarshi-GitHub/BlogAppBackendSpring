package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.*;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/user")
public class PostController {
	@Autowired
	PostService postSer;
	@GetMapping("/{username}/blogs")
	public List<Post> getPosts(@PathVariable("username")String username){
		return postSer.getAll(username);
	}
	
	@GetMapping("/{username}/blogs/{id}")
	public Post getPost(@PathVariable("username")String username,@PathVariable("id")int pid){
		return postSer.getPost(pid);
	}
	
	@PostMapping("/{username}/blogs/create")
	public ResponseEntity<String> createPosts(@PathVariable("username")String username,@RequestBody Post post){
		postSer.createPost(username, post);
		return new ResponseEntity<>("Blog created", HttpStatus.OK);
	}
	
	@PutMapping("/{username}/blogs/update/{id}")
	public ResponseEntity<String> updatePost(@PathVariable("username")String username,@PathVariable("id")int pid,@RequestBody Post post){
		return postSer.updatePost(username, pid, post);
	}
	
	@DeleteMapping("{username}/blogs/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("username")String username,@PathVariable("id")int pid){
		return postSer.deletePost(username, pid);
	}
	
}
