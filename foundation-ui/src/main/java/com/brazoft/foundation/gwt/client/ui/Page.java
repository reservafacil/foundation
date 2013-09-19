package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.event.api.*;
import com.brazoft.foundation.gwt.client.ui.*;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Widget;

public class Page<P extends Page<P>>
    extends Component<P> {

    private PageHeader header = new PageHeader();

    private PageBody   body   = new PageBody();

    private PageFooter footer = new PageFooter();

    public Page() {
	super(ElementResolver.div());
	this.setup();
    }

    private void setup() {
	this.add(this.header).add(this.body).add(this.footer);
    }

    public P onClose(EventHandler<Void> handler) {
	return (P) this.closeable().addHandler(FireableEvent.CLOSE, handler);
    }

    public P closeable() {
	this.header.closeButton.visible();
	this.header.closeButton.onClick(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		Page.this.close();
	    }
	});
	
	return (P) this;
    }
    
    public PageHeader header() {
	return header;
    }

    public PageBody body() {
	return body;
    }
    
    public PageFooter footer() {
	return footer;
    }
    
    P close(){
	return this.hidden().fireEvent(FireableEvent.CLOSE);
    }
    
    public static class PageHeader
	extends Bootstrap<PageHeader>
	implements HasText<PageHeader> {

	private Heading heading     = new Heading(3);

	private Button  closeButton = new Button().className("close").text("×").hidden();

	public PageHeader() {
	    super(ElementResolver.div());
	    this.className("page-header");
	    this.add(this.closeButton).add(this.heading);
	}

	@Override
	public String getText() {
	    return this.heading.getText();
	}

	@Override
	public PageHeader text(String text) {
	    Component.Util.setHTML(this.heading, text);
	    return this;
	}
    }

    public static class PageBody
	extends Bootstrap<PageBody> {

	public PageBody() {
	    super(ElementResolver.div());
	    this.className("page-body");
	}

	@Override
	public PageBody detachChildren() {
	    return super.detachChildren();
	}

	@Override
	public PageBody add(Widget add) {
	    return super.add(add);
	}
    }

    public static class PageFooter
	extends Bootstrap<PageFooter> {

	public PageFooter() {
	    super(ElementResolver.div());
	    this.className("page-footer");
	}

	@Override
	public PageFooter detachChildren() {
	    return super.detachChildren();
	}

	@Override
	public PageFooter add(Widget add) {
	    return super.add(add);
	}
    }

    enum FireableEvent
	implements EventType {
	CLOSE;

	public String method() {
	    return this.name().toLowerCase();
	}
    }
}