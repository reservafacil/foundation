package com.brazoft.foundation.jpa.api;

import java.util.List;

import org.hibernate.Session;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractQueryStrategy<T> {

    private Session        context;

    private AbstractDAO<T> dao;

    /**
     * @param context
     */
    public void setContext(Session context) {
	this.context = context;
    }

    /**
     * @param dao
     */
    public void setDAO(AbstractDAO<T> dao) {
	this.dao = dao;
    }

    /**
     * @return Returns Session
     */
    protected Session getContext() {
	return context;
    }

    /**
     * @return AbstractDAO<T>
     */
    protected AbstractDAO<T> getDAO() {
	return this.dao;
    }

    public abstract T doLoad(T search);

    /**
     * @param search
     * @return Returns List<T>
     */
    public abstract List<T> doFind(T search);

    /**
     * @param search
     * @param orderBy
     * @return Returns List<T>
     */
    public abstract List<T> doFindAsc(T search, String... orderBy);

    /**
     * @param search
     * @param orderBy
     * @return Returns List<T>
     */
    public abstract List<T> doFindDesc(T search, String... orderBy);
}