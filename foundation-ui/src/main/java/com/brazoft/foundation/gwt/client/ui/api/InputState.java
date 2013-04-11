package com.brazoft.foundation.gwt.client.ui.api;

import com.brazoft.foundation.gwt.client.component.api.UIInput;

public enum InputState
{
	NONE
	{
		@Override
		public void visit(UIInput<?, ?> input)
		{
			return;
		}
	},
	READONLY
	{
		@Override
		public void visit(UIInput<?, ?> input)
		{
			input.readonly();
		}
	},
	EDITABLE
	{
		@Override
		public void visit(UIInput<?, ?> input)
		{
			input.editable();
		}
	},
	NULLABLE
	{
		@Override
		public void visit(UIInput<?, ?> input)
		{
			input.nullable();
		}
	},
	REQUIRED
	{
		@Override
		public void visit(UIInput<?, ?> input)
		{
			input.required();
		}
	};

	public abstract void visit(UIInput<?, ?> input);
}