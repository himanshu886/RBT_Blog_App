package com.rbt.service;

import java.util.List;

import com.rbt.payload.BlogDto;

public interface BlogService {
	
	//create
    BlogDto createBlog (BlogDto blogDto, Integer userId);
	
	//update
	BlogDto updateBlog(BlogDto blogDto, Integer blogId);
	
	//delete
	void deleteBlog(Integer blogId);
	
	//get single blog
	BlogDto getBlogById(Integer postId);
	
	//get all blogs by users
	List<BlogDto> getBlogByUser(Integer userId);
}
