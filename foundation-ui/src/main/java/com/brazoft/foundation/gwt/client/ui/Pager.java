package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.ui.api.Pagination;
import com.google.gwt.event.dom.client.*;

public final class Pager
    extends Pagination<Pager> {

    private int    pages;

    private int    page            = 1;

    private Item   next            = new Control().icon(Icon.CHEVRON_RIGHT);

    private Item   previous        = new Control().icon(Icon.CHEVRON_LEFT).disabled();

    private Item   counter         = new Item(ElementResolver.span());

    private String counterTemplate = "{0} - {1}";

    public Pager() {
	this.init();
    }

    private void init() {
	this.className("pager").add(this.previous).add(this.counter).add(this.next);

	this.previous.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Pager.this.previous();
	    }
	});

	this.next.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Pager.this.next();
	    }
	});
    }

    @Override
    public Pager pages(int pages) {
	if (pages == 1) {
	    this.next.disabled();
	}

	this.pages = pages;

	return this.count();
    }

    public Pager reset() {
	this.page = 1;

	this.previous.disabled();
	this.next.activate();

	return this.count();
    }

    @Override
    public int getCurrentPage() {
	return this.page;
    }

    @Override
    protected Pager previous() {
	this.next.activate();

	return this.run(-1);
    }

    @Override
    protected Pager next() {
	this.previous.activate();

	return this.run(1);
    }

    Pager run(int direction) {
	Item control = direction > 0 ? this.next : this.previous;

	int newPage = (this.page + direction);

	if (newPage == this.pages || newPage == 1) {
	    control.disabled();
	}

	if (newPage > 0 && newPage <= this.pages) {
	    this.page += direction;
	    this.fireEvent(this.page);

	    return this.count();
	}

	control.disabled();
	return this;
    }

    Pager count() {
	this.counter.text(this.counterTemplate.replace("{0}", String.valueOf(this.page)).replace("{1}", String.valueOf(this.pages)));
	return this;
    }

    public static class Control
	extends Item {

	@Override
	public Item activate() {
	    return this.visible();
	}

	@Override
	public Item disabled() {
	    return this.hidden();
	}
    }
}