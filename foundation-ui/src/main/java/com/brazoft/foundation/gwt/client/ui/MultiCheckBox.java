package com.brazoft.foundation.gwt.client.ui;

import java.util.ArrayList;

import com.brazoft.foundation.gwt.client.ui.api.WidgetGroup;
import com.google.gwt.user.client.ui.Widget;

public final class MultiCheckBox
    extends WidgetGroup<MultiCheckBox, String[]> {

	public MultiCheckBox(Orientation orientation) {
		super(orientation);
	}

	@Override
	public MultiCheckBox clear() {
		return this.uncheckAll();
	}

	@Override
	public MultiCheckBox value(String[] values) {
		for (Widget child : getChildren()) {
			CheckBox check = this.checkbox(child);
			for (String value : values) {
				check.checked(check.getValue().equals(value));
			}
		}

		return this;
	}

	@Override
	public String[] getValue() {
		java.util.List<String> values = new ArrayList<String>();

		for (Widget child : getChildren()) {
			CheckBox radio = this.checkbox(child);
			if (radio.isChecked()) {
				values.add(radio.getValue());
			}
		}

		return values.toArray(new String[values.size()]);
	}
	
	public MultiCheckBox item(Image image, String value) {
		return this.item(image.toString(), value);
	}

	@Override
	public MultiCheckBox item(String text, String value) {
		CheckBox input = new CheckBox().value(value);

		return this.input(input, text);
	}
	
	public MultiCheckBox checkAll() {
		return this.check(true);
	}
	
	public MultiCheckBox uncheckAll() {
		return this.check(false);
	}
	
	public MultiCheckBox uncheck(String value) {
		return this.check(value, false);
	}
	
	public MultiCheckBox check(String value){
		return this.check(value, true);
	}
	
	public MultiCheckBox check(String value, boolean check){
		for (Widget child : getChildren()) {
			CheckBox checkbox = this.checkbox(child);
			if(checkbox.getValue().equals(value)) {
				checkbox.checked(check);
				break;
			}
		}
		
		return this;
	}
	
	private MultiCheckBox check(boolean check){
		for (Widget child : getChildren()) {
			CheckBox checkbox = this.checkbox(child);
			checkbox.checked(check);
		}
		
		return this;
	}

	private CheckBox checkbox(Widget child) {
		Label label = (Label)child;
		return (CheckBox)label.getInput();
	}
}