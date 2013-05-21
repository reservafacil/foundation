package com.brazoft.foundation.soa;

import org.hibernate.Session;

import com.brazoft.foundation.jpa.Activation;
import com.brazoft.foundation.jpa.api.AbstractDAO;
import com.brazoft.foundation.transaction.api.AbstractSession;
import com.brazoft.foundation.transaction.api.ITransaction;
import com.brazoft.foundation.util.api.IDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JPASession
    extends AbstractSession {

    private Session session;

    /**
	 * 
	 */
    public JPASession() {
	super();
    }

    /**
     * @param data
     */
    public JPASession(Object data) {
	super(data);
    }

    @Override
    protected boolean isOpened() {
	return this.session != null;
    }

    @Override
    protected void open() {
	this.session = Activation.getInstance().getSession();
    }

    protected void close() {
	this.session.close();
	this.session = null;
    }

    /**
     * @return Returns Session
     */
    Session getContext() {
	return this.session;
    }

    protected void setDatasource(IDAO dao) {
	((AbstractDAO<?>)dao).setContext(this.session);
    }

    @Override
    protected ITransaction getTransactionInstance() {
	return new JPATransaction(this);
    }
}