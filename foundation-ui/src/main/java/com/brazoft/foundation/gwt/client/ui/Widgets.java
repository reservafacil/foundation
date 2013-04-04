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

package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.ui.api.UIButton;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.HTML;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class Widgets
{
	public static <W extends Widget> W muted(W widget)
	{
		widget.addStyleName("muted");
		return widget;
	}
	
	public static <W extends Widget> W setIcon(W widget, Icon icon)
	{
		return setIcon(widget, icon, false);
	}
	
	public static <W extends Widget> W setIcon(W widget, Icon icon, boolean insert)
	{
		Element element = widget.getElement();
		String className = element.getClassName();
		
		MatchResult matcher = RegExp.compile("primary|info|success|warning|danger|inverse").exec(className);
		boolean white = matcher != null && matcher.getGroupCount() > 0;
				
		Element i = ElementResolver.getElementByTagName(element, "i");
		if(i == null)
		{
			i = ElementResolver.create("i");

			if(insert)
			{
				widget.getElement().insertFirst(i);
			}
			else
			{
				widget.getElement().appendChild(i);
			}
		}
		
		i.setClassName(icon.className(white));
		
		return widget;
	}
	
	public static <W extends Widget> W scrollSpy(W widget)
	{
		BodyElement body = ElementResolver.body();
		body.setAttribute("data-spy", "scroll");
		body.setAttribute("data-target", widget.getStyleName());
		
		return widget;
	}
	
	static <B extends UIButton<? extends Widget>> B setSize(B button, Size size)
	{
		switch (size)
		{
			case LARGE:
				return (B) button.className("btn-large");
			case SMALL:
				return (B) button.className("btn-small");
			case MINI:
				return (B) button.className("btn-mini");
			default:
				break;
		}
		
		return (B) button;
	}

	static <B extends UIButton<? extends Widget>> B setPrimary(B button)
	{
		return (B) button.className("btn-primary");
	}
	
	static <B extends UIButton<? extends Widget>> B setInfo(B button)
	{
		return (B) button.className("btn-info");
	}
	
	static <B extends UIButton<? extends Widget>> B setSuccess(B button)
	{
		return (B) button.className("btn-success"); 
	}
	
	static <B extends UIButton<? extends Widget>> B setWarning(B button)
	{
		return (B) button.className("btn-warning"); 
	}
	
	static <B extends UIButton<? extends Widget>> B setDanger(B button)
	{
		return (B) button.className("btn-danger");
	}
	
	static <B extends UIButton<? extends Widget>> B setInverse(B button)
	{
		return (B) button.className("btn-inverse");
	}
	
	static <B extends UIButton<? extends Widget>> B setLink(B button)
	{
		return (B) button.className("btn-link");
	}

	static <B extends UIButton<? extends Widget>> B setExpanded(B button)
	{
		return (B) button.className("btn-block");
	}
	
	static void adaptPlaceholder(Input<?, ?> input, String placeholder, String className)
	{
		Widget parent = input.getParent();
		
		if(!parent.getElement().hasTagName("label"))
		{
			HTML<LabelElement> label = HTML.asLabel().text(placeholder).className(className);
			DOM.appendChild(parent.getElement(), label.getElement());
			label.setParent(parent);
			label.add(input);
			return;
		}
		
		parent.getElement().setInnerText(placeholder);
		parent.addStyleName(className);
	}
	
	static <W extends Widget> W activateClass(W widget)
	{
		widget.addStyleName("active");
		
		return widget;
	}
	
	static <W extends Widget> W deactivateClass(W widget)
	{
		widget.removeStyleName("active");
		
		return widget;
	}
	
	static boolean isDisabledClass(Widget widget)
	{
		return widget.getStyleName().contains("disabled");
	}
	
	static <W extends Widget> W disabledClass(W widget)
	{
		widget.addStyleName("disabled");
		
		return widget;
	}
	
	static <W extends Widget> W enabledClass(W widget)
	{
		widget.removeStyleName("disabled");
		
		return widget;
	}
}