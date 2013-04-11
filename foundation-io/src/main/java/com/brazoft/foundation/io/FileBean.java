package com.brazoft.foundation.io;

import java.io.Serializable;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class FileBean implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String name;

	private byte[] content;

	private String type;

	/**
	 * 
	 */
	public FileBean()
	{
		super();
	}
	
	/**
	 * @param name
	 * @param type
	 */
	public FileBean(String name, Type type)
	{
		super();
		this.name = name;
		this.type = type.toString();
		
		this.check(type);
	}
	
	private void check(Type type)
	{
		if(!this.name.contains(type.extension))
		{
			this.name = name.concat(type.extension);
		}
	}

	/**
	 * @return Returns extensao do arquivo
	 */
	public String getExtension()
	{
		return this.name.substring(this.name.lastIndexOf(".") + 1);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(byte[] content)
	{
		this.content = content;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return this.type.toString();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * @author Anderson Braz - anderson.braz@brazoft.com.br
	 */
	public enum Type
	{
		/**
		 * 
		 */
		GZIP("application/x-gzip", ".gz"),
		
		/**
		 * 
		 */
		IMAGE_BMP("image/bmp", ".bmp"),
		
		/**
		 * 
		 */
		IMAGE_GIF("image/gif", ".gif"),
		
		/**
		 * 
		 */
		IMAGE_JPEG("image/jpeg", ".jpg"),
		
		/**
		 * 
		 */
		IMAGE_PNG("image/png", ".png"),
		
		/**
		 * 
		 */
		IMAGE_SVG("image/svg+xml", ".svg"),
		
		/**
		 * 
		 */
		JAVASCRIPT("application/x-javascript", ".js"),
		
		/**
		 * Office Excel
		 */
		MS_EXCEL("application/vnd.ms-excel", ".xls"),
		
		/**
		 * Office Excel (Open XML Formats)
		 */
		MS_EXCEL_X("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"),
		
		/**
		 * Office Power Point
		 */
		MS_POWER_POINT("application/vnd.ms-powerpoint", ".ppt"),
		
		/**
		 * Office Power Point (Open XML Formats) 
		 */
		MS_POWER_POINT_X("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx"),
		
		/**
		 * Microsoft Project
		 */
		MS_PROJECT("application/vnd.ms-project", ".mpp"),
		
		/**
		 * Office Word
		 */
		MS_WORD("application/msword", ".doc"),
		
		/**
		 * Office Word (Open XML Formats)
		 */
		MS_WORD_X("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx"),
		
		/**
		 * Adobe PDF
		 */
		PDF("application/pdf", ".pdf"),
		
		/**
		 * 
		 */
		SHOCKWAVE_FLASH("application/x-shockwave-flash", ".swf"),
		
		/**
		 * Generic file type(*, bin, class, dms, exe, lha, lzh, oda)
		 */
		STREAM("application/octet-stream", ""),
		
		/**
		 * 
		 */
		TAR("application/x-tar", ".tar"),
		
		/**
		 * 
		 */
		TEXT_PLAIN("text/plain", ".txt"),
		
		/**
		 * 
		 */
		TEXT_HTML("text/html", ".html"),
		
		/**
		 * 
		 */
		ZIP("application/zip", ".zip");
		
		
		private String type;
		
		private String extension;
		
		private Type(String type, String extension)
		{
			this.type = type;
			this.extension = extension;
		}
		
		@Override
		public String toString()
		{
			return this.type;
		}
	}
}