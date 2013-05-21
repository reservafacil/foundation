package com.brazoft.foundation.transaction;

import org.aopalliance.intercept.MethodInvocation;

import com.brazoft.foundation.transaction.api.AbstractTransactionInterceptor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class TransactionInterceptor
    extends AbstractTransactionInterceptor {

    @Override
    protected void doBeforeInvoke(MethodInvocation invocation) {
	super.doCheck(invocation);
	super.getTransaction(invocation).add(new TransactionState());
    }

    @Override
    protected void doAfterInvoke(MethodInvocation invocation) {
	super.getTransaction(invocation).commit();
    }

    @Override
    protected void doOnExceptionInvoke(MethodInvocation invocation, Throwable e) {
	super.getTransaction(invocation).rollback();
    }

    @Override
    protected void doOnFinallyInvoke(MethodInvocation invocation) {
	super.getTransaction(invocation).release();
    }
}