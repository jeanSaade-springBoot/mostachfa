package com.springframework.dto;

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
public class AppointmentReqDTO {
	
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime assignedDate;
    private LocalDateTime closedDate;
    private AppointmentStatusEnum status;
    private String doctorId;
    private String patientId;
}
