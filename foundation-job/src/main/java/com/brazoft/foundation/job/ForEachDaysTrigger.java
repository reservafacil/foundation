package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ForEachDaysTrigger extends AbstractCronTrigger
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private int days;

	/**
	 * @param schedule
	 * @param registry
	 * @param days 
	 */
	public ForEachDaysTrigger(Calendar schedule, JobRegistry registry, int days)
	{
		super(schedule, registry);
		this.days = days;
	}
	
	@Override
	protected String getExpressionString()
	{
		StringBuffer expression;
		
		
		expression = new StringBuffer(Calendar.getInstance().get(Calendar.DATE));
		expression.append("/");
		expression.append(days);
		expression.append(" * ?");
		
		return expression.toString();
	}
}