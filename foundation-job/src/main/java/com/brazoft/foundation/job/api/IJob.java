package com.brazoft.foundation.job.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public interface IJob {

    /**
	 * 
	 */
    public static final String JOB_CLASS_KEY     = IJob.class.getName();

    /**
	 * 
	 */
    public static final String JOB_PARAMETER_KEY = IJob.JOB_CLASS_KEY + ".parameter";

    /**
	 * 
	 */
    public static final String JOB_SCHEDULER_KEY = IJob.JOB_CLASS_KEY + ".scheduler";

    /**
	 * 
	 */
    public static final String DEFAULT           = "DEFAULT";

    /**
     * @param context
     * @throws JobException
     */
    public abstract void execute(AbstractJobContext context)
	throws JobException;
}