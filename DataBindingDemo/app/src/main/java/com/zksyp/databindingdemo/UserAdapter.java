package com.zksyp.databindingdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zksyp.databindingdemo.databinding.ItemUserBinding;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * User: kaishen
 * Date: 2017/8/9
 * Time: 下午11:07
 * Desc:
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private List<UserInfo> mUserInfoList;

    public UserAdapter(Context context, List<UserInfo> list) {
        mContext = context;
        mUserInfoList = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binder = DataBindingUtil.inflate(LayoutInflater.from(mContext)
                , R.layout.item_user, parent, false);
        UserViewHolder holder = new UserViewHolder(binder.getRoot());
        holder.setBinder(binder);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.setInfo(mUserInfoList.get(position));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            String timeStr = mUserInfoList.get(position).remainTime > 0 ? String.format("剩余%s"
                    , getCountdown(mUserInfoList.get(position).remainTime)) : "已超时";
            holder.binder.tvCountdown.setText(timeStr);
        }
    }

    @Override
    public int getItemCount() {
        return mUserInfoList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private boolean isShowGender = false;
        ItemUserBinding binder;

        public void setBinder(ItemUserBinding binder) {
            this.binder = binder;
        }

        public void setInfo(UserInfo info) {
            isShowGender = false;
            binder.setHolder(this);
            binder.setUserInfo(info);
        }

        public UserViewHolder(View itemView) {
            super(itemView);
        }

        public void showGenderClick() {
            binder.tvGender.setVisibility(isShowGender ? View.GONE : View.VISIBLE);
            isShowGender = !isShowGender;
        }
    }

    private Subscription countdownTask;

    public void startCountdown() {
        countdownTask = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        for (int i = 0; i < mUserInfoList.size(); i++) {
                            UserInfo info = mUserInfoList.get(i);
                            if (info != null && info.remainTime != null && info.remainTime > 0) {
                                info.remainTime--;
                                notifyItemChanged(i, true);
                            }
                        }
                    }
                });
    }

    public void closeCountdown() {
        if (countdownTask != null && !countdownTask.isUnsubscribed()) {
            countdownTask.unsubscribe();
        }
    }

    public static String getCountdown(Long second) {
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
