package com.demo.yelu.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements PackageAble<User> {

	private String username;

	private String password;

	private String tag;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String getPackage() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("username", username);
		json.put("password", password);
		json.put("tag", tag);
		return json.toString();
	}

	@Override
	public User parserPackage(JSONObject jObject) throws JSONException {
		if (jObject.has("username")) {
			this.setUsername(jObject.getString("username"));
		}
		if (jObject.has("password")) {
			this.setPassword(jObject.getString("password"));
		}
		if (jObject.has("tag")) {
			this.setTag(jObject.getString("tag"));
		}
		return this;
	}

}
