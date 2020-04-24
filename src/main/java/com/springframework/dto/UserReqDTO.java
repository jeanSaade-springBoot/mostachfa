package com.springframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class UserReqDTO {
	public String fName;
	public String lName;
	public String userCode;
	public String emailAddress;
	public String fileNbr;
	public String telNumber;
	public String password;
	public String nationalNbr;
	public String age;
}
