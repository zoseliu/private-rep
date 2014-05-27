package com.demo.yelu.fragment;

import java.text.ParseException;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.yelu.R;
import com.demo.yelu.bean.Menu;
import com.demo.yelu.common.Constants;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.loader.MenuLoader;
import com.demo.yelu.netbean.MenuNetBean;
import com.demo.yelu.util.TimerUtils;

public class TodayMenuFragment extends Fragment implements
		LoaderCallbacks<MenuNetBean>, View.OnClickListener {

	private List<Menu> menuList;
	private ProgressInterface progressInterface;
	private WeekMenuInterface weekMenuInterface;

	private TextView backButton;
	private TextView titleText;
	private TextView date;
	private TextView menuContent;
	private TextView weekMenu;
	private View line;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		progressInterface = (ProgressInterface) activity;
		weekMenuInterface = (WeekMenuInterface) activity;
	}

	private boolean hasLoaded = true;
	private boolean initLoader = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.today_menu, container, false);
		backButton = (TextView) view.findViewById(R.id.button_title_goback);
		backButton.setVisibility(View.VISIBLE);
		backButton.setOnClickListener(this);
		titleText = (TextView) view.findViewById(R.id.text_title);
		titleText.setText("今日餐单");
		date = (TextView) view.findViewById(R.id.id_today_menu_time);
		menuContent = (TextView) view.findViewById(R.id.id_today_menu_week);
		weekMenu = (TextView) view.findViewById(R.id.button_title_fourth);
		weekMenu.setVisibility(View.VISIBLE);
		weekMenu.setOnClickListener(this);
		line = view.findViewById(R.id.line);
		if (hasLoaded) {
			hasLoaded = false;
			if (initLoader) {
				getLoaderManager().initLoader(0, null, this);
			} else {
				getLoaderManager().restartLoader(0, null, this);
			}
			progressInterface.show("努力加载中~~");
		}
		return view;
	}

	@Override
	public Loader<MenuNetBean> onCreateLoader(int arg0, Bundle arg1) {
		MenuNetBean bean = new MenuNetBean(DataProvider.user);
		return new MenuLoader(getActivity(), bean);
	}

	@Override
	public void onLoadFinished(Loader<MenuNetBean> arg0, MenuNetBean arg1) {
		initLoader = false;
		progressInterface.hide();
		if (arg1.getCode().equals("1")) {
			menuList = arg1.getBean();
			DataProvider.menuList = menuList;
			for (final Menu menu : menuList) {
				if (TextUtils.equals(menu.getCookbookdate(), TimerUtils
						.getTime(Constants.TIME_FORMET_STR_YYYY_MM_DD))) {
					Handler handler = new Handler(Looper.getMainLooper());
					handler.post(new Runnable() {
						@Override
						public void run() {
							showMenu(menu);
						}
					});
				}
			}
		} else if (TextUtils.isEmpty(arg1.getCode())) {
			Toast.makeText(getActivity(), arg1.getErrorMsg(), Toast.LENGTH_LONG)
					.show();
			getActivity().finish();
		} else {
			menuContent.setText(arg1.getErrorMsg());
			line.setVisibility(View.GONE);
		}
	}

	public void showMenu(Menu menu) {
		try {
			date.setText(TimerUtils.changeTimeFormat(
					Constants.TIME_FORMET_STR_YYYY_MM_DD,
					Constants.TIME_FORMET_STR_N_Y_R, menu.getCookbookdate()));
		} catch (ParseException e) {
			Log.e("LiuYi", "解析时间发生异常！", e);
		}
		menuContent.setText(menu.getCookbook());
	}

	@Override
	public void onLoaderReset(Loader<MenuNetBean> arg0) {
		menuList = null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_title_fourth:
			weekMenuInterface.onWeekMenuButtonClick();
			break;
		case R.id.button_title_goback:
			getActivity().finish();
			break;
		}
	}

	public interface WeekMenuInterface {
		public void onWeekMenuButtonClick();
	}

}
