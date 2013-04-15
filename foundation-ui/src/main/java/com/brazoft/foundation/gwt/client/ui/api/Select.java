/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.NodeIterable;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.component.api.HasValue;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.event.Events;
import com.brazoft.foundation.gwt.client.event.api.AttachHandler;
import com.brazoft.foundation.gwt.client.event.api.HasChangeHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.json.JSON;
import com.brazoft.foundation.gwt.client.json.JSONObject;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class Select<S extends Select<S, V>, V> extends Bootstrap<S> implements UIInput<S, V>, HasFocusHandlers<S>, HasChangeHandlers<S>, HasKeyHandlers<S>, HasMouseHandlers<S>//, ResponsiveComponent<S>
{
	private JSONObject options = JSON.asObject();
	
	private boolean required;
	
	public Select(boolean multiple)
	{
		super(ElementResolver.select(multiple));
		this.placeholder("");
		this.onAttach(new AttachHandler()
		{
			@Override
			protected void onAttach(AttachEvent event)
			{
				initJS(getId(), options.getJavaScriptObject());
			}
		});
	}
	
	public S block()
	{
		this.style().width(100, Unit.PCT);
		return (S) this;
	}
	
	@Override
	public S onMouseDown(MouseDownHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onMouseMove(MouseMoveHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onMouseOut(MouseOutHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onMouseOver(MouseOverHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onMouseUp(MouseUpHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onMouseWheel(MouseWheelHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onKeyPress(KeyPressHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onKeyDown(KeyDownHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onKeyUp(KeyUpHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onChange(ChangeHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onFocus(FocusHandler handler)
	{
		return Events.on((S) this, handler);
	}

	@Override
	public S onBlur(BlurHandler handler)
	{
		return Events.on((S) this, handler);
	}
	
//	@Override
//	public S adaptSize(Widget container)
//	{
//		double parentWidth = Component.Util.innerWidth(container);
//		this.style().width(parentWidth, Unit.PX);
//		
//		return (S) this;
//	}
//	
//	@Override
//	public S responsiveTo(Widget container)
//	{
//		return Component.Util.responsiveBehavior(this, container);
//	}
	
	public S open()
	{
		this.doIt(this.getId(), "open");
		return (S) this;
	}
	
	public S close()
	{
		this.doIt(this.getId(), "close");
		return (S) this;
	}
	
	public S openOnEnter(boolean open)
	{
		this.options.put("openOnEnter", open);
		return this.update();
	}
	
	public S allowClear(boolean allow)
	{
		this.options.put("allowClear", allow);
		return this.update();
	}
	
	public S minimumInputLength(int length)
	{
		this.options.put("minimumInputLength", length);
		return this.update();
	}
	
	public S maximumInputLength(int length)
	{
		this.options.put("maximumInputLength", length);
		return this.update();
	}
	
	protected JSONObject getOptions()
	{
		return this.options;
	}

	public S select(int index)
	{
		this.element().getOptions().getItem(index).setSelected(true);
		
		return (S) this;
	}
	
	public S deselect(int index)
	{
		this.element().getOptions().getItem(index).setSelected(false);
		
		return (S) this;
	}
	
	public S item(String value)
	{
		return this.item(value, value);
	}
	
	public S item(String text, String value)
	{
		Option option = new Option().text(text).value(value);
		
		this.add(option);
		
		return (S) this;
	}
	
	@Override
	public S placeholder(String placeholder)
	{
		this.attribute("data-placeholder", placeholder);
		return (S) this;
	}
	
	public S span(int span)
	{
		return this.className("span" + span);
	}

	@Override
	public boolean isReadOnly()
	{
		return !this.isEditable();
	}
	
	@Override
	public S readonly()
	{
		this.element().setDisabled(true);
		this.update();
		return (S) this;
	}
	
	@Override
	public boolean isEditable()
	{
		return !this.element().isDisabled();
	}

	@Override
	public S editable()
	{
		this.element().setDisabled(false);
		this.update();
		return (S) this;
	}
	
	@Override
	public boolean isNullable()
	{
		return !this.isRequired();
	}
	
	public S nullable()
	{
		this.required = false;
		return (S) this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.required;
	}
	
	public S required()
	{
		this.required = true;
		return (S) this;
	}
	
	protected NodeIterable<OptionElement> options()
	{
		return new NodeIterable<OptionElement>(this.element().getOptions());
	}
	
	protected SelectElement element()
	{
		return this.getElement().cast();
	}
	
	public S update()
	{
		this.update(this.getId());
		return (S) this;
	}
	
	class Option extends Widget implements HasText<Option>, HasValue<Option, String>
	{
		public Option()
		{
			this.setElement(ElementResolver.option());
		}
		
		OptionElement element()
		{
			return this.getElement().cast();
		}
		
		public boolean isSelected()
		{
			return this.element().isSelected();
		}

		@Override
		public Option value(String value)
		{
			this.element().setValue(value);
			return this;
		}
		
		@Override
		public String getValue()
		{
			return this.element().getValue();
		}

		@Override
		public Option text(String text)
		{
			return Component.Util.setHTML(this, text);
		}

		@Override
		public String getText()
		{
			return Component.Util.getHTML(this);
		}
	}
	
	protected native String getSelection(String id) /*-{
		return $wnd.$("#" + id).select2("val");
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
}