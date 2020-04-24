package com.springframework.enums;

import lombok.Getter;

@Getter
public enum AppointmentStatusEnum 
{
    PENDING(1),
    UNDERVALIDATION(2),
    CLOSED(3),
    REJECTED(4);
	
    private int code;

    AppointmentStatusEnum(int code) {
        this.code = code;
    }
}


