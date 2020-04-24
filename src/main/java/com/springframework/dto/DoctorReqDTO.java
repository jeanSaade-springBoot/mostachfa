package com.springframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class DoctorReqDTO {
    private String fName;
	private String fatherName;
    private String lName;
    private String doctorIdNumber;
}
