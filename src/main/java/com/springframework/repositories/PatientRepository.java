package com.springframework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Override
	Optional<Patient> findById(Long id);
	
//	@Query(value = "select * from user where email_address= :emailAddress and"
//			+ " password= :password" ,nativeQuery = true)
	Patient findByPasswordAndEmailAddress(String password, String emailAddress);
	
	Optional<Patient> findByEmailAddress(String deptName);
}
