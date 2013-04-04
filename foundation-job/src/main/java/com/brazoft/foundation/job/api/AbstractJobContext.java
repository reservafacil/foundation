package com.brazoft.foundation.job.api;

import java.util.Map;

import org.quartz.JobExecutionContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractJobContext
{
	private JobExecutionContext context;
	
	/**
	 * @param context
	 */
	public AbstractJobContext(JobExecutionContext context)
	{
		this.context = context;
	}
	
	/**
	 * @param key 
	 * @return Returns Job parameter from key
	 */
	public Object[] getParameters()
	{
		return (Object[]) this.getMap().get(IJob.JOB_PARAMETER_KEY);
	}
	
	/**
	 * @param index
	 * @return Returns Object
	 */
	public Object getParameter(int index)
	{
		return this.getParameters()[index];
	}
	
	/**
	 * @return Returns job class name
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends IJob> getJobClass()
	{
		return (Class<? extends IJob>) this.getMap().get(IJob.JOB_CLASS_KEY);
	}
	
	/**
	 * @return Returns scheduler
	 */
	public AbstractScheduler getScheduler()
	{
		return (AbstractScheduler) this.getMap().get(IJob.JOB_SCHEDULER_KEY);
	}
	
	/**
	 * 
	 */
	public void dispose()
	{
		this.context = null;
	}
	
	private Map<?, ?> getMap()
	{
		return this.context.getJobDetail().getJobDataMap();	
	}
}