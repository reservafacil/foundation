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

import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.ui.api.NativeEvent;
import com.google.gwt.user.client.ui.Widget;

public final class Alert
    extends NativeEvent<Alert> {

    private Button dismiss = new Button().className("close").attribute("data-dismiss", "alert").text("×");

    public Alert(AlertOptions option) {
	super(ElementResolver.div());
	this.init(option);
    }

    private void init(AlertOptions option) {
	this.className("alert fade in");
	super.add(dismiss);

	switch (option) {
	    case ERROR:
		this.className("alert-error");
		break;
	    case INFO:
		this.className("alert-info");
		break;
	    case SUCCESS:
		this.className("alert-success");
		break;
	    default:
		break;
	}
    }

    public Alert onClose(EventHandler event) {
	return this.addEvent("close", event);
    }

    public Alert whenClosed(EventHandler event) {
	return this.addEvent("closed", event);
    }

    public Alert block() {
	return this.className("alert-block");
    }

    @Override
    public Alert add(Widget add) {
	if (add instanceof Paragraph) {
	    return super.add(add);
	}

	return super.add(new Paragraph().add(add));
    }

    @Override
    protected void registerNativeEvent(Iterable<String> types) {
	for (String type : types) {
	    this.registerEvent(this, type, this.getId());
	}
    }

    private native void registerEvent(Alert widget, String type, String id) /*-{
	                                                                    $wnd.$("#" + id).on(type, function () {
	                                                                    widget.@com.brazoft.foundation.gwt.client.component.api.Component::fireEvent(Ljava/lang/String;)(type);
	                                                                    });
	                                                                    }-*/;

    public enum AlertOptions {
	ERROR, INFO, SUCCESS, WARNING;
    }
}
