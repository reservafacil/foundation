/**
 * Copyright (C) 2009-2012 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.brazoft.foundation.gwt.client.ui;

import java.util.*;
import java.util.List;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.Style;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.ui.NavigationList.ListItem;
import com.brazoft.foundation.gwt.client.ui.ProgressBar.ProgressBarOptions;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.*;

public final class Wizard
    extends EventSource<Wizard> {

	private final Modal      wizard = new Modal().className("wizard-modal");

	private final LeftPanel  left   = new LeftPanel();

	private final RightPanel right  = new RightPanel();

	private final Footer     footer = new Footer();

	private final Pager      pager  = new Pager();

	public Wizard() {
		this.setup();
		this.setElement(this.wizard.getElement());
	}

	public Style<Modal> style() {
		return this.wizard.style();
	}

	private void setup() {
		this.right.panel.add(this.footer);
		this.wizard.add(this.left).add(this.right);
	}

	public Wizard closeable() {
		this.wizard.heading("");
		return this;
	}

	public Wizard statical() {
		this.wizard.statical();
		return this;
	}

	public Wizard hideOnComplete() {
		this.wizard.hideOnComplete();
		return this;
	}

	public Wizard open() {
		this.wizard.show();
		return this;
	}

	public Wizard close() {
		this.wizard.hide();
		return this;
	}

	public Wizard previousText(String text) {
		this.footer.previous.text(text);
		return this;
	}

	public Wizard nextText(String text) {
		this.footer.next.text(text);
		return this;
	}

	public Wizard add(String name, WizardPage page) {
		this.left.step(name);
		this.pager.add(page);
		return this;
	}

	public Wizard onNext(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.NEXT, handler);
	}

	public Wizard onPrevious(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.PREVIOUS, handler);
	}

	class Pager {

		private List<WizardPage> pages = new ArrayList<WizardPage>();

		private int              index;

		private WizardPage       currentPage;

		void add(WizardPage page) {
			if (this.pages.isEmpty()) {
				this.attach(page);
				this.currentPage = page;
			}

			Wizard.this.left.bar.total(this.pages.size());
			this.pages.add(page);
		}

		void attach(WizardPage page) {
			Wizard.this.right.add(page);
		}

		void doNext() {
			if (this.currentPage.validate()) {
				this.doNavigation(1);
			}
		}

		void doPrevious() {
			if (this.index > 0) {
				this.doNavigation(-1);
			}
		}

		private void doNavigation(int direction) {
			this.currentPage.detach();
			this.index = this.index + direction;
			this.currentPage = this.pages.get(this.index);
			this.attach(this.currentPage);

			Wizard.this.left.steps.activate(this.index);
			this.assertControls();

			if (direction > 0) {
				Wizard.this.left.bar.worked(this.index);
				Wizard.this.fireEvent(FireableEvent.NEXT);
				return;
			}

			Wizard.this.fireEvent(FireableEvent.PREVIOUS);
		}

		private void assertControls() {
			Wizard.this.footer.next.enable();
			Wizard.this.footer.previous.enable();

			if (this.index == (pages.size() - 1)) {
				Wizard.this.footer.next.disable();
			}

			if (this.index == 0) {
				Wizard.this.footer.previous.disable();
			}
		}
	}

	class Footer
	    extends Composite<Footer> {

		private HTML<DivElement> panel     = HTML.asDiv().className("wizard-modal-footer");

		private HTML<DivElement> container = HTML.asDiv().className("wizard-buttons-container");

		private Button           previous  = new Button().className("wizard-back");

		private Button           next      = new Button().className("wizard-next");

		public Footer() {
			this.initWidget(this.panel);
			this.setup();
		}

		private void setup() {
			this.previous.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Wizard.this.pager.doPrevious();
				}
			});

			this.next.onClick(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Wizard.this.pager.doNext();
				}
			});

			this.container.add(this.previous.disable()).add(this.next);
			this.panel.add(this.container);
		}
	}

	class RightPanel
	    extends Composite<RightPanel> {

		private HTML<DivElement> panel     = HTML.asDiv().className("wizard-cards");

		private HTML<DivElement> container = HTML.asDiv().className("wizard-card-container");

		public RightPanel() {
			this.initWidget(this.panel);
			this.setup();
		}

		private void setup() {
			this.panel.add(this.container);
		}

		public RightPanel add(WizardPage page) {
			this.container.add(page);
			return this;
		}
	}

	class LeftPanel
	    extends Composite<LeftPanel> {

		private HTML<DivElement> panel     = HTML.asDiv().className("pull-left wizard-steps");

		private HTML<DivElement> container = HTML.asDiv().className("wizard-nav-container");

		private NavigationList   steps     = new NavigationList();

		private HTML<DivElement> progress  = HTML.asDiv().className("wizard-progress-container");

		private ProgressBar      bar       = new ProgressBar(ProgressBarOptions.ANIMATED);

		public LeftPanel() {
			this.initWidget(this.panel);
			this.setup();
		}

		private void setup() {
			this.steps.style().paddingBottom(30, Unit.PX);

			this.container.add(this.steps);
			this.progress.add(this.bar);
			this.panel.add(this.container).add(this.progress);
		}

		public LeftPanel step(String label) {
			boolean activate = false;

			if (!this.steps.hasChildren()) {
				activate = true;
			}

			ListItem item = this.steps.item(label).className("wizard-nav-item");
			item.link().className("wizard-nav-link");
			Widgets.setIcon(item.link(), Icon.CHEVRON_RIGHT);

			if (activate) {
				this.steps.activate(0);
			}

			return this;
		}
	}

	enum FireableEvent
	    implements EventType {
		NEXT, PREVIOUS;
	}
}