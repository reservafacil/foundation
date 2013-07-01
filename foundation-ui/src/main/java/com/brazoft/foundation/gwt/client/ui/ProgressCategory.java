package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.dom.client.Style.Unit;

public final class ProgressCategory
    extends Bootstrap<ProgressCategory> {

	private double                         total   = 100;

	private StyleChooser<ProgressCategory> chooser = new StyleChooser<ProgressCategory>("bar-success", "bar-warning", "bar-danger",
	                                                                                    "bar-info");

	public ProgressCategory() {
		super(ElementResolver.div());
		this.className("bar");
	}

	public ProgressCategory success() {
		return this.chooser.className(this, "bar-success");
	}

	public ProgressCategory warning() {
		return this.chooser.className(this, "bar-warning");
	}

	public ProgressCategory danger() {
		return this.chooser.className(this, "bar-danger");
	}

	public ProgressCategory info() {
		return this.chooser.className(this, "bar-info");
	}

	public ProgressCategory total(double total) {
		this.total = total;
		return this;
	}

	public ProgressCategory worked(double amount) {
		this.style().width((amount / this.total) * 100d, Unit.PCT);
		return this;
	}
}