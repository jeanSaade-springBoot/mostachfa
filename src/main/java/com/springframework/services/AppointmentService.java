package com.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Appointment;
import com.springframework.domain.User;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.UserReqDTO;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.repositories.AppointmentRepository;
import com.springframework.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	public Optional<Appointment> getAppointmentById(Long id) 
	{      
        return appointmentRepository.findById(id);
	}
	
	public Appointment saveAppointment(AppointmentReqDTO appointmentReqDTO) 
	{
		// TODO Auto-generated method stub
		Appointment appointment = Appointment.builder()
	                .description(appointmentReqDTO.getDescription())
	                .patientId(appointmentReqDTO.getPatientId())
	                .status(AppointmentStatusEnum.PENDING)
	                .build();
        return appointmentRepository.save(appointment);
	}
	

}
