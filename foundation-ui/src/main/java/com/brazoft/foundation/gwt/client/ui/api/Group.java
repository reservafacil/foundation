package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.ui.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;

@SuppressWarnings("unchecked")
public abstract class Group<G extends Group<G>>
    extends Composite<G> {

    private LabelGroup label = new LabelGroup();

    private int        colspan = 1;

    public G label(String labelText) {
	this.label.text(labelText);

	return (G)this;
    }

    public G hint(String title) {
	this.label.hint(title);

	return (G)this;
    }

    public LabelGroup getLabel() {
	return this.label;
    }

    public G colspan(int colspan) {
	this.colspan = colspan;
	return (G)this;
    }

    public int getColspan() {
	return this.colspan;
    }

    public class LabelGroup
	extends Component<LabelGroup>
	implements HasText<LabelGroup> {

	private LabeledText         label = new LabeledText().align(Alignment.LEFT);

	private HTML<SpanElement>   mark  = HTML.asSpan().className("required");

	private HTML<AnchorElement> hint  = HTML.asAnchor("#").className("hint");

	public LabelGroup() {
	    super(ElementResolver.div());
	    this.add(this.label).add(this.mark).add(this.hint).hidden();
	    this.style().marginBottom(5, Unit.PX);
	}

	public LabelGroup hint(String title) {
	    Widgets.setIcon(this.hint, Icon.QUESTION_SIGN);
	    this.hint.title(title).style().marginLeft(5, Unit.PX);
	    return this.visible();
	}

	public LabelGroup mark() {
	    this.mark.text("*");
	    return this.visible();
	}

	public LabelGroup unmark() {
	    this.mark.text("");
	    return this.visible();
	}

	@Override
	public LabelGroup text(String text) {
	    this.label.text(text);
	    return this.visible();
	}

	@Override
	public String getText() {
	    return this.label.getText();
	}
    }
}