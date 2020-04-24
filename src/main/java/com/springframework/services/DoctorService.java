package com.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Appointment;
import com.springframework.domain.Doctor;
import com.springframework.domain.User;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.DoctorReqDTO;
import com.springframework.dto.UserReqDTO;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.repositories.AppointmentRepository;
import com.springframework.repositories.DoctorRepository;
import com.springframework.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getDoctors(String name) 
	{      
        return doctorRepository.findByFNameContainingIgnoreCase(name);
	}
	
	
	public Doctor saveDoctor(DoctorReqDTO doctorReqDTO)
	{
		// TODO Auto-generated method stub
		Doctor doctor = Doctor.builder()
	                .fName(doctorReqDTO.getFName())
	                .fatherName(doctorReqDTO.getFatherName())
	                .doctorIdNumber(doctorReqDTO.getDoctorIdNumber())
	                .lName(doctorReqDTO.getLName())
	                .build();
        return doctorRepository.save(doctor);
	}
}
