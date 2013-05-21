package com.brazoft.foundation.aop;

import com.brazoft.foundation.aop.api.AbstractAOPModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class AOPInjector {

    private static AOPInjector instance = new AOPInjector();

    private Injector           injector;

    /**
     * @return Returns OrqueztraInjector
     */
    public static AOPInjector getInstance() {
	return AOPInjector.instance;
    }

    /**
     * @param module
     */
    public void init(AbstractAOPModule... module) {
	this.injector = Guice.createInjector(module);
    }

    /**
     * @param injector
     */
    public void setInjector(Injector injector) {
	this.injector = injector;
    }

    /**
     * @param <T>
     * @param clazz
     * @return Returns an instance of clazz
     */
    public <T> T newInstanceOf(Class<T> clazz) {
	return this.injector.getInstance(clazz);
    }
}