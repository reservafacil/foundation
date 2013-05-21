package com.brazoft.foundation.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brazoft.foundation.security.api.ILDAPClasses;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class LDAPObject {

    private String                    dn;

    private Map<String, List<String>> attributes;

    /**
	 * 
	 */
    public LDAPObject() {
	super();
    }

    /**
     * @param dn
     */
    public LDAPObject(String dn) {
	this.dn = dn;
    }

    /**
     * @return Returns the attributes.
     */
    public Map<String, List<String>> getAttributes() {
	if (this.attributes == null) {
	    this.attributes = new HashMap<String, List<String>>();
	}

	return attributes;
    }

    /**
     * @param key
     * @param value
     */
    public void put(String key, String value) {
	key = key.toUpperCase();

	if (!this.getAttributes().containsKey(key)) {
	    this.getAttributes().put(key, new ArrayList<String>());
	}

	this.getAttributes().get(key).add(value);
    }

    /**
     * @param key
     * @return Returns Object
     */
    public Object get(String key) {
	if (!this.getAttributes().containsKey(key)) {
	    return null;
	}

	return this.getAttributes().get(key).get(0);
    }

    /**
     * @return Returns name
     */
    public String getName() {
	for (String key : ILDAPClasses.NAMING_ATTRIBUTES) {
	    if (this.getAttributes().containsKey(key)) {
		return (String)this.getAttributes().get(key).get(0);
	    }
	}

	return this.getDn();
    }

    /**
     * @return Returns the dn.
     */
    public String getDn() {
	return dn;
    }

    /**
     * @param dn
     *            The dn to set.
     */
    public void setDn(String dn) {
	this.dn = dn;
    }
}
