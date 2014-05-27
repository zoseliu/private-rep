package com.demo.yelu.act;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.demo.yelu.R;
import com.demo.yelu.app.YeluApplication;
import com.demo.yelu.common.BaseActivity;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.fragment.AboutUsFragment;
import com.demo.yelu.fragment.BaseFragment;
import com.demo.yelu.fragment.HomePageFragment;
import com.demo.yelu.fragment.HomePageFragment.HomepageFunctionInterface;
import com.demo.yelu.fragment.HomePageFragment.LogoutInterface;
import com.demo.yelu.fragment.LogonFragment;
import com.demo.yelu.fragment.LogonFragment.LoginInterface;

/**
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends BaseActivity implements LoginInterface,
		LogoutInterface, HomepageFunctionInterface,
		BaseFragment.BackButtonListener {

	private FragmentManager fm;
	private LogonFragment logonFragment;
	private HomePageFragment homepageFragment;
	private AboutUsFragment aboutUsFragment;
	private Fragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fm = getSupportFragmentManager();
		logonFragment = new LogonFragment();
		homepageFragment = new HomePageFragment();
		aboutUsFragment = new AboutUsFragment();
		if (!DataProvider.isLogon) {
			fm.beginTransaction()
					.add(R.id.container, setCurrentFragment(logonFragment))
					.commit();
		} else {
			fm.beginTransaction()
					.add(R.id.container, setCurrentFragment(homepageFragment))
					.commit();
		}
	}

	@Override
	public void onLoginSuccess() {
		homepageFragment = new HomePageFragment();
		fm.beginTransaction().remove(currentFragment)
				.add(R.id.container, setCurrentFragment(homepageFragment))
				.commit();
	}

	@Override
	public void onLogout() {
		DataProvider.isLogon = false;
		DataProvider.user = null;
		Editor editor = YeluApplication.getInstance().getSharedPreferences()
				.edit();
		editor.clear().commit();
		fm.beginTransaction().remove(currentFragment)
				.add(R.id.container, setCurrentFragment(logonFragment))
				.commit();
		Toast.makeText(this, "登出成功！", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onFunctionPressed(int id) {
		switch (id) {
		case HomePageFragment.ABOUT_US:
			fm.beginTransaction().remove(currentFragment)
					.add(R.id.container, setCurrentFragment(aboutUsFragment))
					.commit();
			break;
		}
	}

	@Override
	public void onBackButtonPressed() {
		fm.beginTransaction().remove(currentFragment)
				.add(R.id.container, setCurrentFragment(homepageFragment))
				.commit();
	}

	@Override
	public void onBackPressed() {
		if(currentFragment instanceof LogonFragment){
			super.onBackPressed();
			return;
		}
		onBackButtonPressed();
	}

	public Fragment setCurrentFragment(Fragment currentFragment) {
		return this.currentFragment = currentFragment;
	}

	public Fragment getCurrentFragment() {
		return this.currentFragment;
	}
}
