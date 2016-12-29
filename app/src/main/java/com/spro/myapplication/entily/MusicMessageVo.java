package com.spro.myapplication.entily;

import android.provider.MediaStore;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/22.
 */

public class MusicMessageVo implements Serializable{
    //获取歌名
    private String name;
    //获取歌手
    private String singer;
    //获取专辑
    private String album ;
    //获取ID
    private int songid ;
    //获取专辑ID
    private int albumid ;
    //获取内存
    private long size;
    //获取路径
    private String path;

    public MusicMessageVo(String name, String singer, String album, int songid, int albumid, long size, String path) {
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.songid = songid;
        this.albumid = albumid;
        this.size = size;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
