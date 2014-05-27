package com.demo.yelu.loader;

import com.demo.yelu.netbean.Request;
import com.demo.yelu.util.HttpUtils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class BaseHttpBuzLoader<T extends Request<?>> extends
		AsyncTaskLoader<T> {

	HttpUtils<T> httpUtils = new HttpUtils<T>();
	
	T bean;

	public BaseHttpBuzLoader(Context context,T bean) {
		super(context);
		this.bean = bean;
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		this.forceLoad();
	}
	
	@Override
	public T loadInBackground() {
		return doRequest();
	}

	public abstract T doRequest();
}
