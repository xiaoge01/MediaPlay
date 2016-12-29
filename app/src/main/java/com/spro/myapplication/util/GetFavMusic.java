package com.spro.myapplication.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.spro.myapplication.activity.MainActivity;
import com.spro.myapplication.entily.MusicMessageVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class GetFavMusic {
    private ArrayList<MusicMessageVo> musicMessageVos;
    private Context context;

    public GetFavMusic (Context context){
        this.context=context;

    }
    public List<MusicMessageVo> getmusic() {
        musicMessageVos = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//资源位置
        ContentResolver contentResolver = context.getContentResolver();//ContentProvider 的核心类
        //查找所有音乐
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取歌名
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            //获取歌手
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            //获取专辑
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            //获取ID
            int songid = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            //获取专辑ID
            int albumid = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            //获取内存
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            //获取路径
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));


            Log.e("MUSIC", name + "," + singer + "," + album + "," + songid + "," + albumid + "," + size);
            musicMessageVos.add(new MusicMessageVo(name, singer, album, songid, albumid, size, path));
        }
        return musicMessageVos;
    }
}
