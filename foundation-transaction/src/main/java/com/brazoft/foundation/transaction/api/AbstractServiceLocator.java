package com.brazoft.foundation.transaction.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractServiceLocator {

    private AbstractSession session;

    /**
	 * 
	 */
    public AbstractServiceLocator() {
	super();
    }

    /**
     * @param locator
     */
    public AbstractServiceLocator(AbstractServiceLocator locator) {
	this();
	this.session = locator.session;
    }

    /**
     * @param session
     */
    public AbstractServiceLocator(AbstractSession session) {
	this();
	this.session = session;
    }

    /**
     * @param <T>
     * @param clazz
     * @return Returns an instance of clazz
     */
    public <T> T getService(Class<T> clazz) {
	return this.session.getService(clazz);
    }

    /**
     * @return Returns AbstractSession
     */
    protected AbstractSession getSession() {
	return this.session;
    }

    /**
     * @param session
     */
    public void setSession(AbstractSession session) {
	this.session = session;
    }
}