package com.brazoft.foundation.soa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.brazoft.foundation.commons.ResponseStatus;

public class Response<V> 
{
	private List<V> data = new ArrayList<V>();
	
	private int status = ResponseStatus.SUCCESS.getCode();
	
	private int start;
	
	private int end;
	
	private long total;

	public Response<V> add(V value)
	{
		this.end++;
		this.data.add(value);
		return this;
	}
	
	public Response<V> add(Collection<V> values)
	{
		this.data.addAll(values);
		this.end = values.size();
		return this;
	}
	
	public List<V> getData()
	{
		return data;
	}

	public int getStart()
	{
		return start;
	}
	
	public int getEnd()
	{
		return this.end + this.start;
	}

	public Response<V> start(int start)
	{
		this.start = start;
		return this;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public Response<V> status(ResponseStatus status)
	{
		this.status = status.getCode();
		return this;
	}

	public long getTotal()
	{
		return total;
	}

	public Response<V> total(long total)
	{
		this.total = total;
		return this;
	}
}