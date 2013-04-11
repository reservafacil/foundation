package com.brazoft.foundation.job;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerListener;

import com.brazoft.foundation.job.api.AbstractScheduler;
import com.brazoft.foundation.job.api.JobException;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class WebServiceScheduler extends AbstractScheduler
{
	private static WebServiceScheduler instance;
	
	private Scheduler scheduler;
	
	private ServletContext context;
	
	/**
	 * @return Returns WebScheduler
	 */
	public static WebServiceScheduler getInstance()
	{
		return instance;
	}
	
	/**
	 * @param context
	 * @throws JobException 
	 */
	public WebServiceScheduler(ServletContext context) throws JobException
	{
		WebServiceScheduler.instance = this;
		this.init(context);
	}
	
	private void init(ServletContext context) throws JobException
	{
		try
		{
			this.context = context;
			this.scheduler = ((SchedulerFactory) context.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY)).getScheduler();
		}
		catch (SchedulerException e)
		{
			super.handle(e);
		}
	}
	
	@Override
	protected Class<? extends Job> getJobClass()
	{
		return WebJob.class;
	}
	
	@Override
	protected Scheduler getScheduler() throws SchedulerException
	{
		return this.scheduler;
	}
	
	@Override
	protected void append(JobDataMap map)
	{
		super.append(map);
		map.put(WebJob.SERVLET_CONTEXT_KEY, this.context);
	}
}