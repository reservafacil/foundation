package com.brazoft.foundation.soa;

import org.aopalliance.intercept.MethodInvocation;

import com.brazoft.foundation.transaction.api.AbstractServiceInterceptor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ServiceInterceptor extends AbstractServiceInterceptor
{
	@Override
	protected void doBeforeInvoke(MethodInvocation invocation) throws Throwable
	{
		super.open(super.getSession(invocation));
	}
	
	@Override
	protected void doAfterInvoke(MethodInvocation invocation) throws Throwable
	{
		super.getSession(invocation).dispose();
	}
}