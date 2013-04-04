package com.brazoft.foundation.job;

import com.brazoft.foundation.job.api.IJob;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JobRegistry
{
	private Class<? extends IJob> clazz;
	
	private String id;
	
	private String groupId;
	
	/**
	 * @param clazz
	 */
	public JobRegistry(Class<? extends IJob> clazz)
	{
		this(clazz, clazz.getName());
	}
	
	/**
	 * @param clazz
	 * @param id
	 */
	public JobRegistry(Class<? extends IJob> clazz, String id)
	{
		this(clazz, id, IJob.DEFAULT);
	}
	
	/**
	 * @param clazz
	 * @param id
	 * @param groupId
	 */
	public JobRegistry(Class<? extends IJob> clazz, String id, String groupId)
	{
		super();
		this.clazz = clazz;
		this.id = id;
		this.groupId = groupId;
	}	

	/**
	 * @return the clazz
	 */
	public Class<? extends IJob> getJobClass()
	{
		return clazz;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId()
	{
		return groupId;
	}
}