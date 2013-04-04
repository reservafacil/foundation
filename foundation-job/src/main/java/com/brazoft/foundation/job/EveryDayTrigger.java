package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class EveryDayTrigger extends AbstractCronTrigger
{
	/**
	 * @param schedule
	 * @param registry
	 */
	public EveryDayTrigger(Calendar schedule, JobRegistry registry)
	{
		super(schedule, registry);
	}

	@Override
	protected String getExpressionString()
	{
		return "* * ?";
	}
}