package com.demo.yelu.loader;

import android.content.Context;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.netbean.UserLoginNetBean;

public class LogonBuzLoader extends BaseHttpBuzLoader<UserLoginNetBean> {

	public LogonBuzLoader(Context context, UserLoginNetBean bean) {
		super(context, bean);
	}

	@Override
	public UserLoginNetBean doRequest() {
		if (this.httpUtils.request(bean) && bean.getCode().equals("1")) {
			DataProvider.isLogon = true;
		}
		return bean;
	}

}
