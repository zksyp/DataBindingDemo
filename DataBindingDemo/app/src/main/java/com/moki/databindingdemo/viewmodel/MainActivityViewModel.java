package com.moki.databindingdemo.viewmodel;

import android.databinding.ObservableArrayList;

import com.zksyp.databindingdemo.UserInfo;

/**
 * Copyright © 2017 Worktile. All Rights Reserved.
 * Author: Moki
 * Email: mosicou@163.com
 * Date: 2017/8/13
 * Time: 14:11
 * Desc:
 */

public class MainActivityViewModel {
    public ObservableArrayList<ItemUserViewModel> mData = new ObservableArrayList<>();

    public MainActivityViewModel() {
        initData();
    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.name = "赵凯";
            userInfo.gender = "男";
            userInfo.age = "23";
            userInfo.remainTime = 1000L;

            mData.add(ItemUserViewModel.fromUserInfo(userInfo));
        }
    }
}
