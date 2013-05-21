package com.brazoft.foundation.job;

import javax.servlet.ServletContext;

import org.quartz.JobExecutionContext;

import com.brazoft.foundation.job.api.AbstractJobContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class WebServiceJobContext
    extends AbstractJobContext {

    private ServletContext servlet;

    /**
     * @param context
     * @param servlet
     */
    public WebServiceJobContext(JobExecutionContext context, ServletContext servlet) {
	super(context);
	this.servlet = servlet;
    }

    /**
     * @return Returns ServletContext
     */
    public ServletContext getServletContext() {
	return this.servlet;
    }

    @Override
    public void dispose() {
	this.servlet = null;
	super.dispose();
    }
}