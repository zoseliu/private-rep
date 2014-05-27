package com.demo.yelu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.text.TextUtils;

public class TimerUtils {

	public static long parseTime(String time, String formatStr)
			throws ParseException {
		return getSDF(formatStr).parse(time).getTime();
	}

	public static String getTime(String formatStr) {
		return getSDF(formatStr).format(System.currentTimeMillis());
	}

	public static SimpleDateFormat getSDF(String formatStr) {
		return new SimpleDateFormat(formatStr, Locale.CHINA);
	}

	public static String changeTimeFormat(String formatStr1, String formatStr2,
			String time) throws ParseException {
		long timeLong = parseTime(time, formatStr1);
		return getSDF(formatStr2).format(timeLong);
	}

	public static boolean compareTime(String time1, String time2,
			String formatStr) throws ParseException {
		SimpleDateFormat sdf = getSDF(formatStr);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		time2 = TextUtils.isEmpty(time2) ? getTime(formatStr) : time2;
		c1.setTime(sdf.parse(time1));
		c2.setTime(sdf.parse(time2));
		int n = 0;
		while (c2.after(c1)) {
			n++;
			c1.add(Calendar.MONTH, 1);
		}
		if (n > 3) {
			return false;
		} else {
			return true;
		}
	}
}
