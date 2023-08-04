package com.carecart.service;

import java.util.List;

import com.carecart.dto.UserUpdateDto;
import com.carecart.exception.UserException;
import com.carecart.models.Users;

public interface UserService {
	
	Users addUser(Users user,String secretKey) throws UserException;
	Users findByEmail(String email) throws UserException;
	Users updateUser(UserUpdateDto user)throws UserException;
	Users deleteUser(long id) throws UserException;
	List<Users> getAllUsers()throws UserException;
	
}
