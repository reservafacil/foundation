package com.brazoft.foundation.job.api;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.brazoft.foundation.job.JobRegistry;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractScheduler {

    /**
     * @param registry
     * @param parameters
     * @throws JobException
     */
    public void run(JobRegistry registry, Object... parameters)
	throws JobException {
	try {
	    this.getScheduler().addJob(this.toJobDetail(registry, parameters), true);
	    this.getScheduler().triggerJob(registry.getId(), registry.getGroupId());
	} catch (SchedulerException e) {
	    this.handle(e);
	}
    }

    /**
     * @param trigger
     * @param parameters
     * @throws JobException
     */
    public void schedule(AbstractTrigger trigger, Object... parameters)
	throws JobException {
	try {
	    this.getScheduler().scheduleJob(this.toJobDetail(trigger.getJobRegistry(), parameters), trigger.toTrigger());
	} catch (Exception e) {
	    this.handle(e);
	}
    }

    /**
     * @param trigger
     * @throws JobException
     */
    public void unschedule(AbstractTrigger trigger)
	throws JobException {
	try {
	    this.getScheduler().deleteJob(trigger.getJobRegistry().getId(), trigger.getJobRegistry().getGroupId());
	} catch (SchedulerException e) {
	    this.handle(e);
	}
    }

    /**
     * @param e
     * @throws JobException
     */
    protected void handle(Exception e)
	throws JobException {
	e.printStackTrace();
	throw new JobException(e);
    }

    /**
     * @param map
     */
    protected void append(JobDataMap map) {
	return;
    }

    private JobDetail toJobDetail(JobRegistry registry, Object... parameters) {
	JobDetail detail;
	JobDataMap map;

	detail = new JobDetail();
	detail.setGroup(registry.getGroupId());
	detail.setName(registry.getId());
	detail.setJobClass(this.getJobClass());

	map = detail.getJobDataMap();
	map.put(IJob.JOB_SCHEDULER_KEY, this);
	map.put(IJob.JOB_CLASS_KEY, registry.getJobClass());
	map.put(IJob.JOB_PARAMETER_KEY, parameters);

	this.append(map);

	return detail;
    }

    /**
     * @return Returns Class of type Job
     */
    protected abstract Class<? extends Job> getJobClass();

    /**
     * @return Returns Scheduler
     * @throws SchedulerException
     */
    protected abstract Scheduler getScheduler()
	throws SchedulerException;
}