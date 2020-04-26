package com.springframework.utils;

import java.util.Optional;

import com.springframework.domain.Admin;
import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;
import com.springframework.dto.SignInResponseDTO;

public class UserUtils {
	
	public static SignInResponseDTO buildSigninResponseFromPatient(Patient pat)
	{
		SignInResponseDTO signInResponseDTO = SignInResponseDTO.builder()
				.id(pat.getId())
		        .fName(pat.getFName())
		        .lName(pat.getLName())
		        .userCode(pat.getUserCode())
		        .age(pat.getAge())
		        .telNumber(pat.getTelNumber())
		        .fileNbr(pat.getFileNbr())
		        .emailAddress(pat.getEmailAddress())
		        .password(pat.getPassword())
		        .nationalNbr(pat.getNationalNbr())
		        .isAdmin(false)
		        .isDoctor(false)
		        .isPatient(true)
		        .build();
		return signInResponseDTO;
	}
	
	public static SignInResponseDTO buildSigninResponseFromAdmin(Admin user)
	{
		SignInResponseDTO signInResponseDTO = SignInResponseDTO.builder()
				.id(user.getId())
		        .fName(user.getFName())
		        .lName(user.getLName())
		        .emailAddress(user.getEmailAddress())
		        .isAdmin(false)
		        .isDoctor(true)
		        .isPatient(false)
		        .build();
		return signInResponseDTO;
	}
	
	public static SignInResponseDTO buildSigninResponseFromDoctor(Doctor user)
	{
		SignInResponseDTO signInResponseDTO = SignInResponseDTO.builder()
				.id(user.getId())
		        .fName(user.getFName())
		        .lName(user.getLName())
		        .emailAddress(user.getEmailAddress())
		        .password(user.getPassword())
		        .isAdmin(false)
		        .isDoctor(true)
		        .isPatient(false)
		        .build();
		return signInResponseDTO;
	}

}
