package com.brazoft.foundation.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.brazoft.foundation.io.IOHandler;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public abstract class JAXBUtil {

    /**
     * @param xml
     * @return Returns byte[] from xml
     * @throws Exception
     */
    public static byte[] toByteArray(Object xml)
	throws Exception {
	ByteArrayOutputStream output;

	output = new ByteArrayOutputStream();

	JAXBCompiler.serialize(xml, output, xml.getClass().getPackage().getName());

	return output.toByteArray();
    }

    /**
     * @param bytes
     * @param packageImpl
     * @return Returns Object from byte[]
     * @throws Exception
     */
    public static Object toObject(byte[] bytes, String packageImpl)
	throws Exception {
	return (Object)JAXBCompiler.compile(new ByteArrayInputStream(bytes), packageImpl);
    }

    /**
     * @param bytes
     * @param packageImpl
     * @return Returns Object from byte[]
     * @throws Exception
     */
    public static String toString(byte[] bytes, String packageImpl)
	throws Exception {
	return JAXBCompiler.serializeToString(JAXBUtil.toObject(bytes, packageImpl), packageImpl);
    }

    /**
     * @param input
     * @param packageImpl
     * @return Returns Object from InputStream
     * @throws Exception
     */
    public static Object toObject(InputStream input, String packageImpl)
	throws Exception {
	return JAXBUtil.toObject(IOHandler.read(input), packageImpl);
    }

    /**
     * @param xml
     * @return Returns InputStream
     * @throws Exception
     */
    public static InputStream toInputStream(Object xml)
	throws Exception {
	return new ByteArrayInputStream(JAXBUtil.toByteArray(xml));
    }

    /**
     * @param date
     * @return Returns XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
	if (date != null) {
	    return JAXBUtil.toXMLGregorianCalendar(date.getTime());
	}

	return null;
    }

    /**
     * @param miliseconds
     * @return Returns XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(long miliseconds) {
	GregorianCalendar calendar;

	calendar = (GregorianCalendar)GregorianCalendar.getInstance();
	calendar.setTimeInMillis(miliseconds);

	return new XMLGregorianCalendarImpl(calendar);
    }

    /**
     * @param value
     * @return Returns TimesTamp
     */
    public static Timestamp toTimesTamp(String value) {
	return Converter.toTimestamp(JAXBUtil.toDate(value));
    }

    /**
     * @param value
     * @return Returns TimesTamp
     */
    public static Date toDate(String value) {
	return DatatypeConverter.parseDate(value).getTime();
    }

    /**
     * @param calendar
     * @return Returns Date
     */
    public static Date toDate(XMLGregorianCalendar calendar) {
	return JAXBUtil.toDate(calendar.toXMLFormat());
    }
}