package com.zksyp.databindingdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zksyp.databindingdemo.databinding.ItemUserBinding;

import java.util.List;

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
}
