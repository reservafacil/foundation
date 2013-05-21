package com.brazoft.foundation.job;

import java.util.Calendar;

import com.brazoft.foundation.job.api.AbstractCronTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class MonthlyTrigger
    extends AbstractCronTrigger {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private int               day;

    private EMonths[]         months;

    /**
     * @param schedule
     * @param registry
     * @param day
     * @param months
     * @throws ParseException
     */
    public MonthlyTrigger(Calendar schedule, JobRegistry registry, int day, EMonths[] months) {
	super(schedule, registry);
	this.day = day;
	this.months = months;
    }

    @Override
    protected String getExpressionString() {
	StringBuffer buffer;
	StringBuffer expression;

	expression = new StringBuffer(day);
	expression.append(" ");

	buffer = new StringBuffer();
	for (EMonths month : months) {
	    buffer.append(month.intValue() + 1);
	    buffer.append(",");
	}

	buffer.delete(buffer.lastIndexOf(","), buffer.length());
	expression.append(buffer);

	expression.append(" ? ");

	return expression.toString();
    }
}