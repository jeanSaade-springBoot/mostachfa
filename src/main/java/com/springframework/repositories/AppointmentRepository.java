package com.springframework.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.domain.Appointment;
import com.springframework.domain.Doctor;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.enums.RedirectPagesEnum;

/**
 * Created by js on 2020.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
	@Override
	Optional<Appointment> findById(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update Appointment set status= :statusId ,  doctor_id= :doctorId where id =:appointmentId",nativeQuery = true)
	void updateAppointmentStatus(@Param("appointmentId") String appointmentId,@Param("doctorId") String doctorId,@Param("statusId") int statusId);
	
	@Query(value = "select * from Appointment where patient_id =:patientId",nativeQuery = true)
	List<Appointment> findByPatientIdAndStatus(@Param("patientId") String patientId);
	
	@Query(value = "select * from Appointment where doctor_id =:doctorId",nativeQuery = true)
	List<Appointment> findByDoctorIdAndStatus(@Param("doctorId")String doctorId);
}
