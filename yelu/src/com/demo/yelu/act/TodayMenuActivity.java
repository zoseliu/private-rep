package com.demo.yelu.act;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.demo.yelu.R;
import com.demo.yelu.common.BaseActivity;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.fragment.TodayMenuFragment;
import com.demo.yelu.fragment.TodayMenuFragment.WeekMenuInterface;
import com.demo.yelu.fragment.WeekMenuFragment.WeekMenuBackInterface;
import com.demo.yelu.fragment.WeekMenuFragment;

public class TodayMenuActivity extends BaseActivity implements
		WeekMenuInterface, WeekMenuBackInterface {

	private FragmentManager fm;
	private TodayMenuFragment todayMenuFragment;
	private WeekMenuFragment weekMenuFragment;

	private int mode = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		fm = getSupportFragmentManager();
		todayMenuFragment = new TodayMenuFragment();
		fm.beginTransaction().add(R.id.container, todayMenuFragment).commit();
	}

	@Override
	public void onWeekMenuButtonClick() {
		if (DataProvider.menuList == null || DataProvider.menuList.size() == 0) {
			return;
		}
		weekMenuFragment = new WeekMenuFragment();
		fm.beginTransaction().remove(todayMenuFragment)
				.add(R.id.container, weekMenuFragment).commit();
		mode = 1;
	}

	@Override
	public void onBackButtonPressed() {
		gotoTodayMenuFragment();
	}

	private void gotoTodayMenuFragment() {
		todayMenuFragment = new TodayMenuFragment();
		fm.beginTransaction().remove(weekMenuFragment)
				.add(R.id.container, todayMenuFragment).commit();
		mode = 0;
	}

	@Override
	public void onBackPressed() {
		if (mode == 0) {
			super.onBackPressed();
		} else {
			gotoTodayMenuFragment();
		}
	}
}
