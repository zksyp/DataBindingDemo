package com.moki.databindingdemo.utils;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Copyright © 2017 Worktile. All Rights Reserved.
 * Author: Moki
 * Email: mosicou@163.com
 * Date: 2017/8/13
 * Time: 13:46
 * Desc:
 */

public class RecyclerViewBindingAdapter {
    @BindingAdapter("data")
    public static void setRecyclerViewAdapterOrNotifyChange(RecyclerView recyclerView, ObservableArrayList<? extends RecyclerViewItemViewModel> data) {
        final SimpleBindingRecyclerViewAdapter adapter = new SimpleBindingRecyclerViewAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        data.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<? extends RecyclerViewItemViewModel>>() {
            @Override
            public void onChanged(ObservableList<? extends RecyclerViewItemViewModel> sender) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<? extends RecyclerViewItemViewModel> sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<? extends RecyclerViewItemViewModel> sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<? extends RecyclerViewItemViewModel> sender, int fromPosition, int toPosition, int itemCount) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<? extends RecyclerViewItemViewModel> sender, int positionStart, int itemCount) {
                adapter.notifyItemRemoved(positionStart);
            }
        });
    }
}
