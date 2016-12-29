package com.spro.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.spro.myapplication.R;
import com.spro.myapplication.activity.MainActivity;
import com.spro.myapplication.adapter.SettingCheckLsitAdapter;
import com.spro.myapplication.adapter.SettingLsitAdapter;
import com.spro.myapplication.entily.SettingCheckLisetAdapterVo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */

public class RightFragmentMenu extends Fragment {
    @Bind(R.id.setting_check_listview)
    ListView settingCheckListview;
    @Bind(R.id.setting_listview)
    ListView settingListview;

    public SettingCheckLsitAdapter adapter;
    private SettingLsitAdapter adapter01;
    private MainActivity activity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rightmenu_fragment, null);
        ButterKnife.bind(this, view);
        getchecklist();
        getlist();

        return view;
    }

    /**
     * 获取checklist列表
     */
    public void getchecklist() {

        ArrayList<SettingCheckLisetAdapterVo> arrayList = new ArrayList<>();

        SettingCheckLisetAdapterVo adapterVo = new SettingCheckLisetAdapterVo();
        adapterVo.setName("开机启动");
        adapterVo.setIscheck(false);

        SettingCheckLisetAdapterVo adapterVo01 = new SettingCheckLisetAdapterVo();
        adapterVo01.setName("通知图标");
        adapterVo01.setIscheck(false);

        SettingCheckLisetAdapterVo adapterVo02 = new SettingCheckLisetAdapterVo();
        adapterVo02.setName("消息推送");
        adapterVo02.setIscheck(false);

        arrayList.add(adapterVo);
        arrayList.add(adapterVo01);
        arrayList.add(adapterVo02);

        adapter = new SettingCheckLsitAdapter(arrayList, activity);
        settingCheckListview.setAdapter(adapter);
    }

    /**
     * 获取list列表
     */
    public void getlist() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("帮助说明");
        nameList.add("意见反馈");
        nameList.add("好友分享");
        nameList.add("版本更新");
        nameList.add("关于我们");
        adapter01 = new SettingLsitAdapter(nameList);
        settingListview.setAdapter(adapter01);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
