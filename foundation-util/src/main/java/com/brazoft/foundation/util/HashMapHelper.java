package com.brazoft.foundation.util;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * @param <K>
 * @param <V>
 */
public class HashMapHelper<K, V>
    extends HashMap<K, V> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * @param key
     * @return Returns Boolean
     */
    public Boolean getBoolean(K key) {
	return Converter.toBoolean(this.get(key));
    }

    /**
     * @param key
     * @return Returns Date
     */
    public Date getDate(K key) {
	return (Date)this.get(key);
    }

    /**
     * @param key
     * @return Returns Double
     */
    public Double getDouble(K key) {
	return Converter.toDouble(this.get(key));
    }

    /**
     * @param key
     * @return Returns Integer
     */
    public Integer getInteger(K key) {
	return Converter.toInteger(this.get(key));
    }

    /**
     * @param key
     * @return Returns Integer
     */
    public int getInt(K key) {
	return Converter.toint(this.get(key));
    }

    /**
     * @param key
     * @return Returns String
     */
    public String getString(K key) {
	String value;

	value = Converter.toString(this.get(key));

	if (value == null) {
	    value = key.toString();
	}

	return value;
    }

    @Override
    public V get(Object key) {
	return super.get(key);
    }
}