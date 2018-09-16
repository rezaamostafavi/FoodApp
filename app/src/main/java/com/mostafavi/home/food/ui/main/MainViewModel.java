package com.mostafavi.home.food.ui.main;

import com.mostafavi.home.food.ui.base.BaseViewModel;
import com.mostafavi.home.food.util.rx.SchedulerProvider;

/**
 * Created by Reza on 9/16/2018.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public MainViewModel(SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }
}
