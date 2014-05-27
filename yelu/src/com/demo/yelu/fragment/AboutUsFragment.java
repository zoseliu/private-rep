package com.demo.yelu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.yelu.R;

public class AboutUsFragment extends BaseFragment {


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about_us, container, false);
		super.setupTitle(view, "小耶鲁幼儿园");
		return view;
	}


}
