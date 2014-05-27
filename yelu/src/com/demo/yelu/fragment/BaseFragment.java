package com.demo.yelu.fragment;

import com.demo.yelu.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class BaseFragment extends Fragment {

	private TextView backButton;
	private TextView titleText;
	private BackButtonListener backButtonListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		backButtonListener = (BackButtonListener) activity;
	}

	protected void setupTitle(View view, String title) {
		backButton = (TextView) view.findViewById(R.id.button_title_goback);
		backButton.setVisibility(View.VISIBLE);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backButtonListener.onBackButtonPressed();
			}
		});
		titleText = (TextView) view.findViewById(R.id.text_title);
		titleText.setText("小耶鲁幼儿园");
	}

	public interface BackButtonListener {
		public void onBackButtonPressed();
	}

}
