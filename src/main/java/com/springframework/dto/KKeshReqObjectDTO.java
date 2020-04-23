package com.springframework.dto;

import com.springframework.enums.RedirectPagesEnum;
import com.springframework.enums.UserCategoryEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class KKeshReqObjectDTO {
	
	public String doctorId;
	public String adminId;
	public String patientId;
	public UserCategoryEnum  userCategory;
	public RedirectPagesEnum redirectUrl;
}
