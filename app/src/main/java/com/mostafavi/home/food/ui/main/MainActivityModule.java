package com.mostafavi.home.food.ui.main;

import com.mostafavi.home.food.util.rx.SchedulerProvider;

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
}
