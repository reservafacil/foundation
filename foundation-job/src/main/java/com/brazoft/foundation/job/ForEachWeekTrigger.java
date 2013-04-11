package com.brazoft.foundation.job;

import java.text.ParseException;
import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ForEachWeekTrigger extends AbstractCronTrigger
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int weeks;

	/**
	 * @param schedule
	 * @param registry 
	 * @param weeks 
	 */
	public ForEachWeekTrigger(Calendar schedule, JobRegistry registry, int weeks)
	{
		super(schedule, registry);
		this.weeks = weeks;
	}
	
	@Override
	protected String getExpressionString()
	{
		StringBuffer expression;
		
		expression = new StringBuffer(Calendar.getInstance().get(Calendar.DATE));
		expression.append("/");
		expression.append(this.weeks * 7);
		expression.append(" * ? *");
		
		return expression.toString();
	}

	/**
	 * @param weeks
	 * @throws ParseException
	 */
	public void setForEach() throws ParseException
	{
		
	}
}