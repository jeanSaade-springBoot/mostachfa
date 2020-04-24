package com.springframework.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.domain.AppointmentTrack;

/**
 * 
 */
@Repository
public interface AppointmentTrackRepository
		extends
			JpaRepository<AppointmentTrack, Long> {
	@Override
	Optional<AppointmentTrack> findById(Long id);

	@Query(value = "update appointment_track set is_current= :isCurrent"
			+ ",update_date= :d where appointment_id= :appointmentId and is_current = 1", nativeQuery = true)
	AppointmentTrack updateAppointmentTrack(long appointmentId, boolean isCurrent,
			Date d);
}
