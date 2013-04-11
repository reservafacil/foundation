package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class OnceAWeekTrigger extends AbstractCronTrigger
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param schedule
	 * @param registry
	 */
	public OnceAWeekTrigger(Calendar schedule, JobRegistry registry)
	{
		super(schedule, registry);
	}
	
	@Override
	protected String getExpressionString()
	{
		return "* * MON";
	}
}