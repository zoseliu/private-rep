package com.demo.yelu.app;

import java.text.ParseException;

import com.demo.yelu.common.Constants;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.util.TimerUtils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class YeluApplication extends Application {

	private static YeluApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			initData();
		} catch (Exception e) {
			Log.e("LiuYi", "初始化数据时发生异常!", e);
		}
		instance = this;
	}

	public static YeluApplication getInstance() {
		return instance;
	}

	private void initData() throws ParseException {
		SharedPreferences db = getSharedPreferences();
		boolean isLogon = db.getBoolean("isLogon", false);
		String lastLogonTime = db.getString("logonTime", "");
		if (!(isLogon && TimerUtils.compareTime(lastLogonTime, null,
				Constants.TIME_FORMET_STR_SS)))
			isLogon = false;
		if (isLogon) {
			DataProvider.isLogon = isLogon;
			DataProvider.user.setUsername(db.getString("username", null));
			DataProvider.user.setTag(db.getString("tag", null));
			Editor editor = getSharedPreferences().edit();
			editor.putString("logonTime",
					TimerUtils.getTime(Constants.TIME_FORMET_STR_SS));
			editor.commit();
		} else {
			DataProvider.isLogon = isLogon;
			Editor editor = getSharedPreferences().edit();
			editor.putBoolean("isLogon", isLogon);
			editor.putString("logonTime", "");
			editor.putString("username", "");
			editor.putString("tag", "");
			editor.commit();
		}
	}

	public SharedPreferences getSharedPreferences() {
		return this.getSharedPreferences("yeluData", Context.MODE_PRIVATE);
	}

}
