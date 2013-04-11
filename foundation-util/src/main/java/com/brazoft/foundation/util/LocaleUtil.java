package com.brazoft.foundation.util;

import java.util.Locale;

import com.brazoft.foundation.commons.Validator;
import com.ibm.icu.util.ULocale;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class LocaleUtil
{
	/**
	 * @param locale
	 * @return Returns country translation
	 */
	public static String getCountryLabel(Locale locale)
	{
		return LocaleUtil.getCountryLabel(locale, locale);
	}
	
	/**
	 * @param locale
	 * @param translateTo
	 * @return Returns country translation
	 */
	public static String getCountryLabel(Locale locale, Locale translateTo)
	{
		return ULocale.forLocale(locale).getDisplayCountry(ULocale.forLocale(translateTo));
	}
	
	/**
	 * @param locale
	 * @return Returns language translation
	 */
	public static String getLanguageLabel(Locale locale)
	{
		return LocaleUtil.getLanguageLabel(locale, locale);
	}
	
	/**
	 * @param locale
	 * @param translateTo
	 * @return Returns language name
	 */
	public static String getLanguageLabel(Locale locale, Locale translateTo)
	{
		String label;
		
		label = ULocale.forLocale(locale).getDisplayLanguage(ULocale.forLocale(translateTo));
		
		return Converter.capitalise(label);
	}
	
	/**
	 * @param locale
	 * @return Returns locale translation
	 */
	public static String translate(Locale locale)
	{
		return LocaleUtil.translate(locale, locale);
	}
	
	/**
	 * @param locale
	 * @param translateTo 
	 * @return Returns locale translation
	 */
	public static String translate(Locale locale, Locale translateTo)
	{
		StringBuffer buffer;

		buffer = new StringBuffer(LocaleUtil.getLanguageLabel(locale, translateTo));
		if(!Validator.isEmptyOrNull(locale.getCountry()))
		{
			buffer.append(" (");
			buffer.append(LocaleUtil.getCountryLabel(locale, translateTo));
			buffer.append(")");			
		}

		return buffer.toString();
	}
	
	/**
	 * @param locale
	 * @return Returns ISO code from locale
	 */
	public static String toISOCode(Locale locale)
	{
		if (Validator.isEmptyOrNull(locale.getCountry()))
		{
			return locale.getLanguage();
		}

		return new StringBuffer(locale.getLanguage()).append("_").append(locale.getCountry()).toString();
	}
}