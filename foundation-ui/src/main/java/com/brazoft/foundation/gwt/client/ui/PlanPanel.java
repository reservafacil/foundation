package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.util.MessageFormat;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

public class PlanPanel
    extends Component<PlanPanel> {

	private PlanHeader   header;

	private PlanFeatures features;

	private PlanBody     body;

	private PlanFooter   footer;

	public PlanPanel() {
		super(ElementResolver.div());
		this.className("plan");
	}

	public PlanHeader header() {
		if (this.header == null) {
			this.header = new PlanHeader();
			this.add(this.header);
		}

		return this.header;
	}

	public PlanFeatures features() {
		if (this.features == null) {
			this.features = new PlanFeatures();
			this.add(this.features);
		}

		return this.features;
	}

	public PlanBody body() {
		if (this.body == null) {
			this.body = new PlanBody();
			this.add(this.body);
		}

		return this.body;
	}

	public PlanFooter footer() {
		if (this.footer == null) {
			this.footer = new PlanFooter();
			this.add(this.footer);
		}

		return this.footer;
	}

	public class PlanHeader
	    extends Component<PlanHeader>
	    implements HasText<PlanHeader> {

		private HTML<DivElement> title;

		private HTML<DivElement> subtitle;

		private HTML<DivElement> price;

		public PlanHeader() {
			super(ElementResolver.div());
			this.className("plan-header");
		}

		public PlanHeader add(Widget widget) {
			if (this.subtitle == null) {
				this.subtitle = HTML.asDiv().className("plan-subtitle");
				super.add(this.subtitle);
			}

			this.subtitle.add(widget);

			return this;
		}

		@Override
		public PlanHeader text(String text) {
			if (this.title == null) {
				this.title = HTML.asDiv().className("plan-title");
				super.add(this.title);
			}

			this.title.text(text);
			return this;
		}

		@Override
		public String getText() {
			return this.title.getText();
		}

		public PlanHeader price(String symbol, String value, String term) {
			if (this.price == null) {
				this.price = HTML.asDiv().className("plan-price");
				super.add(this.title);
			}

			String html = MessageFormat.format("<span class=\"note\">{0}</span><span>{1}</span><span class=\"term\">{2}</span>", symbol,
			                                   value, term);
			this.price.text(html);

			return this;
		}
	}

	public class PlanBody
	    extends Component<PlanBody> {

		private HTML<UListElement> ul = HTML.as(ElementResolver.ul());

		public PlanBody() {
			super(ElementResolver.div());
			this.className("plan-features").add(this.ul);
		}

		public PlanBody add(Widget widget) {
			HTML<LIElement> li = HTML.asListItem().add(widget);
			li.style().borderWidth(0, Unit.PX);
			this.ul.add(li);

			return this;
		}
	}

	public class PlanFeatures
	    extends Component<PlanFeatures> {

		private HTML<UListElement> ul = HTML.as(ElementResolver.ul());

		public PlanFeatures() {
			super(ElementResolver.div());
			this.className("plan-features").add(this.ul);
		}

		public PlanFeatures add(Widget widget) {
			this.ul.add(HTML.asListItem().add(widget));
			return this;
		}
	}

	public class PlanFooter
	    extends Component<PlanFooter> {

		public PlanFooter() {
			super(ElementResolver.div());
			this.className("plan-actions");
		}

		@Override
		public PlanFooter add(Widget add) {
			return super.add(add);
		}
	}
}
