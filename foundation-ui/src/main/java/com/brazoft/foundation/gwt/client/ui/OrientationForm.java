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

package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.component.api.Composite;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row;

@SuppressWarnings("unchecked")
public abstract class OrientationForm<O extends OrientationForm<O>>
    extends Composite<O> {

    private Table table = new Table();

    public OrientationForm() {
	this.initWidget(this.table);
    }

    public <V> O item(InputGroup<V> group) {
	Row row = this.row();
	group.getLabel().detach().style().clearMarginBottom();
	row.cell().verticalAlign(VerticalAlignment.MIDDLE).add(group.getLabel());
	row.cell().add(group);

	return (O)this;
    }

    protected Table table() {
	return this.table;
    }

    protected abstract Row row();
}