package com.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springframework.domain.Appointment;
import com.springframework.domain.Patient;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.PatientReqDTO;
import com.springframework.dto.SignInResponseDTO;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.enums.RedirectPagesEnum;
import com.springframework.repositories.AppointmentRepository;
import com.springframework.repositories.PatientRepository;

import com.springframework.enums.AppointmentStatusEnum;
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
	
	public void updateAppointmentStatus(String appointmentId, String doctorId, int statusId) 
	{
        appointmentRepository.updateAppointmentStatus(appointmentId,doctorId,statusId);
	}
	
	public List<Appointment> getAppointmentAdminList() 
	{
        return appointmentRepository.findAll();
	}
	
	public List<Appointment> getAppointmentDoctorList(long id,AppointmentStatusEnum status) 
	{
        return appointmentRepository.findByDoctorIdAndStatus(String.valueOf(id));
	}
	
	public List<Appointment> getAppointmentPatientList(long id,AppointmentStatusEnum status) 
	{
        return appointmentRepository.findByPatientIdAndStatus(String.valueOf(id));
	}
	
	
	
	

}
