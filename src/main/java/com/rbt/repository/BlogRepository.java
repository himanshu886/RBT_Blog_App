package com.rbt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbt.entity.Blog;
import com.rbt.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByUser(User user);
}
