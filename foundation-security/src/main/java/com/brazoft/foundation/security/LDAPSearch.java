package com.brazoft.foundation.security;

import com.brazoft.foundation.security.api.ILDAPClasses;
import com.novell.ldap.LDAPConnection;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class LDAPSearch
{
	private StringBuffer	filter;

	private int				scope;

	private boolean			and;

	private boolean			or;

	private boolean			not;

	/**
	 * 
	 */
	public LDAPSearch()
	{
		this.filter = new StringBuffer();
		this.scope = LDAPSearch.SCOPE_ONE;
		this.and = true;
	}

	/**
	 * @param filter
	 */
	public void add(String filter)
	{
		this.filter.append("(").append(filter).append(")");
	}
	
	/**
	 * @param object 
	 */
	public void add(LDAPObject object)
	{
		for(String key : object.getAttributes().keySet())
		{
			for(String value : object.getAttributes().get(key))
			{
				this.add(key + "=" + value);
			}
		}
	}

	/**
	 * @return the and
	 */
	public boolean isAnd()
	{
		return and;
	}

	/**
	 * @param and
	 *            the and to set
	 */
	public void setAnd(boolean and)
	{
		this.and = and;
		this.or = !and;
	}

	/**
	 * @return the not
	 */
	public boolean isNot()
	{
		return not;
	}

	/**
	 * @param not
	 *            the not to set
	 */
	public void setNot(boolean not)
	{
		this.not = not;
	}

	/**
	 * @return the or
	 */
	public boolean isOr()
	{
		return or;
	}

	/**
	 * @param or
	 *            the or to set
	 */
	public void setOr(boolean or)
	{
		this.or = or;
		this.and = !or;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(int scope)
	{
		this.scope = scope;
	}

	/**
	 * @return Returns scope of filter
	 */
	public int getScope()
	{
		return scope;
	}

	/**
	 * @return Returns mounted filter
	 */
	public String getFilter()
	{
		StringBuffer query;

		query = new StringBuffer();

		if (this.not)
		{
			query.append("(!");
		}
		if (this.and)
		{
			query.append("(&");
		}
		else
		{
			query.append("(|");
		}

		if (this.filter.length() == 0)
		{
			this.add(ILDAPClasses.ALL);
		}

		query.append(this.filter);

		query.append(")");

		if (this.not)
		{
			query.append(")");
		}

		return query.toString();
	}

	/**
	 * 
	 */
	public static final int	SCOPE_BASE					= LDAPConnection.SCOPE_BASE;

	/**
	 * 
	 */
	public static final int	SCOPE_ONE					= LDAPConnection.SCOPE_ONE;

	/**
	 * 
	 */
	public static final int	SCOPE_SUB					= LDAPConnection.SCOPE_SUB;

	/**
	 * 
	 */
	public static final int	SCOPE_SUBORDINATE_SUB_TREE	= LDAPConnection.SCOPE_SUBORDINATESUBTREE;
}