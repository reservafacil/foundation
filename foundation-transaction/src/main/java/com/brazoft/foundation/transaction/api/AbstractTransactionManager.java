package com.brazoft.foundation.transaction.api;

import java.util.ArrayList;
import java.util.List;

import com.brazoft.foundation.transaction.TransactionState;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public abstract class AbstractTransactionManager {

    private ITransaction           transaction;

    private List<TransactionState> stateList;

    /**
	 * 
	 */
    public AbstractTransactionManager() {
	this.stateList = new ArrayList<TransactionState>();
    }

    /**
     * @return the transaction
     */
    public ITransaction getTransaction() {
	return this.transaction;
    }

    /**
     * @param transaction
     *            the transaction to set
     */
    public void setTransaction(ITransaction transaction) {
	this.transaction = transaction;
    }

    /**
     * @param state
     */
    public void add(TransactionState state) {
	this.stateList.add(state);
    }

    /**
	 * 
	 */
    public void release() {
	if (this.evaluate(TransactionState.State.UNKNOW)) {
	    this.transaction.flush();
	    return;
	}

	if (this.evaluate(TransactionState.State.ERROR)) {
	    this.transaction.rollback();
	} else {
	    this.transaction.flush();
	    this.transaction.commit();
	}

	this.dispose();
    }

    private void dispose() {
	this.transaction.release();
	this.transaction = null;
    }

    /**
	 * 
	 */
    public void commit() {
	this.setState(TransactionState.State.VALID);
    }

    /**
	 * 
	 */
    public void rollback() {
	this.setState(TransactionState.State.ERROR);
    }

    private boolean evaluate(TransactionState.State e) {
	for (TransactionState state : this.stateList) {
	    if (state.getState().equals(e)) {
		return true;
	    }
	}

	return false;
    }

    private void setState(TransactionState.State e) {
	TransactionState state;

	for (int i = this.stateList.size() - 1; i >= 0; i--) {
	    state = this.stateList.get(i);
	    if (state.getState().equals(TransactionState.State.UNKNOW)) {
		state.setState(e);
		return;
	    }
	}

	// No caso de chamar o commit e depois rollback
	this.stateList.get(0).setState(e);
    }
}
