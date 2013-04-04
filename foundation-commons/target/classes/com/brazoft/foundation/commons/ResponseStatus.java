package com.brazoft.foundation.commons;

public enum ResponseStatus
{
	FAILURE(-1),
	LOGIN_INCORRECT(-5),
	LOGIN_REQUIRED(-7),
	LOGIN_SUCCESS(-8),
	MAX_LOGIN_ATTEMPTS_EXCEEDED(-6),
	SERVER_TIMEOUT(-100),
	SUCCESS(0),
	TRANSPORT_ERROR(-90),
	VALIDATION_ERROR(-4);
	
	private int code;

	private ResponseStatus(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public static ResponseStatus valueOf(int code)
	{
		for(ResponseStatus status : values())
		{
			if(status.getCode() == code)
			{
				return status;
			}
		}
		
		return null;
	}
}