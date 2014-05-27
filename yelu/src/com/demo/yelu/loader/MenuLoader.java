package com.demo.yelu.loader;

import android.content.Context;

import com.demo.yelu.netbean.MenuNetBean;

public class MenuLoader extends BaseHttpBuzLoader<MenuNetBean> {

	public MenuLoader(Context context, MenuNetBean bean) {
		super(context, bean);
	}

	@Override
	public MenuNetBean doRequest() {
		if (!httpUtils.request(bean)) {
			bean.setErrorMsg("您的设备当前网络状态不佳，请稍后再试~");
		}
		return bean;
	}

}
