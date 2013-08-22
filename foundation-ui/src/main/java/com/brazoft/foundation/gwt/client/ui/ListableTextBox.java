package com.brazoft.foundation.gwt.client.ui;

import java.util.*;
import java.util.List;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.*;
import com.brazoft.foundation.gwt.client.util.Entry;
import com.google.gwt.event.dom.client.*;

public class ListableTextBox
    extends Component<ListableTextBox>
    implements HasValues<ListableTextBox, Entry> {

	private TextBox       input     = new TextBox().block();

	private Button        addBtn    = new Button().icon(Icon.PLUS);

	private Button        removeBtn = new Button().danger().icon(Icon.MINUS).size(Size.MINI);

	private MultiCheckBox items     = new MultiCheckBox(Orientation.VERTICAL);

	public ListableTextBox() {
		super(ElementResolver.div());
		this.init();
	}

	private void init() {
		this.items.onChange(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				ListableTextBox.this.change();
			}
		});
		
		this.addBtn.onClick(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String value = ListableTextBox.this.input.getValue();
				if (!Validator.isEmptyOrNull(value)) {
					Entry entry = Entry.create().key(value).value(value);
					ListableTextBox.this.add(entry);
				}
			}
		});
		
		this.removeBtn.onClick(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ListableTextBox.this.remove();
			}
		}).hidden();

		this.input.append(this.addBtn);
		this.add(this.input).add(this.items).add(this.removeBtn);
	}
	
	private void change() {
		if(this.items.childrenCount() == this.items.getValue().length) {
			this.removeBtn.hidden();
			return;
		}
		
		this.removeBtn.visible();
	}
	
	private void remove(){
		Entry[] values = this.getValue();
		this.items.detachChildren();
		this.value(values);
		if(!this.items.hasChildren()) {
			this.removeBtn.hidden();
		}
	}
	
	public TextBox input() {
		return this.input;
	}
	
	public ListableTextBox removeText(String text){
		this.removeBtn.text(text);
		return this;
	}

	public ListableTextBox add(Entry entry) {
		for(String value : this.items.getValue()) {
			if(value.equals(entry.getKey())) {
				return this;
			}
		}
		
		this.change();
		this.items.item(entry.getValue(), entry.getKey()).check(entry.getKey());
		
		return this;
	}

	@Override
	public ListableTextBox value(Entry[] values) {
		for (Entry entry : values) {
			this.add(entry);
		}

		return this;
	}

	@Override
	public Entry[] getValue() {
		List<Entry> values = new ArrayList<Entry>();

		for (String value : this.items.getValue()) {
			values.add(Entry.create().key(value).value(value));
		}

		return values.toArray(new Entry[values.size()]);
	}
}