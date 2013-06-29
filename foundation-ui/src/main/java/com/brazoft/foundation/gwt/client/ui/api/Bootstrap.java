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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.api.Component;
import com.brazoft.foundation.gwt.client.ui.*;
import com.google.gwt.dom.client.Element;

@SuppressWarnings("unchecked")
public abstract class Bootstrap<B extends Bootstrap<B>>
    extends Component<B> {

    public Bootstrap(Element element) {
	super(element);
    }

    public B muted() {
	return (B)Widgets.muted(this);
    }

    public B align(Alignment alignment) {
	return this.className(alignment.className());
    }

    public B visibleOn(ViewPort... viewPorts) {
	for (ViewPort viewPort : viewPorts) {
	    viewPort.visible(this);
	}

	return (B)this;
    }

    public B hiddenOn(ViewPort... viewPorts) {
	for (ViewPort viewPort : viewPorts) {
	    viewPort.hidden(this);
	}

	return (B)this;
    }
}