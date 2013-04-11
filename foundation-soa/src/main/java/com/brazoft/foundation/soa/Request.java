package com.brazoft.foundation.soa;

public class Request<V>
{
	private V 	data;
	
	private int start;
	
	private int end;
	
	public Request<V> data(V data)
	{
		this.data = data;
		return this;
	}
	
	public V getData()
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

	public Request<V> start(int start)
	{
		this.start = start;
		return this;
	}
	
	public Request<V> end(int end)
	{
		this.end = end;
		return this;
	}
}