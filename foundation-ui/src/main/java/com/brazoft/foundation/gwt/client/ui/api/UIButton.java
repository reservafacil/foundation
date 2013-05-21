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

package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.ui.Icon;
import com.brazoft.foundation.gwt.client.ui.Size;
import com.brazoft.foundation.gwt.client.component.api.HasText;
import com.brazoft.foundation.gwt.client.component.api.UIComponent;
import com.brazoft.foundation.gwt.client.event.api.HasClickHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasKeyHandlers;
import com.brazoft.foundation.gwt.client.event.api.HasMouseHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface UIButton<W extends Widget>
    extends HasText<W>, HasClickHandlers<W>, HasFocusHandlers<W>, HasMouseHandlers<W>, HasKeyHandlers<W>, UIComponent<W> {

    W size(Size size);

    W primary();

    W info();

    W success();

    W warning();

    W danger();

    W inverse();

    W link();

    W expanded();

    W icon(Icon icon);
}