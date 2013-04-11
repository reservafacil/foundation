package com.brazoft.foundation.transaction.api;

import org.aopalliance.intercept.MethodInvocation;

import com.brazoft.foundation.aop.api.AbstractMethodInterceptor;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 *
 */
public abstract class AbstractServiceInterceptor extends AbstractMethodInterceptor
{
	/**
	 * @param session
	 */
	protected void open(AbstractSession session)
	{
		if(!session.isOpened())
		{
			session.open();
		}
	}
	
	/**
	 * @param invocation
	 * @return Returns AbstractSession
	 */
	protected AbstractSession getSession(MethodInvocation invocation)
	{
		AbstractService service;
		
		service = (AbstractService) invocation.getThis();

		return service.getSession();
	}
}