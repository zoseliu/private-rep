/*******************************************************************************
 * @(#)ActivityManager.java 2012-6-21
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.demo.yelu.common;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.os.Process;
import android.util.Log;

/**
 * Activity管理
 * 
 * @author <a href="mailto:wenxw@neusoft.com">sherly.wen </a>
 * @version $Revision 1.1 $ 2012-6-21 下午12:20:35
 */
public final class ActivityManager {

	private static final List<WeakReference<Activity>> references = new Vector<WeakReference<Activity>>();

	private ActivityManager() {

	}

	/**
	 * 添加Activity到容器中[/color]
	 * 
	 * @param activity
	 */
	public static void addActivity(WeakReference<Activity> reference) {
		references.add(reference);
	}

	/**
	 * 添加Activity到容器中[/color]
	 * 
	 * @param activity
	 */
	public static Activity getOneActivity() {
		for (WeakReference<Activity> reference : references) {
			if (reference.get() != null) {
				return reference.get();
			}
		}
		return null;
	}

	/**
	 * 遍历Activity并finish[/color]
	 */
	public static void exit() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				releaseAllResource();
			}
		}).start();

	}

	/**
	 * 遍历Activity并finish[/color]
	 */
	public static void exitApp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				closeAllActivity();
				clearReciverAndNotification();
				killProcess();
			}
		}).start();
	}

	/**
	 * 释放资源
	 */
	public static void releaseAllResource() {
		closeAllActivity();
		clearReciverAndNotification();
		killProcess();
	}

	private static void killProcess() {
		Process.killProcess(Process.myPid());
	}

	public static void clearReciverAndNotification() {
	}

	private static void closeAllActivity() {
		try {
			for (WeakReference<Activity> reference : references) {
				if (reference.get() != null) {
					reference.get().finish();
				}
			}
		} catch (Exception e) {
			Log.e("yelu", "one error occur", e);
		}

	}

	// public static void releaseAllResourceAndGoLogin() {
	// closeAllActivity();
	// new LogoutBiz().clearCurrentAcountLoginInfo(YtApplication.getInstance()
	// .getApplicationContext());
	// Intent intent = new Intent(YtApplication.getInstance()
	// .getApplicationContext(), LoginAcitvity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// YtApplication.getInstance().startActivity(intent);
	// }
}
