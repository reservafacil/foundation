package com.brazoft.foundation.security.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public interface ILDAPClasses
{
	/**
	 * 
	 */
	public static final String[]	NAMING_ATTRIBUTES		= new String[] {"CN", "O", "OU"};
	
	/**
	 * 
	 */
	public static final String		ALL						= "objectClass=*";

	/**
	 * 
	 */
	public static final String		ACCOUNT					= "account";

	/**
	 * 
	 */
	public static final String		COUNTRY					= "country";

	/**
	 * 
	 */
	public static final String		DC_OBJECT				= "dcObject";

	/**
	 * 
	 */
	public static final String		DEVICE					= "device";

	/**
	 * 
	 */
	public static final String		FRIENDLY_COUNTRY		= "friendlyCountry";

	/**
	 * 
	 */
	public static final String		GROUP_OF_NAMES			= "groupOfNames";

	/**
	 * 
	 */
	public static final String		GROUP_OF_UNIQUE_NAMES	= "groupOfUniqueNames";

	/**
	 * 
	 */
	public static final String		INET_ORG_PERSON			= "inetOrgPerson";

	/**
	 * 
	 */
	public static final String		LOCALITY				= "locality";

	/**
	 * 
	 */
	public static final String		ORGANIZATIONAL_PERSON	= "organizationalPerson";

	/**
	 * 
	 */
	public static final String		ORGANIZATION			= "organization";

	/**
	 * 
	 */
	public static final String		ORGANIZATIONAL_ROLE		= "organizationalRole";

	/**
	 * 
	 */
	public static final String		ORGANIZATIONAL_UNIT		= "organizationalUnit";

	/**
	 * 
	 */
	public static final String		PERSON					= "person";

	/**
	 * 
	 */
	public static final String		POSIX_ACCOUNT			= "posixAccount";

	/**
	 * 
	 */
	public static final String		RESIDENTIAL_PERSON		= "residentialPerson";
}