package com.springframework.enums;

public enum RedirectPagesEnum 
{
	    LOGIN("html/login"),
	    APPOINTMENT_MAIN_LIST("html/appointmentMainList"),
	    MAIN_DETAILS("html/mainDetails");
	
	public final String label;
	 
    private RedirectPagesEnum(String label) {
        this.label = label;
    }
}
