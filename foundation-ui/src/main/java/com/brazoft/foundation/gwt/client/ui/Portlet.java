package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.ui.Widget;

public final class Portlet
    extends Component<Portlet> {

	private Header header = new Header();

	private Body   body   = new Body();

	public Portlet() {
		super(ElementResolver.div());
		this.className("portlet").add(this.header).add(this.body);
	}

	public Header header() {
		return this.header;
	}

	public Body body() {
		return this.body;
	}

	public class Header
	    extends Component<Header>
	    implements HasText<Header> {

		private HTML<SpanElement> icon  = HTML.asSpan().className("icon");

		private Heading           title = new Heading(5);

		private Toolbar           toolbar;

		Header() {
			super(ElementResolver.div());
			this.className("portlet-header");
			this.add(this.title);
		}

		public Header icon(Icon icon) {
			if(!this.icon.isAttached()) {
				this.insert(this.icon, this.title);
			}
			
			Widgets.setIcon(this.icon, icon);
			return this;
		}

		public Header text(String title) {
			this.title.text(title);
			return this;
		}

		@Override
		public String getText() {
			return this.title.getText();
		}

		public Toolbar toolbar() {
			if (this.toolbar == null) {
				this.toolbar = new Toolbar();
				this.add(this.toolbar);
			}

			return this.toolbar;
		}

		public class Toolbar
		    extends Component<Toolbar> {

			public Toolbar() {
				super(ElementResolver.div());
				this.className("toolbar");
			}

			public Toolbar button(UIButton<?> button) {
				return this.add(button.size(Size.SMALL).asWidget());
			}
		}
	}

	public class Body
	    extends Component<Body> {

		Body() {
			super(ElementResolver.div());
			this.className("portlet-body");
		}

		public Body add(Widget widget) {
			return super.add(widget);
		}

		public Row row() {
			Row row = new Row();

			this.add(row);

			return row;
		}

		public class Row
		    extends Component<Row> {

			public Row() {
				super(ElementResolver.div());
				this.className("portlet-row clearfix");
			}
		}
	}
}