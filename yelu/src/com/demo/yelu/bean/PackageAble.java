package com.demo.yelu.bean;

import org.json.JSONException;
import org.json.JSONObject;

public interface PackageAble<T> {
	
	public String getPackage() throws JSONException;
	
	public T parserPackage(JSONObject jObject) throws JSONException;
	
}
