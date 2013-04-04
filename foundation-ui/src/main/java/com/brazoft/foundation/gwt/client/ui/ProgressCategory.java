package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.google.gwt.dom.client.Style.Unit;

public class ProgressCategory extends Bootstrap<ProgressCategory>
{
	private int	total	= 100;

	public ProgressCategory()
	{
		super(ElementResolver.div());
		this.className("bar");
	}

	public ProgressCategory success()
	{
		return this.className("bar-success");
	}

	public ProgressCategory warning()
	{
		return this.className("bar-warning");
	}

	public ProgressCategory danger()
	{
		return this.className("bar-danger");
	}

	public ProgressCategory info()
	{
		return this.className("bar-info");
	}

	public ProgressCategory total(int total)
	{
		this.total = total;
		return this;
	}

	public ProgressCategory worked(int amount)
	{
		this.style().width((amount / this.total) * 100, Unit.PCT);
		return this;
	}
}