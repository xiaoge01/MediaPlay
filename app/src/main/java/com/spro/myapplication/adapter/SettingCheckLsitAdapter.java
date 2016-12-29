package com.spro.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.spro.myapplication.R;
import com.spro.myapplication.entily.SettingCheckLisetAdapterVo;

import java.util.ArrayList;

public class SettingCheckLsitAdapter extends BaseAdapter {
	private ArrayList<SettingCheckLisetAdapterVo> arrayList;
	private Context context;

	public SettingCheckLsitAdapter(ArrayList<SettingCheckLisetAdapterVo> arrayList, Context context) {
		super();
		this.arrayList = arrayList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.settingchecklistadapter, null);
			viewHolder.name = (TextView) convertView.findViewById(R.id.settingchecklistadapter_text);
			viewHolder.check = (CheckBox) convertView.findViewById(R.id.settingchecklistadapter_check);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(arrayList.get(position).getName());
		viewHolder.check.setChecked(arrayList.get(position).isIscheck());


		return convertView;
	}

	public class ViewHolder {
		TextView name;
		CheckBox check;

	}

}
