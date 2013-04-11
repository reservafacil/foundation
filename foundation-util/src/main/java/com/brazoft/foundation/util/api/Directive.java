package com.brazoft.foundation.util.api;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.parser.node.Node;

import com.brazoft.foundation.util.Converter;

public abstract class Directive extends org.apache.velocity.runtime.directive.Directive
{
	private File source;

	public static final String KEY = "userdirective.templates.location";

	@Override
	public int getType()
	{
		return LINE;
	}
	
	protected void resolve(RuntimeServices rs, String directiveName)
	{
		this.source =  new File(rs.getString(KEY), directiveName + ".cmf");
	}
	
	protected File source()
	{
		return this.source;
	}

	public static void setSource(RuntimeServices rs, File location)
	{
		rs.setProperty(KEY, location.getAbsolutePath());
	}

	@Override
	public String getName()
	{
		return Converter.uncapitalise(this.getClass().getSimpleName());
	}
	
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws ResourceNotFoundException
	{
		List<Variable> variables;

		try
		{
			variables = new ArrayList<Variable>();

			for(int i = 0; i < node.jjtGetNumChildren(); i++)
			{
				Variable variable = new Variable(node.jjtGetChild(i).literal(), node.jjtGetChild(i).value(context));
				variables.add(variable);
			}

			return this.doRender(context, writer, variables);
		}
		catch(Exception e)
		{
			throw new ResourceNotFoundException(e);
		}
	}
	
	public static class Variable
	{
		private String name;
		
		private Object value;

		public Variable(String name, Object value)
		{
			super();
			this.name = name.replace("$", "").replace("{", "").replace("}", "");
			this.value = value;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
		
		public Object getValue()
		{
			return value;
		}
	}
	
	protected abstract boolean doRender(InternalContextAdapter context, Writer writer, List<Variable> variables);
}