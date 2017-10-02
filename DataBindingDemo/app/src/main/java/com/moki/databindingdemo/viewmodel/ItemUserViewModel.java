package com.moki.databindingdemo.viewmodel;

import android.databinding.ObservableField;

import com.moki.databindingdemo.utils.RecyclerViewItemViewModel;
import com.zksyp.databindingdemo.BR;
import com.zksyp.databindingdemo.R;
import com.zksyp.databindingdemo.UserInfo;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright © 2017 Worktile. All Rights Reserved.
 * Author: Moki
 * Email: mosicou@163.com
 * Date: 2017/8/13
 * Time: 14:12
 * Desc:
 */

public class ItemUserViewModel implements RecyclerViewItemViewModel {
    public ObservableField<String> mName = new ObservableField<>();
    public ObservableField<String> mGender = new ObservableField<>();
    public ObservableField<String> mAge = new ObservableField<>();
    public ObservableField<String> mRemain = new ObservableField<>();

    @Override
    public int getLayoutId() {
        return R.layout.item_user_copy;
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    public static ItemUserViewModel fromUserInfo(UserInfo userInfo) {
        ItemUserViewModel viewModel = new ItemUserViewModel();
        viewModel.setName(userInfo);
        viewModel.setGender(userInfo);
        viewModel.setAge(userInfo);
        viewModel.setRemain(userInfo);
        return viewModel;
    }

    private void setName(UserInfo userInfo) {
        mName.set(userInfo.name);
    }

    private void setGender(UserInfo userInfo) {
        mGender.set(userInfo.gender);
    }

    private void setAge(UserInfo userInfo) {
        mAge.set(userInfo.age);
    }

    private void setRemain(final UserInfo userInfo) {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (userInfo.remainTime != null && userInfo.remainTime > 0) {
                            mRemain.set(userInfo.remainTime > 0
                                    ? String.format("剩余%s", getCountdown(userInfo.remainTime))
                                    : "已超时");
                            userInfo.remainTime--;
                        }
                    }
                });
    }

    private static String getCountdown(Long second) {
        long day = second / 86400;
        long hour = second % 86400 / 3600;
        long minute = second % 3600 / 60;
        long seconds = second % 60;

        DecimalFormat format = new DecimalFormat("00");
        String builder = String.valueOf(day) + "天" +
                format.format(hour) + "小时" +
                format.format(minute) + "分" +
                format.format(seconds) + "秒";

        return builder;
    }
}
