package com.springframework.enums;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum 
{
    USER_ALREADY_EXISTS(1);
	
    private int code;

    ResponseCodeEnum(int code) {
        this.code = code;
    }
}


