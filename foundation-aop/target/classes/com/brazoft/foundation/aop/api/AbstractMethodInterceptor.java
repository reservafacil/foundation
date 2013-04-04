package com.brazoft.foundation.aop.api;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractMethodInterceptor implements MethodInterceptor
{
	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		Object object;

		try
		{
			this.doBeforeInvoke(invocation);
			object = invocation.proceed();
			this.doAfterInvoke(invocation);

			return object;
		}
		catch (Throwable e)
		{
			this.doOnExceptionInvoke(invocation, e);
			throw e;
		}
		finally
		{
			this.doOnFinallyInvoke(invocation);
		}
	}
	
	/**
	 * @param invocation
	 * @throws Throwable 
	 */
	protected void doBeforeInvoke(MethodInvocation invocation) throws Throwable
	{
		return;
	}
	
	/**
	 * @param invocation
	 * @throws Throwable 
	 */
	protected void doAfterInvoke(MethodInvocation invocation) throws Throwable
	{
		return;
	}
	
	/**
	 * @param invocation
	 * @param e 
	 * @throws Throwable 
	 */
	protected void doOnExceptionInvoke(MethodInvocation invocation, Throwable e) throws Throwable
	{
		return;
	}
	
	/**
	 * @param invocation
	 * @throws Throwable 
	 */
	protected void doOnFinallyInvoke(MethodInvocation invocation) throws Throwable
	{
		return;
	}
}