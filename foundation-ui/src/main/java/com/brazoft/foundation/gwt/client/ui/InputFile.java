package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.gwt.client.component.ElementResolver;
import com.brazoft.foundation.gwt.client.component.api.UIInput;
import com.brazoft.foundation.gwt.client.ui.api.Bootstrap;
import com.brazoft.foundation.gwt.client.ui.api.Input;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class InputFile
    extends Bootstrap<InputFile>
    implements UIInput<InputFile, String> {

    private File    file   = new File();

    private TextBox input  = new TextBox();

    private Button  submit = new Button();

    private Button  reset  = new Button().danger().icon(Icon.REMOVE).hidden();

    public InputFile() {
	super(ElementResolver.div());
	this.init();
    }

    private void init() {
	this.add(this.file).add(this.input);
	this.input.append(this.submit).append(this.reset);
	this.info().icon(Icon.UPLOAD);
	this.submit.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		file.open();
	    }
	});

	this.reset.onClick(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		clear();
	    }
	});

	this.file.onChange(new ChangeHandler() {

	    @Override
	    public void onChange(ChangeEvent event) {
		input.value(file.getValue());
		reset.setVisible(!Validator.isEmptyOrNull(file.getValue()));
	    }
	});
    }

    public InputFile submitText(String text) {
	this.submit.text(text);
	return this;
    }

    public InputFile resetText(String text) {
	this.reset.text(text);
	return this;
    }

    @Override
    public InputFile value(String value) {
	this.input.value(value);
	return this;
    }

    @Override
    public String getValue() {
	return this.input.getValue();
    }

    @Override
    public InputFile clear() {
	this.input.clear();
	this.reset.hidden();
	return this;
    }

    public InputFile primary() {
	this.submit.primary();
	return this;
    }

    public InputFile info() {
	this.submit.info();
	return this;
    }

    public InputFile success() {
	this.submit.success();
	return this;
    }

    public InputFile warning() {
	this.submit.warning();
	return this;
    }

    public InputFile danger() {
	this.submit.danger();
	return this;
    }

    public InputFile inverse() {
	this.submit.inverse();
	return this;
    }

    public InputFile link() {
	this.submit.link();
	return this;
    }

    public InputFile icon(Icon icon) {
	this.submit.icon(icon);
	return this;
    }

    @Override
    public InputFile placeholder(String placeholder) {
	this.input.placeholder(placeholder);
	return this;
    }

    @Override
    public boolean isReadOnly() {
	return this.input.isReadOnly();
    }

    @Override
    public InputFile readonly() {
	this.input.readonly();
	return this;
    }

    @Override
    public boolean isEditable() {
	return this.input.isEditable();
    }

    @Override
    public InputFile editable() {
	this.input.editable();
	return this;
    }

    @Override
    public boolean isNullable() {
	return this.input.isNullable();
    }

    @Override
    public InputFile nullable() {
	this.input.nullable();
	return this;
    }

    @Override
    public boolean isRequired() {
	return this.input.isRequired();
    }

    @Override
    public InputFile required() {
	this.input.required();
	return this;
    }

    class File
	extends Input<File, String> {

	public File() {
	    super(ElementResolver.file());
	    this.hidden();
	}

	public File open() {
	    this.element().click();
	    return this;
	}

	@Override
	public File clear() {
	    return this.value("");
	}

	@Override
	public File value(String value) {
	    this.element().setValue(value);
	    return this;
	}

	@Override
	public String getValue() {
	    return this.element().getValue();
	}
    }
}