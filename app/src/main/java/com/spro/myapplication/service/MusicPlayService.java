package com.spro.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spro.myapplication.Configs.Configs;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/28.
 */

public class MusicPlayService extends Service {
    MediaPlayer mp;


    //创建Service
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAL", "onCreate");
        mp= new MediaPlayer();

    }

    //启动Service
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("TAL", "onStart");

        playmusic();

    }

    //销毁Service
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAL", "onDestroy");
    }

    //绑定Activity
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAL", "onBind");
        //返回值为 建的Binder的类
        return new MyIBinder(mp);
    }

    // 建个Binder的类，绑定Activity
    public class MyIBinder extends Binder {
     public MediaPlayer mp01;
     public MyIBinder(MediaPlayer mp){
         mp01=mp;
     }



    }

    /**
     * 播放音乐
     */
    public void playmusic() {
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.reset();
        try {
            mp.setDataSource(Configs.musicMessageVos.get(Configs.position).getPath());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();

    }


}
