package com.springframework.enums;

public enum RedirectPagesEnum 
{
	    LOGIN(1),
	    REGISTER(2),
	    CHATTING(3),
	    APPOINTMENT(4);
	
	private String url;
	
	RedirectPagesEnum(int code)
	{
		switch(code)
		{
		case 1:  url = "/html/kkeshLogin";
		default: url = "pageNotFound";
		}
		
    }
}
