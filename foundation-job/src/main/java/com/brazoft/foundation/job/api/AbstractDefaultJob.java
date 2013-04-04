package com.brazoft.foundation.job.api;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractDefaultJob implements org.quartz.Job
{
	public void execute(JobExecutionContext jec) throws JobExecutionException
	{
		IJob job;
		AbstractJobContext context;
		
		context = this.getContext(jec);
		
		try
		{
			job = (IJob) context.getJobClass().newInstance();
			job.execute(context);
		}
		catch (Exception e)
		{
			throw new JobExecutionException(e);
		}
		finally
		{
			context.dispose();
		}
	}
	
	/**
	 * @param jec
	 * @return Returns AbstractJobContext instance
	 */
	protected abstract AbstractJobContext getContext(JobExecutionContext jec);
}