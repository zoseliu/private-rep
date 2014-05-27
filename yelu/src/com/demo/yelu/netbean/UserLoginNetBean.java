package com.demo.yelu.netbean;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.demo.yelu.bean.User;

public class UserLoginNetBean implements Request<User> {

	private String mobileiDentification = "345353534535";
	private String mobileos = "1";
	String errorMsg = "";
	String code = "";
	private User user;

	public UserLoginNetBean(String mobileiDentification, User user) {
		this.mobileiDentification = mobileiDentification;
		this.user = user;
	}

	@Override
	public String getUrl() {
		return apiUrl + "/ceilLogin.php";
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getParamer() throws JSONException {
		JSONObject data = new JSONObject();
		data.put("loginname", user.getUsername());
		data.put("password", user.getPassword());
		data.put("mobileiDentification", mobileiDentification);
		data.put("mobileos", mobileos);
		return data.toString();
	}

	@Override
	public User parsePackage(String jsonPackage) throws JSONException {
		JSONObject responseJson = new JSONObject(jsonPackage);
		code = responseJson.getString("code");
		if (TextUtils.equals(code, "1")) {
			user.parserPackage(responseJson);
		}
		errorMsg = responseJson.getString("message");
		return user;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public User getBean() {
		return user;
	}

	@Override
	public void setErrorMsg(String msg) {
		this.errorMsg = msg;
	}
}
