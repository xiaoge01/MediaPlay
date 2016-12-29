package com.spro.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.spro.myapplication.R;
import com.spro.myapplication.adapter.MyViewPagerAdapter;
import com.spro.myapplication.entily.MusicMessageVo;
import com.spro.myapplication.fragment.LeftFragmentMenu;
import com.spro.myapplication.fragment.RightFragmentMenu;
import com.spro.myapplication.service.MusicPlayService;
import com.spro.myapplication.util.CircleImageView;
import com.spro.myapplication.util.MusicGetPic;
import com.spro.myapplication.util.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity implements SeekBar.OnSeekBarChangeListener {


    @Bind(R.id.tablelayout)
    TabLayout tablelayout;
    @Bind(R.id.main_viewpager)
    ViewPager mainViewpager;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.circle_iv)
    CircleImageView circleIv;
    @Bind(R.id.song)
    TextView song;
    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.play)
    ImageView play;


    private MyViewPagerAdapter adapter;
    private SlidingMenu slidingMenu;
    private FragmentManager fm;
    private ArrayList<String> namelist;
    private LeftFragmentMenu leftFragmentMenu;
    private RightFragmentMenu rightFragmentMenu;
    private FragmentTransaction ft;

    private MusicPlayService.MyIBinder myIBinder;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        animation.setInterpolator(new LinearInterpolator());//匀速插值器

        seekbar.setOnSeekBarChangeListener(this);//设置滑动监听
        binderService();//绑定Service
        showViewpager();//展示ViewPager

        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                adapter.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        initSlidingMenu();//侧滑菜单


        tablelayout.setupWithViewPager(mainViewpager);
    }

    /**
     * 展示ViewPager
     */
    public void showViewpager() {
        fm = getSupportFragmentManager();
        namelist = new ArrayList<>();
        namelist.add("我的音乐");
        namelist.add("本地音乐");
        adapter = new MyViewPagerAdapter(fm, namelist);
        mainViewpager.setAdapter(adapter);
    }

    /**
     * 侧滑菜单
     */
    public void initSlidingMenu() {
        //初始化 slidingMenu
        slidingMenu = new SlidingMenu(this);
        //设置为左右两边菜单栏
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //设置全屏范围都可以打开菜单栏 TOUCHMODE_FULLSCREEN，边角打开TOUCHMODE_MARGIN,不可打开TOUCHMODE_NONE
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置菜单栏的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置菜单栏与类的关联：当前类显示的为菜单栏的中间界面
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        //设置左菜单栏样式（能直接传xml布局或者直接传View）
        slidingMenu.setMenu(R.layout.leftfragment);
        //设置关闭监听
        slidingMenu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {
                activityMain.setBackgroundColor(getResources().getColor(R.color.background01));
            }
        });

        //设置右菜单栏样式（能直接传xml布局或者直接传View）
        slidingMenu.setSecondaryMenu(R.layout.rightfragment);
        //先打开一个空白xml,用Fragment替换
        leftFragmentMenu = new LeftFragmentMenu();
        rightFragmentMenu = new RightFragmentMenu();

        ft = fm.beginTransaction();
        ft.replace(R.id.left, leftFragmentMenu);
        ft.replace(R.id.right, rightFragmentMenu);
        ft.commit();


    }

    /**
     * 点击监听
     */
    @OnClick({R.id.main_menu, R.id.main_setting, R.id.play,R.id.circle_iv})
    public void onClick01(View view) {

        switch (view.getId()) {
            case R.id.main_menu:
                slidingMenu.showMenu();
                activityMain.setBackgroundColor(getResources().getColor(R.color.background));
                break;
            case R.id.main_setting:
                slidingMenu.showSecondaryMenu();
                activityMain.setBackgroundColor(getResources().getColor(R.color.background));
                break;
            case R.id.play:
                if (myIBinder.mp01.isPlaying()) {
                    play.setImageResource(R.drawable.ic_play);
                    myIBinder.mp01.pause();
                    animation.cancel();
                } else {
                    play.setImageResource(R.drawable.ic_pause);
                    myIBinder.mp01.start();
                    animation.start();
                }
                break;
            case R.id.circle_iv:
                Intent intent=new Intent(this,PlayMusicActivity.class);
                startActivity(intent);
                break;

        }
    }

    /**
     * 绑定Service
     */
    public void binderService() {
        Intent intent = new Intent(this, MusicPlayService.class);
        //绑定Service (intent,ServiceConnection的一个对象，BIND_AUTO_CREATE)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //初始化 ServiceConnection
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myIBinder = (MusicPlayService.MyIBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    /**
     * 播放音乐
     */
    public void playmusic(int position, List<MusicMessageVo> musicMessageVos) {

        play.setImageResource(R.drawable.ic_pause);
        song.setText(musicMessageVos.get(position).getName());
        //获取图片（context,歌的Id,专辑Id,true）
        circleIv.setImageBitmap(MusicGetPic.getArtwork(this, musicMessageVos.get(position).getSongid(), musicMessageVos.get(position).getAlbumid(), true));
        circleIv.startAnimation(animation);
        handler.sendEmptyMessage(1);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (myIBinder.mp01.getDuration() >= myIBinder.mp01.getCurrentPosition()) {
                    seekbar.setMax(myIBinder.mp01.getDuration());
                    seekbar.setProgress(myIBinder.mp01.getCurrentPosition());
                    handler.sendEmptyMessageDelayed(1, 200);
                }

            }
        }
    };

    /**
     * seekBar 监听
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        myIBinder.mp01.seekTo(seekBar.getProgress());
    }

    @OnClick(R.id.circle_iv)
    public void onClick() {
    }
}
