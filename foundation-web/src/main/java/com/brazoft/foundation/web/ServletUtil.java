package com.brazoft.foundation.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brazoft.foundation.io.FileBean;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class ServletUtil {

    /**
     * @param response
     * @param bean
     * @throws IOException
     */
    public static void write(HttpServletResponse response, FileBean bean)
	throws IOException {
	ServletOutputStream output;

	output = response.getOutputStream();

	try {
	    response.setHeader("Content-Disposition", "attachment;filename=\"" + bean.getName() + "\"");
	    response.setContentType(bean.getType());
	    response.setContentLength(bean.getContent().length);

	    output.write(bean.getContent());
	    output.flush();
	} finally {
	    output.close();
	}
    }

    /**
     * @param event
     * @return Returns HttpSession
     */
    public static HttpSession getSession(ServletRequestEvent event) {
	return ServletUtil.getSession(event.getServletRequest());
    }

    /**
     * @param request
     * @return Returns HttpSession
     */
    public static HttpSession getSession(ServletRequest request) {
	return ((HttpServletRequest)request).getSession();
    }

    /**
     * @param request
     * @return Returns HttpSession id
     */
    public static String getSessionId(HttpServletRequest request) {
	return request.getSession().getId();
    }

    /**
     * @param request
     * @return Returns root context path
     */
    public static String getRoot(HttpServletRequest request) {
	return request.getContextPath();
    }

    /**
     * @param request
     * @return Returns if Browser is InternetExplorer
     */
    public static boolean isInternetExplorer(HttpServletRequest request) {
	return request.getHeader("User-Agent").contains("MSIE");
    }

    /**
     * @param context
     * @param name
     * @return Returns path to WEB-INF/classes/name
     */
    public static String getClassesPath(ServletContext context, String name) {
	return ServletUtil.getPath(context, ServletUtil.CLASSES_PATH + name.replace(".", File.separator));
    }

    /**
     * @param context
     * @return Returns path to WEB-INF/classes/name
     */
    public static String getClassesPath(ServletContext context) {
	return ServletUtil.getPath(context, ServletUtil.CLASSES_PATH);
    }

    /**
     * @param context
     * @return Returns path to WEB-INF/lib
     */
    public static String getLibPath(ServletContext context) {
	return ServletUtil.getPath(context, ServletUtil.LIB_PATH);
    }

    /**
     * @param context
     * @param name
     * @return Returns path to WEB-INF/lib/name
     */
    public static String getLibPath(ServletContext context, String name) {
	return ServletUtil.getPath(context, ServletUtil.LIB_PATH + name);
    }

    /**
     * @param context
     * @param path
     * @return Returns file system path
     */
    public static String getPath(ServletContext context, String path) {
	return context.getRealPath(path);
    }

    private static final String LIB_PATH     = "WEB-INF/lib/";

    private static final String CLASSES_PATH = "WEB-INF/classes/";
}