package com.brazoft.foundation.soa;

import com.brazoft.foundation.transaction.api.AbstractTransaction;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class JPATransaction
    extends AbstractTransaction {

    private JPASession session;

    /**
     * @param session
     */
    public JPATransaction(JPASession session) {
	this.session = session;
	this.begin();
    }

    /**
     * @param transaction
     */
    public JPATransaction(JPATransaction transaction) {
	this.session = transaction.session;
    }

    public void begin() {
	this.session.getContext().getTransaction().begin();
    }

    public void flush() {
	this.session.getContext().flush();
	this.session.getContext().clear();
    }

    protected void doCommit() {
	this.session.getContext().getTransaction().commit();
    }

    protected void doRelease() {
	this.session.close();
    }

    protected void doRollback() {
	this.session.getContext().getTransaction().rollback();
    }

    public boolean isActive() {
	return this.session.getContext().getTransaction().isActive();
    }
}