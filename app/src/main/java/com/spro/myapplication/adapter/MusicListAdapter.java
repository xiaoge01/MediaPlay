package com.spro.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spro.myapplication.R;
import com.spro.myapplication.entily.MusicMessageVo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/22.
 */

public class MusicListAdapter extends BaseAdapter {

    private ArrayList<MusicMessageVo> musicMessageVos;

    public MusicListAdapter(ArrayList<MusicMessageVo> musicMessageVos) {
        this.musicMessageVos = musicMessageVos;
    }

    @Override
    public int getCount() {
        return musicMessageVos.size();
    }

    @Override
    public Object getItem(int i) {
        return musicMessageVos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.musiclistadapter, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(musicMessageVos.get(i).getName());
        viewHolder.singer.setText(musicMessageVos.get(i).getSinger());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.singer)
        TextView singer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
