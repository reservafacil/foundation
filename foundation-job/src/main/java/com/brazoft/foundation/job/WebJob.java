package com.brazoft.foundation.job;

import javax.servlet.ServletContext;

import org.quartz.JobExecutionContext;

import com.brazoft.foundation.job.api.AbstractDefaultJob;
import com.brazoft.foundation.job.api.AbstractJobContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class WebJob
    extends AbstractDefaultJob {

    /**
	 * 
	 */
    public static final String SERVLET_CONTEXT_KEY = ServletContext.class.getName();

    @Override
    protected AbstractJobContext getContext(JobExecutionContext jec) {
	return new WebServiceJobContext(jec, this.getServletContext(jec));
    }

    private ServletContext getServletContext(JobExecutionContext jec) {
	return (ServletContext)jec.getJobDetail().getJobDataMap().get(WebJob.SERVLET_CONTEXT_KEY);
    }
}