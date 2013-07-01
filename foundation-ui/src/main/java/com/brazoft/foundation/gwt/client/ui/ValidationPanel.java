package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.validator.api.Validator;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.ui.api.InputControl;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationAction;

public class ValidationPanel<V>
    extends Component<ValidationPanel<V>> {

	private InputGroup<V> group;

	public ValidationPanel(InputGroup<V> group) {
		super(ElementResolver.div());
		this.group = group;
		this.setup();
	}

	private void setup() {
		final InputControl control = new InputControl().input(this.group);
		this.add(control);

		ValidationAction action = new ValidationAction() {

			@Override
			public void whenValid() {
				control.reset();
			}

			@Override
			public void whenInvalid(String message) {
				control.error().message(message);
			}
		};

		this.group.action(action);
	}

	public ValidationPanel<V> requiredMessage(String text) {
		this.group.requiredMessage(text);
		return this;
	}

	public ValidationPanel<V> required() {
		this.group.required();
		return this;
	}

	public ValidationPanel<V> nullable() {
		this.group.nullable();
		return this;
	}

	public ValidationPanel<V> validator(Validator<V> validator) {
		this.group.validator(validator);
		return this;
	}

	public boolean validate() {
		return this.group.validate();
	}
}