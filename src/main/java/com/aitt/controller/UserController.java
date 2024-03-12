package com.aitt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aitt.customException.UserNotFoundCustomException;
import com.aitt.dto.UserDto;
import com.aitt.entity.User;
import com.aitt.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody @Valid UserDto dto){  // we put valid because spring can understand in dto we added validation annotations , so use them
		User user = service.saveUser(dto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers(){
		List<User> allUsers = service.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) throws UserNotFoundCustomException{
		User user = service.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);		
	}
	
	//=> Steps to spring validation api :
    //1. Create an entity, repo,controller,service classes.Create a dto class (which resembles entity) to which we will add validation annotations.
	//2. Now in controller saveUser() method, write @Valid.And write validation annotations to dto class.
	//3. Create an exception handler class.
    //url : https://www.youtube.com/watch?v=gPnd-hzM_6A

	//In h2 db , its in memory db , so all records will b deleted if u will restart the app.
}
