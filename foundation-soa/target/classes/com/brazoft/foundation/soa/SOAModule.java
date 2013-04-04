package com.brazoft.foundation.soa;

import com.brazoft.foundation.aop.api.AbstractAOPModule;
import com.brazoft.foundation.transaction.TransactionInterceptor;
import com.brazoft.foundation.transaction.api.Service;
import com.brazoft.foundation.transaction.api.Transactional;

public class SOAModule extends AbstractAOPModule
{
	@Override
	protected void configure()
	{
		this.bindInterceptor(Transactional.class, new TransactionInterceptor());
		this.bindInterceptor(Service.class, new TransactionInterceptor());
		this.bind(JsonProvider.class);
	}

}
