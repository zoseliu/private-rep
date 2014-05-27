package com.demo.yelu.netbean;

import org.json.JSONException;

public interface Request<T> extends Response<T>{

	String apiUrl = "http://www.syhnex.com:83/api";
	String method = "GET";

	public String getUrl();

	public String getMethod();

	public String getParamer() throws JSONException;
	
}
