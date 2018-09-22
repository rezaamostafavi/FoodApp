package com.mostafavi.home.food.ui.main;

import android.view.View;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.ui.base.BaseNavigator;

import java.util.List;

/**
 * Created by Reza on 9/16/2018.
 */

public interface MainNavigator extends BaseNavigator{

    void OnItemLick(String food, View image);

    void refreshList(List<Food> foods);
}
