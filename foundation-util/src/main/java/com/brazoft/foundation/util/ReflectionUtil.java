package com.brazoft.foundation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ReflectionUtil
{
	public static boolean isTransient(Field field)
	{
		return Modifier.isTransient(field.getModifiers());
	}
	
	public static boolean isAnnotated(Field field, Class<? extends Annotation>... classes)
	{
		for(Class<? extends Annotation> clazz : classes)
		{
			if(field.getAnnotation(clazz) != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static Field getField(Class<?> clazz, String name)
	{
		try
		{
			return clazz.getDeclaredField(name);
		}
		catch (Exception e)
		{
			for(Field field : ReflectionUtil.getFields(clazz))
			{
				if(field.getName().toLowerCase().equals(name.toLowerCase()))
				{
					return field;
				}
			}
		}
		
		return null;
		//throw new RuntimeException("Field with name " + name + " not found in class: " + clazz.getName());
	}
	
	public static Object getValue(Object instance, Field field)
	{
		try
		{
			field.setAccessible(true);
			return field.get(instance);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void setValue(Object instance, Object value, Field field)
	{
		try
		{
			field.setAccessible(true);
			field.set(instance, value);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void setValue(String name, Object value, Object instance)
	{
		Field field;
		
		field = ReflectionUtil.getField(instance.getClass(), name);
		ReflectionUtil.setValue(instance, value, field);
	}
	
	public static Object getValue(String name, Object instance)
	{
		Field field;
		
		field = ReflectionUtil.getField(instance.getClass(), name);
		
		if(field == null)
		{
			return null;
		}
		
		return ReflectionUtil.getValue(instance, field);
	}
	
	/**
	 * @param clazz 
	 * @return Returns List<Method>
	 */
	public static List<Field> getFields(Class<?> clazz)
	{
		List<Field> list;
		
		list = new ArrayList<Field>();
		list.addAll(Arrays.asList(clazz.getDeclaredFields()));
		ReflectionUtil.addSuperclassFields(clazz, list);
		
		return list;
	}
	
	private static void addSuperclassFields(Class<?> clazz, List<Field> list)
	{
		if(clazz.getSuperclass() != null)
		{
			list.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
			ReflectionUtil.addSuperclassFields(clazz.getSuperclass(), list);
		}
	}
}