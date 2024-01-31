package com.example.demo.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.PostRepo;

@Service
public class PostService {
	@Autowired
	PostRepo postRep;
	@Autowired
	UserService userSer;
	public List<Post> getAll(String username){
		User user = userSer.getUser(username);
		return postRep.findByUser(user);
	}
	public void createPost(String username,Post post) {
		User user = userSer.getUser(username);
		post.setUser(user);
		postRep.save(post);
	}
	public Post getPost(int pid) {
		Optional<Post> postData = postRep.findById(pid);
		if(postData.isPresent()) return postData.get();
		return null;
	}
	
	public ResponseEntity<String> updatePost(String username,int pid,Post post){
		if(getPost(pid)==null)
			return new ResponseEntity<>( "Blog not found",  HttpStatus.NOT_FOUND);
		User postUser = getPost(pid).getUser();
		if(username.equals(postUser.getUsername())) {
			post.setUser(postUser);
			postRep.save(post);
			return new ResponseEntity<>("Blog update", HttpStatus.OK);
		}
		return new ResponseEntity<>( "User can only update it's own blogs",  HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> deletePost(String username,int pid){
		if(getPost(pid)==null)
			return new ResponseEntity<>( "Blog not found",  HttpStatus.NOT_FOUND);
		User postUser = getPost(pid).getUser();
		if(username.equals(postUser.getUsername())) {
			postRep.deleteById(pid);
			return new ResponseEntity<>("Blog deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>( "User can only delete it's own blogs",  HttpStatus.BAD_REQUEST);
	}
}
