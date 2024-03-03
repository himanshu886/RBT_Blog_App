package com.rbt.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.entity.User;
import com.rbt.exception.ResourceNotFoundException;
import com.rbt.payload.UserDto;
import com.rbt.repository.UserRepository;
import com.rbt.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;	
	
	@Override
	public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);			
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAbout(userDto.getAbout());	
		
		User updatedUser = this.userRepo.save(user); 
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1; 
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));		
		this.userRepo.delete(user);
		
	}
	
    public User dtoToUser(UserDto userDto) { //it converts one object to another
		User user = this.modelMapper.map(userDto, User.class);
		return user;	
	}
	
	public UserDto userToDto (User user) { // converting user entity to userDto
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
