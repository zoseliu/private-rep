package com.demo.yelu.common;

import java.lang.ref.WeakReference;

import com.demo.yelu.fragment.ProgressInterface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseActivity extends FragmentActivity implements ProgressInterface {
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		WeakReference<Activity> reference = new WeakReference<Activity>(this);
		ActivityManager.addActivity(reference);
	}

	@Override
	public void show(String msg) {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(this, "请稍后", msg, true, false);
		}
	}

	@Override
	public void hide() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
}
