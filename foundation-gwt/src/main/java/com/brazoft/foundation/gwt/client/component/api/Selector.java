package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.component.Style;
import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Window;

@SuppressWarnings("unchecked")
public class Selector<S extends Selector<S>>
    extends EventSource<S> {

	private GQuery   selector;

	private Style<S> style;

	private String tooltipText;

	public String getTooltipText() {
		return this.tooltipText;
	}

	public S tooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
		return (S)this;
	}

	public S attribute(String name, String value) {
		this.getElement().setAttribute(name, value);
		return (S)this;
	}

	public S removeAttribute(String name) {
		this.getElement().removeAttribute(name);
		return (S)this;
	}

	public String getAttribute(String name) {
		return this.getElement().getAttribute(name);
	}

	public S className(String className) {
		this.addStyleName(className);
		return (S)this;
	}

	public S styleName(String styleName) {
		this.setStyleName(styleName);
		return (S)this;
	}

	public S removeClassName(String className) {
		this.removeStyleName(className);
		return (S)this;
	}

	public S name(String name) {
		return this.attribute("name", name);
	}

	public Style<S> style() {
		if (this.style == null) {
			this.style = Style.<S> create((S)this);
		}

		return this.style;
	}

	public S blur() {
		this.getElement().blur();
		return (S)this;
	}

	public S focus() {
		this.getElement().focus();
		return (S)this;
	}

	public S fadeIn() {
		this.selector().fadeIn();
		return (S)this;
	}

	public S fadeIn(int millisecs) {
		this.selector().fadeIn(millisecs);
		return (S)this;
	}

	public S fadeOut() {
		this.selector().fadeOut();
		return (S)this;
	}

	public S fadeOut(int millisecs) {
		this.selector().fadeOut(millisecs);
		return (S)this;
	}

	public S fadeToggle(int millisecs) {
		this.selector().fadeToggle(millisecs);
		return (S)this;
	}

	public int height() {
		return this.selector().height();
	}

	public int innerHeight() {
		return this.selector().innerHeight();
	}

	public int innerWidth() {
		return this.selector().innerWidth();
	}

	public int left() {
		return this.selector().left();
	}

	public Offset offset() {
		return new Offset(this.selector().offset());
	}

	public S offsetParent() {
		this.selector().offsetParent();
		return (S)this;
	}

	public int outerHeight() {
		return this.selector().outerHeight();
	}

	public int outerHeight(boolean includeMargin) {
		return this.selector().outerHeight(includeMargin);
	}

	public int outerWidth() {
		return this.selector().outerWidth();
	}

	public int outerWidth(boolean includeMargin) {
		return this.selector().outerWidth(includeMargin);
	}

	public Offset position() {
		return new Offset(this.selector().position());
	}

	public S resize() {
		this.selector().resize();
		return (S)this;
	}

	public S scroll() {
		this.selector().scroll();
		return (S)this;
	}

	public S scrollIntoView() {
		this.selector().scrollIntoView();
		return (S)this;
	}

	public S scrollIntoView(boolean ensure) {
		this.selector().scrollIntoView(ensure);
		return (S)this;
	}

	public int scrollLeft() {
		return this.selector().scrollLeft();
	}

	public S scrollLeft(int left) {
		this.selector().scrollLeft(left);
		return (S)this;
	}

	public S scrollTo(int left, int top) {
		this.selector().scrollTo(left, top);
		return (S)this;
	}

	public int scrollTop() {
		return this.selector().scrollTop();
	}

	public S scrollTop(int top) {
		this.selector().scrollTop(top);
		return (S)this;
	}

	public S slice(int start, int end) {
		this.selector().slice(start, end);
		return (S)this;
	}

	public S slideDown() {
		this.selector().slideDown();
		return (S)this;
	}

	public S slideDown(int millisecs) {
		this.selector().slideDown(millisecs);
		return (S)this;
	}

	public S slideToggle(int millisecs) {
		this.selector().slideToggle(millisecs);
		return (S)this;
	}

	public S slideUp() {
		this.selector().slideUp();
		return (S)this;
	}

	public S slideUp(int millisecs) {
		this.selector().slideUp(millisecs);
		return (S)this;
	}

	public int top() {
		return this.selector().top();
	}

	public int width() {
		return this.selector().width();
	}

	public S screenCenter() {
		int width = this.outerWidth();
		int height = this.outerHeight();

		int left = (Window.getClientWidth() - width) >> 1;
		int top = (Window.getClientHeight() - height) >> 1;

		int computedLeft = Math.max(Window.getScrollLeft() + left, 0) - Document.get().getBodyOffsetLeft();
		int computedTop = Math.max(Window.getScrollTop() + top, 0) - Document.get().getBodyOffsetTop();

		Element element = this.getElement();
		element.getStyle().setPropertyPx("left", computedLeft);
		element.getStyle().setPropertyPx("top", computedTop);
		element.getStyle().setPosition(Position.ABSOLUTE);

		return (S)this;
	}

	public double computeInnerLeft() {
		return this.style().computePropertyInt("padding-left")
		        + this.style().computePropertyInt("margin-left")
		        + this.style().computePropertyInt("border-left");
	}

	public double computeInnerRight() {
		return this.style().computePropertyInt("padding-right")
		        + this.style().computePropertyInt("margin-right")
		        + this.style().computePropertyInt("border-right");
	}

	public S asSelector() {
		return (S)this;
	}

	private GQuery selector() {
		if (this.selector == null) {
			this.selector = GQuery.$(this);
		}

		return this.selector;
	}

	public static class Offset {

		private com.google.gwt.query.client.GQuery.Offset wrapped;

		private Offset(com.google.gwt.query.client.GQuery.Offset wrapped) {
			this.wrapped = wrapped;
		}

		public int left() {
			return this.wrapped.left;
		}

		public int top() {
			return this.wrapped.top;
		}
	}
}