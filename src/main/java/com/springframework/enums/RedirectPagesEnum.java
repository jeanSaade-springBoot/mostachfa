package com.springframework.enums;

public enum RedirectPagesEnum 
{
	    LOGIN("/html/kkeshLogin"),
	    REGISTER("/html/kkeshLogin"),
	    CHATTING("/html/CHATTING"),
	    APPOINTMENTDEATIL("/html/appointmentDetail");
	
	public final String label;
	 
    private RedirectPagesEnum(String label) {
        this.label = label;
    }
}
