package com.moki.databindingdemo.utils;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Copyright © 2017 Worktile. All Rights Reserved.
 * Author: Moki
 * Email: mosicou@163.com
 * Date: 2017/8/13
 * Time: 13:11
 * Desc:
 */

public class SimpleBindingRecyclerViewAdapter extends RecyclerView.Adapter<SimpleBindingRecyclerViewAdapter.ViewHolder> {

    private ObservableArrayList<? extends RecyclerViewItemViewModel> mData;

    public SimpleBindingRecyclerViewAdapter(ObservableArrayList<? extends RecyclerViewItemViewModel> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerViewItemViewModel itemViewModel = mData.get(position);
        holder.getBinding().setVariable(itemViewModel.getVariableId(), itemViewModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        RecyclerViewItemViewModel itemViewModel = mData.get(position);
        return itemViewModel.getLayoutId();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mViewDataBinding = binding;
        }

        ViewDataBinding getBinding() {
            return mViewDataBinding;
        }
    }
}
