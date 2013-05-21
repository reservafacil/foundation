package com.brazoft.foundation.aop.api;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public abstract class AbstractAOPModule
    extends AbstractModule {

    /**
     * @param annotation
     * @param interceptors
     */
    public void bindInterceptor(Annotation annotation, MethodInterceptor... interceptors) {
	super.bindInterceptor(Matchers.any(), Matchers.annotatedWith(annotation), interceptors);
    }

    /**
     * @param annotation
     * @param interceptors
     */
    public void bindInterceptor(Class<? extends Annotation> annotation, MethodInterceptor... interceptors) {
	super.bindInterceptor(Matchers.any(), Matchers.annotatedWith(annotation), interceptors);
    }
}