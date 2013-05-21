package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class WeeklyTrigger
    extends AbstractCronTrigger {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private EDays[]           days;

    /**
     * @param schedule
     * @param registry
     * @param days
     */
    public WeeklyTrigger(Calendar schedule, JobRegistry registry, EDays[] days) {
	super(schedule, registry);
    }

    @Override
    protected String getExpressionString() {
	StringBuffer buffer;
	StringBuffer expression;

	buffer = new StringBuffer();
	expression = new StringBuffer("? * ");

	for (EDays day : this.days) {
	    buffer.append(day.intValue()).append(",");
	}

	buffer.delete(buffer.lastIndexOf(","), buffer.length());
	expression.append(buffer);

	return expression.toString();
    }
}