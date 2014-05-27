package com.demo.yelu.netbean;

import org.json.JSONException;

public interface Response<T> {

	public T parsePackage(String jsonPackage) throws JSONException;

	public String getCode();

	public String getErrorMsg();

	public T getBean();
	
	public void setErrorMsg(String msg);

}
