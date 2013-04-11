package com.brazoft.foundation.util.api;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.parser.node.Node;

import com.brazoft.foundation.io.IOHandler;
import com.brazoft.foundation.util.VelocityUtil;

public abstract class TemplateDirective extends Directive
{
	@Override
	public void init(RuntimeServices rs, InternalContextAdapter context, Node node) throws TemplateInitException
	{
		super.init(rs, context, node);
		this.resolve(rs, this.getName());
	}
	
	@Override
	protected boolean doRender(InternalContextAdapter context, Writer writer, List<Variable> variables)
	{
		try
		{
			Template directive = VelocityUtil.getInstance().createTemplate(this.getName(), new ByteArrayInputStream(IOHandler.read(this.source())));
			
			List<Object> values = new ArrayList<Object>();
			for(Variable variable : variables)
			{
				values.add(variable.getValue());
			}
			
			directive.merge(this.getContext(values), writer);

			return true;
		}
		catch(Exception e)
		{
			throw new ResourceNotFoundException(e);
		}
	}

	protected abstract VelocityContext getContext(List<Object> variables) throws Exception;
}