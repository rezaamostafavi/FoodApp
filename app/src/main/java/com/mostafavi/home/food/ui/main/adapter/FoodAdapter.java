package com.mostafavi.home.food.ui.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.databinding.ItemLstFoodBinding;
import com.mostafavi.home.food.interfaces.ListItemClickListener;
import com.mostafavi.home.food.ui.base.BaseAdapter;
import com.mostafavi.home.food.ui.base.BaseAdapterViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Reza on 2/2/2018.
 */

public class FoodAdapter extends BaseAdapter<FoodAdapter.Holder> {


    public FoodAdapter(List items) {
        super(items);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lst_food, parent, false);
        ItemLstFoodBinding binding = DataBindingUtil.bind(view);
        return new Holder(view, binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int pos) {
        final int position = holder.getAdapterPosition();
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ItemLstFoodBinding binding;

        public Holder(View itemView, ItemLstFoodBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void onBind(int position) {
            Food food = (Food) items.get(position);
            FoodAdapterViewModel viewModel = new FoodAdapterViewModel(food, itemClickListener);
            binding.setViewModel(viewModel);
            viewModel.init();
        }
    }
}
