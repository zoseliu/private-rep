package com.demo.yelu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.yelu.R;
import com.demo.yelu.act.TodayMenuActivity;

public class HomePageFragment extends Fragment implements View.OnClickListener {
	private ProgressInterface progress;
	private LogoutInterface logoutInterface;
	private HomepageFunctionInterface homepageFunctionListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		progress = (ProgressInterface) activity;
		logoutInterface = (LogoutInterface) activity;
		homepageFunctionListener = (HomepageFunctionInterface) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_layout, container, false);
		initTitleBar(view);
		initFunctions(view);
		return view;
	}

	private void initFunctions(View view) {
		ImageView function = (ImageView) view.findViewById(R.id.id_about_us);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_child_photo);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_homework);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_news);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_class_chat);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_education);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_notification);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_today_menu);
		function.setOnClickListener(this);
		function = (ImageView) view.findViewById(R.id.id_online_apply);
		function.setOnClickListener(this);
	}

	private void initTitleBar(View view) {
		TextView titleTv = (TextView) view.findViewById(R.id.text_title);
		titleTv.setText("小耶鲁幼儿园");
		TextView logoutTv = (TextView) view
				.findViewById(R.id.button_title_thrid);
		logoutTv.setVisibility(View.VISIBLE);
		logoutTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_title_thrid:
			logoutInterface.onLogout();
			break;
		case R.id.id_about_us:
			homepageFunctionListener.onFunctionPressed(ABOUT_US);
			break;
		case R.id.id_child_photo:
			Toast.makeText(getActivity(), "孩子风采功能正在建设中，敬请期待。",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_homework:
			Toast.makeText(getActivity(), "家庭作业功能正在建设中，敬请期待。",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_news:
			Toast.makeText(getActivity(), "新闻功能正在建设中，敬请期待。", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.id_class_chat:
			Toast.makeText(getActivity(), "班级聊天功能正在建设中，敬请期待。",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_education:
			Toast.makeText(getActivity(), "教育功能正在建设中，敬请期待。", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.id_notification:
			Toast.makeText(getActivity(), "即时提醒功能正在建设中，敬请期待。",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_today_menu:
			Intent intent = new Intent(getActivity(), TodayMenuActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.id_online_apply:
			Toast.makeText(getActivity(), "在线报名功能正在建设中，敬请期待。",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

	public interface LogoutInterface {
		public void onLogout();
	}

	public static final int ABOUT_US = 0x00;

	public interface HomepageFunctionInterface {
		public void onFunctionPressed(int id);
	}

}
