package com.springframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class AdminReqDTO {
    private String firstName;
	private String fatherName;
    private String lastName;
    String emailAddress;
    String password;
}
