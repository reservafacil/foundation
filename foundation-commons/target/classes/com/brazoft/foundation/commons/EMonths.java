package com.brazoft.foundation.commons;

import java.util.Calendar;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public enum EMonths
{
	/**
	 * 
	 */
	JANUARY(Calendar.JANUARY), 
	
	/**
	 * 
	 */
	FEBRUARY(Calendar.FEBRUARY), 
	
	/**
	 * 
	 */
	MARCH(Calendar.MARCH), 
	
	/**
	 * 
	 */
	APRIL(Calendar.APRIL),
	
	/**
	 * 
	 */
	MAY(Calendar.MAY),
	
	/**
	 * 
	 */
	JUNE(Calendar.JUNE),
	
	/**
	 * 
	 */
	JULY(Calendar.JULY),
	
	/**
	 * 
	 */
	AUGUST(Calendar.AUGUST),
	
	/**
	 * 
	 */
	SEPTEMBER(Calendar.SEPTEMBER),
	
	/**
	 * 
	 */
	OCTOBER(Calendar.OCTOBER),
	
	/**
	 * 
	 */
	NOVEMBER(Calendar.NOVEMBER),
	
	/**
	 * 
	 */
	DECEMBER(Calendar.DECEMBER);
	
	private int month;
	
	private EMonths(int month)
	{
		this.month = month;
	}
	
	/**
	 * @return Returns int value for specified month
	 */
	public int intValue()
	{
		return this.month;
	}
	
	/**
	 * @param value
	 * @return Returns EMonths from int
	 */
	public static EMonths valueOf(int value)
	{
		for(EMonths month : EMonths.values())
		{
			if(month.intValue() == value)
			{
				return month;
			}
		}
		
		return null;
	}
}