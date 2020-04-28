package com.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Appointment;
import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.DoctorReqDTO;
import com.springframework.dto.PatientReqDTO;
import com.springframework.dto.SignInReqDTO;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.repositories.AppointmentRepository;
import com.springframework.repositories.DoctorRepository;
import com.springframework.repositories.PatientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getDoctorsByFNameOrLNameContainingIgnoreCase(String name) 
	{      
        return doctorRepository.findByFNameOrLNameContainingIgnoreCase(name);
	}
	
	public List<Doctor> getAllDoctors() 
	{      
        return doctorRepository.findAll();
	}
	
	public Doctor saveDoctor(DoctorReqDTO doctorReqDTO)
	{
		// TODO Auto-generated method stub
		Doctor doctor = Doctor.builder()
	                .fName(doctorReqDTO.getFirstName())
	                .fatherName(doctorReqDTO.getFatherName())
	                .doctorIdNumber(doctorReqDTO.getDoctorIdNumber())
	                .lName(doctorReqDTO.getLastName())
	                .emailAddress(doctorReqDTO.getEmailAddress())
	                .password(doctorReqDTO.getPassword())
	                .build();
        return doctorRepository.save(doctor);
	}
	
	public Optional<Doctor> findDoctorByEmail(String emailAddress) 
	{
		// TODO Auto-generated method stub 
		return doctorRepository.findByEmailAddress(emailAddress);
	}
	
	public Doctor signIn(SignInReqDTO signInReqDTO) 
	{
		// TODO Auto-generated method stub 
		Doctor doctor = doctorRepository.findByPasswordAndEmailAddress(signInReqDTO.getPassword(), signInReqDTO.getEmailAddress());
		return doctor;
	}
}
