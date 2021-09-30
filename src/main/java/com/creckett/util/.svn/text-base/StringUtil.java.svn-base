/**
 * Copyright InnoTech Solutions Pvt. Ltd 2011. All rights reserved. No part of this program may be photocopied 
 * reproduced or translated to another program language without prior written consent of InnoTech Solutions Pvt. Ltd
 *
 * FILE         : StringUtil.java
 *
 * PACKAGE      : com.creckett.util
 *
 * AUTHOR(S)    : Dhaval P Shah - shah_d_p@yahoo.com
 *
 * VERSION      : 1.0
 *
 * REVISION LOG :
 *
 *    Date          By         Version  TicketId        Description
 *    ------------  ---------  -------  --------  --------------------------------------------------
 *    May 5, 2011	    Dhaval	   1.0      NA        Created first cut of code.
 *
 */
package com.creckett.util;

import java.util.UUID;

public class StringUtil {

    public StringUtil() {
	super();
    }

    public String getUUID() {
	return UUID.randomUUID().toString();
    }

    public static int getIntFromString(String value)
	    throws NumberFormatException {
	int toReturn = -1;
	toReturn = Integer.valueOf(value);
	return toReturn;
    }

    public static long getLongFromString(String value)
	    throws NumberFormatException {
	long toReturn = -1;
	toReturn = Long.valueOf(value);
	return toReturn;
    }
    
	public static Boolean getBooleanValueFromString(String value,Boolean defaultValue) {
		Boolean toReturn = defaultValue;
		if (value != null) {
			if (value.equalsIgnoreCase("Y") ) {
				toReturn = true;
			} else if (value.equalsIgnoreCase("N")) {
				toReturn = false;
			}
		}
		return toReturn;
	}
	
	public static String getStringValueFromBoolean(Boolean value) {
		String toReturn = null;
		if (value != null) {
			if (value) {
				toReturn = "Y";
			} else  {
				toReturn = "N";
			}
		}
		return toReturn;
	}

}
