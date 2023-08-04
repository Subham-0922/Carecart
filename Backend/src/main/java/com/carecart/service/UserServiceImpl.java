package com.carecart.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.carecart.dto.UserUpdateDto;
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
	public Users updateUser(UserUpdateDto user) throws UserException {
		Users existingUser=customerRepository.findById(user.getUserId()).orElseThrow( ()-> new UserException("User not found with this id"));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setCity(user.getCity());
		existingUser.setCountry(user.getCountry());
		existingUser.setPostalCode(user.getPostalCode());
		return customerRepository.save(existingUser);
	}

	@Override
	public Users deleteUser(long id) throws UserException {
		Users existingUser=customerRepository.findById(id).orElseThrow( ()-> new UserException("User not found with this id"));
		customerRepository.delete(existingUser);
		return existingUser;
	}

	@Override
	public List<Users> getAllUsers() throws UserException {
		// TODO Auto-generated method stub
		List<Users> users=customerRepository.findAll().stream().filter((a)->a.getRole()=="ROLE_USER").toList();
		if(users.isEmpty())throw new UserException("Users not found");
		return users;
	}

}
