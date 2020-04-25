package com.springframework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springframework.domain.Appointment;
import com.springframework.enums.RedirectPagesEnum;

/**
 * Created by jt on 1/10/17.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
	@Override
	Optional<Appointment> findById(Long id);
	
	@Query(value = "update Appointment set status= :status where id =:appointmentId",nativeQuery = true)
	void updateAppointmentStatus(@Param("appointmentId") String appointmentId,@Param("status") String status);
}
