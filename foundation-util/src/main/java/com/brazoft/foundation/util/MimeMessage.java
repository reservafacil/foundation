package com.brazoft.foundation.util;


/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class MimeMessage
{
	private StringBuffer		to;

	private StringBuffer		cc;

	private StringBuffer		bcc;

	private String				contentType;

	private String				subject;

	private String				content;

	private static final String	COMMA	= ", ";
	
	/**
	 * 
	 */
	public MimeMessage()
	{
		this.contentType = "text/plain; charset=UTF-8;";
	}

	/**
	 * @param to
	 */
	public void addTo(String to)
	{
		if (this.to == null)
		{
			this.to = new StringBuffer();
		}
		
		this.append(this.to, to);
	}

	/**
	 * @return the to
	 */
	public String getTo()
	{
		if (this.to == null)
		{
			return null;
		}

		this.parse(this.to);

		return this.to.toString();
	}

	/**
	 * @param cc
	 */
	public void addCc(String cc)
	{
		if (this.cc == null)
		{
			this.cc = new StringBuffer();
		}
		
		this.append(this.cc, cc);
	}

	/**
	 * @return the cc
	 */
	public String getCc()
	{
		if (this.cc == null)
		{
			return null;
		}

		this.parse(this.cc);

		return this.cc.toString();
	}

	/**
	 * @param bcc
	 */
	public void addBcc(String bcc)
	{
		if (this.bcc == null)
		{
			this.bcc = new StringBuffer();
		}
		
		this.append(this.bcc, bcc);
	}

	/**
	 * @return the bcc
	 */
	public String getBcc()
	{
		if (this.bcc == null)
		{
			return null;
		}

		this.parse(this.bcc);

		return this.bcc.toString();
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	/**
	 * @param contentType
	 */
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	/**
	 * @return Returns contentType
	 */
	public String getContentType()
	{
		return this.contentType;
	}
	
	private void parse(StringBuffer addresses)
	{
		if (addresses.lastIndexOf(MimeMessage.COMMA) > -1)
		{
			addresses.delete(addresses.lastIndexOf(MimeMessage.COMMA), addresses.length());
		}
	}
	
	private void append(StringBuffer addresses, String value)
	{
		if(addresses.indexOf(value) == -1)
		{
			addresses.append(value).append(MimeMessage.COMMA);
		}
	}
}