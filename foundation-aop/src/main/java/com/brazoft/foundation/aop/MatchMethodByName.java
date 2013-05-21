package com.brazoft.foundation.aop;

import java.lang.reflect.Method;

import com.google.inject.matcher.AbstractMatcher;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class MatchMethodByName
    extends AbstractMatcher<Method> {

    private String name;

    /**
     * @param name
     */
    public MatchMethodByName(String name) {
	super();
	this.name = name;
    }

    public boolean matches(Method method) {
	return method.getName().equals(this.name);
    }
}