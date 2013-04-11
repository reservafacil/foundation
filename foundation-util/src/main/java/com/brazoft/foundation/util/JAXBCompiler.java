package com.brazoft.foundation.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Document;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JAXBCompiler
{
	public static Unmarshaller createUnmarshaller(String packageImpl) throws Exception
	{
		return JAXBContext.newInstance(packageImpl).createUnmarshaller();
	}

	public static Marshaller createMarshaller(String packageImpl, String encoding) throws Exception
	{
		Marshaller m;

		m = JAXBContext.newInstance(packageImpl).createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, encoding);

		return m;
	}

	public static Object compile(String xml, String packageImpl) throws Exception
	{
		return JAXBCompiler.createUnmarshaller(packageImpl).unmarshal(new ByteArrayInputStream(xml.getBytes()));
	}
	
	public static Object compile(InputStream xml, String packageImpl) throws Exception
	{
		return JAXBCompiler.createUnmarshaller(packageImpl).unmarshal(xml);
	}
	
	public static Object compile(File xml, String packageImpl) throws Exception
	{
		return JAXBCompiler.compile(new FileInputStream(xml), packageImpl);
	}
	
	public static void serialize(Object object, File file, String packageImpl) throws Exception
	{
		JAXBCompiler.serialize(object, new FileOutputStream(file), packageImpl);
	}
	
	public static void serialize(Object object, OutputStream output, String packageImpl) throws Exception
	{
		JAXBCompiler.serialize(object, output, packageImpl, "UTF-8");
	}
	
	public static void serialize(Object object, OutputStream output, String packageImpl, String encoding) throws Exception
	{
		JAXBCompiler.createMarshaller(packageImpl, encoding).marshal(object, output);
	}
	
	public static void serialize(Object object, Document output, String packageImpl, String encoding) throws Exception
	{
		JAXBCompiler.createMarshaller(packageImpl, encoding).marshal(object, output);
	}
	
	public static String serializeToString(Object object, String packageImpl)
	{
		ByteArrayOutputStream output;
		
		try
		{
			output = new ByteArrayOutputStream();
			JAXBCompiler.serialize(object, output, packageImpl);
			
			return output.toString("UTF-8");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}