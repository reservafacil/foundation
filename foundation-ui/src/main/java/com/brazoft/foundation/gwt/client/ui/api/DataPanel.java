package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.ui.*;
import com.brazoft.foundation.gwt.client.ui.Layout.LayoutOptions;
import com.brazoft.foundation.gwt.client.ui.LayoutRow.LayoutCell;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class DataPanel<D extends DataPanel<D>>
    extends Component<D> {

    private DataPanelStrategy strategy;

    public DataPanel(PanelOptions option, int columns) {
	super(ElementResolver.div());
	this.init(option, columns);
    }

    private void init(PanelOptions option, int columns) {
	this.className("data-panel");
	this.strategy = option.get().init(this, columns);
    }

    public D width(double width, Unit unit) {
	this.strategy.width(width, unit);
	
	return (D)this;
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
	this.strategy.cell().add(widget);
	return (D)this;
    }

    public D item(Widget widget, int colspan) {
	this.cell(colspan).add(widget);

	return (D)this;
    }
    
    public D adopt(Composite<?> composite) {
	composite.hidden().removeFromParent();
	this.item(composite, 1);
	composite.asQuery().fadeIn();
	
	return (D)this;
    }

    public D adopt(Composite<?> composite, int colspan) {
	composite.hidden().removeFromParent();
	this.item(composite, colspan);
	composite.asQuery().fadeIn();
	
	return (D)this;
    }

    protected UICell<?> cell(int colspan) {
	return this.strategy.cell(colspan);
    }
    
    public enum PanelOptions
    {
	FIXED
	{
	    DataPanelStrategy get()
	    {
		return new FixedStrategy();
	    }
	}
	, FLUID
	{
	    DataPanelStrategy get()
	    {
		return new FluidStrategy();
	    }
	};
	
	abstract DataPanelStrategy get();
    }
    
    static class FluidStrategy
	implements DataPanelStrategy {
	
	private HTML<DivElement> container = HTML.asDiv();
	
	private LayoutRow row;

	private int    columns;
	
	private int    columnCounter;

	private int    maxColumns = 12;

	public DataPanelStrategy init(DataPanel<?> panel, int columns)
	{
	    panel.add(this.container);
	    this.columns = columns;
	    return this;
	}
	
	@Override
	public UICell<?> cell() {
	    return this.cell(this.columns);
	}
	
	@Override
	public UICell<?> cell(int colspan) {
	    if (this.columnCounter == 0) {
		this.row = new LayoutRow(LayoutOptions.FLUID);
		this.container.add(this.row);
	    }

	    this.columnCounter += colspan;
	    if (columnCounter >= this.columns) {
		this.columnCounter = 0;
	    }
	    
	    LayoutCell cell = this.row.cell().span(Integer.valueOf((this.maxColumns / this.columns) * colspan));
	    cell.style().marginBottom(15, Unit.PX);

	    return cell;
	}
	
	@Override
	public void width(double width, Unit unit) {
	    GWT.log("FluidStrategy in DataPanel does not support width()");
	}
    }

    static class FixedStrategy
	implements DataPanelStrategy {

	private Table  table     = new Table();

	private Row    row;

	private int    columns;

	private int    columnCounter;

	private double width     = 100;

	private Unit   widthUnit = Unit.PCT;
	
	public DataPanelStrategy init(DataPanel<?> panel, int columns)
	{
	    panel.add(this.table);
	    this.table.style().width(this.width, this.widthUnit);
	    panel.add(this.table);
	    this.columns = columns;
	    
	    return this;
	}
	
	@Override
	public UICell<?> cell() {
	    return this.cell(this.columns);
	}
	
	public void width(double width, Unit unit) {
	    this.width = width;
	    this.widthUnit = unit;
	    this.table.style().width(this.width, this.widthUnit);
	}

	@Override
	public UICell<?> cell(int colspan) {
	    if (this.columnCounter == 0) {
		this.row = this.table.body().row();
	    }

	    this.columnCounter += colspan;
	    if (columnCounter >= this.columns) {
		this.columnCounter = 0;
	    }

	    Cell cell = this.row.cell();
	    cell.verticalAlign(VerticalAlignment.TOP);
	    cell.style().width((this.width / this.columns) * colspan, this.widthUnit).marginBottom(15, Unit.PX);

	    if (colspan > 1) {
		cell.colspan(colspan);
	    }

	    return cell;
	}
    }

    interface DataPanelStrategy {

	DataPanelStrategy init(DataPanel<?> panel, int columns);
	
	UICell<?> cell();
	
	UICell<?> cell(int colspan);
	
	void width(double width, Unit unit);
    }
}