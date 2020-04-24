package com.springframework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.springframework.exceptions.SystemException;

public class DateUtils {

	public static Date getCurrentDateWithTime() throws SystemException {

		Date d = Calendar.getInstance().getTime();

		return d;
	}

	public static Date getCurrentDateNoTime(String format)
			throws SystemException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = Calendar.getInstance().getTime();

		return sdf.parse(sdf.format(d));

	}

}
