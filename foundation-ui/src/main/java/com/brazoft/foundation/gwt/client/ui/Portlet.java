package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.component.api.ResponsiveComponent;
import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.ui.Widget;

public class Portlet extends Component<Portlet>
{
	private Header header = new Header();
	
	private Body body = new Body();
	
	public Portlet()
	{
		super(ElementResolver.div());
		this.className("portlet").add(this.header).add(this.body);
	}
	
	public Header header()
	{
		return this.header;
	}
	
	public Body body()
	{
		return this.body;
	}
	
	public class Header extends Component<Header> implements HasText<Header>
	{
		private HTML<SpanElement> icon = HTML.asSpan().className("icon");
		
		private Heading title = new Heading(5);
		
		private Toolbar toolbar;
		
		Header()
		{
			super(ElementResolver.div());
			this.className("portlet-header");
			this.add(this.icon).add(this.title).icon(Icon.NONE);
		}
		
		public Header icon(Icon icon)
		{
			Widgets.setIcon(this.icon, icon);
			return this;
		}
		
		public Header text(String title)
		{
			this.title.text(title);
			return this;
		}
		
		@Override
		public String getText()
		{
			return this.title.getText();
		}
		
		public Toolbar toolbar()
		{
			if(this.toolbar == null)
			{
				this.toolbar = new Toolbar();
				this.add(this.toolbar);
			}
			
			return this.toolbar;
		}
		
		public class Toolbar extends Component<Toolbar>
		{
			public Toolbar()
			{
				super(ElementResolver.div());
				this.className("toolbar");
			}
			
			public Toolbar button(UIButton<?> button)
			{
				return this.add(button.asWidget());
			}
		}
	}
	
	public class Body extends Component<Body>
	{
		Body()
		{
			super(ElementResolver.div());
			this.className("portlet-body");
		}
		
		public Body add(Widget widget)
		{
			return super.add(widget);
		}
		
		public Row row()
		{
			Row row = new Row();
			
			this.add(row);
			
			return row;
		}
		
		public class Row extends Component<Row>
		{
			public Row()
			{
				super(ElementResolver.div());
				this.className("portlet-row clearfix");
			}
			
			@Override
			public Row add(Widget child)
			{
				if(child instanceof ResponsiveComponent)
				{
					((ResponsiveComponent<?>) child).responsiveTo(this);
				}
				
				return super.add(child);
			}
		}
	}
}