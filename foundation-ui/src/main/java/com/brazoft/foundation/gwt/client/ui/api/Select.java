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
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
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
public abstract class Select<W extends Widget, V> extends Bootstrap<W> implements UIInput<W, V>, HasFocusHandlers<W>, HasChangeHandlers<W>, HasKeyHandlers<W>, HasMouseHandlers<W>//, ResponsiveComponent<W>
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
	
	public W block()
	{
		this.style().width(100, Unit.PCT);
		return (W) this;
	}
	
	@Override
	public W onMouseDown(MouseDownHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onMouseMove(MouseMoveHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onMouseOut(MouseOutHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onMouseOver(MouseOverHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onMouseUp(MouseUpHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onMouseWheel(MouseWheelHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onKeyPress(KeyPressHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onKeyDown(KeyDownHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onKeyUp(KeyUpHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onChange(ChangeHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onFocus(FocusHandler handler)
	{
		return Events.on((W) this, handler);
	}

	@Override
	public W onBlur(BlurHandler handler)
	{
		return Events.on((W) this, handler);
	}
	
	public W open()
	{
		this.doIt(this.getId(), "open");
		return (W) this;
	}
	
	public W close()
	{
		this.doIt(this.getId(), "close");
		return (W) this;
	}
	
	public W openOnEnter(boolean open)
	{
		this.options.put("openOnEnter", open);
		return this.update();
	}
	
	public W allowClear(boolean allow)
	{
		this.options.put("allowClear", allow);
		return this.update();
	}
	
	public W minimumInputLength(int length)
	{
		this.options.put("minimumInputLength", length);
		return this.update();
	}
	
	public W maximumInputLength(int length)
	{
		this.options.put("maximumInputLength", length);
		return this.update();
	}
	
	
	
	protected JSONObject getOptions()
	{
		return this.options;
	}

	public W select(int index)
	{
		this.element().getOptions().getItem(index).setSelected(true);
		
		return (W) this;
	}
	
	public W deselect(int index)
	{
		this.element().getOptions().getItem(index).setSelected(false);
		
		return (W) this;
	}
	
	public W item(String value)
	{
		return this.item(value, value);
	}
	
	public W item(String text, String value)
	{
		Option option = new Option().text(text).value(value);
		
		this.add(option);
		
		return (W) this;
	}
	
	@Override
	public W placeholder(String placeholder)
	{
		this.attribute("data-placeholder", placeholder);
		return (W) this;
	}
	
	public W span(int span)
	{
		return this.className("span" + span);
	}

	protected W select(String... values)
	{
		for(OptionElement option : this.options())
		{
			for(String value : values)
			{
				option.setSelected(option.getValue().equals(value));
			}
		}
		
		return (W) this;
	}
	
	@Override
	public boolean isReadOnly()
	{
		return !this.isEditable();
	}
	
	@Override
	public W readonly()
	{
		this.element().setDisabled(true);
		return (W) this;
	}
	
	@Override
	public boolean isEditable()
	{
		return !this.element().isDisabled();
	}

	@Override
	public W editable()
	{
		this.element().setDisabled(false);
		return (W) this;
	}
	
	@Override
	public boolean isNullable()
	{
		return !this.isRequired();
	}
	
	public W nullable()
	{
		this.required = false;
		return (W) this;
	}
	
	@Override
	public boolean isRequired()
	{
		return this.required;
	}
	
	public W required()
	{
		this.required = true;
		return (W) this;
	}
	
	protected NodeIterable<OptionElement> options()
	{
		return new NodeIterable<OptionElement>(this.element().getOptions());
	}
	
	protected SelectElement element()
	{
		return this.getElement().cast();
	}
	
	public W update()
	{
		this.update(this.getId());
		return (W) this;
	}
	
//	@Override
//	public W adaptSize(Widget container)
//	{
//		double width = Component.Util.innerWidth(container);
//		this.style().width(width, Unit.PX);
//		
//		return (W) this;
//	}
//	
//	@Override
//	public W responsiveTo(Widget container)
//	{
//		return Component.Util.responsiveBehavior(this, container);
//	}

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