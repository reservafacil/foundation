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

package com.brazoft.foundation.gwt.client.component;

import com.brazoft.foundation.gwt.client.component.api.Component;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Clear;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.ListStyleType;
import com.google.gwt.dom.client.Style.OutlineStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TableLayout;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.TextJustify;
import com.google.gwt.dom.client.Style.TextOverflow;
import com.google.gwt.dom.client.Style.TextTransform;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class Style<W extends Widget> {

	private W widget;

	public static <W extends Widget> Style<W> create(W widget) {
		return new Style<W>(widget);
	}

	public static <W extends Widget> Style<W> get(W widget) {
		if (widget instanceof Component) {
			return (Style<W>)((Component<?>)widget).style();
		}

		return Style.create(widget);
	}

	private Style(W widget) {
		this.widget = widget;
	}

	public Style<W> clearBackgroundColor() {
		style().clearBackgroundColor();
		return this;
	}

	public Style<W> clearBackgroundImage() {
		style().clearBackgroundImage();
		return this;
	}

	public Style<W> clearBorderColor() {
		style().clearBorderColor();
		return this;
	}

	public Style<W> clearBorderStyle() {
		style().clearBorderStyle();
		return this;
	}

	public Style<W> clearBorderWidth() {
		style().clearBorderWidth();
		return this;
	}

	public Style<W> clearBottom() {
		style().clearBottom();
		return this;
	}

	public Style<W> clearClear() {
		style().clearClear();
		return this;
	}

	public Style<W> clearColor() {
		style().clearColor();
		return this;
	}

	public Style<W> clearCursor() {
		style().clearCursor();
		return this;
	}

	public Style<W> clearDisplay() {
		style().clearDisplay();
		return this;
	}

	public Style<W> clearFloat() {
		style().clearFloat();
		return this;
	}

	public Style<W> clearFontSize() {
		style().clearFontSize();
		return this;
	}

	public Style<W> clearFontStyle() {
		style().clearFontStyle();
		return this;
	}

	public Style<W> clearFontWeight() {
		style().clearFontWeight();
		return this;
	}

	public Style<W> clearHeight() {
		style().clearHeight();
		return this;
	}

	public Style<W> clearLeft() {
		style().clearLeft();
		return this;
	}

	public Style<W> clearLineHeight() {
		style().clearLineHeight();
		return this;
	}

	public Style<W> clearListStyleType() {
		style().clearListStyleType();
		return this;
	}

	public Style<W> clearMargin() {
		style().clearMargin();
		return this;
	}

	public Style<W> clearMarginBottom() {
		style().clearMarginBottom();
		return this;
	}

	public Style<W> clearMarginLeft() {
		style().clearMarginLeft();
		return this;
	}

	public Style<W> clearMarginRight() {
		style().clearMarginRight();
		return this;
	}

	public Style<W> clearMarginTop() {
		style().clearMarginTop();
		return this;
	}

	public Style<W> clearOpacity() {
		style().clearOpacity();
		return this;
	}

	public Style<W> clearOutlineColor() {
		style().clearOutlineColor();
		return this;
	}

	public Style<W> clearOutlineStyle() {
		style().clearOutlineStyle();
		return this;
	}

	public Style<W> clearOutlineWidth() {
		style().clearOutlineWidth();
		return this;
	}

	public Style<W> clearOverflow() {
		style().clearOverflow();
		return this;
	}

	public Style<W> clearOverflowX() {
		style().clearOverflowX();
		return this;
	}

	public Style<W> clearOverflowY() {
		style().clearOverflowY();
		return this;
	}

	public Style<W> clearPadding() {
		style().clearPadding();
		return this;
	}

	public Style<W> clearPaddingBottom() {
		style().clearPaddingBottom();
		return this;
	}

	public Style<W> clearPaddingLeft() {
		style().clearPaddingLeft();
		return this;
	}

	public Style<W> clearPaddingRight() {
		style().clearPaddingRight();
		return this;
	}

	public Style<W> clearPaddingTop() {
		style().clearPaddingTop();
		return this;
	}

	public Style<W> clearPosition() {
		style().clearPosition();
		return this;
	}

	public Style<W> clearProperty(String name) {
		style().clearProperty(name);
		return this;
	}

	public Style<W> clearRight() {
		style().clearRight();
		return this;
	}

	public Style<W> clearTableLayout() {
		style().clearTableLayout();
		return this;
	}

	public Style<W> clearTextAlign() {
		style().clearTextAlign();
		return this;
	}

	public Style<W> clearTextDecoration() {
		style().clearTextDecoration();
		return this;
	}

	public Style<W> clearTextIndent() {
		style().clearTextIndent();
		return this;
	}

	public Style<W> clearTextJustify() {
		style().clearTextJustify();
		return this;
	}

	public Style<W> clearTextOverflow() {
		style().clearTextOverflow();
		return this;
	}

	public Style<W> clearTextTransform() {
		style().clearTextTransform();
		return this;
	}

	public Style<W> clearTop() {
		style().clearTop();
		return this;
	}

	public Style<W> clearVisibility() {
		style().clearVisibility();
		return this;
	}

	public Style<W> clearWhiteSpace() {
		style().clearWhiteSpace();
		return this;
	}

	public Style<W> clearWidth() {
		style().clearWidth();
		return this;
	}

	public Style<W> clearZIndex() {
		style().clearZIndex();
		return this;
	}

	public String getBackgroundColor() {
		return style().getBackgroundColor();
	}

	public String getBackgroundImage() {
		return style().getBackgroundImage();
	}

	public String getBorderColor() {
		return style().getBorderColor();
	}

	public String getBorderStyle() {
		return style().getBorderStyle();
	}

	public String getBorderWidth() {
		return style().getBorderWidth();
	}

	public String getBottom() {
		return style().getBottom();
	}

	public String getClear() {
		return style().getClear();
	}

	public String getColor() {
		return style().getColor();
	}

	public String getCursor() {
		return style().getCursor();
	}

	public String getDisplay() {
		return style().getDisplay();
	}

	public String getFontSize() {
		return style().getFontSize();
	}

	public String getFontStyle() {
		return style().getFontStyle();
	}

	public String getFontWeight() {
		return style().getFontWeight();
	}

	public String getHeight() {
		return style().getHeight();
	}

	public String getLeft() {
		return style().getLeft();
	}

	public String getLineHeight() {
		return style().getLineHeight();
	}

	public String getListStyleType() {
		return style().getListStyleType();
	}

	public String getMargin() {
		return style().getMargin();
	}

	public String getMarginBottom() {
		return style().getMarginBottom();
	}

	public String getMarginLeft() {
		return style().getMarginLeft();
	}

	public String getMarginRight() {
		return style().getMarginRight();
	}

	public String getMarginTop() {
		return style().getMarginTop();
	}

	public String getOpacity() {
		return style().getOpacity();
	}

	public String getOverflow() {
		return style().getOverflow();
	}

	public String getOverflowX() {
		return style().getOverflowX();
	}

	public String getOverflowY() {
		return style().getOverflowY();
	}

	public String getPadding() {
		return style().getPadding();
	}

	public String getPaddingBottom() {
		return style().getPaddingBottom();
	}

	public String getPaddingLeft() {
		return style().getPaddingLeft();
	}

	public String getPaddingRight() {
		return style().getPaddingRight();
	}

	public String getPaddingTop() {
		return style().getPaddingTop();
	}

	public String getPosition() {
		return style().getPosition();
	}

	public String getProperty(String name) {
		return style().getProperty(name);
	}

	public String getRight() {
		return style().getRight();
	}

	public String getTableLayout() {
		return style().getTableLayout();
	}

	public String getTextAlign() {
		return style().getTextAlign();
	}

	public String getTextDecoration() {
		return style().getTextDecoration();
	}

	public String getTextIndent() {
		return style().getTextIndent();
	}

	public String getTextJustify() {
		return style().getTextJustify();
	}

	public String getTextOverflow() {
		return style().getTextOverflow();
	}

	public String getTextTransform() {
		return style().getTextTransform();
	}

	public String getTop() {
		return style().getTop();
	}

	public String getVerticalAlign() {
		return style().getVerticalAlign();
	}

	public String getVisibility() {
		return style().getVisibility();
	}

	public String getWhiteSpace() {
		return style().getWhiteSpace();
	}

	public String getWidth() {
		return style().getWidth();
	}

	public String getZIndex() {
		return style().getZIndex();
	}

	public Style<W> backgroundColor(String value) {
		style().setBackgroundColor(value);
		return this;
	}

	public Style<W> backgroundImage(String value) {
		style().setBackgroundImage(value);
		return this;
	}

	public Style<W> borderColor(String value) {
		style().setBorderColor(value);
		return this;
	}

	public Style<W> borderStyle(BorderStyle value) {
		style().setBorderStyle(value);
		return this;
	}

	public Style<W> borderWidth(double value, Unit unit) {
		style().setBorderWidth(value, unit);
		return this;
	}

	public Style<W> bottom(double value, Unit unit) {
		style().setBottom(value, unit);
		return this;
	}

	public Style<W> clear(Clear value) {
		style().setClear(value);
		return this;
	}

	public Style<W> color(String value) {
		style().setColor(value);
		return this;
	}

	public Style<W> cursor(Cursor value) {
		style().setCursor(value);
		return this;
	}

	public Style<W> display(Display value) {
		style().setDisplay(value);
		return this;
	}

	public Style<W> floating(Float value) {
		style().setFloat(value);
		return this;
	}

	public Style<W> fontSize(double value, Unit unit) {
		style().setFontSize(value, unit);
		return this;
	}

	public Style<W> fontStyle(FontStyle value) {
		style().setFontStyle(value);
		return this;
	}

	public Style<W> fontWeight(FontWeight value) {
		style().setFontWeight(value);
		return this;
	}

	public Style<W> height(double value, Unit unit) {
		style().setHeight(value, unit);
		return this;
	}

	public Style<W> left(double value, Unit unit) {
		style().setLeft(value, unit);
		return this;
	}

	public Style<W> lineHeight(double value, Unit unit) {
		style().setLineHeight(value, unit);
		return this;
	}

	public Style<W> listStyleType(ListStyleType value) {
		style().setListStyleType(value);
		return this;
	}

	public Style<W> margin(double value, Unit unit) {
		style().setMargin(value, unit);
		return this;
	}

	public Style<W> marginBottom(double value, Unit unit) {
		style().setMarginBottom(value, unit);
		return this;
	}

	public Style<W> marginLeft(double value, Unit unit) {
		style().setMarginLeft(value, unit);
		return this;
	}

	public Style<W> marginRight(double value, Unit unit) {
		style().setMarginRight(value, unit);
		return this;
	}

	public Style<W> marginTop(double value, Unit unit) {
		style().setMarginTop(value, unit);
		return this;
	}

	public Style<W> minHeight(double value, Unit unit) {
		style().setProperty("minHeight", value, unit);
		return this;
	}

	public Style<W> maxHeight(double value, Unit unit) {
		style().setProperty("maxHeight", value, unit);
		return this;
	}

	public Style<W> minWidth(double value, Unit unit) {
		style().setProperty("minWidth", value, unit);
		return this;
	}

	public Style<W> maxWidth(double value, Unit unit) {
		style().setProperty("maxWidth", value, unit);
		return this;
	}

	public Style<W> opacity(double value) {
		style().setOpacity(value);
		return this;
	}

	public Style<W> outlineColor(String value) {
		style().setOutlineColor(value);
		return this;
	}

	public Style<W> outlineStyle(OutlineStyle value) {
		style().setOutlineStyle(value);
		return this;
	}

	public Style<W> outlineWidth(double value, Unit unit) {
		style().setOutlineWidth(value, unit);
		return this;
	}

	public Style<W> overflow(Overflow value) {
		style().setOverflow(value);
		return this;
	}

	public Style<W> overflowX(Overflow value) {
		style().setOverflowX(value);
		return this;
	}

	public Style<W> overflowY(Overflow value) {
		style().setOverflowY(value);
		return this;
	}

	public Style<W> padding(double value, Unit unit) {
		style().setPadding(value, unit);
		return this;
	}

	public Style<W> paddingBottom(double value, Unit unit) {
		style().setPaddingBottom(value, unit);
		return this;
	}

	public Style<W> paddingLeft(double value, Unit unit) {
		style().setPaddingLeft(value, unit);
		return this;
	}

	public Style<W> paddingRight(double value, Unit unit) {
		style().setPaddingRight(value, unit);
		return this;
	}

	public Style<W> paddingTop(double value, Unit unit) {
		style().setPaddingTop(value, unit);
		return this;
	}

	public Style<W> position(Position value) {
		style().setPosition(value);
		return this;
	}

	public Style<W> right(double value, Unit unit) {
		style().setRight(value, unit);
		return this;
	}

	public Style<W> tableLayout(TableLayout value) {
		style().setTableLayout(value);
		return this;
	}

	public Style<W> textAlign(TextAlign value) {
		style().setTextAlign(value);
		return this;
	}

	public Style<W> textDecoration(TextDecoration value) {
		style().setTextDecoration(value);
		return this;
	}

	public Style<W> textIndent(double value, Unit unit) {
		style().setTextIndent(value, unit);
		return this;
	}

	public Style<W> textJustify(TextJustify value) {
		style().setTextJustify(value);
		return this;
	}

	public Style<W> textOverflow(TextOverflow value) {
		style().setTextOverflow(value);
		return this;
	}

	public Style<W> textTransform(TextTransform value) {
		style().setTextTransform(value);
		return this;
	}

	public Style<W> top(double value, Unit unit) {
		style().setTop(value, unit);
		return this;
	}

	public Style<W> verticalAlign(VerticalAlign value) {
		style().setVerticalAlign(value);
		return this;
	}

	public Style<W> verticalAlign(double value, Unit unit) {
		style().setVerticalAlign(value, unit);
		return this;
	}

	public Style<W> visibility(Visibility value) {
		style().setVisibility(value);
		return this;
	}

	public Style<W> whiteSpace(WhiteSpace value) {
		style().setWhiteSpace(value);
		return this;
	}

	public Style<W> width(double value, Unit unit) {
		style().setWidth(value, unit);
		return this;
	}

	public Style<W> property(String style, String value) {
		style().setProperty(style, value);
		return this;
	}

	public Style<W> zIndex(int value) {
		style().setZIndex(value);
		return this;
	}

	public int computePropertyInt(String name) {
		String value = this.computeProperty(this.widget.getElement().getId(), name);

		if (value == null || value.trim().isEmpty() || value.contains("auto")) {
			return 0;
		}

		for (Unit unit : Unit.values()) {
			value = value.replace(unit.getType(), "");
		}

		if (value.contains(" ")) {
			value = value.split(" ")[0];
		}

		return Integer.valueOf(value);
	}

	public String computeProperty(String name) {
		return this.computeProperty(this.widget.getElement().getId(), name);
	}

	native String computeProperty(String id, String name)/*-{
		return $wnd.$("#" + id).css(name);
	}-*/;

	private com.google.gwt.dom.client.Style style() {
		return this.widget.getElement().getStyle();
	}
}