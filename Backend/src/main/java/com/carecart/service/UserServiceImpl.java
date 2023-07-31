package com.carecart.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.carecart.exception.UserException;
import com.carecart.models.Users;
import com.carecart.repository.CustomerRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomerRepository customerRepository;
	@Value("${secretKey}")
	private String secretKey;
	@Override
	public Users addUser(Users user, String secretKey) throws UserException {
		if(user==null)throw new UserException("User details not Valid");
		if(secretKey.equals(this.secretKey)) {
			user.setRole("ROLE_ADMIN");
		}else {
			user.setRole("ROLE_USER");
		}
		return customerRepository.save(user);
	}

	@Override
	public Users findByEmail(String email) throws UserException {
		Users user=customerRepository.findByEmail(email);
		if (user==null)throw new UserException("User not found with this email Address");
		return user;
	}

	@Override
	public Users updateUser(Users user) throws UserException {
		Users existingUser=customerRepository.findById(user.getUserId()).orElseThrow( ()-> new UserException("User not found with this id"));
		
		return customerRepository.save(user);
	}

	@Override
	public Users deleteUser(long id) throws UserException {
		Users existingUser=customerRepository.findById(id).orElseThrow( ()-> new UserException("User not found with this id"));
		customerRepository.delete(existingUser);
		return existingUser;
	}

}
