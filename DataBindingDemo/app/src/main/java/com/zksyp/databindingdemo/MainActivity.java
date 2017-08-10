package com.zksyp.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.zksyp.databindingdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.name = "赵凯";
            userInfo.gender = "男";
            userInfo.age = "23";
            userInfoList.add(userInfo);
        }
        ActivityMainBinding binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserAdapter adapter = new UserAdapter(this, userInfoList);
        binder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binder.setAdapter(adapter);
    }
}
