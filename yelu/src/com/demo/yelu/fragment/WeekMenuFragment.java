package com.demo.yelu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.yelu.R;
import com.demo.yelu.adapter.WeekMenuListAdapter;
import com.demo.yelu.datautil.DataProvider;

public class WeekMenuFragment extends Fragment implements View.OnClickListener {

	private TextView backButton;
	private TextView titleText;
	private ListView listView;

	private WeekMenuBackInterface weekMenuBackInterface;
	private WeekMenuListAdapter adapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		weekMenuBackInterface = (WeekMenuBackInterface) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.week_menu_list, container, false);
		backButton = (TextView) view.findViewById(R.id.button_title_goback);
		backButton.setVisibility(View.VISIBLE);
		backButton.setOnClickListener(this);
		titleText = (TextView) view.findViewById(R.id.text_title);
		titleText.setText("一周餐单");
		listView = (ListView) view.findViewById(R.id.week_menu_list);
		adapter = new WeekMenuListAdapter(inflater, DataProvider.menuList);
		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_title_goback:
			weekMenuBackInterface.onBackButtonPressed();
			break;
		}
	}

	public interface WeekMenuBackInterface {
		public void onBackButtonPressed();
	}

}
