package com.brazoft.foundation.job.api;

import java.util.Calendar;

import org.quartz.CronTrigger;
import org.quartz.Trigger;

import com.brazoft.foundation.job.JobRegistry;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractCronTrigger
    extends AbstractTrigger {

    /**
     * @param schedule
     * @param registry
     */
    public AbstractCronTrigger(Calendar schedule, JobRegistry registry) {
	super(schedule, registry);
    }

    @Override
    public Trigger toTrigger()
	throws Exception {
	CronTrigger cron;

	cron = new CronTrigger();
	cron.setName(this.getClass().getName());
	cron.setCronExpression(this.getExpression());

	return cron;
    }

    /**
     * @return Returns cron expression
     */
    protected String getExpression() {
	StringBuffer expression;

	expression = new StringBuffer();

	expression.append(super.getSchedule().get(Calendar.SECOND)).append(" ");
	expression.append(super.getSchedule().get(Calendar.MINUTE)).append(" ");
	expression.append(super.getSchedule().get(Calendar.HOUR_OF_DAY)).append(" ");
	expression.append(this.getExpressionString());

	return expression.toString();
    }

    /**
     * @return Returns expression string
     */
    protected abstract String getExpressionString();
}