package com.springframework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Override
	Optional<User> findById(Long id);
	
//	@Query(value = "select * from user where email_address= :emailAddress and"
//			+ " password= :password" ,nativeQuery = true)
	User findByPasswordAndEmailAddress(String password, String emailAddress);
}
