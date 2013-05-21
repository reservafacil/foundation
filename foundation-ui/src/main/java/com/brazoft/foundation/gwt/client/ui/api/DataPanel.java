package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.ui.*;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class DataPanel<D extends DataPanel<D>>
    extends Component<D> {

    private Table  table     = new Table();

    private Row    row;

    private int    columns;

    private int    columnCounter;

    private double width     = 100;

    private Unit   widthUnit = Unit.PCT;

    public DataPanel(int columns) {
	super(ElementResolver.div());
	this.className("data-panel");
	this.init(columns);
    }

    private void init(int columns) {
	super.add(this.table);
	this.table.style().width(this.width, this.widthUnit);
	this.columns = columns;
    }

    public D width(double width, Unit unit) {
	this.width = width;
	this.widthUnit = unit;
	this.table.style().width(this.width, this.widthUnit);

	return (D)this;
    }

    protected Cell cell(int colspan) {
	if (this.columnCounter == 0) {
	    this.row = this.table.body().row();
	}

	this.columnCounter += colspan;
	if (columnCounter >= this.columns) {
	    this.columnCounter = 0;
	}

	Cell cell = this.row.cell();
	cell.verticalAlign(VerticalAlignment.TOP);
	cell.style().width((this.width / this.columns), this.widthUnit);

	if (colspan > 1) {
	    cell.colspan(colspan);
	    cell.style().width((this.width / this.columns) * colspan, this.widthUnit);
	}

	return cell;
    }

    public D warning(String headingText, String messageText) {
	HTML<DivElement> alert = HTML.asDiv().className("alert alert-warning");
	Heading heading = new Heading(4).className("alert-heading").text(headingText);
	Paragraph message = new Paragraph().text(messageText);

	alert.add(heading).add(message);
	this.item(alert);

	return (D)this;
    }

    public D item(Widget widget) {
	return this.item(widget, this.columns);
    }
    
    public D item(Widget widget, int colspan) {
	this.cell(colspan).add(widget);

	return (D)this;
    }
}