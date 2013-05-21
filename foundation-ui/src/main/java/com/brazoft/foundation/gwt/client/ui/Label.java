package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.Emphasis.EmphasisOptions;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;

public final class Label
    extends Component<Label>
    implements HasText<Label> {

    private Emphasis      text;

    private UIInput<?, ?> input;

    public Label() {
	this(Orientation.VERTICAL);
    }

    public Label(Orientation orientation) {
	super(ElementResolver.label());
	this.init(orientation);
    }

    private void init(Orientation orientation) {
	if (orientation == Orientation.HORIZONTAL) {
	    this.className("inline");
	}
    }

    public UIInput<?, ?> getInput() {
	return this.input;
    }

    public Label forInput(UIInput<?, ?> input) {
	boolean checkbox = (input instanceof CheckBox);
	this.input = input;
	this.forId(input.getId());

	if (checkbox || input instanceof RadioButton) {
	    this.className(checkbox ? "checkbox" : "radio");
	    this.add(input.asWidget());
	    input.style().display(Display.INLINE).paddingLeft(4, Unit.PX);
	    this.text = new Emphasis(EmphasisOptions.SMALL).muted();
	    this.text.style().display(Display.INLINE).paddingLeft(4, Unit.PX);
	    this.add(this.text);

	    return this;
	}

	return this.add(input.asWidget());
    }

    public Label forId(String id) {
	this.element().setHtmlFor(id);
	return this;
    }

    @Override
    public Label text(String text) {
	if (this.text == null) {
	    this.text = new Emphasis(EmphasisOptions.SMALL);
	    this.add(this.text);
	}
	this.text.text(text);

	return this;
    }

    @Override
    public String getText() {
	if (this.text != null) {
	    return this.text.getText();
	}

	return "";
    }

    protected LabelElement element() {
	return this.getElement().cast();
    }
}