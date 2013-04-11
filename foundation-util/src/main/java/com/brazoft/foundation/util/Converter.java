package com.brazoft.foundation.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.Normalizer.Form;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;

import sun.text.Normalizer;

import com.brazoft.foundation.commons.Validator;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br Non Localized Converter
 */
public class Converter
{
	/**
	 * @param value
	 * @return Returns capitalized String
	 */
	public static String capitalise(String value)
	{
		return StringUtils.capitalize(value);
	}
	
	/**
	 * @param calendar
	 * @return Returns Date from calendar
	 */
	public static Date toDate(Calendar calendar)
	{
		if(calendar == null)
		{
			return null;
		}
		
		return calendar.getTime();
	}
	
	/**
	 * @param time
	 * @return Returns Calendar
	 */
	public static Calendar toCalendar(Date time)
	{
		if(time == null)
		{
			return null;
		}
		
		return Converter.toCalendar(time.getTime());
	}

	/**
	 * @param time
	 * @return Returns Calendar
	 */
	public static Calendar toCalendar(long time)
	{
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);

		return calendar;
	}

	/**
	 * @param date
	 * @return Returns Timestamp
	 */
	public static Timestamp toTimestamp(Date date)
	{
		if (date == null)
		{
			return null;
		}

		return new Timestamp(date.getTime());
	}

	/**
	 * @param calendar
	 * @return Returns Timestamp
	 */
	public static Timestamp toTimestamp(Calendar calendar)
	{
		if (calendar == null)
		{
			return null;
		}

		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * @param value
	 * @return Returns converted BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object value)
	{
		return (BigDecimal) Converter.toObject(value, BigDecimal.class);
	}

	/**
	 * @param value
	 * @return Returns converted BigInteger
	 */
	public static BigInteger toBigInteger(Object value)
	{
		return (BigInteger) Converter.toObject(value, BigInteger.class);
	}

	/**
	 * @param value
	 * @return Returns converted Boolean
	 */
	public static Boolean toBoolean(Object value)
	{
		return (Boolean) Converter.toObject(value, Boolean.class);
	}

	/**
	 * @param value
	 * @return Returns converted Double
	 */
	public static Double toDouble(Object value)
	{
		return (Double) Converter.toObject(value, Double.class);
	}

	/**
	 * @param value
	 * @return Returns converted Float
	 */
	public static Float toFloat(Object value)
	{
		return (Float) Converter.toObject(value, Float.class);
	}

	/**
	 * @param value
	 * @return Returns converted Long
	 */
	public static Long toLong(Object value)
	{
		return (Long) Converter.toObject(value, Long.class);
	}

	/**
	 * @param value
	 * @return Returns converted Integer
	 */
	public static Integer toInteger(Object value)
	{
		return (Integer) Converter.toObject(value, Integer.class);
	}

	/**
	 * @param value
	 * @return Returns converted Boolean
	 */
	public static Boolean toBoolean(int value)
	{
		return value == 1;
	}

	/**
	 * @param value
	 * @return Returns converted byte array
	 */
	public static byte[] toByteArray(String value)
	{
		return value.getBytes();
	}

	/**
	 * @param value
	 * @return Returns converted Integer
	 */
	public static Integer toInteger(Boolean value)
	{
		return value ? 1 : 0;
	}

	/**
	 * @param value
	 * @return Returns converted boolean primitive
	 */
	public static boolean toboolean(Object value)
	{
		return Converter.toBoolean(value).booleanValue();
	}

	/**
	 * @param value
	 * @return Returns converted boolean primitive
	 */
	public static boolean toboolean(int value)
	{
		return Converter.toBoolean(value).booleanValue();
	}

	/**
	 * @param value
	 * @return Returns converted double primitive
	 */
	public static double todouble(Object value)
	{
		return Converter.toDouble(value).doubleValue();
	}

	/**
	 * @param value
	 * @return Returns converted float primitive
	 */
	public static float tofloat(Object value)
	{
		return Converter.toFloat(value).floatValue();
	}

	/**
	 * @param value
	 * @return Returns converted int primitive
	 */
	public static int toint(Object value)
	{
		if(value instanceof Boolean)
		{
			return Converter.toint(((Boolean) value).booleanValue());
		}
		
		return Converter.toInteger(value).intValue();
	}

	/**
	 * @param value
	 * @return Returns converted int primitive
	 */
	public static int toint(boolean value)
	{
		return Converter.toInteger(value).intValue();
	}

	/**
	 * @param value
	 * @return Returns converted long primitive
	 */
	public static long tolong(Object value)
	{
		return Converter.toLong(value).longValue();
	}

	/**
	 * @param value
	 * @return Returns converted Short
	 */
	public static Short toShort(Object value)
	{
		return (Short) Converter.toObject(value, Short.class);
	}
	
	/**
	 * @param value
	 * @return Returns converted String
	 */
	public static String toString(char value)
	{
		return String.valueOf(value);
	}

	/**
	 * @param value
	 * @return Returns converted String
	 */
	public static String toString(long value)
	{
		return String.valueOf(value);
	}

	/**
	 * @param value
	 * @return Returns converted String
	 */
	public static String toString(boolean value)
	{
		return String.valueOf(value);
	}

	/**
	 * @param value
	 * @return Returns converted String
	 */
	public static String toString(double value)
	{
		return String.valueOf(value);
	}

	/**
	 * @param value
	 * @return Returns converted String
	 */
	public static String toString(Object value)
	{
		if (value == null)
		{
			return null;
		}

		if (value instanceof String)
		{
			return (String) value;
		}

		return (String) Converter.toObject(value, String.class);
	}
	
	/**
	 * @param bytes
	 * @return Returns String from bytes
	 */
	public static String toString(byte[] bytes)
	{
		if(bytes == null)
		{
			return null;
		}
		
		return new String(bytes);
	}

	/**
	 * @param value
	 * @return Returns trimmed String
	 */
	public static String toTrimmedString(Object value)
	{
		if (value == null)
		{
			return null;
		}

		return Converter.toString(value).trim();
	}

	/**
	 * @param value
	 * @return Returns a not null String
	 */
	public static String toNotNull(String value)
	{
		return value == null ? "" : value;
	}

	/**
	 * @param value
	 * @return Returns a 'null' String
	 */
	public static String toNone(String value)
	{
		return Validator.isEmptyOrNull(value) ? "none" : value;
	}

	/**
	 * @param value
	 * @return Returns converted StringBuffer
	 */
	public static StringBuffer toStringBuffer(String value)
	{
		return new StringBuffer(value);
	}

	/**
	 * @param bytes
	 * @return Returns bytes as Hexa Decimal String
	 */
	public static String toHexa(byte[] bytes)
	{
		return new BigInteger(bytes).toString(16);
	}

	/**
	 * @param hexa
	 * @return Returns byte[] from Hexa Decimal String
	 */
	public static byte[] toByteArrayHexa(String hexa)
	{
		return new BigInteger(hexa, 16).toByteArray();
	}

	/**
	 * @deprecated Use capitalise instead
	 * @param value
	 * @return Returns first letter as Upper Case
	 */
	public static String firstUpperCase(String value)
	{
		return StringUtils.capitalise(value);
	}

	/**
	 * @param content
	 * @return Returns UTF-8 String charset
	 * @throws UnsupportedEncodingException
	 */
	public static String toUTF8(String content) throws UnsupportedEncodingException
	{
		if (content == null)
		{
			return null;
		}

		return new String(content.getBytes("UTF-8"), "UTF-8");
	}

	/**
	 * @param value
	 * @return Returns ASCII String
	 */
	public static String toASCII(String value)
	{
		return Normalizer.normalize(value, Form.NFKD, 0).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * @param value
	 * @param clazz
	 * @return Returns converted Object
	 */
	protected static Object toObject(Object value, Class<?> clazz)
	{
		if (value == null || Validator.isEmptyOrNull(value.toString()))
		{
			if (Integer.class.isAssignableFrom(clazz))
			{
				value = "0";
			}
			else if (Boolean.class.isAssignableFrom(clazz))
			{
				value = false;
			}
			else
			{
				return null;
			}
		}

		return ConvertUtils.convert(value.toString(), clazz);
	}
	
	/**
	 * @param value
	 * @return Returns uncapitalized String
	 */
	public static String uncapitalise(String value)
	{
		return StringUtils.uncapitalize(value);
	}
}