package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.user.client.ui.Widget;

public abstract class OutputPanel<O extends OutputPanel<O>>
    extends DataPanel<O> {

	public OutputPanel(PanelOptions options, int columns) {
		super(options, columns);
	}

	public UICell<?> item(String label, String value) {
		return this.item(label, value, 1);
	}

	public UICell<?> item(String label, String value, int colspan) {
		if (value == null) {
			value = "&nbsp;";
		}

		Paragraph p = new Paragraph().text(value);
		return this.item(label, p, colspan);
	}

	public UICell<?> item(String label, Widget value) {
		return this.item(label, value, 1);
	}

	public UICell<?> item(String label, Widget value, int colspan) {
		OutputGroup group = new OutputGroup(value).label(label).colspan(colspan);

		UICell<?> cell = this.cell(group.getColspan());
		cell.add(group.getLabel());
		cell.add(group);

		return cell;
	}
}