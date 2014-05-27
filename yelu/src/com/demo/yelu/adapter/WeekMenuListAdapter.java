package com.demo.yelu.adapter;

import java.text.ParseException;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.yelu.R;
import com.demo.yelu.bean.Menu;
import com.demo.yelu.bean.WeekMenuListHolder;
import com.demo.yelu.common.Constants;
import com.demo.yelu.util.TimerUtils;

public class WeekMenuListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Menu> list;

	public WeekMenuListAdapter(LayoutInflater inflater, List<Menu> list) {
		this.inflater = inflater;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Menu getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeekMenuListHolder weekMenuListHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.week_menu_item, parent,
					false);
			weekMenuListHolder = new WeekMenuListHolder();
			weekMenuListHolder.cookcontent = (TextView) convertView
					.findViewById(R.id.id_week_menu_week);
			weekMenuListHolder.dayby = (TextView) convertView
					.findViewById(R.id.id_week_item_week_day);
			weekMenuListHolder.timeTV = (TextView) convertView
					.findViewById(R.id.id_week_item_time);
			convertView.setTag(weekMenuListHolder);
		} else {
			weekMenuListHolder = (WeekMenuListHolder) convertView.getTag();
		}
		try {
			weekMenuListHolder.timeTV.setText(TimerUtils.changeTimeFormat(
					Constants.TIME_FORMET_STR_YYYY_MM_DD,
					Constants.TIME_FORMET_STR_N_Y_R, getItem(position)
							.getCookbookdate()));
		} catch (ParseException e) {
		}
		weekMenuListHolder.dayby.setText(getItem(position).getDayby());
		weekMenuListHolder.cookcontent.setText(getItem(position).getCookbook());
		return convertView;
	}
}
