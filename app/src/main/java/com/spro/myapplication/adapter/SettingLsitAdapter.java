package com.spro.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spro.myapplication.R;

import java.util.ArrayList;

public class SettingLsitAdapter extends BaseAdapter {

	private ArrayList<String> nameList;

	public SettingLsitAdapter(ArrayList<String> nameList) {
		super();
		this.nameList = nameList;
	}

	@Override
	public int getCount() {
		return nameList.size();
	}

	@Override
	public Object getItem(int position) {
		return nameList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settinglistadapter, null);
			viewHolder.name = (TextView) convertView.findViewById(R.id.settinglistadapter_text);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(nameList.get(position));

		return convertView;
	}

	public class ViewHolder {
		TextView name;

	}

}
