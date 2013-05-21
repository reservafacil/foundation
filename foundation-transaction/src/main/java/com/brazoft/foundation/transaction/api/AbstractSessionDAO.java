package com.brazoft.foundation.transaction.api;

import com.brazoft.foundation.jpa.api.AbstractDAO;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 * @param <T>
 */
public abstract class AbstractSessionDAO<T>
    extends AbstractDAO<T> {

    private AbstractSession session;

    /**
     * @return the session
     */
    public AbstractSession getSession() {
	return session;
    }

    /**
     * @param session
     *            the session to set
     */
    public void setSession(AbstractSession session) {
	this.session = session;
    }
}