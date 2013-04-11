package com.brazoft.foundation.job;

import org.quartz.JobExecutionContext;

import com.brazoft.foundation.job.api.AbstractJobContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JobContext extends AbstractJobContext
{
	/**
	 * @param context
	 */
	public JobContext(JobExecutionContext context)
	{
		super(context);
	}
}