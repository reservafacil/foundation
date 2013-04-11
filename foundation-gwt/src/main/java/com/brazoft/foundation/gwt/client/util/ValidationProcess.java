package com.brazoft.foundation.gwt.client.util;

import java.util.ArrayList;
import java.util.List;

import com.brazoft.foundation.commons.validator.api.Validator;
import com.brazoft.foundation.gwt.client.component.api.UIInput;

public class ValidationProcess
{
	private List<ValidationConstraint<?>> constraints = new ArrayList<ValidationConstraint<?>>();
	
	public <V> ValidationConstraint<V> constraintFor(UIInput<?, V> input)
	{
		ValidationConstraint<V> constraint = new ValidationConstraint<V>(input);
		this.add(constraint);
		
		return constraint; 
	}
	
	public ValidationProcess add(ValidationConstraint<?> constraint)
	{
		this.constraints.add(constraint);
		return this;
	}
	
	public boolean validate()
	{
		return this.validate(Propagation.ALL);
	}
	
	public boolean validate(Propagation propagation)
	{
		boolean result = true;
		
		for(ValidationConstraint<?> constraint : this.constraints)
		{
			result = constraint.validate() && result;
			if(!result && propagation == Propagation.STOP_AT_ONCE)
			{
				return false;
			}
		}
		
		return result;
	}
	
	public interface ValidationAction
	{
		void whenValid();
		
		void whenInvalid(String message);
	}
	
	public static class ValidationConstraint<V>
	{
		private UIInput<?, V> input;
		
		private final List<Validator<V>> validators = new ArrayList<Validator<V>>();
		
		private ValidationAction action;
		
		public ValidationConstraint(UIInput<?, V> input)
		{
			this.input = input;
		}

		public ValidationConstraint<V> add(Validator<V> validator)
		{
			this.validators.add(validator);
			return this;
		}
		
		public ValidationConstraint<V> remove(Validator<V> validator)
		{
			this.validators.remove(validator);
			return this;
		}
		
		public ValidationConstraint<V> action(ValidationAction action)
		{
			this.action = action;
			return this;
		}
		
		public boolean validate()
		{
			V value = this.input.getValue();
			
			for(Validator<V> validator : this.validators)
			{
				boolean result = validator.validate(value);
				if(!result)
				{
					this.action.whenInvalid(validator.getMessage());
					return false;
				}
				this.action.whenValid();
			}
			
			return true;
		}
	}
	
	public enum Propagation
	{
		ALL,
		STOP_AT_ONCE;
	}
}