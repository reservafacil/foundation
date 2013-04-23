package com.brazoft.foundation.commons;

import java.util.Collection;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class Validator
{
	/**
	 * @param collection
	 * @return Returns if collection is null or empty
	 */
	public static boolean isEmptyOrNull(Collection<?> collection)
	{
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * @param value
	 * @return Returns value is not null and length > 0
	 */
	public static boolean isEmptyOrNull(Object[] value)
	{
		return value == null || value.length == 0;
	}
	
	/**
	 * @param value
	 * @return Returns if value is blank or null
	 */
	public static boolean isEmptyOrNull(String value)
	{
		return (value == null) || value.isEmpty();
	}
	
	/**
	 * @param value
	 * @return Returns if value is blank or null or zero("0")
	 */
	public static boolean isEmptyOrNullOrZero(String value)
	{
		return Validator.isEmptyOrNull(value) || "0".equals(value);
	}
	
	/**
	 * @param value
	 * @return Returns if value is blank or null
	 */
	public static boolean isEmptyOrNull(Object value)
	{
		if(value == null)
		{
			return true;
		}
		
		if(value instanceof Number){
			return Validator.isZero((Number) value);
		}
		
		if(value instanceof String)
		{
			return Validator.isEmptyOrNull((String) value);
		}
		
		return false;
	}
	
	/**
	 * @param value
	 * @return Returns Boolean validation
	 */
	public static boolean isTrue(Boolean value)
	{
		return value != null && value;
	}
	
	/**
	 * @param number
	 * @return Returns if number is zero
	 */
	public static boolean isZero(Number number)
	{
		return number.doubleValue() == 0.0;
	}

	/**
	 * @param value
	 * @return Returns if value is Integer
	 */
	public static boolean isInt(String value)
	{
		try
		{
			Integer.parseInt(value);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * @param value
	 * @return Returns if value is Double
	 */
	public static boolean isDouble(String value)
	{
		try
		{
			Double.parseDouble(value);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * @param value
	 * @return Returns if value is zero
	 */
	public static boolean isZero(String value)
	{
		return value.equals("0");
	}

	/**
	 * @param value
	 * @param mod
	 * @return Returns if mod of value is zero
	 */
	public static boolean isZeroMod(int value, int mod)
	{
		return (value % mod) == 0;
	}
	
    /**
    * <p>Checks if a value is within a range (min &amp; max specified
    * in the vars attribute).</p>
    *
    * @param value The value validation is being performed on.
    * @param min The minimum value of the range.
    * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
    */
    public static boolean isInRange(byte value, byte min, byte max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(int value, int min, int max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(float value, float min, float max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(short value, short min, short max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(long value, long min, long max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if a value is within a range (min &amp; max specified
     * in the vars attribute).</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return true if the value is in the specified range.
     */
    public static boolean isInRange(double value, double min, double max) {
        return ((value >= min) && (value <= max));
    }

    /**
     * <p>Checks if the value's length is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum length.
     * @return true if the value's length is less than the specified maximum.
     */
    public static boolean maxLength(String value, int max) {
        return (value.length() <= max);
    }

    /**
     * <p>Checks if the value's adjusted length is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum length.
     * @param lineEndLength The length to use for line endings.
     * @return true if the value's length is less than the specified maximum.
     */
    public static boolean maxLength(String value, int max, int lineEndLength) {
        int adjustAmount = adjustForLineEnding(value, lineEndLength);
        return ((value.length() + adjustAmount) <= max);
    }

    /**
     * <p>Checks if the value's length is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum length.
     * @return true if the value's length is more than the specified minimum.
     */
    public static boolean minLength(String value, int min) {
        return (value.length() >= min);
    }

    /**
     * <p>Checks if the value's adjusted length is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum length.
     * @param lineEndLength The length to use for line endings.
     * @return true if the value's length is more than the specified minimum.
     */
    public static boolean minLength(String value, int min, int lineEndLength) {
        int adjustAmount = adjustForLineEnding(value, lineEndLength);
        return ((value.length() + adjustAmount) >= min);
    }

    /**
     * Calculate an adjustment amount for line endings.
     *
     * See Bug 37962 for the rational behind this.
     *
     * @param value The value validation is being performed on.
     * @param lineEndLength The length to use for line endings.
     * @return the adjustment amount.
     */
    private static int adjustForLineEnding(String value, int lineEndLength) {
        int nCount = 0;
        int rCount = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '\n') {
                nCount++;
            }
            if (value.charAt(i) == '\r') {
                rCount++;
            }
        }
        return ((nCount * lineEndLength) - (rCount + nCount));
    }

    // See http://issues.apache.org/bugzilla/show_bug.cgi?id=29015 WRT the "value" methods

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(int value, int min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(long value, long min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(double value, double min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is greater than or equal to the min.</p>
     *
     * @param value The value validation is being performed on.
     * @param min The minimum numeric value.
     * @return true if the value is &gt;= the specified minimum.
     */
    public static boolean minValue(float value, float min) {
        return (value >= min);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(int value, int max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(long value, long max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(double value, double max) {
        return (value <= max);
    }

    /**
     * <p>Checks if the value is less than or equal to the max.</p>
     *
     * @param value The value validation is being performed on.
     * @param max The maximum numeric value.
     * @return true if the value is &lt;= the specified maximum.
     */
    public static boolean maxValue(float value, float max) {
        return (value <= max);
    }
}