package com.brazoft.foundation.job;

import java.util.Calendar;

import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.brazoft.foundation.job.api.AbstractTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class NowTrigger extends AbstractTrigger
{
	/**
	 * @param schedule
	 * @param registry
	 */
	public NowTrigger(JobRegistry registry)
	{
		super(Calendar.getInstance(), registry);
	}

	@Override
	public Trigger toTrigger() throws Exception
	{
		return new SimpleTrigger(super.getId(), super.getSchedule().getTime());
	}
}