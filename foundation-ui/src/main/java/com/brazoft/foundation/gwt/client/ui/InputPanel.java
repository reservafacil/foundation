package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.brazoft.foundation.gwt.client.util.*;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationAction;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationConstraint;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationResult;
import com.google.gwt.event.dom.client.*;

@SuppressWarnings("unchecked")
public abstract class InputPanel<I extends InputPanel<I>>
    extends OutputPanel<I> {

	protected ValidationProcess process = new ValidationProcess();

	public InputPanel(PanelOptions option, int columns) {
		super(option, columns);
	}

	public InputItem adopt(InputGroup<?> input) {

		input.hidden().removeFromParent();
		InputItem item = this.item(input);
		input.fadeIn();

		return item;
	}

	public InputItem item(final InputGroup<?> group) {
		UICell<?> cell = this.cell(group.getColspan());

		if (group.getInput() instanceof Select2) {
			group.getInput().style().property("width", cell.style().getWidth());
		}

		final InputControl control = new InputControl().input(group);

		cell.add(group.getLabel().detach());
		cell.add(control);

		if (group.getInput() instanceof HasFocusHandlers) {
			((HasFocusHandlers<?>)group.getInput()).onBlur(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					group.validate();
				}
			});
		}

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

		group.action(action);
		this.process.add(group.getConstraint());

		return new InputItem(cell, control);
	}

	public I onValidation(EventHandler<ValidationResult> handler) {
		this.process.onComplete(handler);
		return (I)this;
	}

	public boolean validate() {
		boolean isValid = this.process.validate();
		if (!isValid) {
			if (this.process.getConstraint().getInput() instanceof Bootstrap) {
				((Bootstrap<?>)this.process.getConstraint().getInput()).focus();
			}
		}
		return isValid;
	}

	public InputPanel<?> addConstraint(final InputGroup<?> group) {
		this.process.add(group.getConstraint());
		return this;
	}

	public InputPanel<?> mergeConstraint(final InputPanel<?> panel) {
		for (ValidationConstraint<?> validationConstraint : panel.process.getConstraints()) {
			this.process.add(validationConstraint);
		}
		return this;
	}

	public InputControl addItemControl(final InputGroup<?> group) {
		final InputControl control = new InputControl().input(group);

		if (group.getInput() instanceof HasFocusHandlers) {
			((HasFocusHandlers<?>)group.getInput()).onBlur(new BlurHandler() {
				@Override
				public void onBlur(BlurEvent event) {
					group.validate();
				}
			});
		}

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

		group.action(action);
		this.process.add(group.getConstraint());
		return control;
	}
	
	public static class InputItem {

		private UICell<?>    cell;

		private InputControl control;

		InputItem(UICell<?> cell, InputControl control) {
			super();
			this.cell = cell;
			this.control = control;
		}

		public UICell<?> cell() {
			return this.cell;
		}

		public InputControl control() {
			return this.control;
		}
	}
}