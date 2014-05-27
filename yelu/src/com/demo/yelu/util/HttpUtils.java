package com.demo.yelu.util;

import android.util.Log;

import com.demo.yelu.netbean.Request;

public class HttpUtils<T extends Request<?>> {

	public static int code = 0;

	public boolean request(T request) {
		HttpRequest httpRequest;
		boolean flag = false;
		try {
			httpRequest = HttpRequest.get(request.getUrl(), true, "data",
					request.getParamer());
			code = httpRequest.code();
			if (httpRequest.ok()) {
				request.parsePackage(httpRequest.body());
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			Log.e("LiuYi", "尝试联网时发生异常", e);
			flag = false;
		}
		return flag;
	}
}
