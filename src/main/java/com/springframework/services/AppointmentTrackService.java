package com.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.domain.AppointmentTrack;
import com.springframework.dto.AppointmentTrackReqDTO;
import com.springframework.exceptions.SystemException;
import com.springframework.repositories.AppointmentTrackRepository;
import com.springframework.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentTrackService {

	@Autowired
	AppointmentTrackRepository appointmentTrackRepository;

	public Optional<AppointmentTrack> getAppointmentDetailsById(Long id)
			throws SystemException {
		AppointmentTrackService.log.debug("Finding Appointment Details");
		return appointmentTrackRepository.findById(id);
	}

	public AppointmentTrack saveAppointmentTrack(
			AppointmentTrackReqDTO appointmentTrackReqDTO)
			throws SystemException {
		AppointmentTrackService.log.debug("Saving Appointment Details");
		AppointmentTrack appointmentDetails = AppointmentTrack.builder()
				.appointmentId(appointmentTrackReqDTO.getAppointmentId())
				.doctorId(appointmentTrackReqDTO.getDoctorId())
				.assignedBy(appointmentTrackReqDTO.getAssignedBy())
				.isCurrent(true).memo(appointmentTrackReqDTO.getMemo())
				.build();
		return appointmentTrackRepository.save(appointmentDetails);
	}

	public AppointmentTrack closeAppointmentTrack(
			AppointmentTrackReqDTO appointmentTrackReqDTO)
			throws SystemException {

		AppointmentTrackService.log.debug("Closing old Appointment Details [ "
				+ appointmentTrackReqDTO.getId() + " ]");
		
		if (appointmentTrackReqDTO.getId() == null) {
			return null;
		}
		return appointmentTrackRepository.updateAppointmentTrack(
				appointmentTrackReqDTO.getId(),
				appointmentTrackReqDTO.getIsCurrent(),
				DateUtils.getCurrentDateWithTime());
	}

}
