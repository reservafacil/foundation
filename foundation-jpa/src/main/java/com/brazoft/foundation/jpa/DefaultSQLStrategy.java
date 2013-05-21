package com.brazoft.foundation.jpa;

import java.util.Map;

import org.hibernate.Query;

import com.brazoft.foundation.jpa.api.AbstractSQLStrategy;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class DefaultSQLStrategy<T>
    extends AbstractSQLStrategy<T> {

    private static final String AND   = " AND ";

    private static final String WHERE = " WHERE ";

    /**
     * @param query
     */
    public DefaultSQLStrategy(StringBuffer query) {
	super(query);
    }

    /**
     * This method creates a query based on search object
     * 
     * @param search
     * @return Returns Criteria
     */
    protected Query createQuery(T search) {
	Query query;
	Map<String, Object> fields;

	fields = JPAUtil.getNotNullFields(search);
	this.appendToQuery(fields);

	query = super.getContext().createSQLQuery(super.getQuery().toString());
	this.setData(query, fields);

	return query;
    }

    private void setData(Query query, Map<String, Object> fields) {
	int index = 0;
	for (Object value : fields.entrySet()) {
	    query.setParameter(index++, value);
	}
    }

    /**
     * @param sql
     * @param search
     */
    private void appendToQuery(Map<String, Object> fields) {
	int start;
	int end;

	for (String fieldName : fields.keySet()) {
	    super.getQuery().append(" AND ");
	    super.getQuery().append(fieldName);
	    super.getQuery().append(this.getRestriction(fields.get(fieldName)));
	}

	start = super.getQuery().indexOf(DefaultSQLStrategy.AND);
	end = start + DefaultSQLStrategy.AND.length();

	super.getQuery().replace(start, end, DefaultSQLStrategy.WHERE);
    }

    private String getRestriction(Object value) {
	String s;

	if (value instanceof String) {
	    s = (String)value;

	    if (s.indexOf("%") > -1) {
		return " LIKE ?";
	    }
	}

	return " = ?";
    }
}
