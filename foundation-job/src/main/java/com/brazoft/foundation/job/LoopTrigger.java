package com.brazoft.foundation.job;

import java.util.Calendar;

import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.brazoft.foundation.job.api.AbstractTrigger;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class LoopTrigger
    extends AbstractTrigger {

    private long loopInterval;

    /**
     * @param schedule
     * @param registry
     * @param loopInterval
     */
    public LoopTrigger(Calendar schedule, JobRegistry registry, long loopInterval) {
	super(schedule, registry);
	this.loopInterval = loopInterval;
    }

    @Override
    public Trigger toTrigger()
	throws Exception {
	return new SimpleTrigger(super.getJobRegistry().getId(), super.getJobRegistry().getGroupId(), super.getSchedule().getTime(), null,
	                         SimpleTrigger.REPEAT_INDEFINITELY, this.loopInterval);
    }
}