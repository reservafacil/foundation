package com.brazoft.foundation.security;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 * 
 */
@SuppressWarnings("unchecked")
public class LDAPAuthentication
    extends Hashtable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * @param userDN
     * @param password
     * @return Returns if user and password are permitted on LDAP
     */
    public boolean login(String userDN, String password) {
	DirContext context;

	context = null;

	try {
	    this.put(Context.SECURITY_CREDENTIALS, password);
	    this.put(Context.SECURITY_PRINCIPAL, userDN);

	    context = new InitialDirContext(this);

	    return true;
	} catch (NamingException e) {
	    e.printStackTrace();
	} finally {
	    this.release(context);
	}

	return false;
    }

    /**
     * @param context
     */
    protected void release(DirContext context) {
	if (context != null) {
	    try {
		context.close();
	    } catch (NamingException e) {
		e.printStackTrace();
	    }
	}
    }
}
