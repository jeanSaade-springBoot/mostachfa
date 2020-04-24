package com.springframework.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AppointmentTrack")
public class AppointmentTrack {

	@Id
	@GeneratedValue
	private Long id;
	@CreationTimestamp
	private LocalDateTime assignedDate;
	private LocalDateTime updateDate;
	private String assignedBy; // User Code who assigned the appointment
	private String doctorId;
	private String appointmentId;
	private Boolean isCurrent;
	private String memo;
}
