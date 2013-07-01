package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public class Select
    extends Component<Select>
    implements UIInput<Select, String>, HasFocusHandlers<Select>, HasChangeHandlers<Select>, HasKeyHandlers<Select>,
    HasMouseHandlers<Select> {

	private boolean required;

	public Select(boolean multiple) {
		super(ElementResolver.select(multiple));
	}

	@Override
	public Select onMouseDown(MouseDownHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onMouseMove(MouseMoveHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onMouseOut(MouseOutHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onMouseOver(MouseOverHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onMouseUp(MouseUpHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onMouseWheel(MouseWheelHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onKeyPress(KeyPressHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onKeyDown(KeyDownHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onKeyUp(KeyUpHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onChange(ChangeHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onFocus(FocusHandler handler) {
		return Events.on(this, handler);
	}

	@Override
	public Select onBlur(BlurHandler handler) {
		return Events.on(this, handler);
	}

	public Select block() {
		this.style().width(100, Unit.PCT);
		return this;
	}

	public Select select(int index) {
		this.element().getOptions().getItem(index).setSelected(true);

		return this;
	}

	public Select deselect(int index) {
		this.element().getOptions().getItem(index).setSelected(false);

		return this;
	}

	public Select item(String value) {
		return this.item(value, value);
	}

	public Select item(String text, String value) {
		Option option = new Option().text(text).value(value);

		this.add(option);

		return this;
	}

	@Override
	public Select placeholder(String placeholder) {
		this.attribute("data-placeholder", placeholder);
		return this;
	}

	@Override
	public boolean isReadOnly() {
		return !this.isEditable();
	}

	@Override
	public Select readonly() {
		this.element().setDisabled(true);
		return this;
	}

	@Override
	public boolean isEditable() {
		return !this.element().isDisabled();
	}

	@Override
	public Select editable() {
		this.element().setDisabled(false);
		return this;
	}

	@Override
	public boolean isNullable() {
		return !this.isRequired();
	}

	public Select nullable() {
		this.required = false;
		return this;
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	public Select required() {
		this.required = true;
		return this;
	}

	protected NodeIterable<OptionElement> options() {
		return new NodeIterable<OptionElement>(this.element().getOptions());
	}

	public SelectElement element() {
		return this.getElement().cast();
	}

	public Select clear() {
		this.value("");
		return this;
	}

	@Override
	public Select value(String value) {
		for (OptionElement option : this.options()) {
			option.setSelected(option.getValue().equals(value));
		}

		return this;
	}

	@Override
	public String getValue() {
		for (OptionElement option : this.options()) {
			if (option.isSelected()) {
				return option.getValue();
			}
		}

		return null;
	}

	class Option
	    extends Widget
	    implements HasText<Option>, HasValue<Option, String> {

		public Option() {
			this.setElement(ElementResolver.option());
		}

		OptionElement element() {
			return this.getElement().cast();
		}

		public boolean isSelected() {
			return this.element().isSelected();
		}

		@Override
		public Option value(String value) {
			this.element().setValue(value);
			DomEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
			return this;
		}

		@Override
		public String getValue() {
			return this.element().getValue();
		}

		@Override
		public Option text(String text) {
			return Component.Util.setHTML(this, text);
		}

		@Override
		public String getText() {
			return Component.Util.getHTML(this);
		}
	}
}