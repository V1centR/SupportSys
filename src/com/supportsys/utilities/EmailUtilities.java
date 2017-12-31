package com.supportsys.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities for email functions
 * @author vicentcdb@gmail.com
 *
 */
public class EmailUtilities {

	/**
	 * Check if email is valid
	 * @return
	 */
	public boolean checkEmail(String email)
	{
		//validate email
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if(matcher.matches())
		{
			return true;

		} else {

			return false;
		}
	}

}
