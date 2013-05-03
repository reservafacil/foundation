package com.brazoft.foundation.gwt.client.ui;

import com.brazoft.foundation.gwt.client.event.api.EventHandler;
import com.brazoft.foundation.gwt.client.event.api.HasFocusHandlers;
import com.brazoft.foundation.gwt.client.ui.api.AbstractTable.Row.Cell;
import com.brazoft.foundation.gwt.client.ui.api.InputControl;
import com.brazoft.foundation.gwt.client.ui.api.Select;
import com.brazoft.foundation.gwt.client.util.ValidationProcess;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationAction;
import com.brazoft.foundation.gwt.client.util.ValidationProcess.ValidationResult;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;

@SuppressWarnings("unchecked")
public abstract class InputPanel<I extends InputPanel<I>> extends OutputPanel<I>
{
	private ValidationProcess	process	= new ValidationProcess();
	
	public InputPanel(int columns)
	{
		super(columns);
	}
	
	public InputItem item(final InputGroup<?> group)
	{
		Cell cell = this.cell(group.getColspan());
		
		if(group.getInput() instanceof Select)
		{
			group.getInput().style().property("width", cell.style().getWidth());
		}

		final InputControl control = new InputControl().input(group);
		cell.add(group.getLabel());
		cell.add(control);

		if (group.getInput() instanceof HasFocusHandlers)
		{
			((HasFocusHandlers<?>) group.getInput()).onBlur(new BlurHandler()
			{
				@Override
				public void onBlur(BlurEvent event)
				{
					group.validate();
				}
			});
		}

		ValidationAction action = new ValidationAction()
		{
			@Override
			public void whenValid()
			{
				control.reset();
			}

			@Override
			public void whenInvalid(String message)
			{
				control.error().message(message);
			}
		};

		group.action(action);
		this.process.add(group.getConstraint());

		return new InputItem(cell, control);
	}
	
	public I onValidation(EventHandler<ValidationResult> handler)
	{
		this.process.onComplete(handler);
		return (I) this;
	}

	public boolean validate()
	{
		return this.process.validate();
	}
	
	public static class InputItem
	{
		private Cell cell;
		
		private InputControl control;
		
		InputItem(Cell cell, InputControl control)
		{
			super();
			this.cell = cell;
			this.control = control;
		}

		public Cell cell()
		{
			return this.cell;
		}
		
		public InputControl control()
		{
			return this.control;
		}
	}
}