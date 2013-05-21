package com.brazoft.foundation.jpa.api;

import org.hibernate.Criteria;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractCriteriaStrategyDecorator
    extends AbstractCriteriaStrategy {

    private AbstractCriteriaStrategy strategy;

    /**
     * @param strategy
     */
    public AbstractCriteriaStrategyDecorator(AbstractCriteriaStrategy strategy) {
	super();
	this.strategy = strategy;
    }

    @Override
    protected void setCriteria(Object search, Criteria query) {
	this.strategy.setCriteria(search, query);
    }
}