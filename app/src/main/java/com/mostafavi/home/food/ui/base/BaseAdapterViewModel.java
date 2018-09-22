package com.mostafavi.home.food.ui.base;

import android.arch.lifecycle.ViewModel;
import android.view.View;


public abstract class BaseAdapterViewModel<T> extends ViewModel {

    public interface OnItemClickListener<T> {
        void onClick(T item, View view);
    }

    protected OnItemClickListener itemClickListener;
    protected T item;

    public BaseAdapterViewModel(T item, OnItemClickListener<T> itemClickListener) {
        this.item = item;
        this.itemClickListener = itemClickListener;
    }

}
