package com.demo.yelu.fragment;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.yelu.R;
import com.demo.yelu.app.YeluApplication;
import com.demo.yelu.bean.User;
import com.demo.yelu.common.Constants;
import com.demo.yelu.datautil.DataProvider;
import com.demo.yelu.loader.LogonBuzLoader;
import com.demo.yelu.netbean.UserLoginNetBean;
import com.demo.yelu.util.TimerUtils;

public class LogonFragment extends Fragment implements
		LoaderCallbacks<UserLoginNetBean>, OnClickListener {

	UserLoginNetBean requestBean;
	private EditText username;
	private EditText password;
	private TextView loginButton;
	private TextView changePasswordButton;
	private ProgressInterface progress;
	private LoginInterface loginInterface;
	private boolean canReloader = false;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		progress = (ProgressInterface) activity;
		loginInterface = (LoginInterface) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_layout, container, false);
		username = (EditText) view.findViewById(R.id.username_login);
		password = (EditText) view.findViewById(R.id.password_login);
		loginButton = (TextView) view.findViewById(R.id.login_button);
		changePasswordButton = (TextView) view
				.findViewById(R.id.change_password_button);
		loginButton.setOnClickListener(this);
		changePasswordButton.setOnClickListener(this);
		return view;
	}

	@Override
	public Loader<UserLoginNetBean> onCreateLoader(int arg0, Bundle arg1) {
		return new LogonBuzLoader(getActivity(), requestBean);
	}

	@Override
	public void onLoadFinished(Loader<UserLoginNetBean> arg0,
			UserLoginNetBean requestBean) {
		progress.hide();
		this.requestBean = requestBean;
		if (DataProvider.isLogon) {
			DataProvider.user = requestBean.getBean();
			Editor editor = YeluApplication.getInstance()
					.getSharedPreferences().edit();
			editor.putString("username", requestBean.getBean().getUsername());
			editor.putBoolean("isLogon", true);
			editor.putString("logonTime",
					TimerUtils.getTime(Constants.TIME_FORMET_STR_SS));
			editor.putString("tag", requestBean.getBean().getTag());
			editor.commit();
			Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_LONG).show();
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					password.setText("");
					loginInterface.onLoginSuccess();
				}
			});
		} else if (TextUtils.equals(requestBean.getCode(), "0")) {
			Toast.makeText(getActivity(), requestBean.getErrorMsg(),
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getActivity(), "网络错误，请检查您的设备当前网络状态",
					Toast.LENGTH_LONG).show();
		}
		canReloader = true;
	}

	@Override
	public void onLoaderReset(Loader<UserLoginNetBean> arg0) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			if (!TextUtils.isEmpty(username.getText().toString())
					&& !TextUtils.isEmpty(password.getText().toString())) {
				progress.show("登录中...");
				User user = new User();
				user.setUsername(username.getText().toString());
				user.setPassword(password.getText().toString());
				requestBean = new UserLoginNetBean("345353534535", user);
				if (canReloader) {
					getLoaderManager().restartLoader(0, null, this);
				} else {
					getLoaderManager().initLoader(0, null, this);
				}
			} else {
				Toast.makeText(getActivity(), "用户名与密码不能为空！", Toast.LENGTH_LONG)
						.show();
			}
			break;
		case R.id.change_password_button:
			Toast.makeText(getActivity(), "功能正在建设中，敬请期待！", Toast.LENGTH_SHORT)
					.show();
			break;
		}
	}

	public interface LoginInterface {
		public void onLoginSuccess();
	}
}
