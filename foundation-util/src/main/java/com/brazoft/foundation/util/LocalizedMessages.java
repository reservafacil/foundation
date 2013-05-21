package com.brazoft.foundation.util;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class LocalizedMessages {

    private static Map<String, LocalizedMessages> cache;

    private ResourceBundle                        bundle;

    protected LocalizedMessages(String name, Locale locale) {
	this.bundle = ResourceBundle.getBundle(name, locale);
    }

    public static LocalizedMessages getInstance(String name) {
	return LocalizedMessages.getInstance(name, Locale.getDefault());
    }

    public static LocalizedMessages getInstance(String name, Locale locale) {
	if (LocalizedMessages.cache == null) {
	    LocalizedMessages.cache = new HashMap<String, LocalizedMessages>();
	}

	if (!LocalizedMessages.cache.containsKey(name)) {
	    LocalizedMessages.cache.put(name, new LocalizedMessages(name, Locale.getDefault()));
	}

	return LocalizedMessages.cache.get(name);
    }

    public boolean contains(String key) {
	Enumeration<String> keys;

	keys = this.bundle.getKeys();
	while (keys.hasMoreElements()) {
	    if (keys.nextElement().equals(key)) {
		return true;
	    }
	}

	return false;
    }

    public String getProperty(String key) {
	return this.bundle.getString(key);
    }

    public String getFormatted(String key, Object[] args) {
	return MessageFormat.format(this.getProperty(key), args);
    }

    public Map<Object, Object> getMessageMap() {
	Map<Object, Object> map;
	Enumeration<String> keys;
	String key;

	map = new HashMap<Object, Object>();
	keys = this.bundle.getKeys();

	while (keys.hasMoreElements()) {
	    key = keys.nextElement();
	    map.put(key, this.bundle.getObject(key));
	}

	return map;
    }
}