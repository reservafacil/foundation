package com.brazoft.foundation.transaction.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractService {

    private AbstractSession session;

    /**
     * @param session
     */
    protected void setSession(AbstractSession session) {
	this.session = session;
    }

    /**
     * @return Returns AbstractSession
     */
    protected AbstractSession getSession() {
	if (this.session == null) {
	    this.session = this.createSession();
	}

	return session;
    }

    /**
     * @param <T>
     * @param clazz
     * @return Returns an instance of clazz
     */
    protected <T> T getDAO(Class<T> clazz) {
	return this.session.getDAO(clazz);
    }

    /**
     * @return Returns session
     */
    protected abstract AbstractSession createSession();

    /**
     * @return Returns AbstractServiceLocator
     */
    protected abstract AbstractServiceLocator getLocator();
}