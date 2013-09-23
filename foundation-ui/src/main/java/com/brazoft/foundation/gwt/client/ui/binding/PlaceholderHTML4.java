package com.brazoft.foundation.gwt.client.ui.binding;

import com.brazoft.foundation.gwt.client.ui.api.Input;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;

public class PlaceholderHTML4
    implements Placeholder {

        private String lastValue   = "";
        private String placeholder = "";

        @Override
        public void apply(final Input<?, String> input, String placeholder) {
                this.placeholder = placeholder;

                // Coloca texto padrão
                input.value(placeholder);
                input.onFocus(new FocusHandler() {

                        @Override
                        public void onFocus(FocusEvent event) {
                                if (input.getValue().equals(PlaceholderHTML4.this.placeholder)) {
                                        input.cursorAt(0);
                                }
                        }
                });
                input.onBlur(new BlurHandler() {
                        @Override
                        public void onBlur(BlurEvent event) {
                                if (input.getValue().isEmpty()) {
                                        input.value(PlaceholderHTML4.this.placeholder);
                                }
                        }
                });
                input.onKeyDown(new KeyDownHandler() {
                        @Override
                        public void onKeyDown(KeyDownEvent event) {
                                // Ignora algumas teclas para evitar efeito desagradável
                                if (event.getNativeKeyCode() != KeyCodes.KEY_ALT
                                        && event.getNativeKeyCode() != KeyCodes.KEY_CTRL
                                        && event.getNativeKeyCode() != KeyCodes.KEY_SHIFT
                                        && event.getNativeKeyCode() != KeyCodes.KEY_PAGEUP
                                        && event.getNativeKeyCode() != KeyCodes.KEY_PAGEDOWN) {
                                    if (input.getValue().isEmpty() || input.getValue().equals(PlaceholderHTML4.this.placeholder)) {
                                        input.value("");
                                    }
                                }
                        }
                });
                input.onKeyUp(new KeyUpHandler() {
                    @Override
                    public void onKeyUp(KeyUpEvent event) {
                        if (input.getValue().isEmpty()) {
                            input.value(PlaceholderHTML4.this.placeholder);
                            input.cursorAt(0);
                        }
                    }
                });
                input.onClick(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        // Se o texto do placeholder, selecione o texto todo
                        if (input.getValue().equals(PlaceholderHTML4.this.placeholder)) {
                            input.cursorAt(0);
                        } else {
                            input.selectAll();
                        }
                    }
                });
        }

        @Override
        public String getValue(Input<?, String> input) {
            String value = ((InputElement) input.getElement().cast()).getValue();
            
            if(this.placeholder.equals(value)){
                value = "";
            }
            
                return value;
        }
}
