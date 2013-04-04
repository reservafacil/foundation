package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.google.gwt.dom.client.SpanElement;
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
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.event.Events;

public class QuickButton extends Component<QuickButton> implements UIButton<QuickButton>
{
	private Paragraph text = new Paragraph();
	
	private HTML<SpanElement> notification = HTML.asSpan().className("notification");
	
	public QuickButton()
	{
		super(ElementResolver.a());
		this.className("quick-button");
		this.add(this.text).add(this.notification);
	}

	@Override
	public QuickButton onFocus(FocusHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onBlur(BlurHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onClick(ClickHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onDoubleClick(DoubleClickHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseDown(MouseDownHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseMove(MouseMoveHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseOut(MouseOutHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseOver(MouseOverHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseUp(MouseUpHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onMouseWheel(MouseWheelHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onKeyPress(KeyPressHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onKeyDown(KeyDownHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton onKeyUp(KeyUpHandler handler)
	{
		return Events.on(this, handler);
	}

	@Override
	public QuickButton size(Size size)
	{
		return Widgets.setSize(this, size);
	}

	@Override
	public QuickButton primary()
	{
		this.notification.className("btn-primary");
		return this;
	}

	@Override
	public QuickButton info()
	{
		this.notification.className("btn-info");
		return this;
	}

	@Override
	public QuickButton success()
	{
		this.notification.className("btn-success");
		return this;
	}

	@Override
	public QuickButton warning()
	{
		this.notification.className("btn-warning");
		return this;
	}

	@Override
	public QuickButton danger()
	{
		this.notification.className("btn-danger");
		return this;
	}

	@Override
	public QuickButton inverse()
	{
		this.notification.className("btn-inverse");
		return this;
	}

	@Override
	public QuickButton link()
	{
		this.notification.className("btn-link");
		return this;
	}

	@Override
	public QuickButton expanded()
	{
		this.notification.className("btn-block");
		return this;
	}

	@Override
	public QuickButton icon(Icon icon)
	{
		Widgets.setIcon(this, icon, true);
		return this;
	}
	
	public QuickButton notification(String notification)
	{
		this.notification.text(notification);
		return this;
	}
	
	@Override
	public QuickButton text(String text)
	{
		this.text.text(text);
		return this;
	}

	@Override
	public String getText()
	{
		return this.text.getText();
	}
}

/*
<a class="quick-button span2">
	<i class="fa-icon-group"></i>
	<p>Users</p>
	<span class="notification">1.367</span>
</a> 
*/
