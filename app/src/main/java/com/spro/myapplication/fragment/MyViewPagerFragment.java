package com.spro.myapplication.fragment;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.spro.myapplication.Configs.Configs;
import com.spro.myapplication.R;
import com.spro.myapplication.activity.MainActivity;
import com.spro.myapplication.activity.PlayMusicActivity;
import com.spro.myapplication.adapter.MusicListAdapter;
import com.spro.myapplication.entily.MusicMessageVo;
import com.spro.myapplication.service.MusicPlayService;
import com.spro.myapplication.util.GetFavMusic;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/23.
 */

public class MyViewPagerFragment extends Fragment {
    @Bind(R.id.viewpager_fragment_lv)
    ListView viewpagerFragmentLv;
    @Bind(R.id.mymusic_text)
    TextView mymusicText;
    @Bind(R.id.mymusic_login)
    Button mymusicLogin;

    private ArrayList<MusicMessageVo> musicMessageVos;
    private int position;
    private MainActivity activity;
    private MusicPlayService.MyIBinder myIBinder;


    public MyViewPagerFragment(int position) {
        this.position = position;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpagerfragment, null);
        ButterKnife.bind(this, view);


        getmusic();
        showlist();

        viewpagerFragmentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activity.playmusic(i, musicMessageVos);
                Configs.isdj=true;
                Configs.position=i;
                Configs.musicMessageVos=musicMessageVos;
                Intent intent = new Intent(activity, MusicPlayService.class);
                activity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                activity.startService(intent);

            }
        });
        return view;
    }

    /**
     * 初始化 ServiceConnection
     */
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //使Activity与Service建立连接
            myIBinder = (MusicPlayService.MyIBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //使Activity与Service取消连接
        }
    };


    /**
     * 展示列表
     */
    public void showlist() {
        if (position == 0) {
            if (Configs.islogin) {
                viewpagerFragmentLv.setAdapter(new MusicListAdapter(musicMessageVos));
            } else {
                mymusicText.setVisibility(View.VISIBLE);
                mymusicLogin.setVisibility(View.VISIBLE);
            }
        } else if (position == 1) {
            viewpagerFragmentLv.setAdapter(new MusicListAdapter(musicMessageVos));
            Log.e("musicMessageVos", "musicMessageVos:" + musicMessageVos.size());
        } else if (position == -1) {
            Toast.makeText(activity, "获取列表失败", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 获取手机上的所有音乐(需要音乐资源地址：Uri)
     */
    public void getmusic() {
        GetFavMusic getFavMusic = new GetFavMusic(activity);

        musicMessageVos = new ArrayList<>();
        musicMessageVos.addAll(getFavMusic.getmusic());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.mymusic_login)
    public void onClick() {
    }
}
