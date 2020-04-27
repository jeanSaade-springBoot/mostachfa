package com.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Admin;
import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;
import com.springframework.dto.AdminReqDTO;
import com.springframework.dto.DoctorReqDTO;
import com.springframework.dto.PatientReqDTO;
import com.springframework.dto.SignInReqDTO;
import com.springframework.repositories.AdminRepository;
import com.springframework.repositories.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {
	
	private static final String USER_DOES_NOT_EXIST           = "user with id %s does not exist.";
	@Autowired
	AdminRepository adminRepository;
	
	
	public Optional<Admin> findAdminByEmail(String emailAddress) 
	{
		// TODO Auto-generated method stub 
		return adminRepository.findByEmailAddress(emailAddress);
	}
	
	public Admin signIn(SignInReqDTO signInReqDTO) 
	{
		// TODO Auto-generated method stub 
		Admin admin = adminRepository.findByPasswordAndEmailAddress(signInReqDTO.getPassword(), signInReqDTO.getEmailAddress());
		return admin;
	}
	
	public Admin saveAdmin(AdminReqDTO adminReqDTO)
	{
		// TODO Auto-generated method stub
		Admin admin = Admin.builder()
	                .fName(adminReqDTO.getFirstName())
	                .fatherName(adminReqDTO.getFatherName())
	                .lName(adminReqDTO.getLastName())
	                .emailAddress(adminReqDTO.getEmailAddress())
	                .password(adminReqDTO.getPassword())
	                .build();
        return adminRepository.save(admin);
	}

}
