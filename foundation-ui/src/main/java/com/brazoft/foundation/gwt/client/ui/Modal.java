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

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.ui.Button.ButtonOptions;
import com.brazoft.foundation.gwt.client.ui.api.*;
import com.google.gwt.user.client.ui.*;

public final class Modal
    extends NativeEvent<Modal> {

	private ModalHeader header = new ModalHeader();

	private ModalBody   body   = new ModalBody();

	private ModalFooter footer = new ModalFooter();

	public Modal() {
		super(ElementResolver.div());
		String id = ElementResolver.document().createUniqueId();
		this.id(id).className("modal hide fade in").attribute("role", "dialog");
		this.attribute("aria-labelledby", id + "_label").attribute("aria-hidden", "true");
		this.attribute("data-keyboard", "true");

		this.add(this.header.hidden()).add(this.body.hidden()).add(this.footer.hidden());
	}

	@Override
	public Modal detachChildren() {
		this.body.detachChildren();
		this.footer.detachChildren();
		return this;
	}

	public Modal hideOnComplete() {
		this.header.closeButton.hidden();
		return this.attribute("data-keyboard", "false");
	}

	public Modal statical() {
		return this.attribute("data-backdrop", "static");
	}

	public Button trigger() {
		return new Button(ButtonOptions.ANCHOR).attribute("href", "#" + this.getId()).attribute("role", "button").attribute("data-toggle",
		                                                                                                                    "modal");
	}

	public Modal heading(String text) {
		this.header.visible();
		return this.add(this.header.text(text));
	}

	public ModalBody body() {
		this.body.visible();

		return this.body;
	}

	public ModalFooter footer() {
		this.footer.visible();

		return this.footer;
	}

	public static class ModalBody
	    extends Bootstrap<ModalBody> {

		public ModalBody() {
			super(ElementResolver.div());
			this.className("modal-body");
		}

		@Override
		public ModalBody detachChildren() {
			return super.detachChildren();
		}

		@Override
		public ModalBody add(Widget add) {
			return super.add(add);
		}
	}

	public static class ModalFooter
	    extends Bootstrap<ModalFooter> {

		public ModalFooter() {
			super(ElementResolver.div());
			this.className("modal-footer");
		}

		@Override
		public ModalFooter detachChildren() {
			return super.detachChildren();
		}

		@Override
		public ModalFooter add(Widget add) {
			return super.add(add);
		}
	}

	public static class ModalHeader
	    extends Bootstrap<ModalHeader>
	    implements HasText<ModalHeader> {

		private Heading heading     = new Heading(3);

		private Button  closeButton = new Button().className("close").attribute("data-dismiss", "modal").attribute("aria-hidden", "true").text("×");

		public ModalHeader() {
			super(ElementResolver.div());
			this.className("modal-header");
			this.add(this.closeButton).add(this.heading);
		}

		@Override
		public String getText() {
			return this.heading.getText();
		}

		@Override
		public ModalHeader text(String text) {
			Component.Util.setHTML(this.heading, text);
			return this;
		}
	}

	public Modal onShow(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.SHOW, handler);
	}

	public Modal whenShown(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.SHOWN, handler);
	}

	public Modal onHide(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.HIDE, handler);
	}

	public Modal whenHidden(EventHandler<Void> handler) {
		return this.addHandler(FireableEvent.HIDDEN, handler);
	}

	public Modal show() {
		if (!this.isAttached()) {
			RootPanel.get().add(this);
		}
		this.doFunction(FireableEvent.SHOW.method(), this.getId());
		this.getElement().focus();
		return this;
	}

	public Modal hide() {
		this.doFunction(FireableEvent.HIDE.method(), this.getId());
		return this;
	}

	protected native void doFunction(String method, String id) /*-{
		$wnd.$("#" + id).modal(method);
	}-*/;

	enum FireableEvent
	    implements EventType {
		HIDE, HIDDEN, SHOW, SHOWN;

		public String method() {
			return this.name().toLowerCase();
		}
	}
}