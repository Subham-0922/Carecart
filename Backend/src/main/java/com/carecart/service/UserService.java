package com.carecart.service;

import com.carecart.exception.UserException;
import com.carecart.models.Users;

public interface UserService {
	
	Users addUser(Users user,String secretKey) throws UserException;
	Users findByEmail(String email) throws UserException;
	Users updateUser(Users user)throws UserException;
	Users deleteUser(long id) throws UserException;
	
}
