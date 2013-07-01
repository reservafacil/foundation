package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.Pagination;
import com.google.gwt.event.dom.client.*;

public final class Paginator
    extends Pagination<Paginator> {

	private Item previous;

	private Item next;

	private Item active;

	public Paginator() {
		super();
		this.className("pagination");
	}

	public Paginator size(Size size) {
		switch (size) {
			case LARGE:
				this.className("pagination-large");
				break;
			case MINI:
				this.className("pagination-mini");
				break;
			case SMALL:
				this.className("pagination-small");
				break;
			default:
				break;
		}

		return this;
	}

	@Override
	public Paginator align(Alignment alignment) {
		switch (alignment) {
			case CENTER:
				this.className("pagination-centered");
				break;
			case RIGHT:
				this.className("pagination-right");
				break;
			default:
				break;
		}

		return this;
	}

	public Paginator pages(final int pages) {
		this.detachChildren();

		if (this.previous == null) {
			this.previous = new Item();
			this.previous.text("&laquo;");
			this.previous.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Paginator.this.previous();
				}
			});
		}

		this.previous.disabled();
		this.add(this.previous);

		for (int i = 0; i < pages; i++) {
			final Item item = new Item();
			item.text(String.valueOf(i + 1));
			item.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (!item.isDisabled()) {
						Paginator.this.active.activate();
						Paginator.this.active = item;
						run(0);
					}
				}
			});
			this.add(item);
		}

		this.active = (Item)this.getChild(1);
		this.active.disabled();

		if (this.next == null) {
			this.next = new Item();
			this.next.text("&raquo;");
			this.next.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Paginator.this.next();
				}
			});
		}

		this.add(this.next);

		if (pages <= 1) {
			this.next.disabled();
		}

		return this;
	}

	public int getCurrentPage() {
		return this.getIndex(this.active);
	}

	protected Paginator previous() {
		this.next.activate();
		return this.run(-1);
	}

	protected Paginator next() {
		this.previous.activate();
		return this.run(1);
	}

	Paginator run(int page) {
		Item selection = this.getChild(this.getCurrentPage() + page);
		Item control = page > 0 ? this.next : this.previous;

		if (!selection.equals(control)) {
			this.active.activate();
			selection.disabled();

			this.active = selection;
			this.fireEvent(Integer.valueOf(this.active.getText()));

			return this;
		}

		control.disabled();

		return this;
	}
}