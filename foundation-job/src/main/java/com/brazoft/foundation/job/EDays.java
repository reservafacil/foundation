package com.brazoft.foundation.job;

import java.util.Calendar;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public enum EDays {
    /**
	 * 
	 */
    MONDAY(Calendar.MONDAY),

    /**
	 * 
	 */
    TUESDAY(Calendar.TUESDAY),

    /**
	 * 
	 */
    WEDNESDAY(Calendar.WEDNESDAY),

    /**
	 * 
	 */
    THURSDAY(Calendar.THURSDAY),

    /**
	 * 
	 */
    FRIDAY(Calendar.FRIDAY),

    /**
	 * 
	 */
    SATURDAY(Calendar.SATURDAY),

    /**
	 * 
	 */
    SUNDAY(Calendar.SUNDAY);

    private int day;

    private EDays(int day) {
	this.day = day;
    }

    /**
     * @return Returns int for specified day
     */
    public int intValue() {
	return this.day;
    }

    /**
     * @param value
     * @return Returns EDays
     */
    public static EDays valueOf(int value) {
	for (EDays day : EDays.values()) {
	    if (day.intValue() == value) {
		return day;
	    }
	}

	return null;
    }
}