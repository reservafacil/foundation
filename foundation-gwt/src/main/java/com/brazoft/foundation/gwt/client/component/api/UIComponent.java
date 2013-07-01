package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.component.Style;
import com.google.gwt.user.client.ui.*;

public interface UIComponent<W extends Widget>
    extends IsWidget {

	W attribute(String name, String value);

	String getAttribute(String name);

	W className(String className);

	W removeClassName(String className);

	String getId();

	W id(String id);

	W name(String name);

	Style<W> style();

	W title(String title);

	W visible();

	W hidden();

	void removeFromParent();

	Selector<?> asSelector();
}