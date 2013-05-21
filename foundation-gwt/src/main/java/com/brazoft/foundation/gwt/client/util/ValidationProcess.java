package com.brazoft.foundation.gwt.client.util;

import java.util.ArrayList;
import java.util.List;

import com.brazoft.foundation.commons.validator.api.Validator;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationResult.FieldState;

public class ValidationProcess {

    private List<ValidationConstraint<?>>  constraints = new ArrayList<ValidationConstraint<?>>();

    private EventHandler<ValidationResult> handler;

    public ValidationProcess onComplete(EventHandler<ValidationResult> handler) {
	this.handler = handler;
	return this;
    }

    public <V> ValidationConstraint<V> constraintFor(UIInput<?, V> input) {
	ValidationConstraint<V> constraint = new ValidationConstraint<V>(input);
	this.add(constraint);

	return constraint;
    }

    public ValidationProcess add(ValidationConstraint<?> constraint) {
	this.constraints.add(constraint);
	return this;
    }

    public boolean validate() {
	return this.validate(Propagation.ALL);
    }

    public boolean validate(Propagation propagation) {
	boolean valid = true;
	ValidationResult result = new ValidationResult();

	for (ValidationConstraint<?> constraint : this.constraints) {
	    FieldState field = new FieldState(constraint.validate(), constraint.name);
	    result.add(field);

	    valid = field.isValid() && valid;
	    if (!valid && propagation == Propagation.STOP_AT_ONCE) {
		return false;
	    }
	}

	if (!valid && this.handler != null) {
	    this.handler.onEvent(new Event<ValidationResult>(result));
	}

	return valid;
    }

    public interface ValidationAction {

	void whenValid();

	void whenInvalid(String message);
    }

    public static class ValidationConstraint<V> {

	private String                   name;

	private UIInput<?, V>            input;

	private final List<Validator<V>> validators = new ArrayList<Validator<V>>();

	private ValidationAction         action;

	public ValidationConstraint(UIInput<?, V> input) {
	    this.input = input;
	}

	public ValidationConstraint<V> name(String name) {
	    this.name = name;
	    return this;
	}

	public ValidationConstraint<V> add(Validator<V> validator) {
	    this.validators.add(validator);
	    return this;
	}

	public ValidationConstraint<V> remove(Validator<V> validator) {
	    this.validators.remove(validator);
	    return this;
	}

	public ValidationConstraint<V> action(ValidationAction action) {
	    this.action = action;
	    return this;
	}

	public boolean validate() {
	    V value = this.input.getValue();

	    for (Validator<V> validator : this.validators) {
		boolean valid = validator.validate(value);
		if (!valid) {
		    this.action.whenInvalid(validator.getMessage());
		    return false;
		}
	    }

	    this.action.whenValid();

	    return true;
	}
    }

    public static class ValidationResult {

	private List<FieldState> fields = new ArrayList<FieldState>();

	void add(FieldState field) {
	    this.fields.add(field);
	}

	public List<FieldState> getFields() {
	    return fields;
	}

	public static class FieldState {

	    private boolean valid;

	    private String  name;

	    FieldState(boolean valid, String name) {
		super();
		this.valid = valid;
		this.name = name;
	    }

	    public String getName() {
		return name;
	    }

	    public boolean isValid() {
		return valid;
	    }
	}
    }

    public enum Propagation {
	ALL, STOP_AT_ONCE;
    }
}