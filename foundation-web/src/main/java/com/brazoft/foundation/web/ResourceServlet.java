package com.brazoft.foundation.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brazoft.foundation.io.IOHandler;
import com.brazoft.foundation.util.ResourceHelper;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ResourceServlet
    extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException {
	File resource;

	try {
	    resource = new File(request.getSession().getServletContext().getRealPath(request.getServletPath()));

	    if (!resource.exists()) {
		IOHandler.write(response.getOutputStream(),
		                IOHandler.read(ResourceHelper.getResource(request.getServletPath()).openStream()));

		return;
	    }

	    IOHandler.write(response.getOutputStream(), IOHandler.read(resource));
	} catch (IOException e) {
	    System.out.println("Resource not found: " + request.getServletPath());
	}
    }
}