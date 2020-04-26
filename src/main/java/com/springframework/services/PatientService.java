package com.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Admin;
import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;
import com.springframework.dto.PatientReqDTO;
import com.springframework.dto.SignInReqDTO;
import com.springframework.repositories.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientService {
	
	private static final String USER_DOES_NOT_EXIST           = "user with id %s does not exist.";
	@Autowired
	PatientRepository patientRepository;
	
	public Patient saveUser(PatientReqDTO patientReqDTO) 
	{
		// TODO Auto-generated method stub
		Patient patient = Patient.builder()
	                .fName(patientReqDTO.getFName())
	                .lName(patientReqDTO.getLName())
	                .userCode(patientReqDTO.getUserCode())
	                .age(patientReqDTO.getAge())
	                .telNumber(patientReqDTO.getTelNumber())
	                .fileNbr(patientReqDTO.getFileNbr())
	                .emailAddress(patientReqDTO.getEmailAddress())
	                .password(patientReqDTO.getPassword())
	                .nationalNbr(patientReqDTO.getNationalNbr())
	                .build();
        
        return patientRepository.save(patient);
	}
	
	public Patient signIn(SignInReqDTO signInReqDTO) 
	{
		// TODO Auto-generated method stub 
		Patient patient = patientRepository.findByPasswordAndEmailAddress(signInReqDTO.getPassword(), signInReqDTO.getEmailAddress());
		return patient;
	}
	
	public Optional<Patient> findPatientByEmail(String emailAddress) 
	{
		// TODO Auto-generated method stub 
		return patientRepository.findByEmailAddress(emailAddress);
	}

}
