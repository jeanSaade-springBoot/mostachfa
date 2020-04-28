package com.springframework.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Override
	Optional<Doctor> findById(Long id);

	@Query(value = "select d from Doctor d where d.fName like %?1%"
			+ " or d.lName like %?1%")
    List<Doctor> findByFNameOrLNameContainingIgnoreCase(String name);
	
	Doctor findByPasswordAndEmailAddress(String password, String emailAddress);
	
	Optional<Doctor> findByEmailAddress(String deptName);

}
