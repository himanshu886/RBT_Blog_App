package com.rbt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbt.payload.ApiResponse;
import com.rbt.payload.BlogDto;
import com.rbt.service.BlogService;

@RestController
@RequestMapping("/api")
public class BlogController {
	
	@Autowired 
	BlogService blogService;
	
	//CREATE BLOG
	@PostMapping("/user/{userId}/blogs")
	public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto, @PathVariable Integer userId){
		
		BlogDto createBlog = blogService.createBlog(blogDto, userId);
		return new ResponseEntity<BlogDto>(createBlog, HttpStatus.CREATED);
	}
	
	//UPDATE BLOG
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<BlogDto> updateBlog(@RequestBody BlogDto blogDto, @PathVariable Integer blogId){
		BlogDto updatedBlog = blogService.updateBlog(blogDto, blogId);
		
		return new ResponseEntity<BlogDto>(updatedBlog, HttpStatus.OK);
	}

	//DELETE BLOG
	@DeleteMapping("/blogs/{blogId}")
	public ApiResponse deleteBlog(@PathVariable Integer blogId) {
		blogService.deleteBlog(blogId);
		return new ApiResponse("Blog is successfully deleted!!", true);
	}
	
	//GET BLOGS BY USER
	@GetMapping("/user/{userId}/blogs")
	public ResponseEntity<List<BlogDto>> getBlogByUser(@PathVariable Integer userId){
		
		List<BlogDto> blogs = blogService.getBlogByUser(userId);
		
		return new ResponseEntity<List<BlogDto>>(blogs, HttpStatus.OK);
	}
	
	

}
