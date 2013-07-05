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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.json.*;
import com.brazoft.foundation.gwt.client.ui.Select;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.AttachEvent;

@SuppressWarnings("unchecked")
public abstract class Select2<S extends Select2<S, V>, V>
    extends Bootstrap<S>
    implements UIInput<S, V>, HasChangeHandlers<S>, HasFocusHandlers<S> {

	private JSONObject   options = JSON.asObject();

	private final Select input;

	private boolean      required;
	
	/*
	<div class="select2-container select2-dropdown-open select2-container-active" id="s2id_gwt-uid-2" style="width: 100%;">
		<a tabindex="-1" class="select2-choice" onclick="return false;" href="javascript:void(0)">
		<span>PlaceHolder</span>
		<abbr style="display:none;" class="select2-search-choice-close"></abbr>
	</a>
	<input type="text" class="select2-focusser select2-offscreen" disabled="disabled"></div>
	*/

	public Select2(boolean multiple) {
		super(ElementResolver.div());
		this.input = new Select(multiple).placeholder("");
		this.init();
	}

	private void init() {
		this.onAttach(new AttachHandler() {

			@Override
			protected void onAttach(AttachEvent event) {
				initJS(getId(), options.toJavaScriptObject());
			}
		});
		this.add(this.input);
	}

	@Override
	public S onChange(ChangeHandler handler) {
		this.input.onChange(handler);
		return (S)this;
	}

	@Override
	public S onFocus(FocusHandler handler) {
		this.input.onFocus(handler);
		return (S)this;
	}

	@Override
	public S onBlur(BlurHandler handler) {
		this.input.onBlur(handler);
		return (S)this;
	}

	public S block() {
		this.input.block();
		return (S)this;
	}

	public S open() {
		this.doIt(getId(), "open");
		return (S)this;
	}

	public S close() {
		this.doIt(getId(), "close");
		return (S)this;
	}

	public S openOnEnter(boolean open) {
		this.options.put("openOnEnter", open);
		return (S)this;
	}

	public S allowClear(boolean allow) {
		this.options.put("allowClear", allow);
		return (S)this;
	}

	public S minimumInputLength(int length) {
		this.options.put("minimumInputLength", length);
		return (S)this;
	}

	public S maximumInputLength(int length) {
		this.options.put("maximumInputLength", length);
		return (S)this;
	}

	protected JSONObject getOptions() {
		return this.options;
	}

	public S select(int index) {
		this.input.select(index);

		return this.update();
	}

	public S deselect(int index) {
		this.input.deselect(index);

		return this.update();
	}

	public S item(String value) {
		return this.item(value, value);
	}

	public S item(String text, String value) {
		this.input.item(text, value);
		return (S)this;
	}

	@Override
	public S placeholder(String placeholder) {
		this.options.put("placeholder", placeholder);
		return (S)this;
	}

	@Override
	public boolean isReadOnly() {
		return !this.isEditable();
	}

	@Override
	public S readonly() {
		this.element().setDisabled(true);
		return this.update();
	}

	@Override
	public boolean isEditable() {
		return !this.element().isDisabled();
	}

	@Override
	public S editable() {
		this.element().setDisabled(false);
		return this.update();
	}

	@Override
	public boolean isNullable() {
		return !this.isRequired();
	}

	public S nullable() {
		this.required = false;
		return this.update();
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	public S required() {
		this.required = true;
		return this.update();
	}

	protected NodeIterable<OptionElement> options() {
		return new NodeIterable<OptionElement>(this.input.element().getOptions());
	}

	protected SelectElement element() {
		return this.getElement().cast();
	}

	public S clear() {
		this.select(getId(), "");
		return (S)this;
	}

	public S update() {
		this.update(getId());
		return (S)this;
	}

	@Override
	public String getId() {
		return this.input.getId();
	}

	@Override
	public S detach() {
		this.input.hidden();
		return super.detach();
	}

	protected native String getSelection(String id) /*-{
		var value = $wnd.$("#" + id).select2("val");
		return value.toString();
	}-*/;

	protected native void select(String id, String value)/*-{
		$wnd.$("#" + id).select2("val", value);
	}-*/;

	native void update(String id)/*-{
		$wnd.$('#' + id).select2().trigger('change');
	}-*/;

	native void doIt(String id, String action)/*-{
		$wnd.$("#" + id).select2(action);
	}-*/;

	native void initJS(String id, JavaScriptObject options)/*-{
		$wnd.$("#" + id).select2(options);
	}-*/;
	
	/*Options HTML 
		<div style="top: 30px; left: 0px; width: 1920px; display: block;" class="select2-drop select2-drop-active" id="select2-drop">
		<div class="select2-search select2-search-hidden">
			<input type="text" class="select2-input" autocomplete="off">
		</div>
		<ul class="select2-results">
			<li class="select2-results-dept-0 select2-result select2-result-selectable">
				<div class="select2-result-label"><span class="select2-match"></span>Value 1</div>
			</li>
			<li class="select2-results-dept-0 select2-result select2-result-selectable">
				<div class="select2-result-label"><span class="select2-match"></span>Value 2</div>
			</li>
			<li class="select2-results-dept-0 select2-result select2-result-selectable select2-highlighted">
				<div class="select2-result-label"><span class="select2-match"></span>Value 3</div>
			</li>
		</ul>
	</div> 
	*/
}