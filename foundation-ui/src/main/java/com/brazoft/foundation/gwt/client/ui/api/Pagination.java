package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.event.Event;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.Widgets;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
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
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public abstract class Pagination<P extends Pagination<P>> extends Bootstrap<P>
{
	private HTML<UListElement> list = HTML.as(ElementResolver.ul());
	
	public Pagination()
	{
		super(ElementResolver.div());
		super.add(this.list, true);
	}
	
	public P whenPaginate(EventHandler handler)
	{
		this.addEvent("pager-event", handler);
		
		return (P) this;
	}
	
	public P fire(Event event)
	{
		return this.fireEvent("pager-event", event);
	}
	
	@Override
	protected P detachChildren()
	{
		this.list.detachChildren();
		return (P) this;
	}

	@Override
	protected P remove(Widget child)
	{
		this.list.remove(child);
		return (P) this;
	}

	@Override
	protected P add(Widget add)
	{
		this.list.add(add);
		return (P) this;
	}

	@Override
	protected P add(Widget add, boolean ignoreIfParent)
	{
		this.list.add(add, ignoreIfParent);
		return (P) this;
	}

	@Override
	protected P insert(Widget add, Widget before)
	{
		this.list.insert(add, before);
		return (P) this;
	}

	@Override
	protected Iterable<Widget> getChildren()
	{
		return this.list.getChildren();
	}

	@Override
	protected int getIndex(Widget child)
	{
		return this.list.getIndex(child);
	}

	@Override
	protected int childrenCount()
	{
		return this.list.childrenCount();
	}

	@Override
	protected Item getChild(int index)
	{
		return (Item) this.list.getChild(index);
	}
	
	public static class Item extends Bootstrap<Item> implements HasText<Item>, HasClickHandlers<Item>, HasMouseHandlers<Item>, HasKeyHandlers<Item>, HasFocusHandlers<Item>
	{
		private HTML<? extends Element> link;
		
		public Item()
		{
			this(ElementResolver.li());
		}
		
		public Item(Element e)
		{
			super(e);
			this.link = HTML.as(e);
			this.add(this.link);
		}
		
		public Item icon(Icon icon)
		{
			Widgets.setIcon(this.link, icon);
			return this;
		}
		
		@Override
		public Item onFocus(FocusHandler handler)
		{
			this.link.onFocus(handler);
			return this;
		}

		@Override
		public Item onBlur(BlurHandler handler)
		{
			this.link.onBlur(handler);
			return this;
		}

		@Override
		public Item onKeyPress(KeyPressHandler handler)
		{
			this.link.onKeyPress(handler);
			return this;
		}

		@Override
		public Item onKeyDown(KeyDownHandler handler)
		{
			this.link.onKeyDown(handler);
			return this;
		}

		@Override
		public Item onKeyUp(KeyUpHandler handler)
		{
			this.link.onKeyUp(handler);
			return this;
		}

		@Override
		public Item onMouseDown(MouseDownHandler handler)
		{
			this.link.onMouseDown(handler);
			return this;
		}

		@Override
		public Item onMouseMove(MouseMoveHandler handler)
		{
			this.link.onMouseMove(handler);
			return this;
		}

		@Override
		public Item onMouseOut(MouseOutHandler handler)
		{
			this.link.onMouseOut(handler);
			return this;
		}

		@Override
		public Item onMouseOver(MouseOverHandler handler)
		{
			this.link.onMouseOver(handler);
			return this;
		}

		@Override
		public Item onMouseUp(MouseUpHandler handler)
		{
			this.link.onMouseUp(handler);
			return this;
		}

		@Override
		public Item onMouseWheel(MouseWheelHandler handler)
		{
			this.link.onMouseWheel(handler);
			return this;
		}

		@Override
		public Item onClick(ClickHandler handler)
		{
			this.link.onClick(handler);
			return this;
		}

		@Override
		public Item onDoubleClick(DoubleClickHandler handler)
		{
			this.link.onDoubleClick(handler);
			return this;
		}
		
		public boolean isDisabled()
		{
			return Widgets.isDisabledClass(this);
		}

		public Item disabled()
		{
			return Widgets.disabledClass(this);
		}
		
		public Item activate()
		{
			Widgets.enabledClass(this);
			return this;
		}
		
		@Override
		public Item text(String text)
		{
			this.link.text(text);
			return this;
		}

		@Override
		public String getText()
		{
			return this.link.getText();
		}
	}
	
	public abstract P pages(int pages);
	
	public abstract int getCurrentPage();
	
	protected abstract P previous();
	
	protected abstract P next();
}