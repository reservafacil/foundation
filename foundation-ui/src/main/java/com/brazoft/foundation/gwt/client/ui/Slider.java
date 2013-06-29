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

import com.brazoft.foundation.gwt.client.component.*;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.json.*;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.util.MessageFormat;
import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.DivElement;

public final class Slider
    extends Bootstrap<Slider>
    implements UIInput<Slider, Double> {

    private JSONObject       options = JSON.asObject();

    private HTML<DivElement> slider  = HTML.asDiv();

    private HTML<DivElement> placeholder;

    private boolean          firstOnChange;

    private boolean          required;

    public Slider() {
	super(ElementResolver.div());
	this.init();
    }

    private void init() {
	this.add(this.slider).range(0, 100).step(1).value(0d);
    }

    public Slider onChange(EventHandler<JsArrayNumber> handler) {
	this.addHandler(SliderEvent.CHANGE, handler);
	return this;
    }

    public Slider onSlide(EventHandler<JsArrayNumber> handler) {
	this.addHandler(SliderEvent.SLIDE, handler);
	return this;
    }

    public Slider onStart(EventHandler<JsArrayNumber> handler) {
	this.addHandler(SliderEvent.START, handler);
	return this;
    }

    public Slider onStop(EventHandler<JsArrayNumber> handler) {
	this.addHandler(SliderEvent.STOP, handler);
	return this;
    }

    public Slider animate(boolean animate) {
	return this.option(SliderOption.ANIMATE, animate);
    }

    public Slider orientation(Orientation orientation) {
	return this.option(SliderOption.ORIENTATION, orientation.toString().toLowerCase());
    }

    public Slider range(double start, double end) {
	this.option(SliderOption.START, start);
	return this.option(SliderOption.END, end);
    }

    public Slider range(double start, double end, Range limitTo) {
	switch (limitTo) {
	    case MAXIMUM:
		this.option(SliderOption.RANGE, "max");
		break;
	    case MINIMUM:
		this.option(SliderOption.RANGE, "min");
		break;
	    default:
		this.option(SliderOption.RANGE, true);
	}
	return this.range(start, end);
    }

    public Slider step(double step) {
	return this.option(SliderOption.STEP, step);
    }

    Slider option(SliderOption option, boolean value) {
	this.options.put(option.toString(), value);

	if (this.isAttached()) {
	    this.setOptionJS(this.slider.getId(), option.toString(), value);
	}

	return this;
    }

    Slider option(SliderOption option, double value) {
	this.options.put(option.toString(), value);

	if (this.isAttached()) {
	    this.setOptionJS(this.slider.getId(), option.toString(), value);
	}

	return this;
    }

    Slider option(SliderOption option, String value) {
	this.options.put(option.toString(), value);

	if (this.isAttached()) {
	    this.setOptionJS(this.slider.getId(), option.toString(), value);
	}

	return this;
    }

    public double getStart() {
	return this.options.getNumber(SliderOption.START.toString()).doubleValue();
    }

    public double getEnd() {
	return this.options.getNumber(SliderOption.END.toString()).doubleValue();
    }

    @Override
    public Slider clear() {
	this.value(this.getStart());
	return this;
    }

    public Slider value(Double value) {
	return this.option(SliderOption.VALUE, value);
    }

    public Double getValue() {
	return this.getValueAtIndex(0);
    }

    public Double[] getValues() {
	JsArrayNumber number = this.getValuesJS(this.slider.getId());
	Double[] values = new Double[number.length()];

	for (int i = 0; i < number.length(); i++) {
	    values[i] = number.get(i);
	}

	return values;
    }

    public double getValueAtIndex(int index) {
	return getValueJS(this.slider.getId(), index);
    }

    public static Double getValue(Event<JsArrayNumber> event) {
	JsArrayNumber values = Slider.getValues(event);

	if (values.length() == 0) {
	    return -1d;
	}

	return values.get(0);
    }

    public static JsArrayNumber getValues(Event<JsArrayNumber> event) {
	return event.data();
    }

    public Slider values(Double[] values) {
	JSONCollection<Number> collection = JSON.asNumberCollection();

	for (Double value : values) {
	    collection.add(value);
	}

	this.options.put(SliderOption.VALUES.toString(), collection);

	if (this.isAttached()) {
	    this.setValuesJS(this.slider.getId(), collection.array().getJavaScriptObject());
	}

	this.firstOnChange = true;

	return this;
    }

    @Override
    public Slider placeholder(final String placeholder) {
	if (this.placeholder == null) {
	    this.placeholder = HTML.asDiv().className("ui-slider-placeholder").text("&nbsp;");
	    this.updatePlaceHolder(placeholder);
	    this.insert(this.placeholder, this.slider);

	    this.onSlide(new EventHandler<JsArrayNumber>() {

		@Override
		public void onEvent(Event<JsArrayNumber> e) {
		    Slider.this.updatePlaceHolder(placeholder);
		}
	    });
	}

	return this;
    }

    void updatePlaceHolder(String placeholder) {
	Double[] values = this.getValues();

	if (values.length == 0 && !this.getValue().isNaN()) {
	    values = new Double[] {this.getValue()};
	}

	String message = MessageFormat.format(placeholder, values);
	if (!message.equals(placeholder)) {
	    this.placeholder.text(message);
	}
    }

    @Override
    public boolean isReadOnly() {
	return this.options.getBoolean(SliderOption.DISABLED.toString());
    }

    @Override
    public Slider readonly() {
	this.option(SliderOption.DISABLED, true);
	return this;
    }

    @Override
    public boolean isEditable() {
	return !this.isReadOnly();
    }

    @Override
    public Slider editable() {
	this.option(SliderOption.DISABLED, false);
	return this;
    }

    @Override
    public boolean isNullable() {
	return !this.required;
    }

    @Override
    public Slider nullable() {
	this.required = false;
	return this;
    }

    @Override
    public boolean isRequired() {
	return this.required;
    }

    @Override
    public Slider required() {
	this.required = true;
	return this;
    }

    public enum Range {
	MINIMUM, MAXIMUM, VALUES;
    }

    @Override
    protected void onLoad() {
	this.createSliderJS(this, this.slider.getId(), this.options.toJavaScriptObject());
    }

    @Override
    protected void onUnload() {
	this.destroySliderJS(this, this.slider.getId());
    }

    private void fireOnChangeEvent(com.google.gwt.user.client.Event evt, JsArrayNumber values, boolean hasOriginalEvent) {
	if (hasOriginalEvent || this.firstOnChange) {
	    this.fireEvent(new Event<JsArrayNumber>(SliderEvent.CHANGE, this, values));
	}

	this.firstOnChange = false;
    }

    private boolean fireOnSlideEvent(com.google.gwt.user.client.Event evt, JsArrayNumber values) {
	this.fireEvent(new Event<JsArrayNumber>(SliderEvent.SLIDE, this, values));

	return true;
    }

    private void fireOnStartEvent(com.google.gwt.user.client.Event evt, JsArrayNumber values) {
	this.fireEvent(new Event<JsArrayNumber>(SliderEvent.START, this, values));
    }

    private void fireOnStopEvent(com.google.gwt.user.client.Event evt, JsArrayNumber values) {
	this.fireEvent(new Event<JsArrayNumber>(SliderEvent.STOP, this, values));
    }

    /*
     * JSNI methods
     */
    private native void setOptionJS(String id, String option, double value) /*-{
	                                                                    $wnd.$("#" + id).slider("option", option, value);
	                                                                    }-*/;

    private native double getOptionJS(String id, String option) /*-{
	                                                        return $wnd.$("#" + id).slider("option", option);
	                                                        }-*/;

    private native void setOptionJS(String id, String option, boolean value) /*-{
	                                                                     $wnd.$("#" + id).slider("option", option, value);
	                                                                     }-*/;

    private native boolean getBooleanOptionJS(String id, String option) /*-{
	                                                                return $wnd.$("#" + id).slider("option", option);
	                                                                }-*/;

    private native void setOptionJS(String id, String option, String value) /*-{
	                                                                    $wnd.$("#" + id).slider("option", option, value);
	                                                                    }-*/;

    private native String getStringOptionJS(String id, String option) /*-{
	                                                              return $wnd.$("#" + id).slider("option", option);
	                                                              }-*/;

    private native void setValuesJS(String id, JavaScriptObject values) /*-{
	                                                                $wnd.$("#" + id).slider("option", "values", values);
	                                                                }-*/;

    private native JsArrayNumber getValuesJS(String id) /*-{
	                                                return $wnd.$("#" + id).slider("values");
	                                                }-*/;

    private native double getValueJS(String id, int index) /*-{
	                                                   return Number($wnd.$("#" + id).slider("values", index));
	                                                   }-*/;

    private native void createSliderJS(Slider widget, String id, JavaScriptObject options) /*-{
	                                                                                   options.start = function(event, ui) {
	                                                                                   widget.@com.brazoft.foundation.gwt.client.ui.Slider::fireOnStartEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
	                                                                                   };
	                                                                                   options.slide = function(event, ui) {
	                                                                                   return widget.@com.brazoft.foundation.gwt.client.ui.Slider::fireOnSlideEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
	                                                                                   };
	                                                                                   options.change = function(event, ui) {
	                                                                                   var has = event.originalEvent ? true : false;
	                                                                                   widget.@com.brazoft.foundation.gwt.client.ui.Slider::fireOnChangeEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;Z)(event, ui.values, has);
	                                                                                   };
	                                                                                   options.stop = function(event, ui) {
	                                                                                   widget.@com.brazoft.foundation.gwt.client.ui.Slider::fireOnStopEvent(Lcom/google/gwt/user/client/Event;Lcom/google/gwt/core/client/JsArrayNumber;)(event, ui.values);
	                                                                                   };

	                                                                                   $wnd.$("#" + id).slider(options);
	                                                                                   }-*/;

    private native void destroySliderJS(Slider widget, String id) /*-{
	                                                          $wnd.$("#" + id).slider("destroy");
	                                                          }-*/;

    enum SliderEvent
	implements EventType {
	CHANGE, SLIDE, START, STOP;
    }

    enum SliderOption {
	DISABLED("disabled"),

	ANIMATE("animate"),

	END("max"),

	START("min"),

	ORIENTATION("orientation"),

	RANGE("range"),

	STEP("step"),

	VALUE("value"),

	VALUES("values");

	private String name;

	private SliderOption(String name) {
	    this.name = name;
	}

	@Override
	public String toString() {
	    return this.name;
	}
    }
}