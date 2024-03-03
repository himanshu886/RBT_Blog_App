package com.rbt.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.entity.Blog;
import com.rbt.entity.User;
import com.rbt.exception.ResourceNotFoundException;
import com.rbt.payload.BlogDto;
import com.rbt.repository.BlogRepository;
import com.rbt.repository.UserRepository;
import com.rbt.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepo; // to perform db operations

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public BlogDto createBlog(BlogDto blogDto, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		
		Blog blog = this.modelMapper.map(blogDto, Blog.class);
		blog.setUser(user);

		Blog newBlog = this.blogRepo.save(blog);

		return this.modelMapper.map(newBlog, BlogDto.class);
	}

	@Override
	public BlogDto updateBlog(BlogDto blogDto, Integer blogId) {
		Blog blog = blogRepo.findById(blogId)
				.orElseThrow(() -> new ResourceNotFoundException("Blog", "Blog id", blogId));
		
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		
		Blog updatedBlog = blogRepo.save(blog);
		return this.modelMapper.map(updatedBlog, BlogDto.class);
	}

	@Override
	public void deleteBlog(Integer blogId) {
		
		Blog blog = blogRepo.findById(blogId)
			.orElseThrow(()-> new ResourceNotFoundException("Blog", "Blog id", blogId));
		
		blogRepo.delete(blog);
		
	}

	@Override
	public BlogDto getBlogById(Integer BlogId) {
		
		Blog blog = blogRepo.findById(BlogId)
		.orElseThrow(()-> new ResourceNotFoundException("Blog", "Blog Id", BlogId));
		
		return modelMapper.map(blog, BlogDto.class);
	}

	@Override
	public List<BlogDto> getBlogByUser(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","userId", userId));
		
		List<Blog> blogs = blogRepo.findByUser(user);
		
		List<BlogDto> blogDtos = blogs.stream().map((blog)-> modelMapper.map(blog, BlogDto.class)).collect(Collectors.toList());
		
		return blogDtos;
	}

}
