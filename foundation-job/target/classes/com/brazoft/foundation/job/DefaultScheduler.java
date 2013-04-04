package com.brazoft.foundation.job;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.brazoft.foundation.job.api.AbstractScheduler;
import com.brazoft.foundation.job.api.JobException;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DefaultScheduler extends AbstractScheduler
{
	private Scheduler scheduler;
		
	/**
	 * @throws JobException 
	 * 
	 */
	public void startup() throws JobException
	{
		try
		{
			this.getScheduler().start();
		}
		catch (SchedulerException e)
		{
			super.handle(e);
		}
	}
	
	/**
	 * @throws JobException 
	 */
	public void shutdown() throws JobException
	{
		try
		{
			this.getScheduler().shutdown();
		}
		catch (SchedulerException e)
		{
			super.handle(e);
		}
	}
	
	@Override
	protected Class<? extends Job> getJobClass()
	{
		return DefaultJob.class;
	}
	
	@Override
	protected Scheduler getScheduler() throws SchedulerException
	{
		if(this.scheduler == null)
		{
			this.scheduler = new StdSchedulerFactory().getScheduler();
		}
		
		return this.scheduler;
	}
}