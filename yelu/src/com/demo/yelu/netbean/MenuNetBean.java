package com.demo.yelu.netbean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.demo.yelu.bean.Menu;
import com.demo.yelu.bean.User;
import com.demo.yelu.common.Constants;
import com.demo.yelu.util.TimerUtils;

public class MenuNetBean implements Request<List<Menu>> {

	String errorMsg = "";
	String code = "";
	private User user;
	private List<Menu> list;

	public MenuNetBean(User user) {
		this.user = user;
	}

	@Override
	public List<Menu> parsePackage(String jsonPackage) throws JSONException {
		list = new ArrayList<Menu>();
		JSONObject responseJson = new JSONObject(jsonPackage);
		code = responseJson.getString("code");
		if (TextUtils.equals(code, "1")) {
			JSONArray jArray = responseJson.getJSONArray("list");
			for (int i = 0; i < jArray.length(); i++) {
				Menu menu = new Menu();
				list.add(menu.parserPackage(jArray.getJSONObject(i)));
			}
		}
		errorMsg = responseJson.getString("message");
		return list;
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
	public List<Menu> getBean() {
		return list;
	}

	@Override
	public String getUrl() {
		return apiUrl + "/getCookbook.php";
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getParamer() throws JSONException {
		JSONObject jObj = new JSONObject();
		jObj.put("phone", user.getUsername());
		jObj.put("time",
				TimerUtils.getTime(Constants.TIME_FORMET_STR_YYYY_MM_DD));
		return jObj.toString();
	}

	@Override
	public void setErrorMsg(String msg) {
		this.errorMsg = msg;
	}
}
