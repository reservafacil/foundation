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

package com.brazoft.foundation.gwt.client.component.api;

import com.brazoft.foundation.gwt.client.event.api.*;

@SuppressWarnings("unchecked")
public abstract class Composite<C extends Composite<C>>
    extends Selector<C>
    implements HasVisibility<C> {

	public Composite() {
		super();
	}

	public C detach() {
		this.removeFromParent();
		return (C)this;
	}

	public C onAttach(AttachHandler handler) {
		this.addAttachHandler(handler);
		return (C)this;
	}

	public C onDetach(DetachHandler handler) {
		this.addAttachHandler(handler);
		return (C)this;
	}

	public C onHidden(EventHandler<Void> handler) {
		this.addHandler(HasVisibility.TriggerEvent.HIDDEN, handler);
		return (C)this;
	}

	public C onVisible(EventHandler<Void> handler) {
		this.addHandler(HasVisibility.TriggerEvent.VISIBLE, handler);
		return (C)this;
	}

	public C title(String title) {
		this.getElement().setTitle(title);
		return (C)this;
	}

	public C visible() {
		this.fireEvent(HasVisibility.TriggerEvent.VISIBLE);
		this.setVisible(true);
		return (C)this;
	}

	public C hidden() {
		this.fireEvent(HasVisibility.TriggerEvent.HIDDEN);
		this.setVisible(false);
		return (C)this;
	}
	
	public C toggleVisibility() {
		if(this.isVisible()){
			return this.hidden();
		}
		
		return this.visible();
	}

	public String getId() {
		return this.getElement().getId();
	}

	public C id(String id) {
		this.getElement().setId(id);
		return (C)this;
	}
}