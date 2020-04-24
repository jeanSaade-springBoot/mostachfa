package com.springframework.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentTrackReqDTO {

	private Long id;
	private LocalDateTime assignedDate;
	private LocalDateTime updateDate;
	private String assignedBy; // User Code who assigned the appointment
	private String doctorId;
	private String appointmentId;
	private Boolean isCurrent;
	private String memo;
}
