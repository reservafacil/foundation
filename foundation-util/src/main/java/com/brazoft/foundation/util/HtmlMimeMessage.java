package com.brazoft.foundation.util;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class HtmlMimeMessage
    extends MimeMessage {

    /**
	 * 
	 */
    public HtmlMimeMessage() {
	super();
	super.setContentType("text/html; charset=UTF-8;");
    }
}
