package com.mostafavi.home.food.ui.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List items;
    protected BaseAdapterViewModel.OnItemClickListener itemClickListener;


    public BaseAdapter(List items) {
        this.items = items;
    }

    public BaseAdapterViewModel.OnItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(BaseAdapterViewModel.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateList(List items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
