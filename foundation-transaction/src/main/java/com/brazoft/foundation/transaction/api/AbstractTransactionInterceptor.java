package com.brazoft.foundation.transaction.api;

import org.aopalliance.intercept.MethodInvocation;

import com.brazoft.foundation.transaction.api.AbstractTransactionManager;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public abstract class AbstractTransactionInterceptor
    extends AbstractServiceInterceptor {

    /**
     * @param invocation
     */
    protected void doCheck(MethodInvocation invocation) {
	super.open(super.getSession(invocation));
    }

    /**
     * @param invocation
     * @return Returns TransactionManager
     */
    protected AbstractTransactionManager getTransaction(MethodInvocation invocation) {
	AbstractSession session;
	AbstractTransactionManager manager;

	session = super.getSession(invocation);

	manager = session.getTransactionManager();

	if (manager == null || manager.getTransaction() == null) {
	    session.openTransaction();
	    manager = session.getTransactionManager();
	}

	return manager;
    }
}