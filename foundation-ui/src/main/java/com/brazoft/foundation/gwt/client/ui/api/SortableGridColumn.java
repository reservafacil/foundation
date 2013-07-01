package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.ui.Icon;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jso.JSObject;

@SuppressWarnings("unchecked")
public abstract class SortableGridColumn<T extends SortableGridColumn<T, J>, J extends JSObject>
    extends GridColumn<T, J> {

	private SortDirection direction = SortDirection.NONE;

	public SortableGridColumn() {
		this.sortable();
	}

	@Override
	public T unsort() {
		this.headerCell().removeClassName("sorted").icon(Icon.NONE);
		return (T)this;
	}

	@Override
	public T sort(JsArray<J> rows) {
		this.direction = this.direction.reverse();

		this.mark(this.direction);

		this.doSort(rows, this.getName(), this.direction);

		return (T)this;
	}

	public T mark(SortDirection direction) {
		this.headerCell().className("sorted").icon(this.direction.icon());
		return (T)this;
	}

	protected abstract void doSort(JsArray<J> rows, String name, SortDirection direction);

	public enum SortDirection {
		NONE {

			@Override
			public int direction() {
				return 0;
			}

			@Override
			SortDirection reverse() {
				return ASC;
			}

			@Override
			Icon icon() {
				return null;
			}
		},
		DESC {

			@Override
			public int direction() {
				return -1;
			}

			@Override
			SortDirection reverse() {
				return ASC;
			}

			@Override
			Icon icon() {
				return Icon.CHEVRON_DOWN;
			}
		},
		ASC {

			@Override
			public int direction() {
				return 1;
			}

			@Override
			SortDirection reverse() {
				return DESC;
			}

			@Override
			Icon icon() {
				return Icon.CHEVRON_UP;
			}
		};

		public abstract int direction();

		abstract SortDirection reverse();

		abstract Icon icon();
	}
}