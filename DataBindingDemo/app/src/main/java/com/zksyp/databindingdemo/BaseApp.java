package com.zksyp.databindingdemo;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.SavePowerConfig;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnicornImageLoader;
import com.qiyukf.unicorn.api.YSFOptions;

/**
 * Created with Android Studio.
 * User: kaishen
 * Date: 2017/8/14
 * Time: 下午8:47
 * Desc:
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Unicorn.init(getApplicationContext(), "ea563060280d88e081b95c3f2ef0a415", options()
                , new UnicornImageLoader() {
                    @Nullable
                    @Override
                    public Bitmap loadImageSync(String s, int i, int i1) {
                        return null;
                    }

                    @Override
                    public void loadImage(String uri, int width, int height, ImageLoaderListener listener) {

                    }
                });
    }

    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.savePowerConfig = new SavePowerConfig();
        return options;
    }
}
