package com.brazoft.foundation.gwt.client.ui;

import java.util.*;

import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Composite;
import com.google.gwt.dom.client.DivElement;

@SuppressWarnings("unchecked")
public class NavigationPanel
    extends Composite<NavigationPanel> {

    private static final NavigationPanel instance  = new NavigationPanel();

    private Map<String, NavigationRule>  rules     = new HashMap<String, NavigationRule>();

    private DispatchEvent                event;

    private Outcome                      currentOutcome;

    private HTML<DivElement>             container = HTML.asDiv();

    public static NavigationPanel get() {
	return NavigationPanel.instance;
    }

    private NavigationPanel() {
	this.setup();
    }

    private void setup() {
	this.initWidget(this.container);
	this.event = new DispatchEvent() {

	    @Override
	    public void onDispatch(Outcome from, Outcome to) {
		if (from != null) {
		    from.get().hidden();
		}

		to.get().visible();
	    }
	};
    }

    public NavigationPanel onDispatch(DispatchEvent event) {
	this.event = event;
	return this;
    }

    public NavigationPanel add(NavigationRule rule) {
	this.rules.put(rule.target().name(), rule);
	return this;
    }
    
    public Outcome dispatch(Target target) {
	Outcome to = this.rules.get(target.name()).run();
	
	if(!to.get().isAttached()){
	    this.container.add(to.get());
	}

	this.event.onDispatch(this.currentOutcome, to);
	return this.currentOutcome = to;
    }

    public static interface NavigationRule {

	Outcome run();

	Target target();
    }

    public static abstract class Outcome {

	protected abstract Composite<?> get();
	
	public <C extends Composite<C>> C cast(){
	    return (C) this.get();
	}
    }

    public static interface Target {

	String name();
    }

    public static interface DispatchEvent {

	public abstract void onDispatch(Outcome from, Outcome to);
    }
}