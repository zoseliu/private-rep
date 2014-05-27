package com.demo.yelu.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Menu implements PackageAble<Menu> {

	private String cookbookdate;

	private String dayby;

	private String cookbook;

	public String getCookbookdate() {
		return cookbookdate;
	}

	public void setCookbookdate(String cookbookdate) {
		this.cookbookdate = cookbookdate;
	}

	public String getDayby() {
		return dayby;
	}

	public void setDayby(String dayby) {
		this.dayby = dayby;
	}

	public String getCookbook() {
		return cookbook;
	}

	public void setCookbook(String cookbook) {
		this.cookbook = cookbook;
	}

	@Override
	public String getPackage() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("cookbookdate", cookbookdate);
		json.put("dayby", dayby);
		json.put("cookbook", cookbook);
		return json.toString();
	}

	@Override
	public Menu parserPackage(JSONObject jObject) throws JSONException {
		if (jObject.has("cookbookdate")) {
			cookbookdate = jObject.getString("cookbookdate");
		}
		if (jObject.has("dayby")) {
			dayby = jObject.getString("dayby");
		}
		if (jObject.has("cookbook")) {
			cookbook = jObject.getString("cookbook");
		}
		return this;
	}

}
