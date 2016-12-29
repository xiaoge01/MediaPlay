package com.spro.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.spro.myapplication.R;
import com.spro.myapplication.util.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/23.
 */

public class SplashActivity extends Activity {


    @Bind(R.id.splash_background)
    ImageView splashBackground;
    @Bind(R.id.splash_iv)
    CircleImageView splashIv;
    @Bind(R.id.splash_suishenting)
    TextView splashSuishenting;
    @Bind(R.id.splash_xiang)
    TextView splashXiang;
    @Bind(R.id.splash_ting01)
    TextView splashTing01;
    @Bind(R.id.splash_jiu)
    TextView splashJiu;
    @Bind(R.id.splash_ting02)
    TextView splashTing02;
    @Bind(R.id.splash_jiu01)
    TextView splashJiu01;
    @Bind(R.id.splash_shi)
    TextView splashShi;
    @Bind(R.id.splash_na)
    TextView splashNa;
    @Bind(R.id.splash_me)
    TextView splashMe;
    @Bind(R.id.splash_ren)
    TextView splashRen;
    @Bind(R.id.splash_xing)
    TextView splashXing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        handler.sendEmptyMessage(1);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Animation animation01 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);
                    splashBackground.startAnimation(animation01);
                    handler.sendEmptyMessageDelayed(2, 2500);
                    break;
                case 2:
                    splashIv.setImageResource(R.drawable.splash_01);
                    Animation animation02 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_ivanim);
                    splashIv.startAnimation(animation02);
                    handler.sendEmptyMessageDelayed(3, 2000);
                    break;
                case 3:
                    splashSuishenting.setText("随身听");
                    Animation animation03 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_nameanim);
                    splashSuishenting.startAnimation(animation03);
                    handler.sendEmptyMessageDelayed(4, 2000);
                    break;
                case 4:
                    splashXiang.setVisibility(View.VISIBLE);
                    Animation animation04 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashXiang.startAnimation(animation04);
                    handler.sendEmptyMessageDelayed(5, 200);
                    break;
                case 5:
                    splashTing01.setVisibility(View.VISIBLE);
                    Animation animation05 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashTing01.startAnimation(animation05);
                    handler.sendEmptyMessageDelayed(6, 200);
                    break;
                case 6:
                    splashJiu.setVisibility(View.VISIBLE);
                    Animation animation06 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashJiu.startAnimation(animation06);
                    handler.sendEmptyMessageDelayed(7, 200);
                    break;
                case 7:
                    splashTing02.setVisibility(View.VISIBLE);
                    Animation animation07 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashTing02.startAnimation(animation07);
                    handler.sendEmptyMessageDelayed(8, 200);
                    break;
                case 8:
                    splashJiu01.setVisibility(View.VISIBLE);
                    Animation animation08 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashJiu01.startAnimation(animation08);
                    handler.sendEmptyMessageDelayed(9, 200);
                    break;
                case 9:
                    splashShi.setVisibility(View.VISIBLE);
                    Animation animation09 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashShi.startAnimation(animation09);
                    handler.sendEmptyMessageDelayed(10, 200);
                    break;
                case 10:
                    splashNa.setVisibility(View.VISIBLE);
                    Animation animation10 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashNa.startAnimation(animation10);
                    handler.sendEmptyMessageDelayed(11, 200);
                    break;
                case 11:
                    splashMe.setVisibility(View.VISIBLE);
                    Animation animation11 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashMe.startAnimation(animation11);
                    handler.sendEmptyMessageDelayed(12, 200);
                    break;
                case 12:
                    splashRen.setVisibility(View.VISIBLE);
                    Animation animation12 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashRen.startAnimation(animation12);
                    handler.sendEmptyMessageDelayed(13, 200);
                    break;
                case 13:
                    splashXing.setVisibility(View.VISIBLE);
                    Animation animation13 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_textanim);
                    splashXing.startAnimation(animation13);
                    handler.sendEmptyMessageDelayed(14, 2000);
                    break;
                case 14:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    break;
                case 15:

                    break;

            }
        }
    };
}
