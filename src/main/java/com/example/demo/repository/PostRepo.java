package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer>{
	public List<Post> findByUser(User user);
}
