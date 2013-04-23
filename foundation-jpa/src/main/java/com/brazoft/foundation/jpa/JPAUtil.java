package com.brazoft.foundation.jpa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.util.ReflectionUtil;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
@SuppressWarnings("unchecked")
public class JPAUtil
{
	/**
	 * @param instance
	 * @return Returns Map<Field, Object>
	 */
	public static Map<String, Object> getNotNullFields(Object instance)
	{
		Object value;
		Map<String, Object> fields;
		
		fields = new HashMap<String, Object>();
		
		for(Field field : instance.getClass().getDeclaredFields())
		{
			if(!JPAUtil.isPersistent(field) || JPAUtil.isCollection(field))
			{
				continue;
			}
			
			value = JPAUtil.getValue(instance, field);
			
			if(!Validator.isEmptyOrNull(value))
			{
				fields.put(JPAUtil.getName(field), value);
			}
		}
		
		return fields;
	}
	
	private static Object getValue(Object instance, Field field)
	{
		Object reference;
		
		if(!JPAUtil.isRelation(field))
		{
			return ReflectionUtil.getValue(instance, field);
		}
		
		reference = ReflectionUtil.getValue(instance, field);
		if(reference == null)
		{
			return null;
		}
		
		return ReflectionUtil.getValue(reference, JPAUtil.getRelationField(field));
	}
	
	private static String getName(Field field)
	{
		StringBuffer name;
		String relationName;
		String relationField;
		
		if(!JPAUtil.isRelation(field))
		{
			return JPAUtil.getColumnName(field);
		}
		
		relationName = field.getName();
		relationField = JPAUtil.getColumnName(JPAUtil.getRelationField(field));
		
		name = new StringBuffer(relationName);
		name.append(".");
		name.append(relationField);
		
		return name.toString();
	}
	
	private static Field getRelationField(Field parent)
	{
		for(Field field : parent.getType().getDeclaredFields())
		{
			if(JPAUtil.isId(field))
			{
				return field;
			}
			
			if(JPAUtil.isEmbeddedId(field))
			{
				return JPAUtil.getRelationField(field);
			}
		}
		
		return parent;
	}
	
	private static String getColumnName(Field field)
	{
		Column c;
		
		c = field.getAnnotation(Column.class);
		if(c != null && !"".equals(c.name()))
		{
			return c.name();
		}
		
		return field.getName();
	}
	
	private static boolean isPersistent(Field field)
	{
		return !JPAUtil.isTransient(field);
	}
	
	private static boolean isTransient(Field field)
	{
		return ReflectionUtil.isTransient(field) || ReflectionUtil.isAnnotated(field, Transient.class);
	}
	
	private static boolean isCollection(Field field)
	{
		return ReflectionUtil.isAnnotated(field, OneToMany.class, ManyToAny.class, ManyToMany.class, ManyToOne.class);
	}
	
	private static boolean isRelation(Field field)
	{
		return ReflectionUtil.isAnnotated(field, OneToOne.class);
	}
	
	private static boolean isId(Field field)
	{
		return ReflectionUtil.isAnnotated(field, Id.class);
	}
	
	private static boolean isEmbeddedId(Field field)
	{
		return ReflectionUtil.isAnnotated(field, EmbeddedId.class);
	}
}