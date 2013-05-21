package com.brazoft.foundation.job;

import org.quartz.JobExecutionContext;

import com.brazoft.foundation.job.api.AbstractDefaultJob;
import com.brazoft.foundation.job.api.AbstractJobContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class DefaultJob
    extends AbstractDefaultJob {

    @Override
    protected AbstractJobContext getContext(JobExecutionContext jec) {
	return new JobContext(jec);
    }
}