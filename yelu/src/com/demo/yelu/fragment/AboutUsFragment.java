package com.demo.yelu.fragment;

import com.demo.yelu.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutUsFragment extends Fragment implements View.OnClickListener {

	private BackButtonListener backButtonListener;
	private TextView backButton;
	private TextView titleText;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		backButtonListener = (BackButtonListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about_us, container, false);
		backButton = (TextView) view.findViewById(R.id.button_title_goback);
		backButton.setVisibility(View.VISIBLE);
		backButton.setOnClickListener(this);
		titleText = (TextView) view.findViewById(R.id.text_title);
		titleText.setText("小耶鲁幼儿园");
		return view;
	}

	public interface BackButtonListener {
		public void onBackButtonPressed();
	}

	@Override
	public void onClick(View v) {
		backButtonListener.onBackButtonPressed();
	}

}
