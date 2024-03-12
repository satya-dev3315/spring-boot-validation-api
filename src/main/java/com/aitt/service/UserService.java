package com.aitt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitt.customException.UserNotFoundCustomException;
import com.aitt.dto.UserDto;
import com.aitt.entity.User;
import com.aitt.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	
	//save user
	
	public User saveUser(UserDto dto) {
		
		 User user = new User();
		 user.setAge(dto.getAge());
		 user.setEmail(dto.getEmail());
		 user.setGender(dto.getGender());
		 user.setMobile(dto.getMobile());
		 user.setName(dto.getName());
		 user.setNationality(dto.getNationality());
		 
		return userRepo.save(user);
		
	}
	
	
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	
	
	
	public User getUserById(Integer id) throws UserNotFoundCustomException {
		 Optional<User> findById = userRepo.findById(id);
		 if(findById.isPresent()) {
			 return findById.get();
		 }
		throw new UserNotFoundCustomException("User not found with user id : " +id);  
		
		//if we search user by id that is nt present in db , instead of throwing null gve exception
		//once u get this exception , deligate the req to UserAppException.class
	}
}
