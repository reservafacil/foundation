/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brazoft.foundation.gwt.client.ui;

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.json.JSON;
import com.brazoft.foundation.gwt.client.json.JSONCollection;
import com.brazoft.foundation.gwt.client.ui.api.Select;

public final class ListBox
    extends Select<ListBox, String[]> {

    public ListBox() {
	super(true);
    }

    public ListBox closeOnSelect(boolean close) {
	this.getOptions().put("closeOnSelect", close);
	return this.update();
    }

    public ListBox maximumSelectionSize(int size) {
	this.getOptions().put("maximumSelectionSize", size);
	return this.update();
    }

    @Override
    public ListBox value(String... value) {
	return this.select(value);
    }

    protected ListBox select(String... values) {
	JSONCollection<String> selection = JSON.asStringCollection();

	for (String value : values) {
	    selection.add(value);
	}

	this.select(this.getId(), selection.toString());

	return this;
    }

    @Override
    public String[] getValue() {
	java.util.List<String> values = new ArrayList<String>();
	JSONCollection<String> jsonValues = JSON.asStringCollection(this.getSelection(this.getId()));

	for (String value : jsonValues) {
	    values.add(value);
	}

	return values.toArray(new String[values.size()]);
    }
}