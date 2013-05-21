package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class OnceTrigger
    extends AbstractCronTrigger {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * @param schedule
     * @param registry
     */
    public OnceTrigger(Calendar schedule, JobRegistry registry) {
	super(schedule, registry);
    }

    @Override
    protected String getExpressionString() {
	StringBuffer expression;

	expression = new StringBuffer();
	expression.append(super.getSchedule().get(Calendar.DATE)).append(" ");
	expression.append(super.getSchedule().get(Calendar.MONTH) + 1).append(" ");
	expression.append("? ");
	expression.append(super.getSchedule().get(Calendar.YEAR));

	return expression.toString();
    }
}