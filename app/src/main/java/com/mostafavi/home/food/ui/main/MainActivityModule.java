package com.mostafavi.home.food.ui.main;

import com.mostafavi.home.food.ui.main.adapter.FoodAdapter;
import com.mostafavi.home.food.util.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Reza on 9/16/2018.
 */
@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(SchedulerProvider schedulerProvider){
        return new MainViewModel(schedulerProvider);
    }

    @Provides
    FoodAdapter provideFoodAdapter(){
        return new FoodAdapter(new ArrayList());
    }
}
