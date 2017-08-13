package com.moki.databindingdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moki.databindingdemo.viewmodel.MainActivityViewModel;
import com.zksyp.databindingdemo.R;
import com.zksyp.databindingdemo.databinding.ActivityMainCopyBinding;

/**
 * Copyright © 2017 Worktile. All Rights Reserved.
 * Author: Moki
 * Email: mosicou@163.com
 * Date: 2017/8/13
 * Time: 13:09
 * Desc:
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainCopyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_copy);
        binding.setViewModel(new MainActivityViewModel());
    }
}
