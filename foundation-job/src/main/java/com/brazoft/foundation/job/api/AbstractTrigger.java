package com.brazoft.foundation.job.api;

import java.util.Calendar;

import org.quartz.Trigger;

import com.brazoft.foundation.job.JobRegistry;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractTrigger
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private JobRegistry			registry;

	private Calendar			schedule;

	private String				groupId;
	
	/**
	 * @param schedule
	 * @param registry
	 */
	public AbstractTrigger(Calendar schedule, JobRegistry registry)
	{
		this(schedule, registry, IJob.DEFAULT);
	}

	/**
	 * @param schedule
	 * @param registry
	 * @param groupId 
	 */
	public AbstractTrigger(Calendar schedule, JobRegistry registry, String groupId)
	{
		this.schedule = schedule;
		this.registry = registry;
		this.groupId = groupId;
	}

	/**
	 * @return Returns trigger id
	 */
	public String getId()
	{
		return this.getClass().getName();
	}
	
	/**
	 * @return Returns group id
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * @return Returns JobRegistry
	 */
	public JobRegistry getJobRegistry()
	{
		return registry;
	}

	/**
	 * @return Returns Calendar
	 */
	protected Calendar getSchedule()
	{
		return schedule;
	}

	/**
	 * @return Returns Trigger
	 * @throws Exception
	 */
	public abstract Trigger toTrigger() throws Exception;
}