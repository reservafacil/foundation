package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.validator.NotEmptyValidator;
import com.brazoft.foundation.commons.validator.api.Validator;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.api.Group;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationAction;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationConstraint;

public class InputGroup<V>
    extends Group<InputGroup<V>>
    implements UIInput<InputGroup<V>, V> {

    private final UIInput<?, V>           input;

    private final ValidationConstraint<V> constraint;

    private final NotEmptyValidator<V>    validator = new NotEmptyValidator<V>();

    public InputGroup(UIInput<?, V> input) {
	this.input = input;
	this.initWidget(input.asWidget());
	this.constraint = new ValidationConstraint<V>(input);
    }

    public InputGroup<V> requiredMessage(String text) {
	this.validator.message(text);
	return this;
    }

    @Override
    public InputGroup<V> label(String labelText) {
	this.constraint.name(labelText);

	return super.label(labelText);
    }

    public UIInput<?, V> getInput() {
	return input;
    }

    public InputGroup<V> clear() {
	this.input.clear();
	return this;
    }

    @Override
    public InputGroup<V> value(V value) {
	this.input.value(value);
	return this;
    }

    @Override
    public V getValue() {
	return this.input.getValue();
    }

    public ValidationConstraint<V> getConstraint() {
	return this.constraint;
    }

    public InputGroup<V> validator(Validator<V> validator) {
	this.constraint.add(validator);
	return this;
    }

    public InputGroup<V> action(ValidationAction action) {
	this.constraint.action(action);
	return this;
    }

    boolean validate() {
	return this.constraint.validate();
    }

    @Override
    public InputGroup<V> placeholder(String placeholder) {
	this.input.placeholder(placeholder);
	return this;
    }

    @Override
    public boolean isReadOnly() {
	return this.input.isReadOnly();
    }

    @Override
    public InputGroup<V> readonly() {
	this.input.readonly();
	return this;
    }

    @Override
    public boolean isEditable() {
	return this.input.isEditable();
    }

    @Override
    public InputGroup<V> editable() {
	this.input.editable();
	return this;
    }

    @Override
    public boolean isNullable() {
	return this.input.isNullable();
    }

    @Override
    public InputGroup<V> nullable() {
	this.getLabel().unmark();
	this.constraint.remove(this.validator);
	this.input.nullable();
	this.validate();
	return this;
    }

    @Override
    public boolean isRequired() {
	return this.input.isRequired();
    }

    @Override
    public InputGroup<V> required() {
	this.getLabel().mark();
	this.constraint.add(this.validator);
	this.input.required();
	return this;
    }
}