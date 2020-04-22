package com.springframework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.User;
import com.springframework.dto.UserReqDTO;
import com.springframework.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	private static final String USER_DOES_NOT_EXIST           = "user with id %s does not exist.";
	@Autowired
	UserRepository userRepository;
	
	public User saveUser(UserReqDTO userReqDTO) 
	{
		// TODO Auto-generated method stub
		User user = User.builder()
	                .fName(userReqDTO.getFName())
	                .lName(userReqDTO.getLName())
	                .userCode(userReqDTO.getUserCode())
	                .age(userReqDTO.getAge())
	                .telNumber(userReqDTO.getTelNumber())
	                .fileNbr(userReqDTO.getFileNbr())
	                .emailAddress(userReqDTO.getEmailAddress())
	                .password(userReqDTO.getPassword())
	                .ikamaNbr(userReqDTO.getIkamaNbr())
	                .build();
        
        return userRepository.save(user);
	}
	
	public User signIn(UserReqDTO userReqDTO) 
	{
		// TODO Auto-generated method stub 
		User user = userRepository.findByEmailAndPassword(userReqDTO.getPassword(), userReqDTO.getEmailAddress());
		return user;
	}

}
