package com.springframework.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.springframework.enums.AppointmentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name = "Doctor")
public class Doctor {
	
	@Id
    @GeneratedValue
    private Long id;
    private String fName;
	private String fatherName;
    private String lName;
    private String doctorIdNumber;
    private String emailAddress;
    private String password;
}
