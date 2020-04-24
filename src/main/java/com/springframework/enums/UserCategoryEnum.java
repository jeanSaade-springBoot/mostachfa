package com.springframework.enums;

import lombok.Getter;

@Getter
public enum UserCategoryEnum {

	    DOCTOR(1),
	    PATIENT(2),
	    ADMIN(3);
	private int code;
	UserCategoryEnum(int code){this.code = code;}
}
