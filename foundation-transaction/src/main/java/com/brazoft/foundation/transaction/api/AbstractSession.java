package com.brazoft.foundation.transaction.api;

import com.brazoft.foundation.aop.AOPInjector;
import com.brazoft.foundation.transaction.TransactionManager;
import com.brazoft.foundation.util.Converter;
import com.brazoft.foundation.util.ExceptionHandler;
import com.brazoft.foundation.util.api.IDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractSession {

    private String                     id;

    private Object                     data;

    private AbstractTransactionManager manager;

    public AbstractSession() {
	this.id = Converter.toString(this.hashCode());
    }

    public AbstractSession(Object data) {
	this();
	this.data = data;
    }

    public String getId() {
	return this.id;
    }

    /**
     * @return Returns Object
     */
    public Object getData() {
	return data;
    }

    public <T> T getService(Class<T> clazz) {
	T service;

	service = AOPInjector.getInstance().newInstanceOf(clazz);
	((AbstractService)service).setSession(this);

	return service;
    }

    public <T> T getDAO(Class<T> clazz) {
	T object;

	try {
	    object = AOPInjector.getInstance().newInstanceOf(clazz);
	    ((AbstractSessionDAO<T>)object).setSession(this);
	    this.setDatasource((IDAO)object);
	    // this.getTransactionManager().getTransaction().setDatasource((IDAO) object);

	    return object;
	} catch (Exception e) {
	    ExceptionHandler.handleRuntime(e);
	    return null;
	}
    }

    public void openTransaction() {
	this.openTransaction(this.getTransactionInstance());
    }

    /**
	 * 
	 */
    public void dispose() {
	this.close();
	this.manager = null;
	this.id = null;
    }

    protected AbstractTransactionManager getTransactionManager() {
	return this.manager;
    }

    private void openTransaction(ITransaction transaction) {
	if (this.manager == null) {
	    // TODO: retirar daqui
	    this.manager = new TransactionManager();
	}

	if (this.manager.getTransaction() != null) {
	    throw new TransactionAlreadyOpenedException();
	}

	this.manager.setTransaction(transaction);
    }

    protected abstract boolean isOpened();

    protected abstract void open();

    protected abstract void close();

    protected abstract void setDatasource(IDAO dao);

    protected abstract ITransaction getTransactionInstance();
}