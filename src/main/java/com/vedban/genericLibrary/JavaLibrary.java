package com.vedban.genericLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaLibrary {
	public long stringToLong(String timeout)
	{
		return Long.parseLong(timeout);
	}
	public String dateFormat()
	{
		return new SimpleDateFormat("dd-MM-YY-hh-mm-ss").format(new Date());
	}
}
