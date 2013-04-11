package com.brazoft.foundation.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.util.ExceptionHandler;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
public class LDAP
{
	private static LDAP			instance = new LDAP();

	private LDAPConnection		connection;

	private LDAPAuthentication	auth;

	private LDAPObject			base;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected LDAP()
	{
		this.connection = new LDAPConnection();
		this.auth = new LDAPAuthentication();
		this.auth.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		this.auth.put(Context.SECURITY_AUTHENTICATION, "simple");
	}

	/**
	 * @return the instance
	 */
	public static LDAP getInstance()
	{
		return LDAP.instance;
	}

	/**
	 * @param host
	 */
	public void connect(String host)
	{
		this.connect(host, LDAPConnection.DEFAULT_PORT);
	}

	/**
	 * @param host
	 * @param port
	 */
	@SuppressWarnings("unchecked")
	public void connect(String host, int port)
	{
		try
		{
			this.connection.connect(host, port);
			this.auth.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port + "/");
		}
		catch (LDAPException e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}

	/**
	 * Anonymous Bind
	 */
	public void bind()
	{
		this.bind("Anonymous", null);
	}

	/**
	 * @param user
	 * @param password
	 */
	public void bind(String user, String password)
	{
		byte[] bytes;

		bytes = null;

		try
		{
			if (!Validator.isEmptyOrNull(password))
			{
				bytes = password.getBytes("UTF-8");
			}

			this.connection.bind(LDAPConnection.LDAP_V3, user, bytes);
		}
		catch (Exception e)
		{
			ExceptionHandler.handleRuntime(e);
		}
	}
	
	/**
	 * @param base
	 */
	public void setBase(LDAPObject base)
	{
		this.base = base;
	}

	/**
	 * @param userDN 
	 * @param password
	 * @return Returns if username and password matches with LDAP
	 */
	public boolean login(String userDN, String password)
	{
		return this.auth.login(userDN, password);
	}

	/**
	 * 
	 */
	public void release()
	{
		try
		{
			this.connection.disconnect();
		}
		catch (LDAPException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param base
	 * @return Returns List<LDAPObject>
	 */
	public List<LDAPObject> retriveRoot(String base)
	{
		LDAPObject object;

		try
		{
			object = new LDAPObject();
			object.setDn(base);

			return this.find(object, new LDAPSearch());
		}
		catch (Exception e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}

	/**
	 * @param from
	 * @param search
	 * @return Returns LDAPObject
	 */
	public List<LDAPObject> find(LDAPObject from, LDAPSearch search)
	{
		return this.find(from, search.getFilter(), search.getScope());
	}
	
	/**
	 * @param from
	 * @param filter 
	 * @param scope 
	 * @return Returns LDAPObject
	 */
	public List<LDAPObject> find(LDAPObject from, String filter, int scope)
	{
		LDAPSearchResults results;

		try
		{
			if(from == null)
			{
				from = this.base;
			}
			
			System.out.println("Search Base: " + from.getDn());
			System.out.println("Search Filter: " + filter);
			System.out.println("Search Scope: " + scope);

			results = this.connection.search(from.getDn(), scope, filter, null, false);

			return this.retrieve(results);
		}
		catch (Exception e)
		{
			ExceptionHandler.handleRuntime(e);
			return null;
		}
	}
	
	/**
	 * @param results
	 * @return Returns List<LDAPObject>
	 */
	protected List<LDAPObject> retrieve(LDAPSearchResults results)
	{
		List<LDAPObject> list;

		list = new ArrayList<LDAPObject>();

		while (results.hasMore())
		{
			try
			{
				list.add(this.toLDAPObject(results.next()));
			}
			catch (LDAPException e)
			{
				System.out.println("LDAPException: " + e.getMessage());
				break;
			}
		}

		return list;
	}

	/**
	 * @param entry
	 * @return Returns AbstractLDAPObject
	 */
	@SuppressWarnings("unchecked")
	protected LDAPObject toLDAPObject(LDAPEntry entry)
	{
		Iterator<LDAPAttribute> iterator;
		LDAPAttribute attribute;
		String[] values;
		LDAPObject object;

		object = new LDAPObject();
		object.setDn(entry.getDN());
		iterator = entry.getAttributeSet().iterator();

		while (iterator.hasNext())
		{
			attribute = iterator.next();

			values = attribute.getStringValueArray();
			if (!Validator.isEmptyOrNull(values))
			{
				for (String value : values)
				{
					object.put(attribute.getName(), this.parse(value));
				}
			}
		}

		return object;
	}

	/**
	 * @param value
	 * @return Returns attribute value
	 */
	protected String parse(String value)
	{
		if (value == null)
		{
			return null;
		}

		if (Base64.isLDIFSafe(value))
		{
			return value;
		}

		return Base64.encode(value.getBytes());
	}
}